package me.ziok.application.service;

import me.ziok.application.dao.AccountRepository;
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
    public Account saveAccount(String email, String password) {

        if (accountRepository.existsByEmail(email)) {
            return null;
        }

        Account account = new Account(email);

        account.setPassword(passwordEncoder.encode(password));

        return accountRepository.save(account);
    }

    public Account saveAccount(String email, String password, String nickName, Boolean isEmailVerified, String imageUrl, AuthProviderType providerType, String providerId) {

        Account account = new Account(email);

        account.setPassword(passwordEncoder.encode(password));
        account.setNickName(nickName);
        account.setIsEmailVerified(isEmailVerified);
        account.setImageUrl(imageUrl);
        account.setProviderType(providerType);
        account.setProviderId(providerId);

        return account;

    }

    @Override
    public Account deleteAccount(String email) {
        Account account = accountRepository.findByEmail(email).orElse(null);

        if (account == null) {
            return  null;
        }

        accountRepository.delete(account);
        return account;
    }

    @Override
    public Account updateAccount(Account account) {

        //todo: account의 필드값을 받아서, 수정하는 걸로 바꿀수도 있음.
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
