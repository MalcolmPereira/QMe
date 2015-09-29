/**
 * Name      : com.malcolm.qme.springdata.entity.QuestionEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Question Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class QuestionEntityTest {

    @Test
    public void testEquals() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QuestionEntity questionEntity1 = new QuestionEntity(1L, "test","test", 1, 1L, now , 1L, now, 1L);
        QuestionEntity questionEntity2 = new QuestionEntity(1L, "test","test", 1, 1L, now , 1L, now, 1L);
        assertTrue(questionEntity1.equals(questionEntity2));
        assertTrue(questionEntity1.equals(questionEntity1));

        questionEntity1 = new QuestionEntity(1L, "test","test", 1, 1L, now , 1L, now, 1L);
        questionEntity1.setQuestionId(1L);
        questionEntity2 = new QuestionEntity(1L, "test","test", 1, 1L, now , 1L, now, 1L);
        questionEntity2.setQuestionId(1L);
        assertTrue(questionEntity1.equals(questionEntity2));

        questionEntity1 = new QuestionEntity(1L, "test1","test1", 1, 1L, now , 1L, now, 1L);
        questionEntity2 = new QuestionEntity(2L, "test2","test2", 2, 2L, now , 2L, now, 2L);
        assertFalse(questionEntity1.equals(questionEntity2));

        questionEntity1 = new QuestionEntity(1L, "test1","test1", 1, 1L, now , 1L, now, 1L);
        questionEntity1.setQuestionId(1L);
        questionEntity2 = new QuestionEntity(2L, "test2","test2", 2, 2L, now , 2L, now, 2L);
        questionEntity2.setQuestionId(2L);
        assertFalse(questionEntity1.equals(questionEntity2));

        assertFalse(questionEntity1.equals(null));
        assertFalse(questionEntity1.equals(""));

    }

    @Test
    public void testHashCode() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QuestionEntity questionEntity1 = new QuestionEntity(1L, "test","test", 1, 1L, now , 1L, now, 1L);
        QuestionEntity questionEntity2 = new QuestionEntity(1L, "test","test", 1, 1L, now , 1L, now, 1L);
        assertThat(questionEntity1.hashCode(), equalTo(questionEntity2.hashCode()));
        assertThat(questionEntity1.hashCode(), equalTo(questionEntity1.hashCode()));

        questionEntity1 = new QuestionEntity(1L, "test","test", 1, 1L, now , 1L, now, 1L);
        questionEntity1.setQuestionId(1L);
        questionEntity2 = new QuestionEntity(1L, "test","test", 1, 1L, now , 1L, now, 1L);
        questionEntity2.setQuestionId(1L);
        assertThat(questionEntity1.hashCode(), equalTo(questionEntity2.hashCode()));

        questionEntity1 = new QuestionEntity(1L, "test1","test1", 1, 1L, now , 1L, now, 1L);
        questionEntity2 = new QuestionEntity(2L, "test2","test2", 2, 2L, now , 2L, now, 2L);
        assertNotEquals(questionEntity1.hashCode(), questionEntity2.hashCode());

        questionEntity1 = new QuestionEntity(1L, "test1","test1", 1, 1L, now , 1L, now, 1L);
        questionEntity1.setQuestionId(1L);
        questionEntity2 = new QuestionEntity(2L, "test2","test2", 2, 2L, now , 2L, now, 2L);
        questionEntity2.setQuestionId(2L);
        assertNotEquals(questionEntity1.hashCode(), questionEntity2.hashCode());
    }
}