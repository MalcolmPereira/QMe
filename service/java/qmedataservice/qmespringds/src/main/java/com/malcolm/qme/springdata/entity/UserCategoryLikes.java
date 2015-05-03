/**
 * Name      : com.malcolm.qme.springdata.entity.UserCategoryLikes.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : UserCategoryLikes Entity Class
 */
// Generated May 3, 2015 6:11:19 PM by Hibernate Tools 4.3.1
package com.malcolm.qme.springdata.entity;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UserCategoryLikes generated by hbm2java
 */
@Entity
@Table(name = "USER_CATEGORY_LIKES", catalog = "qme")
public class UserCategoryLikes implements java.io.Serializable {

	private static final long serialVersionUID = -3439766010783398098L;
	
	private UserCategoryLikesId id;

	public UserCategoryLikes() {
	}

	public UserCategoryLikes(UserCategoryLikesId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false)),
			@AttributeOverride(name = "catId", column = @Column(name = "CAT_ID", nullable = false)) })
	public UserCategoryLikesId getId() {
		return this.id;
	}

	public void setId(UserCategoryLikesId id) {
		this.id = id;
	}

}
