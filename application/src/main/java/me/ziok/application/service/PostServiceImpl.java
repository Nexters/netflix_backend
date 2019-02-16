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

    public void savePost(Post post, String email, MultipartFile[] multipartFiles){
        Account account = accountRepository.findByEmail(email).orElse(null);
        post.setAccount(account);
        post = postRepository.save(post);
        imageService.fileUpload(multipartFiles, post.getId());
    }

    public void updatePost(Post post, String email, String[] imageNames, MultipartFile[] multipartFiles){
        Account account = accountRepository.findByEmail(email).orElse(null);
        post.setAccount(account);
        post = postRepository.save(post);
        imageService.imageUpdate(imageNames, post.getId());
        imageService.fileUpload(multipartFiles, post.getId());
    }

    public Post loadPost(Long id, String email){
        Account account = accountRepository.findByEmail(email).orElse(null);
        Post post = postRepository.findById(id).get();
        //todo: hits를 설정하지 않아도
        post.setHits(post.getHits()+1); //조회 수 +1
        postRepository.save(post); //조회 수 +1 update
        List<Comment> commentList = commentService.findByPostIdOrderByParentCommentIdAscIdAsc(account.getId(), post);
        post.setComment(commentList);
        return post;
    }

    public List<Post> loadOpenPostsWithAccountId(Long accountId) {
        return postRepository.findByIsOpenTrueAndAccount_IdOrderByCreateDateDesc(accountId);
    }

    public List<Post> loadClosedPostsWithAccountId(Long accountId) {
        return postRepository.findByIsOpenFalseAndAccount_IdOrderByCreateDateDesc(accountId);
    }

    public List<Post> findTop5ByOrderByIdDesc(){
        return postRepository.findTop5ByOrderByIdDesc();
    }

    public List<Post> findPostByLimit(Long id, PostSortType sortType){
         if(sortType == PostSortType.lowFee){
            return postRepository.findPostByLimitOrderByFee(id);
        }else{
            return postRepository.findPostByLimit(id);
        }
    }

    public List<Post> findPostByConditions(int number, int periodStart, int periodEnd){
        return postRepository.findPostByConditions(number, periodStart, periodEnd);
    }


    public List<Post> findPostByConditions(Long id, int number, int periodStart, int periodEnd, PostSortType sortType){
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

}