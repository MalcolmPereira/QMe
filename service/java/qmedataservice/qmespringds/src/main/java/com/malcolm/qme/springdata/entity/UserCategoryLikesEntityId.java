/**
 * Name      : com.malcolm.qme.springdata.entity.UserCategoryLikesEntityId.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Category Likes Entity Id
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

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
     * Public Constructor
     * @param userId User Id
     * @param catId Category Id
     */
    public UserCategoryLikesEntityId(Long userId, Long catId) {
        this.userId = userId;
        this.catId = catId;
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
		UserCategoryLikesEntityId that = (UserCategoryLikesEntityId) o;
		return Objects.equals(userId, that.userId) &&
				Objects.equals(catId, that.catId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, catId);
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
