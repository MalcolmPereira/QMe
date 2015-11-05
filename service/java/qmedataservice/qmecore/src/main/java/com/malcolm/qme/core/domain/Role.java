/**
 * Name      : com.malcolm.qme.core.domain.Role.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe Role Domain Class
 */
package com.malcolm.qme.core.domain;

import java.util.Objects;

/**
 * @author Malcolm
 */
public final class Role {
    /**
     * Role Id
     */
    private final Integer roleID;
    /**
     * Role Name
     */
    private final String roleName;
    /**
     * Role Desc
     */
    private final String roleDesc;

    /**
     * Public Constructor
     * @param roleID Role ID
     * @param roleName Role Name
     * @param roleDesc Role Description
     */
    public Role(Integer roleID, String roleName, String roleDesc) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }

    /**
     * Public Constructor
     * @param roleName Role Name
     * @param roleDesc Role Description
     */
    public Role(String roleName, String roleDesc) {
        this.roleID     = 0;
        this.roleName   = roleName;
        this.roleDesc   = roleDesc;
    }

    /**
     * Get Role ID
     * @return Role ID
     */
    public Integer  getRoleID() {
        return roleID;
    }

    /**
     * Get Role Name
     * @return Role Name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Get Role Desc
     * @return Role Desc
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleID, role.roleID) &&
                Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleID, roleName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleID=" + roleID +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
