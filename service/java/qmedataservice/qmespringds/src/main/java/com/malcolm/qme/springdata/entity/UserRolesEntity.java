/**
 * Name      : com.malcolm.qme.springdata.entity.UserRolesEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Roles Entity
 */
package com.malcolm.qme.springdata.entity;

// Generated May 14, 2015 7:02:12 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: Malcolm
 */
@Entity
@Table(name = "USER_ROLES", catalog = "qme")
public class UserRolesEntity implements java.io.Serializable {


	private static final long serialVersionUID = 8593806823811122424L;

	/**
	 * User Role Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ROLE_ID", unique = true, nullable = false)
	private Long userRoleId;

	/**
	 * User ID
	 */
	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	/**
	 * Role ID
	 */
	@Column(name = "ROLE_ID", nullable = false)
	private Integer roleId;

	/**
	 * Public Constructor
	 */
	public UserRolesEntity() {
	}

	/**
	 * Public Constructor
	 *
	 * @param userId
	 * @param roleId
	 */
	public UserRolesEntity(Long userId, Integer roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	/**
	 * @return the userRoleId
	 */
	public Long getUserRoleId() {
		return userRoleId;
	}

	/**
	 * @param userRoleId the userRoleId to set
	 */
	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((roleId == null) ? 0 : roleId.hashCode());
		result = (prime * result) + ((userId == null) ? 0 : userId.hashCode());
		result = (prime * result)
				+ ((userRoleId == null) ? 0 : userRoleId.hashCode());
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
		final UserRolesEntity other = (UserRolesEntity) obj;
		if (roleId == null) {
			if (other.roleId != null) {
				return false;
			}
		} else if (!roleId.equals(other.roleId)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (userRoleId == null) {
			if (other.userRoleId != null) {
				return false;
			}
		} else if (!userRoleId.equals(other.userRoleId)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRolesEntity [userRoleId=" + userRoleId + ", userId="
				+ userId + ", roleId=" + roleId + "]";
	}
}
