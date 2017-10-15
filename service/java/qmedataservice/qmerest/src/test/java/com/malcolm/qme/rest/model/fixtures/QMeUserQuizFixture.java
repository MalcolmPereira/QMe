/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeUserQuizFixture.java
 * Date      : 10/12/2017
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe User Quiz
 */
package com.malcolm.qme.rest.model.fixtures;

import com.malcolm.qme.rest.model.QMeUserQuiz;

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
    public static QMeUserQuiz simpleQMeQuizDetail(){
        return getQMeUserQuizDetail();
    }

    /**
     * Get QMe User Quiz List
     * @return QMeUserQuiz List
     */
    public static List<QMeUserQuiz> simpleQMeQuizDetailList(){
        List<QMeUserQuiz> userQuizList = new ArrayList<>();
        QMeUserQuiz qMeUserQuiz = getQMeUserQuizDetail();
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
    private static QMeUserQuiz getQMeUserQuizDetail() {
        QMeUserQuiz qMeUserQuiz = new QMeUserQuiz();
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


}
