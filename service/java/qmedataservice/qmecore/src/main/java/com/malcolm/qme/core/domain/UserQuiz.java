/**
 * Name      : com.malcolm.qme.core.domain.UserQuiz.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Domain Class
 */

package com.malcolm.qme.core.domain;

import java.util.Date;

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
    private final Date quizStartDate;

    /**
     * Quiz End Date
     */
    private final Date quizEndDate;

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
     * Correct
     */
    private final Boolean quizComplete;

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
			Date quizStartDate, Date quizEndDate, String userQuizToken,
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
        this.quizStartDate = null;
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
	public Date getQuizStartDate() {
		return quizStartDate;
	}

	/**
	 * @return the quizEndDate
	 */
	public Date getQuizEndDate() {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryID == null) ? 0 : categoryID.hashCode());
		result = prime * result
				+ ((quizComplete == null) ? 0 : quizComplete.hashCode());
		result = prime * result
				+ ((quizEndDate == null) ? 0 : quizEndDate.hashCode());
		result = prime * result + ((quizID == null) ? 0 : quizID.hashCode());
		result = prime * result
				+ ((quizMaxScore == null) ? 0 : quizMaxScore.hashCode());
		result = prime * result
				+ ((quizStartDate == null) ? 0 : quizStartDate.hashCode());
		result = prime * result
				+ ((quizUserScore == null) ? 0 : quizUserScore.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		result = prime * result
				+ ((userQuizID == null) ? 0 : userQuizID.hashCode());
		result = prime * result
				+ ((userQuizToken == null) ? 0 : userQuizToken.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserQuiz other = (UserQuiz) obj;
		if (categoryID == null) {
			if (other.categoryID != null)
				return false;
		} else if (!categoryID.equals(other.categoryID))
			return false;
		if (quizComplete == null) {
			if (other.quizComplete != null)
				return false;
		} else if (!quizComplete.equals(other.quizComplete))
			return false;
		if (quizEndDate == null) {
			if (other.quizEndDate != null)
				return false;
		} else if (!quizEndDate.equals(other.quizEndDate))
			return false;
		if (quizID == null) {
			if (other.quizID != null)
				return false;
		} else if (!quizID.equals(other.quizID))
			return false;
		if (quizMaxScore == null) {
			if (other.quizMaxScore != null)
				return false;
		} else if (!quizMaxScore.equals(other.quizMaxScore))
			return false;
		if (quizStartDate == null) {
			if (other.quizStartDate != null)
				return false;
		} else if (!quizStartDate.equals(other.quizStartDate))
			return false;
		if (quizUserScore == null) {
			if (other.quizUserScore != null)
				return false;
		} else if (!quizUserScore.equals(other.quizUserScore))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		if (userQuizID == null) {
			if (other.userQuizID != null)
				return false;
		} else if (!userQuizID.equals(other.userQuizID))
			return false;
		if (userQuizToken == null) {
			if (other.userQuizToken != null)
				return false;
		} else if (!userQuizToken.equals(other.userQuizToken))
			return false;
		return true;
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
