package me.ziok.application.dao;

import me.ziok.application.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void whenFindByNameThenReturnAccount() {

        //given
        Account accountConstructed = new Account();
        accountConstructed.setUserName("accountConstructed");
        this.entityManager.persistAndFlush(accountConstructed);

        //when
        Account accountFound = this.accountRepository.findByUserName("accountConstructed");

        //then
        assertThat(accountFound.getUserName())
                .isEqualTo(accountConstructed.getUserName());
    }

    @Test
    public void whenInvalidName_thenReturnNull() {
        Account anonymousAccount = accountRepository.findByUserName("invalidName");

        assertThat(anonymousAccount).isNull();

    }

    @Test
    public void whenFindById_thenReturnAccount() {
        Account accountConstructed = new Account();

        accountConstructed.setUserName("accountConstructed");

        entityManager.persistAndFlush(accountConstructed);

        Account accountFound = accountRepository.findById(accountConstructed.getId()).orElse(null);

        assertThat(accountFound.getUserName()).isEqualTo(accountConstructed.getUserName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {

        Account anonymousAccount = accountRepository.findById(-11l).orElse(null);
        assertThat(anonymousAccount).isNull();
    }

    @Test
    public void AccountRepository_FindAll() {

        //given
        Account accountConstructed1 = new Account();
        accountConstructed1.setUserName("accountConstructed1");

        Account accountConstructed2 = new Account();
        accountConstructed2.setUserName("accountConstructed2");

        Account accountConstructed3 = new Account();
        accountConstructed3.setUserName("accountConstructed3");

        entityManager.persist(accountConstructed1);
        entityManager.persist(accountConstructed2);
        entityManager.persist(accountConstructed3);
        entityManager.flush();


        //when
        List<Account> allAccounts = accountRepository.findAll();

        //then
        assertThat(allAccounts).hasSize(3).extracting(Account::getUserName).containsOnly(accountConstructed1.getUserName(), accountConstructed2.getUserName(), accountConstructed3.getUserName());



    }


}
