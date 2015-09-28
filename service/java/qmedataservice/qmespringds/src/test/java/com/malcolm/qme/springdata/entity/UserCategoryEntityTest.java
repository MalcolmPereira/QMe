/**
 * Name      : com.malcolm.qme.springdata.entity.UserCategoryEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Category Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserCategoryEntityTest {

    @Test
    public void testEquals() throws Exception {
        UserCategoryEntity userCategoryEntity1 = new  UserCategoryEntity(1L, 1L);
        UserCategoryEntity userCategoryEntity2 = new  UserCategoryEntity(1L, 1L);
        assertTrue(userCategoryEntity1.equals(userCategoryEntity2));
        assertTrue(userCategoryEntity1.equals(userCategoryEntity1));

        userCategoryEntity1 = new  UserCategoryEntity(1L, 1L);
        userCategoryEntity1.setUserCatId(1L);
        userCategoryEntity2 = new  UserCategoryEntity(1L, 1L);
        userCategoryEntity2.setUserCatId(1L);
        assertTrue(userCategoryEntity1.equals(userCategoryEntity2));

        userCategoryEntity1 = new  UserCategoryEntity(1L, 1L);
        userCategoryEntity2 = new  UserCategoryEntity(2L, 2L);
        assertFalse(userCategoryEntity1.equals(userCategoryEntity2));

        userCategoryEntity1 = new  UserCategoryEntity(1L, 1L);
        userCategoryEntity1.setUserCatId(1L);
        userCategoryEntity2 = new  UserCategoryEntity(2L, 2L);
        userCategoryEntity2.setUserCatId(2L);
        assertFalse(userCategoryEntity1.equals(userCategoryEntity2));

        assertFalse(userCategoryEntity1.equals(null));
        assertFalse(userCategoryEntity1.equals(""));
    }

    @Test
    public void testHashCode() throws Exception {
        UserCategoryEntity userCategoryEntity1 = new  UserCategoryEntity(1L, 1L);
        UserCategoryEntity userCategoryEntity2 = new  UserCategoryEntity(1L, 1L);
        assertThat(userCategoryEntity1.hashCode(), equalTo(userCategoryEntity2.hashCode()));
        assertThat(userCategoryEntity1.hashCode(), equalTo(userCategoryEntity1.hashCode()));

        userCategoryEntity1 = new  UserCategoryEntity(1L, 1L);
        userCategoryEntity1.setUserCatId(1L);
        userCategoryEntity2 = new  UserCategoryEntity(1L, 1L);
        userCategoryEntity2.setUserCatId(1L);
        assertThat(userCategoryEntity1.hashCode(), equalTo(userCategoryEntity2.hashCode()));

        userCategoryEntity1 = new  UserCategoryEntity(1L, 1L);
        userCategoryEntity2 = new  UserCategoryEntity(2L, 2L);
        assertNotEquals(userCategoryEntity1.hashCode(), userCategoryEntity2.hashCode());

        userCategoryEntity1 = new  UserCategoryEntity(1L, 1L);
        userCategoryEntity1.setUserCatId(1L);
        userCategoryEntity2 = new  UserCategoryEntity(2L, 2L);
        userCategoryEntity2.setUserCatId(2L);
        assertNotEquals(userCategoryEntity1.hashCode(), userCategoryEntity2.hashCode());

    }

}