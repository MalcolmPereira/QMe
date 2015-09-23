/**
 * Name      : com.malcolm.qme.core.domain.UserCategoryTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe User Category Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserCategoryTest {

    @Test
    public void testGetUserCategoryID() throws Exception {
        UserCategory userCategory = new UserCategory(1L, 1L, 1L);
        assertThat(userCategory.getUserCategoryID(), equalTo(1L));
        userCategory = new UserCategory(1L, 1L);
        assertThat(userCategory.getUserCategoryID(), equalTo(0L));
    }

    @Test
    public void testGetUserID() throws Exception {
        UserCategory userCategory = new UserCategory(1L, 1L, 1L);
        assertThat(userCategory.getUserID(), equalTo(1L));
        userCategory = new UserCategory(1L, 1L);
        assertThat(userCategory.getUserID(), equalTo(1L));
    }

    @Test
    public void testGetCategoryID() throws Exception {
        UserCategory userCategory = new UserCategory(1L, 1L, 1L);
        assertThat(userCategory.getCategoryID(), equalTo(1L));
        userCategory = new UserCategory(1L, 1L);
        assertThat(userCategory.getCategoryID(), equalTo(1L));
    }

    @Test
    public void testEquals(){
        UserCategory userCategory1 = new UserCategory(1L, 1L, 1L);
        UserCategory userCategory2 = new UserCategory(1L, 1L, 1L);
        assertTrue(userCategory1.equals(userCategory1));
        userCategory1 = new UserCategory(1L, 1L, 1L);
        userCategory2 = new UserCategory(1L, 2L, 1L);
        assertFalse(userCategory1.equals(userCategory2));
        userCategory1 = new UserCategory(1L, 1L);
        userCategory2 = new UserCategory(1L, 1L);
        assertTrue(userCategory1.equals(userCategory2));
        userCategory1 = new UserCategory(1L, 1L);
        userCategory2 = new UserCategory(1L, 2L);
        assertFalse(userCategory1.equals(userCategory2));
    }

    @Test
    public void testHashCode(){
        UserCategory userCategory1 = new UserCategory(1L, 1L, 1L);
        UserCategory userCategory2 = new UserCategory(1L, 1L, 1L);
        assertThat(userCategory1.hashCode(),equalTo(userCategory2.hashCode()));
        userCategory1 = new UserCategory(1L, 1L, 1L);
        userCategory2 = new UserCategory(1L, 2L, 1L);
        assertNotEquals(userCategory1.hashCode(),userCategory2.hashCode());
        userCategory1 = new UserCategory(1L, 1L);
        userCategory2 = new UserCategory(1L, 1L);
        assertThat(userCategory1.hashCode(),equalTo(userCategory2.hashCode()));
        userCategory1 = new UserCategory(1L, 1L);
        userCategory2 = new UserCategory(1L, 2L);
        assertNotEquals(userCategory1.hashCode(),userCategory2.hashCode());
    }
}