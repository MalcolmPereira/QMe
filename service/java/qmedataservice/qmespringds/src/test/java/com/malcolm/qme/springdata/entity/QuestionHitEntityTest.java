/**
 * Name      : com.malcolm.qme.springdata.entity.QuestionHitEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Question Hit Entity
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class QuestionHitEntityTest {

    @Test
    public void testEquals() throws Exception {
        QuestionHitEntity questionHitEntity1 = new QuestionHitEntity(1L, 1L, 1L,1l,1l);
        QuestionHitEntity questionHitEntity2 = new QuestionHitEntity(1L, 1L, 1L,1l,1l);
        assertTrue(questionHitEntity1.equals(questionHitEntity2));
        assertTrue(questionHitEntity1.equals(questionHitEntity1));

        questionHitEntity1 = new QuestionHitEntity(1L, 1L, 1L,1l,1l);
        questionHitEntity2 = new QuestionHitEntity(2L, 2L, 2L,2l,2l);
        assertFalse(questionHitEntity1.equals(questionHitEntity2));
    }

    @Test
    public void testHashCode() throws Exception {
        QuestionHitEntity questionHitEntity1 = new QuestionHitEntity(1L, 1L, 1L,1l,1l);
        QuestionHitEntity questionHitEntity2 = new QuestionHitEntity(1L, 1L, 1L,1l,1l);
        assertThat(questionHitEntity1.hashCode(), equalTo(questionHitEntity2.hashCode()));
        assertThat(questionHitEntity1.hashCode(), equalTo(questionHitEntity1.hashCode()));

        questionHitEntity1 = new QuestionHitEntity(1L, 1L, 1L,1l,1l);
        questionHitEntity2 = new QuestionHitEntity(2L, 2L, 2L,2l,2l);
        assertNotEquals(questionHitEntity1.hashCode(), questionHitEntity2.hashCode());

    }
}