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
    private final Long userID;

    /**
     * Question Id
     */
    private final Long questionID;

    /**
     * Public Constructor
     * @param userID
     * @param questionID
     */
    public UserQuestionLikes(Long userID, Long questionID) {
        this.userID = userID;
        this.questionID = questionID;
    }

    /**
     * Get User Id
     * @return
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Get Question Id
     * @return
     */
    public Long getQuestionID() {
        return questionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserQuestionLikes that = (UserQuestionLikes) o;

        if (!getUserID().equals(that.getUserID())) return false;
        return getQuestionID().equals(that.getQuestionID());

    }

    @Override
    public int hashCode() {
        int result = getUserID().hashCode();
        result = 31 * result + getQuestionID().hashCode();
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