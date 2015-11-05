/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizGameEntityId.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Entity Id
 */
package com.malcolm.qme.springdata.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserQuizGameEntityId that = (UserQuizGameEntityId) o;
		return Objects.equals(userId, that.userId) &&
				Objects.equals(catId, that.catId) &&
				Objects.equals(quizGameToken, that.quizGameToken);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, catId, quizGameToken);
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
