/**
 * Name      : com.malcolm.qme.rest.exception.QMeResourceConflictExceptionTest.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : Exception class for resource conflict data Test Case
 */
package com.malcolm.qme.rest.exception;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class QMeResourceConflictExceptionTest {

    @Test
    public void testGetErrorMessage() {
        QMeResourceConflictException qMeResourceConflictException = new QMeResourceConflictException("Some Error Message");
        assertThat(qMeResourceConflictException.getErrorMessage(),equalTo("Some Error Message"));
        qMeResourceConflictException = new QMeResourceConflictException("Some Error Message", new Exception("Some Error Message"));
        assertThat(qMeResourceConflictException.getErrorMessage(),equalTo("Some Error Message"));
    }

    @Test
    public void testGetError() {
        QMeResourceConflictException qMeResourceConflictException = new QMeResourceConflictException("Some Error Message");
        assertNull(qMeResourceConflictException.getError());
        assertThat(qMeResourceConflictException.getErrorMessage(), equalTo("Some Error Message"));
        qMeResourceConflictException = new QMeResourceConflictException("Some Error Message", new Exception("Some Error Message"));
        assertNotNull(qMeResourceConflictException.getError());
        assertThat(qMeResourceConflictException.getErrorMessage(), equalTo("Some Error Message"));
        assertThat(qMeResourceConflictException.getError().getMessage(),equalTo("Some Error Message"));
    }
}