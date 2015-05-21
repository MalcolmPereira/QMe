/**
 * Name      : com.malcolm.qme.springdata.entity.UserCategoryLikesEntityId.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Category Likes Entity Id
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Malcolm
 */
@Embeddable
public class UserCategoryLikesEntityId implements java.io.Serializable {


	private static final long serialVersionUID = -4470609228632391708L;

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
	public UserCategoryLikesEntityId() {
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
		final UserCategoryLikesEntityId other = (UserCategoryLikesEntityId) obj;
		if (catId == null) {
			if (other.catId != null) {
				return false;
			}
		} else if (!catId.equals(other.catId)) {
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
		return "UserCategoryLikesEntityId [userId=" + userId + ", catId="
				+ catId + "]";
	}

}
