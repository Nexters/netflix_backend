package me.ziok.application.service;

import me.ziok.application.dao.AccountRepository;
import me.ziok.application.dao.PostRepository;
import me.ziok.application.model.Account;
import me.ziok.application.model.Comment;
import me.ziok.application.model.Post;
import me.ziok.application.model.PostSortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    AccountRepository accountRepository;

    public Post savePost(Post post){
        Account account = accountRepository.findByEmail(post.getAccount().getEmail()).orElse(null);
        post.setAccount(account);
        return postRepository.save(post);
    }

    public Post loadPost(Long id, String email){
        Account account = accountRepository.findByEmail(email).orElse(null);
        Post post = postRepository.findById(id).get();
        //todo: hits를 설정하지 않아도
        post.setHits(post.getHits()+1); //조회 수 +1
        savePost(post); //조회 수 +1 update
        List<Comment> commentList = commentService.findByPostIdOrderByParentCommentIdAscIdAsc(account.getId(), post);
        post.setComment(commentList);
        return post;
    }

    public List<Post> findTop5ByOrderByIdDesc(){
        return postRepository.findTop5ByOrderByIdDesc();
    }

    public List<Post> findPostByLimit(Long id, PostSortType sortType){
        if(sortType == PostSortType.recruitNumber){
            return postRepository.findPostByLimitOrderByRecruitNumber(id);
        }else if(sortType == PostSortType.lowFee){
            return postRepository.findPostByLimitOrderByFee(id);
        }else{
            return postRepository.findPostByLimit(id);
        }
    }

    public List<Post> findPostByConditions(int number, int periodStart, int periodEnd){
        return postRepository.findPostByConditions(number, periodStart, periodEnd);
    }

    public List<Post> findPostByConditions(Long id, int number, int periodStart, int periodEnd, PostSortType sortType){
        if(sortType == PostSortType.recruitNumber){
            return postRepository.findPostByConditionsOrderByRecruitNumber(id, number, periodStart, periodEnd);
        }else if(sortType == PostSortType.lowFee){
            return postRepository.findPostByConditionsOrderByFee(id, number, periodStart, periodEnd);
        }else{
            return postRepository.findPostByConditions(id, number, periodStart, periodEnd);
        }
    }

    public void deleteById(Long id){
        commentService.deleteAllComment(id);
        postRepository.deleteById(id);
    }
}