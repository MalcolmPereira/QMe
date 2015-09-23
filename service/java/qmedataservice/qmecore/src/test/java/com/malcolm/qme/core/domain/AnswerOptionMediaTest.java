/**
 * Name      : com.malcolm.qme.core.domain.AnswerOptionMediaTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe Answer Option Media Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class AnswerOptionMediaTest {

    @Test
    public void testGetAnswerOptionMediaID() throws Exception {
        AnswerOptionMedia answerOptionMedia = new AnswerOptionMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        assertThat(answerOptionMedia.getAnswerOptionMediaID(), equalTo(1L));
        answerOptionMedia = new AnswerOptionMedia(1L, 1, "SomeMediaData".getBytes());
        assertThat(answerOptionMedia.getAnswerOptionMediaID(), equalTo(0L));

    }

    @Test
    public void testGetAnswerOptionID() throws Exception {
        AnswerOptionMedia answerOptionMedia = new AnswerOptionMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        assertThat(answerOptionMedia.getAnswerOptionID(), equalTo(1L));
        answerOptionMedia = new AnswerOptionMedia(1L, 1, "SomeMediaData".getBytes());
        assertThat(answerOptionMedia.getAnswerOptionID(), equalTo(1L));
    }

    @Test
    public void testGetMedia() throws Exception {
        AnswerOptionMedia answerOptionMedia = new AnswerOptionMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        assertThat(answerOptionMedia.getMedia().length, greaterThan(0));
        answerOptionMedia = new AnswerOptionMedia(1L, 1, "SomeMediaData".getBytes());
        assertThat(answerOptionMedia.getMedia().length, greaterThan(0));
    }

    @Test
    public void testGetMediaTypeID() throws Exception {
        AnswerOptionMedia answerOptionMedia = new AnswerOptionMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        assertThat(answerOptionMedia.getMediaTypeID(), equalTo(1));
        answerOptionMedia = new AnswerOptionMedia(1L, 1, "SomeMediaData".getBytes());
        assertThat(answerOptionMedia.getMediaTypeID(),equalTo(1));
    }

    @Test
    public void testEquals(){
        AnswerOptionMedia answerOptionMedia1 = new AnswerOptionMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        AnswerOptionMedia answerOptionMedia2 = new AnswerOptionMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        assertTrue(answerOptionMedia1.equals(answerOptionMedia2));
        answerOptionMedia1 = new AnswerOptionMedia(1L, 1L, 1, "SomeMediaData1".getBytes());
        answerOptionMedia2 = new AnswerOptionMedia(1L, 1L, 2, "SomeMediaData2".getBytes());
        assertFalse(answerOptionMedia1.equals(answerOptionMedia2));
        answerOptionMedia1 = new AnswerOptionMedia(1L, 1, "SomeMediaData".getBytes());
        answerOptionMedia2 = new AnswerOptionMedia(1L, 1, "SomeMediaData".getBytes());
        assertTrue(answerOptionMedia1.equals(answerOptionMedia2));
        answerOptionMedia1 = new AnswerOptionMedia(1L, 1, "SomeMediaData1".getBytes());
        answerOptionMedia2 = new AnswerOptionMedia(1L, 2, "SomeMediaData2".getBytes());
        assertFalse(answerOptionMedia1.equals(answerOptionMedia2));
    }

    @Test
    public void testHashCode(){
        AnswerOptionMedia answerOptionMedia1 = new AnswerOptionMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        AnswerOptionMedia answerOptionMedia2 = new AnswerOptionMedia(1L, 1L, 1, "SomeMediaData".getBytes());
        assertThat(answerOptionMedia1.hashCode(),equalTo(answerOptionMedia2.hashCode()));
        answerOptionMedia1 = new AnswerOptionMedia(1L, 1L, 1, "SomeMediaData1".getBytes());
        answerOptionMedia2 = new AnswerOptionMedia(1L, 1L, 2, "SomeMediaData2".getBytes());
        assertNotEquals(answerOptionMedia1.hashCode(),answerOptionMedia2.hashCode());
        answerOptionMedia1 = new AnswerOptionMedia(1L, 1, "SomeMediaData".getBytes());
        answerOptionMedia2 = new AnswerOptionMedia(1L, 1, "SomeMediaData".getBytes());
        assertThat(answerOptionMedia1.hashCode(),equalTo(answerOptionMedia2.hashCode()));
        answerOptionMedia1 = new AnswerOptionMedia(1L, 1, "SomeMediaData1".getBytes());
        answerOptionMedia2 = new AnswerOptionMedia(1L, 2, "SomeMediaData2".getBytes());
        assertNotEquals(answerOptionMedia1.hashCode(),answerOptionMedia2.hashCode());
    }
}