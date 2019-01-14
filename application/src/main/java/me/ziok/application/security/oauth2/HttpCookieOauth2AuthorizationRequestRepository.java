package me.ziok.application.security.oauth2;

import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

public interface HttpCookieOauth2AuthorizationRequestRepository extends AuthorizationRequestRepository<OAuth2AuthorizationRequest> {
}
