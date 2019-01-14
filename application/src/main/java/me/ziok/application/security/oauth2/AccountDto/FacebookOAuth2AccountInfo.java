package me.ziok.application.security.oauth2.AccountDto;

import java.util.Map;

public class FacebookOAuth2AccountInfo extends OAuth2AccountInfo {
    public FacebookOAuth2AccountInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getAccountId() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getImageUrl() {
        return null;
    }
}
