/**
 * Name      : com.malcolm.qme.rest.exception.QMeInvalidResourceDataExceptionTest.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : Exception class for invalid resource data Test Case
 */
package com.malcolm.qme.rest.exception;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class QMeInvalidResourceDataExceptionTest {

    @Test
    public void testGetErrorMessage() {
        QMeInvalidResourceDataException qMeInvalidResourceDataException = new QMeInvalidResourceDataException("Some Error Message");
        assertThat(qMeInvalidResourceDataException.getErrorMessage(),equalTo("Some Error Message"));
        qMeInvalidResourceDataException = new QMeInvalidResourceDataException("Some Error Message", new Exception("Some Error Message"));
        assertThat(qMeInvalidResourceDataException.getErrorMessage(),equalTo("Some Error Message"));
    }

    @Test
    public void testGetError() {
        QMeInvalidResourceDataException qMeInvalidResourceDataException = new QMeInvalidResourceDataException("Some Error Message");
        assertNull(qMeInvalidResourceDataException.getError());
        assertThat(qMeInvalidResourceDataException.getErrorMessage(), equalTo("Some Error Message"));
        qMeInvalidResourceDataException = new QMeInvalidResourceDataException("Some Error Message", new Exception("Some Error Message"));
        assertNotNull(qMeInvalidResourceDataException.getError());
        assertThat(qMeInvalidResourceDataException.getErrorMessage(), equalTo("Some Error Message"));
        assertThat(qMeInvalidResourceDataException.getError().getMessage(),equalTo("Some Error Message"));
    }

}