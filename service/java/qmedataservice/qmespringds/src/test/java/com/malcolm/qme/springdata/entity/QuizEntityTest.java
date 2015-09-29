/**
 * Name      : com.malcolm.qme.springdata.entity.QuizEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Quiz Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class QuizEntityTest {

    @Test
    public void testEquals() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QuizEntity quizEntity1 = new  QuizEntity("test", "test", 1L,1L, 1L, 1,now,1L, now,1L);
        QuizEntity quizEntity2 = new  QuizEntity("test", "test", 1L,1L, 1L, 1,now,1L, now,1L);
        assertTrue(quizEntity1.equals(quizEntity2));
        assertTrue(quizEntity1.equals(quizEntity1));

        quizEntity1 = new  QuizEntity("test", "test", 1L,1L, 1L, 1,now,1L, now,1L);
        quizEntity1.setQuizId(1L);
        quizEntity2 = new  QuizEntity("test", "test", 1L,1L, 1L, 1,now,1L, now,1L);
        quizEntity2.setQuizId(1L);
        assertTrue(quizEntity1.equals(quizEntity2));

        quizEntity1 = new  QuizEntity("test1", "test1", 1L,1L, 1L, 1,now,1L, now,1L);
        quizEntity2 = new  QuizEntity("test2", "test2", 2L,2L, 2L, 2,now,2L, now,2L);
        assertFalse(quizEntity1.equals(quizEntity2));

        quizEntity1 = new  QuizEntity("test1", "test1", 1L,1L, 1L, 1,now,1L, now,1L);
        quizEntity1.setQuizId(1L);
        quizEntity2 = new  QuizEntity("test2", "test2", 2L,2L, 2L, 2,now,2L, now,2L);
        quizEntity2.setQuizId(2L);
        assertFalse(quizEntity1.equals(quizEntity2));

        assertFalse(quizEntity1.equals(null));
        assertFalse(quizEntity1.equals(""));

    }

    @Test
    public void testHashCode() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QuizEntity quizEntity1 = new  QuizEntity("test", "test", 1L,1L, 1L, 1,now,1L, now,1L);
        QuizEntity quizEntity2 = new  QuizEntity("test", "test", 1L,1L, 1L, 1,now,1L, now,1L);
        assertThat(quizEntity1.hashCode(), equalTo(quizEntity2.hashCode()));
        assertThat(quizEntity1.hashCode(), equalTo(quizEntity1.hashCode()));

        quizEntity1 = new  QuizEntity("test", "test", 1L,1L, 1L, 1,now,1L, now,1L);
        quizEntity1.setQuizId(1L);
        quizEntity2 = new  QuizEntity("test", "test", 1L,1L, 1L, 1,now,1L, now,1L);
        quizEntity2.setQuizId(1L);
        assertThat(quizEntity1.hashCode(), equalTo(quizEntity2.hashCode()));

        quizEntity1 = new  QuizEntity("test1", "test1", 1L,1L, 1L, 1,now,1L, now,1L);
        quizEntity2 = new  QuizEntity("test2", "test2", 2L,2L, 2L, 2,now,2L, now,2L);
        assertNotEquals(quizEntity1.hashCode(), quizEntity2.hashCode());

        quizEntity1 = new  QuizEntity("test1", "test1", 1L,1L, 1L, 1,now,1L, now,1L);
        quizEntity1.setQuizId(1L);
        quizEntity2 = new  QuizEntity("test2", "test2", 2L,2L, 2L, 2,now,2L, now,2L);
        quizEntity2.setQuizId(2L);
        assertNotEquals(quizEntity1.hashCode(), quizEntity2.hashCode());

    }
}