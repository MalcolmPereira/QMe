/**
 * Name      : com.malcolm.qme.rest.model.QMeAnswerOptionTest.java
 * Date      : 4/5/16
 * Developer : Malcolm
 * Purpose   : QMeAnswerOption Model for REST Service Controller Test
 */
package com.malcolm.qme.rest.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author malcolm
 */
public class QMeAnswerOptionTest {

    @Test
    public void testGetAnswerOptionID() throws Exception {
        QMeAnswerOption qMeAnswerOption = new QMeAnswerOption();
        qMeAnswerOption.setAnswerOptionID(1L);
        assertThat(qMeAnswerOption.getAnswerOptionID(), equalTo(1L));
    }

    @Test
    public void testSetAnswerOptionID() throws Exception {
        QMeAnswerOption qMeAnswerOption = new QMeAnswerOption();
        qMeAnswerOption.setAnswerOptionID(2L);
        assertThat(qMeAnswerOption.getAnswerOptionID(), equalTo(2L));
    }

    @Test
    public void testGetQuestionID() throws Exception {
        QMeAnswerOption qMeAnswerOption = new QMeAnswerOption();
        qMeAnswerOption.setQuestionID(1L);
        assertThat(qMeAnswerOption.getQuestionID(), equalTo(1L));
    }

    @Test
    public void testSetQuestionID() throws Exception {
        QMeAnswerOption qMeAnswerOption = new QMeAnswerOption();
        qMeAnswerOption.setQuestionID(2L);
        assertThat(qMeAnswerOption.getQuestionID(), equalTo(2L));
    }

    @Test
    public void testGetOptionText() throws Exception {
        QMeAnswerOption qMeAnswerOption = new QMeAnswerOption();
        qMeAnswerOption.setOptionText("Option 1");
        assertThat(qMeAnswerOption.getOptionText(), equalTo("Option 1"));
    }

    @Test
    public void testSetOptionText() throws Exception {
        QMeAnswerOption qMeAnswerOption = new QMeAnswerOption();
        qMeAnswerOption.setOptionText("Option 2");
        assertThat(qMeAnswerOption.getOptionText(), equalTo("Option 2"));
    }

    @Test
    public void testGetCorrect() throws Exception {
        QMeAnswerOption qMeAnswerOption = new QMeAnswerOption();
        qMeAnswerOption.setCorrect(Boolean.TRUE);
        assertThat(qMeAnswerOption.getCorrect(), equalTo(Boolean.TRUE));
    }

    @Test
    public void testSetCorrect() throws Exception {
        QMeAnswerOption qMeAnswerOption = new QMeAnswerOption();
        qMeAnswerOption.setCorrect(Boolean.FALSE);
        assertThat(qMeAnswerOption.getCorrect(), equalTo(Boolean.FALSE));
    }

    @Test
    public void testGetAnswerOptionMediaList() throws Exception {
        QMeAnswerOption qMeAnswerOption = new QMeAnswerOption();
        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        QMeAnswerOptionMedia option1 = new QMeAnswerOptionMedia();
        option1.setAnswerOptionID(1L);
        option1.setAnswerOptionMediaID(1L);
        option1.setMedia("Hello Media 1".getBytes());
        answerOptionMediaList.add(option1);

        option1 = new QMeAnswerOptionMedia();
        option1.setAnswerOptionID(2L);
        option1.setAnswerOptionMediaID(2L);
        option1.setMedia("Hello Media 2".getBytes());
        answerOptionMediaList.add(option1);

        qMeAnswerOption.setAnswerOptionMediaList(answerOptionMediaList);

        assertThat(qMeAnswerOption.getAnswerOptionMediaList().size(), equalTo(2));

    }

    @Test
    public void testSetAnswerOptionMediaList() throws Exception {
        QMeAnswerOption qMeAnswerOption = new QMeAnswerOption();
        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        qMeAnswerOption.setAnswerOptionMediaList(answerOptionMediaList);
        assertThat(qMeAnswerOption.getAnswerOptionMediaList().size(), equalTo(0));
    }
}