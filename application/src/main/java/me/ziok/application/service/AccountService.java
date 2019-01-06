package me.ziok.application.service;

import me.ziok.application.model.Account;
import me.ziok.application.model.Post;

public interface AccountService {

    Account saveAccount(String userName, String phoneNumber, String email, String passWord);

    Account deleteAccount(String userName);

    //ToDo: arg 바꿀수도...
    Account updateAccount(Account account);

}
