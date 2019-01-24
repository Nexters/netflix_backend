package me.ziok.application.service;

import me.ziok.application.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public Account saveAccount(String accountId) {
        return null;
    }

    @Override
    public Account deleteAccount(String accountId) {
        return null;
    }

    @Override
    public Account updateAccount(Account account) {
        return null;
    }

    @Override
    public boolean isAbleToRegister(String accountId, String nickName) {
        return false;
    }
}
