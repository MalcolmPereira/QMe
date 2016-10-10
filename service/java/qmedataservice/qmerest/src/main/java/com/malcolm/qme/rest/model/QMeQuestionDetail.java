/**
 * Name      : com.malcolm.qme.rest.model.QMeQuestionDetail.java
 * Date      : 1/2/16
 * Developer : Malcolm
 * Purpose   : QMeQuestionDetail Model for REST Service Controller
 */
package com.malcolm.qme.rest.model;

import java.util.ArrayList;
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
     * Anser Reference Media List
     */
    private List<QMeAnswerReferenceMedia> answerReferenceMediaList;

    /**
     * Answer Option List
     */
    private List<QMeAnswerOption> answerOptionList;

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

    /**
     * Get Answer Option List
     * @return Answer Option List
     */
    public List<QMeAnswerOption> getAnswerOptionList() {
        if(answerOptionList == null){
            answerOptionList = new ArrayList<>();
        }
        return answerOptionList;
    }

    /**
     * Set Answer Option List
     * @param answerOptionList Answer Option List
     */
    public void setAnswerOptionList(List<QMeAnswerOption> answerOptionList) {
        this.answerOptionList = answerOptionList;
    }
}
