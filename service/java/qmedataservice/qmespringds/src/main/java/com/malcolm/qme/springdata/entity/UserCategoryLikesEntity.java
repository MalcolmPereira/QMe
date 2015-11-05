/**
 * Name      : com.malcolm.qme.springdata.entity.UserCategoryLikesEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Category Likes Entity
 */
package com.malcolm.qme.springdata.entity;


import javax.persistence.*;
import java.util.Objects;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "USER_CATEGORY_LIKES", catalog = "qme")
public class UserCategoryLikesEntity implements java.io.Serializable {


	private static final long serialVersionUID = 3588585992432983653L;

	/**
	 * User Category ID
	 */
	@EmbeddedId
	@AttributeOverrides(
			{
				@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false)),
				@AttributeOverride(name = "catId", column = @Column(name = "CAT_ID", nullable = false))
			}
			)
	private UserCategoryLikesEntityId id;

	/**
	 * Public Constructor
	 */
	public UserCategoryLikesEntity() {
	}

    /**
     * Public Constructor
     * @param id UserCategory Likes Entity ID
     */
    public UserCategoryLikesEntity(UserCategoryLikesEntityId id) {
        this.id = id;
    }

    /**
	 * @return the id
	 */
	public UserCategoryLikesEntityId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UserCategoryLikesEntityId id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserCategoryLikesEntity that = (UserCategoryLikesEntity) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
	@Override
	public String toString() {
		return "UserCategoryLikesEntity [id=" + id + "]";
	}

}
