/**
 * Name      : com.malcolm.qme.springdata.entity.QuizEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Quiz Entity
 */
package com.malcolm.qme.springdata.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "QUIZ", catalog = "qme", uniqueConstraints = @UniqueConstraint(columnNames = "QUIZ_NAME"))
public class QuizEntity implements java.io.Serializable {

	private static final long serialVersionUID = -6004675333716243501L;

	/**
	 * Quiz Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUIZ_ID", unique = true, nullable = false)
	private Long quizId;

	/**
	 * Quiz Name
	 */
	@Column(name = "QUIZ_NAME", unique = true, nullable = false)
	private String quizName;

	/**
	 * Quiz Desc
	 */
	@Column(name = "QUIZ_DESC")
	private String quizDesc;

	/**
	 * Category ID
	 */
	@Column(name = "CAT_ID", nullable = false)
	private Long catId;

	/**
	 * Quiz Hits
	 */
	@Column(name = "QUIZ_HITS")
	private Long quizHits;

	/**
	 * Quiz Likes
	 */
	@Column(name = "QUIZ_LIKES", nullable = false)
	private Long quizLikes;

	/**
	 * Quiz Max Attempts
	 */
	@Column(name = "MAX_ATTEMPTS", nullable = false)
	private Integer maxAttempts;

	/**
	 * Quiz Create Date
	 */
	@Column(name = "QUIZ_CREATE_DATE", nullable = false, length = 19)
	private LocalDateTime quizCreateDate;

	/**
	 * Quiz Create User
	 */
	@Column(name = "QUIZ_CREATE_USER", nullable = false)
	private Long quizCreateUser;

	/**
	 * Quiz Update Date
	 */
	@Column(name = "QUIZ_UPDATE_DATE", nullable = false, length = 19)
	private LocalDateTime quizUpdateDate;

	/**
	 * Quiz Update User
	 */
	@Column(name = "QUIZ_UPDATE_USER", nullable = false)
	private Long quizUpdateUser;

	/**
	 * public Constructor
	 */
	public QuizEntity() {
	}

