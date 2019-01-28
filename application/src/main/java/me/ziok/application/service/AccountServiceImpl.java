package me.ziok.application.service;

import me.ziok.application.dao.AccountRepository;
import me.ziok.application.exceptions.ResourceNotFoundException;
import me.ziok.application.model.Account;
import me.ziok.application.model.AuthProviderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Account loadAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account loadAccountByEmail(String email) {
        return accountRepository.findByEmail(email).orElse(null);
    }


    @Override
    public Account saveAccount(Account account) {

        account.setPassword(passwordEncoder.encode(account.getPassword()));

        return accountRepository.save(account);

    }

    @Override
    public Account deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElse(null);

        if (account == null) {
            return  null;
        }

        accountRepository.delete(account);
        return account;
    }

    @Override
    public Account updateAccount(Account account) {

        accountRepository.findById(account.getId()).orElseThrow(() -> new ResourceNotFoundException("Account", "id", account.getId()));

        return accountRepository.save(account);
    }

    @Override
    public boolean isAbleToRegister(String email, String nickName) {

        //todo: 이메일 글자수가 적절한지, 닉네임 글자수가 적절한지, 패스워드 글자수나 정책에 적절한지 등 확인

        if (accountRepository.existsByEmailOrNickName(email, nickName)) {
            return false;
        }
        return true;
    }
}
