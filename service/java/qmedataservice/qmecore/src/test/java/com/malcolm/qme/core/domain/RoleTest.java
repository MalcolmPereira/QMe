/**
 * Name      : com.malcolm.qme.core.domain.RoleTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe Role Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class RoleTest {

    @Test
    public void testGetRoleID() throws Exception {
        Role role = new Role(1, "Some Role", "Description");
        assertThat(role.getRoleID(), equalTo(1));
        role = new  Role("Some Role", "Description");
        assertThat(role.getRoleID(), equalTo(0));
    }

    @Test
    public void testGetRoleName() throws Exception {
        Role role = new Role(1, "Some Role", "Description");
        assertThat(role.getRoleName(), equalTo("Some Role"));
        role = new  Role("Some Role", "Description");
        assertThat(role.getRoleName(), equalTo("Some Role"));
    }

    @Test
    public void testGetRoleDesc() throws Exception {
        Role role = new Role(1, "Some Role", "Description");
        assertThat(role.getRoleDesc(), equalTo("Description"));
        role = new  Role("Some Role", "Description");
        assertThat(role.getRoleDesc(), equalTo("Description"));
    }

    @Test
    public void testEquals(){
        Role role1 = new Role(1, "Some Role", "Description");
        Role role2 = new Role(1, "Some Role", "Description");
        assertTrue(role1.equals(role2));
        assertTrue(role1.equals(role1));
        role1 = new Role(1, "Some Role1", "Description");
        role2 = new Role(2, "Some Role2", "Description");
        assertFalse(role1.equals(role2));
        role1 = new  Role("Some Role", "Description");
        role2 = new  Role("Some Role", "Description");
        assertTrue(role1.equals(role2));
        role1 = new  Role("Some Role1", "Description");
        role2 = new  Role("Some Role2", "Description");
        assertFalse(role1.equals(role2));
        assertFalse(role1.equals(null));
        assertFalse(role1.equals(""));
    }

    @Test
    public void testHashCode(){
        Role role1 = new Role(1, "Some Role", "Description");
        Role role2 = new Role(1, "Some Role", "Description");
        assertThat(role1.hashCode(),equalTo(role2.hashCode()));
        role1 = new Role(1, "Some Role1", "Description");
        role2 = new Role(2, "Some Role2", "Description");
        assertNotEquals(role1.hashCode(),role2.hashCode());
        role1 = new  Role("Some Role", "Description");
        role2 = new  Role("Some Role", "Description");
        assertThat(role1.hashCode(), equalTo(role2.hashCode()));
        role1 = new  Role("Some Role1", "Description");
        role2 = new  Role("Some Role2", "Description");
        assertNotEquals(role1.hashCode(),role2.hashCode());
    }
}