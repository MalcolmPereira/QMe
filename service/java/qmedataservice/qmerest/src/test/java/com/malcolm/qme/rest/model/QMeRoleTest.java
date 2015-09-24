/**
 * Name      : com.malcolm.qme.rest.model.QMeRoleTest.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : QMe Role Test
 */
package com.malcolm.qme.rest.model;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author malcolm
 */
public class QMeRoleTest {

    @Test
    public void testGetRoleID() throws Exception {
        QMeRole qMeRole = new QMeRole();
        qMeRole.setRoleID(1);
        assertThat(qMeRole.getRoleID(), equalTo(1));
    }

    @Test
    public void testSetRoleID() throws Exception {
        QMeRole qMeRole = new QMeRole();
        qMeRole.setRoleID(1);
        assertThat(qMeRole.getRoleID(), equalTo(1));
    }

    @Test
    public void testGetRoleName() throws Exception {
        QMeRole qMeRole = new QMeRole();
        qMeRole.setRoleName("some role");
        assertThat(qMeRole.getRoleName(), equalTo("some role"));

    }

    @Test
    public void testSetRoleName() throws Exception {
        QMeRole qMeRole = new QMeRole();
        qMeRole.setRoleName("some role");
        assertThat(qMeRole.getRoleName(), equalTo("some role"));
    }

    @Test
    public void testGetRoleDesc() throws Exception {
        QMeRole qMeRole = new QMeRole();
        qMeRole.setRoleDesc("some role desc");
        assertThat(qMeRole.getRoleDesc(), equalTo("some role desc"));
    }

    @Test
    public void testSetRoleDesc() throws Exception {
        QMeRole qMeRole = new QMeRole();
        qMeRole.setRoleDesc("some role desc");
        assertThat(qMeRole.getRoleDesc(), equalTo("some role desc"));
    }

}