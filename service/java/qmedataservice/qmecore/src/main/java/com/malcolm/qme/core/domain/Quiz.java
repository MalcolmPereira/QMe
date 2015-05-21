/**
 * Name      : com.malcolm.qme.core.domain.Quiz.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Domain Class
 */

package com.malcolm.qme.core.domain;

import java.util.Date;

/**
 * @author Malcolm
 */
public final class Quiz {
    /**
     * Quiz Id
     */
    private final Long quizID;

    /**
     * Quiz Name
     */
    private final String quizName;

    /**
     * Quiz Desc
     */
    private final String quizDesc;

    /**
     * Category Id
     */
    private final Long categoryID;

    /**
     * Quiz Likes
     */
    private final Long likes;

    /**
     * Quiz Hits
     */
    private final Long quizHit;

    /**
     * Quiz Max Attempts
     */
    private final Integer quizMaxAttempts;

    /**
     * Quiz Create Date
     */
    private final Date quizCreateDate;

    /**
     * Quiz Create User Id
     */
    private final Long  createUserID;

    /**
     * Quiz Update Date
     */
    private final Date quizUpdateDate;

    /**
     * Update User Id
     */
    private final Long  updateUserID;

    /**
     * Public Constructor
     *
     * @param quizID Quiz ID
     * @param quizName Quiz Name
     * @param quizDesc Quiz Desc
     * @param categoryID Category ID
     * @param likes Likes
     * @param quizHit Quiz Hit
     * @param quizMaxAttempts Quiz Max Attempts
     * @param quizCreateDate Quiz Create Date
     * @param createUserID Quiz Create User ID
     * @param quizUpdateDate Quiz Update Date
     * @param updateUserID Quiz Update User Id
     */
    public Quiz(Long quizID, String quizName, String quizDesc, Long categoryID, Long likes, Long quizHit, Integer quizMaxAttempts, Date quizCreateDate, Long createUserID, Date quizUpdateDate, Long updateUserID) {
        this.quizID = quizID;
        this.quizName = quizName;
        this.quizDesc = quizDesc;
        this.categoryID = categoryID;
        this.likes = likes;
        this.quizHit = quizHit;
        this.quizMaxAttempts = quizMaxAttempts;
        this.quizCreateDate = quizCreateDate;
        this.createUserID = createUserID;
        this.quizUpdateDate = quizUpdateDate;
        this.updateUserID = updateUserID;
    }

    /**
     * Public Constructor
     *
     * @param quizName Quiz Name
     * @param quizDesc Quiz Desc
     * @param categoryID Category ID
     * @param quizMaxAttempts Quiz Max Attempts
     * @param createUserID Quiz Create User ID
     */
    public Quiz(String quizName, String quizDesc, Long categoryID, Integer quizMaxAttempts, Long createUserID) {
        this.quizID = 0L;
        this.quizName = quizName;
        this.quizDesc = quizDesc;
        this.categoryID = categoryID;
        this.likes = 0L;
        this.quizHit = 0L;
        this.quizMaxAttempts = quizMaxAttempts;
        this.quizCreateDate = null;
        this.createUserID = createUserID;
        this.quizUpdateDate = null;
        this.updateUserID = 0L;
    }

    /**
     * Get Quiz ID
     * @return Quiz ID
     */
    public Long getQuizID() {
        return quizID;
    }

    /**
     * Get Quiz Name
     * @return Quiz Name
     */
    public String getQuizName() {
        return quizName;
    }

    /**
     * Get Quiz Desc
     * @return Quiz Desc
     */
    public String getQuizDesc() {
        return quizDesc;
    }

    /**
     * Get Category ID
     * @return Category ID
     */
    public Long getCategoryID() {
        return categoryID;
    }

    /**
     * Get Quiz Likes
     * @return Quiz Likes
     */
    public Long getLikes() {
        return likes;
    }

    /**
     * Get Quiz Hits
     * @return Quiz Hits
     */
    public Long getQuizHit() {
        return quizHit;
    }

    /**
     * Get Quiz Max Attempts
     * @return Quiz Max Attempts
     */
    public Integer getQuizMaxAttempts() {
        return quizMaxAttempts;
    }

    /**
     * Get Quiz Create Date
     * @return Quiz Create Date
     */
    public Date getQuizCreateDate() {
        return quizCreateDate;
    }

    /**
     * Get Quiz Create User
     * @return Quiz Create User
     */
    public Long getCreateUserID() {
        return createUserID;
    }

    /**
     * Get Quiz Update Date
     * @return Quiz Update Date
     */
    public Date getQuizUpdateDate() {
        return quizUpdateDate;
    }

    /**
     * Get Quiz Update User
     * @return Quiz Update User
     */
    public Long getUpdateUserID() {
        return updateUserID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz quiz = (Quiz) o;

        return getQuizID().equals(quiz.getQuizID()) && getQuizName().equals(quiz.getQuizName()) && getCategoryID().equals(quiz.getCategoryID()) && getCreateUserID().equals(quiz.getCreateUserID());

    }

    @Override
    public int hashCode() {
        int result = getQuizID().hashCode();
        result = 31 * result + getQuizName().hashCode();
        result = 31 * result + getCategoryID().hashCode();
        result = 31 * result + getCreateUserID().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizID=" + quizID +
                ", quizName='" + quizName + '\'' +
                ", quizDesc='" + quizDesc + '\'' +
                ", categoryID=" + categoryID +
                ", likes=" + likes +
                ", quizHit=" + quizHit +
                ", quizMaxAttempts=" + quizMaxAttempts +
                ", quizCreateDate=" + quizCreateDate +
                ", createUserID=" + createUserID +
                ", quizUpdateDate=" + quizUpdateDate +
                ", updateUserID=" + updateUserID +
                '}';
    }
}


