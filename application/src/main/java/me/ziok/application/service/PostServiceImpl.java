package me.ziok.application.service;

import me.ziok.application.dao.PostRepository;
import me.ziok.application.model.Comment;
import me.ziok.application.model.Post;
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

    public void savePost(Post post){
        postRepository.save(post);
    }

    public Post getPost(int id, long accountId){
        Post post = postRepository.findById((long)id).get();
        post.setHits(post.getHits()+1); //조회 수 +1
        savePost(post); //조회 수 +1 update
        List<Comment> commentList = commentService.findByPostIdOrderByParentCommentIdAscIdAsc(accountId, post);
        post.setComment(commentList);
        return post;
    }

    public List<Post> getPostList(){
        return postRepository.findAll();
    }

    public List<Post> findTop5ByOrderByIdDesc(){
        return postRepository.findTop5ByOrderByIdDesc();
    }

    public List<Post> findPostByLimit(int id){
        return postRepository.findPostByLimit(id);
    }

    public List<Post> findPostByConditions(int number, int periodStart, int periodEnd){
        return postRepository.findPostByConditions(number, periodStart, periodEnd);
    }

    public List<Post> findPostByConditions(int id, int number, int periodStart, int periodEnd){
        return postRepository.findPostByConditions(id, number, periodStart, periodEnd);
    }

    public void deletePost(Post post){
        commentService.deleteAllComment(post.getId());
        postRepository.delete(post);
    }
}