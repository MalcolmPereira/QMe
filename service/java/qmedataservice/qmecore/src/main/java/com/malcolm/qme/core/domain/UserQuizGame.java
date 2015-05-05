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
    private final Long userGameToken;

    /**
     * User Id
     */
    private final Long userID;

    /**
     * Category Id
     */
    private final Long categoryID;

    /**
     * User Game Score
     */
    private final Integer userGameScore;

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
    public UserQuizGame(Long userGameToken, Long userID, Long categoryID, Integer userGameScore, Date quizStartDate, Date quizEndDate) {
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
    public UserQuizGame(Long userGameToken, Long userID, Long categoryID) {
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
    public Long getUserGameToken() {
        return userGameToken;
    }

    /**
     * Get User ID
     * @return
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Get Category ID
     * @return
     */
    public Long getCategoryID() {
        return categoryID;
    }

    /**
     * Get User Game Score
     * @return
     */
    public Integer getUserGameScore() {
        return userGameScore;
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

        if (!getUserGameToken().equals(that.getUserGameToken())) return false;
        if (!getUserID().equals(that.getUserID())) return false;
        if (!getCategoryID().equals(that.getCategoryID())) return false;
        if (getUserGameScore() != null ? !getUserGameScore().equals(that.getUserGameScore()) : that.getUserGameScore() != null)
            return false;
        if (getQuizStartDate() != null ? !getQuizStartDate().equals(that.getQuizStartDate()) : that.getQuizStartDate() != null)
            return false;
        return !(getQuizEndDate() != null ? !getQuizEndDate().equals(that.getQuizEndDate()) : that.getQuizEndDate() != null);

    }

    @Override
    public int hashCode() {
        int result = getUserGameToken().hashCode();
        result = 31 * result + getUserID().hashCode();
        result = 31 * result + getCategoryID().hashCode();
        result = 31 * result + (getUserGameScore() != null ? getUserGameScore().hashCode() : 0);
        result = 31 * result + (getQuizStartDate() != null ? getQuizStartDate().hashCode() : 0);
        result = 31 * result + (getQuizEndDate() != null ? getQuizEndDate().hashCode() : 0);
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
