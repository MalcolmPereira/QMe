/**
 * Name      : com.malcolm.qme.core.domain.UserQuiz.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Domain Class
 */

package com.malcolm.qme.core.domain;

import java.util.Date;

/**
 * @Author: Malcolm
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
     * Quiz Id
     */
    private final Integer quizScore;

    /**
     * Correct
     */
    private final Boolean quizComplete;

    /**
     * Public Constructor
     *
     * @param userQuizID
     * @param userID
     * @param quizID
     * @param categoryID
     * @param quizStartDate
     * @param quizEndDate
     * @param userQuizToken
     * @param quizScore
     * @param quizComplete
     */
    public UserQuiz(Long userQuizID, Long userID, Long quizID, Long categoryID, Date quizStartDate, Date quizEndDate, String userQuizToken, Integer quizScore, Boolean quizComplete) {
        this.userQuizID = userQuizID;
        this.userID = userID;
        this.quizID = quizID;
        this.categoryID = categoryID;
        this.quizStartDate = quizStartDate;
        this.quizEndDate = quizEndDate;
        this.userQuizToken = userQuizToken;
        this.quizScore = quizScore;
        this.quizComplete = quizComplete;
    }

    /**
     * Public Constructor
     *
     * @param userID
     * @param quizID
     * @param categoryID
     * @param userQuizToken
     */
    public UserQuiz(Long userID, Long quizID, Long categoryID, String userQuizToken) {
        this.userQuizID = 0L;
        this.userID = userID;
        this.quizID = quizID;
        this.categoryID = categoryID;
        this.quizStartDate = null;
        this.quizEndDate = null;
        this.userQuizToken = userQuizToken;
        this.quizScore = 0;
        this.quizComplete = Boolean.FALSE;
    }

    /**
     * Get User Quiz ID
     * @return
     */
    public Long getUserQuizID() {
        return userQuizID;
    }

    /**
     * Get User ID
     * @return
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Get Quiz ID
     * @return
     */
    public Long getQuizID() {
        return quizID;
    }

    /**
     * Get Category ID
     * @return
     */
    public Long getCategoryID() {
        return categoryID;
    }

    /**
     * Get Quiz Start Date
     * @return
     */
    public Date getQuizStartDate() {
        return quizStartDate;
    }

    /**
     * Get Quiz End Date
     * @return
     */
    public Date getQuizEndDate() {
        return quizEndDate;
    }

    /**
     * Get Quiz Token
     * @return
     */
    public String getUserQuizToken() {
        return userQuizToken;
    }

    /**
     * Get Quiz Score
     * @return
     */
    public Integer getQuizScore() {
        return quizScore;
    }

    /**
     * Get Quiz Complete
     * @return
     */
    public Boolean isQuizComplete() {
        return quizComplete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserQuiz userQuiz = (UserQuiz) o;

        if (!getUserQuizID().equals(userQuiz.getUserQuizID())) return false;
        if (!getUserID().equals(userQuiz.getUserID())) return false;
        if (!getQuizID().equals(userQuiz.getQuizID())) return false;
        if (!getCategoryID().equals(userQuiz.getCategoryID())) return false;
        if (getQuizStartDate() != null ? !getQuizStartDate().equals(userQuiz.getQuizStartDate()) : userQuiz.getQuizStartDate() != null)
            return false;
        if (getQuizEndDate() != null ? !getQuizEndDate().equals(userQuiz.getQuizEndDate()) : userQuiz.getQuizEndDate() != null)
            return false;
        if (!getUserQuizToken().equals(userQuiz.getUserQuizToken())) return false;
        if (getQuizScore() != null ? !getQuizScore().equals(userQuiz.getQuizScore()) : userQuiz.getQuizScore() != null)
            return false;
        return !(quizComplete != null ? !quizComplete.equals(userQuiz.quizComplete) : userQuiz.quizComplete != null);

    }

    @Override
    public int hashCode() {
        int result = getUserQuizID().hashCode();
        result = 31 * result + getUserID().hashCode();
        result = 31 * result + getQuizID().hashCode();
        result = 31 * result + getCategoryID().hashCode();
        result = 31 * result + (getQuizStartDate() != null ? getQuizStartDate().hashCode() : 0);
        result = 31 * result + (getQuizEndDate() != null ? getQuizEndDate().hashCode() : 0);
        result = 31 * result + getUserQuizToken().hashCode();
        result = 31 * result + (getQuizScore() != null ? getQuizScore().hashCode() : 0);
        result = 31 * result + (quizComplete != null ? quizComplete.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserQuiz{" +
                "userQuizID=" + userQuizID +
                ", userID=" + userID +
                ", quizID=" + quizID +
                ", categoryID=" + categoryID +
                ", quizStartDate=" + quizStartDate +
                ", quizEndDate=" + quizEndDate +
                ", userQuizToken='" + userQuizToken + '\'' +
                ", quizScore=" + quizScore +
                ", quizComplete=" + quizComplete +
                '}';
    }
}
