/**
 * Name      : com.malcolm.qme.rest.model.QMeUserDetailTest.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : QMeUser Details Model for REST Service Controller Test
 */
package com.malcolm.qme.rest.model;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Malcolm
 */
public class QMeUserDetailTest {

    @Test
    public void testGetUserId() throws Exception {
        QMeUserDetail qMeUserDetail = new QMeUserDetail();
        qMeUserDetail.setUserId(1L);
        assertThat(qMeUserDetail.getUserId(), equalTo(1L));
    }

    @Test
    public void testSetUserId() throws Exception {
        QMeUserDetail qMeUserDetail = new QMeUserDetail();
        qMeUserDetail.setUserId(1L);
        assertThat(qMeUserDetail.getUserId(), equalTo(1L));
    }

    @Test
    public void testGetUserRegisteredDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QMeUserDetail qMeUserDetail = new QMeUserDetail();
        qMeUserDetail.setUserRegisteredDate(now);
        assertThat(qMeUserDetail.getUserRegisteredDate(), equalTo(now));
    }

    @Test
    public void testSetUserRegisteredDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QMeUserDetail qMeUserDetail = new QMeUserDetail();
        qMeUserDetail.setUserRegisteredDate(now);
        assertThat(qMeUserDetail.getUserRegisteredDate(), equalTo(now));
    }

    @Test
    public void testGetUserUpdateDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QMeUserDetail qMeUserDetail = new QMeUserDetail();
        qMeUserDetail.setUserUpdateDate(now);
        assertThat(qMeUserDetail.getUserUpdateDate(), equalTo(now));
    }

    @Test
    public void testSetUserUpdateDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QMeUserDetail qMeUserDetail = new QMeUserDetail();
        qMeUserDetail.setUserUpdateDate(now);
        assertThat(qMeUserDetail.getUserUpdateDate(), equalTo(now));
    }

    @Test
    public void testGetUserLastLoginDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QMeUserDetail qMeUserDetail = new QMeUserDetail();
        qMeUserDetail.setUserLastLoginDate(now);
        assertThat(qMeUserDetail.getUserLastLoginDate(), equalTo(now));
    }

    @Test
    public void testSetUserLastLoginDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QMeUserDetail qMeUserDetail = new QMeUserDetail();
        qMeUserDetail.setUserLastLoginDate(now);
        assertThat(qMeUserDetail.getUserLastLoginDate(), equalTo(now));
    }

    @Test
    public void testGetUpdateUserID() throws Exception {
        QMeUserDetail qMeUserDetail = new QMeUserDetail();
        qMeUserDetail.setUpdateUserID(1L);
        assertThat(qMeUserDetail.getUpdateUserID(), equalTo(1L));
    }

    @Test
    public void testSetUpdateUserID() throws Exception {
        QMeUserDetail qMeUserDetail = new QMeUserDetail();
        qMeUserDetail.setUpdateUserID(1L);
        assertThat(qMeUserDetail.getUpdateUserID(), equalTo(1L));
    }

    @Test
    public void testGetUpdateUserName() throws Exception {
        QMeUserDetail qMeUserDetail = new QMeUserDetail();
        qMeUserDetail.setUpdateUserName("some user");
        assertThat(qMeUserDetail.getUpdateUserName(), equalTo("some user"));
    }

    @Test
    public void testSetUpdateUserName() throws Exception {
        QMeUserDetail qMeUserDetail = new QMeUserDetail();
        qMeUserDetail.setUpdateUserName("some user");
        assertThat(qMeUserDetail.getUpdateUserName(), equalTo("some user"));
    }
}