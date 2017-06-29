/**
 * Name      : com.malcolm.qme.rest.model.QMeQuiz.java
 * Date      : 6/20/2017
 * Developer : Malcolm
 * Purpose   : QMeQuiz Model for REST Service Controller
 */
package com.malcolm.qme.rest.model;

import java.time.LocalDateTime;

/**
 * QMeQuiz Model for REST Service Controller
 *
 * @author Malcolm
 */
public class QMeQuiz extends QMeResource {
    /**
     * Generated Serialized Version Id
     */
    private static final long serialVersionUID = 1323257150480182233L;

    /**
     * Quiz Id
     */
    private Long quizID;

    /**
     * Quiz Name
     */
    private String quizName;

    /**
     * Quiz Desc
     */
    private String quizDesc;

    /**
     * Category Id
     */
    private Long categoryID;

    /**
     * Category Name
     */
    private String categoryName;

    /**
     * Quiz Likes
     */
    private Long likes;

    /**
     * Quiz Hits
     */
    private Long quizHit;

    /**
     * Quiz Max Attempts
     */
    private Integer quizMaxAttempts;

    /**
     * Quiz Create Date
     */
    private LocalDateTime quizCreateDate;

    /**
     * Quiz Create User Id
     */
    private Long  createUserID;

    /**
     * Create User Name
     */
    private String createUserName;

    /**
     * Quiz Update Date
     */
    private  LocalDateTime quizUpdateDate;

    /**
     * Update User Id
     */
    private Long  updateUserID;

    /**
     * Update User Name
     */
    private String updateUserName;

    /**
     * Get Quiz ID
     * @return Long
     */
    public Long getQuizID() {
        return quizID;
    }

    /**
     * Set Quiz Id
     * @param quizID Long
     */
    public void setQuizID(Long quizID) {
        this.quizID = quizID;
    }

    /**
     * Get Quiz Name
     * @return String
     */
    public String getQuizName() {
        return quizName;
    }

    /**
     * Set Quiz Name
     * @param quizName String
     */
    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    /**
     * Get Quiz Desc
     * @return String
     */
    public String getQuizDesc() {
        return quizDesc;
    }

    /**
     * Set Quiz Desc
     * @param quizDesc String
     */
    public void setQuizDesc(String quizDesc) {
        this.quizDesc = quizDesc;
    }

    /**
     * Get Category ID
     * @return Category ID
     */
    public Long getCategoryID() {
        return categoryID;
    }

    /**
     * Set Category Id
     * @param categoryID String
     */
    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * Get Category Name
     * @return String
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Set Category Name
     * @param categoryName String
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Get Likes
     * @return Long
     */
    public Long getLikes() {
        return likes;
    }

    /**
     * Set Likes
     * @param likes Long
     */
    public void setLikes(Long likes) {
        this.likes = likes;
    }

    /**
     * Get Quiz Hit
     * @return Long
     */
    public Long getQuizHit() {
        return quizHit;
    }

    /**
     * Set Quiz Hit
     * @param quizHit Long
     */
    public void setQuizHit(Long quizHit) {
        this.quizHit = quizHit;
    }

    /**
     * Get Quiz Max Attempts
     * @return Integer
     */
    public Integer getQuizMaxAttempts() {
        return quizMaxAttempts;
    }

    /**
     * Set Quiz Max Attempts
     * @param quizMaxAttempts Integer
     */
    public void setQuizMaxAttempts(Integer quizMaxAttempts) {
        this.quizMaxAttempts = quizMaxAttempts;
    }

    /**
     * Get Quiz Create Date
     * @return LocalDateTime
     */
    public LocalDateTime getQuizCreateDate() {
        return quizCreateDate;
    }

    /**
     * Set Quiz Create Date
     * @param quizCreateDate LocalDateTime
     */
    public void setQuizCreateDate(LocalDateTime quizCreateDate) {
        this.quizCreateDate = quizCreateDate;
    }

    /**
     * Get Create User ID
     * @return Long
     */
    public Long getCreateUserID() {
        return createUserID;
    }

    /**
     * Set Create User ID
     * @param createUserID Long
     */
    public void setCreateUserID(Long createUserID) {
        this.createUserID = createUserID;
    }

    /**
     * Get Create User Name
     * @return String
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * Set Create User Name
     * @param createUserName String
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * Get Quiz Update Date
     * @return LocalDateTime
     */
    public LocalDateTime getQuizUpdateDate() {
        return quizUpdateDate;
    }

    /**
     * Set Quiz Update Date
     * @param quizUpdateDate LocalDateTime
     */
    public void setQuizUpdateDate(LocalDateTime quizUpdateDate) {
        this.quizUpdateDate = quizUpdateDate;
    }

    /**
     * Get Update User ID
     * @return Long
     */
    public Long getUpdateUserID() {
        return updateUserID;
    }

    /**
     * Set Update User ID
     * @param updateUserID Long
     */
    public void setUpdateUserID(Long updateUserID) {
        this.updateUserID = updateUserID;
    }

    /**
     * Get Update User Name
     * @return String
     */
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * Set Update User Name
     * @param updateUserName String
     */
    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
