/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuestionLikesEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Question Likes Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserQuestionLikesEntityTest {

    @Test
    public void testEquals() throws Exception {
        UserQuestionLikesEntityId userQuestionLikesEntityId1 = new UserQuestionLikesEntityId(1L, 1L);
        UserQuestionLikesEntityId userQuestionLikesEntityId2 = new UserQuestionLikesEntityId(1L, 1L);
        assertTrue(userQuestionLikesEntityId1.equals(userQuestionLikesEntityId2));
        assertTrue(userQuestionLikesEntityId1.equals(userQuestionLikesEntityId1));
        UserQuestionLikesEntity userQuestionLikesEntity1 = new UserQuestionLikesEntity(userQuestionLikesEntityId1);
        UserQuestionLikesEntity userQuestionLikesEntity2 = new UserQuestionLikesEntity(userQuestionLikesEntityId2);
        assertTrue(userQuestionLikesEntity1.equals(userQuestionLikesEntity2));
        assertTrue(userQuestionLikesEntity1.equals(userQuestionLikesEntity1));

        userQuestionLikesEntityId1 = new UserQuestionLikesEntityId(1L, 1L);
        userQuestionLikesEntityId2 = new UserQuestionLikesEntityId(2L, 2L);
        assertFalse(userQuestionLikesEntityId1.equals(userQuestionLikesEntityId2));
        userQuestionLikesEntity1 = new UserQuestionLikesEntity(userQuestionLikesEntityId1);
        userQuestionLikesEntity2 = new UserQuestionLikesEntity(userQuestionLikesEntityId2);
        assertFalse(userQuestionLikesEntity1.equals(userQuestionLikesEntity2));

    }

    @Test
    public void testHashCode() throws Exception {
        UserQuestionLikesEntityId userQuestionLikesEntityId1 = new UserQuestionLikesEntityId(1L, 1L);
        UserQuestionLikesEntityId userQuestionLikesEntityId2 = new UserQuestionLikesEntityId(1L, 1L);
        assertTrue(userQuestionLikesEntityId1.equals(userQuestionLikesEntityId2));
        assertTrue(userQuestionLikesEntityId1.equals(userQuestionLikesEntityId1));
        UserQuestionLikesEntity userQuestionLikesEntity1 = new UserQuestionLikesEntity(userQuestionLikesEntityId1);
        UserQuestionLikesEntity userQuestionLikesEntity2 = new UserQuestionLikesEntity(userQuestionLikesEntityId2);
        assertThat(userQuestionLikesEntity1.hashCode(), equalTo(userQuestionLikesEntity2.hashCode()));
        assertThat(userQuestionLikesEntity1.hashCode(), equalTo(userQuestionLikesEntity1.hashCode()));

        userQuestionLikesEntityId1 = new UserQuestionLikesEntityId(1L, 1L);
        userQuestionLikesEntityId2 = new UserQuestionLikesEntityId(2L, 2L);
        assertFalse(userQuestionLikesEntityId1.equals(userQuestionLikesEntityId2));
        userQuestionLikesEntity1 = new UserQuestionLikesEntity(userQuestionLikesEntityId1);
        userQuestionLikesEntity2 = new UserQuestionLikesEntity(userQuestionLikesEntityId2);
        assertNotEquals(userQuestionLikesEntity1.hashCode(), userQuestionLikesEntity2.hashCode());

    }
}