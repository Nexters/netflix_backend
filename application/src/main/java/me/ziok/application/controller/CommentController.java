package me.ziok.application.controller;

import me.ziok.application.model.Account;
import me.ziok.application.model.Comment;
import me.ziok.application.model.CommentSaveDTO;
import me.ziok.application.model.Post;
import me.ziok.application.service.CommentService;
import me.ziok.application.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(method = RequestMethod.POST)
    public void saveComment(@RequestParam CommentSaveDTO commentSaveDTO){
        commentService.saveComment(commentSaveDTO.getComment(), commentSaveDTO.getPostId());
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateComment(@RequestParam CommentSaveDTO commentSaveDTO){
         commentService.saveComment(commentSaveDTO.getComment(), commentSaveDTO.getPostId());
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
    }

}