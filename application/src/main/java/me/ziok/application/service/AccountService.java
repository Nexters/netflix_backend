package me.ziok.application.service;

import me.ziok.application.model.Account;
import me.ziok.application.model.AuthProviderType;
import me.ziok.application.model.ProfileDTO;

public interface AccountService {
    Account loadAccountById(Long id);
    Account loadAccountByEmail(String email);

    Account saveAccount(Account account);
    Account deleteAccount(Long id);
    //todo: update에 nickname 설정하는 것 넣기
    Account updateAccount(Long id,Account account);
    boolean isAbleToRegister(String email, String nickName);


    ProfileDTO constructProfile(Long accountId);
}
