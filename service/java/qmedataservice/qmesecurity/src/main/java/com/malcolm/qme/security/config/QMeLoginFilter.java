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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
public class QMeLoginFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * JSON Content Type
     */
    private static final String JSON_CONTENT_TYPE = "application/json";

    /**
     * UTF-8 Encoding
     */
    private static final String UTF_8_ENCODING = "utf-8";

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
        try{
            final QMeLoginUser user = new ObjectMapper().readValue(request.getInputStream(), QMeLoginUser.class);

            final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword());

            if (getAuthenticationManager() != null){
                return getAuthenticationManager().authenticate(loginToken);
            }

            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }catch(Exception err){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        final QMeUserDetails qMeUserDetails = (QMeUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        String authToken = qmeTokenAuthenticationService.addAuthToken(response,qMeUserDetails);
        SecurityContextHolder.getContext().setAuthentication(qMeUserDetails.getQMeAuthenticatedUser());
        writeQMeUser(response, authToken, qMeUserDetails);
    }

    private void writeQMeUser(HttpServletResponse response, String authToken, QMeUserDetails qMeUserDetails ) throws IOException {

        if(qMeUserDetails != null) {
            QMeLoginUser loginUser = new QMeLoginUser();
            loginUser.setAuthToken(authToken);
            loginUser.setUserID(qMeUserDetails.getUserID());
            loginUser.setUserName(qMeUserDetails.getUsername());
            loginUser.setUserEmail(qMeUserDetails.getUserEmail());
            loginUser.setUserFirstName(qMeUserDetails.getUserFirstName());
            loginUser.setUserLastName(qMeUserDetails.getUserLastName());
            loginUser.setUserLastLoginDate(qMeUserDetails.getUserLastLoginDate());
            loginUser.setUserRegisteredDate(qMeUserDetails.getUserRegisteredDate());

            List<String> roles = new ArrayList<>();
            for(GrantedAuthority role : qMeUserDetails.getAuthorities()){
                roles.add(role.getAuthority());
            }
            loginUser.setRoles(roles);
            response.setContentType(JSON_CONTENT_TYPE);
            response.setCharacterEncoding(UTF_8_ENCODING);
            PrintWriter out = response.getWriter();
            new ObjectMapper().writeValue(out, loginUser);
            out.close();
            out.flush();
        }
    }
}
