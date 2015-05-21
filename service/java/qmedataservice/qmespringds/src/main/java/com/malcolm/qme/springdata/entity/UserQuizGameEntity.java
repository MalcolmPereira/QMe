/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizGameEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Game Entity
 */
package com.malcolm.qme.springdata.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "USER_QUIZ_GAME", catalog = "qme")
public class UserQuizGameEntity implements java.io.Serializable {

	private static final long serialVersionUID = 6369583277714554743L;

	/**
	 * UserQuizGameEntity Id
	 */
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false)),
		@AttributeOverride(name = "catId", column = @Column(name = "CAT_ID", nullable = false)),
		@AttributeOverride(name = "quizGameToken", column = @Column(name = "QUIZ_GAME_TOKEN", nullable = false)) })
	private UserQuizGameEntityId id;

	/**
	 * Start Date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE", nullable = false, length = 19)
	private Date startDate;

	/**
	 * End Date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE", length = 19)
	private Date endDate;

	/**
	 * User Score
	 */
	@Column(name = "USER_SCORE", nullable = false)
	private Integer userScore;

	/**
	 * Public Constructor
	 */
	public UserQuizGameEntity() {
	}

	/**
	 * @return the id
	 */
	public UserQuizGameEntityId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UserQuizGameEntityId id) {
		this.id = id;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the userScore
	 */
	public Integer getUserScore() {
		return userScore;
	}

	/**
	 * @param userScore the userScore to set
	 */
	public void setUserScore(Integer userScore) {
		this.userScore = userScore;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((endDate == null) ? 0 : endDate.hashCode());
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
		result = (prime * result)
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = (prime * result)
				+ ((userScore == null) ? 0 : userScore.hashCode());
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
		final UserQuizGameEntity other = (UserQuizGameEntity) obj;
		if (endDate == null) {
			if (other.endDate != null) {
				return false;
			}
		} else if (!endDate.equals(other.endDate)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (startDate == null) {
			if (other.startDate != null) {
				return false;
			}
		} else if (!startDate.equals(other.startDate)) {
			return false;
		}
		if (userScore == null) {
			if (other.userScore != null) {
				return false;
			}
		} else if (!userScore.equals(other.userScore)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserQuizGameEntity [id=" + id + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", userScore=" + userScore + "]";
	}

}
