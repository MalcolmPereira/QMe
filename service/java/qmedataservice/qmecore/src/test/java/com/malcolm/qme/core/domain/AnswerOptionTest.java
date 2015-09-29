/**
 * Name      : com.malcolm.qme.core.domain.AnswerOptionTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe Answer Option Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class AnswerOptionTest  {

    @Test
    public void testGetAnswerOptionID() throws Exception {
        AnswerOption answerOption = new AnswerOption(1L, 1L, "SomeOptionText", Boolean.TRUE);
        assertThat(answerOption.getAnswerOptionID(), equalTo(1L));
        answerOption = new AnswerOption(1L, "SomeOptionText", Boolean.TRUE);
        assertThat(answerOption.getAnswerOptionID(), equalTo(0L));
    }

    @Test
    public void testGetQuestionID() throws Exception {
        AnswerOption answerOption = new AnswerOption(1L, 1L, "SomeOptionText", Boolean.TRUE);
        assertThat(answerOption.getQuestionID(), equalTo(1L));
        answerOption = new AnswerOption(1L, "SomeOptionText", Boolean.TRUE);
        assertThat(answerOption.getQuestionID(), equalTo(1L));
    }

    @Test
    public void testGetOptionText() throws Exception {
        AnswerOption answerOption = new AnswerOption(1L, 1L, "SomeOptionText", Boolean.TRUE);
        assertThat(answerOption.getOptionText(), equalTo("SomeOptionText"));
        answerOption = new AnswerOption(1L, "SomeOptionText", Boolean.TRUE);
        assertThat(answerOption.getOptionText(), equalTo("SomeOptionText"));
    }

    @Test
    public void testIsCorrect() throws Exception {
        AnswerOption answerOption = new AnswerOption(1L, 1L, "SomeOptionText", Boolean.TRUE);
        assertThat(answerOption.isCorrect(), equalTo(Boolean.TRUE));
        answerOption = new AnswerOption(1L, "SomeOptionText", Boolean.TRUE);
        assertThat(answerOption.isCorrect(), equalTo(Boolean.TRUE));

        answerOption = new AnswerOption(1L, 1L, "SomeOptionText", Boolean.FALSE);
        assertThat(answerOption.isCorrect(), equalTo(Boolean.FALSE));
        answerOption = new AnswerOption(1L, "SomeOptionText", Boolean.FALSE);
        assertThat(answerOption.isCorrect(), equalTo(Boolean.FALSE));
    }

    @Test
    public void testEquals(){
        AnswerOption answerOption1 = new AnswerOption(1L, 1L, "SomeOptionText", Boolean.TRUE);
        AnswerOption answerOption2 = new AnswerOption(1L, 1L, "SomeOptionText", Boolean.TRUE);
        assertTrue(answerOption1.equals(answerOption2));
        assertTrue(answerOption1.equals(answerOption1));
        answerOption1 = new AnswerOption(1L, 1L, "SomeOptionText1", Boolean.TRUE);
        answerOption2 = new AnswerOption(1L, 2L, "SomeOptionText2", Boolean.TRUE);
        assertFalse(answerOption1.equals(answerOption2));
        answerOption1 = new AnswerOption(1L, "SomeOptionText", Boolean.TRUE);
        answerOption2 = new AnswerOption(1L, "SomeOptionText", Boolean.TRUE);
        assertTrue(answerOption1.equals(answerOption2));
        answerOption1 = new AnswerOption(1L, "SomeOptionText1", Boolean.TRUE);
        answerOption2 = new AnswerOption(2L, "SomeOptionText2", Boolean.TRUE);
        assertFalse(answerOption1.equals(answerOption2));
        assertFalse(answerOption1.equals(null));
        assertFalse(answerOption1.equals(""));
    }

    @Test
    public void testHashCode(){
        AnswerOption answerOption1 = new AnswerOption(1L, 1L, "SomeOptionText", Boolean.TRUE);
        AnswerOption answerOption2 = new AnswerOption(1L, 1L, "SomeOptionText", Boolean.TRUE);
        assertThat(answerOption1.hashCode(),equalTo(answerOption2.hashCode()));
        answerOption1 = new AnswerOption(1L, 1L, "SomeOptionText1", Boolean.TRUE);
        answerOption2 = new AnswerOption(1L, 2L, "SomeOptionText2", Boolean.TRUE);
        assertNotEquals(answerOption1.hashCode(),answerOption2.hashCode());
        answerOption1 = new AnswerOption(1L, "SomeOptionText", Boolean.TRUE);
        answerOption2 = new AnswerOption(1L, "SomeOptionText", Boolean.TRUE);
        assertThat(answerOption1.hashCode(),equalTo(answerOption2.hashCode()));
        answerOption1 = new AnswerOption(1L, "SomeOptionText1", Boolean.TRUE);
        answerOption2 = new AnswerOption(2L, "SomeOptionText2", Boolean.TRUE);
        assertNotEquals(answerOption1.hashCode(),answerOption2.hashCode());
    }
}