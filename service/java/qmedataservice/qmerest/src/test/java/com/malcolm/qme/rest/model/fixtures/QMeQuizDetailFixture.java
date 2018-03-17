/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeQuizDetailFixture.java
 * Date      : 6/27/2017
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe Quiz Details
 */
package com.malcolm.qme.rest.model.fixtures;

import com.malcolm.qme.rest.model.QMeAnswerOption;
import com.malcolm.qme.rest.model.QMeQuestionDetail;
import com.malcolm.qme.rest.model.QMeQuizDetail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Test Fixtures for QMe Quiz Details
 * @author Malcolm
 */
public class QMeQuizDetailFixture extends QMeResourceFixture<QMeQuizDetail>{

    /**
     * Get QMe Quiz Detail
     * @return QMeQuizDetail
     */
    public static QMeQuizDetail simpleQMeQuizDetail(){
        return getQMeQuizDetail();
    }


    /**
     * Get QMe Quiz Details with Question Id
     * @return
     */
    public static QMeQuizDetail qMeQuizDetailWithQuestionsIdsAndDetails() {
        QMeQuizDetail qMeQuizDetail = getQMeQuizDetail();
        qMeQuizDetail.addQuestionIdList(1L);
        qMeQuizDetail.addQuestionIdList(2L);
        qMeQuizDetail.addQuestionIdList(3L);
        qMeQuizDetail.addQuestionIdList(4L);
        qMeQuizDetail.addQuestionIdList(5L);
        QMeQuestionDetail qmeQuestionDetail = getQMeQuestionDetail(1L);
        qmeQuestionDetail.addAnswerOptionList(getQMeAnswerOption(1L,1L));
        qmeQuestionDetail.addAnswerOptionList(getQMeAnswerOption(2L,1L));
        qmeQuestionDetail.addAnswerOptionList(getQMeAnswerOption(3L,1L));
        qMeQuizDetail.addQmeQuestionDetailList(qmeQuestionDetail);
        qmeQuestionDetail = getQMeQuestionDetail(2L);
        qmeQuestionDetail.addAnswerOptionList(getQMeAnswerOption(1L,2L));
        qmeQuestionDetail.addAnswerOptionList(getQMeAnswerOption(2L,2L));
        qmeQuestionDetail.addAnswerOptionList(getQMeAnswerOption(3L,2L));
        qMeQuizDetail.addQmeQuestionDetailList(qmeQuestionDetail);
        return qMeQuizDetail;
    }

    /**
     * Get QMe Quiz Detail
     * @return QMeQuizDetail
     */
    public static QMeQuizDetail qMeQuizDetailWithQuestions(){
        QMeQuizDetail qMeQuizDetail = getQMeQuizDetail();
        qMeQuizDetail.addQuestionIdList(1L);
        qMeQuizDetail.addQuestionIdList(2L);
        qMeQuizDetail.addQuestionIdList(3L);
        qMeQuizDetail.addQuestionIdList(4L);
        qMeQuizDetail.addQuestionIdList(5L);
        return qMeQuizDetail;
    }

    /**
     * Return QMe Quiz Detail List
     * @return List QMeQuizDetail
     */
    public static List<QMeQuizDetail> simpleQMeQuizDetailList(){
        List<QMeQuizDetail> qMeQuizDetailList = new ArrayList<>();
        QMeQuizDetail qMeQuizDetail = qMeQuizDetailWithQuestions();
        qMeQuizDetail.setQuizID(1L);
        qMeQuizDetailList.add(qMeQuizDetail);
        qMeQuizDetail = qMeQuizDetailWithQuestions();
        qMeQuizDetail.setQuizID(2L);
        qMeQuizDetailList.add(qMeQuizDetail);
        qMeQuizDetail = qMeQuizDetailWithQuestions();
        qMeQuizDetail.setQuizID(3L);
        qMeQuizDetailList.add(qMeQuizDetail);
        qMeQuizDetail = qMeQuizDetailWithQuestions();
        qMeQuizDetail.setQuizID(4L);
        qMeQuizDetailList.add(qMeQuizDetail);
        qMeQuizDetail = qMeQuizDetailWithQuestions();
        qMeQuizDetail.setQuizID(5L);
        qMeQuizDetailList.add(qMeQuizDetail);
        return qMeQuizDetailList;
    }

    /**
     * Get QMe Quiz Detail
     * @return QMeQuizDetail
     */
    private static QMeQuizDetail getQMeQuizDetail() {
        QMeQuizDetail qMeQuizDetail = new QMeQuizDetail();
        qMeQuizDetail.setQuizID(1L);
        qMeQuizDetail.setQuizName("Some Quiz Name");
        qMeQuizDetail.setQuizDesc("Some Quiz Desc");
        qMeQuizDetail.setCategoryID(1L);
        qMeQuizDetail.setQuizMaxAttempts(3);
        return qMeQuizDetail;
    }


    /**
     * Get Question Detail
     * @param questionID Question ID
     * @return QMeQuestionDetail Question Detail
     */
    private static QMeQuestionDetail getQMeQuestionDetail(Long questionID) {
        QMeQuestionDetail qmeQuestionDetail = new QMeQuestionDetail();
        qmeQuestionDetail.setQuestionId(questionID);
        qmeQuestionDetail.setCategoryId(1L);
        qmeQuestionDetail.setQuestionText("This is a test");
        qmeQuestionDetail.setAnswer("Some Answer");
        qmeQuestionDetail.setQuestionPoint(1);
        qmeQuestionDetail.setLikes(1L);
        qmeQuestionDetail.setQuestionCreateDate(LocalDateTime.now());
        qmeQuestionDetail.setCreateUserID(1L);
        qmeQuestionDetail.setQuestionUpdateDate(LocalDateTime.now());
        qmeQuestionDetail.setUpdateUserID(1L);
        qmeQuestionDetail.setCreateUserName("Hello");
        qmeQuestionDetail.setUpdateUserName("test");
        qmeQuestionDetail.setCategoryName("Some");
        return qmeQuestionDetail;
    }

    /**
     * Get QMe Answer Option
     * @return QMeAnswerOption Answer Option
     */
    private static QMeAnswerOption getQMeAnswerOption(Long AnswerOptionID, Long questionID) {
        QMeAnswerOption qMeAnswerOption = new QMeAnswerOption();
        qMeAnswerOption.setAnswerOptionID(AnswerOptionID);
        qMeAnswerOption.setQuestionID(questionID);
        qMeAnswerOption.setOptionText("some option");
        if(AnswerOptionID == 1){
            qMeAnswerOption.setCorrect(Boolean.TRUE);
        }else{
            qMeAnswerOption.setCorrect(Boolean.FALSE);
        }
        return qMeAnswerOption;
    }


}