	/**
	 * public Constructor
	 *
	 * @param quizName Quiz Name
	 * @param quizDesc Quiz Desc
	 * @param catId Category ID
	 * @param quizHits Quiz Hits
	 * @param quizLikes Quiz Likes
	 * @param maxAttempts Quiz Max Attempts
	 * @param quizCreateDate Quiz Create Date
	 * @param quizCreateUser Quiz Create User
	 * @param quizUpdateDate Quiz Update Date
	 * @param quizUpdateUser Quiz Update User
	 */
	public QuizEntity(String quizName, String quizDesc, Long catId,
			Long quizHits, Long quizLikes, Integer maxAttempts,
			LocalDateTime quizCreateDate, Long quizCreateUser, LocalDateTime quizUpdateDate,
			Long quizUpdateUser) {
		this.quizName = quizName;
		this.quizDesc = quizDesc;
		this.catId = catId;
		this.quizHits = quizHits;
		this.quizLikes = quizLikes;
		this.maxAttempts = maxAttempts;
		this.quizCreateDate = quizCreateDate;
		this.quizCreateUser = quizCreateUser;
		this.quizUpdateDate = quizUpdateDate;
		this.quizUpdateUser = quizUpdateUser;
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
	 * @return the quizName
	 */
	public String getQuizName() {
		return quizName;
	}

	/**
	 * @param quizName the quizName to set
	 */
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	/**
	 * @return the quizDesc
	 */
	public String getQuizDesc() {
		return quizDesc;
	}

	/**
	 * @param quizDesc the quizDesc to set
	 */
	public void setQuizDesc(String quizDesc) {
		this.quizDesc = quizDesc;
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
	 * @return the quizHits
	 */
	public Long getQuizHits() {
		return quizHits;
	}

	/**
	 * @param quizHits the quizHits to set
	 */
	public void setQuizHits(Long quizHits) {
		this.quizHits = quizHits;
	}

	/**
	 * @return the quizLikes
	 */
	public Long getQuizLikes() {
		return quizLikes;
	}

	/**
	 * @param quizLikes the quizLikes to set
	 */
	public void setQuizLikes(Long quizLikes) {
		this.quizLikes = quizLikes;
	}

	/**
	 * @return the maxAttempts
	 */
	public Integer getMaxAttempts() {
		return maxAttempts;
	}

	/**
	 * @param maxAttempts the maxAttempts to set
	 */
	public void setMaxAttempts(Integer maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	/**
	 * @return the quizCreateDate
	 */
	public LocalDateTime getQuizCreateDate() {
		return quizCreateDate;
	}

	/**
	 * @param quizCreateDate the quizCreateDate to set
	 */
	public void setQuizCreateDate(LocalDateTime quizCreateDate) {
		this.quizCreateDate = quizCreateDate;
	}

	/**
	 * @return the quizCreateUser
	 */
	public Long getQuizCreateUser() {
		return quizCreateUser;
	}

	/**
	 * @param quizCreateUser the quizCreateUser to set
	 */
	public void setQuizCreateUser(Long quizCreateUser) {
		this.quizCreateUser = quizCreateUser;
	}

	/**
	 * @return the quizUpdateDate
	 */
	public LocalDateTime getQuizUpdateDate() {
		return quizUpdateDate;
	}

	/**
	 * @param quizUpdateDate the quizUpdateDate to set
	 */
	public void setQuizUpdateDate(LocalDateTime quizUpdateDate) {
		this.quizUpdateDate = quizUpdateDate;
	}

	/**
	 * @return the quizUpdateUser
	 */
	public Long getQuizUpdateUser() {
		return quizUpdateUser;
	}

	/**
	 * @param quizUpdateUser the quizUpdateUser to set
	 */
	public void setQuizUpdateUser(Long quizUpdateUser) {
		this.quizUpdateUser = quizUpdateUser;
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
				+ ((maxAttempts == null) ? 0 : maxAttempts.hashCode());
		result = (prime * result)
				+ ((quizCreateDate == null) ? 0 : quizCreateDate.hashCode());
		result = (prime * result)
				+ ((quizCreateUser == null) ? 0 : quizCreateUser.hashCode());
		result = (prime * result)
				+ ((quizDesc == null) ? 0 : quizDesc.hashCode());
		result = (prime * result)
				+ ((quizHits == null) ? 0 : quizHits.hashCode());
		result = (prime * result) + ((quizId == null) ? 0 : quizId.hashCode());
		result = (prime * result)
				+ ((quizLikes == null) ? 0 : quizLikes.hashCode());
		result = (prime * result)
				+ ((quizName == null) ? 0 : quizName.hashCode());
		result = (prime * result)
				+ ((quizUpdateDate == null) ? 0 : quizUpdateDate.hashCode());
		result = (prime * result)
				+ ((quizUpdateUser == null) ? 0 : quizUpdateUser.hashCode());
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
		final QuizEntity other = (QuizEntity) obj;
		if (catId == null) {
			if (other.catId != null) {
				return false;
			}
		} else if (!catId.equals(other.catId)) {
			return false;
		}
		if (maxAttempts == null) {
			if (other.maxAttempts != null) {
				return false;
			}
		} else if (!maxAttempts.equals(other.maxAttempts)) {
			return false;
		}
		if (quizCreateDate == null) {
			if (other.quizCreateDate != null) {
				return false;
			}
		} else if (!quizCreateDate.equals(other.quizCreateDate)) {
			return false;
		}
		if (quizCreateUser == null) {
			if (other.quizCreateUser != null) {
				return false;
			}
		} else if (!quizCreateUser.equals(other.quizCreateUser)) {
			return false;
		}
		if (quizDesc == null) {
			if (other.quizDesc != null) {
				return false;
			}
		} else if (!quizDesc.equals(other.quizDesc)) {
			return false;
		}
		if (quizHits == null) {
			if (other.quizHits != null) {
				return false;
			}
		} else if (!quizHits.equals(other.quizHits)) {
			return false;
		}
		if (quizId == null) {
			if (other.quizId != null) {
				return false;
			}
		} else if (!quizId.equals(other.quizId)) {
			return false;
		}
		if (quizLikes == null) {
			if (other.quizLikes != null) {
				return false;
			}
		} else if (!quizLikes.equals(other.quizLikes)) {
			return false;
		}
		if (quizName == null) {
			if (other.quizName != null) {
				return false;
			}
		} else if (!quizName.equals(other.quizName)) {
			return false;
		}
		if (quizUpdateDate == null) {
			if (other.quizUpdateDate != null) {
				return false;
			}
		} else if (!quizUpdateDate.equals(other.quizUpdateDate)) {
			return false;
		}
		if (quizUpdateUser == null) {
			if (other.quizUpdateUser != null) {
				return false;
			}
		} else if (!quizUpdateUser.equals(other.quizUpdateUser)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuizEntity [quizId=" + quizId + ", quizName=" + quizName
				+ ", quizDesc=" + quizDesc + ", catId=" + catId + ", quizHits="
				+ quizHits + ", quizLikes=" + quizLikes + ", maxAttempts="
				+ maxAttempts + ", quizCreateDate=" + quizCreateDate
				+ ", quizCreateUser=" + quizCreateUser + ", quizUpdateDate="
				+ quizUpdateDate + ", quizUpdateUser=" + quizUpdateUser + "]";
	}

}
