/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizLikesEntityId.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Likes Entity Id Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserQuizLikesEntityIdTest {

    @Test
    public void testEquals() throws Exception {
        UserQuizLikesEntityId userQuizLikesEntityId1 = new UserQuizLikesEntityId(1L, 1L);
        UserQuizLikesEntityId userQuizLikesEntityId2 = new UserQuizLikesEntityId(1L, 1L);
        assertTrue(userQuizLikesEntityId1.equals(userQuizLikesEntityId2));
        assertTrue(userQuizLikesEntityId1.equals(userQuizLikesEntityId1));

        userQuizLikesEntityId1 = new UserQuizLikesEntityId(1L, 1L);
        userQuizLikesEntityId2 = new UserQuizLikesEntityId(2L, 2L);
        assertFalse(userQuizLikesEntityId1.equals(userQuizLikesEntityId2));
    }

    @Test
    public void testHashCode() throws Exception {
        UserQuizLikesEntityId userQuizLikesEntityId1 = new UserQuizLikesEntityId(1L, 1L);
        UserQuizLikesEntityId userQuizLikesEntityId2 = new UserQuizLikesEntityId(1L, 1L);
        assertThat(userQuizLikesEntityId1.hashCode(), equalTo(userQuizLikesEntityId2.hashCode()));
        assertThat(userQuizLikesEntityId1.hashCode(), equalTo(userQuizLikesEntityId1.hashCode()));

        userQuizLikesEntityId1 = new UserQuizLikesEntityId(1L, 1L);
        userQuizLikesEntityId2 = new UserQuizLikesEntityId(2L, 2L);
        assertNotEquals(userQuizLikesEntityId1.hashCode(), userQuizLikesEntityId2.hashCode());
    }
}