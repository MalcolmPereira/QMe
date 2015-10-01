/**
 * Name      : com.malcolm.qme.rest.controller.QMeCORSFilterTest.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : QMe CORS Filter Test
 */
package com.malcolm.qme.rest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QMeCORSFilterTest  {

    /**
     * QME Authentication Header
     */
    private static final String QME_AUTH_HEADER_NAME = "QME-AUTH-TOKEN";

    @Mock
    private ServletRequest req;

    @Mock
    private HttpServletResponse res;

    @Mock
    private FilterChain chain;

    @Mock
    private FilterConfig config;

    @Test
    public void testDoFilter() throws Exception {
        doNothing().when(res).setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
        doNothing().when(res).setHeader("Access-Control-Allow-Methods", "GET,OPTIONS,POST,DELETE,PUT");
        doNothing().when(res).setHeader("Access-Control-Allow-Credentials", "true");
        doNothing().when(res).setHeader("Access-Control-Request-Headers", "content-type,x-requested-with,accept,origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,"+QME_AUTH_HEADER_NAME);
        doNothing().when(res).setHeader("Access-Control-Allow-Headers", "content-type,x-requested-with,accept,origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,"+QME_AUTH_HEADER_NAME);
        doNothing().when(res).setHeader("Access-Control-Max-Age", "3600");
        QMeCORSFilter filter = new QMeCORSFilter();
        filter.doFilter(req, res, chain);
        verify(res).setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
        verify(res).setHeader("Access-Control-Allow-Methods", "GET,OPTIONS,POST,DELETE,PUT");
        verify(res).setHeader("Access-Control-Allow-Credentials", "true");
        verify(res).setHeader("Access-Control-Request-Headers", "content-type,x-requested-with,accept,origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization," + QME_AUTH_HEADER_NAME);
        verify(res).setHeader("Access-Control-Allow-Headers", "content-type,x-requested-with,accept,origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,"+QME_AUTH_HEADER_NAME);
        verify(res).setHeader("Access-Control-Max-Age", "3600");
    }

    @Test
    public void testInit() throws Exception {
        QMeCORSFilter filter = new QMeCORSFilter();
        filter.init(config);
    }

    @Test
    public void testDestroy() throws Exception {
        QMeCORSFilter filter = new QMeCORSFilter();
        filter.destroy();
    }

}