/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeQuestionFixture.java
 * Date      : 3/16/15
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe Question
 */
package com.malcolm.qme.rest.model.fixtures;

import com.malcolm.qme.rest.model.QMeQuestion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
public class QMeQuestionFixture extends QMeResourceFixture<QMeQuestion>{

    /**
     * Return Simple Question
     * @return QMeQuestion
     */
    public static QMeQuestion simpleQMeQuestion(){
        return getQMeQuestion();
    }

    /**
     * Return Question With Question Test
     * @param question Question Text
     * @return QMeQuestion
     */
    public static QMeQuestion simpleQMeQuestionWithQuestionText(String question){
        QMeQuestion qmeQuestion = getQMeQuestion();
        qmeQuestion.setQuestionText(question);
        qmeQuestion.setAnswer("Some Answer");
        return qmeQuestion;
    }

    /**
     * Return Question List
     * @return QMeQuestion List
     */
    public static List<QMeQuestion> simpleQMeQuestionList(){
        List<QMeQuestion> qmeQuestionList = new ArrayList<>();

        QMeQuestion qmeQuestion = getQMeQuestion();
        qmeQuestionList.add(qmeQuestion);

        qmeQuestion = getQMeQuestion();
        qmeQuestion.setQuestionId(qmeQuestion.getQuestionId()+2);
        qmeQuestion.setQuestionText(qmeQuestion.getQuestionText()+ " "+2);
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestionList.add(qmeQuestion);

        qmeQuestion = getQMeQuestion();
        qmeQuestion.setQuestionId(qmeQuestion.getQuestionId()+3);
        qmeQuestion.setQuestionText(qmeQuestion.getQuestionText()+ " "+3);
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestionList.add(qmeQuestion);

        qmeQuestion = getQMeQuestion();
        qmeQuestion.setQuestionId(qmeQuestion.getQuestionId()+4);
        qmeQuestion.setQuestionText(qmeQuestion.getQuestionText()+ " "+4);
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestionList.add(qmeQuestion);

        qmeQuestion = getQMeQuestion();
        qmeQuestion.setQuestionId(qmeQuestion.getQuestionId()+5);
        qmeQuestion.setQuestionText(qmeQuestion.getQuestionText()+ " "+5);
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestionList.add(qmeQuestion);

        return qmeQuestionList;
    }

    /**
     * Get Question Fixture Base
     * @return QMeQuestion
     */
    private static QMeQuestion getQMeQuestion(){
        QMeQuestion qmeQuestion = new QMeQuestion();
        qmeQuestion.setQuestionId(1L);
        qmeQuestion.setCategoryId(1L);
        qmeQuestion.setQuestionText("Some Question");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setQuestionPoint(1);
        qmeQuestion.setLikes(1L);
        qmeQuestion.setQuestionCreateDate(LocalDateTime.now());
        qmeQuestion.setCreateUserID(1L);
        qmeQuestion.setQuestionUpdateDate(LocalDateTime.now());
        qmeQuestion.setUpdateUserID(1L);
        return qmeQuestion;
    }
}
