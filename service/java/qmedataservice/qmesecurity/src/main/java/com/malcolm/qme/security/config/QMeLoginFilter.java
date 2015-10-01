/**
 * Name      : com.malcolm.qme.security.config.QMeLoginFilter.java
 * Date      : 9/30/15
 * Developer : Malcolm
 * Purpose   : QMe Login Filter
 */
package com.malcolm.qme.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malcolm.qme.security.service.QMETokenAuthenticationService;
import com.malcolm.qme.security.service.QMeUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Malcolm
 */
public class QMeLoginFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * User Details Service
     */
    private final UserDetailsService userDetailsService;

    /**
     * QME Token Authentication Service
     */
    private final QMETokenAuthenticationService qmeTokenAuthenticationService;

    /**
     * Public Constructor
     *
     * @param defaultFilterProcessesUrl Default Filter Processes URL
     * @param userDetailsService User Details Service
     * @param qmeTokenAuthenticationService QMe Token Authentication Service
     * @param authManager Authentication Manager
     */
    public QMeLoginFilter(String defaultFilterProcessesUrl, UserDetailsService userDetailsService, QMETokenAuthenticationService qmeTokenAuthenticationService,AuthenticationManager authManager) {
        super(defaultFilterProcessesUrl);
        this.userDetailsService = userDetailsService;
        this.qmeTokenAuthenticationService = qmeTokenAuthenticationService;
        if(authManager!= null) {
            setAuthenticationManager(authManager);
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        final QMeLoginUser user = new ObjectMapper().readValue(request.getInputStream(), QMeLoginUser.class);
        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        if (getAuthenticationManager() != null){
            return getAuthenticationManager().authenticate(loginToken);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        final QMeUserDetails qMeUserDetails = (QMeUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        qmeTokenAuthenticationService.addAuthToken(response,qMeUserDetails);
        SecurityContextHolder.getContext().setAuthentication(qMeUserDetails.getQMeAuthenticatedUser());
    }
}
