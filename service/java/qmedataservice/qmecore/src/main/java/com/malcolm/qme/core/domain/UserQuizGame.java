/**
 * Name      : com.malcolm.qme.core.domain.UserQuizGame.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Game Domain Class
 */

package com.malcolm.qme.core.domain;

import java.util.Date;

/**
 * @Author: Malcolm
 */
public final class UserQuizGame {
    /**
     * User Game Unique Token
     */
    private final long userGameToken;

    /**
     * User Id
     */
    private final long userID;

    /**
     * Category Id
     */
    private final long categoryID;

    /**
     * User Game Score
     */
    private final long userGameScore;

    /**
     * Quiz Start Date
     */
    private final Date quizStartDate;

    /**
     * Quiz End Date
     */
    private final Date quizEndDate;

    /**
     * Public Constructor
     *
     * @param userGameToken
     * @param userID
     * @param categoryID
     * @param userGameScore
     * @param quizStartDate
     * @param quizEndDate
     */
    public UserQuizGame(long userGameToken, long userID, long categoryID, long userGameScore, Date quizStartDate, Date quizEndDate) {
        this.userGameToken = userGameToken;
        this.userID = userID;
        this.categoryID = categoryID;
        this.userGameScore = userGameScore;
        this.quizStartDate = quizStartDate;
        this.quizEndDate = quizEndDate;
    }

    /**
     * Public Constructor
     *
     * @param userGameToken
     * @param userID
     * @param categoryID
     */
    public UserQuizGame(long userGameToken, long userID, long categoryID) {
        this.userGameToken = userGameToken;
        this.userID = userID;
        this.categoryID = categoryID;
        this.userGameScore = 0;
        this.quizStartDate = null;
        this.quizEndDate = null;
    }

    /**
     * Get User Game Token
     * @return
     */
    public long getUserGameToken() {
        return userGameToken;
    }

    /**
     * Get User ID
     * @return
     */
    public long getUserID() {
        return userID;
    }

    /**
     * Get Category ID
     * @return
     */
    public long getCategoryID() {
        return categoryID;
    }

    /**
     * Get Start Date
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
     * Get User Game Score
     * @return
     */
    public long getUserGameScore() {
        return userGameScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserQuizGame that = (UserQuizGame) o;

        if (categoryID != that.categoryID) return false;
        if (userGameScore != that.userGameScore) return false;
        if (userGameToken != that.userGameToken) return false;
        if (userID != that.userID) return false;
        if (quizEndDate != null ? !quizEndDate.equals(that.quizEndDate) : that.quizEndDate != null) return false;
        if (quizStartDate != null ? !quizStartDate.equals(that.quizStartDate) : that.quizStartDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userGameToken ^ (userGameToken >>> 32));
        result = 31 * result + (int) (userID ^ (userID >>> 32));
        result = 31 * result + (int) (categoryID ^ (categoryID >>> 32));
        result = 31 * result + (int) (userGameScore ^ (userGameScore >>> 32));
        result = 31 * result + (quizStartDate != null ? quizStartDate.hashCode() : 0);
        result = 31 * result + (quizEndDate != null ? quizEndDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserQuizGame{" +
                "userGameToken=" + userGameToken +
                ", userID=" + userID +
                ", categoryID=" + categoryID +
                ", userGameScore=" + userGameScore +
                ", quizStartDate=" + quizStartDate +
                ", quizEndDate=" + quizEndDate +
                '}';
    }
}
