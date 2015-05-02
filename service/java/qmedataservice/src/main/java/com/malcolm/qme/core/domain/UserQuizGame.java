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
    private final String userGameToken;

    /**
     * User Id
     */
    private final long userID;

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
     * Public Constructor
     *
     * @param userGameToken
     * @param userID
     * @param categoryID
     * @param quizStartDate
     * @param quizEndDate
     */
    public UserQuizGame(String userGameToken, long userID, long categoryID, Date quizStartDate, Date quizEndDate) {
        this.userGameToken = userGameToken;
        this.userID = userID;
        this.categoryID = categoryID;
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
    public UserQuizGame(String userGameToken, long userID, long categoryID) {
        this.userGameToken = userGameToken;
        this.userID = userID;
        this.categoryID = categoryID;
        this.quizStartDate = null;
        this.quizEndDate = null;
    }

    /**
     * Get User Game Token
     * @return
     */
    public String getUserGameToken() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserQuizGame that = (UserQuizGame) o;

        if (categoryID != that.categoryID) return false;
        if (userID != that.userID) return false;
        if (quizEndDate != null ? !quizEndDate.equals(that.quizEndDate) : that.quizEndDate != null) return false;
        if (quizStartDate != null ? !quizStartDate.equals(that.quizStartDate) : that.quizStartDate != null)
            return false;
        if (!userGameToken.equals(that.userGameToken)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userGameToken.hashCode();
        result = 31 * result + (int) (userID ^ (userID >>> 32));
        result = 31 * result + (int) (categoryID ^ (categoryID >>> 32));
        result = 31 * result + (quizStartDate != null ? quizStartDate.hashCode() : 0);
        result = 31 * result + (quizEndDate != null ? quizEndDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserQuizGame{" +
                "userGameToken='" + userGameToken + '\'' +
                ", userID=" + userID +
                ", categoryID=" + categoryID +
                ", quizStartDate=" + quizStartDate +
                ", quizEndDate=" + quizEndDate +
                '}';
    }
}
