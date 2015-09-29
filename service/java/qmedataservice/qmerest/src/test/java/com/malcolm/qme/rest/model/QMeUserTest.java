/**
 * Name      : com.malcolm.qme.rest.model.QMeUserTest.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : QMe User Test
 */
package com.malcolm.qme.rest.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Malcolm
 */
public class QMeUserTest {

    @Test
    public void testGetUserName() throws Exception {
        QMeUser qMeUser = new QMeUser();
        qMeUser.setUserName("some user");
        assertThat(qMeUser.getUserName(), equalTo("some user"));
    }

    @Test
    public void testSetUserName() throws Exception {
        QMeUser qMeUser = new QMeUser();
        qMeUser.setUserName("some user");
        assertThat(qMeUser.getUserName(), equalTo("some user"));
    }

    @Test
    public void testGetUserPassword() throws Exception {
        QMeUser qMeUser = new QMeUser();
        qMeUser.setUserPassword("some user password");
        assertThat(qMeUser.getUserPassword(), equalTo("some user password"));
    }

    @Test
    public void testSetUserPassword() throws Exception {
        QMeUser qMeUser = new QMeUser();
        qMeUser.setUserPassword("some user password");
        assertThat(qMeUser.getUserPassword(), equalTo("some user password"));
    }

    @Test
    public void testGetUserFirstName() throws Exception {
        QMeUser qMeUser = new QMeUser();
        qMeUser.setUserFirstName("First Name");
        assertThat(qMeUser.getUserFirstName(), equalTo("First Name"));
    }

    @Test
    public void testSetUserFirstName() throws Exception {
        QMeUser qMeUser = new QMeUser();
        qMeUser.setUserFirstName("First Name");
        assertThat(qMeUser.getUserFirstName(), equalTo("First Name"));
    }

    @Test
    public void testGetUserLastName() throws Exception {
        QMeUser qMeUser = new QMeUser();
        qMeUser.setUserLastName("Last Name");
        assertThat(qMeUser.getUserLastName(), equalTo("Last Name"));
    }

    @Test
    public void testSetUserLastName() throws Exception {
        QMeUser qMeUser = new QMeUser();
        qMeUser.setUserLastName("Last Name");
        assertThat(qMeUser.getUserLastName(), equalTo("Last Name"));
    }

    @Test
    public void testGetUserEmail() throws Exception {
        QMeUser qMeUser = new QMeUser();
        qMeUser.setUserEmail("Email");
        assertThat(qMeUser.getUserEmail(), equalTo("Email"));
    }

    @Test
    public void testSetUserEmail() throws Exception {
        QMeUser qMeUser = new QMeUser();
        qMeUser.setUserEmail("Email");
        assertThat(qMeUser.getUserEmail(), equalTo("Email"));
    }

    @Test
    public void testGetUserRoles() throws Exception {
        QMeUser qMeUser = new QMeUser();
        List<String> userRoles = new ArrayList<>();
        userRoles.add("Role 1");
        userRoles.add("Role 2");
        qMeUser.setUserRoles(userRoles);
        assertThat(qMeUser.getUserRoles().size(), equalTo(2));
    }

    @Test
    public void testSetUserRoles() throws Exception {
        QMeUser qMeUser = new QMeUser();
        List<String> userRoles = new ArrayList<>();
        userRoles.add("Role 1");
        userRoles.add("Role 2");
        qMeUser.setUserRoles(userRoles);
        assertThat(qMeUser.getUserRoles().size(), equalTo(2));
    }

}