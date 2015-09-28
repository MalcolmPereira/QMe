/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserQuizEntityTest {

    @Test
    public void testEquals() throws Exception {
        LocalDateTime now = LocalDateTime.now();

        UserQuizEntity userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, 1, 10,(byte) 1);
        UserQuizEntity userQuizEntity2 = new UserQuizEntity(1L, 1L, 1L, now, 1, 10,(byte) 1);
        assertTrue(userQuizEntity1.equals(userQuizEntity2));
        assertTrue(userQuizEntity1.equals(userQuizEntity1));

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token", (byte) 1);
        userQuizEntity2 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token", (byte) 1);
        assertTrue(userQuizEntity1.equals(userQuizEntity2));

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token", (byte) 1);
        userQuizEntity1.setQuizId(1L);
        userQuizEntity2 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token", (byte) 1);
        userQuizEntity2.setQuizId(1L);
        assertTrue(userQuizEntity1.equals(userQuizEntity2));


        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, 1, 10,(byte) 1);
        userQuizEntity2 = new UserQuizEntity(2L, 2L, 2L, now, 1, 10,(byte) 0);
        assertFalse(userQuizEntity1.equals(userQuizEntity2));

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token", (byte) 1);
        userQuizEntity2 = new UserQuizEntity(2L, 2L, 2L, now, now, 1, 10, "token", (byte) 0);
        assertFalse(userQuizEntity1.equals(userQuizEntity2));

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token", (byte) 1);
        userQuizEntity1.setQuizId(1L);
        userQuizEntity2 = new UserQuizEntity(2L, 2L, 2L, now, now, 1, 10, "token", (byte) 0);
        userQuizEntity2.setQuizId(2L);
        assertFalse(userQuizEntity1.equals(userQuizEntity2));

        assertFalse(userQuizEntity1.equals(null));
        assertFalse(userQuizEntity1.equals(""));

    }

    @Test
    public void testHashCode() throws Exception {
        LocalDateTime now = LocalDateTime.now();

        UserQuizEntity userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, 1, 10,(byte) 1);
        UserQuizEntity userQuizEntity2 = new UserQuizEntity(1L, 1L, 1L, now, 1, 10,(byte) 1);
        assertThat(userQuizEntity1.hashCode(), equalTo(userQuizEntity2.hashCode()));
        assertThat(userQuizEntity1.hashCode(), equalTo(userQuizEntity1.hashCode()));


        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token", (byte) 1);
        userQuizEntity2 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token", (byte) 1);
        assertThat(userQuizEntity1.hashCode(), equalTo(userQuizEntity2.hashCode()));

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token", (byte) 1);
        userQuizEntity1.setQuizId(1L);
        userQuizEntity2 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token", (byte) 1);
        userQuizEntity2.setQuizId(1L);
        assertThat(userQuizEntity1.hashCode(), equalTo(userQuizEntity2.hashCode()));

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, 1, 10,(byte) 1);
        userQuizEntity2 = new UserQuizEntity(2L, 2L, 2L, now, 1, 10,(byte) 0);
        assertNotEquals(userQuizEntity1.hashCode(), userQuizEntity2.hashCode());

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token", (byte) 1);
        userQuizEntity2 = new UserQuizEntity(2L, 2L, 2L, now, now, 1, 10, "token", (byte) 0);
        assertNotEquals(userQuizEntity1.hashCode(), userQuizEntity2.hashCode());

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token", (byte) 1);
        userQuizEntity1.setQuizId(1L);
        userQuizEntity2 = new UserQuizEntity(2L, 2L, 2L, now, now, 1, 10, "token", (byte) 0);
        userQuizEntity2.setQuizId(2L);
        assertNotEquals(userQuizEntity1.hashCode(), userQuizEntity2.hashCode());
    }
}