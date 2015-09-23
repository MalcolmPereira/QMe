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

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

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

    @Test
    public void testConfigureGlobal() throws Exception {
        when(authenticationBuilder.userDetailsService(Matchers.<UserDetailsService>anyObject())).thenReturn(daoAuthenticationConfigurer);
        when(daoAuthenticationConfigurer.passwordEncoder(Matchers.<PasswordEncoder>anyObject())).thenReturn(null);
        QMeSecurityConfig qMeSecurityConfig = new QMeSecurityConfig();
        boolean isConfigured = false;
        try {
            qMeSecurityConfig.configureGlobal(authenticationBuilder);
            System.out.println(authenticationBuilder.getDefaultUserDetailsService());
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
        boolean isConfigured = false;
        try {
            qMeSecurityConfig.configure(httpSecurity);
            isConfigured = true;
        }catch(Exception err){
            err.printStackTrace();
            isConfigured = false;
        }
        assertThat(isConfigured, equalTo(true));
    }

    @Test
    public void testPasswordEncoder() throws Exception {
        QMeSecurityConfig qMeSecurityConfig = new QMeSecurityConfig();
        assertNotNull(qMeSecurityConfig.passwordEncoder());
    }
}