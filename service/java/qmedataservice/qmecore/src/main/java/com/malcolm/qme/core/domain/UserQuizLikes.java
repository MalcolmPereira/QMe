/**
 * Name      : com.malcolm.qme.core.domain.UserQuizLikes.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Likes Domain Class
 */

package com.malcolm.qme.core.domain;

import java.util.Objects;

/**
 * @author Malcolm
 */
public final class UserQuizLikes {
    /**
     * User Id
     */
    private final Long userID;

    /**
     * Quiz Id
     */
    private final Long quizID;

    /**
     * Public Constructor
     *
     * @param userID User ID
     * @param quizID Quiz ID
     */
    public UserQuizLikes(Long userID, Long quizID) {
        this.userID = userID;
        this.quizID = quizID;
    }

    /**
     * Get User ID
     *
     * @return User ID
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Get Quiz ID
     * @return Quiz ID
     */
    public Long getQuizID() {
        return quizID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserQuizLikes that = (UserQuizLikes) o;
        return Objects.equals(userID, that.userID) &&
                Objects.equals(quizID, that.quizID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, quizID);
    }

    @Override
    public String toString() {
        return "UserQuizLikes{" +
                "userID=" + userID +
                ", quizID=" + quizID +
                '}';
    }
}

