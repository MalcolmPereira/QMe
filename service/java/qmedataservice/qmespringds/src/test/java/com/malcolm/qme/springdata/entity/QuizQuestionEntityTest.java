/**
 * Name      : com.malcolm.qme.springdata.entity.QuizQuestionEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Quiz Question Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class QuizQuestionEntityTest {

    @Test
    public void testEquals() throws Exception {
        QuizQuestionEntity quizQuestionEntity1 = new  QuizQuestionEntity(1L, 1L);
        QuizQuestionEntity quizQuestionEntity2 = new  QuizQuestionEntity(1L, 1L);
        assertTrue(quizQuestionEntity1.equals(quizQuestionEntity2));
        assertTrue(quizQuestionEntity1.equals(quizQuestionEntity1));


        quizQuestionEntity1 = new  QuizQuestionEntity(1L, 1L);
        quizQuestionEntity1.setQuizQuestionId(1L);
        quizQuestionEntity2 = new  QuizQuestionEntity(1L, 1L);
        quizQuestionEntity2.setQuizQuestionId(1L);
        assertTrue(quizQuestionEntity1.equals(quizQuestionEntity2));

        quizQuestionEntity1 = new  QuizQuestionEntity(1L, 1L);
        quizQuestionEntity2 = new  QuizQuestionEntity(2L, 2L);
        assertFalse(quizQuestionEntity1.equals(quizQuestionEntity2));

        quizQuestionEntity1 = new  QuizQuestionEntity(1L, 1L);
        quizQuestionEntity1.setQuizQuestionId(1L);
        quizQuestionEntity2 = new  QuizQuestionEntity(2L, 2L);
        quizQuestionEntity2.setQuizQuestionId(2L);
        assertFalse(quizQuestionEntity1.equals(quizQuestionEntity2));

        assertFalse(quizQuestionEntity1.equals(null));
        assertFalse(quizQuestionEntity1.equals(""));


    }

    @Test
    public void testHashCode() throws Exception {
        QuizQuestionEntity quizQuestionEntity1 = new  QuizQuestionEntity(1L, 1L);
        QuizQuestionEntity quizQuestionEntity2 = new  QuizQuestionEntity(1L, 1L);
        assertThat(quizQuestionEntity1.hashCode(), equalTo(quizQuestionEntity2.hashCode()));
        assertThat(quizQuestionEntity1.hashCode(), equalTo(quizQuestionEntity1.hashCode()));

        quizQuestionEntity1 = new  QuizQuestionEntity(1L, 1L);
        quizQuestionEntity1.setQuizQuestionId(1L);
        quizQuestionEntity2 = new  QuizQuestionEntity(1L, 1L);
        quizQuestionEntity2.setQuizQuestionId(1L);
        assertThat(quizQuestionEntity1.hashCode(), equalTo(quizQuestionEntity2.hashCode()));


        quizQuestionEntity1 = new  QuizQuestionEntity(1L, 1L);
        quizQuestionEntity2 = new  QuizQuestionEntity(2L, 2L);
        assertNotEquals(quizQuestionEntity1.hashCode(), quizQuestionEntity2.hashCode());

        quizQuestionEntity1 = new  QuizQuestionEntity(1L, 1L);
        quizQuestionEntity1.setQuizQuestionId(1L);
        quizQuestionEntity2 = new  QuizQuestionEntity(2L, 2L);
        quizQuestionEntity2.setQuizQuestionId(2L);
        assertNotEquals(quizQuestionEntity1.hashCode(), quizQuestionEntity2.hashCode());

    }
}