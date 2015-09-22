/**
 * Name      : com.malcolm.qme.core.domain.UserRole.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe User Role Domain Class
 */
package com.malcolm.qme.core.domain;

/**
 * @author Malcolm
 */
public final class UserRole {
    /**
     * User Role Id
     */
    private final Long userRoleID;

    /**
     * Role Id
     */
    private final Integer roleID;

    /**
     * Role Name
     */
    private final String roleName;

    /**
     * User Id
     */
    private final Long userID;

    /**
     * Public Constructor
     * @param userRoleID User Role ID
     * @param roleID Role ID
     * @param roleName Role Name
     * @param userID User ID
     */
    public UserRole(Long userRoleID, Integer roleID, String roleName, Long userID) {
        this.userRoleID = userRoleID;
        this.roleID = roleID;
        this.roleName = roleName;
        this.userID = userID;
    }

    /**
     * Public Constructor
     * @param userRoleID User Role ID
     * @param roleID Role ID
     * @param userID User ID
     */
    public UserRole(Long userRoleID, Integer roleID,Long userID) {
        this.userRoleID = userRoleID;
        this.roleID = roleID;
        this.roleName   = "";
        this.userID = userID;
    }

    /**
     * Public Constructor
     * @param roleID Role ID
     * @param userID User ID
     */
    public UserRole(Integer roleID, Long userID) {
        this.userRoleID = 0L;
        this.roleID     = roleID;
        this.roleName   = "";
        this.userID     = userID;
    }

    /**
     * Get User Role ID
     * @return User Role ID
     */
    public Long getUserRoleID() {
        return userRoleID;
    }

    /**
     * Get Role ID
     * @return Role ID
     */
    public Integer getRoleID() {
        return roleID;
    }

    /**
     * Get Role Name
     *
     * @return String Role Name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Get User ID
     * @return User ID
     */
    public Long getUserID() {
        return userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        return getUserRoleID().equals(userRole.getUserRoleID()) && getRoleID().equals(userRole.getRoleID()) && getUserID().equals(userRole.getUserID());

    }

    @Override
    public int hashCode() {
        int result = getUserRoleID().hashCode();
        result = 31 * result + getRoleID().hashCode();
        result = 31 * result + getUserID().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userRoleID=" + userRoleID +
                ", roleID=" + roleID +
                ", userID=" + userID +
                '}';
    }
}
