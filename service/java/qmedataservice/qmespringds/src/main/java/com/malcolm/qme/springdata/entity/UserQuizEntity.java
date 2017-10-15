/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Quiz Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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
	@Column(name = "QUIZ_START_DATE", nullable = false, length = 19)
	private LocalDateTime quizStartDate;

	/**
	 * Quiz End Date
	 */
	@Column(name = "QUIZ_END_DATE", length = 19)
	private LocalDateTime quizEndDate;

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

	@ManyToOne
	@JoinColumn(name = "QUIZ_ID", referencedColumnName = "QUIZ_ID", insertable = false, updatable = false)
	private QuizEntity quiz;


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
	 */
	public UserQuizEntity(Long userId, Long quizId, Long catId,LocalDateTime quizStartDate, Integer quizUserScore, Integer quizMaxScore) {
		this.userId = userId;
		this.quizId = quizId;
		this.catId = catId;
		this.quizStartDate = quizStartDate;
		this.quizUserScore = quizUserScore;
		this.quizMaxScore = quizMaxScore;
	}

	/**
	 * Public Constructor
	 *
	 * @param userId User ID
	 * @param quizId Quiz ID
	 * @param catId Category ID
	 * @param quizStartDate Quiz Start Date
	 * @param quizEndDate Quiz End Date
	 * @param quizUserScore Quiz User Score
	 * @param quizMaxScore  Quiz Max Score
	 * @param quizToken  Quiz Token
	 */
	public UserQuizEntity(Long userId, Long quizId, Long catId, LocalDateTime quizStartDate, LocalDateTime quizEndDate, Integer quizUserScore, Integer quizMaxScore, String quizToken) {
		this.userId = userId;
		this.quizId = quizId;
		this.catId = catId;
		this.quizStartDate = quizStartDate;
		this.quizEndDate = quizEndDate;
		this.quizUserScore = quizUserScore;
		this.quizMaxScore = quizMaxScore;
		this.quizToken = quizToken;
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
	public LocalDateTime getQuizStartDate() {
		return quizStartDate;
	}

	/**
	 * @param quizStartDate the quizStartDate to set
	 */
	public void setQuizStartDate(LocalDateTime quizStartDate) {
		this.quizStartDate = quizStartDate;
	}

	/**
	 * @return the quizEndDate
	 */
	public LocalDateTime getQuizEndDate() {
		return quizEndDate;
	}

	/**
	 * @param quizEndDate the quizEndDate to set
	 */
	public void setQuizEndDate(LocalDateTime quizEndDate) {
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
     * Get Quiz
     * @return
     */
    public QuizEntity getQuiz() {
        return quiz;
    }

    /**
     * Set Quiz
     * @param quiz
     */
    public void setQuiz(QuizEntity quiz) {
        this.quiz = quiz;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserQuizEntity that = (UserQuizEntity) o;
		return Objects.equals(userQuizId, that.userQuizId) &&
				Objects.equals(userId, that.userId) &&
				Objects.equals(quizId, that.quizId) &&
				Objects.equals(catId, that.catId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userQuizId, userId, quizId, catId);
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
				+ ", quizMaxScore=" + quizMaxScore + ", quizToken=" + quizToken;
	}

}
