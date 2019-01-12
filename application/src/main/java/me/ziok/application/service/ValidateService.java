package me.ziok.application.service;

import org.springframework.stereotype.Service;

public interface ValidateService {

    boolean isAvailableToCreateAccount(String userName);

    boolean isAvailableToDeleteAccount(String userName);

    boolean isAccountExists(String userName);
}
