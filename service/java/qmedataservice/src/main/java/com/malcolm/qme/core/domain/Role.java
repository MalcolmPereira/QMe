/**
 * Name      : com.malcolm.qme.core.domain.Role.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe Role Domain Class
 */
package com.malcolm.qme.core.domain;

/**
 * Created by Malcolm on 5/1/2015.
 */
public final class Role {
    /**
     * Role Id
     */
    private final long roleID;
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
     * @param roleID
     * @param roleName
     * @param roleDesc
     */
    public Role(long roleID, String roleName, String roleDesc) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }

    /**
     * Public Constructor
     * @param roleName
     * @param roleDesc
     */
    public Role(String roleName, String roleDesc) {
        this.roleID     = 0;
        this.roleName   = roleName;
        this.roleDesc   = roleDesc;
    }

    /**
     * Get Role ID
     * @return
     */
    public long getRoleID() {
        return roleID;
    }

    /**
     * Get Role Name
     * @return
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Get Role Desc
     * @return
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (getRoleID() != role.getRoleID()) return false;
        if (!getRoleName().equals(role.getRoleName())) return false;
        return getRoleDesc().equals(role.getRoleDesc());

    }

    @Override
    public int hashCode() {
        int result = (int) (getRoleID() ^ (getRoleID() >>> 32));
        result = 31 * result + getRoleName().hashCode();
        result = 31 * result + getRoleDesc().hashCode();
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
