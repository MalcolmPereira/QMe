/**
 * Name      : com.malcolm.qme.core.domain.UserRole.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe User Role Domain Class
 */
package com.malcolm.qme.core.domain;

/**
 * @Author Malcolm
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
     * User Id
     */
    private final Long userID;

    /**
     * Public Constructor
     * @param userRoleID
     * @param roleID
     * @param userID
     */
    public UserRole(Long userRoleID, Integer roleID, Long userID) {
        this.userRoleID = userRoleID;
        this.roleID = roleID;
        this.userID = userID;
    }

    /**
     * Public Constructor
     * @param roleID
     * @param userID
     */
    public UserRole(Integer roleID, Long userID) {
        this.userRoleID = 0L;
        this.roleID     = roleID;
        this.userID     = userID;
    }

    /**
     * Get User Role ID
     * @return
     */
    public Long getUserRoleID() {
        return userRoleID;
    }

    /**
     * Get Role ID
     * @return
     */
    public Integer getRoleID() {
        return roleID;
    }

    /**
     * Get User ID
     * @return
     */
    public Long getUserID() {
        return userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (!getUserRoleID().equals(userRole.getUserRoleID())) return false;
        if (!getRoleID().equals(userRole.getRoleID())) return false;
        return getUserID().equals(userRole.getUserID());

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
