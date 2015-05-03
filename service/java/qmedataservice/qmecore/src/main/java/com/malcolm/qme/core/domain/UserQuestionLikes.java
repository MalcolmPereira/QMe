/**
 * Name      : com.malcolm.qme.core.domain.UserQuestionLikes.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe User Question Likes Domain Class
 */
package com.malcolm.qme.core.domain;

/**
 * @Author Malcolm
 */
public final class UserQuestionLikes {

    /**
     * User Id
     */
    private final long userID;

    /**
     * Question Id
     */
    private final long questionID;

    /**
     * Public Constructor
     * @param userID
     * @param questionID
     */
    public UserQuestionLikes(long userID, long questionID) {
        this.userID = userID;
        this.questionID = questionID;
    }

    /**
     * Get User Id
     * @return
     */
    public long getUserID() {
        return userID;
    }

    /**
     * Get Question Id
     * @return
     */
    public long getQuestionID() {
        return questionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserQuestionLikes that = (UserQuestionLikes) o;

        if (getUserID() != that.getUserID()) return false;
        return getQuestionID() == that.getQuestionID();

    }

    @Override
    public int hashCode() {
        int result = (int) (getUserID() ^ (getUserID() >>> 32));
        result = 31 * result + (int) (getQuestionID() ^ (getQuestionID() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UserQuestionLikes{" +
                "userID=" + userID +
                ", questionID=" + questionID +
                '}';
    }
}