/**
 * Name      : com.malcolm.qme.rest.controller.QMeControllerTest.java
 * Date      : 5/25/15
 * Developer : Malcolm
 * Purpose   : QMe REST Controller Base Test:
 */

package com.malcolm.qme.rest.controller;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

/**
 * @author malcolm
 */
public abstract class QMeControllerTest {
    /**
     * MockMVC Instance
     */
    MockMvc mockMvc;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        final ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver = new ExceptionHandlerExceptionResolver();
        final StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerBeanDefinition("advice", new RootBeanDefinition(QMeExceptionHandler.class, null, null));
        exceptionHandlerExceptionResolver.setApplicationContext(applicationContext);
        exceptionHandlerExceptionResolver.afterPropertiesSet();

        mockMvc = this.getMockMVCInstance(exceptionHandlerExceptionResolver);
    }

    /**
     * Abstract method to get Mock MVC Instance
     *
     * @param exceptionHandlerExceptionResolver Exception Handler Resolver
     * @return MockMvc Instance
     */
    protected abstract MockMvc getMockMVCInstance(final ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver);
}
