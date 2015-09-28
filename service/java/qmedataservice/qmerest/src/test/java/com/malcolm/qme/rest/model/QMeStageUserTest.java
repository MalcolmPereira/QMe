/**
 * Name      : com.malcolm.qme.rest.model.QMeStageUser.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : QMe Staging User Test
 */
package com.malcolm.qme.rest.model;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class QMeStageUserTest {

    @Test
    public void testGetUserName() throws Exception {
        QMeStageUser qMeStageUser = new QMeStageUser();
        qMeStageUser.setUserName("someuserName");
        assertThat(qMeStageUser.getUserName(), equalTo("someuserName"));
    }

    @Test
    public void testSetUserName() throws Exception {
        QMeStageUser qMeStageUser = new QMeStageUser();
        qMeStageUser.setUserName("someuserName");
        assertThat(qMeStageUser.getUserName(), equalTo("someuserName"));
    }

    @Test
    public void testGetUserPassword() throws Exception {
        QMeStageUser qMeStageUser = new QMeStageUser();
        qMeStageUser.setUserPassword("somepassword");
        assertThat(qMeStageUser.getUserPassword(), equalTo("somepassword"));
    }

    @Test
    public void testSetUserPassword() throws Exception {
        QMeStageUser qMeStageUser = new QMeStageUser();
        qMeStageUser.setUserPassword("somepassword");
        assertThat(qMeStageUser.getUserPassword(), equalTo("somepassword"));
    }

    @Test
    public void testGetUserFirstName() throws Exception {
        QMeStageUser qMeStageUser = new QMeStageUser();
        qMeStageUser.setUserFirstName("firstname");
        assertThat(qMeStageUser.getUserFirstName(), equalTo("firstname"));
    }

    @Test
    public void testSetUserFirstName() throws Exception {
        QMeStageUser qMeStageUser = new QMeStageUser();
        qMeStageUser.setUserFirstName("firstname");
        assertThat(qMeStageUser.getUserFirstName(), equalTo("firstname"));
    }

    @Test
    public void testGetUserLastName() throws Exception {
        QMeStageUser qMeStageUser = new QMeStageUser();
        qMeStageUser.setUserLastName("lastname");
        assertThat(qMeStageUser.getUserLastName(), equalTo("lastname"));
    }

    @Test
    public void testSetUserLastName() throws Exception {
        QMeStageUser qMeStageUser = new QMeStageUser();
        qMeStageUser.setUserLastName("lastname");
        assertThat(qMeStageUser.getUserLastName(), equalTo("lastname"));
    }

    @Test
    public void testGetUserEmail() throws Exception {
        QMeStageUser qMeStageUser = new QMeStageUser();
        qMeStageUser.setUserEmail("email");
        assertThat(qMeStageUser.getUserEmail(), equalTo("email"));
    }

    @Test
    public void testSetUserEmail() throws Exception {
        QMeStageUser qMeStageUser = new QMeStageUser();
        qMeStageUser.setUserEmail("email");
        assertThat(qMeStageUser.getUserEmail(), equalTo("email"));
    }

    @Test
    public void testGetConfirmURL() throws Exception {
        QMeStageUser qMeStageUser = new QMeStageUser();
        qMeStageUser.setConfirmURL("url");
        assertThat(qMeStageUser.getConfirmURL(), equalTo("url"));
    }

    @Test
    public void testSetConfirmURL() throws Exception {
        QMeStageUser qMeStageUser = new QMeStageUser();
        qMeStageUser.setConfirmURL("url");
        assertThat(qMeStageUser.getConfirmURL(), equalTo("url"));
    }
}