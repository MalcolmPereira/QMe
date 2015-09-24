/**
 * Name      : com.malcolm.qme.core.domain.fixtures.RoleFixtures.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : Test Fixtures for Role
 */

package com.malcolm.qme.core.domain.fixtures;

import com.malcolm.qme.core.domain.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
public class RoleFixtures {

    /**
     * Simple Role
     *
     * @return Role
     */
    public static Role simpleRole(){
        return new Role(1, "role name", "role desc");
    }

    /**
     * Simple Category
     *

     * @return Category List
     */
    public static List<Role> simpleRoleList(){
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(1,"role name 1", "role desc 1"));
        roleList.add(new Role(2,"role name 2", "role desc 2"));
        roleList.add(new Role(3,"role name 3", "role desc 3"));
        roleList.add(new Role(4,"role name 4", "role desc 4"));
        roleList.add(new Role(5,"role name 5", "role desc 5"));;
        return roleList;
    }
}
