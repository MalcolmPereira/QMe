/**
 * Name      : com.malcolm.qme.core.domain.UserQuizGame.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Game Domain Class
 */

package com.malcolm.qme.core.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Malcolm
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
    private final LocalDateTime quizStartDate;

    /**
     * Quiz End Date
     */
    private final LocalDateTime quizEndDate;

    /**
     * Public Constructor
     *
     * @param userGameToken Unique User Game Token
     * @param userID User ID
     * @param categoryID Category ID
     * @param userGameScore User Game Score
     * @param quizStartDate Quiz Start Date
     * @param quizEndDate Quiz End Date
     */
    public UserQuizGame(Long userGameToken, Long userID, Long categoryID, Integer userGameScore, LocalDateTime quizStartDate, LocalDateTime quizEndDate) {
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
     * @param userGameToken Unique User Game Token
     * @param userID User ID
     * @param categoryID Category ID
     */
    public UserQuizGame(Long userGameToken, Long userID, Long categoryID) {
        this.userGameToken = userGameToken;
        this.userID = userID;
        this.categoryID = categoryID;
        this.userGameScore = 0;
        this.quizStartDate = LocalDateTime.now();
        this.quizEndDate = null;
    }

    /**
     * Get User Game Token
     * @return User Game Token
     */
    public Long getUserGameToken() {
        return userGameToken;
    }

    /**
     * Get User ID
     * @return User ID
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Get Category ID
     * @return Category ID
     */
    public Long getCategoryID() {
        return categoryID;
    }

    /**
     * Get User Game Score
     * @return User Game Score
     */
    public Integer getUserGameScore() {
        return userGameScore;
    }

    /**
     * Get Quiz Start Date
     * @return Quiz Start Date
     */
    public LocalDateTime getQuizStartDate() {
        return quizStartDate;
    }

    /**
     * Get Quiz End Date
     * @return Quiz End Date
     */
    public LocalDateTime getQuizEndDate() {
        return quizEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserQuizGame that = (UserQuizGame) o;
        return Objects.equals(userGameToken, that.userGameToken) &&
                Objects.equals(userID, that.userID) &&
                Objects.equals(categoryID, that.categoryID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userGameToken, userID, categoryID);
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
