/**
 * Name      : com.malcolm.qme.rest.exception.QMeResourceExceptionTest.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : Exception class for resource data Test Case
 */
package com.malcolm.qme.rest.exception;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class QMeResourceExceptionTest {

    @Test
    public void testGetErrorMessage() throws Exception {
        QMeResourceException qMeResourceException = new QMeResourceException("Some Error Message");
        assertThat(qMeResourceException.getErrorMessage(),equalTo("Some Error Message"));
        qMeResourceException= new QMeResourceException("Some Error Message", new Exception("Some Error Message"));
        assertThat(qMeResourceException.getErrorMessage(),equalTo("Some Error Message"));
    }

    @Test
    public void testGetError() throws Exception {
        QMeResourceException qMeResourceException = new QMeResourceException("Some Error Message");
        assertNull(qMeResourceException.getError());
        assertThat(qMeResourceException.getErrorMessage(), equalTo("Some Error Message"));
        qMeResourceException = new QMeResourceException("Some Error Message", new Exception("Some Error Message"));
        assertNotNull(qMeResourceException.getError());
        assertThat(qMeResourceException.getErrorMessage(), equalTo("Some Error Message"));
        assertThat(qMeResourceException.getError().getMessage(),equalTo("Some Error Message"));
    }
}