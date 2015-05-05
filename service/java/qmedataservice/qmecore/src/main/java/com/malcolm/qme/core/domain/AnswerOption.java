/**
 * Name      : com.malcolm.qme.core.domain.AnswerOption.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Answer Option Domain Class
 */

package com.malcolm.qme.core.domain;

/**
 * @Author: Malcolm
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
     * @param answerOptionID
     * @param questionID
     * @param optionText
     * @param correct
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
     * @param questionID
     * @param optionText
     * @param correct
     */
    public AnswerOption(Long questionID, String optionText, Boolean correct) {
        this.answerOptionID = 0L;
        this.questionID = questionID;
        this.optionText = optionText;
        this.correct = correct;
    }

    /**
     * Get Answer Option ID
     * @return
     */
    public Long getAnswerOptionID() {
        return answerOptionID;
    }

    /**
     * Get Question ID
     * @return
     */
    public Long getQuestionID() {
        return questionID;
    }

    /**
     * Get Option Text
     * @return
     */
    public String getOptionText() {
        return optionText;
    }

    /**
     * Is Correct
     * @return
     */
    public Boolean isCorrect() {
        return correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerOption that = (AnswerOption) o;

        if (!getAnswerOptionID().equals(that.getAnswerOptionID())) return false;
        if (!getQuestionID().equals(that.getQuestionID())) return false;
        if (!getOptionText().equals(that.getOptionText())) return false;
        return correct.equals(that.correct);

    }

    @Override
    public int hashCode() {
        int result = getAnswerOptionID().hashCode();
        result = 31 * result + getQuestionID().hashCode();
        result = 31 * result + getOptionText().hashCode();
        result = 31 * result + correct.hashCode();
        return result;
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


