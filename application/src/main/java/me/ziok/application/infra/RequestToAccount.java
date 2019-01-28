package me.ziok.application.infra;

import me.ziok.application.model.Account;
import me.ziok.application.payload.LoginRequest;
import me.ziok.application.payload.SignUpRequest;

public class RequestToAccount {

    public static Account toAccount(SignUpRequest signUpRequest) {
        Account account = new Account(signUpRequest.getEmail(), signUpRequest.getPassword());
        account.setNickName(signUpRequest.getNickName());
        return account;


    }
}
