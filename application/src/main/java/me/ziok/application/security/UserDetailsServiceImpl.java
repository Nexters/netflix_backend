package me.ziok.application.security;

import me.ziok.application.dao.AccountRepository;
import me.ziok.application.model.Account;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByAccountId(String accountId) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(accountId).orElseThrow(() -> new UsernameNotFoundException("User not found with this email : " + accountId));
        return UserPrincipal.create(account);
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with this id : " + id));
        return UserPrincipal.create(account);
    }
}