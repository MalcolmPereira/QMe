/**
 * Name      : com.malcolm.qme.rest.model.QMeQuestionDetail.java
 * Date      : 1/2/16
 * Developer : Malcolm
 * Purpose   : QMeQuestionDetail Model for REST Service Controller
 */
package com.malcolm.qme.rest.model;

import java.util.List;

/**
 * QMeQuestionDetail Model for REST Service Controller
 *
 * @author Malcolm
 */
public class QMeQuestionDetail extends QMeQuestion {
    /**
     * Generated Serialized Version Id
     */
    private static final long serialVersionUID = 1343257850480182833L;

    /**
     * Question Answer
     */
    private String answer;

    /**
     * Anser Reference Media List
     */
    private List<QMeAnswerReferenceMedia> answerReferenceMediaList;

    /**
     * Get Answer Description
     * @return Answer Description
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Set Answer Description
     * @param answer Answer Description to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Get Answer Reference Media List
     * @return Answer Reference Media List
     */
    public List<QMeAnswerReferenceMedia> getAnswerReferenceMediaList() {
        return answerReferenceMediaList;
    }

    /**
     * Set Answer Reference Media List
     * @param answerReferenceMediaList Answer Reference Media List to set
     */
    public void setAnswerReferenceMediaList(List<QMeAnswerReferenceMedia> answerReferenceMediaList) {
        this.answerReferenceMediaList = answerReferenceMediaList;
    }
}
