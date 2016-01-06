/**
 * QMeAnswerOption Model for REST Service Controller
 *
 * @author Malcolm
 */
package com.malcolm.qme.rest.model;

import java.util.List;

/**
 * QMeQuestion Model for REST Service Controller
 *
 * @author Malcolm
 */
public class QMeAnswerOption extends QMeResource {

    /**
     * Generated Serialized Version Id
     */
    private static final long serialVersionUID = 1323257850480182822L;

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
     * Correct
     */
    private Boolean correct;

    /**
     * Answer Option Media List
     */
    private List<QMeAnswerOptionMedia> answerOptionMediaList;

    /**
     * Get Answer Option Id
     * @return Answer Option Id
     */
    public Long getAnswerOptionID() {
        return answerOptionID;
    }

    /**
     * Set Answer Option Id
     * @param answerOptionID Answer Option Id
     */
    public void setAnswerOptionID(Long answerOptionID) {
        this.answerOptionID = answerOptionID;
    }

    /**
     * Get Question Id
     * @return Question Id
     */
    public Long getQuestionID() {
        return questionID;
    }

    /**
     * Set Question Id
     * @param questionID Question Id
     */
    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    /**
     * Get Option Text
     * @return Option Text
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
     * Get Correct
     * @return Correct True / Not Correct False
     */
    public Boolean getCorrect() {
        return correct;
    }

    /**
     * Set Correct
     * @param correct Whether Answer Option is Correct Correct True / Not Correct False
     */
    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    /**
     * Get Answer Option Media List
     * @return Answer Option Media List
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


