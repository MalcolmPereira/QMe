/**
 * Name      : com.malcolm.qme.rest.controller.QMeCORSFilter.java
 * Date      : 7/15/15
 * Developer : Malcolm
 * Purpose   : QMe CORS Filter
 */

package com.malcolm.qme.rest.controller;

import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Malcolm
 */
@Component
public class QMeCORSFilter implements Filter {
    /*
   * Allow Origin
   */
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    /*
     * Allow All Origin
     */
    private static final String ALLOW_ORIGIN_ALL = "*";
    /*
    * Allow Origin Domain
    */
    private static final String ALLOW_ORIGIN_LOCAL_DOMAIN = "http://localhost:8000";

    /**
     * Allow Methods
     */
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";

    /**
     * Methods
     */
    private static final String ALLOW_METHODS = "GET,OPTIONS,POST,DELETE,PUT";

    /**
     * Allow Control Maximum Age
     */
    private static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";

    /**
     * Maximum Age
     */
    private static final String MAX_AGE = "3600";

    /**
     * Allow Headers
     */
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";

    /**
     * Allow Credentials
     */
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";

    /**
     * Allow Credentials True
     */
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS_TRUE = "true";

    /**
     * Allow Control Request Headers
     */
    private static final String ACCESS_CONTROL_REQUEST_HEADERS = "Access-Control-Request-Headers";

    /**
     * Control Header Names
     */
    private static final String ACCESS_CONTROL_HEADERS_NAME = "content-type,x-requested-with,accept,origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,x-csrftoken,X-XSRF-TOKEN";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, ALLOW_ORIGIN_LOCAL_DOMAIN);
        response.setHeader(ACCESS_CONTROL_ALLOW_METHODS,ALLOW_METHODS );
        response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS,ACCESS_CONTROL_ALLOW_CREDENTIALS_TRUE );
        response.setHeader(ACCESS_CONTROL_REQUEST_HEADERS, ACCESS_CONTROL_HEADERS_NAME);
        response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS,ACCESS_CONTROL_HEADERS_NAME);
        response.setHeader(ACCESS_CONTROL_MAX_AGE, MAX_AGE);
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}
