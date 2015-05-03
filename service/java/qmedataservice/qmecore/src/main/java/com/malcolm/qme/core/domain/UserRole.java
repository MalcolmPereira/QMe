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
    private final long userRoleID;

    /**
     * Role Id
     */
    private final long roleID;

    /**
     * User Id
     */
    private final long userID;

    /**
     * Public Constructor
     * @param userRoleID
     * @param roleID
     * @param userID
     */
    public UserRole(long userRoleID, long roleID, long userID) {
        this.userRoleID = userRoleID;
        this.roleID = roleID;
        this.userID = userID;
    }

    /**
     * Public Constructor
     * @param roleID
     * @param userID
     */
    public UserRole(long roleID, long userID) {
        this.userRoleID = 0;
        this.roleID     = roleID;
        this.userID     = userID;
    }

    /**
     * Get User Role ID
     * @return
     */
    public long getUserRoleID() {
        return userRoleID;
    }

    /**
     * Get Role ID
     * @return
     */
    public long getRoleID() {
        return roleID;
    }

    /**
     * Get User ID
     * @return
     */
    public long getUserID() {
        return userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (getUserRoleID() != userRole.getUserRoleID()) return false;
        if (getRoleID() != userRole.getRoleID()) return false;
        return getUserID() == userRole.getUserID();

    }

    @Override
    public int hashCode() {
        int result = (int) (getUserRoleID() ^ (getUserRoleID() >>> 32));
        result = 31 * result + (int) (getRoleID() ^ (getRoleID() >>> 32));
        result = 31 * result + (int) (getUserID() ^ (getUserID() >>> 32));
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
