package me.ziok.application.controller;

import me.ziok.application.model.Account;
import me.ziok.application.model.Comment;
import me.ziok.application.model.Post;
import me.ziok.application.service.CommentService;
import me.ziok.application.service.CommentServiceImpl;
import me.ziok.application.service.PostService;
import me.ziok.application.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    PostService postService;

    //post 생성
    @RequestMapping(method= RequestMethod.POST)
    public Post savePost(@RequestBody Post post){
        return postService.savePost(post);
    }

    //post 수정
    @RequestMapping(method= RequestMethod.PUT)
    public Post updatePost(@RequestBody Post post){
        return postService.savePost(post);
    }

    //post 상세보기 - account정보
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Post getPost(@PathVariable int id, String email){
        return postService.getPost(id, email);
    }

    //postList - 게시판 첫 요청 시
    @RequestMapping(method=RequestMethod.GET, value="list")
    public List<Post> getPostList() {
        return postService.findTop5ByOrderByIdDesc();
    }

    //0최신순 1남은인원수 2저가순
    //postList - 마지막 글 번호를 파라미터로 받고 그 다음 글 5개 리턴
    @RequestMapping(method=RequestMethod.GET, value="list/{lastPostId}")
    public List<Post> getPostList(@PathVariable int lastPostId, @RequestParam(defaultValue = "0") int sortId) {
        return postService.findPostByLimit(lastPostId,sortId);
    }

    //postList - 첫 필터링 후 조회 시 조건에 해당하는 글 리턴
    @RequestMapping(method=RequestMethod.GET, value="list/conditions")
    public List<Post> getPostListByConditions(int number, int periodStart, int periodEnd) {
        return postService.findPostByConditions(number, periodStart, periodEnd);
    }

    //postList - 마지막 글 번호를 파라미터로 받고 조건에 해당하는 그 다음 글 5개 리턴
    @RequestMapping(method=RequestMethod.GET, value="list/conditions/{lastPostId}")
    public List<Post> getPostListByConditions(@PathVariable int lastPostId, int number, int periodStart, int periodEnd,  @RequestParam(defaultValue = "0") int sortId) {
        return postService.findPostByConditions(lastPostId ,number, periodStart, periodEnd, sortId);
    }

    //post 삭제
    @RequestMapping(method=RequestMethod.DELETE)
    public void deletePost(@RequestBody Post post){
        postService.deletePost(post);
    }
}