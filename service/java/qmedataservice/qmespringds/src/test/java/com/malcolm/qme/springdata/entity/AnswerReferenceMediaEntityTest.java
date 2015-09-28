/**
 * Name      : com.malcolm.qme.springdata.entity.AnswerReferenceMediaEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Answer Reference Media Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class AnswerReferenceMediaEntityTest {

    @Test
    public void testEquals() throws Exception {
        AnswerReferenceMediaEntity answerReferenceMediaEntity1 = new AnswerReferenceMediaEntity(1L, 1,"testing".getBytes());
        AnswerReferenceMediaEntity answerReferenceMediaEntity2 = new AnswerReferenceMediaEntity(1L, 1,"testing".getBytes());
        assertTrue(answerReferenceMediaEntity1.equals(answerReferenceMediaEntity2));
        assertTrue(answerReferenceMediaEntity1.equals(answerReferenceMediaEntity1));

        answerReferenceMediaEntity1 = new AnswerReferenceMediaEntity(1L, 1,"testing".getBytes());
        answerReferenceMediaEntity1.setAnswerRefMediaId(1L);
        answerReferenceMediaEntity2 = new AnswerReferenceMediaEntity(1L, 1,"testing".getBytes());
        answerReferenceMediaEntity2.setAnswerRefMediaId(1L);
        assertTrue(answerReferenceMediaEntity1.equals(answerReferenceMediaEntity2));

        answerReferenceMediaEntity1 = new AnswerReferenceMediaEntity(1L, 1,"testing1".getBytes());
        answerReferenceMediaEntity2 = new AnswerReferenceMediaEntity(1L, 2,"testing2".getBytes());
        assertFalse(answerReferenceMediaEntity1.equals(answerReferenceMediaEntity2));
        answerReferenceMediaEntity1 = new AnswerReferenceMediaEntity(1L, 1,"testing1".getBytes());
        answerReferenceMediaEntity1.setAnswerRefMediaId(1L);
        answerReferenceMediaEntity2 = new AnswerReferenceMediaEntity(1L, 1,"testing2".getBytes());
        answerReferenceMediaEntity2.setAnswerRefMediaId(2L);
        assertFalse(answerReferenceMediaEntity1.equals(answerReferenceMediaEntity2));

        assertFalse(answerReferenceMediaEntity1.equals(null));
        assertFalse(answerReferenceMediaEntity1.equals(""));

    }

    @Test
    public void testHashCode() throws Exception {
        AnswerReferenceMediaEntity answerReferenceMediaEntity1 = new AnswerReferenceMediaEntity(1L, 1,"testing".getBytes());
        AnswerReferenceMediaEntity answerReferenceMediaEntity2 = new AnswerReferenceMediaEntity(1L, 1,"testing".getBytes());
        assertThat(answerReferenceMediaEntity1.hashCode(), equalTo(answerReferenceMediaEntity2.hashCode()));
        assertThat(answerReferenceMediaEntity1.hashCode(), equalTo(answerReferenceMediaEntity1.hashCode()));

        answerReferenceMediaEntity1 = new AnswerReferenceMediaEntity(1L, 1,"testing".getBytes());
        answerReferenceMediaEntity1.setAnswerRefMediaId(1L);
        answerReferenceMediaEntity2 = new AnswerReferenceMediaEntity(1L, 1,"testing".getBytes());
        answerReferenceMediaEntity2.setAnswerRefMediaId(1L);
        assertThat(answerReferenceMediaEntity1.hashCode(), equalTo(answerReferenceMediaEntity2.hashCode()));

        answerReferenceMediaEntity1 = new AnswerReferenceMediaEntity(1L, 1,"testing1".getBytes());
        answerReferenceMediaEntity2 = new AnswerReferenceMediaEntity(1L, 2,"testing2".getBytes());
        assertNotEquals(answerReferenceMediaEntity1.hashCode(), answerReferenceMediaEntity2.hashCode());
        answerReferenceMediaEntity1 = new AnswerReferenceMediaEntity(1L, 1,"testing1".getBytes());
        answerReferenceMediaEntity1.setAnswerRefMediaId(1L);
        answerReferenceMediaEntity2 = new AnswerReferenceMediaEntity(1L, 1,"testing2".getBytes());
        answerReferenceMediaEntity2.setAnswerRefMediaId(2L);
        assertNotEquals(answerReferenceMediaEntity1.hashCode(), answerReferenceMediaEntity2.hashCode());
    }
}