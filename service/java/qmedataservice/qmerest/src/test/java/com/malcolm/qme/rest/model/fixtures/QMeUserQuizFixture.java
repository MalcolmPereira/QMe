/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeUserQuizFixture.java
 * Date      : 10/12/2017
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe User Quiz
 */
package com.malcolm.qme.rest.model.fixtures;

import com.malcolm.qme.rest.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Test Fixtures for QMe User Quiz
 * @author Malcolm
 */
public class QMeUserQuizFixture {

    /**
     * Get QMe User Quiz
     * @return QMeUserQuiz
     */
    public static QMeUserQuizDetail simpleQMeQuizDetail(){
        return getQMeUserQuizDetail();
    }

    /**
     * Get QMe Quiz Details with Question Id
     * @return
     */
    public static QMeUserQuizDetail getQMeUserQuizDetailWithQuestions() {
        QMeUserQuizDetail qMeUserQuizDetail = getQMeUserQuizDetail();
        qMeUserQuizDetail.setUserQuizToken("somequiztoken");
        QMeQuizQuestionDetail  qMeQuizQuestionDetail = getQMeQuestionDetail(1L);
        qMeQuizQuestionDetail.addAnswerOptionList(getQMeQuizAnswerOption(1L, 1L));
        qMeQuizQuestionDetail.addAnswerOptionList(getQMeQuizAnswerOption(2L, 1L));
        qMeQuizQuestionDetail.addAnswerOptionList(getQMeQuizAnswerOption(3L, 1L));
        qMeUserQuizDetail.addQmeQuestionDetailList(qMeQuizQuestionDetail);
        qMeQuizQuestionDetail = getQMeQuestionDetail(2L);
        qMeQuizQuestionDetail.addAnswerOptionList(getQMeQuizAnswerOption(1L, 2L));
        qMeQuizQuestionDetail.addAnswerOptionList(getQMeQuizAnswerOption(2L, 2L));
        qMeQuizQuestionDetail.addAnswerOptionList(getQMeQuizAnswerOption(3L, 2L));
        qMeUserQuizDetail.addQmeQuestionDetailList(qMeQuizQuestionDetail);
        return qMeUserQuizDetail;
    }

    /**
     * Get QMe User Quiz List
     * @return QMeUserQuiz List
     */
    public static List<QMeUserQuizDetail> simpleQMeQuizDetailList(){
        List<QMeUserQuizDetail> userQuizList = new ArrayList<>();
        QMeUserQuizDetail qMeUserQuiz = getQMeUserQuizDetail();
        qMeUserQuiz.setUserQuizID(1L);
        userQuizList.add(qMeUserQuiz);
        qMeUserQuiz = getQMeUserQuizDetail();
        qMeUserQuiz.setUserQuizID(2L);
        userQuizList.add(qMeUserQuiz);
        qMeUserQuiz = getQMeUserQuizDetail();
        qMeUserQuiz.setUserQuizID(3L);
        userQuizList.add(qMeUserQuiz);
        qMeUserQuiz = getQMeUserQuizDetail();
        qMeUserQuiz.setUserQuizID(4L);
        userQuizList.add(qMeUserQuiz);
        qMeUserQuiz = getQMeUserQuizDetail();
        qMeUserQuiz.setUserQuizID(5L);
        userQuizList.add(qMeUserQuiz);
        return userQuizList;
    }

    /**
     * Get QMe User Quiz
     * @return QMeUserQuiz
     */
    private static QMeUserQuizDetail getQMeUserQuizDetail() {
        QMeUserQuizDetail qMeUserQuiz = new QMeUserQuizDetail();
        qMeUserQuiz.setUserQuizID(1L);
        qMeUserQuiz.setUserID(1L);
        qMeUserQuiz.setQuizID(1L);
        qMeUserQuiz.setCategoryID(1L);
        qMeUserQuiz.setQuizStartDate(LocalDateTime.now());
        qMeUserQuiz.setQuizEndDate(LocalDateTime.now());
        qMeUserQuiz.setUserQuizToken("sometoken");
        qMeUserQuiz.setQuizUserScore(10);
        qMeUserQuiz.setQuizMaxScore(10);
        return qMeUserQuiz;
    }

    /**
     * Get Quiz Question Detail
     * @param questionID Question ID
     * @return QMeQuestionDetail Question Detail
     */
    private static QMeQuizQuestionDetail getQMeQuestionDetail(Long questionID) {
        QMeQuizQuestionDetail qmeQuestionDetail = new QMeQuizQuestionDetail();
        qmeQuestionDetail.setQuestionId(questionID);
        qmeQuestionDetail.setCategoryId(1L);
        qmeQuestionDetail.setQuestionText("This is a test");
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
     * Get QMe Quiz Answer Option
     * @return QMeAnswerOption Answer Option
     */
    private static QMeQuizAnswerOption getQMeQuizAnswerOption(Long AnswerOptionID, Long questionID) {
        QMeQuizAnswerOption qMeAnswerOption = new QMeQuizAnswerOption();
        qMeAnswerOption.setAnswerOptionID(AnswerOptionID);
        qMeAnswerOption.setQuestionID(questionID);
        qMeAnswerOption.setOptionText("some option");
        if(AnswerOptionID ==1){
            qMeAnswerOption.setSelected(Boolean.TRUE);
        }else{
            qMeAnswerOption.setSelected(Boolean.FALSE);
        }
        return qMeAnswerOption;
    }

}
