/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeQuestionFixture.java
 * Date      : 3/16/15
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe Question
 */
package com.malcolm.qme.rest.model.fixtures;

import com.malcolm.qme.rest.model.QMeQuestion;

import java.time.LocalDateTime;

/**
 * @author Malcolm
 */
public class QMeQuestionFixture extends QMeResourceFixture<QMeQuestion>{

    /**
     * Return Simple Question
     * @return
     */
    public static QMeQuestion simpleQMeQuestion(){
        return getQMeQuestion();
    }

    /**
     * Return Question With Question Test
     * @param question Question Text
     * @return
     */
    public static QMeQuestion simpleQMeQuestionWithQuestionText(String question){
        QMeQuestion qmeQuestion = getQMeQuestion();
        qmeQuestion.setQuestionText(question);
        return qmeQuestion;
    }

    /**
     * Get Question Fixture Base
     * @return
     */
    private static QMeQuestion getQMeQuestion(){
        QMeQuestion qmeQuestion = new QMeQuestion();
        qmeQuestion.setQuestionID(1L);
        qmeQuestion.setCategoryID(1L);
        qmeQuestion.setQuestionText("Some Question");
        qmeQuestion.setQuestionPoint(1);
        qmeQuestion.setLikes(1L);
        qmeQuestion.setQuestionCreateDate(LocalDateTime.now());
        qmeQuestion.setCreateUserID(1L);
        qmeQuestion.setQuestionUpdateDate(LocalDateTime.now());
        qmeQuestion.setUpdateUserID(1L);
        return qmeQuestion;
    }
}
