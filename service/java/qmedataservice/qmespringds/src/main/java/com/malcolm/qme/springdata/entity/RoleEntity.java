/**
 * Name      : com.malcolm.qme.springdata.entity.RoleEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Role Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @Author: Malcolm
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

	/**
	 * Public Constructor
	 *
	 * @param roleName
	 * @param roleDesc
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((roleDesc == null) ? 0 : roleDesc.hashCode());
		result = (prime * result) + ((roleId == null) ? 0 : roleId.hashCode());
		result = (prime * result)
				+ ((roleName == null) ? 0 : roleName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final RoleEntity other = (RoleEntity) obj;
		if (roleDesc == null) {
			if (other.roleDesc != null) {
				return false;
			}
		} else if (!roleDesc.equals(other.roleDesc)) {
			return false;
		}
		if (roleId == null) {
			if (other.roleId != null) {
				return false;
			}
		} else if (!roleId.equals(other.roleId)) {
			return false;
		}
		if (roleName == null) {
			if (other.roleName != null) {
				return false;
			}
		} else if (!roleName.equals(other.roleName)) {
			return false;
		}
		return true;
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
