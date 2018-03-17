/**
 * QMeUserQuizDetail Model for REST User Quiz Service Controller
 *
 * @author Malcolm
 */
package com.malcolm.qme.rest.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * QMeQuizAnswerOption Model for REST Service Controller
 *
 * @author Malcolm
 */
public class QMeUserQuizDetail extends QMeUserQuiz {
    /**
     * Generated Serialized Version Id
     */
    private static final long serialVersionUID = 1323257850480181823L;
    /**
     * Quiz Name
     */
    private String quizName;

    /**
     * Quiz Desc
     */
    private String quizDesc;

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
     * QMe Quiz Question List
     */
    private List<QMeQuizQuestionDetail> qmeQuestionDetailList;

    /**
     * Get Quiz Name
     * @return quizName Quiz Name
     */
    public String getQuizName() {
        return quizName;
    }

    /**
     * Set Quiz Name
     * @param quizName Quiz Name
     */
    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    /**
     * Get Quiz Description
     * @return quizDesc Quiz Description
     */
    public String getQuizDesc() {
        return quizDesc;
    }

    /**
     * Set Quiz Description
     * @param quizDesc Quiz Description
     */
    public void setQuizDesc(String quizDesc) {
        this.quizDesc = quizDesc;
    }

    /**
     * Get Category Name
     * @return categoryName Category Name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Set Category Name
     * @param categoryName Category Name
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Get Likes
     * @return likes Likes
     */
    public Long getLikes() {
        return likes;
    }

    /**
     * Set Likes
     * @param likes Likes
     */
    public void setLikes(Long likes) {
        this.likes = likes;
    }

    /**
     * Get Quiz Hit
     * @return quizHit Quiz Hit
     */
    public Long getQuizHit() {
        return quizHit;
    }

    /**
     * Set Quiz Hit
     * @param quizHit Quiz Hit
     */
    public void setQuizHit(Long quizHit) {
        this.quizHit = quizHit;
    }

    /**
     * Get Quiz Max Attempts
     * @return quizMaxAttempts Quiz Max Attempts
     */
    public Integer getQuizMaxAttempts() {
        return quizMaxAttempts;
    }

    /**
     * Set Quiz Max Attempts
     * @param quizMaxAttempts Quiz Max Attempts
     */
    public void setQuizMaxAttempts(Integer quizMaxAttempts) {
        this.quizMaxAttempts = quizMaxAttempts;
    }

    /**
     * Get Quiz Create Date
     * @return quizCreateDate Quiz Create Date
     */
    public LocalDateTime getQuizCreateDate() {
        return quizCreateDate;
    }

    /**
     * Set Quiz Create Date
     * @param quizCreateDate Quiz Create Date
     */
    public void setQuizCreateDate(LocalDateTime quizCreateDate) {
        this.quizCreateDate = quizCreateDate;
    }

    /**
     * Get Quiz Create User ID
     * @return createUserID Quiz Create User ID
     */
    public Long getCreateUserID() {
        return createUserID;
    }

    /**
     * Set Quiz Create User ID
     * @param createUserID Quiz Create User ID
     */
    public void setCreateUserID(Long createUserID) {
        this.createUserID = createUserID;
    }

    /**
     * Get Quiz Create User Name
     * @return createUserName Quiz Create User Name
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * Set Quiz Create User Name
     * @param createUserName Quiz Create User Name
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * Get Quiz Update Date
     * @return quizUpdateDate Quiz Update Date
     */
    public LocalDateTime getQuizUpdateDate() {
        return quizUpdateDate;
    }

    /**
     * Set Quiz Update Date
     * @param quizUpdateDate Quiz Update Date
     */
    public void setQuizUpdateDate(LocalDateTime quizUpdateDate) {
        this.quizUpdateDate = quizUpdateDate;
    }

    /**
     * Get Quiz Update User
     * @return updateUserID Quiz Update User
     */
    public Long getUpdateUserID() {
        return updateUserID;
    }

    /**
     * Set Quiz Update User
     * @param updateUserID Quiz Update User
     */
    public void setUpdateUserID(Long updateUserID) {
        this.updateUserID = updateUserID;
    }

    /**
     * Get Quiz Update User Name
     * @return updateUserName Quiz Update User Name
     */
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * Set Quiz Update User Name
     * @param updateUserName Quiz Update User Name
     */
    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    /**
     * Get Quiz Question Details
     * @return qmeQuestionDetailList Quiz Question Details
     */
    public List<QMeQuizQuestionDetail> getQmeQuestionDetailList() {
        if(this.qmeQuestionDetailList == null){
            this.qmeQuestionDetailList = new ArrayList<>();
        }
        return this.qmeQuestionDetailList;
    }

    /**
     * Set Quiz Question Details
     * @param qmeQuestionDetailList Quiz Question Details
     */
    public void setQmeQuestionDetailList(List<QMeQuizQuestionDetail> qmeQuestionDetailList) {
        this.qmeQuestionDetailList = qmeQuestionDetailList;
    }

    /**
     * Add Quiz Question Details
     * @param qMeQuizQuestionDetail Quiz Question Detail
     */
    public void addQmeQuestionDetailList(QMeQuizQuestionDetail qMeQuizQuestionDetail){
        if(this.qmeQuestionDetailList == null){
            this.qmeQuestionDetailList = new ArrayList<>();
        }
        this.qmeQuestionDetailList.add(qMeQuizQuestionDetail);
    }
}
