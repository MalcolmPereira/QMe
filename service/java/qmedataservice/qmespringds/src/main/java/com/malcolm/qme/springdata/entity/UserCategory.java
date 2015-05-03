/**
 * Name      : com.malcolm.qme.springdata.entity.UserCategory.java
 * Date      : 5/3/15
 * Developer : Ma
 * lcolm
 * Purpose   : UserCategory Entity Class
 */
// Generated May 3, 2015 6:11:19 PM by Hibernate Tools 4.3.1
package com.malcolm.qme.springdata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserCategory generated by hbm2java
 */
@Entity
@Table(name = "USER_CATEGORY", catalog = "qme")
public class UserCategory implements java.io.Serializable {

	private static final long serialVersionUID = -5740356493801609726L;
	
	private Integer userCatId;
	private int userId;
	private int catId;

	public UserCategory() {
	}

	public UserCategory(int userId, int catId) {
		this.userId = userId;
		this.catId = catId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USER_CAT_ID", unique = true, nullable = false)
	public Integer getUserCatId() {
		return this.userCatId;
	}

	public void setUserCatId(Integer userCatId) {
		this.userCatId = userCatId;
	}

	@Column(name = "USER_ID", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "CAT_ID", nullable = false)
	public int getCatId() {
		return this.catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

}
