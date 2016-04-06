/**
 * Name      : com.malcolm.qme.rest.model.QMeAnswerOptionMediaTest.java
 * Date      : 4/5/16
 * Developer : Malcolm
 * Purpose   : QMeAnswerOptionMedia Model for REST Service Controller Test
 */
package com.malcolm.qme.rest.model;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author malcolm
 */
public class QMeAnswerOptionMediaTest {

    @Test
    public void testGetAnswerOptionMediaID() throws Exception {
        QMeAnswerOptionMedia qMeAnswerOptionMedia = new QMeAnswerOptionMedia();
        qMeAnswerOptionMedia.setAnswerOptionMediaID(1L);
        assertThat(qMeAnswerOptionMedia.getAnswerOptionMediaID(), equalTo(1L));
    }

    @Test
    public void testSetAnswerOptionMediaID() throws Exception {
        QMeAnswerOptionMedia qMeAnswerOptionMedia = new QMeAnswerOptionMedia();
        qMeAnswerOptionMedia.setAnswerOptionMediaID(2L);
        assertThat(qMeAnswerOptionMedia.getAnswerOptionMediaID(), equalTo(2L));
    }

    @Test
    public void testGetAnswerOptionID() throws Exception {
        QMeAnswerOptionMedia qMeAnswerOptionMedia = new QMeAnswerOptionMedia();
        qMeAnswerOptionMedia.setAnswerOptionID(1L);
        assertThat(qMeAnswerOptionMedia.getAnswerOptionID(), equalTo(1L));
    }

    @Test
    public void testSetAnswerOptionID() throws Exception {
        QMeAnswerOptionMedia qMeAnswerOptionMedia = new QMeAnswerOptionMedia();
        qMeAnswerOptionMedia.setAnswerOptionID(2L);
        assertThat(qMeAnswerOptionMedia.getAnswerOptionID(), equalTo(2L));
    }

    @Test
    public void testGetMediaTypeID() throws Exception {
        QMeAnswerOptionMedia qMeAnswerOptionMedia = new QMeAnswerOptionMedia();
        qMeAnswerOptionMedia.setMediaTypeID(1);
        assertThat(qMeAnswerOptionMedia.getMediaTypeID(), equalTo(1));
    }

    @Test
    public void testSetMediaTypeID() throws Exception {
        QMeAnswerOptionMedia qMeAnswerOptionMedia = new QMeAnswerOptionMedia();
        qMeAnswerOptionMedia.setMediaTypeID(2);
        assertThat(qMeAnswerOptionMedia.getMediaTypeID(), equalTo(2));
    }

    @Test
    public void testGetMedia() throws Exception {
        QMeAnswerOptionMedia qMeAnswerOptionMedia = new QMeAnswerOptionMedia();
        qMeAnswerOptionMedia.setMedia("Hello Media 1".getBytes());
        assertThat(new String(qMeAnswerOptionMedia.getMedia()), equalTo("Hello Media 1"));
    }

    @Test
    public void testSetMedia() throws Exception {
        QMeAnswerOptionMedia qMeAnswerOptionMedia = new QMeAnswerOptionMedia();
        qMeAnswerOptionMedia.setMedia("Hello Media 2".getBytes());
        assertThat(new String(qMeAnswerOptionMedia.getMedia()), equalTo("Hello Media 2"));
    }
}