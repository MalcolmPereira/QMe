/**
 * Name      : com.malcolm.qme.core.domain.UserQuestionLikesTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe User Questions Like Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserQuestionLikesTest {

    @Test
    public void testGetUserID() throws Exception {
        UserQuestionLikes userQuestionLikes = new UserQuestionLikes(1L, 1L);
        assertThat(userQuestionLikes.getUserID(), equalTo(1L));
    }

    @Test
    public void testGetQuestionID() throws Exception {
        UserQuestionLikes userQuestionLikes = new UserQuestionLikes(1L, 1L);
        assertThat(userQuestionLikes.getQuestionID(), equalTo(1L));
    }

    @Test
    public void testEquals(){
        UserQuestionLikes userQuestionLikes1 = new UserQuestionLikes(1L, 1L);
        UserQuestionLikes userQuestionLikes2 = new UserQuestionLikes(1L, 1L);
        assertTrue(userQuestionLikes1.equals(userQuestionLikes2));
        assertTrue(userQuestionLikes1.equals(userQuestionLikes1));
        userQuestionLikes1 = new UserQuestionLikes(1L, 1L);
        userQuestionLikes2 = new UserQuestionLikes(1L, 2L);
        assertFalse(userQuestionLikes1.equals(userQuestionLikes2));
        assertFalse(userQuestionLikes1.equals(null));
        assertFalse(userQuestionLikes1.equals(""));
    }

    @Test
    public void testHashCode(){
        UserQuestionLikes userQuestionLikes1 = new UserQuestionLikes(1L, 1L);
        UserQuestionLikes userQuestionLikes2 = new UserQuestionLikes(1L, 1L);
        assertThat(userQuestionLikes1.hashCode(), equalTo(userQuestionLikes2.hashCode()));
        userQuestionLikes1 = new UserQuestionLikes(1L, 1L);
        userQuestionLikes2 = new UserQuestionLikes(1L, 2L);
        assertNotEquals(userQuestionLikes1.hashCode(),userQuestionLikes2.hashCode());
    }
}