/**
 * Name      : com.malcolm.qme.core.domain.QuizQuestion.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Question Domain Class
 */

package com.malcolm.qme.core.domain;

/**
 * @Author: Malcolm
 */
public final class QuizQuestion {
    /**
     * Quiz Question Id
     */
    private final long quizQuestionID;

    /**
     * Quiz Id
     */
    private final long quizID;

    /**
     * Question Id
     */
    private final long questionID;

    /**
     * Public Constructor
     *
     * @param quizQuestionID
     * @param quizID
     * @param questionID
     */
    public QuizQuestion(long quizQuestionID, long quizID, long questionID) {
        this.quizQuestionID = quizQuestionID;
        this.quizID = quizID;
        this.questionID = questionID;
    }

    /**
     * Get Quiz Question ID
     * @return
     */
    public long getQuizQuestionID() {
        return quizQuestionID;
    }

    /**
     * Get Quiz ID
     * @return
     */
    public long getQuizID() {
        return quizID;
    }

    /**
     * Get Question ID
     * @return
     */
    public long getQuestionID() {
        return questionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizQuestion that = (QuizQuestion) o;

        if (questionID != that.questionID) return false;
        if (quizID != that.quizID) return false;
        if (quizQuestionID != that.quizQuestionID) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (quizQuestionID ^ (quizQuestionID >>> 32));
        result = 31 * result + (int) (quizID ^ (quizID >>> 32));
        result = 31 * result + (int) (questionID ^ (questionID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "QuizQuestion{" +
                "quizQuestionID=" + quizQuestionID +
                ", quizID=" + quizID +
                ", questionID=" + questionID +
                '}';
    }
}

