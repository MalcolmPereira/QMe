/**
 * Name      : com.malcolm.qme.core.domain.QMeExceptionTest.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : QMe Exception Class Test
 */
package com.malcolm.qme.core.repository;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Malcolm
 */
public class QMeExceptionTest{

    @Test
    public void testGetErrorMessage(){
        QMeException qmeErr = new QMeException("Some Error Message");
        assertThat(qmeErr.getErrorMessage(),equalTo("Some Error Message"));
        qmeErr = new QMeException(new Exception("Some Error Message"));
        assertThat(qmeErr.getErrorMessage(),equalTo("Some Error Message"));
        qmeErr = new QMeException("Some Error Message", new Exception("Some Error Message"));
        assertThat(qmeErr.getErrorMessage(),equalTo("Some Error Message"));
    }

    @Test
    public void testGetError() {
        QMeException qmeErr = new QMeException("Some Error Message");
        assertNull(qmeErr.getError());
        qmeErr = new QMeException(new Exception("Some Error Message"));
        assertNotNull(qmeErr.getError());
        assertThat(qmeErr.getErrorMessage(), equalTo("Some Error Message"));
        qmeErr = new QMeException("Some Error Message", new Exception("Some Error Message"));
        assertNotNull(qmeErr.getError());
        assertThat(qmeErr.getErrorMessage(), equalTo("Some Error Message"));
    }

    @Test
    public void testGetErrorMessageNullError(){
        QMeException qmeErr = new QMeException((Throwable)null);
        assertNull(qmeErr.getErrorMessage());
        assertNull(qmeErr.getCause());
    }

}