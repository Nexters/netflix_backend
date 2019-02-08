package me.ziok.application.controller;


import me.ziok.application.model.Account;
import me.ziok.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable Long id) {
        return accountService.loadAccountById(id);
    }

    //todo: registerAccount와 겹침. 버리던지 해야함.
    @RequestMapping(method = RequestMethod.POST)
    public Account createAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Account updateAccount(@RequestBody Account accountDetails) {

        return accountService.updateAccount(accountDetails);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Account deleteAccount(@PathVariable(value = "id") Long id) {
        return accountService.deleteAccount(id);

    }
}
