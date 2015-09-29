/**
 * Name      : com.malcolm.qme.springdata.entity.UserStagingEntity.java
 * Date      : 9/18/2015
 * Developer : Malcolm
 * Purpose   : User Staging Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserStagingEntityTest {

    @Test
    public void testEquals() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        UserStagingEntity userStagingEntity1 = new  UserStagingEntity("test", "test", "test", "test", "test", now, "test");
        UserStagingEntity userStagingEntity2 = new  UserStagingEntity("test", "test", "test", "test", "test", now, "test");
        assertTrue(userStagingEntity1.equals(userStagingEntity2));
        assertTrue(userStagingEntity1.equals(userStagingEntity1));

        userStagingEntity1 = new  UserStagingEntity("test", "test", "test", "test", "test", now, "test");
        userStagingEntity1.setUserId(1L);
        userStagingEntity2 = new  UserStagingEntity("test", "test", "test", "test", "test", now, "test");
        userStagingEntity2.setUserId(1L);
        assertTrue(userStagingEntity1.equals(userStagingEntity2));

        userStagingEntity1 = new  UserStagingEntity("test1", "test1", "test1", "test1", "test1", now, "test1");
        userStagingEntity2 = new  UserStagingEntity("test2", "test2", "test2", "test2", "test2", now, "test2");
        assertFalse(userStagingEntity1.equals(userStagingEntity2));

        userStagingEntity1 = new  UserStagingEntity("test1", "test1", "test1", "test1", "test1", now, "test1");
        userStagingEntity1.setUserId(1L);
        userStagingEntity2 = new  UserStagingEntity("test2", "test2", "test2", "test2", "test2", now, "test2");
        userStagingEntity2.setUserId(2L);
        assertFalse(userStagingEntity1.equals(userStagingEntity2));
        assertFalse(userStagingEntity1.equals(null));
        assertFalse(userStagingEntity1.equals(""));
    }

    @Test
    public void testHashCode() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        UserStagingEntity userStagingEntity1 = new  UserStagingEntity("test", "test", "test", "test", "test", now, "test");
        UserStagingEntity userStagingEntity2 = new  UserStagingEntity("test", "test", "test", "test", "test", now, "test");
        assertThat(userStagingEntity1.hashCode(), equalTo(userStagingEntity2.hashCode()));
        assertThat(userStagingEntity1.hashCode(), equalTo(userStagingEntity1.hashCode()));


        userStagingEntity1 = new  UserStagingEntity("test", "test", "test", "test", "test", now, "test");
        userStagingEntity1.setUserId(1L);
        userStagingEntity2 = new  UserStagingEntity("test", "test", "test", "test", "test", now, "test");
        userStagingEntity2.setUserId(1L);
        assertThat(userStagingEntity1.hashCode(), equalTo(userStagingEntity2.hashCode()));

        userStagingEntity1 = new  UserStagingEntity("test1", "test1", "test1", "test1", "test1", now, "test1");
        userStagingEntity2 = new  UserStagingEntity("test2", "test2", "test2", "test2", "test2", now, "test2");
        assertNotEquals(userStagingEntity1.hashCode(), userStagingEntity2.hashCode());

        userStagingEntity1 = new  UserStagingEntity("test1", "test1", "test1", "test1", "test1", now, "test1");
        userStagingEntity1.setUserId(1L);
        userStagingEntity2 = new  UserStagingEntity("test2", "test2", "test2", "test2", "test2", now, "test2");
        userStagingEntity2.setUserId(2L);
        assertNotEquals(userStagingEntity1.hashCode(), userStagingEntity2.hashCode());
    }
}