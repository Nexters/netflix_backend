package me.ziok.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ziok.application.model.Account;
import me.ziok.application.model.Comment;
import me.ziok.application.model.Post;
import me.ziok.application.model.PostSortType;
import me.ziok.application.service.CommentService;
import me.ziok.application.service.CommentServiceImpl;
import me.ziok.application.service.PostService;
import me.ziok.application.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("posts")
public class PostController  {

    @Autowired
    PostService postService;

    //post 생성
    @RequestMapping(method= RequestMethod.POST)
    public void savePost(Post post, @RequestParam("email") String email, @RequestParam("files")MultipartFile[] multipartFiles){
        postService.savePost(post, email, multipartFiles);
    }

    //post 수정
    @RequestMapping(method= RequestMethod.PUT)
    public void updatePost(Post post, String[] imageNames, @RequestParam("email") String email, @RequestParam("files") MultipartFile[] multipartFiles){
        postService.updatePost(post, email, imageNames, multipartFiles);
    }

    //post 상세보기
    @RequestMapping(value="/{postId}", method=RequestMethod.GET)
    public Post getPost(@PathVariable("postId") Long postId, @RequestParam("email") String email){
        return postService.loadPost(postId, email);
    }

    //postList - 게시판 첫 요청 시(lastPostId x, 필터 x, 글 정렬x)
    @RequestMapping(method=RequestMethod.GET, value="/list")
    public List<Post> getPostList() {
        return postService.findTop5ByOrderByIdDesc();
    }

    //postList - 마지막 글 번호를 파라미터로 받고 그 다음 글 5개 리턴(lastPostId o, 필터 x, 글 정렬o)
    @RequestMapping(method=RequestMethod.GET, value="/list/{lastPostId}")
    public List<Post> getPostList(@PathVariable("lastPostId")Long lastPostId, PostSortType sortType) {
        return postService.findPostByLimit(lastPostId,sortType);
    }

    //postList - 첫 필터링 후 조회 시 조건에 해당하는 글 리턴 (lastPostId o, 필터 d, 글 정렬x)
    @RequestMapping(method=RequestMethod.GET, value="/list/conditions")
    public List<Post> getPostListByConditions(int number, int periodStart, int periodEnd) {
        return postService.findPostByConditions(number, periodStart, periodEnd);
    }

    //postList - 마지막 글 번호를 파라미터로 받고 조건에 해당하는 그 다음 글 5개 리턴(lastPostId o, 필터 o, 글 정렬o)
    @RequestMapping(method=RequestMethod.GET, value="/list/{lastPostId}/conditions")
    public List<Post> getPostListByConditions(@PathVariable("lastPostId") Long lastPostId, int number, int periodStart, int periodEnd, PostSortType sortType) {
        return postService.findPostByConditions(lastPostId ,number, periodStart, periodEnd, sortType);
    }

    //post 삭제
    @RequestMapping(method=RequestMethod.DELETE, value="/{postId}")
    public void deletePost(@PathVariable("postId") Long postId){
        postService.deleteById(postId);
    }
}