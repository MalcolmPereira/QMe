/**
 * Name      : com.malcolm.qme.rest.exception.QMeResourceNotFoundExceptionTest.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : Exception class for resource not found data Test Case
 */
package com.malcolm.qme.rest.exception;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class QMeResourceNotFoundExceptionTest {

    @Test
    public void testGetErrorMessage() throws Exception {
        QMeResourceNotFoundException qMeResourceNotFoundException = new QMeResourceNotFoundException("Some Error Message");
        assertThat(qMeResourceNotFoundException.getErrorMessage(),equalTo("Some Error Message"));
        qMeResourceNotFoundException= new QMeResourceNotFoundException("Some Error Message", new Exception("Some Error Message"));
        assertThat(qMeResourceNotFoundException.getErrorMessage(),equalTo("Some Error Message"));
    }

    @Test
    public void testGetError() throws Exception {
        QMeResourceNotFoundException qMeResourceNotFoundException = new QMeResourceNotFoundException("Some Error Message");
        assertNull(qMeResourceNotFoundException.getError());
        assertThat(qMeResourceNotFoundException.getErrorMessage(), equalTo("Some Error Message"));
        qMeResourceNotFoundException = new QMeResourceNotFoundException("Some Error Message", new Exception("Some Error Message"));
        assertNotNull(qMeResourceNotFoundException.getError());
        assertThat(qMeResourceNotFoundException.getErrorMessage(), equalTo("Some Error Message"));
        assertThat(qMeResourceNotFoundException.getError().getMessage(),equalTo("Some Error Message"));
    }
}