/**
 * Name      : com.malcolm.qme.rest.model.QMeQuestionTest.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : QMeQuestion Model for REST Service Controller Test
 */
package com.malcolm.qme.rest.model;

import com.malcolm.qme.rest.model.fixtures.QMeQuestionFixture;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author Malcolm
 */
public class QMeQuestionTest {

    @Test
    public void testGetQuestionID() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertThat(qMeQuestion.getQuestionId(), equalTo(1L));
    }

    @Test
    public void testSetQuestionID() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertThat(qMeQuestion.getQuestionId(), equalTo(1L));
        qMeQuestion.setQuestionId(2L);
        assertThat(qMeQuestion.getQuestionId(), equalTo(2L));
    }

    @Test
    public void testGetCategoryID() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertThat(qMeQuestion.getCategoryId(), equalTo(1L));
    }

    @Test
    public void testSetCategoryID() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertThat(qMeQuestion.getCategoryId(), equalTo(1L));
        qMeQuestion.setCategoryId(2L);
        assertThat(qMeQuestion.getCategoryId(), equalTo(2L));
    }

    @Test
    public void testGetQuestionText() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertThat(qMeQuestion.getQuestionText(), equalTo("Some Question"));
    }

    @Test
    public void testSetQuestionText() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertThat(qMeQuestion.getQuestionText(), equalTo("Some Question"));
        qMeQuestion.setQuestionText("Some New Text");
        assertThat(qMeQuestion.getQuestionText(), equalTo("Some New Text"));
    }

    @Test
    public void testGetQuestionPoint() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertThat(qMeQuestion.getQuestionPoint(),equalTo(1));
    }

    @Test
    public void testSetQuestionPoint() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertThat(qMeQuestion.getQuestionPoint(),equalTo(1));
        qMeQuestion.setQuestionPoint(2);
        assertThat(qMeQuestion.getQuestionPoint(),equalTo(2));

    }

    @Test
    public void testGetLikes() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertThat(qMeQuestion.getLikes(),equalTo(1L));
    }

    @Test
    public void testSetLikes() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertThat(qMeQuestion.getLikes(),equalTo(1L));
        qMeQuestion.setLikes(2L);
        assertThat(qMeQuestion.getLikes(),equalTo(2L));
    }

    @Test
    public void testGetQuestionCreateDate() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertNotNull(qMeQuestion.getQuestionCreateDate());
    }

    @Test
    public void testSetQuestionCreateDate() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertNotNull(qMeQuestion.getQuestionCreateDate());
        qMeQuestion.setQuestionCreateDate(LocalDateTime.now());
        assertNotNull(qMeQuestion.getQuestionCreateDate());
    }

    @Test
    public void testGetCreateUserID() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertThat(qMeQuestion.getCreateUserID(),equalTo(1L));
    }

    @Test
    public void testSetCreateUserID() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertThat(qMeQuestion.getCreateUserID(),equalTo(1L));
        qMeQuestion.setCreateUserID(2L);
        assertThat(qMeQuestion.getCreateUserID(),equalTo(2L));
    }

    @Test
    public void testGetQuestionUpdateDate() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertNotNull(qMeQuestion.getQuestionUpdateDate());
    }

    @Test
    public void testSetQuestionUpdateDate() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertNotNull(qMeQuestion.getQuestionUpdateDate());
        qMeQuestion.setQuestionUpdateDate(LocalDateTime.now());
        assertNotNull(qMeQuestion.getQuestionUpdateDate());
    }

    @Test
    public void testGetUpdateUserID() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertThat(qMeQuestion.getUpdateUserID(),equalTo(1L));
    }

    @Test
    public void testSetUpdateUserID() throws Exception {
        QMeQuestion qMeQuestion = QMeQuestionFixture.simpleQMeQuestion();
        assertThat(qMeQuestion.getUpdateUserID(),equalTo(1L));
        qMeQuestion.setUpdateUserID(2L);
        assertThat(qMeQuestion.getUpdateUserID(),equalTo(2L));
    }
}