/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizGameEntityId.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Entity Id Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserQuizGameEntityIdTest {

    @Test
    public void testEquals() throws Exception {
        UserQuizGameEntityId userQuizGameEntityId1 = new UserQuizGameEntityId(1L, 1L, 1L);
        UserQuizGameEntityId userQuizGameEntityId2 = new UserQuizGameEntityId(1L, 1L, 1L);
        assertTrue(userQuizGameEntityId1.equals(userQuizGameEntityId2));
        assertTrue(userQuizGameEntityId1.equals(userQuizGameEntityId1));

        userQuizGameEntityId1 = new UserQuizGameEntityId(1L, 1L, 1L);
        userQuizGameEntityId2 = new UserQuizGameEntityId(2L, 2L, 2L);
        assertFalse(userQuizGameEntityId1.equals(userQuizGameEntityId2));
    }

    @Test
    public void testHashCode() throws Exception {
        UserQuizGameEntityId userQuizGameEntityId1 = new UserQuizGameEntityId(1L, 1L, 1L);
        UserQuizGameEntityId userQuizGameEntityId2 = new UserQuizGameEntityId(1L, 1L, 1L);
        assertThat(userQuizGameEntityId1.hashCode(), equalTo(userQuizGameEntityId2.hashCode()));
        assertThat(userQuizGameEntityId1.hashCode(), equalTo(userQuizGameEntityId1.hashCode()));

        userQuizGameEntityId1 = new UserQuizGameEntityId(1L, 1L, 1L);
        userQuizGameEntityId2 = new UserQuizGameEntityId(2L, 2L, 2L);
        assertNotEquals(userQuizGameEntityId1.hashCode(), userQuizGameEntityId2.hashCode());
    }
}