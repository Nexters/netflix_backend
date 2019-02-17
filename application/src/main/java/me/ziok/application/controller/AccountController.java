package me.ziok.application.controller;


import me.ziok.application.exceptions.UserNotFoundException;
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
        Account account = accountService.loadAccountById(id);
        if (account == null) {
            throw new UserNotFoundException("User with the 'id' doesn't exists.");
        }

        return account;
    }

    //todo: registerAccount와 겹침. 버리던지 해야함.
    @RequestMapping(method = RequestMethod.POST)
    public Account createAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Account updateAccount(@PathVariable Long id,@RequestBody Account accountDetails) {

        return accountService.updateAccount(id, accountDetails);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Account deleteAccount(@PathVariable(value = "id") Long id) {
        return accountService.deleteAccount(id);

    }

    //사용자 email 존재 시 email로 인증 코드 발송
    @RequestMapping(value="/confirmUser", method=RequestMethod.POST)
    public boolean confirmUser(@RequestParam("email") String email){
         return accountService.confirmUser(email);
    }

    //인증코드 일치 시 true리턴
    @RequestMapping(value="/matchAuthenticationCode", method = RequestMethod.POST)
    public boolean matchAuthenticationCode(@RequestParam("email") String email, @RequestParam("code") String code){
        return accountService.matchAuthenticationCode(email, code);
    }

    //사용자가 입력한 password를 암호화해서 db에 저장
    @RequestMapping(value="/changeUserPassword", method = RequestMethod.POST)
    public boolean setUserPassword(@RequestParam("email") String email, @RequestParam("password") String password){
        return accountService.changeUserPassword(email, password);
    }
}
