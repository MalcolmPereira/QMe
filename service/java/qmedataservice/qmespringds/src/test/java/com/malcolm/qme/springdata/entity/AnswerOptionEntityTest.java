/**
 * Name      : com.malcolm.qme.springdata.entity.AnswerOptionEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Answer Option Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class AnswerOptionEntityTest {

    @Test
    public void testEquals() throws Exception {
        AnswerOptionEntity answerOptionEntity1 = new AnswerOptionEntity(1L,"some text", (byte)1);
        AnswerOptionEntity answerOptionEntity2 = new AnswerOptionEntity(1L,"some text", (byte)1);
        assertTrue(answerOptionEntity1.equals(answerOptionEntity2));
        assertTrue(answerOptionEntity1.equals(answerOptionEntity1));

        answerOptionEntity1 = new AnswerOptionEntity(1L,"some text", (byte)1);
        answerOptionEntity1.setOptionId(1L);
        answerOptionEntity2 = new AnswerOptionEntity(1L,"some text", (byte)1);
        answerOptionEntity2.setOptionId(1L);
        assertTrue(answerOptionEntity1.equals(answerOptionEntity2));

        answerOptionEntity1 = new AnswerOptionEntity(1L,"some text1", (byte)1);
        answerOptionEntity2 = new AnswerOptionEntity(1L,"some text3", (byte)1);
        assertFalse(answerOptionEntity1.equals(answerOptionEntity2));
        answerOptionEntity1 = new AnswerOptionEntity(1L,"some text1", (byte)1);
        answerOptionEntity1.setOptionId(1L);
        answerOptionEntity2 = new AnswerOptionEntity(1L,"some text3", (byte)1);
        answerOptionEntity1.setOptionId(2L);
        assertFalse(answerOptionEntity1.equals(answerOptionEntity2));

        answerOptionEntity1 = new AnswerOptionEntity(1L,"some text1", (byte)1);
        assertFalse(answerOptionEntity1.equals(null));
        assertFalse(answerOptionEntity1.equals(""));
    }

    @Test
    public void testHashCode() throws Exception {
        AnswerOptionEntity answerOptionEntity1 = new AnswerOptionEntity(1L,"some text", (byte)1);
        AnswerOptionEntity answerOptionEntity2 = new AnswerOptionEntity(1L,"some text", (byte)1);
        assertThat(answerOptionEntity1.hashCode(), equalTo(answerOptionEntity2.hashCode()));

        answerOptionEntity1 = new AnswerOptionEntity(1L,"some text", (byte)1);
        answerOptionEntity1.setOptionId(1L);
        answerOptionEntity2 = new AnswerOptionEntity(1L,"some text", (byte)1);
        answerOptionEntity2.setOptionId(1L);
        assertThat(answerOptionEntity1.hashCode(), equalTo(answerOptionEntity2.hashCode()));

        answerOptionEntity1 = new AnswerOptionEntity(1L,"some text1", (byte)1);
        answerOptionEntity2 = new AnswerOptionEntity(1L,"some text3", (byte)1);
        assertNotEquals(answerOptionEntity1.hashCode(), answerOptionEntity2.hashCode());
        answerOptionEntity1 = new AnswerOptionEntity(1L,"some text1", (byte)1);
        answerOptionEntity1.setOptionId(1L);
        answerOptionEntity2 = new AnswerOptionEntity(1L,"some text3", (byte)1);
        answerOptionEntity1.setOptionId(2L);
        assertNotEquals(answerOptionEntity1.hashCode(), answerOptionEntity2.hashCode());
    }
}