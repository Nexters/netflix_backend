package me.ziok.application.controller;

import me.ziok.application.model.ProfileDTO;
import me.ziok.application.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public ProfileDTO getProfileByEmail(@RequestParam("accountId") Long accountId) {


        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.setInCompleteNum(postService.loadOpenPostsWithAccountId(accountId).size());
        profileDTO.setCompleteNum(postService.loadClosedPostsWithAccountId(accountId).size());
        profileDTO.setIncompletePosts(postService.loadOpenPostsWithAccountId(accountId));
        return profileDTO;
    }
}
