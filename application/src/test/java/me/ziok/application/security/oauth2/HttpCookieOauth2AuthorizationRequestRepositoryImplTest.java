package me.ziok.application.security.oauth2;

import me.ziok.application.util.CookieUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class HttpCookieOauth2AuthorizationRequestRepositoryImplTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    private HttpCookieOauth2AuthorizationRequestRepository authorizationRequestRepository =
            new HttpCookieOauth2AuthorizationRequestRepositoryImpl();


    @Test
    public void loadAuthorizationRequestWhenNotSavedThenReturnNull() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(OAuth2ParameterNames.STATE, "state-1234");
        OAuth2AuthorizationRequest authorizationRequest =
                this.authorizationRequestRepository.loadAuthorizationRequest(request);
        System.out.println(request.getParameter(OAuth2ParameterNames.STATE));


        assertThat(authorizationRequest).isNull();

    }

    @Test
    public void loadAuthorizationRequestWhenSavedThenReturnAuthorizationRequest() {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        MockHttpServletResponse response = new MockHttpServletResponse();

        //given
        OAuth2AuthorizationRequest authorizationRequest = createAuthorizationRequest().build();
        Cookie[] cookies = {new Cookie("oauth2_auth_request", CookieUtils.serialize(authorizationRequest))};
        when(request.getCookies()).thenReturn(cookies);
        // request.addParameter(OAuth2ParameterNames.STATE, authorizationRequest.getState());

        //when
        this.authorizationRequestRepository.saveAuthorizationRequest(authorizationRequest,request,response);
        OAuth2AuthorizationRequest loadedAuthorizationRequest =
                this.authorizationRequestRepository.loadAuthorizationRequest(request);

        //then
        assertThat(loadedAuthorizationRequest.getClientId()).isEqualTo(authorizationRequest.getClientId());
    }

    @Test
    public void saveAuthorizationRequest() {
    }

    @Test
    public void removeAuthorizationRequest() {
    }

    @Test
    public void removeAuthorizationRequestCookies() {
    }

    private OAuth2AuthorizationRequest.Builder createAuthorizationRequest() {
        return OAuth2AuthorizationRequest.authorizationCode()
                .authorizationUri("https://example.com/oauth2/authorize")
                .clientId("client-id-1234")
                .state("state-1234");
    }
}