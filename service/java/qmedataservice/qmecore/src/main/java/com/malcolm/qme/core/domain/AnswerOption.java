/**
 * Name      : com.malcolm.qme.core.domain.AnswerOption.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Answer Option Domain Class
 */
package com.malcolm.qme.core.domain;

import java.util.Objects;

/**
 * @author Malcolm
 */
public final class AnswerOption {

    /**
     * Answer Option Id
     */
    private final Long answerOptionID;

    /**
     * Question Id
     */
    private final Long questionID;

    /**
     * Option Text
     */
    private final String optionText;

    /**
     * Correct
     */
    private final Boolean correct;

    /**
     * Public Constructor
     *
     * @param answerOptionID Answer Option ID
     * @param questionID Question ID
     * @param optionText Answer Option Text
     * @param correct Is the Answer Option Correct
     */
    public AnswerOption(Long answerOptionID, Long questionID, String optionText, Boolean correct) {
        this.answerOptionID = answerOptionID;
        this.questionID = questionID;
        this.optionText = optionText;
        this.correct = correct;
    }

    /**
     * Public Constructor
     *
     * @param questionID Question ID
     * @param optionText Answer Option Text
     * @param correct Is the Answer Option Correct
     */
    public AnswerOption(Long questionID, String optionText, Boolean correct) {
        this.answerOptionID = 0L;
        this.questionID = questionID;
        this.optionText = optionText;
        this.correct = correct;
    }

    /**
     * Get Answer Option ID
     * @return Answer Option ID
     */
    public Long getAnswerOptionID() {
        return answerOptionID;
    }

    /**
     * Get Question ID
     * @return Question ID
     */
    public Long getQuestionID() {
        return questionID;
    }

    /**
     * Get Option Text
     * @return Answer Option Text
     */
    public String getOptionText() {
        return optionText;
    }

    /**
     * Is Correct
     * @return Return Answer Option Value - true correct option, false wrong option
     */
    public Boolean isCorrect() {
        return correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerOption that = (AnswerOption) o;
        return Objects.equals(answerOptionID, that.answerOptionID) &&
                Objects.equals(questionID, that.questionID) &&
                Objects.equals(optionText, that.optionText) &&
                Objects.equals(correct, that.correct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerOptionID, questionID, optionText, correct);
    }

    @Override
    public String toString() {
        return "AnswerOption{" +
                "answerOptionID=" + answerOptionID +
                ", questionID=" + questionID +
                ", optionText='" + optionText + '\'' +
                ", correct=" + correct +
                '}';
    }
}


