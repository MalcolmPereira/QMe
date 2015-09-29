/**
 * Name      : com.malcolm.qme.core.domain.UserQuizGameTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Game Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserQuizGameTest {

    @Test
    public void testGetUserGameToken() throws Exception {
        UserQuizGame userQuizGame = new UserQuizGame(1L, 1L, 1L, 1, LocalDateTime.now(), LocalDateTime.now());
        assertThat(userQuizGame.getUserGameToken(), equalTo(1L));
        userQuizGame = new UserQuizGame(1L, 1L, 1L);
        assertThat(userQuizGame.getUserGameToken(), equalTo(1L));
    }

    @Test
    public void testGetUserID() throws Exception {
        UserQuizGame userQuizGame = new UserQuizGame(1L, 1L, 1L, 1, LocalDateTime.now(), LocalDateTime.now());
        assertThat(userQuizGame.getUserID(), equalTo(1L));
        userQuizGame = new UserQuizGame(1L, 1L, 1L);
        assertThat(userQuizGame.getUserID(), equalTo(1L));
    }

    @Test
    public void testGetCategoryID() throws Exception {
        UserQuizGame userQuizGame = new UserQuizGame(1L, 1L, 1L, 1, LocalDateTime.now(), LocalDateTime.now());
        assertThat(userQuizGame.getCategoryID(), equalTo(1L));
        userQuizGame = new UserQuizGame(1L, 1L, 1L);
        assertThat(userQuizGame.getCategoryID(), equalTo(1L));
    }

    @Test
    public void testGetUserGameScore() throws Exception {
        UserQuizGame userQuizGame = new UserQuizGame(1L, 1L, 1L, 1, LocalDateTime.now(), LocalDateTime.now());
        assertThat(userQuizGame.getUserGameScore(), equalTo(1));
        userQuizGame = new UserQuizGame(1L, 1L, 1L);
        assertThat(userQuizGame.getUserGameScore(), equalTo(0));
    }

    @Test
    public void testGetQuizStartDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        UserQuizGame userQuizGame = new UserQuizGame(1L, 1L, 1L, 1, now, now);
        assertThat(userQuizGame.getQuizStartDate(), equalTo(now));
        userQuizGame = new UserQuizGame(1L, 1L, 1L);
        assertNotNull(userQuizGame.getQuizStartDate());
    }

    @Test
    public void testGetQuizEndDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        UserQuizGame userQuizGame = new UserQuizGame(1L, 1L, 1L, 1, now, now);
        assertThat(userQuizGame.getQuizEndDate(), equalTo(now));
        userQuizGame = new UserQuizGame(1L, 1L, 1L);
        assertNull(userQuizGame.getQuizEndDate());
    }

    @Test
    public void testEquals(){
        UserQuizGame userQuizGame1 = new UserQuizGame(1L, 1L, 1L, 1, LocalDateTime.now(), LocalDateTime.now());
        UserQuizGame userQuizGame2 = new UserQuizGame(1L, 1L, 1L, 1, LocalDateTime.now(), LocalDateTime.now());
        assertTrue(userQuizGame1.equals(userQuizGame2));
        assertTrue(userQuizGame1.equals(userQuizGame1));
        userQuizGame1 = new UserQuizGame(1L, 1L, 1L, 1, LocalDateTime.now(), LocalDateTime.now());
        userQuizGame2 = new UserQuizGame(2L, 1L, 1L, 2, LocalDateTime.now(), LocalDateTime.now());
        assertFalse(userQuizGame1.equals(userQuizGame2));
        userQuizGame1 = new UserQuizGame(1L, 1L, 1L);
        userQuizGame2 = new UserQuizGame(1L, 1L, 1L);
        assertTrue(userQuizGame1.equals(userQuizGame2));
        userQuizGame1 = new UserQuizGame(1L, 1L, 1L);
        userQuizGame2 = new UserQuizGame(2L, 2L, 1L);
        assertFalse(userQuizGame1.equals(userQuizGame2));
        assertFalse(userQuizGame1.equals(null));
        assertFalse(userQuizGame1.equals(""));
    }

    @Test
    public void testHashCode(){
        UserQuizGame userQuizGame1 = new UserQuizGame(1L, 1L, 1L, 1, LocalDateTime.now(), LocalDateTime.now());
        UserQuizGame userQuizGame2 = new UserQuizGame(1L, 1L, 1L, 1, LocalDateTime.now(), LocalDateTime.now());
        assertThat(userQuizGame1.hashCode(), equalTo(userQuizGame2.hashCode()));
        userQuizGame1 = new UserQuizGame(1L, 1L, 1L, 1, LocalDateTime.now(), LocalDateTime.now());
        userQuizGame2 = new UserQuizGame(2L, 1L, 1L, 2, LocalDateTime.now(), LocalDateTime.now());
        assertNotEquals(userQuizGame1.hashCode(),userQuizGame2.hashCode());
        userQuizGame1 = new UserQuizGame(1L, 1L, 1L);
        userQuizGame2 = new UserQuizGame(1L, 1L, 1L);
        assertThat(userQuizGame1.hashCode(), equalTo(userQuizGame2.hashCode()));
        userQuizGame1 = new UserQuizGame(1L, 1L, 1L);
        userQuizGame2 = new UserQuizGame(2L, 2L, 1L);
        assertNotEquals(userQuizGame1.hashCode(),userQuizGame2.hashCode());
    }

}