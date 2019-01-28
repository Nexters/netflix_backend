package me.ziok.application.service;

import me.ziok.application.dao.AccountRepository;
import me.ziok.application.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Account loadAccountByAccountId(String accountId) {
        return accountRepository.findByAccountId(accountId).orElse(null);
    }

    @Override
    public Account saveAccount(String accountId, String password) {
        Account account = new Account(accountId);

        account.setPassword(passwordEncoder.encode(password));

        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(String accountId) {
        Account account = accountRepository.findByAccountId(accountId).orElse(null);
        //todo: 조회 안됐을 시 처리
        if (account == null) {

        }

        accountRepository.delete(account);
    }

    @Override
    public Account updateAccount(Account account) {

        //todo: account의 필드값을 받아서, 수정하는 걸로 바꾸기
        return accountRepository.save(account);
    }

    @Override
    public boolean isAbleToRegister(String accountId, String nickName) {
        //todo: 로직 추가
        return true;
    }
}
