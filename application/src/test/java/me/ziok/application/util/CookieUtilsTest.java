package me.ziok.application.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CookieUtilsTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCookieTest() {
        //given
        Cookie cookieForTest = new Cookie("testCookie", "testCookieValue");
        Cookie[] cookies = new Cookie[] {cookieForTest};
        when(request.getCookies()).thenReturn(cookies);

        //when
        Cookie cookieRetrieved = CookieUtils.getCookie(request, "testCookie").get();

        //then
        assertEquals(cookieForTest,cookieRetrieved);
    }

    @Test
    public  void addCookie() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        boolean expResult = false;
        //when
        boolean result = CookieUtils.addCookie(response,null, null, 1);

        //then
        assertEquals(expResult, result);

        expResult = true;

        //when
        result = CookieUtils.addCookie(response, "cookieName", "cookieValue", 1);

        //then
        assertEquals(expResult, result);


        System.out.println(CookieUtils.getCookie(request, "cookieNmae"));

    }

}