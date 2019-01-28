package me.ziok.application.model;

import me.ziok.application.dao.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Account account = new Account("sample@naver.com", "4430999");
        accountRepository.save(account);
        System.out.println("jpa runner running");

        Account foundAccount = accountRepository.findByAccountId(account.getAccountId()).orElse(null);
        System.out.println(foundAccount.getAccountId());
        System.out.println(foundAccount.getId());

    }
}
