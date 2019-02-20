package me.ziok.application.service;

import me.ziok.application.dao.AccountRepository;
import me.ziok.application.dao.ImageRepository;
import me.ziok.application.dao.PostRepository;
import me.ziok.application.model.*;
import me.ziok.application.util.S3Util;
import me.ziok.application.util.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.lang.Exception;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ImageService imageService;

    public void savePost(Post post, Long accountId, MultipartFile[] multipartFiles){
        Account account = accountRepository.findById(accountId).orElse(null);
        post.setAccount(account);
        post = postRepository.save(post);
        if(multipartFiles!=null && multipartFiles.length>0)
            imageService.fileUpload(multipartFiles, post.getId());
    }

    
    public void updatePost(Post post, Long accountId, String[] imageNames, MultipartFile[] multipartFiles){
        Account account = accountRepository.findById(accountId).orElse(null);
        post.setAccount(account);
        post = postRepository.save(post);
        imageService.imageUpdate(imageNames, post.getId());
        if(multipartFiles!=null && multipartFiles.length>0)
            imageService.fileUpload(multipartFiles, post.getId());
    }

    public Post loadPost(Long postId, Long accountId ){
        Account account = accountRepository.findById(accountId).orElse(null);
        Post post = postRepository.findById(postId).get();
        //todo: hits를 설정하지 않아도
        post.setHits(post.getHits()+1); //조회 수 +1
        postRepository.save(post); //조회 수 +1 update
        List<Comment> commentList = commentService.loadByPostIdOrderByParentCommentIdAscIdAsc(account.getId(), post);
        post.setComment(commentList);
        return post;
    }


    public List<Post> loadOpenPostsWithAccountId(Long accountId) {
        return postRepository.findByIsOpenTrueAndAccount_IdOrderByCreateDateDesc(accountId);
    }

    public List<Post> loadClosedPostsWithAccountId(Long accountId) {
        return postRepository.findByIsOpenFalseAndAccount_IdOrderByCreateDateDesc(accountId);
    }

    public Post loadPostByComment(Comment comment) {
        return postRepository.findByComment(comment);
    }


    public List<Post> loadTop20ByOrderByIdDesc(){
        return postRepository.findTop20ByOrderByIdDesc();
    }

    public List<Post> loadPostByLimit(Long id, PostSortType sortType){
         if(sortType == PostSortType.lowFee){
            return postRepository.findPostByLimitOrderByFee(id);
        }else{
            return postRepository.findPostByLimit(id);
        }
    }

    public List<Post> loadPostByConditions(int number, int periodStart, int periodEnd){
        return postRepository.findPostByConditions(number, periodStart, periodEnd);
    }


    public List<Post> loadPostByConditions(Long id, int number, int periodStart, int periodEnd, PostSortType sortType){
        if(sortType == PostSortType.lowFee){
            return postRepository.findPostByConditionsOrderByFee(id, number, periodStart, periodEnd);
        }else{
            return postRepository.findPostByConditions(id, number, periodStart, periodEnd);
        }
    }

    public void deleteById(Long id){
        commentService.deleteAllComment(id);
        imageService.deleteImage(id);//aws s3와 db에 저장된 해당 postId의 파일들 삭제
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> findPostsWithCommentsByAccountId(Long accountId) {

        List<Comment> commentsWrittenByAccountId = commentService.loadByAccountIdOrderByIdDesc(accountId);

        return extractUniquePostsWithComment(commentsWrittenByAccountId);


    }

    private List<Post> extractUniquePostsWithComment(List<Comment> commentsWrittenByAccountId) {
        List<Post> tempPosts = new ArrayList<>();

        List<Post> postsUnique = new ArrayList<>();

        for (Comment comment : commentsWrittenByAccountId) {
            List<Comment> tempCommentList = new ArrayList<>();
            Post postUnique = new Post();
            Post postWithComment = loadPostByComment(comment);

            if (tempPosts.contains(postWithComment)) {
                continue;
            }

            tempPosts.add(postWithComment);
            tempCommentList.add(comment);

            //todo: builder 패턴으로 바꾸기
            postUnique.setContent(postWithComment.getContent());
            postUnique.setPostName(postWithComment.getPostName());
            postUnique.setAccount(postWithComment.getAccount());
            postUnique.setId(postWithComment.getId());
            postUnique.setComment(tempCommentList);
            postUnique.setHits(postWithComment.getHits());
            postUnique.setCreateDate(postWithComment.getCreateDate());
            postUnique.setFee(postWithComment.getFee());
            postUnique.setImg(postWithComment.getImg());
            postUnique.setMembership(postWithComment.getMembership());

            postsUnique.add(postUnique);

        }

        return postsUnique;
    }

}