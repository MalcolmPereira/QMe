/**
 * Name      : com.malcolm.qme.springdata.entity.UserRolesEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Roles Entity
 */
package com.malcolm.qme.springdata.entity;

// Generated May 14, 2015 7:02:12 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Malcolm
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

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ROLE_ID", referencedColumnName="ROLE_ID", insertable = false, updatable = false)
	private RoleEntity roleEntity;

	/**
	 * Public Constructor
	 */
	public UserRolesEntity() {
	}

    /**
     * Public Constructor
     *
     * @param userId User Id
     * @param roleId Role Id
     * @param roleEntity Role Entity
     */
	public UserRolesEntity(Long userId, Integer roleId, RoleEntity roleEntity) {
		this.userId = userId;
		this.roleId = roleId;
		this.roleEntity = roleEntity;
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

    /**
     * Return Role Entity
     *
     * @return RoleEntity
     */
    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    /**
     * Set Role Entity
     *
     * @param roleEntity RoleEntity
     */
    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRolesEntity that = (UserRolesEntity) o;
        return Objects.equals(userRoleId, that.userRoleId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRoleId, userId, roleId);
    }

    @Override
	public String toString() {
		return "UserRolesEntity{" +
				"userRoleId=" + userRoleId +
				", userId=" + userId +
				", roleId=" + roleId +
				", roleEntity=" + roleEntity +
				'}';
	}
}
