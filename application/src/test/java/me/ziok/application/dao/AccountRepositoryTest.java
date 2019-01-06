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

        accountConstructed.setUserName("accountConstructedFindById");

        entityManager.persistAndFlush(accountConstructed);

        Account accountFound = accountRepository.findById(accountConstructed.getId()).orElse(null);

        assertThat(accountFound.getUserName()).isEqualTo(accountConstructed.getUserName());


    }


}
