package me.ziok.application.service;

import me.ziok.application.dao.AccountRepository;
import me.ziok.application.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private ValidateService validateService;

    @Override
    public Account saveAccount(String userName, String phoneNumber, String email, String passWord) {
        if (validateService.isAvailableToCreateAccount(userName)) {
            Account account = new Account(userName);

            account.setPhoneNumber(phoneNumber);
            account.setEmail(email);
            account. setPassword(passWord);

            accountRepository.save(account);

            return account;
        }
        return null;
    }

    @Override
    public Account deleteAccount(String userName) {
        if (validateService.isAvailableToDeleteAccount(userName)) {
            Account account = accountRepository.findByUserName(userName);
            accountRepository.delete(account);
            return account;
        }

        return null;
    }

    @Override
    public Account updateAccount(Account account) {

        if (Objects.isNull(account)) {
            //todo: null을 리턴하는 것으로 충분한 지, 에러를 발생시켜야 하는 것은 아닌지 확인하기.
            return null;
        }

        if (!validateService.isAccountExists(account.getUserId())) {
            //todo: null을 리턴하는 것으로 충분한 지, 에러를 발생시켜야 하는 것은 아닌지 확인하기.
            return null;

        }

        accountRepository.save(account);

        return account;


    }
}
