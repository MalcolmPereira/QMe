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
    private final long userQuizID;

    /**
     * User Id
     */
    private final long userID;

    /**
     * Quiz Id
     */
    private final long quizID;

    /**
     * Category Id
     */
    private final long categoryID;

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
    private final long quizScore;

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
    public UserQuiz(long userQuizID, long userID, long quizID, long categoryID, Date quizStartDate, Date quizEndDate, String userQuizToken, long quizScore, Boolean quizComplete) {
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
    public UserQuiz(long userID, long quizID, long categoryID, String userQuizToken) {
        this.userQuizID = 0;
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
    public long getUserQuizID() {
        return userQuizID;
    }

    /**
     * Get User ID
     * @return
     */
    public long getUserID() {
        return userID;
    }

    /**
     * Get Quiz ID
     * @return
     */
    public long getQuizID() {
        return quizID;
    }

    /**
     * Get Category ID
     * @return
     */
    public long getCategoryID() {
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
    public long getQuizScore() {
        return quizScore;
    }

    /**
     * Get Quiz Complete
     * @return
     */
    public Boolean getQuizComplete() {
        return quizComplete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserQuiz userQuiz = (UserQuiz) o;

        if (categoryID != userQuiz.categoryID) return false;
        if (quizID != userQuiz.quizID) return false;
        if (quizScore != userQuiz.quizScore) return false;
        if (userID != userQuiz.userID) return false;
        if (userQuizID != userQuiz.userQuizID) return false;
        if (quizComplete != null ? !quizComplete.equals(userQuiz.quizComplete) : userQuiz.quizComplete != null)
            return false;
        if (quizEndDate != null ? !quizEndDate.equals(userQuiz.quizEndDate) : userQuiz.quizEndDate != null)
            return false;
        if (quizStartDate != null ? !quizStartDate.equals(userQuiz.quizStartDate) : userQuiz.quizStartDate != null)
            return false;
        if (!userQuizToken.equals(userQuiz.userQuizToken)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userQuizID ^ (userQuizID >>> 32));
        result = 31 * result + (int) (userID ^ (userID >>> 32));
        result = 31 * result + (int) (quizID ^ (quizID >>> 32));
        result = 31 * result + (int) (categoryID ^ (categoryID >>> 32));
        result = 31 * result + (quizStartDate != null ? quizStartDate.hashCode() : 0);
        result = 31 * result + (quizEndDate != null ? quizEndDate.hashCode() : 0);
        result = 31 * result + userQuizToken.hashCode();
        result = 31 * result + (int) (quizScore ^ (quizScore >>> 32));
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
