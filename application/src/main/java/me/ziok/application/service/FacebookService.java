package me.ziok.application.service;

import me.ziok.application.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

@Service
public class FacebookService implements SocialService {

    @Autowired
    AccountService accountService;

    @Override
    public Account translateAccessTokenToAccount(String accessToken) {

        Facebook facebook = new FacebookTemplate(accessToken);

        User userProfile = facebook.userOperations().getUserProfile();

        //todo: email 외 다른 정보들도 다 넣어주기
        Account account = new Account(userProfile.getEmail(), "4430515s");

        return account;
    }

    @Override
    public Account saveAccount(Account account) {

        if (accountService.isAbleToRegister(account.getEmail(), account.getNickName())) {
            //todo: 이부분도 saveAccount 함수를 바꾸든, 다른 함수를 만들어주든 바꿔주기
            accountService.saveAccount(account);
        }

        return account;
    }
}
