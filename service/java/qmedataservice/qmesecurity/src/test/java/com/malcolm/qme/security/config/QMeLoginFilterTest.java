/**
 * Name      : com.malcolm.qme.security.config.QMeLoginFilter.java
 * Date      : 9/30/15
 * Developer : Malcolm
 * Purpose   : QMe Login Filter Test
 */
package com.malcolm.qme.security.config;

import com.malcolm.qme.security.service.QMETokenAuthenticationService;
import com.malcolm.qme.security.service.QMETokenAuthenticationServiceJWTImpl;
import com.malcolm.qme.security.service.QMeUserDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;


/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QMeLoginFilterTest {
    @Mock
    private HttpServletRequest req;

    @Mock
    private HttpServletResponse res;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private FilterChain chain;

    /**
     * QMeLoginFilter instance
     */
    private QMeLoginFilter qMeLoginFilter;

    /**
     * QMeLoginFilter instance
     */
    private QMeLoginFilter qMeLoginFilterNullAuth;

    @Before
    public void setUp(){
        qMeLoginFilter = new QMeLoginFilter("some login URL", userDetailsService, new QMETokenAuthenticationServiceJWTImpl(),authManager);
        qMeLoginFilterNullAuth = new QMeLoginFilter("some login URL", userDetailsService, new QMETokenAuthenticationServiceJWTImpl(),null);
    }

    @Test
    public void testAttemptAuthentication() throws Exception {
        MockServletInputStream stream = null;
        try {
            QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
            String userCredentials = "{\"username\": \"SomeUserName\",\"password\": \"SomePassword\"}";
            stream = new MockServletInputStream(new ByteArrayInputStream(userCredentials.getBytes(StandardCharsets.UTF_8)));
            when(req.getInputStream()).thenReturn(stream);
            when(authManager.authenticate(Matchers.<UsernamePasswordAuthenticationToken>anyObject())).thenReturn(qMeUserDetails.getQMeAuthenticatedUser());
            Authentication authentication = qMeLoginFilter.attemptAuthentication(req,res);
            assertNotNull(authentication);
            verify(req).getInputStream();
            verify(authManager).authenticate(Matchers.<UsernamePasswordAuthenticationToken>anyObject());

        }finally {
            if(stream != null){
                stream.close();
            }
        }
    }

    @Test
    public void testAttemptAuthenticationNullReturn() throws Exception {
        MockServletInputStream stream = null;
        try {
            QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
            String userCredentials = "{\"username\": \"SomeUserName\",\"password\": \"SomePassword\"}";
            stream = new MockServletInputStream(new ByteArrayInputStream(userCredentials.getBytes(StandardCharsets.UTF_8)));
            when(req.getInputStream()).thenReturn(stream);
            Authentication authentication = qMeLoginFilterNullAuth.attemptAuthentication(req,res);
            assertNull(authentication);
            verify(req).getInputStream();
        }finally {
            if(stream != null){
                stream.close();
            }
        }
    }

    @Test
    public void testSuccessfulAuthentication() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        when(userDetailsService.loadUserByUsername("Some User Name")).thenReturn(qMeUserDetails);
        doNothing().when(res).addHeader(eq(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME), Matchers.<String>anyObject());
        qMeLoginFilter.successfulAuthentication(req, res, chain, qMeUserDetails.getQMeAuthenticatedUser());
        verify(userDetailsService).loadUserByUsername("Some User Name");
        verify(res).addHeader(eq(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME), Matchers.<String>anyObject());
    }

    /**
     * Mock Servlet InputStream
     * @author Malcolm
     */
    private final class MockServletInputStream extends ServletInputStream {

        /**
         * InputStream
         */
        private final InputStream sourceStream;

        /**
         * Mock Servlet Input Strea,
         * @param sourceStream
         */
        public MockServletInputStream(InputStream sourceStream) {
            this.sourceStream = sourceStream;
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }

        @Override
        public int read() throws IOException {
            return this.sourceStream.read();
        }
    }
}