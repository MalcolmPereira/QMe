/**
 * Name      : com.malcolm.qme.rest.model.QMeUserRole.java
 * Date      : 8/25/15
 * Developer : malcolm
 * Purpose   : QMe User Role
 */

package com.malcolm.qme.rest.model;

/**
 * @author malcolm
 */
public class QMeUserRole extends QMeResource {
    /**
     * User Role Id
     */
    private Long userRoleID;

    /**
     * Role Id
     */
    private Integer roleID;

    /**
     * Role Name
     */
    private String roleName;

    /**
     * User Id
     */
    private Long userID;

    /**
     * Get User Role ID
     * @return userRoleID
     */
    public Long getUserRoleID() {
        return userRoleID;
    }

    /**
     * Set User Role ID
     * @param userRoleID userRoleID to set
     */
    public void setUserRoleID(Long userRoleID) {
        this.userRoleID = userRoleID;
    }

    /**
     * Get Role ID
     * @return roleId
     */
    public Integer getRoleID() {
        return roleID;
    }

    /**
     * Set Role Id
     * @param roleID role id to set
     */
    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    /**
     * Get Role Name
     * @return roleName role name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Set Role Name
     * @param roleName roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Get User ID
     * @return userID user ID
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Set User ID
     * @param userID userID to set
     */
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        QMeUserRole that = (QMeUserRole) o;

        if (getUserRoleID() != null ? !getUserRoleID().equals(that.getUserRoleID()) : that.getUserRoleID() != null)
            return false;
        if (getRoleID() != null ? !getRoleID().equals(that.getRoleID()) : that.getRoleID() != null) return false;
        if (getRoleName() != null ? !getRoleName().equals(that.getRoleName()) : that.getRoleName() != null)
            return false;
        return !(getUserID() != null ? !getUserID().equals(that.getUserID()) : that.getUserID() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getUserRoleID() != null ? getUserRoleID().hashCode() : 0);
        result = 31 * result + (getRoleID() != null ? getRoleID().hashCode() : 0);
        result = 31 * result + (getRoleName() != null ? getRoleName().hashCode() : 0);
        result = 31 * result + (getUserID() != null ? getUserID().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QMeUserRole{" +
                "userRoleID=" + userRoleID +
                ", roleID=" + roleID +
                ", roleName='" + roleName + '\'' +
                ", userID=" + userID +
                '}';
    }
}
