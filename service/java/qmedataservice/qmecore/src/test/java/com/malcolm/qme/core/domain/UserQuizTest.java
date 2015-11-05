/**
 * Name      : com.malcolm.qme.core.domain.UserQuizTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserQuizTest {

    @Test
    public void testGetUserQuizID() throws Exception {
        UserQuiz userQuiz = new UserQuiz(1L, 1L, 1L, 1L,LocalDateTime.now(), LocalDateTime.now(), "sometoken",1, 1, Boolean.TRUE);
        assertThat(userQuiz.getUserQuizID(), equalTo(1L));
        userQuiz = new UserQuiz(1L, 1L, 1L, 1, "sometoken");
        assertThat(userQuiz.getUserQuizID(), equalTo(0L));
    }

    @Test
    public void testGetUserID() throws Exception {
        UserQuiz userQuiz = new UserQuiz(1L, 1L, 1L, 1L,LocalDateTime.now(), LocalDateTime.now(), "sometoken",1, 1, Boolean.TRUE);
        assertThat(userQuiz.getUserID(), equalTo(1L));
        userQuiz = new UserQuiz(1L, 1L, 1L, 1, "sometoken");
        assertThat(userQuiz.getUserID(), equalTo(1L));
    }

    @Test
    public void testGetQuizID() throws Exception {
        UserQuiz userQuiz = new UserQuiz(1L, 1L, 1L, 1L,LocalDateTime.now(), LocalDateTime.now(), "sometoken",1, 1, Boolean.TRUE);
        assertThat(userQuiz.getQuizID(), equalTo(1L));
        userQuiz = new UserQuiz(1L, 1L, 1L, 1, "sometoken");
        assertThat(userQuiz.getQuizID(), equalTo(1L));
    }

    @Test
    public void testGetCategoryID() throws Exception {
        UserQuiz userQuiz = new UserQuiz(1L, 1L, 1L, 1L,LocalDateTime.now(), LocalDateTime.now(), "sometoken",1, 1, Boolean.TRUE);
        assertThat(userQuiz.getCategoryID(), equalTo(1L));
        userQuiz = new UserQuiz(1L, 1L, 1L, 1, "sometoken");
        assertThat(userQuiz.getCategoryID(), equalTo(1L));
    }

    @Test
    public void testGetQuizStartDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        UserQuiz userQuiz = new UserQuiz(1L, 1L, 1L, 1L,now, now, "sometoken",1, 1, Boolean.TRUE);
        assertThat(userQuiz.getQuizStartDate(), equalTo(now));
        userQuiz = new UserQuiz(1L, 1L, 1L, 1, "sometoken");
        assertNotNull(userQuiz.getQuizStartDate());
    }

    @Test
    public void testGetQuizEndDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        UserQuiz userQuiz = new UserQuiz(1L, 1L, 1L, 1L,now, now, "sometoken",1, 1, Boolean.TRUE);
        assertThat(userQuiz.getQuizEndDate(), equalTo(now));
        userQuiz = new UserQuiz(1L, 1L, 1L, 1, "sometoken");
        assertNull(userQuiz.getQuizEndDate());
    }

    @Test
    public void testGetUserQuizToken() throws Exception {
        UserQuiz userQuiz = new UserQuiz(1L, 1L, 1L, 1L,LocalDateTime.now(), LocalDateTime.now(), "sometoken",1, 1, Boolean.TRUE);
        assertThat(userQuiz.getUserQuizToken(), equalTo("sometoken"));
        userQuiz = new UserQuiz(1L, 1L, 1L, 1, "sometoken");
        assertThat(userQuiz.getUserQuizToken(), equalTo("sometoken"));
    }

    @Test
    public void testGetQuizUserScore() throws Exception {
        UserQuiz userQuiz = new UserQuiz(1L, 1L, 1L, 1L,LocalDateTime.now(), LocalDateTime.now(), "sometoken",1, 1, Boolean.TRUE);
        assertThat(userQuiz.getQuizUserScore(), equalTo(1));
        userQuiz = new UserQuiz(1L, 1L, 1L, 1, "sometoken");
        assertThat(userQuiz.getQuizUserScore(), equalTo(0));
    }

    @Test
    public void testGetQuizMaxScore() throws Exception {
        UserQuiz userQuiz = new UserQuiz(1L, 1L, 1L, 1L,LocalDateTime.now(), LocalDateTime.now(), "sometoken",1, 1, Boolean.TRUE);
        assertThat(userQuiz.getQuizMaxScore(), equalTo(1));
        userQuiz = new UserQuiz(1L, 1L, 1L, 1, "sometoken");
        assertThat(userQuiz.getQuizMaxScore(), equalTo(1));
    }

    @Test
    public void testGetQuizComplete() throws Exception {
        UserQuiz userQuiz = new UserQuiz(1L, 1L, 1L, 1L,LocalDateTime.now(), LocalDateTime.now(), "sometoken",1, 1, Boolean.TRUE);
        assertThat(userQuiz.getQuizComplete(), equalTo(Boolean.TRUE));
        userQuiz = new UserQuiz(1L, 1L, 1L, 1, "sometoken");
        assertThat(userQuiz.getQuizComplete(), equalTo(Boolean.FALSE));
    }

    @Test
    public void testEquals(){
        UserQuiz userQuiz1 = new UserQuiz(1L, 1L, 1L, 1L,LocalDateTime.now(), LocalDateTime.now(), "sometoken",1, 1, Boolean.TRUE);
        UserQuiz userQuiz2 = new UserQuiz(1L, 1L, 1L, 1L,LocalDateTime.now(), LocalDateTime.now(), "sometoken",1, 1, Boolean.TRUE);
        assertTrue(userQuiz1.equals(userQuiz2));
        assertTrue(userQuiz1.equals(userQuiz1));
        userQuiz1 = new UserQuiz(1L, 1L, 1L, 1L,LocalDateTime.now(), LocalDateTime.now(), "sometoken1",1, 1, Boolean.TRUE);
        userQuiz2 = new UserQuiz(2L, 2L, 2L, 2L,LocalDateTime.now(), LocalDateTime.now(), "sometoken2",1, 1, Boolean.TRUE);
        assertFalse(userQuiz1.equals(userQuiz2));
        userQuiz1 = new UserQuiz(1L, 1L, 1L, 1, "sometoken");
        userQuiz2 = new UserQuiz(1L, 1L, 1L, 1, "sometoken");
        assertTrue(userQuiz1.equals(userQuiz2));
        userQuiz1 = new UserQuiz(1L, 1L, 1L, 1, "sometoken1");
        userQuiz2 = new UserQuiz(2L, 2L, 2L, 1, "sometoken2");
        assertFalse(userQuiz1.equals(userQuiz2));
        assertFalse(userQuiz1.equals(null));
        assertFalse(userQuiz1.equals(""));
    }

    @Test
    public void testHashCode(){
        UserQuiz userQuiz1 = new UserQuiz(1L, 1L, 1L, 1L,LocalDateTime.now(), LocalDateTime.now(), "sometoken",1, 1, Boolean.TRUE);
        UserQuiz userQuiz2 = new UserQuiz(1L, 1L, 1L, 1L,LocalDateTime.now(), LocalDateTime.now(), "sometoken",1, 1, Boolean.TRUE);
        assertThat(userQuiz1.hashCode(), equalTo(userQuiz2.hashCode()));
        userQuiz1 = new UserQuiz(1L, 1L, 1L, 1L,LocalDateTime.now(), LocalDateTime.now(), "sometoken1",1, 1, Boolean.TRUE);
        userQuiz2 = new UserQuiz(2L, 2L, 2L, 2L,LocalDateTime.now(), LocalDateTime.now(), "sometoken2",1, 1, Boolean.TRUE);
        assertNotEquals(userQuiz1.hashCode(),userQuiz2.hashCode());
        userQuiz1 = new UserQuiz(1L, 1L, 1L, 1, "sometoken");
        userQuiz2 = new UserQuiz(1L, 1L, 1L, 1, "sometoken");
        assertThat(userQuiz1.hashCode(), equalTo(userQuiz2.hashCode()));
        userQuiz1 = new UserQuiz(1L, 1L, 1L, 1, "sometoken1");
        userQuiz2 = new UserQuiz(2L, 2L, 2L, 1, "sometoken2");
        assertNotEquals(userQuiz1.hashCode(),userQuiz2.hashCode());
    }
}