/**
 * Name      : com.malcolm.qme.springdata.entity.UserEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserEntityTest {

    @Test
    public void testEquals() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        UserEntity userEntity1 = new UserEntity("test", "test","test","test", "test",now, now,now);
        UserEntity userEntity2 = new UserEntity("test", "test","test","test", "test",now, now,now);
        assertTrue(userEntity1.equals(userEntity2));
        assertTrue(userEntity1.equals(userEntity1));

        userEntity1 = new UserEntity("test", "test", "test", "test", "test", now, now, now, now,1L);
        userEntity2 = new UserEntity("test", "test", "test", "test", "test", now, now, now, now,1L);
        assertTrue(userEntity1.equals(userEntity2));


        userEntity1 = new UserEntity("test", "test", "test", "test", "test", now, now, now, now,1L);
        userEntity1.setUserId(1L);
        userEntity2 = new UserEntity("test", "test", "test", "test", "test", now, now, now, now,1L);
        userEntity2.setUserId(1L);
        assertTrue(userEntity1.equals(userEntity2));

        userEntity1 = new UserEntity("test1", "test1","test1","test1", "test1",now, now,now);
        userEntity2 = new UserEntity("test2", "test2","test2","test2", "test2",now, now,now);
        assertFalse(userEntity1.equals(userEntity2));

        userEntity1 = new UserEntity("test1", "test1", "test1", "test1", "test1", now, now, now, now,1L);
        userEntity2 = new UserEntity("test2", "test2", "test2", "test2", "test2", now, now, now, now,1L);
        assertFalse(userEntity1.equals(userEntity2));

        userEntity1 = new UserEntity("test1", "test1", "test1", "test1", "test1", now, now, now, now,1L);
        userEntity1.setUserId(1L);
        userEntity2 = new UserEntity("test2", "test2", "test2", "test2", "test2", now, now, now, now,1L);
        userEntity2.setUserId(2L);
        assertFalse(userEntity1.equals(userEntity2));

        assertFalse(userEntity1.equals(null));
        assertFalse(userEntity1.equals(""));


   }

    @Test
    public void testHashCode() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        UserEntity userEntity1 = new UserEntity("test", "test","test","test", "test",now, now,now);
        UserEntity userEntity2 = new UserEntity("test", "test","test","test", "test",now, now,now);
        assertThat(userEntity1.hashCode(), equalTo(userEntity2.hashCode()));
        assertThat(userEntity1.hashCode(), equalTo(userEntity1.hashCode()));

        userEntity1 = new UserEntity("test", "test", "test", "test", "test", now, now, now, now,1L);
        userEntity2 = new UserEntity("test", "test", "test", "test", "test", now, now, now, now,1L);
        assertThat(userEntity1.hashCode(), equalTo(userEntity2.hashCode()));

        userEntity1 = new UserEntity("test", "test", "test", "test", "test", now, now, now, now,1L);
        userEntity1.setUserId(1L);
        userEntity2 = new UserEntity("test", "test", "test", "test", "test", now, now, now, now,1L);
        userEntity2.setUserId(1L);
        assertThat(userEntity1.hashCode(), equalTo(userEntity2.hashCode()));

        userEntity1 = new UserEntity("test1", "test1","test1","test1", "test1",now, now,now);
        userEntity2 = new UserEntity("test2", "test2","test2","test2", "test2",now, now,now);
        assertNotEquals(userEntity1.hashCode(), userEntity2.hashCode());

        userEntity1 = new UserEntity("test1", "test1", "test1", "test1", "test1", now, now, now, now,1L);
        userEntity2 = new UserEntity("test2", "test2", "test2", "test2", "test2", now, now, now, now,1L);
        assertNotEquals(userEntity1.hashCode(), userEntity2.hashCode());

        userEntity1 = new UserEntity("test1", "test1", "test1", "test1", "test1", now, now, now, now,1L);
        userEntity1.setUserId(1L);
        userEntity2 = new UserEntity("test2", "test2", "test2", "test2", "test2", now, now, now, now,1L);
        userEntity2.setUserId(2L);
        assertNotEquals(userEntity1.hashCode(), userEntity2.hashCode());

    }

}