/**
 * Name      : com.malcolm.qme.springdata.entity.RoleEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Role Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "ROLE", catalog = "qme", uniqueConstraints = @UniqueConstraint(columnNames = "ROLE_NAME"))
public class RoleEntity implements java.io.Serializable {

	private static final long serialVersionUID = 4668281417163867805L;

	/**
	 * Role Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	private Integer roleId;

	/**
	 * Role Name
	 */
	@Column(name = "ROLE_NAME", unique = true, nullable = false, length = 45)
	private String roleName;

	/**
	 * Role Desc
	 */
	@Column(name = "ROLE_DESC", nullable = false, length = 128)
	private String roleDesc;

	/**
	 * Public Constructor
	 */
	public RoleEntity() {
	}

    /***
     * Public Constructor
     *
     * @param roleName Role Name
     * @param roleDesc Role Description
     */
    public RoleEntity(String roleName, String roleDesc) {
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }

    /**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the roleDesc
	 */
	public String getRoleDesc() {
		return roleDesc;
	}

	/**
	 * @param roleDesc the roleDesc to set
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RoleEntity that = (RoleEntity) o;
		return Objects.equals(roleId, that.roleId) &&
				Objects.equals(roleName, that.roleName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleId, roleName);
	}

	/* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
	@Override
	public String toString() {
		return "RoleEntity [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleDesc=" + roleDesc + "]";
	}

}
