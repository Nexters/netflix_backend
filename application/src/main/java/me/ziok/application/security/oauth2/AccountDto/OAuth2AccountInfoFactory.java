package me.ziok.application.security.oauth2.AccountDto;

import me.ziok.application.exceptions.OAuth2AuthenticationProcessingException;
import me.ziok.application.model.AuthProviderType;

import java.util.Map;

public class OAuth2AccountInfoFactory {

    public static OAuth2AccountInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProviderType.FACEBOOK.toString())) {
            return new FacebookOAuth2AccountInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
