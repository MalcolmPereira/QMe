/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuestionLikesEntityId.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Question Likes Entity Id Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserQuestionLikesEntityIdTest {

    @Test
    public void testEquals() throws Exception {
        UserQuestionLikesEntityId userQuestionLikesEntityId1 = new UserQuestionLikesEntityId(1L, 1L);
        UserQuestionLikesEntityId userQuestionLikesEntityId2 = new UserQuestionLikesEntityId(1L, 1L);
        assertTrue(userQuestionLikesEntityId1.equals(userQuestionLikesEntityId2));
        assertTrue(userQuestionLikesEntityId1.equals(userQuestionLikesEntityId1));

        userQuestionLikesEntityId1 = new UserQuestionLikesEntityId(1L, 1L);
        userQuestionLikesEntityId2 = new UserQuestionLikesEntityId(2L, 2L);
        assertFalse(userQuestionLikesEntityId1.equals(userQuestionLikesEntityId2));
    }

    @Test
    public void testHashCode() throws Exception {
        UserQuestionLikesEntityId userQuestionLikesEntityId1 = new UserQuestionLikesEntityId(1L, 1L);
        UserQuestionLikesEntityId userQuestionLikesEntityId2 = new UserQuestionLikesEntityId(1L, 1L);
        assertThat(userQuestionLikesEntityId1.hashCode(), equalTo(userQuestionLikesEntityId2.hashCode()));
        assertThat(userQuestionLikesEntityId1.hashCode(), equalTo(userQuestionLikesEntityId1.hashCode()));

        userQuestionLikesEntityId1 = new UserQuestionLikesEntityId(1L, 1L);
        userQuestionLikesEntityId2 = new UserQuestionLikesEntityId(2L, 2L);
        assertNotEquals(userQuestionLikesEntityId1.hashCode(), userQuestionLikesEntityId2.hashCode());
    }
}