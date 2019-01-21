package me.ziok.application.security;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.*;


public class JwtAuthenticationEntryPointTest {

    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Before
    public void setUp() {
        authenticationEntryPoint = new JwtAuthenticationEntryPoint();
    }

    @Test
    public void commenceTest() throws IOException, ServletException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        AuthenticationException exception = new AuthenticationCredentialsNotFoundException("");

        authenticationEntryPoint.commence(request,response,exception);

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());


    }

}