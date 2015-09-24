/**
 * Name      : com.malcolm.qme.rest.controller.QMeExceptionHandlerTest.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : Exception Handler for QMe REST resources Test
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QMeExceptionHandlerTest {

    @Mock
    private HttpServletRequest req;


    @Test
    public void testHandleResourceNotFoundException() throws Exception {
        QMeExceptionHandler qMeExceptionHandler = new QMeExceptionHandler();
        qMeExceptionHandler.handleResourceNotFoundException(req, new QMeResourceNotFoundException("some error"));
        qMeExceptionHandler.handleResourceNotFoundException(req, new QMeResourceNotFoundException("some error", new Exception("some error")));

    }

    @Test
    public void testHandleResourceDataException() throws Exception {
        QMeExceptionHandler qMeExceptionHandler = new QMeExceptionHandler();
        qMeExceptionHandler.handleResourceDataException(req, new QMeInvalidResourceDataException("some error"));
        qMeExceptionHandler.handleResourceDataException(req, new QMeInvalidResourceDataException("some error", new Exception("some error")));

    }

    @Test
    public void testHandleResourceConflictException() throws Exception {
        QMeExceptionHandler qMeExceptionHandler = new QMeExceptionHandler();
        qMeExceptionHandler.handleResourceConflictException(req, new QMeResourceConflictException("some error"));
        qMeExceptionHandler.handleResourceConflictException(req, new QMeResourceConflictException("some error", new Exception("some error")));
    }

    @Test
    public void testHandleResourceException() throws Exception {
        QMeExceptionHandler qMeExceptionHandler = new QMeExceptionHandler();
        qMeExceptionHandler.handleResourceException(req, new QMeServerException("some error"));
        qMeExceptionHandler.handleResourceException(req, new QMeServerException("some error", new Exception("some error")));
    }
}