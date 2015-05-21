/**
 * Name      : com.malcolm.qme.core.domain.QuizQuestion.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Question Domain Class
 */

package com.malcolm.qme.core.domain;

/**
 * @author Malcolm
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
     * @param quizQuestionID Quiz Question ID
     * @param quizID Quiz ID
     * @param questionID Question ID
     */
    public QuizQuestion(Long quizQuestionID, Long quizID, Long questionID) {
        this.quizQuestionID = quizQuestionID;
        this.quizID = quizID;
        this.questionID = questionID;
    }

    /**
     * Public Constructor
     *
     * @param quizID Quiz ID
     * @param questionID Question ID
     */
    public QuizQuestion(Long quizID, Long questionID) {
        this.quizQuestionID = 0L;
        this.quizID = quizID;
        this.questionID = questionID;
    }

    /**
     * Get Quiz Question ID
     * @return Quiz Question ID
     */
    public Long getQuizQuestionID() {
        return quizQuestionID;
    }

    /**
     * Get Quiz ID
     * @return Quiz ID
     */
    public Long getQuizID() {
        return quizID;
    }

    /**
     * Get Question ID
     * @return Question ID
     */
    public Long getQuestionID() {
        return questionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizQuestion that = (QuizQuestion) o;

        return getQuizQuestionID().equals(that.getQuizQuestionID()) && getQuizID().equals(that.getQuizID()) && getQuestionID().equals(that.getQuestionID());

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

