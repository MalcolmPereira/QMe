/**
 * Name      : com.malcolm.qme.rest.exception.QMeServerExceptionTest.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : Exception class for server exception Test Case
 */
package com.malcolm.qme.rest.exception;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class QMeServerExceptionTest {

    @Test
    public void testGetErrorMessage() throws Exception {
        QMeServerException qMeServerException = new QMeServerException("Some Error Message");
        assertThat(qMeServerException.getErrorMessage(),equalTo("Some Error Message"));
        qMeServerException= new QMeServerException("Some Error Message", new Exception("Some Error Message"));
        assertThat(qMeServerException.getErrorMessage(),equalTo("Some Error Message"));
    }

    @Test
    public void testGetError() throws Exception {
        QMeServerException qMeServerException = new QMeServerException("Some Error Message");
        assertNull(qMeServerException.getError());
        assertThat(qMeServerException.getErrorMessage(), equalTo("Some Error Message"));
        qMeServerException = new QMeServerException("Some Error Message", new Exception("Some Error Message"));
        assertNotNull(qMeServerException.getError());
        assertThat(qMeServerException.getErrorMessage(), equalTo("Some Error Message"));
        assertThat(qMeServerException.getError().getMessage(),equalTo("Some Error Message"));
    }
}