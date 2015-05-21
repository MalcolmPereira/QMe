/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Entity
 */
package com.malcolm.qme.springdata.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "USER_QUIZ", catalog = "qme")
public class UserQuizEntity implements java.io.Serializable {

	private static final long serialVersionUID = -7214565628037365166L;

	/**
	 * User Quiz ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_QUIZ_ID", unique = true, nullable = false)
	private Long userQuizId;

	/**
	 * User ID
	 */
	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	/**
	 * Quiz Id
	 */
	@Column(name = "QUIZ_ID", nullable = false)
	private Long quizId;

	/**
	 * Category ID
	 */
	@Column(name = "CAT_ID", nullable = false)
	private Long catId;

	/**
	 * Quiz Start Date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUIZ_START_DATE", nullable = false, length = 19)
	private Date quizStartDate;

	/**
	 * Quiz End Date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUIZ_END_DATE", length = 19)
	private Date quizEndDate;

	/**
	 * Quiz User Score
	 */
	@Column(name = "QUIZ_USER_SCORE", nullable = false)
	private Integer quizUserScore;

	/**
	 * Quiz Max Score
	 */
	@Column(name = "QUIZ_MAX_SCORE", nullable = false)
	private Integer quizMaxScore;

	/**
	 * Quiz Token
	 */
	@Column(name = "QUIZ_TOKEN", length = 256)
	private String quizToken;

	/**
	 * Quiz Complete
	 */
	@Column(name = "QUIZ_COMPLETE", nullable = false)
	private byte quizComplete;

	/**
	 * Public Constructor
	 */
	public UserQuizEntity() {
	}

	/**
	 * Public Constructor
	 *
	 * @param userId User ID
	 * @param quizId Quiz ID
	 * @param catId Category ID
	 * @param quizStartDate Quiz Start Date
	 * @param quizUserScore Quiz User Score
	 * @param quizMaxScore Quiz Max Score
	 * @param quizComplete Quiz Complete 1 - Complete/ 0 - Incomplete
	 */
	public UserQuizEntity(Long userId, Long quizId, Long catId,
			Date quizStartDate, Integer quizUserScore, Integer quizMaxScore,
			byte quizComplete) {
		this.userId = userId;
		this.quizId = quizId;
		this.catId = catId;
		this.quizStartDate = quizStartDate;
		this.quizUserScore = quizUserScore;
		this.quizMaxScore = quizMaxScore;
		this.quizComplete = quizComplete;
	}



	/**
	 * @return the userQuizId
	 */
	public Long getUserQuizId() {
		return userQuizId;
	}

	/**
	 * @param userQuizId the userQuizId to set
	 */
	public void setUserQuizId(Long userQuizId) {
		this.userQuizId = userQuizId;
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
	 * @return the quizStartDate
	 */
	public Date getQuizStartDate() {
		return quizStartDate;
	}

	/**
	 * @param quizStartDate the quizStartDate to set
	 */
	public void setQuizStartDate(Date quizStartDate) {
		this.quizStartDate = quizStartDate;
	}

	/**
	 * @return the quizEndDate
	 */
	public Date getQuizEndDate() {
		return quizEndDate;
	}

	/**
	 * @param quizEndDate the quizEndDate to set
	 */
	public void setQuizEndDate(Date quizEndDate) {
		this.quizEndDate = quizEndDate;
	}

	/**
	 * @return the quizUserScore
	 */
	public Integer getQuizUserScore() {
		return quizUserScore;
	}

	/**
	 * @param quizUserScore the quizUserScore to set
	 */
	public void setQuizUserScore(Integer quizUserScore) {
		this.quizUserScore = quizUserScore;
	}

	/**
	 * @return the quizMaxScore
	 */
	public Integer getQuizMaxScore() {
		return quizMaxScore;
	}

	/**
	 * @param quizMaxScore the quizMaxScore to set
	 */
	public void setQuizMaxScore(Integer quizMaxScore) {
		this.quizMaxScore = quizMaxScore;
	}

	/**
	 * @return the quizToken
	 */
	public String getQuizToken() {
		return quizToken;
	}

	/**
	 * @param quizToken the quizToken to set
	 */
	public void setQuizToken(String quizToken) {
		this.quizToken = quizToken;
	}

	/**
	 * @return the quizComplete
	 */
	public byte getQuizComplete() {
		return quizComplete;
	}

	/**
	 * @param quizComplete the quizComplete to set
	 */
	public void setQuizComplete(byte quizComplete) {
		this.quizComplete = quizComplete;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((catId == null) ? 0 : catId.hashCode());
		result = (prime * result) + quizComplete;
		result = (prime * result)
				+ ((quizEndDate == null) ? 0 : quizEndDate.hashCode());
		result = (prime * result) + ((quizId == null) ? 0 : quizId.hashCode());
		result = (prime * result)
				+ ((quizMaxScore == null) ? 0 : quizMaxScore.hashCode());
		result = (prime * result)
				+ ((quizStartDate == null) ? 0 : quizStartDate.hashCode());
		result = (prime * result)
				+ ((quizToken == null) ? 0 : quizToken.hashCode());
		result = (prime * result)
				+ ((quizUserScore == null) ? 0 : quizUserScore.hashCode());
		result = (prime * result) + ((userId == null) ? 0 : userId.hashCode());
		result = (prime * result)
				+ ((userQuizId == null) ? 0 : userQuizId.hashCode());
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
		final UserQuizEntity other = (UserQuizEntity) obj;
		if (catId == null) {
			if (other.catId != null) {
				return false;
			}
		} else if (!catId.equals(other.catId)) {
			return false;
		}
		if (quizComplete != other.quizComplete) {
			return false;
		}
		if (quizEndDate == null) {
			if (other.quizEndDate != null) {
				return false;
			}
		} else if (!quizEndDate.equals(other.quizEndDate)) {
			return false;
		}
		if (quizId == null) {
			if (other.quizId != null) {
				return false;
			}
		} else if (!quizId.equals(other.quizId)) {
			return false;
		}
		if (quizMaxScore == null) {
			if (other.quizMaxScore != null) {
				return false;
			}
		} else if (!quizMaxScore.equals(other.quizMaxScore)) {
			return false;
		}
		if (quizStartDate == null) {
			if (other.quizStartDate != null) {
				return false;
			}
		} else if (!quizStartDate.equals(other.quizStartDate)) {
			return false;
		}
		if (quizToken == null) {
			if (other.quizToken != null) {
				return false;
			}
		} else if (!quizToken.equals(other.quizToken)) {
			return false;
		}
		if (quizUserScore == null) {
			if (other.quizUserScore != null) {
				return false;
			}
		} else if (!quizUserScore.equals(other.quizUserScore)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (userQuizId == null) {
			if (other.userQuizId != null) {
				return false;
			}
		} else if (!userQuizId.equals(other.userQuizId)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserQuizEntity [userQuizId=" + userQuizId + ", userId="
				+ userId + ", quizId=" + quizId + ", catId=" + catId
				+ ", quizStartDate=" + quizStartDate + ", quizEndDate="
				+ quizEndDate + ", quizUserScore=" + quizUserScore
				+ ", quizMaxScore=" + quizMaxScore + ", quizToken=" + quizToken
				+ ", quizComplete=" + quizComplete + "]";
	}

}
