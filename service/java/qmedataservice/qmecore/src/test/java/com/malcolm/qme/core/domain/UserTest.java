/**
 * Name      : com.malcolm.qme.core.domain.UserTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe User Domain Class Test
 */

package com.malcolm.qme.core.domain;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class UserTest {

    @Test
    public void testGetUserID() throws Exception {
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        assertThat(user.getUserID(), equalTo(1L));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        assertThat(user.getUserID(), equalTo(0L));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        assertThat(user.getUserID(), equalTo(0L));
    }

    @Test
    public void testGetUserName() throws Exception {
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        assertThat(user.getUserName(), equalTo("Some User Name"));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        assertThat(user.getUserName(), equalTo("Some User Name"));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        assertThat(user.getUserName(), equalTo("Some User Name"));
    }

    @Test
    public void testGetUserPassword() throws Exception {
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        assertThat(user.getUserPassword(), equalTo("Some Password"));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        assertThat(user.getUserPassword(), equalTo("Some Password"));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        assertThat(user.getUserPassword(), equalTo("Some Password"));
    }

    @Test
    public void testGetUserFirstName() throws Exception {
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        assertThat(user.getUserFirstName(), equalTo("First Name"));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        assertThat(user.getUserFirstName(), equalTo("First Name"));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        assertThat(user.getUserFirstName(), equalTo("First Name"));
    }

    @Test
    public void testGetUserLastName() throws Exception {
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        assertThat(user.getUserLastName(), equalTo("Last Name"));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        assertThat(user.getUserLastName(), equalTo("Last Name"));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        assertThat(user.getUserLastName(), equalTo("Last Name"));
    }

    @Test
    public void testGetUserEmail() throws Exception {
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        assertThat(user.getUserEmail(), equalTo("Email"));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        assertThat(user.getUserEmail(), equalTo("Email"));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        assertThat(user.getUserEmail(), equalTo("Email"));
    }

    @Test
    public void testGetUserRegisteredDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", now , now ,now , now , 1L);
        assertThat(user.getUserRegisteredDate(), equalTo(now));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        assertNotNull(user.getUserRegisteredDate());
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        assertNotNull(user.getUserRegisteredDate());
    }

    @Test
    public void testGetUserUpdateDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", now , now ,now , now , 1L);
        assertThat(user.getUserUpdateDate(), equalTo(now));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        assertNotNull(user.getUserUpdateDate());
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        assertNotNull(user.getUserUpdateDate());
    }

    @Test
    public void testGetUserLastLoginDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", now , now ,now , now , 1L);
        assertThat(user.getUserLastLoginDate(), equalTo(now));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        assertNotNull(user.getUserLastLoginDate());
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        assertNotNull(user.getUserLastLoginDate());
    }

    @Test
    public void testGetUserLoginDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", now , now ,now , now , 1L);
        assertThat(user.getUserLoginDate(), equalTo(now));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        assertNotNull(user.getUserLoginDate());
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        assertNotNull(user.getUserLoginDate());
    }

    @Test
    public void testGetUpdateUserID() throws Exception {
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        assertThat(user.getUpdateUserID(), equalTo(1L));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        assertThat(user.getUpdateUserID(), equalTo(0L));
        user = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        assertThat(user.getUpdateUserID(), equalTo(0L));
    }

    @Test
    public void testEquals(){
        User user1 = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        User user2 = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        assertTrue(user1.equals(user2));
        assertTrue(user1.equals(user1));
        user1 = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email1", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        user2 = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email2", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        assertFalse(user1.equals(user2));
        user1 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        user2 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        assertTrue(user1.equals(user2));
        user1 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email1");
        user2 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email2");
        assertFalse(user1.equals(user2));
        user1 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        user2 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        assertTrue(user1.equals(user2));
        user1 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email1", LocalDateTime.now());
        user2 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email2", LocalDateTime.now());
        assertFalse(user1.equals(user2));
        assertFalse(user1.equals(null));
        assertFalse(user1.equals(""));
    }

    @Test
    public void testHashCode(){
        User user1 = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        User user2 = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        assertThat(user1.hashCode(), equalTo(user2.hashCode()));
        user1 = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email1", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        user2 = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email2", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        assertNotEquals(user1.hashCode(),user2.hashCode());
        user1 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        user2 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email");
        assertThat(user1.hashCode(), equalTo(user2.hashCode()));
        user1 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email1");
        user2 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email2");
        assertNotEquals(user1.hashCode(), user2.hashCode());
        user1 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        user2 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now());
        assertThat(user1.hashCode(), equalTo(user2.hashCode()));
        user1 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email1", LocalDateTime.now());
        user2 = new User("Some User Name", "Some Password", "First Name", "Last Name", "Email2", LocalDateTime.now());
        assertNotEquals(user1.hashCode(),user2.hashCode());
    }
}