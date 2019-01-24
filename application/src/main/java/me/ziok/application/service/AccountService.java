package me.ziok.application.service;

import me.ziok.application.model.Account;

public interface AccountService {
    Account saveAccount(String accountId);
    Account deleteAccount(String accountId);
    Account updateAccount(Account account);
    boolean isAbleToRegister(String accountId, String nickName);

}
