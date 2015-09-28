/**
 * Name      : com.malcolm.qme.core.domain.UserQuizLikesTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Likes Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserQuizLikesTest {

    @Test
    public void testGetUserID() throws Exception {
        UserQuizLikes userQuizLikes = new UserQuizLikes(1L, 1L);
        assertThat(userQuizLikes.getUserID(), equalTo(1L));
    }

    @Test
    public void testGetQuizID() throws Exception {
        UserQuizLikes userQuizLikes = new UserQuizLikes(1L, 1L);
        assertThat(userQuizLikes.getQuizID(), equalTo(1L));
    }

    @Test
    public void testEquals(){
        UserQuizLikes userQuizLikes1 = new UserQuizLikes(1L, 1L);
        UserQuizLikes userQuizLikes2 = new UserQuizLikes(1L, 1L);
        assertTrue(userQuizLikes1.equals(userQuizLikes2));
        assertTrue(userQuizLikes1.equals(userQuizLikes1));
        userQuizLikes1 = new UserQuizLikes(1L, 1L);
        userQuizLikes2 = new UserQuizLikes(1L, 2L);
        assertFalse(userQuizLikes1.equals(userQuizLikes2));
        assertFalse(userQuizLikes1.equals(null));
        assertFalse(userQuizLikes1.equals(""));
    }

    @Test
    public void testHashCode(){
        UserQuizLikes userQuizLikes1 = new UserQuizLikes(1L, 1L);
        UserQuizLikes userQuizLikes2 = new UserQuizLikes(1L, 1L);
        assertThat(userQuizLikes1.hashCode(), equalTo(userQuizLikes2.hashCode()));
        userQuizLikes1 = new UserQuizLikes(1L, 1L);
        userQuizLikes2 = new UserQuizLikes(1L, 2L);
        assertNotEquals(userQuizLikes1.hashCode(),userQuizLikes2.hashCode());
    }
}