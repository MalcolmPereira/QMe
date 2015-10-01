/**
 * Name      : com.malcolm.qme.security.service.QMETokenAuthenticationServiceJWTImpl.java
 * Date      : 9/30/15
 * Developer : Malcolm
 * Purpose   : QMe Token Authentication Service JSON Web Token Implementation
 */
package com.malcolm.qme.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Malcolm on 10/1/2015.
 */
@Service
public class QMETokenAuthenticationServiceJWTImpl implements QMETokenAuthenticationService {

    /**
     * Secret salt for encryption
     */
    private final static String secret = "QMeApplicationMarch2015MP";

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void addAuthToken(HttpServletResponse response, QMeUserDetails qmeUser) {
        response.addHeader(QME_AUTH_HEADER_NAME, createJSONAuthToken(qmeUser));
    }

    @Override
    public QMeUserDetails getAuthenticatedUser(HttpServletRequest request) {
        final String token = request.getHeader(QME_AUTH_HEADER_NAME);
        if (token != null) {
            final QMeUserDetails user = getQMeUserFromToken(token);
            if (user != null) {
                return user;
            }
        }
        return null;
    }

    /**
     * Get QMe User from Authentication Token
     * @param token JSON Authentication Token
     * @return
     */
    private QMeUserDetails getQMeUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return (QMeUserDetails)userDetailsService.loadUserByUsername(username);
    }

    /**
     * Create JSON Authentication Token
     * @param qmeUser QME Authentication Uer
     * @return JSON Token
     */
    private String createJSONAuthToken(QMeUserDetails qmeUser) {
        return Jwts.builder()
                .setSubject(qmeUser.getUsername())
                    .signWith(SignatureAlgorithm.HS512, secret)
                        .compact();
    }
}