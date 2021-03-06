/**
 * Name      : com.malcolm.qme.security.service.QMETokenAuthenticationServiceJWTImplTest.java
 * Date      : 9/30/15
 * Developer : Malcolm
 * Purpose   : QMe Token Authentication Service JSON Web Token Implementation Test
 */
package com.malcolm.qme.security.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QMETokenAuthenticationServiceJWTImplTest {

    @Mock
    private HttpServletRequest req;

    @Mock
    private HttpServletResponse res;

    @Mock
    private  QMeUserDetailsService qMeUserDetailsService;

    @InjectMocks
    private QMETokenAuthenticationService qmeTokenAuthenticationService = new QMETokenAuthenticationServiceJWTImpl();

    @Test
    public void testAddAuthToken() throws Exception {
        doNothing().when(res).addHeader(eq(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME), Matchers.<String>anyObject());
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        String token = qmeTokenAuthenticationService.addAuthToken(res,qMeUserDetails);
        verify(res).addHeader(eq(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME), Matchers.<String>anyObject());
        assertNotNull(token);
    }

    @Test
    public void testAddAuthTokenNullUser() throws Exception {
        qmeTokenAuthenticationService.addAuthToken(res, null);
        verify(res, never()).addHeader(eq(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME), Matchers.<String>anyObject());
    }

    @Test
    public void testGetAuthenticatedUser() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        doNothing().when(res).addHeader(eq(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME), Matchers.<String>anyObject());
        String testToken = qmeTokenAuthenticationService.addAuthToken(res,qMeUserDetails);
        verify(res).addHeader(eq(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME), Matchers.<String>anyObject());
        when(req.getHeader(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME)).thenReturn(testToken);
        when(qMeUserDetailsService.loadUserByUsername("Some User Name")).thenReturn(qMeUserDetails);
        QMeUserDetails user = qmeTokenAuthenticationService.getAuthenticatedUser(req);
        verify(req).getHeader(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME);
        verify(qMeUserDetailsService).loadUserByUsername("Some User Name");
        assertNotNull(user);
    }

    @Test
    public void testGetAuthenticatedUserNullReturn() throws Exception {
        when(req.getHeader(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME)).thenReturn(null);
        QMeUserDetails user = qmeTokenAuthenticationService.getAuthenticatedUser(req);
        verify(req).getHeader(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME);
        assertNull(user);
    }

    @Test
    public void testGetAuthenticatedUserNullToken() throws Exception {
        String testTokenInvalid = "invalidtoken";
        when(req.getHeader(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME)).thenReturn(testTokenInvalid);
        QMeUserDetails user = qmeTokenAuthenticationService.getAuthenticatedUser(req);
        verify(req).getHeader(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME);
        assertNull(user);
    }
}