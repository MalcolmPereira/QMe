/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizLikesEntityId.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Likes Entity Id
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Malcolm
 */
@Embeddable
public class UserQuizLikesEntityId implements java.io.Serializable {


	private static final long serialVersionUID = -2710987586087887522L;

	/**
	 * User Id
	 */
	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	/**
	 * Quiz ID
	 */
	@Column(name = "QUIZ_ID", nullable = false)
	private Long quizId;

	/**
	 * Public Constructor
	 */
	public UserQuizLikesEntityId() {
	}

    /**
     * Public Constructor
     *
     * @param userId User Id
     * @param quizId Quiz Id
     */
    public UserQuizLikesEntityId(Long userId, Long quizId) {
        this.userId = userId;
        this.quizId = quizId;
    }

    /**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the quizId
	 */
	public Long getQuizId() {
		return quizId;
	}

	/**
	 * @param quizId the quizId to set
	 */
	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((quizId == null) ? 0 : quizId.hashCode());
		result = (prime * result) + ((userId == null) ? 0 : userId.hashCode());
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
		final UserQuizLikesEntityId other = (UserQuizLikesEntityId) obj;
		if (quizId == null) {
			if (other.quizId != null) {
				return false;
			}
		} else if (!quizId.equals(other.quizId)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserQuizLikesEntityId [userId=" + userId + ", quizId=" + quizId
				+ "]";
	}

}
