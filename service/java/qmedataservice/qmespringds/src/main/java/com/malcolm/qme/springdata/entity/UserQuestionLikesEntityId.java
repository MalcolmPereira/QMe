/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuestionLikesEntityId.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Question Likes Entity Id
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Malcolm
 */
@Embeddable
public class UserQuestionLikesEntityId implements java.io.Serializable {

	private static final long serialVersionUID = -8147819392757860065L;

	/**
	 * User ID
	 */
	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	/**
	 * Question ID
	 */
	@Column(name = "QUESTION_ID", nullable = false)
	private Long questionId;

	/**
	 * Public Constructor
	 */
	public UserQuestionLikesEntityId() {
	}

    /**
     * Public Constructor
     *
     * @param userId User Id
     * @param questionId Question Id
     */
    public UserQuestionLikesEntityId(Long userId, Long questionId) {
        this.userId = userId;
        this.questionId = questionId;
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
	 * @return the questionId
	 */
	public Long getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((questionId == null) ? 0 : questionId.hashCode());
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
		final UserQuestionLikesEntityId other = (UserQuestionLikesEntityId) obj;
		if (questionId == null) {
			if (other.questionId != null) {
				return false;
			}
		} else if (!questionId.equals(other.questionId)) {
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
		return "UserQuestionLikesEntityId [userId=" + userId + ", questionId="
				+ questionId + "]";
	}

}
