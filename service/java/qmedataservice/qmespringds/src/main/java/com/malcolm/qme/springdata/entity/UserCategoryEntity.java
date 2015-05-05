package com.malcolm.qme.springdata.entity;

// Generated May 4, 2015 10:39:47 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserCategoryEntity generated by hbm2java
 */
@Entity
@Table(name = "USER_CATEGORY", catalog = "qme")
public class UserCategoryEntity implements java.io.Serializable {

	private static final long serialVersionUID = 850550490881060729L;
	
	private Long userCatId;
	private Long userId;
	private Long catId;

	public UserCategoryEntity() {
	}

	public UserCategoryEntity(Long userId, Long catId) {
		this.userId = userId;
		this.catId = catId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USER_CAT_ID", unique = true, nullable = false)
	public Long getUserCatId() {
		return this.userCatId;
	}

	public void setUserCatId(Long userCatId) {
		this.userCatId = userCatId;
	}

	@Column(name = "USER_ID", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "CAT_ID", nullable = false)
	public Long getCatId() {
		return this.catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

}
