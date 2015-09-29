/**
 * Name      : com.malcolm.qme.core.domain.AnswerReferenceMediaTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe Answer Reference Media Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class AnswerReferenceMediaTest {

    @Test
    public void testGetAnswerRefMediaID() throws Exception {
        AnswerReferenceMedia answerReferenceMedia = new AnswerReferenceMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        assertThat(answerReferenceMedia.getAnswerRefMediaID(), equalTo(1L));
        answerReferenceMedia = new AnswerReferenceMedia(1L, 1, "SomeMediaData".getBytes());
        assertThat(answerReferenceMedia.getAnswerRefMediaID(), equalTo(0L));
    }

    @Test
    public void testGetQuestionID() throws Exception {
        AnswerReferenceMedia answerReferenceMedia = new AnswerReferenceMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        assertThat(answerReferenceMedia.getQuestionID(), equalTo(1L));
        answerReferenceMedia = new AnswerReferenceMedia(1L, 1, "SomeMediaData".getBytes());
        assertThat(answerReferenceMedia.getQuestionID(), equalTo(1L));
    }

    @Test
    public void testGetMedia() throws Exception {
        AnswerReferenceMedia answerReferenceMedia = new AnswerReferenceMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        assertThat(answerReferenceMedia.getMedia().length, greaterThan(0));
        answerReferenceMedia = new AnswerReferenceMedia(1L, 1, "SomeMediaData".getBytes());
        assertThat(answerReferenceMedia.getMedia().length, greaterThan(0));
    }

    @Test
    public void testGetMediaTypeID() throws Exception {
        AnswerReferenceMedia answerReferenceMedia = new AnswerReferenceMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        assertThat(answerReferenceMedia.getMediaTypeID(), equalTo(1));
        answerReferenceMedia = new AnswerReferenceMedia(1L, 1, "SomeMediaData".getBytes());
        assertThat(answerReferenceMedia.getMediaTypeID(), equalTo(1));
    }

    @Test
    public void testEquals(){
        AnswerReferenceMedia answerReferenceMedia1 = new AnswerReferenceMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        AnswerReferenceMedia answerReferenceMedia2 = new AnswerReferenceMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        assertTrue(answerReferenceMedia1.equals(answerReferenceMedia2));
        assertTrue(answerReferenceMedia1.equals(answerReferenceMedia1));
        answerReferenceMedia1 = new AnswerReferenceMedia(1L, 1L, 1, "SomeMediaData1".getBytes());
        answerReferenceMedia2 = new AnswerReferenceMedia(1L, 2L, 2, "SomeMediaData2".getBytes());
        assertFalse(answerReferenceMedia1.equals(answerReferenceMedia2));
        answerReferenceMedia1 = new AnswerReferenceMedia(1L, 1, "SomeMediaData".getBytes());
        answerReferenceMedia2 = new AnswerReferenceMedia(1L, 1, "SomeMediaData".getBytes());
        assertTrue(answerReferenceMedia1.equals(answerReferenceMedia2));
        answerReferenceMedia1 = new AnswerReferenceMedia(1L, 1, "SomeMediaData1".getBytes());
        answerReferenceMedia2 = new AnswerReferenceMedia(2L, 2, "SomeMediaData2".getBytes());
        assertFalse(answerReferenceMedia1.equals(answerReferenceMedia2));
        assertFalse(answerReferenceMedia1.equals(null));
        assertFalse(answerReferenceMedia1.equals(""));
    }

    @Test
    public void testHashCode(){
        AnswerReferenceMedia answerReferenceMedia1 = new AnswerReferenceMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        AnswerReferenceMedia answerReferenceMedia2 = new AnswerReferenceMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        assertThat(answerReferenceMedia1.hashCode(),equalTo(answerReferenceMedia2.hashCode()));
        answerReferenceMedia1 = new AnswerReferenceMedia(1L, 1L, 1, "SomeMediaData1".getBytes());
        answerReferenceMedia2 = new AnswerReferenceMedia(1L, 2L, 2, "SomeMediaData2".getBytes());
        assertNotEquals(answerReferenceMedia1.hashCode(),answerReferenceMedia2.hashCode());
        answerReferenceMedia1 = new AnswerReferenceMedia(1L, 1, "SomeMediaData".getBytes());
        answerReferenceMedia2 = new AnswerReferenceMedia(1L, 1, "SomeMediaData".getBytes());
        assertThat(answerReferenceMedia1.hashCode(),equalTo(answerReferenceMedia2.hashCode()));
        answerReferenceMedia1 = new AnswerReferenceMedia(1L, 1, "SomeMediaData1".getBytes());
        answerReferenceMedia2 = new AnswerReferenceMedia(2L, 2, "SomeMediaData2".getBytes());
        assertNotEquals(answerReferenceMedia1.hashCode(),answerReferenceMedia2.hashCode());

    }
}