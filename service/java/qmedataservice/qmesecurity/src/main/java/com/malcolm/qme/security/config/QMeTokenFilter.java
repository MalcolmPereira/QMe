/**
 * Name      : com.malcolm.qme.security.config.QMeTokenFilter.java
 * Date      : 9/30/15
 * Developer : Malcolm
 * Purpose   : QMe Token Filter
 */
package com.malcolm.qme.security.config;

import com.malcolm.qme.security.service.QMETokenAuthenticationService;
import com.malcolm.qme.security.service.QMeUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Malcolm
 */
public class QMeTokenFilter extends GenericFilterBean {

    /**
     * QME Token Authentication Service
     */
    private final QMETokenAuthenticationService qmeTokenAuthenticationService;

    /**
     * Public Constructor
     *
     * @param qmeTokenAuthenticationService QMETokenAuthenticationService
     */
    public QMeTokenFilter(QMETokenAuthenticationService qmeTokenAuthenticationService) {
        this.qmeTokenAuthenticationService = qmeTokenAuthenticationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        QMeUserDetails qMeUserDetails = qmeTokenAuthenticationService.getAuthenticatedUser((HttpServletRequest) request);
        if(qMeUserDetails != null && qMeUserDetails.getQMeAuthenticatedUser() != null){
            SecurityContextHolder.getContext().setAuthentication(qMeUserDetails.getQMeAuthenticatedUser());
        }
        chain.doFilter(request, response);
    }
}
