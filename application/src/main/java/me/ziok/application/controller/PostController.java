package me.ziok.application.controller;

import me.ziok.application.model.Post;
import me.ziok.application.service.CommentService;
import me.ziok.application.service.CommentServiceImpl;
import me.ziok.application.service.PostService;
import me.ziok.application.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public void savePost(Post post){
        System.out.println("savePost()메서드 진행");
        if(post.getId()==null){
            System.out.println("insert당");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            post.setCreateDate(date);
        }
        postService.savePost(post);
    }

    //post 읽기 - O
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Post getPost(@PathVariable int id){
        System.out.println("PostController탓당 id값"+id);
        return postService.getPost(id);
    }

    //post list
    @RequestMapping(method=RequestMethod.GET, value="list/{id}")
    public List<Post> getPostList(@PathVariable int id){
        return postService.getPostList(); //페이지네이션 객체 추가해야함
    }

    //post 삭제 - O(Comment 제외)
    @RequestMapping(method=RequestMethod.DELETE)
    public void deletePost(Post post){
       // commentService.deleteByPostId(post.getId());
        postService.deletePost(post);
    }
}
