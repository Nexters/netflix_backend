package me.ziok.application.service;

import me.ziok.application.model.Account;

public interface SocialService {
    Account translateAccessTokenToAccount(String accessToken);
    Account saveAccount(Account account);
}
