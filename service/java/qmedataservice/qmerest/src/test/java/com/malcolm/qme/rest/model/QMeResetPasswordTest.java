/**
 * Name      : com.malcolm.qme.rest.model.QMeResetPassword.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : QMe Reset Password Token Test
 */
package com.malcolm.qme.rest.model;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Malcolm
 */
public class QMeResetPasswordTest {

    @Test
    public void testGetToken() throws Exception {
        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setToken("some token");
        assertThat(qMeResetPassword.getToken(), equalTo("some token"));
    }

    @Test
    public void testSetToken() throws Exception {
        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setToken("some token");
        assertThat(qMeResetPassword.getToken(), equalTo("some token"));
    }

    @Test
    public void testGetUserName() throws Exception {
        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setUserName("some user");
        assertThat(qMeResetPassword.getUserName(), equalTo("some user"));

    }

    @Test
    public void testSetUserName() throws Exception {
        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setUserName("some user");
        assertThat(qMeResetPassword.getUserName(), equalTo("some user"));
    }

    @Test
    public void testGetUserPassword() throws Exception {
        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setUserPassword("some user password");
        assertThat(qMeResetPassword.getUserPassword(), equalTo("some user password"));
    }

    @Test
    public void testSetUserPassword() throws Exception {
        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setUserPassword("some user password");
        assertThat(qMeResetPassword.getUserPassword(), equalTo("some user password"));
    }
}