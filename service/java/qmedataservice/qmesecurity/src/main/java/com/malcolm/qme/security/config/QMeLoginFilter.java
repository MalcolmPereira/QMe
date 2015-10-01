package com.malcolm.qme.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by malcolm on 9/30/15.
 */
public class QMeLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final QMeJWTokenAuthenticationService qMeJWTokenAuthenticationService;

    private final UserDetailsService userDetailsService;

    public QMeLoginFilter(String defaultFilterProcessesUrl,QMeJWTokenAuthenticationService qMeJWTokenAuthenticationService,
                          UserDetailsService userDetailsService, AuthenticationManager authManager) {
        super(defaultFilterProcessesUrl);
        this.userDetailsService = userDetailsService;
        this.qMeJWTokenAuthenticationService = qMeJWTokenAuthenticationService;
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        final User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());
        return getAuthenticationManager().authenticate(loginToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {

        // Lookup the complete User object from the database and create an Authentication for it
        final UserDetails authenticatedUser = userDetailsService.loadUserByUsername(authentication.getName());

        // Add the custom token as HTTP header to the response
        qMeJWTokenAuthenticationService.addAuthentication(response, authenticatedUser);

        // Add the authentication to the Security context
        SecurityContextHolder.getContext().setAuthentication((Authentication)authenticatedUser);
    }
}
