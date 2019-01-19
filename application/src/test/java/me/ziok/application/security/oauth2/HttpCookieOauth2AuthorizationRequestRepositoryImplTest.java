package me.ziok.application.security.oauth2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class HttpCookieOauth2AuthorizationRequestRepositoryImplTest {

    private HttpCookieOauth2AuthorizationRequestRepository authorizationRequestRepository =
            new HttpCookieOauth2AuthorizationRequestRepositoryImpl();

//    @Test(expected = IllegalArgumentException.class)
//    public void loadAuthorizationRequestWhenHttpServletRequestIsNullThenThrowIllegalArgumentException() {
//        this.authorizationRequestRepository.loadAuthorizationRequest(null);
//    }

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
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        OAuth2AuthorizationRequest authorizationRequest = createAuthorizationRequest().build();

        this.authorizationRequestRepository.saveAuthorizationRequest(authorizationRequest,request,response);

        request.addParameter(OAuth2ParameterNames.STATE, authorizationRequest.getState());
        OAuth2AuthorizationRequest loadedAuthorizationRequest =
                this.authorizationRequestRepository.loadAuthorizationRequest(request);

        assertThat(loadedAuthorizationRequest).isEqualTo(authorizationRequest);
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