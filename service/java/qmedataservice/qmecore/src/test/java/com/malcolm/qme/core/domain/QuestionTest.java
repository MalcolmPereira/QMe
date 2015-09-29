/**
 * Name      : com.malcolm.qme.core.domain.QuestionTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe Question Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class QuestionTest {

    @Test
    public void testGetQuestionID() throws Exception {
        Question question = new Question(1L, 1L, "Some Question Text","Some Answer", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        assertThat(question.getQuestionID(), equalTo(1L));
        question = new Question(1L, "Some Question Text","Some Answer", 1, 1L);
        assertThat(question.getQuestionID(), equalTo(0L));
        question = new Question(1L, "Some Question Text","Some Answer", 1L);
        assertThat(question.getQuestionID(), equalTo(0L));
    }

    @Test
    public void testGetCategoryID() throws Exception {
        Question question = new Question(1L, 1L, "Some Question Text","Some Answer", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        assertThat(question.getCategoryID(), equalTo(1L));
        question = new Question(1L, "Some Question Text","Some Answer", 1, 1L);
        assertThat(question.getCategoryID(), equalTo(1L));
        question = new Question(1L, "Some Question Text","Some Answer", 1L);
        assertThat(question.getCategoryID(), equalTo(1L));
    }

    @Test
    public void testGetQuestionText() throws Exception {
        Question question = new Question(1L, 1L, "Some Question Text","Some Answer", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        assertThat(question.getQuestionText(), equalTo("Some Question Text"));
        question = new Question(1L, "Some Question Text","Some Answer", 1, 1L);
        assertThat(question.getQuestionText(), equalTo("Some Question Text"));
        question = new Question(1L, "Some Question Text","Some Answer", 1L);
        assertThat(question.getQuestionText(), equalTo("Some Question Text"));
    }

    @Test
    public void testGetAnswer() throws Exception {
        Question question = new Question(1L, 1L, "Some Question Text","Some Answer", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        assertThat(question.getAnswer(), equalTo("Some Answer"));
        question = new Question(1L, "Some Question Text","Some Answer", 1, 1L);
        assertThat(question.getAnswer(), equalTo("Some Answer"));
        question = new Question(1L, "Some Question Text","Some Answer", 1L);
        assertThat(question.getAnswer(), equalTo("Some Answer"));
    }

    @Test
    public void testGetQuestionPoint() throws Exception {
        Question question = new Question(1L, 1L, "Some Question Text","Some Answer", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        assertThat(question.getQuestionPoint(), equalTo(1));
        question = new Question(1L, "Some Question Text","Some Answer", 1, 1L);
        assertThat(question.getQuestionPoint(), equalTo(1));
        question = new Question(1L, "Some Question Text","Some Answer", 1L);
        assertThat(question.getQuestionPoint(), equalTo(1));
    }

    @Test
    public void testGetLikes() throws Exception {
        Question question = new Question(1L, 1L, "Some Question Text","Some Answer", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        assertThat(question.getLikes(), equalTo(1L));
        question = new Question(1L, "Some Question Text","Some Answer", 1, 1L);
        assertThat(question.getLikes(), equalTo(0L));
        question = new Question(1L, "Some Question Text","Some Answer", 1L);
        assertThat(question.getLikes(), equalTo(0L));
    }

    @Test
    public void testGetQuestionCreateDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Question question = new Question(1L, 1L, "Some Question Text","Some Answer", 1, 1L,now, 1L,now, 1L);
        assertThat(question.getQuestionCreateDate(), equalTo(now));
        question = new Question(1L, "Some Question Text","Some Answer", 1, 1L);
        assertNotNull(question.getQuestionCreateDate());
        question = new Question(1L, "Some Question Text","Some Answer", 1L);
        assertNotNull(question.getQuestionCreateDate());
    }

    @Test
    public void testGetCreateUserID() throws Exception {
        Question question = new Question(1L, 1L, "Some Question Text","Some Answer", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        assertThat(question.getCreateUserID(), equalTo(1L));
        question = new Question(1L, "Some Question Text","Some Answer", 1, 1L);
        assertThat(question.getCreateUserID(), equalTo(1L));
        question = new Question(1L, "Some Question Text","Some Answer", 1L);
        assertThat(question.getCreateUserID(), equalTo(1L));
    }

    @Test
    public void testGetQuestionUpdateDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Question question = new Question(1L, 1L, "Some Question Text","Some Answer", 1, 1L,now, 1L,now, 1L);
        assertThat(question.getQuestionUpdateDate(), equalTo(now));
        question = new Question(1L, "Some Question Text","Some Answer", 1, 1L);
        assertNotNull(question.getQuestionUpdateDate());
        question = new Question(1L, "Some Question Text","Some Answer", 1L);
        assertNotNull(question.getQuestionUpdateDate());
    }

    @Test
    public void testGetUpdateUserID() throws Exception {
        Question question = new Question(1L, 1L, "Some Question Text","Some Answer", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        assertThat(question.getUpdateUserID(), equalTo(1L));
        question = new Question(1L, "Some Question Text","Some Answer", 1, 1L);
        assertThat(question.getUpdateUserID(), equalTo(0L));
        question = new Question(1L, "Some Question Text","Some Answer", 1L);
        assertThat(question.getUpdateUserID(), equalTo(0L));
    }

    @Test
    public void testEquals(){
        Question question1 = new Question(1L, 1L, "Some Question Text","Some Answer", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        Question question2 = new Question(1L, 1L, "Some Question Text","Some Answer", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        assertTrue(question1.equals(question2));
        assertTrue(question1.equals(question1));
        question1 = new Question(1L, 1L, "Some Question Text1","Some Answer1", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        question2 = new Question(2L, 2L, "Some Question Text2","Some Answer2", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        assertFalse(question1.equals(question2));
        question1 = new Question(1L, "Some Question Text","Some Answer", 1, 1L);
        question2 = new Question(1L, "Some Question Text","Some Answer", 1, 1L);
        assertTrue(question1.equals(question2));
        question1 = new Question(1L, "Some Question Text1","Some Answer", 1, 1L);
        question2 = new Question(2L, "Some Question Text2","Some Answer", 1, 1L);
        assertFalse(question1.equals(question2));
        question1 = new Question(1L, "Some Question Text","Some Answer", 1L);
        question2 = new Question(1L, "Some Question Text","Some Answer", 1L);
        assertTrue(question1.equals(question2));
        question1 = new Question(1L, "Some Question Text1","Some Answer1", 1L);
        question2 = new Question(2L, "Some Question Text2","Some Answer2", 1L);
        assertFalse(question1.equals(question2));
        assertFalse(question1.equals(null));
        assertFalse(question1.equals(""));
    }

    @Test
    public void testHashCode(){
        Question question1 = new Question(1L, 1L, "Some Question Text","Some Answer", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        Question question2 = new Question(1L, 1L, "Some Question Text","Some Answer", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        assertThat(question1.hashCode(),equalTo(question2.hashCode()));
        question1 = new Question(1L, 1L, "Some Question Text1","Some Answer1", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        question2 = new Question(2L, 2L, "Some Question Text2","Some Answer2", 1, 1L,LocalDateTime.now(), 1L,LocalDateTime.now(), 1L);
        assertNotEquals(question1.hashCode(),question2.hashCode());
        question1 = new Question(1L, "Some Question Text","Some Answer", 1, 1L);
        question2 = new Question(1L, "Some Question Text","Some Answer", 1, 1L);
        assertThat(question1.hashCode(), equalTo(question2.hashCode()));
        question1 = new Question(1L, "Some Question Text1","Some Answer", 1, 1L);
        question2 = new Question(2L, "Some Question Text2","Some Answer", 1, 1L);
        assertNotEquals(question1.hashCode(),question2.hashCode());
        question1 = new Question(1L, "Some Question Text","Some Answer", 1L);
        question2 = new Question(1L, "Some Question Text","Some Answer", 1L);
        assertThat(question1.hashCode(),equalTo(question2.hashCode()));
        question1 = new Question(1L, "Some Question Text1","Some Answer1", 1L);
        question2 = new Question(2L, "Some Question Text2","Some Answer2", 1L);
        assertNotEquals(question1.hashCode(), question2.hashCode());
    }
}