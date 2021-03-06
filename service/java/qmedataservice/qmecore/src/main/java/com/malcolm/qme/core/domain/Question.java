/**
 * Name      : com.malcolm.qme.core.domain.Question.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe Question Domain Class
 */
package com.malcolm.qme.core.domain;

import java.time.LocalDateTime;
import java.util.Objects;

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
     * Create User
     */
    private User createUser;
    /**
     * Update User
     */
    private User updateUser;
    /**
     * Question Category
     */
    private Category category;
    
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

    /**
     * Update User Id
     */
    private final Long  updateUserID;

    /**
     * Get Create User
     * @return Create User
     */
    public User getCreateUser() {
        return createUser;
    }

    /**
     * Set Create User
     * @param createUser Create User
     */
    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    /**
     * Get Update User
     * @return Update User
     */
    public User getUpdateUser() {
        return updateUser;
    }

    /**
     * Set Update User
     * @param updateUser Update User
     */
    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * Get Category
     * @return Category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Set Category
     * @param category Category to update
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(questionID, question.questionID) &&
                Objects.equals(categoryID, question.categoryID) &&
                Objects.equals(questionText, question.questionText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionID, categoryID, questionText);
    }

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
