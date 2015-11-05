/**
 * Name      : com.malcolm.qme.springdata.entity.QuizQuestionEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Quiz Question Entity
 */
package com.malcolm.qme.springdata.entity;


import javax.persistence.*;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		QuizQuestionEntity that = (QuizQuestionEntity) o;
		return Objects.equals(quizQuestionId, that.quizQuestionId) &&
				Objects.equals(quizId, that.quizId) &&
				Objects.equals(questionId, that.questionId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(quizQuestionId, quizId, questionId);
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
