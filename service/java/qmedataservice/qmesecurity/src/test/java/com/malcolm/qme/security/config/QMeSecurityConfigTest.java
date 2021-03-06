/**
 * Name      : com.malcolm.qme.security.config.QMeSecurityConfigTest.java
 * Date      : 5/25/15
 * Developer : Malcolm
 * Purpose   : QMe Security Config Test
 */
package com.malcolm.qme.security.config;

import com.malcolm.qme.security.service.QMETokenAuthenticationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.verify;
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
    private AuthenticationManager authenticationManager;

    @Mock
    private DaoAuthenticationConfigurer daoAuthenticationConfigurer;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private QMETokenAuthenticationService qmeTokenAuthenticationService;

    @Mock
    private Environment environment;

    @Mock
    private AuthenticationConfiguration authenticationConfiguration;

    @InjectMocks
    private QMeSecurityConfig qMeSecurityConfig = new QMeSecurityConfig();

    @Test
    public void testConfigureGlobal() throws Exception {
        when(authenticationBuilder.userDetailsService(Matchers.<UserDetailsService>anyObject())).thenReturn(daoAuthenticationConfigurer);
        when(daoAuthenticationConfigurer.passwordEncoder(Matchers.<PasswordEncoder>anyObject())).thenReturn(daoAuthenticationConfigurer);
        boolean isConfigured;
        try {
            qMeSecurityConfig.configureGlobal(authenticationBuilder);
            isConfigured = true;
            verify(authenticationBuilder).userDetailsService(Matchers.<UserDetailsService>anyObject());
            verify(daoAuthenticationConfigurer).passwordEncoder(Matchers.<PasswordEncoder>anyObject());
        }catch(Exception err){
            err.printStackTrace();
            isConfigured = false;
        }
        assertThat(isConfigured, equalTo(true));
    }

    @Test
    public void testConfigure() throws Exception {
        when(authenticationBuilder.userDetailsService(Matchers.<UserDetailsService>anyObject())).thenReturn(daoAuthenticationConfigurer);
        when(daoAuthenticationConfigurer.passwordEncoder(Matchers.<PasswordEncoder>anyObject())).thenReturn(daoAuthenticationConfigurer);
        when(authenticationConfiguration.getAuthenticationManager()).thenReturn(authenticationManager);
        HttpSecurity httpSecurity = new HttpSecurity(objectPostProcessor,authenticationBuilder,anyMap());
        QMeSecurityConfig qMeSecurityConfig = new QMeSecurityConfig();
        boolean isConfigured;
        try {
            qMeSecurityConfig.setAuthenticationConfiguration(authenticationConfiguration);
            qMeSecurityConfig.configureGlobal(authenticationBuilder);
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
}