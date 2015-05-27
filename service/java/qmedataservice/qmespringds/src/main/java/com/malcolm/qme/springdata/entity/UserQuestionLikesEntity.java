/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuestionLikesEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Question Likes Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;

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
		final UserQuestionLikesEntity other = (UserQuestionLikesEntity) obj;
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
		return "UserQuestionLikesEntity [id=" + id + "]";
	}

}
