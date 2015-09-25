/**
 * Name      : com.malcolm.qme.core.domain.fixtures.UserRoleFixtures.java
 * Date      : 9/24/15
 * Developer : Malcolm
 * Purpose   : Test Fixtures for User Role
 */
package com.malcolm.qme.core.domain.fixtures;

import com.malcolm.qme.core.domain.UserRole;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */

public class UserRoleFixtures {

    /**
     * Simple User Role
     *
     * @return User Role
     */
    public static UserRole simpleUserRole(){
        return new UserRole(1L, 1, "role name", 1L);
    }

    /**
     * Simple User Role List
     *

     * @return User Role List
     */
    public static List<UserRole> simpleUserRoleList(){
        List<UserRole> userRoleList = new ArrayList<>();
        userRoleList.add(new UserRole(1L, 1, "role name 1", 1L));
        userRoleList.add(new UserRole(2L, 2, "role name 2", 2L));
        userRoleList.add(new UserRole(3L, 3, "role name 3", 3L));
        userRoleList.add(new UserRole(4L, 4, "role name 4", 4L));
        userRoleList.add(new UserRole(5L, 5, "role name 5", 5L));
        return userRoleList;
    }


    /**
     * Simple User Role List
     *

     * @return User Role List
     */
    public static List<UserRole> simpleUserRoleListForUser(){
        List<UserRole> userRoleList = new ArrayList<>();
        userRoleList.add(new UserRole(1L, 1, "role name 1", 1L));
        userRoleList.add(new UserRole(2L, 2, "role name 2", 1L));
        userRoleList.add(new UserRole(3L, 3, "role name 3", 1L));
        return userRoleList;
    }

    /**
     * Simple User Role List
     *

     * @return User Role List
     */
    public static List<UserRole> simpleUserRoleListForUserDiff(){
        List<UserRole> userRoleList = new ArrayList<>();
        userRoleList.add(new UserRole(1L, 11, "role name 11", 1L));
        return userRoleList;
    }

    /**
     * Simple User Role List
     *

     * @return User Role List
     */
    public static List<UserRole> simpleUserRoleListForRole(){
        List<UserRole> userRoleList = new ArrayList<>();
        userRoleList.add(new UserRole(1L, 1, "role name 1", 1L));
        userRoleList.add(new UserRole(2L, 1, "role name 2", 2L));
        userRoleList.add(new UserRole(3L, 1, "role name 3", 3L));
        return userRoleList;
    }
}
