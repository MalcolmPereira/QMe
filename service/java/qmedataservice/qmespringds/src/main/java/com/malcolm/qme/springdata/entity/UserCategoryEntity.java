/**
 * Name      : com.malcolm.qme.springdata.entity.UserCategoryEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Category Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "USER_CATEGORY", catalog = "qme")
public class UserCategoryEntity implements java.io.Serializable {

	private static final long serialVersionUID = 850550490881060729L;

	/**
	 * User Category ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_CAT_ID", unique = true, nullable = false)
	private Long userCatId;

	/**
	 * User ID
	 */
	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	/**
	 * Category ID
	 */
	@Column(name = "CAT_ID", nullable = false)
	private Long catId;

	/**
	 * Public Constructor
	 */
	public UserCategoryEntity() {
	}

	/**
	 * Public Constructor
	 * @param userId User ID
	 * @param catId Category ID
	 */
	public UserCategoryEntity(Long userId, Long catId) {
		this.userId = userId;
		this.catId = catId;
	}

	/**
	 * @return the userCatId
	 */
	public Long getUserCatId() {
		return userCatId;
	}

	/**
	 * @param userCatId the userCatId to set
	 */
	public void setUserCatId(Long userCatId) {
		this.userCatId = userCatId;
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
	 * @return the catId
	 */
	public Long getCatId() {
		return catId;
	}

	/**
	 * @param catId the catId to set
	 */
	public void setCatId(Long catId) {
		this.catId = catId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserCategoryEntity that = (UserCategoryEntity) o;
		return Objects.equals(userCatId, that.userCatId) &&
				Objects.equals(userId, that.userId) &&
				Objects.equals(catId, that.catId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userCatId, userId, catId);
	}

	/* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
	@Override
	public String toString() {
		return "UserCategoryEntity [userCatId=" + userCatId + ", userId="
				+ userId + ", catId=" + catId + "]";
	}
}
