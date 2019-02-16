package me.ziok.application.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {

    @Autowired
    AccountService accountService;

    @Test
    public void getRandomPw() {

        String randomPw = accountService.getRandomPw(8);

        System.out.println(randomPw);
    }
}