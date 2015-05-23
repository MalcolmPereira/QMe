/**
 * Name      : com.malcolm.qme.core.domain.Question.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe Question Domain Class
 */
package com.malcolm.qme.core.domain;

import java.time.LocalDateTime;

/**
 * @author Malcolm
 */
public final class Question {
    /**
     * Question Id
     */
    private final Long questionID;

    /**
     * Category Id
     */
    private final Long categoryID;

    /**
     * Question Text
     */
    private final String questionText;

    /**
     * Question Answer
     */
    private final String answer;

    /**
     * Question Point
     */
    private final Integer questionPoint;
    
    /**
     * Question Likes
     */
    private final Long likes;

    /**
     * Question Create Date
     */
    private final LocalDateTime questionCreateDate;
    /**
     * Question Create User Id
     */
    private final Long  createUserID;

    /**
     * Question Update Date
     */
    private final LocalDateTime questionUpdateDate;
    /**
     * Update User Id
     */
    private final Long  updateUserID;
    
    
	/**
	 * Public Constructor
	 * 
	 * @param questionID Question ID
	 * @param categoryID Category ID
	 * @param questionText Question Text
	 * @param answer Answer for Question
	 * @param questionPoint Question Point (Score accumulated if the question is answered correctly)
	 * @param likes Question Likes
	 * @param questionCreateDate Question Create Date
	 * @param createUserID Question Create User
	 * @param questionUpdateDate Question Update Date
	 * @param updateUserID Question Update User
	 */
	public Question(Long questionID, Long categoryID, String questionText,
			String answer, Integer questionPoint, Long likes,
			LocalDateTime questionCreateDate, Long createUserID,
			LocalDateTime questionUpdateDate, Long updateUserID) {
		super();
		this.questionID = questionID;
		this.categoryID = categoryID;
		this.questionText = questionText;
		this.answer = answer;
		this.questionPoint = questionPoint;
		this.likes = likes;
		this.questionCreateDate = questionCreateDate;
		this.createUserID = createUserID;
		this.questionUpdateDate = questionUpdateDate;
		this.updateUserID = updateUserID;
	}

	/**
	 * Public Constructor
	 *
	 * @param categoryID Category ID
	 * @param questionText Question Text
	 * @param answer Answer for Question
	 * @param questionPoint Question Point (Score accumulated if the question is answered correctly)
	 * @param createUserID Question Create User
	 */
	public Question(Long categoryID, String questionText, String answer, Integer questionPoint,Long createUserID) {
		this.questionID = 0L;
		this.categoryID = categoryID;
		this.questionText = questionText;
		this.answer = answer;
		this.questionPoint = questionPoint;
		this.likes = 0L;
		this.questionCreateDate = LocalDateTime.now();
		this.createUserID = createUserID;
		this.questionUpdateDate = LocalDateTime.now();
		this.updateUserID = 0L;
	}

	/**
     * Public Constructor
     *
     * @param categoryID Category ID
     * @param questionText Question Text
     * @param answer Answer for Question
     * @param createUserID Question Create User
     */
    public Question(Long categoryID, String questionText, String answer, Long createUserID) {
        this.questionID = 0L;
        this.categoryID = categoryID;
        this.questionText = questionText;
        this.answer = answer;
        this.questionPoint = 1;
        this.likes = 0L;
        this.questionCreateDate = LocalDateTime.now();
        this.createUserID = createUserID;
        this.questionUpdateDate = LocalDateTime.now();
        this.updateUserID = 0L;
    }

	/**
	 * @return the questionID
	 */
	public Long getQuestionID() {
		return questionID;
	}

	/**
	 * @return the categoryID
	 */
	public Long getCategoryID() {
		return categoryID;
	}

	/**
	 * @return the questionText
	 */
	public String getQuestionText() {
		return questionText;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @return the questionPoint
	 */
	public Integer getQuestionPoint() {
		return questionPoint;
	}

	/**
	 * @return the likes
	 */
	public Long getLikes() {
		return likes;
	}

	/**
	 * @return the questionCreateDate
	 */
	public LocalDateTime getQuestionCreateDate() {
		return questionCreateDate;
	}

	/**
	 * @return the createUserID
	 */
	public Long getCreateUserID() {
		return createUserID;
	}

	/**
	 * @return the questionUpdateDate
	 */
	public LocalDateTime getQuestionUpdateDate() {
		return questionUpdateDate;
	}

	/**
	 * @return the updateUserID
	 */
	public Long getUpdateUserID() {
		return updateUserID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result
				+ ((categoryID == null) ? 0 : categoryID.hashCode());
		result = prime * result
				+ ((createUserID == null) ? 0 : createUserID.hashCode());
		result = prime * result + ((likes == null) ? 0 : likes.hashCode());
		result = prime
				* result
				+ ((questionCreateDate == null) ? 0 : questionCreateDate
						.hashCode());
		result = prime * result
				+ ((questionID == null) ? 0 : questionID.hashCode());
		result = prime * result
				+ ((questionPoint == null) ? 0 : questionPoint.hashCode());
		result = prime * result
				+ ((questionText == null) ? 0 : questionText.hashCode());
		result = prime
				* result
				+ ((questionUpdateDate == null) ? 0 : questionUpdateDate
						.hashCode());
		result = prime * result
				+ ((updateUserID == null) ? 0 : updateUserID.hashCode());
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
		Question other = (Question) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (categoryID == null) {
			if (other.categoryID != null)
				return false;
		} else if (!categoryID.equals(other.categoryID))
			return false;
		if (createUserID == null) {
			if (other.createUserID != null)
				return false;
		} else if (!createUserID.equals(other.createUserID))
			return false;
		if (likes == null) {
			if (other.likes != null)
				return false;
		} else if (!likes.equals(other.likes))
			return false;
		if (questionCreateDate == null) {
			if (other.questionCreateDate != null)
				return false;
		} else if (!questionCreateDate.equals(other.questionCreateDate))
			return false;
		if (questionID == null) {
			if (other.questionID != null)
				return false;
		} else if (!questionID.equals(other.questionID))
			return false;
		if (questionPoint == null) {
			if (other.questionPoint != null)
				return false;
		} else if (!questionPoint.equals(other.questionPoint))
			return false;
		if (questionText == null) {
			if (other.questionText != null)
				return false;
		} else if (!questionText.equals(other.questionText))
			return false;
		if (questionUpdateDate == null) {
			if (other.questionUpdateDate != null)
				return false;
		} else if (!questionUpdateDate.equals(other.questionUpdateDate))
			return false;
		if (updateUserID == null) {
			if (other.updateUserID != null)
				return false;
		} else if (!updateUserID.equals(other.updateUserID))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Question [questionID=" + questionID + ", categoryID="
				+ categoryID + ", questionText=" + questionText + ", answer="
				+ answer + ", questionPoint=" + questionPoint + ", likes="
				+ likes + ", questionCreateDate=" + questionCreateDate
				+ ", createUserID=" + createUserID + ", questionUpdateDate="
				+ questionUpdateDate + ", updateUserID=" + updateUserID + "]";
	}
}
