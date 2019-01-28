package me.ziok.application.service;

import me.ziok.application.model.Account;
import me.ziok.application.model.AuthProviderType;

public interface AccountService {
    Account loadAccountById(Long id);
    Account loadAccountByEmail(String email);

    Account saveAccount(String accountId, String password);
    Account saveAccount(String email, String password, String nickName, Boolean isEmailVerified, String imageUrl, AuthProviderType providerType, String providerId);
    Account deleteAccount(String accountId);
    //todo: update에 nickname 설정하는 것 넣기
    Account updateAccount(Account account);
    boolean isAbleToRegister(String email, String nickName);


}
