/**
 * Name      : com.malcolm.qme.springdata.entity.UserCategoryLikesEntityId.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Category Likes Entity Id Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserCategoryLikesEntityIdTest {

    @Test
    public void testEquals() throws Exception {
        UserCategoryLikesEntityId userCategoryLikesEntityId1 =  new UserCategoryLikesEntityId(1L, 1L);
        UserCategoryLikesEntityId userCategoryLikesEntityId2 =  new UserCategoryLikesEntityId(1L, 1L);
        assertTrue(userCategoryLikesEntityId1.equals(userCategoryLikesEntityId2));
        assertTrue(userCategoryLikesEntityId1.equals(userCategoryLikesEntityId1));

        userCategoryLikesEntityId1 =  new UserCategoryLikesEntityId(1L, 1L);
        userCategoryLikesEntityId2 =  new UserCategoryLikesEntityId(2L, 2L);
        assertFalse(userCategoryLikesEntityId1.equals(userCategoryLikesEntityId2));
    }

    @Test
    public void testHashCode() throws Exception {
        UserCategoryLikesEntityId userCategoryLikesEntityId1 =  new UserCategoryLikesEntityId(1L, 1L);
        UserCategoryLikesEntityId userCategoryLikesEntityId2 =  new UserCategoryLikesEntityId(1L, 1L);
        assertThat(userCategoryLikesEntityId1.hashCode(), equalTo(userCategoryLikesEntityId2.hashCode()));
        assertThat(userCategoryLikesEntityId1.hashCode(), equalTo(userCategoryLikesEntityId1.hashCode()));

        userCategoryLikesEntityId1 =  new UserCategoryLikesEntityId(1L, 1L);
        userCategoryLikesEntityId2 =  new UserCategoryLikesEntityId(2L, 2L);
        assertNotEquals(userCategoryLikesEntityId1.hashCode(), userCategoryLikesEntityId2.hashCode());

    }
}