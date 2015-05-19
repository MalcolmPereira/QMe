/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizLikesEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Likes Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: Malcolm
 */
@Entity
@Table(name = "USER_QUIZ_LIKES", catalog = "qme")
public class UserQuizLikesEntity implements java.io.Serializable {


	private static final long serialVersionUID = -7764698214631748355L;

	/**
	 * UserQuizLikesEntity Id
	 */
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false)),
		@AttributeOverride(name = "quizId", column = @Column(name = "QUIZ_ID", nullable = false)) })
	private UserQuizLikesEntityId id;

	/**
	 * Public Constructor
	 */
	public UserQuizLikesEntity() {
	}

	/**
	 * Public Constructor
	 * @param id
	 */
	public UserQuizLikesEntity(UserQuizLikesEntityId id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public UserQuizLikesEntityId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UserQuizLikesEntityId id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
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
		final UserQuizLikesEntity other = (UserQuizLikesEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserQuizLikesEntity [id=" + id + "]";
	}

}
