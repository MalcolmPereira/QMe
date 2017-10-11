/**
 * Name      : com.malcolm.qme.rest.model.QMeUserQuiz.java
 * Date      : 9/20/2017
 * Developer : Malcolm
 * Purpose   : QMeUserQuiz Model for REST Service Controller
 */
package com.malcolm.qme.rest.model;

import java.time.LocalDateTime;

/**
 * QMeUserQuiz Model for REST Service Controller
 *
 * @author Malcolm
 */
public class QMeUserQuiz extends QMeResource {
    /**
     * Generated Serialized Version Id
     */
    private static final long serialVersionUID = 132125715148032233L;

    /**
     * User Quiz  Id
     */
    private Long userQuizID;

    /**
     * User Id
     */
    private Long userID;

    /**
     * Quiz Id
     */
    private Long quizID;

    /**
     * Category Id
     */
    private Long categoryID;

    /**
     * Quiz Start Date
     */
    private LocalDateTime quizStartDate;

    /**
     * Quiz End Date
     */
    private LocalDateTime quizEndDate;

    /**
     * User Quiz Unique Token
     */
    private String userQuizToken;

    /**
     * Quiz User Id
     */
    private Integer quizUserScore;

    /**
     * Quiz Max Score
     */
    private Integer quizMaxScore;

    /**
     * Quiz Complete
     */
    private Boolean quizComplete;

    /**
     * Quiz Details
     */
    private QMeQuizDetail quiz;

    /**
     * Get User Quiz ID
     * @return userQuizID
     */
    public Long getUserQuizID() {
        return userQuizID;
    }

    /**
     * Set User Quiz Id
     * @param userQuizID
     */
    public void setUserQuizID(Long userQuizID) {
        this.userQuizID = userQuizID;
    }

    /**
     * Get User ID
     * @return userID
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Set User ID
     * @param userID
     */
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    /**
     * Get Quiz ID
     * @return quizID
     */
    public Long getQuizID() {
        return quizID;
    }

    /**
     * Set Quiz ID
     * @param quizID
     */
    public void setQuizID(Long quizID) {
        this.quizID = quizID;
    }

    /**
     * Get Category ID
     * @return categoryID
     */
    public Long getCategoryID() {
        return categoryID;
    }

    /**
     * Set Category ID
     * @param categoryID
     */
    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * Get Quiz Start Date
     * @return quizStartDate
     */
    public LocalDateTime getQuizStartDate() {
        return quizStartDate;
    }

    /**
     * Set Quiz Start Date
     * @param quizStartDate
     */
    public void setQuizStartDate(LocalDateTime quizStartDate) {
        this.quizStartDate = quizStartDate;
    }

    /**
     * Get Quiz End Date
     * @return quizEndDate
     */
    public LocalDateTime getQuizEndDate() {
        return quizEndDate;
    }

    /**
     * Set Quiz End Date
     * @param quizEndDate
     */
    public void setQuizEndDate(LocalDateTime quizEndDate) {
        this.quizEndDate = quizEndDate;
    }

    /**
     * Get User Quiz Token
     * @return userQuizToken
     */
    public String getUserQuizToken() {
        return userQuizToken;
    }

    /**
     * Set User Quiz Token
     * @param userQuizToken
     */
    public void setUserQuizToken(String userQuizToken) {
        this.userQuizToken = userQuizToken;
    }

    /**
     * Get Quiz User Score
     * @return quizUserScore
     */
    public Integer getQuizUserScore() {
        return quizUserScore;
    }

    /**
     * Set Quiz User Score
     * @param quizUserScore
     */
    public void setQuizUserScore(Integer quizUserScore) {
        this.quizUserScore = quizUserScore;
    }

    /**
     * Get Quiz Max Score
     * @return quizMaxScore
     */
    public Integer getQuizMaxScore() {
        return quizMaxScore;
    }

    /**
     * Set Quiz Max Score
     * @param quizMaxScore
     */
    public void setQuizMaxScore(Integer quizMaxScore) {
        this.quizMaxScore = quizMaxScore;
    }

    /**
     * Get Quiz Complete
     * @return quizComplete
     */
    public Boolean getQuizComplete() {
        return quizComplete;
    }

    /**
     * Set Quiz Complete
     * @param quizComplete
     */
    public void setQuizComplete(Boolean quizComplete) {
        this.quizComplete = quizComplete;
    }

    /**
     * Get Quiz
     * @return QMeQuizDetail
     */
    public QMeQuizDetail getQuiz() {
        return quiz;
    }

    /**
     * Set Quiz
     * @param quiz Quiz
     */
    public void setQuiz(QMeQuizDetail quiz) {
        this.quiz = quiz;
    }
}
