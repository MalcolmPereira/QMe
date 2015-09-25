/**
 * Name      : com.malcolm.qme.core.domain.QuizQuestionTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Question Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class QuizQuestionTest {

    @Test
    public void testGetQuizQuestionID() throws Exception {
        QuizQuestion quizQuestion = new QuizQuestion(1L, 1L, 1L);
        assertThat(quizQuestion.getQuizQuestionID(), equalTo(1L));
        quizQuestion = new QuizQuestion(1L, 1L);
        assertThat(quizQuestion.getQuizQuestionID(), equalTo(0L));
    }

    @Test
    public void testGetQuizID() throws Exception {
        QuizQuestion quizQuestion = new QuizQuestion(1L, 1L, 1L);
        assertThat(quizQuestion.getQuizID(), equalTo(1L));
        quizQuestion = new QuizQuestion(1L, 1L);
        assertThat(quizQuestion.getQuizID(), equalTo(1L));
    }

    @Test
    public void testGetQuestionID() throws Exception {
        QuizQuestion quizQuestion = new QuizQuestion(1L, 1L, 1L);
        assertThat(quizQuestion.getQuestionID(), equalTo(1L));
        quizQuestion = new QuizQuestion(1L, 1L);
        assertThat(quizQuestion.getQuestionID(), equalTo(1L));
    }

    @Test
    public void testEquals(){
        QuizQuestion quizQuestion1 = new QuizQuestion(1L, 1L, 1L);
        QuizQuestion quizQuestion2 = new QuizQuestion(1L, 1L, 1L);
        assertTrue(quizQuestion1.equals(quizQuestion2));

        quizQuestion1 = new QuizQuestion(1L, 1L, 1L);
        quizQuestion2 = new QuizQuestion(1L, 2L, 3L);
        assertFalse(quizQuestion1.equals(quizQuestion2));

        quizQuestion1 =  new QuizQuestion(1L, 1L);
        quizQuestion2 =  new QuizQuestion(1L, 1L);
        assertTrue(quizQuestion1.equals(quizQuestion2));

        quizQuestion1 =  new QuizQuestion(1L, 1L);
        quizQuestion2 =  new QuizQuestion(1L, 2L);
        assertFalse(quizQuestion1.equals(quizQuestion2));
    }

    @Test
    public void testHashCode(){
        QuizQuestion quizQuestion1 = new QuizQuestion(1L, 1L, 1L);
        QuizQuestion quizQuestion2 = new QuizQuestion(1L, 1L, 1L);
        assertThat(quizQuestion1.hashCode(),equalTo(quizQuestion2.hashCode()));
        quizQuestion1 = new QuizQuestion(1L, 1L, 1L);
        quizQuestion2 = new QuizQuestion(1L, 2L, 3L);
        assertNotEquals(quizQuestion1.hashCode(),quizQuestion2.hashCode());
        quizQuestion1 =  new QuizQuestion(1L, 1L);
        quizQuestion2 =  new QuizQuestion(1L, 1L);
        assertThat(quizQuestion1.hashCode(),equalTo(quizQuestion2.hashCode()));
        quizQuestion1 =  new QuizQuestion(1L, 1L);
        quizQuestion2 =  new QuizQuestion(1L, 2L);
        assertNotEquals(quizQuestion1.hashCode(),quizQuestion2.hashCode());
    }
}