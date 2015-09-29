/**
 * Name      : com.malcolm.qme.security.config.QMeSecurityConfigTest.java
 * Date      : 5/25/15
 * Developer : Malcolm
 * Purpose   : QMe Security Config Test
 */
package com.malcolm.qme.security.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QMeSecurityConfigTest {

    @Mock
    private ObjectPostProcessor<Object> objectPostProcessor;

    @Mock
    private AuthenticationManagerBuilder authenticationBuilder;

    @Mock
    private Map<Class<Object>, Object> sharedObjects;

    @Mock
    private DaoAuthenticationConfigurer daoAuthenticationConfigurer;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private HttpServletRequest req;

    @Mock
    private HttpServletResponse res;

    @Mock
    private FilterChain chain;

    @Mock
    private CsrfToken mockToken;

    @Test
    public void testConfigureGlobal() throws Exception {
        when(authenticationBuilder.userDetailsService(Matchers.<UserDetailsService>anyObject())).thenReturn(daoAuthenticationConfigurer);
        when(daoAuthenticationConfigurer.passwordEncoder(Matchers.<PasswordEncoder>anyObject())).thenReturn(null);
        QMeSecurityConfig qMeSecurityConfig = new QMeSecurityConfig();
        boolean isConfigured;
        try {
            qMeSecurityConfig.configureGlobal(authenticationBuilder);
            isConfigured = true;
        }catch(Exception err){
            err.printStackTrace();
            isConfigured = false;
        }
        assertThat(isConfigured, equalTo(true));
    }

    @Test
    public void testConfigure() throws Exception {
        HttpSecurity httpSecurity = new HttpSecurity(objectPostProcessor,authenticationBuilder,sharedObjects);
        QMeSecurityConfig qMeSecurityConfig = new QMeSecurityConfig();
        boolean isConfigured;
        try {
            qMeSecurityConfig.configure(httpSecurity);
            isConfigured = true;
        }catch(Exception err){
            err.printStackTrace();
            isConfigured = false;
        }
        assertThat(isConfigured, equalTo(true));
        //TODO: Can we get the security paths that were configured?
    }

    @Test
    public void testPasswordEncoder() throws Exception {
        QMeSecurityConfig qMeSecurityConfig = new QMeSecurityConfig();
        assertNotNull(qMeSecurityConfig.passwordEncoder());
    }

    @Test
    public void testGetCSRFilter() throws Exception {
        QMeSecurityConfig qMeSecurityConfig = new QMeSecurityConfig();
        Filter csrfFilter = qMeSecurityConfig.getCSRFilter();
        assertNotNull(csrfFilter);

        when(req.getAttribute(CsrfToken.class.getName())).thenReturn(mockToken);
        when(mockToken.getToken()).thenReturn("sometoken");
        Cookie cookies[] = new Cookie[1];
        Cookie qmeCookie = new Cookie("XSRF-TOKEN", "somecookie");
        cookies[0] =qmeCookie;
        when(req.getCookies()).thenReturn(cookies);
        doNothing().when(res).addCookie(Matchers.<Cookie>anyObject());
        doNothing().when(chain).doFilter(req,res);

        csrfFilter.doFilter(req, res, chain);

        verify(req).getAttribute(CsrfToken.class.getName());
        verify(mockToken).getToken();
        verify(req).getCookies();
        verify(res).addCookie(Matchers.<Cookie>anyObject());
        verify(chain).doFilter(req, res);
    }
}