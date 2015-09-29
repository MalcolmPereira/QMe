/**
 * Name      : com.malcolm.qme.springdata.entity.RoleEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Role Entity
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class RoleEntityTest {

    @Test
    public void testEquals() throws Exception {
        RoleEntity roleEntity1 = new RoleEntity("test", "test");
        RoleEntity roleEntity2 = new RoleEntity("test", "test");
        assertTrue(roleEntity1.equals(roleEntity2));
        assertTrue(roleEntity1.equals(roleEntity1));

        roleEntity1 = new RoleEntity("test", "test");
        roleEntity1.setRoleId(1);
        roleEntity2 = new RoleEntity("test", "test");
        roleEntity2.setRoleId(1);
        assertTrue(roleEntity1.equals(roleEntity2));

        roleEntity1 = new RoleEntity("test1", "test1");
        roleEntity2 = new RoleEntity("test2", "test2");
        assertFalse(roleEntity1.equals(roleEntity2));

        roleEntity1 = new RoleEntity("test1", "test1");
        roleEntity1.setRoleId(1);
        roleEntity2 = new RoleEntity("test2", "test2");
        roleEntity2.setRoleId(2);
        assertFalse(roleEntity1.equals(roleEntity2));

        assertFalse(roleEntity1.equals(null));
        assertFalse(roleEntity1.equals(""));
    }

    @Test
    public void testHashCode() throws Exception {
        RoleEntity roleEntity1 = new RoleEntity("test", "test");
        RoleEntity roleEntity2 = new RoleEntity("test", "test");
        assertThat(roleEntity1.hashCode(), equalTo(roleEntity2.hashCode()));
        assertThat(roleEntity1.hashCode(), equalTo(roleEntity1.hashCode()));

        roleEntity1 = new RoleEntity("test", "test");
        roleEntity1.setRoleId(1);
        roleEntity2 = new RoleEntity("test", "test");
        roleEntity2.setRoleId(1);
        assertThat(roleEntity1.hashCode(), equalTo(roleEntity2.hashCode()));

        roleEntity1 = new RoleEntity("test1", "test1");
        roleEntity2 = new RoleEntity("test2", "test2");
        assertNotEquals(roleEntity1.hashCode(), roleEntity2.hashCode());

        roleEntity1 = new RoleEntity("test1", "test1");
        roleEntity1.setRoleId(1);
        roleEntity2 = new RoleEntity("test2", "test2");
        roleEntity2.setRoleId(2);
        assertNotEquals(roleEntity1.hashCode(), roleEntity2.hashCode());

    }
}