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
    private final Long quizQuestionID;

    /**
     * Quiz Id
     */
    private final Long quizID;

    /**
     * Question Id
     */
    private final Long questionID;

    /**
     * Public Constructor
     *
     * @param quizQuestionID
     * @param quizID
     * @param questionID
     */
    public QuizQuestion(Long quizQuestionID, Long quizID, Long questionID) {
        this.quizQuestionID = quizQuestionID;
        this.quizID = quizID;
        this.questionID = questionID;
    }

    /**
     * Public Constructor
     *
     * @param quizID
     * @param questionID
     */
    public QuizQuestion(Long quizID, Long questionID) {
        this.quizQuestionID = 0L;
        this.quizID = quizID;
        this.questionID = questionID;
    }

    /**
     * Get Quiz Question ID
     * @return
     */
    public Long getQuizQuestionID() {
        return quizQuestionID;
    }

    /**
     * Get Quiz ID
     * @return
     */
    public Long getQuizID() {
        return quizID;
    }

    /**
     * Get Question ID
     * @return
     */
    public Long getQuestionID() {
        return questionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizQuestion that = (QuizQuestion) o;

        if (!getQuizQuestionID().equals(that.getQuizQuestionID())) return false;
        if (!getQuizID().equals(that.getQuizID())) return false;
        return getQuestionID().equals(that.getQuestionID());

    }

    @Override
    public int hashCode() {
        int result = getQuizQuestionID().hashCode();
        result = 31 * result + getQuizID().hashCode();
        result = 31 * result + getQuestionID().hashCode();
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

