/**
 * Name      : com.malcolm.qme.rest.config.RestConfigTest.java
 * Date      : 9/32/15
 * Developer : Malcolm
 * Purpose   : Configuration for REST MVC Test
 */
package com.malcolm.qme.rest.config;

import com.malcolm.qme.rest.api.AtomicTokenGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class RestConfigTest{

    @Mock
    private Environment environment;

    @InjectMocks
    private  RestConfig restConfig;

    @Before
    public void initMocks(){
        restConfig = new RestConfig();
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testJacksonBuilder() throws Exception {
        assertNotNull(restConfig.jacksonBuilder());
    }

    @Test
    public void testAtomicTokenGenerator() throws Exception {
        AtomicTokenGenerator tokenGenerator = restConfig.atomicTokenGenerator();
        assertNotNull(tokenGenerator);
        String token1 = tokenGenerator.generateUniqueResetToken();
        assertNotNull(token1);
        String token2 = tokenGenerator.generateUniqueResetToken();
        assertNotNull(token2);
        assertNotEquals(token1, token2);
        token1 = tokenGenerator.generateUniqueResetToken();
        assertNotNull(token1);
        token2 = tokenGenerator.generateUniqueResetToken();
        assertNotNull(token2);
        assertNotEquals(token1,token2);
    }

    @Test
    public void testJavaMailSenderTLS() throws Exception {
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_USERNAME)).thenReturn("some user name");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_PASSWORD)).thenReturn("some user password");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_AUTH_TYPE)).thenReturn("TLS");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_AUTH)).thenReturn("true");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_HOST)).thenReturn("some host");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_PORT_TLS)).thenReturn("some port");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_START_TLS)).thenReturn("true");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_TIMEOUT)).thenReturn("20000");
        JavaMailSenderImpl mailSender = restConfig.javaMailSender();
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_USERNAME);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_PASSWORD);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_AUTH_TYPE);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_AUTH);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_HOST);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_PORT_TLS);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_START_TLS);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_TIMEOUT);
        assertNotNull(mailSender);
    }

    @Test
    public void testJavaMailSenderTLSSystemProp() throws Exception {
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_USERNAME)).thenReturn(null);
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_USERNAME_SYS)).thenReturn("some user name");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_PASSWORD)).thenReturn(null);
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_PASSWORD_SYS)).thenReturn("some user password");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_AUTH_TYPE)).thenReturn(null);
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_AUTH)).thenReturn("true");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_HOST)).thenReturn("some host");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_PORT_TLS)).thenReturn("some port");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_START_TLS)).thenReturn("true");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_TIMEOUT)).thenReturn("20000");
        JavaMailSenderImpl mailSender = restConfig.javaMailSender();
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_USERNAME);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_USERNAME_SYS);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_PASSWORD);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_PASSWORD_SYS);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_AUTH_TYPE);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_AUTH);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_HOST);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_PORT_TLS);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_START_TLS);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_TIMEOUT);
        assertNotNull(mailSender);
    }


    @Test
    public void testJavaMailSenderSSL() throws Exception {
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_USERNAME)).thenReturn("some user name");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_PASSWORD)).thenReturn("some user password");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_AUTH_TYPE)).thenReturn("SSL");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_AUTH)).thenReturn("true");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_HOST)).thenReturn("some host");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_PORT_SSL)).thenReturn("some port");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_SSL_SOCKET_CLASS)).thenReturn("some class");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_SSL_SOCKET_PORT)).thenReturn("some port");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_TIMEOUT)).thenReturn("20000");
        JavaMailSenderImpl mailSender = restConfig.javaMailSender();
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_USERNAME);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_PASSWORD);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_AUTH_TYPE);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_AUTH);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_HOST);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_PORT_SSL);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_SSL_SOCKET_CLASS);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_SSL_SOCKET_PORT);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_TIMEOUT);
        assertNotNull(mailSender);
    }

    @Test
    public void testJavaMailSenderSSLSystem() throws Exception {
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_USERNAME)).thenReturn(null);
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_USERNAME_SYS)).thenReturn("some user name");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_PASSWORD)).thenReturn(null);
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_PASSWORD_SYS)).thenReturn("some user password");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_AUTH_TYPE)).thenReturn("SSL");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_AUTH)).thenReturn("true");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_HOST)).thenReturn("some host");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_PORT_SSL)).thenReturn("some port");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_SSL_SOCKET_CLASS)).thenReturn("some class");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_SSL_SOCKET_PORT)).thenReturn("some port");
        when(environment.getProperty(QMeMailSender.MAIL_SMTP_TIMEOUT)).thenReturn("20000");
        JavaMailSenderImpl mailSender = restConfig.javaMailSender();
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_USERNAME);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_PASSWORD);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_AUTH_TYPE);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_AUTH);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_HOST);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_PORT_SSL);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_SSL_SOCKET_CLASS);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_SSL_SOCKET_PORT);
        verify(environment).getProperty(QMeMailSender.MAIL_SMTP_TIMEOUT);
        assertNotNull(mailSender);
    }
}