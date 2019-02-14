package me.ziok.application.infra;

import me.ziok.application.model.Account;
import me.ziok.application.payload.SignUpRequest;

public class RequestToAccount {

    //todo: accountService로 들어가는 게 더 맞을것 같음. 고민해보기.
    public static Account toAccount(SignUpRequest signUpRequest) {
        Account account = new Account(signUpRequest.getEmail(), signUpRequest.getPassword());
        account.setNickName(signUpRequest.getNickName());
        return account;


    }
}
