/**
 * Name      : com.malcolm.qme.springdata.entity.AnswerOptionMediaEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Answer Option Media Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class AnswerOptionMediaEntityTest {

    @Test
    public void testEquals() throws Exception {
        AnswerOptionMediaEntity answerOptionMediaEntity1 = new AnswerOptionMediaEntity(1L, 1,"testing".getBytes());
        AnswerOptionMediaEntity answerOptionMediaEntity2 = new AnswerOptionMediaEntity(1L, 1,"testing".getBytes());
        assertTrue(answerOptionMediaEntity1.equals(answerOptionMediaEntity2));
        assertTrue(answerOptionMediaEntity1.equals(answerOptionMediaEntity1));

        answerOptionMediaEntity1 = new AnswerOptionMediaEntity(1L, 1,"testing".getBytes());
        answerOptionMediaEntity1.setOptionMediaId(1L);
        answerOptionMediaEntity2 = new AnswerOptionMediaEntity(1L, 1,"testing".getBytes());
        answerOptionMediaEntity2.setOptionMediaId(1L);
        assertTrue(answerOptionMediaEntity1.equals(answerOptionMediaEntity2));

        answerOptionMediaEntity1 = new AnswerOptionMediaEntity(1L, 1,"testing1".getBytes());
        answerOptionMediaEntity2 = new AnswerOptionMediaEntity(1L, 2,"testing2".getBytes());
        assertFalse(answerOptionMediaEntity1.equals(answerOptionMediaEntity2));
        answerOptionMediaEntity1 = new AnswerOptionMediaEntity(1L, 1,"testing1".getBytes());
        answerOptionMediaEntity1.setOptionMediaId(1L);
        answerOptionMediaEntity2 = new AnswerOptionMediaEntity(1L, 1,"testing2".getBytes());
        answerOptionMediaEntity2.setOptionMediaId(2L);
        assertFalse(answerOptionMediaEntity1.equals(answerOptionMediaEntity2));

        assertFalse(answerOptionMediaEntity1.equals(null));
        assertFalse(answerOptionMediaEntity1.equals(""));

    }

    @Test
    public void testHashCode() throws Exception {
        AnswerOptionMediaEntity answerOptionMediaEntity1 = new AnswerOptionMediaEntity(1L, 1,"testing".getBytes());
        AnswerOptionMediaEntity answerOptionMediaEntity2 = new AnswerOptionMediaEntity(1L, 1,"testing".getBytes());
        assertThat(answerOptionMediaEntity1.hashCode(), equalTo(answerOptionMediaEntity2.hashCode()));
        assertThat(answerOptionMediaEntity1.hashCode(), equalTo(answerOptionMediaEntity1.hashCode()));

        answerOptionMediaEntity1 = new AnswerOptionMediaEntity(1L, 1,"testing".getBytes());
        answerOptionMediaEntity1.setOptionMediaId(1L);
        answerOptionMediaEntity2 = new AnswerOptionMediaEntity(1L, 1,"testing".getBytes());
        answerOptionMediaEntity2.setOptionMediaId(1L);
        assertThat(answerOptionMediaEntity1.hashCode(), equalTo(answerOptionMediaEntity2.hashCode()));

        answerOptionMediaEntity1 = new AnswerOptionMediaEntity(1L, 1,"testing1".getBytes());
        answerOptionMediaEntity2 = new AnswerOptionMediaEntity(1L, 2,"testing2".getBytes());
        assertNotEquals(answerOptionMediaEntity1.hashCode(), answerOptionMediaEntity2.hashCode());
        answerOptionMediaEntity1 = new AnswerOptionMediaEntity(1L, 1,"testing1".getBytes());
        answerOptionMediaEntity1.setOptionMediaId(1L);
        answerOptionMediaEntity2 = new AnswerOptionMediaEntity(1L, 1,"testing2".getBytes());
        answerOptionMediaEntity2.setOptionMediaId(2L);
        assertNotEquals(answerOptionMediaEntity1.hashCode(), answerOptionMediaEntity2.hashCode());
    }
}