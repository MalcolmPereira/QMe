/**
 * Name      : com.malcolm.qme.rest.model.QMeQuestion.java
 * Date      : 1/2/16
 * Developer : Malcolm
 * Purpose   : QMeQuestion Model for REST Service Controller
 */
package com.malcolm.qme.rest.model;

import java.time.LocalDateTime;

/**
 * QMeQuestion Model for REST Service Controller
 *
 * @author Malcolm
 */
public class QMeQuestion extends QMeResource {
    /**
     * Generated Serialized Version Id
     */
    private static final long serialVersionUID = 1243257850480182831L;

    /**
     * Question Id
     */
    private Long questionId;

    /**
     * Category Id
     */
    private Long categoryId;

    /**
     * Question Text
     */
    private String questionText;

    /**
     * Question Point
     */
    private Integer questionPoint;

    /**
     * Question Likes
     */
    private Long likes;

    /**
     * Question Create Date
     */
    private LocalDateTime questionCreateDate;
    /**
     * Question Create User Id
     */
    private Long  createUserID;

    /**
     * Question Update Date
     */
    private LocalDateTime questionUpdateDate;
    /**
     * Update User Id
     */
    private Long  updateUserID;

    /**
     * Get Question Id
     * @return Question Id
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * Set Question Id
     * @param questionId Question Id to set
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * Get Category Id
     * @return Category Id
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Set Category Id
     * @param categoryId Category Id to set
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Get Question Text
     * @return Question Text
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Set Question Test
     * @param questionText Question Text to set
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Get Question Point
     * @return Question Point
     */
    public Integer getQuestionPoint() {
        return questionPoint;
    }

    /**
     * Set Question Point
     * @param questionPoint Question Point to set
     */
    public void setQuestionPoint(Integer questionPoint) {
        this.questionPoint = questionPoint;
    }

    /**
     * Get Likes
     * @return Question Likes
     */
    public Long getLikes() {
        return likes;
    }

    /**
     * Set Likes
     * @param likes Question Likes to set
     */
    public void setLikes(Long likes) {
        this.likes = likes;
    }

    /**
     * Get Question Create Date
     * @return Question Create Date
     */
    public LocalDateTime getQuestionCreateDate() {
        return questionCreateDate;
    }

    /**
     * Set Question Create Date
     * @param questionCreateDate
     */
    public void setQuestionCreateDate(LocalDateTime questionCreateDate) {
        this.questionCreateDate = questionCreateDate;
    }

    /**
     * Get Question Create User Id
     * @return Question Create User Id
     */
    public Long getCreateUserID() {
        return createUserID;
    }

    /**
     * Set Question Create User ID
     * @param createUserID Question Create User ID
     */
    public void setCreateUserID(Long createUserID) {
        this.createUserID = createUserID;
    }

    /**
     * Get Question Update Date
     * @return Get Question Update Date
     */
    public LocalDateTime getQuestionUpdateDate() {
        return questionUpdateDate;
    }

    /**
     * Set Question Update Date
     * @param questionUpdateDate Question Update Date to set
     */
    public void setQuestionUpdateDate(LocalDateTime questionUpdateDate) {
        this.questionUpdateDate = questionUpdateDate;
    }

    /**
     * Get Question Update User Id
     * @return Question Update User Id
     */
    public Long getUpdateUserID() {
        return updateUserID;
    }

    /**
     * Set Update User Id
     * @param updateUserID Question Update User Id to set
     */
    public void setUpdateUserID(Long updateUserID) {
        this.updateUserID = updateUserID;
    }
}
