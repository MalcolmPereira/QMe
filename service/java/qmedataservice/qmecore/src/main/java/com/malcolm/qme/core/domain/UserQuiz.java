/**
 * Name      : com.malcolm.qme.core.domain.UserQuiz.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Domain Class
 */

package com.malcolm.qme.core.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Malcolm
 */
public final class UserQuiz {

    /**
     * User Quiz  Id
     */
    private final Long userQuizID;

    /**
     * User Id
     */
    private final Long userID;

    /**
     * Quiz Id
     */
    private final Long quizID;

    /**
     * Category Id
     */
    private final Long categoryID;

    /**
     * Quiz Start Date
     */
    private final LocalDateTime quizStartDate;

    /**
     * Quiz End Date
     */
    private final LocalDateTime quizEndDate;

    /**
     * User Quiz Unique Token
     */
    private final String userQuizToken;

    /**
     * Quiz User Id
     */
    private final Integer quizUserScore;
    
    /**
     * Quiz Max Score
     */
    private final Integer quizMaxScore;

    /**
     * Quiz Complete
     */
    private final Boolean quizComplete;

    /**
	 * Quiz
	 */
	private Quiz quiz;

    /**
     * Category
     */
	private Category category;

	/**
	 * Public Constructor
	 * 
	 * @param userQuizID User Quiz ID
	 * @param userID User ID
	 * @param quizID Quiz ID
	 * @param categoryID Category ID
	 * @param quizStartDate Quiz Start Date
	 * @param quizEndDate Quiz End Date
	 * @param userQuizToken Unique User Quiz Token
	 * @param quizUserScore Quiz User Score
	 * @param quizMaxScore Quiz Max Score
	 * @param quizComplete Quiz Complete - True Complete/False Incomplete
	 */
	public UserQuiz(Long userQuizID, Long userID, Long quizID, Long categoryID,
			LocalDateTime quizStartDate, LocalDateTime quizEndDate, String userQuizToken,
			Integer quizUserScore, Integer quizMaxScore, Boolean quizComplete) {
		super();
		this.userQuizID = userQuizID;
		this.userID = userID;
		this.quizID = quizID;
		this.categoryID = categoryID;
		this.quizStartDate = quizStartDate;
		this.quizEndDate = quizEndDate;
		this.userQuizToken = userQuizToken;
		this.quizUserScore = quizUserScore;
		this.quizMaxScore = quizMaxScore;
		this.quizComplete = quizComplete;
	}
    
	/**
     * Public Constructor
     *
     * @param userID User ID
     * @param quizID Quiz ID
     * @param categoryID Category ID
     * @param userQuizToken Unique User Quiz Token
     */
    public UserQuiz(Long userID, Long quizID, Long categoryID, Integer quizMaxScore, String userQuizToken) {
        this.userQuizID = 0L;
        this.userID = userID;
        this.quizID = quizID;
        this.categoryID = categoryID;
        this.quizStartDate = LocalDateTime.now();
        this.quizEndDate = null;
        this.userQuizToken = userQuizToken;
        this.quizUserScore = 0;
		this.quizMaxScore = quizMaxScore;
        this.quizComplete = Boolean.FALSE;
    }

	/**
	 * @return the userQuizID
	 */
	public Long getUserQuizID() {
		return userQuizID;
	}

	/**
	 * @return the userID
	 */
	public Long getUserID() {
		return userID;
	}

	/**
	 * @return the quizID
	 */
	public Long getQuizID() {
		return quizID;
	}

	/**
	 * @return the categoryID
	 */
	public Long getCategoryID() {
		return categoryID;
	}

	/**
	 * @return the quizStartDate
	 */
	public LocalDateTime getQuizStartDate() {
		return quizStartDate;
	}

	/**
	 * @return the quizEndDate
	 */
	public LocalDateTime getQuizEndDate() {
		return quizEndDate;
	}

	/**
	 * @return the userQuizToken
	 */
	public String getUserQuizToken() {
		return userQuizToken;
	}

	/**
	 * @return the quizUserScore
	 */
	public Integer getQuizUserScore() {
		return quizUserScore;
	}

	/**
	 * @return the quizMaxScore
	 */
	public Integer getQuizMaxScore() {
		return quizMaxScore;
	}

	/**
	 * @return the quizComplete
	 */
	public Boolean getQuizComplete() {
		return quizComplete;
	}

    /**
     * Get Quiz
     * @return
     */
    public Quiz getQuiz() {
        return quiz;
    }

    /**
     * Set Quiz
     * @param quiz
     */
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    /**
     * Get Category
     * @return
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Set Category
     * @param category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserQuiz userQuiz = (UserQuiz) o;
		return Objects.equals(userQuizID, userQuiz.userQuizID) &&
				Objects.equals(userID, userQuiz.userID) &&
				Objects.equals(quizID, userQuiz.quizID) &&
				Objects.equals(categoryID, userQuiz.categoryID);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userQuizID, userID, quizID, categoryID);
	}

	/* (non-Javadoc)
             * @see java.lang.Object#toString()
             */
	@Override
	public String toString() {
		return "UserQuiz [userQuizID=" + userQuizID + ", userID=" + userID
				+ ", quizID=" + quizID + ", categoryID=" + categoryID
				+ ", quizStartDate=" + quizStartDate + ", quizEndDate="
				+ quizEndDate + ", userQuizToken=" + userQuizToken
				+ ", quizUserScore=" + quizUserScore + ", quizMaxScore="
				+ quizMaxScore + ", quizComplete=" + quizComplete + "]";
	}

}
