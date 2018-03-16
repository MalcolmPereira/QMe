/**
 * QMeQuizAnswerOption Model for REST User Quiz Service Controller
 *
 * @author Malcolm
 */
package com.malcolm.qme.rest.model;

import java.util.List;

/**
 * QMeQuizAnswerOption Model for REST Service Controller
 *
 * @author Malcolm
 */
public class QMeQuizAnswerOption extends QMeResource {
    /**
     * Generated Serialized Version Id
     */
    private static final long serialVersionUID = 1323257850480182821L;

    /**
     * Answer Option Id
     */
    private Long answerOptionID;

    /**
     * Question Id
     */
    private Long questionID;

    /**
     * Option Text
     */
    private String optionText;

    /**
     * Selected
     */
    private Boolean selected;

    /**
     * Correct
     */
    private Boolean correct;

    /**
     * Answer Option Media List
     */
    private List<QMeAnswerOptionMedia> answerOptionMediaList;


    /**
     * Get Answer Option ID
     * @return answerOptionID Answer Option ID
     */
    public Long getAnswerOptionID() {
        return answerOptionID;
    }

    /**
     * Set Answer Option ID
     * @param answerOptionID Answer Option ID
     */
    public void setAnswerOptionID(Long answerOptionID) {
        this.answerOptionID = answerOptionID;
    }

    /**
     * Get Question ID
     * @return questionID Question ID
     */
    public Long getQuestionID() {
        return questionID;
    }

    /**
     * Set Question ID
     * @param questionID  Question ID
     */
    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    /**
     * Get Option Text
     * @return optionText Option Text
     */
    public String getOptionText() {
        return optionText;
    }

    /**
     * Set Option Text
     * @param optionText Option Text
     */
    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    /**
     * Get Selected
     * @return selected Selected
     */
    public Boolean getSelected() {
        return selected;
    }

    /**
     * Set Selected
     * @param selected Selected
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    /**
     * Get Correct
     * @return correct Answered Correctly
     */
    public Boolean getCorrect() {
        return correct;
    }

    /**
     * Set Correct
     * @param correct Answered Correctly
     */
    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }


    /**
     * Get Answer Option Media List
     * @return answerOptionMediaList Answer Option Media List
     */
    public List<QMeAnswerOptionMedia> getAnswerOptionMediaList() {
        return answerOptionMediaList;
    }

    /**
     * Set Answer Option Media List
     * @param answerOptionMediaList Answer Option Media List
     */
    public void setAnswerOptionMediaList(List<QMeAnswerOptionMedia> answerOptionMediaList) {
        this.answerOptionMediaList = answerOptionMediaList;
    }
}
