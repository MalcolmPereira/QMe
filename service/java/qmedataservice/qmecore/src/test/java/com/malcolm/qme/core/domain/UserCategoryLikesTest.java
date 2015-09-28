/**
 * Name      : com.malcolm.qme.core.domain.UserCategoryLikesTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe User Category Likes Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserCategoryLikesTest {

    @Test
    public void testGetUserID() throws Exception {
        UserCategoryLikes userCategoryLikes = new UserCategoryLikes(1L, 1L);
        assertThat(userCategoryLikes.getUserID(), equalTo(1L));
    }

    @Test
    public void testGetCategoryID() throws Exception {
        UserCategoryLikes userCategoryLikes = new UserCategoryLikes(1L, 1L);
        assertThat(userCategoryLikes.getCategoryID(), equalTo(1L));
    }

    @Test
    public void testEquals(){
        UserCategoryLikes userCategoryLikes1 = new UserCategoryLikes(1L, 1L);
        UserCategoryLikes userCategoryLikes2 = new UserCategoryLikes(1L, 1L);
        assertTrue(userCategoryLikes1.equals(userCategoryLikes2));
        assertTrue(userCategoryLikes1.equals(userCategoryLikes1));
        userCategoryLikes1 = new UserCategoryLikes(1L, 1L);
        userCategoryLikes2 = new UserCategoryLikes(1L, 2L);
        assertFalse(userCategoryLikes1.equals(userCategoryLikes2));
        assertFalse(userCategoryLikes1.equals(null));
        assertFalse(userCategoryLikes1.equals(""));
    }

    @Test
    public void testHashCode(){
        UserCategoryLikes userCategoryLikes1 = new UserCategoryLikes(1L, 1L);
        UserCategoryLikes userCategoryLikes2 = new UserCategoryLikes(1L, 1L);
        assertThat(userCategoryLikes1.hashCode(),equalTo(userCategoryLikes2.hashCode()));
        userCategoryLikes1 = new UserCategoryLikes(1L, 1L);
        userCategoryLikes2 = new UserCategoryLikes(1L, 2L);
        assertNotEquals(userCategoryLikes1.hashCode(),userCategoryLikes2.hashCode());
    }
}