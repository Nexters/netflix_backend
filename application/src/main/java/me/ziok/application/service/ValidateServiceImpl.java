package me.ziok.application.service;

import org.springframework.stereotype.Service;

@Service
public class ValidateServiceImpl implements ValidateService {
    @Override
    public boolean isAvailableToCreateAccount(String userName) {
        return false;
    }

    @Override
    public boolean isAvailableToDeleteAccount(String userName) {
        return false;
    }

    @Override
    public boolean isAccountExists(String userName) {
        return false;
    }
}
