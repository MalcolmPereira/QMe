/**
 * Name      : com.malcolm.qme.core.domain.Quiz.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Domain Class
 */

package com.malcolm.qme.core.domain;

import java.util.Date;

/**
 * @Author: Malcolm
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
     * @param quizID
     * @param quizName
     * @param quizDesc
     * @param categoryID
     * @param likes
     * @param quizHit
     * @param quizMaxAttempts
     * @param quizCreateDate
     * @param createUserID
     * @param quizUpdateDate
     * @param updateUserID
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
     * @param quizName
     * @param quizDesc
     * @param categoryID
     * @param quizMaxAttempts
     * @param createUserID
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
     * @return
     */
    public Long getQuizID() {
        return quizID;
    }

    /**
     * Get Quiz Name
     * @return
     */
    public String getQuizName() {
        return quizName;
    }

    /**
     * Get Quiz Desc
     * @return
     */
    public String getQuizDesc() {
        return quizDesc;
    }

    /**
     * Get Category ID
     * @return
     */
    public Long getCategoryID() {
        return categoryID;
    }

    /**
     * Get Quiz Likes
     * @return
     */
    public Long getLikes() {
        return likes;
    }

    /**
     * Get Quiz Hits
     * @return
     */
    public Long getQuizHit() {
        return quizHit;
    }

    /**
     * Get Quiz Max Attempts
     * @return
     */
    public Integer getQuizMaxAttempts() {
        return quizMaxAttempts;
    }

    /**
     * Get Quiz Create Date
     * @return
     */
    public Date getQuizCreateDate() {
        return quizCreateDate;
    }

    /**
     * Get Quiz Create User
     * @return
     */
    public Long getCreateUserID() {
        return createUserID;
    }

    /**
     * Get Quiz Update Date
     * @return
     */
    public Date getQuizUpdateDate() {
        return quizUpdateDate;
    }

    /**
     * Get Quiz Update User
     * @return
     */
    public Long getUpdateUserID() {
        return updateUserID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz quiz = (Quiz) o;

        if (!getQuizID().equals(quiz.getQuizID())) return false;
        if (!getQuizName().equals(quiz.getQuizName())) return false;
        if (getQuizDesc() != null ? !getQuizDesc().equals(quiz.getQuizDesc()) : quiz.getQuizDesc() != null)
            return false;
        if (!getCategoryID().equals(quiz.getCategoryID())) return false;
        if (getLikes() != null ? !getLikes().equals(quiz.getLikes()) : quiz.getLikes() != null) return false;
        if (getQuizHit() != null ? !getQuizHit().equals(quiz.getQuizHit()) : quiz.getQuizHit() != null) return false;
        if (getQuizMaxAttempts() != null ? !getQuizMaxAttempts().equals(quiz.getQuizMaxAttempts()) : quiz.getQuizMaxAttempts() != null)
            return false;
        if (getQuizCreateDate() != null ? !getQuizCreateDate().equals(quiz.getQuizCreateDate()) : quiz.getQuizCreateDate() != null)
            return false;
        if (!getCreateUserID().equals(quiz.getCreateUserID())) return false;
        if (getQuizUpdateDate() != null ? !getQuizUpdateDate().equals(quiz.getQuizUpdateDate()) : quiz.getQuizUpdateDate() != null)
            return false;
        return !(getUpdateUserID() != null ? !getUpdateUserID().equals(quiz.getUpdateUserID()) : quiz.getUpdateUserID() != null);

    }

    @Override
    public int hashCode() {
        int result = getQuizID().hashCode();
        result = 31 * result + getQuizName().hashCode();
        result = 31 * result + (getQuizDesc() != null ? getQuizDesc().hashCode() : 0);
        result = 31 * result + getCategoryID().hashCode();
        result = 31 * result + (getLikes() != null ? getLikes().hashCode() : 0);
        result = 31 * result + (getQuizHit() != null ? getQuizHit().hashCode() : 0);
        result = 31 * result + (getQuizMaxAttempts() != null ? getQuizMaxAttempts().hashCode() : 0);
        result = 31 * result + (getQuizCreateDate() != null ? getQuizCreateDate().hashCode() : 0);
        result = 31 * result + getCreateUserID().hashCode();
        result = 31 * result + (getQuizUpdateDate() != null ? getQuizUpdateDate().hashCode() : 0);
        result = 31 * result + (getUpdateUserID() != null ? getUpdateUserID().hashCode() : 0);
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


