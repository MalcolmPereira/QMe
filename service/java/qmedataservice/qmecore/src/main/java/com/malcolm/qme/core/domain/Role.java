/**
 * Name      : com.malcolm.qme.core.domain.Role.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe Role Domain Class
 */
package com.malcolm.qme.core.domain;

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

        return getRoleID().equals(role.getRoleID()) && getRoleName().equals(role.getRoleName()) && !(getRoleDesc() != null ? !getRoleDesc().equals(role.getRoleDesc()) : role.getRoleDesc() != null);

    }

    @Override
    public int hashCode() {
        int result = getRoleID().hashCode();
        result = 31 * result + getRoleName().hashCode();
        result = 31 * result + (getRoleDesc() != null ? getRoleDesc().hashCode() : 0);
        return result;
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
