package me.ziok.application.controller;

import me.ziok.application.model.Account;
import me.ziok.application.model.Comment;
import me.ziok.application.model.Post;
import me.ziok.application.model.ProfileDTO;
import me.ziok.application.service.AccountService;
import me.ziok.application.service.CommentService;
import me.ziok.application.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public ProfileDTO getProfileByAccountId(@RequestParam("accountId") Long accountId) {

        return accountService.constructProfile(accountId);
    }
}
