/**
 * Name      : com.malcolm.qme.security.config.QMeJWTokenAuthenticationService.java
 * Date      : 9/30/15
 * Developer : Malcolm
 * Purpose   : QMe JSON Web Token Authentication Service
 */
package com.malcolm.qme.security.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by malcolm on 9/30/15.
 */
@Service
public class QMeJWTokenAuthenticationService {

    //TODO
    private final static String secret = "QMeApp";

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    @Autowired
    private UserDetailsService userDetailsService;


    public String addAuthentication(HttpServletResponse response, UserDetails authentication) {
        String token = createTokenForUser(authentication);
        response.addHeader(AUTH_HEADER_NAME, token);
        return token;
    }

    public UserDetails getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final UserDetails user = parseUserFromToken(token);
            if (user != null) {
                return user;
            }
        }
        return null;
    }

    public UserDetails parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userDetailsService.loadUserByUsername(username);
    }

    public String createTokenForUser(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
