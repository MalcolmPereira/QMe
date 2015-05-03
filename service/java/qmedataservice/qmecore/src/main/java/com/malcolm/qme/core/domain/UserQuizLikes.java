/**
 * Name      : com.malcolm.qme.core.domain.UserQuizLikes.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Likes Domain Class
 */

package com.malcolm.qme.core.domain;

/**
 * @Author: Malcolm
 */
public final class UserQuizLikes {
    /**
     * User Id
     */
    private final long userID;

    /**
     * Quiz Id
     */
    private final long quizID;

    /**
     * Public Constructor
     *
     * @param userID
     * @param quizID
     */
    public UserQuizLikes(long userID, long quizID) {
        this.userID = userID;
        this.quizID = quizID;
    }

    /**
     * Get User ID
     *
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserQuizLikes that = (UserQuizLikes) o;

        if (quizID != that.quizID) return false;
        if (userID != that.userID) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userID ^ (userID >>> 32));
        result = 31 * result + (int) (quizID ^ (quizID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UserQuizLikes{" +
                "userID=" + userID +
                ", quizID=" + quizID +
                '}';
    }
}

