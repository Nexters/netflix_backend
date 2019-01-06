package me.ziok.application.service;

import org.springframework.stereotype.Service;

@Service
public interface ValidateService {

    boolean IsAvailableToCreateAccount(String userName);

    boolean IsAvailableToDeleteAccount(String userName);

    boolean IsAccountExists(String userName);
}
