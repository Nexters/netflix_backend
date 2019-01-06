package me.ziok.application.service;

import me.ziok.application.dao.AccountRepository;
import me.ziok.application.model.Account;
import me.ziok.application.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ValidateService validateService;

    @Override
    public Account saveAccount(String userName, String phoneNumber, String email, String passWord) {
        if (validateService.IsAvailableToCreateAccount(userName)) {
            Account account = new Account(userName);

            account.setPhoneNumber(phoneNumber);
            account.setEmail(email);
            account.setPassWord(passWord);

            accountRepository.save(account);

            return account;
        }
        return null;
    }

    @Override
    public Account deleteAccount(String userName) {
        if (validateService.IsAvailableToDeleteAccount(userName)) {
            Account account = accountRepository.findByUserName(userName);
            accountRepository.delete(account);
            return account;
        }

        return null;
    }

    @Override
    public Account updateAccount(Account account) {

        if (validateService.IsAccountExists(account.getUserName())) {

            Account accountFound = accountRepository.findByUserName(account.getUserName());

            accountRepository.delete(accountFound);
            accountRepository.save(account);

            return account;

        }

        return null;
    }
}
