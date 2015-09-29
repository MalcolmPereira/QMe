/**
 * Name      : com.malcolm.qme.rest.model.QMeUserRoleTest.java
 * Date      : 8/25/15
 * Developer : malcolm
 * Purpose   : QMe User Role Test
 */
package com.malcolm.qme.rest.model;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author malcolm
 */
public class QMeUserRoleTest {

    @Test
    public void testGetUserRoleID() throws Exception {
        QMeUserRole qMeUserRole = new QMeUserRole();
        qMeUserRole.setUserRoleID(1L);
        assertThat(qMeUserRole.getUserRoleID(), equalTo(1L));
    }


    @Test
    public void testSetUserRoleID() throws Exception {
        QMeUserRole qMeUserRole = new QMeUserRole();
        qMeUserRole.setUserRoleID(1L);
        assertThat(qMeUserRole.getUserRoleID(), equalTo(1L));
    }

    @Test
    public void testGetRoleID() throws Exception {
        QMeUserRole qMeUserRole = new QMeUserRole();
        qMeUserRole.setRoleID(1);
        assertThat(qMeUserRole.getRoleID(), equalTo(1));
    }

    @Test
    public void testSetRoleID() throws Exception {
        QMeUserRole qMeUserRole = new QMeUserRole();
        qMeUserRole.setRoleID(1);
        assertThat(qMeUserRole.getRoleID(), equalTo(1));
    }

    @Test
    public void testSetRoleName() throws Exception {
        QMeUserRole qMeUserRole = new QMeUserRole();
        qMeUserRole.setRoleName("some role");
        assertThat(qMeUserRole.getRoleName(), equalTo("some role"));
    }

    @Test
    public void testGetUserID() throws Exception {
        QMeUserRole qMeUserRole = new QMeUserRole();
        qMeUserRole.setUserID(1L);
        assertThat(qMeUserRole.getUserID(), equalTo(1L));
    }

    @Test
    public void testSetUserID() throws Exception {
        QMeUserRole qMeUserRole = new QMeUserRole();
        qMeUserRole.setUserID(1L);
        assertThat(qMeUserRole.getUserID(), equalTo(1L));
    }
}