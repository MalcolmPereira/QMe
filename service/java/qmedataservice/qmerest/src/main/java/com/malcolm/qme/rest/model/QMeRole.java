/**
 * Name      : com.malcolm.qme.rest.model.QMeRole.java
 * Date      : 8/25/15
 * Developer : Malcolm
 * Purpose   : QMe Role
 */

package com.malcolm.qme.rest.model;

/**
 * @author malcolm
 */
public class QMeRole extends QMeResource {
    /**
     * Role Id
     */
    private Integer roleID;
    /**
     * Role Name
     */
    private String roleName;
    /**
     * Role Desc
     */
    private String roleDesc;

    /**
     * Get Role Id
     * @return roleID
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
     * @return roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Set Role Name
     * @param roleName the role name to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Get Role Desc
     * @return the role desc to return
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * Set Role Description
     * @param roleDesc the description to set
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        QMeRole qMeRole = (QMeRole) o;

        if (getRoleID() != null ? !getRoleID().equals(qMeRole.getRoleID()) : qMeRole.getRoleID() != null) return false;
        if (getRoleName() != null ? !getRoleName().equals(qMeRole.getRoleName()) : qMeRole.getRoleName() != null)
            return false;
        return !(getRoleDesc() != null ? !getRoleDesc().equals(qMeRole.getRoleDesc()) : qMeRole.getRoleDesc() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getRoleID() != null ? getRoleID().hashCode() : 0);
        result = 31 * result + (getRoleName() != null ? getRoleName().hashCode() : 0);
        result = 31 * result + (getRoleDesc() != null ? getRoleDesc().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QMeRole{" +
                "roleID=" + roleID +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
