/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizLikesEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Likes Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Malcolm
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
	 *
	 * @param id UserQuizLikesEntityId
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserQuizLikesEntity that = (UserQuizLikesEntity) o;
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
		return "UserQuizLikesEntity [id=" + id + "]";
	}

}
