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

        UserQuizEntity userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, 1, 10);
        UserQuizEntity userQuizEntity2 = new UserQuizEntity(1L, 1L, 1L, now, 1, 10);
        assertTrue(userQuizEntity1.equals(userQuizEntity2));
        assertTrue(userQuizEntity1.equals(userQuizEntity1));

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token");
        userQuizEntity2 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token");
        assertTrue(userQuizEntity1.equals(userQuizEntity2));

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token");
        userQuizEntity1.setQuizId(1L);
        userQuizEntity2 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token");
        userQuizEntity2.setQuizId(1L);
        assertTrue(userQuizEntity1.equals(userQuizEntity2));


        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, 1, 10);
        userQuizEntity2 = new UserQuizEntity(2L, 2L, 2L, now, 1, 10);
        assertFalse(userQuizEntity1.equals(userQuizEntity2));

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token");
        userQuizEntity2 = new UserQuizEntity(2L, 2L, 2L, now, now, 1, 10, "token");
        assertFalse(userQuizEntity1.equals(userQuizEntity2));

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token");
        userQuizEntity1.setQuizId(1L);
        userQuizEntity2 = new UserQuizEntity(2L, 2L, 2L, now, now, 1, 10, "token");
        userQuizEntity2.setQuizId(2L);
        assertFalse(userQuizEntity1.equals(userQuizEntity2));

        assertFalse(userQuizEntity1.equals(null));
        assertFalse(userQuizEntity1.equals(""));

    }

    @Test
    public void testHashCode() throws Exception {
        LocalDateTime now = LocalDateTime.now();

        UserQuizEntity userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, 1, 10);
        UserQuizEntity userQuizEntity2 = new UserQuizEntity(1L, 1L, 1L, now, 1, 10);
        assertThat(userQuizEntity1.hashCode(), equalTo(userQuizEntity2.hashCode()));
        assertThat(userQuizEntity1.hashCode(), equalTo(userQuizEntity1.hashCode()));


        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token");
        userQuizEntity2 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token");
        assertThat(userQuizEntity1.hashCode(), equalTo(userQuizEntity2.hashCode()));

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token");
        userQuizEntity1.setQuizId(1L);
        userQuizEntity2 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token");
        userQuizEntity2.setQuizId(1L);
        assertThat(userQuizEntity1.hashCode(), equalTo(userQuizEntity2.hashCode()));

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, 1, 10);
        userQuizEntity2 = new UserQuizEntity(2L, 2L, 2L, now, 1, 10);
        assertNotEquals(userQuizEntity1.hashCode(), userQuizEntity2.hashCode());

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token");
        userQuizEntity2 = new UserQuizEntity(2L, 2L, 2L, now, now, 1, 10, "token");
        assertNotEquals(userQuizEntity1.hashCode(), userQuizEntity2.hashCode());

        userQuizEntity1 = new UserQuizEntity(1L, 1L, 1L, now, now, 1, 10, "token");
        userQuizEntity1.setQuizId(1L);
        userQuizEntity2 = new UserQuizEntity(2L, 2L, 2L, now, now, 1, 10, "token");
        userQuizEntity2.setQuizId(2L);
        assertNotEquals(userQuizEntity1.hashCode(), userQuizEntity2.hashCode());
    }
}