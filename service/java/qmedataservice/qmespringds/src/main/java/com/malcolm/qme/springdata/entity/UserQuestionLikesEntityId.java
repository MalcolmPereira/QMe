/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuestionLikesEntityId.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Question Likes Entity Id
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserQuestionLikesEntityId that = (UserQuestionLikesEntityId) o;
		return Objects.equals(userId, that.userId) &&
				Objects.equals(questionId, that.questionId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, questionId);
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
