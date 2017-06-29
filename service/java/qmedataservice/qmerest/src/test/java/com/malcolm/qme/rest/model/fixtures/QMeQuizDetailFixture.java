/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeQuizDetailFixture.java
 * Date      : 6/27/2017
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe Quiz Details
 */
package com.malcolm.qme.rest.model.fixtures;

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


}
