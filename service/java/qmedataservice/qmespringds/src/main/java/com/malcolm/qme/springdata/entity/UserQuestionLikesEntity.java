/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuestionLikesEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Question Likes Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "USER_QUESTION_LIKES", catalog = "qme")
public class UserQuestionLikesEntity implements java.io.Serializable {


	private static final long serialVersionUID = -5649195723277241965L;

	/**
	 * UserQuestionLikesEntity Id
	 */
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false)),
		@AttributeOverride(name = "questionId", column = @Column(name = "QUESTION_ID", nullable = false)) })
	private UserQuestionLikesEntityId id;

	/**
	 * Public Constructor
	 */
	public UserQuestionLikesEntity() {
	}

    /**
     * Public Constructor
     *
     * @param id UserQuestionLikesEntityId
     */
	public UserQuestionLikesEntity(UserQuestionLikesEntityId id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public UserQuestionLikesEntityId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UserQuestionLikesEntityId id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserQuestionLikesEntity that = (UserQuestionLikesEntity) o;
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
		return "UserQuestionLikesEntity [id=" + id + "]";
	}

}
