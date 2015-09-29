/**
 * Name      : com.malcolm.qme.springdata.entity.UserCategoryEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Category Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((catId == null) ? 0 : catId.hashCode());
		result = (prime * result)
				+ ((userCatId == null) ? 0 : userCatId.hashCode());
		result = (prime * result) + ((userId == null) ? 0 : userId.hashCode());
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
		final UserCategoryEntity other = (UserCategoryEntity) obj;
		if (catId == null) {
			if (other.catId != null) {
				return false;
			}
		} else if (!catId.equals(other.catId)) {
			return false;
		}
		if (userCatId == null) {
			if (other.userCatId != null) {
				return false;
			}
		} else if (!userCatId.equals(other.userCatId)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
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
