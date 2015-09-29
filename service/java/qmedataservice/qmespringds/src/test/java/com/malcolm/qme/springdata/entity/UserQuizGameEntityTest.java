/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizGameEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Game Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserQuizGameEntityTest {

    @Test
    public void testEquals() throws Exception {
        UserQuizGameEntityId userQuizGameEntityId1 = new UserQuizGameEntityId(1L, 1L, 1L);
        UserQuizGameEntityId userQuizGameEntityId2 = new UserQuizGameEntityId(1L, 1L, 1L);
        assertTrue(userQuizGameEntityId1.equals(userQuizGameEntityId2));
        assertTrue(userQuizGameEntityId1.equals(userQuizGameEntityId1));

        LocalDateTime now = LocalDateTime.now();
        UserQuizGameEntity userQuizGameEntity1 = new UserQuizGameEntity(userQuizGameEntityId1,now, now , 10);
        UserQuizGameEntity userQuizGameEntity2 = new UserQuizGameEntity(userQuizGameEntityId2,now, now , 10);
        assertTrue(userQuizGameEntity1.equals(userQuizGameEntity2));
        assertTrue(userQuizGameEntity1.equals(userQuizGameEntity1));

        userQuizGameEntityId1 = new UserQuizGameEntityId(1L, 1L, 1L);
        userQuizGameEntityId2 = new UserQuizGameEntityId(2L, 2L, 2L);
        assertFalse(userQuizGameEntityId1.equals(userQuizGameEntityId2));
        userQuizGameEntity1 = new UserQuizGameEntity(userQuizGameEntityId1,now, now , 10);
        userQuizGameEntity2 = new UserQuizGameEntity(userQuizGameEntityId2,now, now , 10);
        assertFalse(userQuizGameEntity1.equals(userQuizGameEntity2));
    }

    @Test
    public void testHashCode() throws Exception {
        UserQuizGameEntityId userQuizGameEntityId1 = new UserQuizGameEntityId(1L, 1L, 1L);
        UserQuizGameEntityId userQuizGameEntityId2 = new UserQuizGameEntityId(1L, 1L, 1L);
        assertThat(userQuizGameEntityId1.hashCode(), equalTo(userQuizGameEntityId2.hashCode()));
        assertThat(userQuizGameEntityId1.hashCode(), equalTo(userQuizGameEntityId1.hashCode()));

        LocalDateTime now = LocalDateTime.now();
        UserQuizGameEntity userQuizGameEntity1 = new UserQuizGameEntity(userQuizGameEntityId1,now, now , 10);
        UserQuizGameEntity userQuizGameEntity2 = new UserQuizGameEntity(userQuizGameEntityId2,now, now , 10);
        assertThat(userQuizGameEntity1.hashCode(), equalTo(userQuizGameEntity2.hashCode()));
        assertThat(userQuizGameEntity1.hashCode(), equalTo(userQuizGameEntity1.hashCode()));

        userQuizGameEntityId1 = new UserQuizGameEntityId(1L, 1L, 1L);
        userQuizGameEntityId2 = new UserQuizGameEntityId(2L, 2L, 2L);
        assertNotEquals(userQuizGameEntityId1.hashCode(), userQuizGameEntityId2.hashCode());
        userQuizGameEntity1 = new UserQuizGameEntity(userQuizGameEntityId1,now, now , 10);
        userQuizGameEntity2 = new UserQuizGameEntity(userQuizGameEntityId2,now, now , 10);
        assertNotEquals(userQuizGameEntity1.hashCode(), userQuizGameEntity2.hashCode());
    }

}