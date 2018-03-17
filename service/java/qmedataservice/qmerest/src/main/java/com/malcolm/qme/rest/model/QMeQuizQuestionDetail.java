/**
 * Name      : com.malcolm.qme.rest.model.QMeQuizQuestionDetail.java
 * Date      : 3/6/18
 * Developer : Malcolm
 * Purpose   : QMeQuizQuestionDetail Model for REST Service Controller
 */
package com.malcolm.qme.rest.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * QMeQuizQuestionDetail Model for REST Service Controller
 *
 * @author Malcolm
 */
public class QMeQuizQuestionDetail extends QMeResource {
    /**
     * Generated Serialized Version Id
     */
    private static final long serialVersionUID = 1343257850480182833L;

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
     * Create User Name
     */
    private String createUserName;

    /**
     * Update User Name
     */
    private String updateUserName;

    /**
     * Category Name
     */
    private String categoryName;

    /**
     * Selected Correct Answer
     */
    private Boolean correct;

    /**
     * Answer Option List
     */
    private List<QMeQuizAnswerOption> answerOptionList;

    /**
     * Get Question ID
     * @return questionId Question ID
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * Set Question ID
     * @param questionId Question ID
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * Get Category ID
     * @return categoryId Category ID
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Set Category ID
     * @param categoryId Category ID
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Get Question Text
     * @return questionText Question Text
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Set Question Text
     * @param questionText Question Text
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Get Question Point
     * @return questionPoint Question Point
     */
    public Integer getQuestionPoint() {
        return questionPoint;
    }

    /**
     * Set Question Point
     * @param questionPoint Question Point
     */
    public void setQuestionPoint(Integer questionPoint) {
        this.questionPoint = questionPoint;
    }

    /**
     * Get Likes
     * @return likes Question Likes
     */
    public Long getLikes() {
        return likes;
    }

    /**
     * Set Likes
     * @param likes Question Likes
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
     * Set Question create Date
     * @param questionCreateDate Question Create Date
     */
    public void setQuestionCreateDate(LocalDateTime questionCreateDate) {
        this.questionCreateDate = questionCreateDate;
    }

    /**
     * Get Question Create ID
     * @return createUserID Question Create Id
     */
    public Long getCreateUserID() {
        return createUserID;
    }

    /**
     * Set Question Create ID
     * @param createUserID Question Create Id
     */
    public void setCreateUserID(Long createUserID) {
        this.createUserID = createUserID;
    }

    /**
     * Get Question Update Date
     * @return questionUpdateDate Question Update Date
     */
    public LocalDateTime getQuestionUpdateDate() {
        return questionUpdateDate;
    }

    /**
     * Set Question Update Date
     * @param questionUpdateDate Question Update Date
     */
    public void setQuestionUpdateDate(LocalDateTime questionUpdateDate) {
        this.questionUpdateDate = questionUpdateDate;
    }

    /**
     * Get Question Update User ID
     * @return updateUserID Question Update User ID
     */
    public Long getUpdateUserID() {
        return updateUserID;
    }

    /**
     * Set Question Update User ID
     * @param updateUserID Question Update User ID
     */
    public void setUpdateUserID(Long updateUserID) {
        this.updateUserID = updateUserID;
    }

    /**
     * Get Question Create User Name
     * @return createUserName Question Create User Name
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * Set Question Create User Name
     * @param createUserName Question Create User Name
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * Get Question Update User Name
     * @return updateUserName Question Update User Name
     */
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * Set Question Update User Name
     * @param updateUserName Question Update User Name
     *
     */
    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    /**
     * Get Category Name
     * @return categoryName Question Category Name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Set Category Name
     * @param categoryName Question Category Name
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Get Question Correct
     * @return correct Question Answered Correctly
     */
    public Boolean getCorrect() {
        return correct;
    }

    /**
     * Set Question Correct
     * @param correct Question Answered Correctly
     */
    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    /**
     * Get Answer Option List
     * @return answerOptionList Answer Option List
     */
    public List<QMeQuizAnswerOption> getAnswerOptionList() {
        if(this.answerOptionList == null){
            this.answerOptionList = new ArrayList<>();
        }
        return this.answerOptionList;
    }

    /**
     * Set Answer Option List
     * @param answerOptionList Answer Option List
     */
    public void setAnswerOptionList(List<QMeQuizAnswerOption> answerOptionList) {
        this.answerOptionList = answerOptionList;
    }

    /**
     * Add Answer Option List
     * @param answerOption Answer Option to Set
     */
    public void addAnswerOptionList(QMeQuizAnswerOption answerOption){
        if(this.answerOptionList == null){
            this.answerOptionList = new ArrayList<>();
        }
        this.answerOptionList.add(answerOption);
    }
}
