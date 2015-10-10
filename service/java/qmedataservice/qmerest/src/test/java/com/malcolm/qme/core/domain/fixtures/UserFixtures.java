/**
 * Name      : com.malcolm.qme.core.domain.fixtures.UserFixtures.java
 * Date      : 5/27/2015
 * Developer : Malcolm
 * Purpose   : Test Fixtures for User
 **/
package com.malcolm.qme.core.domain.fixtures;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserRole;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
public class UserFixtures {

    /**
     * Return Simple User
     *
     * @return User
     */
    public static User simpleUser() {
        return new User(1L, "suser1", "spassword1", "Simple 1", "Simple User 1", "SimpleUser1@User.com", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(), 1L);
    }

    /**
     * Return Simple User
     *
     * @return User
     */
    public static User simpleUserWithTestPassword() {
        return new User(1L, "suser1", "testtestpassword", "Simple 1", "Simple User 1", "SimpleUser1@User.com", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(), 1L);
    }

    /**
     * Return Simple User List
     *
     * @return User List
     */
    public static List<User> simpleUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(
                new User(1L, "suser1", "spassword1", "Simple 1", "Simple User 1", "SimpleUser1@User.com", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(),1L)
        );
        userList.add(
                new User(2L, "suser2", "spassword2", "Simple 2", "Simple User 2", "SimpleUser2@User.com", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(),2L)
        );
        userList.add(
                new User(3L, "suser3", "spassword3", "Simple 3", "Simple User 3", "SimpleUser3@User.com", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(),3L)
        );
        userList.add(
                new User(4L, "suser4", "spassword4", "Simple 4", "Simple User 4", "SimpleUser4@User.com", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(),4L)
        );
        userList.add(
                new User(5L, "suser5", "spassword5", "Simple 5", "Simple User 5", "SimpleUser5@User.com", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(),5L)
        );
        return userList;
    }

    /**
     * Return Simple User List
     *
     * @return User List
     */
    public static List<UserRole> simpleUserRoleList() {
        List<UserRole> userRoleList = new ArrayList<>();
        userRoleList.add(
                new UserRole(1L, 1, "ADMIN", 1L)
        );
        return userRoleList;
    }

}
