package me.ziok.application.security.oauth2.AccountDto;

import java.util.Map;

public abstract class OAuth2AccountInfo {

    protected Map<String, Object> attributes;

    public OAuth2AccountInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getAccountId();

    public abstract String getEmail();

    public abstract String getImageUrl();
}
