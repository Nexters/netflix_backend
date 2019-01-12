package me.ziok.application.dao;

import me.ziok.application.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void createAccount(){
        Account account1 = new Account();

        account1.setUserId("yeji");
        account1.setPassword("12345z");
        account1.setAccountName("tiber");
        account1.setPhoneNumber("010-1234-5678");
        account1.setEmail("nexflix@naver.com");

        Account account2 = accountRepository.save(account1);
        assertEquals(account1, account2);
    }


    @Test
    public void updateAccount(){
        Account account1 = new Account();

        account1.setUserId("yeji");
        account1.setPassword("12345z");
        account1.setAccountName("tiber");
        account1.setPhoneNumber("010-1234-5678");
        account1.setEmail("nexflix@naver.com");

        Account account2 = accountRepository.save(account1);

        account2.setPassword("z54321");
        account2.setAccountName("tiberlee");
        account2.setPhoneNumber("010-1111-1111");
        account2.setEmail("neflix@google.com");

        account1 = accountRepository.save(account2);

        assertEquals(account1, account2);
    }

    @Test
    public void deleteAccount(){
        Account account1 = new Account();

        account1.setUserId("yeji");
        account1.setPassword("12345z");
        account1.setAccountName("tiber");
        account1.setPhoneNumber("010-1234-5678");
        account1.setEmail("nexflix@naver.com");

        Account account2 = new Account();

        account2.setUserId("geunwon");
        account2.setPassword("11111");
        account2.setAccountName("won");
        account2.setPhoneNumber("010-7777-7777");
        account2.setEmail("nexflix@google.com");

        Account account3 = new Account();

        account3.setUserId("yena");
        account3.setPassword("yena123");
        account3.setAccountName("nana");
        account3.setPhoneNumber("010-1111-2222");
        account3.setEmail("nexflix@daum.net");

        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);

        List<Account> list = accountRepository.findAll();
        assertTrue(list.size()>0);

        for(Account a : list)
            accountRepository.delete(a);

        list = accountRepository.findAll();
        assertTrue(list.size()==0);

    }
}
