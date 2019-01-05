package me.ziok.application.dao;

import me.ziok.application.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void jpaPersistenceTest() {

        //given
        Account accountConstructed = new Account();
        accountConstructed.setUserName("accountConstructed");
        this.entityManager.persist(accountConstructed);

        //when
        Account accountFound = this.accountRepository.findByUserName("accountConstructed");

        //then
        assertThat(accountFound.getUserName())
                .isEqualTo(accountConstructed.getUserName());
    }
}
