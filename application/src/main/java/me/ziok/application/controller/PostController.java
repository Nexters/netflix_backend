package me.ziok.application.controller;

import me.ziok.application.model.Account;
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

    @Autowired
    CommentService commentService;

    //post 생성, 수정
    @RequestMapping(method= RequestMethod.POST)
    public void savePost(@RequestBody Post post){

        Account account = new Account();
        account.setId((long)48);
        post.setAccount(account);//account 정보가져와서 set해야함.

        postService.savePost(post);
    }

    //post 상세보기
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Post getPost(@PathVariable int id){
        Post post = postService.getPost(id);
        post.setHits(post.getHits()+1); //조회 수 +1
        savePost(post); //조회 수 +1 update
        return post;
    }

    //postList - 게시판 첫 요청 시
    @RequestMapping(method=RequestMethod.GET, value="list")
    public List<Post> getPostList() {
        return postService.findTop5ByOrderByIdDesc();
    }

    //postList - 마지막 글 번호를 파라미터로 받고 그 다음 글 5개 리턴
    @RequestMapping(method=RequestMethod.GET, value="list/{id}")
    public List<Post> getPostList(@PathVariable int id) {
        return postService.findPostByLimit(id);
    }

    //postLit - 첫 필터링 후 조회 시 조건에 해당하는 글 리턴
    @RequestMapping(method=RequestMethod.GET, value="list/conditions")
    public List<Post> getPostListByConditions(int number, int periodStart, int periodEnd) {
        return postService.findPostByConditions(number, periodStart, periodEnd);
    }
    //postList - 마지막 글 번호를 파라미터로 받고 조건에 해당하는 그 다음 글 5개 리턴
    @RequestMapping(method=RequestMethod.GET, value="list/conditions/{id}")
    public List<Post> getPostListByConditions(@PathVariable int id, int number, int periodStart, int periodEnd) {
        return postService.findPostByConditions(id ,number, periodStart, periodEnd);
    }

    //post 삭제(Comment 제외)
    @RequestMapping(method=RequestMethod.DELETE)
    public void deletePost(Post post){
       // commentService.deleteByPostId(post.getId());
        postService.deletePost(post);
    }
}
