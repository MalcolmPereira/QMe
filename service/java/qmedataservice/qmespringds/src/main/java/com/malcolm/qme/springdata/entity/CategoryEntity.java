/**
 * Name      : com.malcolm.qme.springdata.entity.CategoryEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Category Entity
 */
package com.malcolm.qme.springdata.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "CATEGORY", catalog = "qme", uniqueConstraints = @UniqueConstraint(columnNames = "CAT_NAME"))
public class CategoryEntity implements java.io.Serializable {

	private static final long serialVersionUID = -5184527654571520450L;

	/**
	 * Category ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CAT_ID", unique = true, nullable = false)
	private Long catId;

	/**
	 * Category Name
	 */
	@Column(name = "CAT_NAME", unique = true, nullable = false, length = 150)
	private String catName;

	/**
	 * Category Parent Id
	 */
	@Column(name = "CAT_PARENT_ID", nullable = false)
	private Long catParentId;

	/**
	 * Category Create Date
	 */
	@Column(name = "CAT_CREATE_DATE", nullable = false, length = 19)
	private LocalDateTime catCreateDate;

	/**
	 * Category Create User
	 */
	@Column(name = "CAT_CREATE_USER", nullable = false)
	private Long catCreateUser;

	/**
	 * Category Likes
	 */
	@Column(name = "CAT_LIKES")
	private Long catLikes;

	/**
	 * Public Constructor
	 */
	public CategoryEntity() {
	}

	/**
	 * Public Constructor
	 *
	 * @param catName Category Name
	 * @param catParentId Category Parent ID
	 * @param catCreateDate Category Create Date
	 * @param catCreateUser Category Create User
	 */
	public CategoryEntity(String catName, Long catParentId, LocalDateTime catCreateDate,
			Long catCreateUser) {
		this.catName = catName;
		this.catParentId = catParentId;
		this.catCreateDate = catCreateDate;
		this.catCreateUser = catCreateUser;
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

	/**
	 * @return the catName
	 */
	public String getCatName() {
		return catName;
	}

	/**
	 * @param catName the catName to set
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}

	/**
	 * @return the catParentId
	 */
	public Long getCatParentId() {
		return catParentId;
	}

	/**
	 * @param catParentId the catParentId to set
	 */
	public void setCatParentId(Long catParentId) {
		this.catParentId = catParentId;
	}

	/**
	 * @return the catCreateDate
	 */
	public LocalDateTime getCatCreateDate() {
		return catCreateDate;
	}

	/**
	 * @param catCreateDate the catCreateDate to set
	 */
	public void setCatCreateDate(LocalDateTime catCreateDate) {
		this.catCreateDate = catCreateDate;
	}

	/**
	 * @return the catCreateUser
	 */
	public Long getCatCreateUser() {
		return catCreateUser;
	}

	/**
	 * @param catCreateUser the catCreateUser to set
	 */
	public void setCatCreateUser(Long catCreateUser) {
		this.catCreateUser = catCreateUser;
	}

	/**
	 * @return the catLikes
	 */
	public Long getCatLikes() {
		return catLikes;
	}

	/**
	 * @param catLikes the catLikes to set
	 */
	public void setCatLikes(Long catLikes) {
		this.catLikes = catLikes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((catCreateDate == null) ? 0 : catCreateDate.hashCode());
		result = (prime * result)
				+ ((catCreateUser == null) ? 0 : catCreateUser.hashCode());
		result = (prime * result) + ((catId == null) ? 0 : catId.hashCode());
		result = (prime * result)
				+ ((catLikes == null) ? 0 : catLikes.hashCode());
		result = (prime * result) + ((catName == null) ? 0 : catName.hashCode());
		result = (prime * result)
				+ ((catParentId == null) ? 0 : catParentId.hashCode());
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
		final CategoryEntity other = (CategoryEntity) obj;
		if (catCreateDate == null) {
			if (other.catCreateDate != null) {
				return false;
			}
		} else if (!catCreateDate.equals(other.catCreateDate)) {
			return false;
		}
		if (catCreateUser == null) {
			if (other.catCreateUser != null) {
				return false;
			}
		} else if (!catCreateUser.equals(other.catCreateUser)) {
			return false;
		}
		if (catId == null) {
			if (other.catId != null) {
				return false;
			}
		} else if (!catId.equals(other.catId)) {
			return false;
		}
		if (catLikes == null) {
			if (other.catLikes != null) {
				return false;
			}
		} else if (!catLikes.equals(other.catLikes)) {
			return false;
		}
		if (catName == null) {
			if (other.catName != null) {
				return false;
			}
		} else if (!catName.equals(other.catName)) {
			return false;
		}
		if (catParentId == null) {
			if (other.catParentId != null) {
				return false;
			}
		} else if (!catParentId.equals(other.catParentId)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CategoryEntity [catId=" + catId + ", catName=" + catName
				+ ", catParentId=" + catParentId + ", catCreateDate="
				+ catCreateDate + ", catCreateUser=" + catCreateUser
				+ ", catLikes=" + catLikes + "]";
	}
}
