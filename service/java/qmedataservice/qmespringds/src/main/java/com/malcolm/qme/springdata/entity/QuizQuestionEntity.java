/**
 * Name      : com.malcolm.qme.springdata.entity.QuizQuestionEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Quiz Question Entity
 */
package com.malcolm.qme.springdata.entity;


import javax.persistence.*;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "QUIZ_QUESTION", catalog = "qme")
public class QuizQuestionEntity implements java.io.Serializable {

	private static final long serialVersionUID = 2739649231157126325L;

	/**
	 * Quiz Question ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUIZ_QUESTION_ID", unique = true, nullable = false)
	private Long quizQuestionId;

	/**
	 * Quiz ID
	 */
	@Column(name = "QUIZ_ID", nullable = false)
	private Long quizId;

	/**
	 * Question ID
	 */
	@Column(name = "QUESTION_ID", nullable = false)
	private Long questionId;

	/**
	 * Public Constructor
	 */
	public QuizQuestionEntity() {
	}

	/**
	 * Public Constructor
	 *
	 * @param quizId Quiz ID
	 * @param questionId Question ID
	 */
	public QuizQuestionEntity(Long quizId, Long questionId) {
		this.quizId = quizId;
		this.questionId = questionId;
	}

	/**
	 * @return the quizQuestionId
	 */
	public Long getQuizQuestionId() {
		return quizQuestionId;
	}

	/**
	 * @param quizQuestionId the quizQuestionId to set
	 */
	public void setQuizQuestionId(Long quizQuestionId) {
		this.quizQuestionId = quizQuestionId;
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
		result = (prime * result) + ((quizId == null) ? 0 : quizId.hashCode());
		result = (prime * result)
				+ ((quizQuestionId == null) ? 0 : quizQuestionId.hashCode());
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
		final QuizQuestionEntity other = (QuizQuestionEntity) obj;
		if (questionId == null) {
			if (other.questionId != null) {
				return false;
			}
		} else if (!questionId.equals(other.questionId)) {
			return false;
		}
		if (quizId == null) {
			if (other.quizId != null) {
				return false;
			}
		} else if (!quizId.equals(other.quizId)) {
			return false;
		}
		if (quizQuestionId == null) {
			if (other.quizQuestionId != null) {
				return false;
			}
		} else if (!quizQuestionId.equals(other.quizQuestionId)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuizQuestionEntity [quizQuestionId=" + quizQuestionId
				+ ", quizId=" + quizId + ", questionId=" + questionId + "]";
	}

}
