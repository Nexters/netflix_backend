package me.ziok.application.controller;


import me.ziok.application.model.Account;
import me.ziok.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/{email}",method = RequestMethod.GET)
    public Account getAccountByEmail(@PathVariable String email) {
        return accountService.loadAccountByEmail(email);
    }
}
