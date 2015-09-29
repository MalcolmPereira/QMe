/**
 * Name      : com.malcolm.qme.core.domain.QuestionHitTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe Question Hit Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class QuestionHitTest {

    @Test
    public void testGetQuestionID() throws Exception {
        QuestionHit questionHit = new QuestionHit(1L, 2L, 3L, 4L, 5L);
        assertThat(questionHit.getQuestionID(), equalTo(1L));
    }

    @Test
    public void testGetCategoryID() throws Exception {
        QuestionHit questionHit = new QuestionHit(1L, 2L, 3L, 4L, 5L);
        assertThat(questionHit.getCategoryID(), equalTo(2L));
    }

    @Test
    public void testGetQuestionHit() throws Exception {
        QuestionHit questionHit = new QuestionHit(1L, 2L, 3L, 4L, 5L);
        assertThat(questionHit.getQuestionHit(), equalTo(3L));
    }

    @Test
    public void testGetRightCount() throws Exception {
        QuestionHit questionHit = new QuestionHit(1L, 2L, 3L, 4L, 5L);
        assertThat(questionHit.getRightCount(), equalTo(4L));
    }

    @Test
    public void testGetWrongCount() throws Exception {
        QuestionHit questionHit = new QuestionHit(1L, 2L, 3L, 4L, 5L);
        assertThat(questionHit.getWrongCount(), equalTo(5L));
    }

    @Test
    public void testEquals(){
        QuestionHit questionHit1 = new QuestionHit(1L, 2L, 3L, 4L, 5L);
        QuestionHit questionHit2 = new QuestionHit(1L, 2L, 3L, 4L, 5L);
        assertTrue(questionHit1.equals(questionHit2));
        assertTrue(questionHit1.equals(questionHit1));
        questionHit1 = new QuestionHit(1L, 2L, 3L, 4L, 5L);
        questionHit2 = new QuestionHit(2L, 3L, 4L, 5L, 6L);
        assertFalse(questionHit1.equals(questionHit2));
        assertFalse(questionHit1.equals(null));
        assertFalse(questionHit1.equals(""));
    }

    @Test
    public void testHashCode(){
        QuestionHit questionHit1 = new QuestionHit(1L, 2L, 3L, 4L, 5L);
        QuestionHit questionHit2 = new QuestionHit(1L, 2L, 3L, 4L, 5L);
        assertThat(questionHit1.hashCode(),equalTo(questionHit2.hashCode()));
        questionHit1 = new QuestionHit(1L, 2L, 3L, 4L, 5L);
        questionHit2 = new QuestionHit(2L, 3L, 4L, 5L, 6L);
        assertNotEquals(questionHit1.hashCode(),questionHit2.hashCode());
    }
}