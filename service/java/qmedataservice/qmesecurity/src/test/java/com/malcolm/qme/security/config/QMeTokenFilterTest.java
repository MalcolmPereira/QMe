/**
 * Name      : com.malcolm.qme.security.config.QMeTokenFilter.java
 * Date      : 9/30/15
 * Developer : Malcolm
 * Purpose   : QMe Token Filter Test
 */
package com.malcolm.qme.security.config;

import com.malcolm.qme.security.service.QMETokenAuthenticationService;
import com.malcolm.qme.security.service.QMETokenAuthenticationServiceJWTImpl;
import com.malcolm.qme.security.service.QMeUserDetails;
import com.malcolm.qme.security.service.QMeUserDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QMeTokenFilterTest {

    @Mock
    private HttpServletRequest req;

    @Mock
    private HttpServletResponse res;

    @Mock
    private FilterChain chain;

    @Mock
    private QMeUserDetailsService userDetailsService;

    @InjectMocks
    private QMETokenAuthenticationService qmeTokenAuthenticationService = new QMETokenAuthenticationServiceJWTImpl();

    @Test
    public void testDoFilter() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        doNothing().when(res).addHeader(eq(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME), Matchers.<String>anyObject());
        String testToken = qmeTokenAuthenticationService.addAuthToken(res,qMeUserDetails);
        verify(res).addHeader(eq(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME), Matchers.<String>anyObject());
        when(req.getHeader(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME)).thenReturn(testToken);
        when(userDetailsService.loadUserByUsername("Some User Name")).thenReturn(qMeUserDetails);
        QMeTokenFilter qMeTokenFilter = new QMeTokenFilter(qmeTokenAuthenticationService);
        qMeTokenFilter.doFilter(req, res,chain);
        verify(req).getHeader(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME);
        verify(userDetailsService).loadUserByUsername("Some User Name");
    }

@Test
    public void testDoFilterNullUser() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        String testInvalidToken = "invalidtoken";
        when(req.getHeader(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME)).thenReturn(testInvalidToken);
        QMeTokenFilter qMeTokenFilter = new QMeTokenFilter(qmeTokenAuthenticationService);
        qMeTokenFilter.doFilter(req, res, chain);
        verify(req).getHeader(QMETokenAuthenticationService.QME_AUTH_HEADER_NAME);
    }
}