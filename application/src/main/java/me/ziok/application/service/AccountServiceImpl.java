package me.ziok.application.service;

import me.ziok.application.dao.AccountRepository;
import me.ziok.application.model.Account;
import me.ziok.application.model.Post;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;


    @Override
    public Account saveAccount(String userName, String phoneNumber, String email, String passWord) {
        Account account = new Account(userName);

        account.setPhoneNumber(phoneNumber);
        account.setEmail(email);
        account.setPassWord(passWord);

        accountRepository.save(account);

        return account;
    }

    @Override
    public Account deleteAccount(String userName) {
        Account account = accountRepository.findByUserName(userName);
        accountRepository.delete(account);
        return account;
    }

    @Override
    public Account updateAccount(Account account) {

        Account accountFound = accountRepository.findByUserName(account.getUserName());

        accountRepository.delete(accountFound);
        accountRepository.save(account);

        return account;
    }
}
