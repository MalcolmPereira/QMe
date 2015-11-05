/**
 * Name      : com.malcolm.qme.core.domain.UserQuestionLikes.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe User Question Likes Domain Class
 */
package com.malcolm.qme.core.domain;

import java.util.Objects;

/**
 * @author Malcolm
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
     * @param userID User ID
     * @param questionID Question ID
     */
    public UserQuestionLikes(Long userID, Long questionID) {
        this.userID = userID;
        this.questionID = questionID;
    }

    /**
     * Get User Id
     * @return User Id
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Get Question Id
     * @return Question Id
     */
    public Long getQuestionID() {
        return questionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserQuestionLikes that = (UserQuestionLikes) o;
        return Objects.equals(userID, that.userID) &&
                Objects.equals(questionID, that.questionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, questionID);
    }

    @Override
    public String toString() {
        return "UserQuestionLikes{" +
                "userID=" + userID +
                ", questionID=" + questionID +
                '}';
    }
}