/**
 * Name      : com.malcolm.qme.springdata.entity.UserRolesEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Roles Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserRolesEntityTest {

    @Test
    public void testEquals() throws Exception {
        RoleEntity roleEntity1 = new RoleEntity("test", "test");
        roleEntity1.setRoleId(1);
        RoleEntity roleEntity2 = new RoleEntity("test", "test");
        roleEntity2.setRoleId(1);
        UserRolesEntity userRolesEntity1 = new UserRolesEntity(1L, 1, roleEntity1);
        UserRolesEntity userRolesEntity2 = new UserRolesEntity(1L, 1, roleEntity1);
        assertTrue(userRolesEntity1.equals(userRolesEntity2));
        assertTrue(userRolesEntity1.equals(userRolesEntity1));

        roleEntity1 = new RoleEntity("test1", "test1");
        roleEntity1.setRoleId(1);
        roleEntity2 = new RoleEntity("test2", "test2");
        roleEntity2.setRoleId(2);
        userRolesEntity1 = new UserRolesEntity(1L, 1, roleEntity1);
        userRolesEntity2 = new UserRolesEntity(2L, 2, roleEntity2);
        assertFalse(userRolesEntity1.equals(userRolesEntity2));
        assertFalse(userRolesEntity1.equals(null));
        assertFalse(userRolesEntity1.equals(""));
    }

    @Test
    public void testHashCode() throws Exception {
        RoleEntity roleEntity1 = new RoleEntity("test", "test");
        roleEntity1.setRoleId(1);
        RoleEntity roleEntity2 = new RoleEntity("test", "test");
        roleEntity2.setRoleId(1);
        UserRolesEntity userRolesEntity1 = new UserRolesEntity(1L, 1, roleEntity1);
        UserRolesEntity userRolesEntity2 = new UserRolesEntity(1L, 1, roleEntity1);
        assertThat(userRolesEntity1.hashCode(), equalTo(userRolesEntity2.hashCode()));
        assertThat(userRolesEntity1.hashCode(), equalTo(userRolesEntity1.hashCode()));

        roleEntity1 = new RoleEntity("test1", "test1");
        roleEntity1.setRoleId(1);
        roleEntity2 = new RoleEntity("test2", "test2");
        roleEntity2.setRoleId(2);
        userRolesEntity1 = new UserRolesEntity(1L, 1, roleEntity1);
        userRolesEntity2 = new UserRolesEntity(2L, 2, roleEntity2);
        assertNotEquals(userRolesEntity1.hashCode(), userRolesEntity2.hashCode());
    }
}