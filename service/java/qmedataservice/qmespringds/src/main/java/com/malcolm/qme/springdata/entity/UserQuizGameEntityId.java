/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizGameEntityId.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Entity Id
 */
package com.malcolm.qme.springdata.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Malcolm
 */
@Embeddable
public class UserQuizGameEntityId implements java.io.Serializable {

	private static final long serialVersionUID = 7631787668803094636L;

	/**
	 * User Id
	 */
	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	/**
	 * Category Id
	 */
	@Column(name = "CAT_ID", nullable = false)
	private Long catId;

	/**
	 * Quiz Game Token
	 */
	@Column(name = "QUIZ_GAME_TOKEN", nullable = false)
	private Long quizGameToken;

	/**
	 * Public Constructor
	 */
	public UserQuizGameEntityId() {
	}

    /**
     * Public Constructor
     *
     * @param userId User Id
     * @param catId Category Id
     * @param quizGameToken Quiz Game Token
     */
	public UserQuizGameEntityId(Long userId, Long catId, Long quizGameToken) {
		this.userId = userId;
		this.catId = catId;
		this.quizGameToken = quizGameToken;
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
	 * @return the catId
	 */
	public Long getCatId() {
		return catId;
	}

	/**
	 * @param catId the catId to set
	 */
	public void setCatId(Long catId) {
		this.catId = catId;
	}

	/**
	 * @return the quizGameToken
	 */
	public Long getQuizGameToken() {
		return quizGameToken;
	}

	/**
	 * @param quizGameToken the quizGameToken to set
	 */
	public void setQuizGameToken(Long quizGameToken) {
		this.quizGameToken = quizGameToken;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((catId == null) ? 0 : catId.hashCode());
		result = (prime * result)
				+ ((quizGameToken == null) ? 0 : quizGameToken.hashCode());
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
		final UserQuizGameEntityId other = (UserQuizGameEntityId) obj;
		if (catId == null) {
			if (other.catId != null) {
				return false;
			}
		} else if (!catId.equals(other.catId)) {
			return false;
		}
		if (quizGameToken == null) {
			if (other.quizGameToken != null) {
				return false;
			}
		} else if (!quizGameToken.equals(other.quizGameToken)) {
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
		return "UserQuizGameEntityId [userId=" + userId + ", catId=" + catId
				+ ", quizGameToken=" + quizGameToken + "]";
	}

}
