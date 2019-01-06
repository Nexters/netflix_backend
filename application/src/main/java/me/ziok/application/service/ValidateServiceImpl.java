package me.ziok.application.service;

import org.springframework.stereotype.Service;

@Service
public class ValidateServiceImpl implements ValidateService {
    @Override
    public boolean IsAvailableToCreateAccount(String userName) {
        return false;
    }

    @Override
    public boolean IsAvailableToDeleteAccount(String userName) {
        return false;
    }

    @Override
    public boolean IsAccountExists(String userName) {
        return false;
    }
}
