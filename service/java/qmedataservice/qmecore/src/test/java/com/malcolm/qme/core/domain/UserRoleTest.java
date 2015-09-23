/**
 * Name      : com.malcolm.qme.core.domain.UserRoleTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe User Role Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserRoleTest {

    @Test
    public void testGetUserRoleID() throws Exception {
        UserRole userRole = new UserRole(1L, 1, "Some Role Name", 1L);
        assertThat(userRole.getUserRoleID(), equalTo(1L));
        userRole = new UserRole(1L, 1,1L);
        assertThat(userRole.getUserRoleID(), equalTo(1L));
        userRole = new UserRole(1, 1L);
        assertThat(userRole.getUserRoleID(), equalTo(0L));
    }

    @Test
    public void testGetRoleID() throws Exception {
        UserRole userRole = new UserRole(1L, 1, "Some Role Name", 1L);
        assertThat(userRole.getRoleID(), equalTo(1));
        userRole = new UserRole(1L, 1,1L);
        assertThat(userRole.getRoleID(), equalTo(1));
        userRole = new UserRole(1, 1L);
        assertThat(userRole.getRoleID(), equalTo(1));
    }

    @Test
    public void testGetRoleName() throws Exception {
        UserRole userRole = new UserRole(1L, 1, "Some Role Name", 1L);
        assertThat(userRole.getRoleName(), equalTo("Some Role Name"));
        userRole = new UserRole(1L, 1,1L);
        assertThat(userRole.getRoleName(), equalTo(""));
        userRole = new UserRole(1, 1L);
        assertThat(userRole.getRoleName(), equalTo(""));
    }

    @Test
    public void testGetUserID() throws Exception {
        UserRole userRole = new UserRole(1L, 1, "Some Role Name", 1L);
        assertThat(userRole.getUserID(), equalTo(1L));
        userRole = new UserRole(1L, 1,1L);
        assertThat(userRole.getUserID(), equalTo(1L));
        userRole = new UserRole(1, 1L);
        assertThat(userRole.getUserID(), equalTo(1L));
    }

    @Test
    public void testEquals(){
        UserRole userRole1 = new UserRole(1L, 1, "Some Role Name", 1L);
        UserRole userRole2 = new UserRole(1L, 1, "Some Role Name", 1L);
        assertTrue(userRole1.equals(userRole2));
        userRole1 = new UserRole(1L, 1, "Some Role Name1", 1L);
        userRole2 = new UserRole(1L, 1, "Some Role Name2", 1L);
        assertFalse(userRole1.equals(userRole2));
        userRole1 = new UserRole(1L, 1,1L);
        userRole2 = new UserRole(1L, 1,1L);
        assertTrue(userRole1.equals(userRole2));
        userRole1 = new UserRole(1L, 1,1L);
        userRole2 = new UserRole(1L, 2,1L);
        assertFalse(userRole1.equals(userRole2));
        userRole1 = new UserRole(1, 1L);
        userRole2 = new UserRole(1, 1L);
        assertTrue(userRole1.equals(userRole2));
        userRole1 = new UserRole(1, 1L);
        userRole2 = new UserRole(1, 2L);
        assertFalse(userRole1.equals(userRole2));
    }

    @Test
    public void testHashCode(){
        UserRole userRole1 = new UserRole(1L, 1, "Some Role Name", 1L);
        UserRole userRole2 = new UserRole(1L, 1, "Some Role Name", 1L);
        assertThat(userRole1.hashCode(), equalTo(userRole2.hashCode()));
        userRole1 = new UserRole(1L, 1, "Some Role Name1", 1L);
        userRole2 = new UserRole(1L, 1, "Some Role Name2", 1L);
        assertNotEquals(userRole1.hashCode(),userRole2.hashCode());
        userRole1 = new UserRole(1L, 1,1L);
        userRole2 = new UserRole(1L, 1,1L);
        assertThat(userRole1.hashCode(), equalTo(userRole2.hashCode()));
        userRole1 = new UserRole(1L, 1,1L);
        userRole2 = new UserRole(1L, 2,1L);
        assertNotEquals(userRole1.hashCode(), userRole2.hashCode());
        userRole1 = new UserRole(1, 1L);
        userRole2 = new UserRole(1, 1L);
        assertThat(userRole1.hashCode(), equalTo(userRole2.hashCode()));
        userRole1 = new UserRole(1, 1L);
        userRole2 = new UserRole(1, 2L);
        assertNotEquals(userRole1.hashCode(),userRole2.hashCode());
    }
}