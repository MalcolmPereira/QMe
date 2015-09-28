/**
 * Name      : com.malcolm.qme.springdata.entity.UserCategoryLikesEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Category Likes Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserCategoryLikesEntityTest {

    @Test
    public void testEquals() throws Exception {
        UserCategoryLikesEntityId userCategoryLikesEntityId1 =  new UserCategoryLikesEntityId(1L, 1L);
        UserCategoryLikesEntityId userCategoryLikesEntityId2 =  new UserCategoryLikesEntityId(1L, 1L);
        assertTrue(userCategoryLikesEntityId1.equals(userCategoryLikesEntityId2));
        assertTrue(userCategoryLikesEntityId1.equals(userCategoryLikesEntityId1));
        UserCategoryLikesEntity userCategoryLikesEntity1 = new UserCategoryLikesEntity(userCategoryLikesEntityId1);
        UserCategoryLikesEntity userCategoryLikesEntity2 = new UserCategoryLikesEntity(userCategoryLikesEntityId2);
        assertTrue(userCategoryLikesEntity1.equals(userCategoryLikesEntity2));
        assertTrue(userCategoryLikesEntity1.equals(userCategoryLikesEntity1));


        userCategoryLikesEntityId1 =  new UserCategoryLikesEntityId(1L, 1L);
        userCategoryLikesEntityId2 =  new UserCategoryLikesEntityId(2L, 2L);
        assertFalse(userCategoryLikesEntityId1.equals(userCategoryLikesEntityId2));
        userCategoryLikesEntity1 = new UserCategoryLikesEntity(userCategoryLikesEntityId1);
        userCategoryLikesEntity2 = new UserCategoryLikesEntity(userCategoryLikesEntityId2);
        assertFalse(userCategoryLikesEntity1.equals(userCategoryLikesEntity2));

        assertFalse(userCategoryLikesEntity1.equals(null));
        assertFalse(userCategoryLikesEntity1.equals(""));

    }

    @Test
    public void testHashCode() throws Exception {
        UserCategoryLikesEntityId userCategoryLikesEntityId1 =  new UserCategoryLikesEntityId(1L, 1L);
        UserCategoryLikesEntityId userCategoryLikesEntityId2 =  new UserCategoryLikesEntityId(1L, 1L);
        assertTrue(userCategoryLikesEntityId1.equals(userCategoryLikesEntityId2));
        assertTrue(userCategoryLikesEntityId1.equals(userCategoryLikesEntityId1));
        UserCategoryLikesEntity userCategoryLikesEntity1 = new UserCategoryLikesEntity(userCategoryLikesEntityId1);
        UserCategoryLikesEntity userCategoryLikesEntity2 = new UserCategoryLikesEntity(userCategoryLikesEntityId2);
        assertThat(userCategoryLikesEntity1.hashCode(), equalTo(userCategoryLikesEntity2.hashCode()));
        assertThat(userCategoryLikesEntity1.hashCode(), equalTo(userCategoryLikesEntity1.hashCode()));


        userCategoryLikesEntityId1 =  new UserCategoryLikesEntityId(1L, 1L);
        userCategoryLikesEntityId2 =  new UserCategoryLikesEntityId(2L, 2L);
        assertFalse(userCategoryLikesEntityId1.equals(userCategoryLikesEntityId2));
        userCategoryLikesEntity1 = new UserCategoryLikesEntity(userCategoryLikesEntityId1);
        userCategoryLikesEntity2 = new UserCategoryLikesEntity(userCategoryLikesEntityId2);
        assertNotEquals(userCategoryLikesEntity1.hashCode(), userCategoryLikesEntity2.hashCode());

    }

}