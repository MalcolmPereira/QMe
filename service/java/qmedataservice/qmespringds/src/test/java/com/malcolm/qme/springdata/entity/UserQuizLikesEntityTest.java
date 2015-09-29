/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizLikesEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Likes Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserQuizLikesEntityTest {

    @Test
    public void testEquals() throws Exception {
        UserQuizLikesEntityId userQuizLikesEntityId1 = new UserQuizLikesEntityId(1L, 1L);
        UserQuizLikesEntityId userQuizLikesEntityId2 = new UserQuizLikesEntityId(1L, 1L);
        assertTrue(userQuizLikesEntityId1.equals(userQuizLikesEntityId2));
        assertTrue(userQuizLikesEntityId1.equals(userQuizLikesEntityId1));

        UserQuizLikesEntity userQuizLikesEntity1 = new UserQuizLikesEntity(userQuizLikesEntityId1);
        UserQuizLikesEntity userQuizLikesEntity2 = new UserQuizLikesEntity(userQuizLikesEntityId2);
        assertTrue(userQuizLikesEntity1.equals(userQuizLikesEntity2));
        assertTrue(userQuizLikesEntity1.equals(userQuizLikesEntity1));


        userQuizLikesEntityId1 = new UserQuizLikesEntityId(1L, 1L);
        userQuizLikesEntityId2 = new UserQuizLikesEntityId(2L, 2L);
        assertFalse(userQuizLikesEntityId1.equals(userQuizLikesEntityId2));
        userQuizLikesEntity1 = new UserQuizLikesEntity(userQuizLikesEntityId1);
        userQuizLikesEntity2 = new UserQuizLikesEntity(userQuizLikesEntityId2);
        assertFalse(userQuizLikesEntity1.equals(userQuizLikesEntity2));
    }

    @Test
    public void testHashCode() throws Exception {
        UserQuizLikesEntityId userQuizLikesEntityId1 = new UserQuizLikesEntityId(1L, 1L);
        UserQuizLikesEntityId userQuizLikesEntityId2 = new UserQuizLikesEntityId(1L, 1L);
        assertThat(userQuizLikesEntityId1.hashCode(), equalTo(userQuizLikesEntityId2.hashCode()));
        assertThat(userQuizLikesEntityId1.hashCode(), equalTo(userQuizLikesEntityId1.hashCode()));

        UserQuizLikesEntity userQuizLikesEntity1 = new UserQuizLikesEntity(userQuizLikesEntityId1);
        UserQuizLikesEntity userQuizLikesEntity2 = new UserQuizLikesEntity(userQuizLikesEntityId2);
        assertTrue(userQuizLikesEntity1.equals(userQuizLikesEntity2));
        assertTrue(userQuizLikesEntity1.equals(userQuizLikesEntity1));

        userQuizLikesEntityId1 = new UserQuizLikesEntityId(1L, 1L);
        userQuizLikesEntityId2 = new UserQuizLikesEntityId(2L, 2L);
        assertNotEquals(userQuizLikesEntityId1.hashCode(), userQuizLikesEntityId2.hashCode());
        userQuizLikesEntity1 = new UserQuizLikesEntity(userQuizLikesEntityId1);
        userQuizLikesEntity2 = new UserQuizLikesEntity(userQuizLikesEntityId2);
        assertNotEquals(userQuizLikesEntity1.hashCode(), userQuizLikesEntity2.hashCode());
    }
}