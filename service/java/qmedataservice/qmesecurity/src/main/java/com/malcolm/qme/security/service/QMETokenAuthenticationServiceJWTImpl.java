/**
 * Name      : com.malcolm.qme.security.service.QMETokenAuthenticationServiceJWTImpl.java
 * Date      : 9/30/15
 * Developer : Malcolm
 * Purpose   : QMe Token Authentication Service JSON Web Token Implementation
 */
package com.malcolm.qme.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Malcolm
 */
@Service
public class QMETokenAuthenticationServiceJWTImpl implements QMETokenAuthenticationService {
    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(QMETokenAuthenticationServiceJWTImpl.class);

    /**
     * Secret salt for encryption
     */
    private final static String secret = "QMeApplicationMarch2015MP";

    /**
     * Max Token Expiration
     * 3 Hours
     */
    private final static long MAX_TOKEN_EXPIRATION = 3600000 * 3;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String addAuthToken(HttpServletResponse response, QMeUserDetails qmeUser) {
        String jwtAuthToken = createJSONAuthToken(qmeUser);
        if(jwtAuthToken != null && jwtAuthToken.trim().length() != 0){
            response.addHeader(QME_AUTH_HEADER_NAME, jwtAuthToken);
            ((QMeUserDetailsService)userDetailsService).updateUserLastLoginDate(qmeUser);
        }
        return jwtAuthToken;
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
     * @return QMe User Details
     */
    private QMeUserDetails getQMeUserFromToken(String token) {
        try {
            String username = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return (QMeUserDetails) userDetailsService.loadUserByUsername(username);
        }catch(Exception err){
            LOG.error("Error parsing JWT Token for Authenticated User ",err);
            return null;
        }
    }

    /**
     * Create JSON Authentication Token
     * @param qmeUser QME Authentication Uer
     * @return JSON Token
     */
    private String createJSONAuthToken(QMeUserDetails qmeUser) {
        try {
            return Jwts.builder()
                    .setSubject(qmeUser.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + MAX_TOKEN_EXPIRATION))
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();

        }catch(Exception err){
            LOG.error("Error creating JWT Token for Authenticated User ",err);
            return null;
        }
    }
}