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

    @Override
    public String getRandomPw(int len) {
        char[] charSet = new char[] {'0','1','2','3','4','5','6','7','8','9',
                'A','B','C','D','E','F','G','H','I','J',
                'K','L','M','N','O','P','Q','R','S','T',
                'U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s'
                ,'t','u','v','w','x','y','z'};
        int index=0;

        StringBuffer sb = new StringBuffer();
        System.out.println("charset.length:"+charSet.length);

        for(int i=0; i<len; ++i) {
            index = (int)(charSet.length * Math.random());//난수
            System.out.println(index);
            sb.append(charSet[index]);
        }
        return sb.toString();
    }
}
