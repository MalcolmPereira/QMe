/**
 * Name      : com.malcolm.qme.springdata.entity.CategoryEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Category Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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
	public CategoryEntity(String catName, Long catParentId, LocalDateTime catCreateDate, Long catCreateUser) {
		this.catName = catName;
		this.catParentId = catParentId;
		this.catCreateDate = catCreateDate;
		this.catCreateUser = catCreateUser;
	}

    /**
     * Public Constructor
     *
     * @param catName Category Name
     * @param catParentId Category Parent Id
     * @param catCreateDate Category Create Date
     * @param catCreateUser Category Create User
     * @param catLikes Category Likes
     */
    public CategoryEntity(String catName, Long catParentId, LocalDateTime catCreateDate, Long catCreateUser, Long catLikes) {
        this.catName = catName;
        this.catParentId = catParentId;
        this.catCreateDate = catCreateDate;
        this.catCreateUser = catCreateUser;
        this.catLikes = catLikes;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(catId, that.catId) &&
                Objects.equals(catName, that.catName) &&
                Objects.equals(catParentId, that.catParentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catId, catName, catParentId);
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
