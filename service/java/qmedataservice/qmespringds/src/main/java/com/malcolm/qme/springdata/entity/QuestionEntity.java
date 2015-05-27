/**
 * Name      : com.malcolm.qme.springdata.entity.QuestionEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Question Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "QUESTION", catalog = "qme")
public class QuestionEntity implements java.io.Serializable {

	private static final long serialVersionUID = 6712594174002258631L;

	/**
	 * Question ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUESTION_ID", unique = true, nullable = false)
	private Long questionId;

	/**
	 * Category ID
	 */
	@Column(name = "CAT_ID", nullable = false)
	private Long catId;

	/**
	 * Question Text
	 */
	@Column(name = "QUESTION_TEXT", nullable = false)
	private String questionText;

	/**
	 * Question Answer
	 */
	@Column(name = "QUESTION_ANSWER")
	private String questionAnswer;

	/**
	 * Question Point
	 */
	@Column(name = "QUESTION_POINT")
	private Integer questionPoint;

	/**
	 * Question Likes
	 */
	@Column(name = "QUESTION_LIKES", nullable = false)
	private Long questionLikes;

	/**
	 * Question Create Date
	 */
	@Column(name = "QUESTION_CREATE_DATE", nullable = false, length = 19)
	private LocalDateTime questionCreateDate;

	/**
	 * Question Create User
	 */
	@Column(name = "QUESTION_CREATE_USER", nullable = false)
	private Long questionCreateUser;

	/**
	 * Question Update Date
	 */
	@Column(name = "QUESTION_UPDATE_DATE", nullable = false, length = 19)
	private LocalDateTime questionUpdateDate;

	/**
	 * Question Update User
	 */
	@Column(name = "QUESTION_UPDATE_USER", nullable = false)
	private Long questionUpdateUser;

	/**
	 * Public Constructor
	 */
	public QuestionEntity() {
	}

	/**
	 * Public Constructor
	 *
	 * @param catId Category ID
	 * @param questionText Question Text
	 * @param questionAnswer Question Answer
	 * @param questionPoint Question Point
	 * @param questionLikes Question Likes
	 * @param questionCreateDate Question Create Date
	 * @param questionCreateUser Question Create User
	 * @param questionUpdateDate Question Update Date
	 * @param questionUpdateUser Question Update User
	 */
	public QuestionEntity(Long catId, String questionText,
			String questionAnswer, Integer questionPoint, Long questionLikes,
			LocalDateTime questionCreateDate, Long questionCreateUser,
			LocalDateTime questionUpdateDate, Long questionUpdateUser) {
		this.catId = catId;
		this.questionText = questionText;
		this.questionAnswer = questionAnswer;
		this.questionPoint = questionPoint;
		this.questionLikes = questionLikes;
		this.questionCreateDate = questionCreateDate;
		this.questionCreateUser = questionCreateUser;
		this.questionUpdateDate = questionUpdateDate;
		this.questionUpdateUser = questionUpdateUser;
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
	 * @return the questionText
	 */
	public String getQuestionText() {
		return questionText;
	}

	/**
	 * @param questionText the questionText to set
	 */
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	/**
	 * @return the questionAnswer
	 */
	public String getQuestionAnswer() {
		return questionAnswer;
	}

	/**
	 * @param questionAnswer the questionAnswer to set
	 */
	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	/**
	 * @return the questionPoint
	 */
	public Integer getQuestionPoint() {
		return questionPoint;
	}

	/**
	 * @param questionPoint the questionPoint to set
	 */
	public void setQuestionPoint(Integer questionPoint) {
		this.questionPoint = questionPoint;
	}

	/**
	 * @return the questionLikes
	 */
	public Long getQuestionLikes() {
		return questionLikes;
	}

	/**
	 * @param questionLikes the questionLikes to set
	 */
	public void setQuestionLikes(Long questionLikes) {
		this.questionLikes = questionLikes;
	}

	/**
	 * @return the questionCreateDate
	 */
	public LocalDateTime getQuestionCreateDate() {
		return questionCreateDate;
	}

	/**
	 * @param questionCreateDate the questionCreateDate to set
	 */
	public void setQuestionCreateDate(LocalDateTime questionCreateDate) {
		this.questionCreateDate = questionCreateDate;
	}

	/**
	 * @return the questionCreateUser
	 */
	public Long getQuestionCreateUser() {
		return questionCreateUser;
	}

	/**
	 * @param questionCreateUser the questionCreateUser to set
	 */
	public void setQuestionCreateUser(Long questionCreateUser) {
		this.questionCreateUser = questionCreateUser;
	}

	/**
	 * @return the questionUpdateDate
	 */
	public LocalDateTime getQuestionUpdateDate() {
		return questionUpdateDate;
	}

	/**
	 * @param questionUpdateDate the questionUpdateDate to set
	 */
	public void setQuestionUpdateDate(LocalDateTime questionUpdateDate) {
		this.questionUpdateDate = questionUpdateDate;
	}

	/**
	 * @return the questionUpdateUser
	 */
	public Long getQuestionUpdateUser() {
		return questionUpdateUser;
	}

	/**
	 * @param questionUpdateUser the questionUpdateUser to set
	 */
	public void setQuestionUpdateUser(Long questionUpdateUser) {
		this.questionUpdateUser = questionUpdateUser;
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
				+ ((questionAnswer == null) ? 0 : questionAnswer.hashCode());
		result = (prime
				* result)
				+ ((questionCreateDate == null) ? 0 : questionCreateDate
						.hashCode());
		result = (prime
				* result)
				+ ((questionCreateUser == null) ? 0 : questionCreateUser
						.hashCode());
		result = (prime * result)
				+ ((questionId == null) ? 0 : questionId.hashCode());
		result = (prime * result)
				+ ((questionLikes == null) ? 0 : questionLikes.hashCode());
		result = (prime * result)
				+ ((questionPoint == null) ? 0 : questionPoint.hashCode());
		result = (prime * result)
				+ ((questionText == null) ? 0 : questionText.hashCode());
		result = (prime
				* result)
				+ ((questionUpdateDate == null) ? 0 : questionUpdateDate
						.hashCode());
		result = (prime
				* result)
				+ ((questionUpdateUser == null) ? 0 : questionUpdateUser
						.hashCode());
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
		final QuestionEntity other = (QuestionEntity) obj;
		if (catId == null) {
			if (other.catId != null) {
				return false;
			}
		} else if (!catId.equals(other.catId)) {
			return false;
		}
		if (questionAnswer == null) {
			if (other.questionAnswer != null) {
				return false;
			}
		} else if (!questionAnswer.equals(other.questionAnswer)) {
			return false;
		}
		if (questionCreateDate == null) {
			if (other.questionCreateDate != null) {
				return false;
			}
		} else if (!questionCreateDate.equals(other.questionCreateDate)) {
			return false;
		}
		if (questionCreateUser == null) {
			if (other.questionCreateUser != null) {
				return false;
			}
		} else if (!questionCreateUser.equals(other.questionCreateUser)) {
			return false;
		}
		if (questionId == null) {
			if (other.questionId != null) {
				return false;
			}
		} else if (!questionId.equals(other.questionId)) {
			return false;
		}
		if (questionLikes == null) {
			if (other.questionLikes != null) {
				return false;
			}
		} else if (!questionLikes.equals(other.questionLikes)) {
			return false;
		}
		if (questionPoint == null) {
			if (other.questionPoint != null) {
				return false;
			}
		} else if (!questionPoint.equals(other.questionPoint)) {
			return false;
		}
		if (questionText == null) {
			if (other.questionText != null) {
				return false;
			}
		} else if (!questionText.equals(other.questionText)) {
			return false;
		}
		if (questionUpdateDate == null) {
			if (other.questionUpdateDate != null) {
				return false;
			}
		} else if (!questionUpdateDate.equals(other.questionUpdateDate)) {
			return false;
		}
		if (questionUpdateUser == null) {
			if (other.questionUpdateUser != null) {
				return false;
			}
		} else if (!questionUpdateUser.equals(other.questionUpdateUser)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuestionEntity [questionId=" + questionId + ", catId=" + catId
				+ ", questionText=" + questionText + ", questionAnswer="
				+ questionAnswer + ", questionPoint=" + questionPoint
				+ ", questionLikes=" + questionLikes + ", questionCreateDate="
				+ questionCreateDate + ", questionCreateUser="
				+ questionCreateUser + ", questionUpdateDate="
				+ questionUpdateDate + ", questionUpdateUser="
				+ questionUpdateUser + "]";
	}

}
