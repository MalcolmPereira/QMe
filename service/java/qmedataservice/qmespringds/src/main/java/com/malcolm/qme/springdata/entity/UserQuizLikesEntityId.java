/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizLikesEntityId.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Likes Entity Id
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserQuizLikesEntityId that = (UserQuizLikesEntityId) o;
		return Objects.equals(userId, that.userId) &&
				Objects.equals(quizId, that.quizId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, quizId);
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
