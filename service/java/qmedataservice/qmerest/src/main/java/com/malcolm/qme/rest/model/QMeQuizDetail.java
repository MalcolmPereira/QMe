/**
 * Name      : com.malcolm.qme.rest.model.QMeQuizDetail.java
 * Date      : 6/20/2017
 * Developer : Malcolm
 * Purpose   : QMeQuizDetail Model for REST Service Controller
 */
package com.malcolm.qme.rest.model;

import java.util.ArrayList;
import java.util.List;

/**
 * QMeQuiz Model for REST Service Controller
 *
 * @author Malcolm
 */
public class QMeQuizDetail extends QMeQuiz {

    /**
     * Generated Serialized Version Id
     */
    private static final long serialVersionUID = 1123217150480182211L;

    /**
     * Question Id List
     */
    private List<Long> questionIdList;

    /**
     * QMe Quiz Question List
     */
    private List<QMeQuestionDetail> qmeQuestionDetailList;

    /**
     * Get Qme Question Detail List
     * @return QMeQuestionDetail List
     */
    public List<QMeQuestionDetail> getQmeQuestionDetailList() {
        return qmeQuestionDetailList;
    }

    /**
     * Set Qme Question Detail List
     * @param qmeQuestionDetailList  QMeQuestionDetail List
     */
    public void setQmeQuestionDetailList(List<QMeQuestionDetail> qmeQuestionDetailList) {
        this.qmeQuestionDetailList = qmeQuestionDetailList;
    }

    /**
     * Add Qme Question Detail List
     * @param qmeQuetionDetail QmeQuestionDetailList
     */
    public void addQmeQuestionDetailList(QMeQuestionDetail qmeQuetionDetail){
        if(qmeQuestionDetailList == null){
            qmeQuestionDetailList = new ArrayList<>();
        }
        qmeQuestionDetailList.add(qmeQuetionDetail);
    }

    /**
     * Get Question Id List
     * @return List QuestionID
     */
    public List<Long> getQuestionIdList() {
        return questionIdList;
    }

    /**
     * Set Question ID
     * @param questionIdList Question Id List
     */
    public void setQuestionIdList(List<Long> questionIdList) {
        this.questionIdList = questionIdList;
    }

    /**
     * Add Question Id List
     * @param questionID Question ID
     */
    public void addQuestionIdList(Long questionID){
        if(questionIdList == null){
            questionIdList = new ArrayList<>();
        }
        if(!questionIdList.contains(questionID)){
            questionIdList.add(questionID);
        }

    }
}
