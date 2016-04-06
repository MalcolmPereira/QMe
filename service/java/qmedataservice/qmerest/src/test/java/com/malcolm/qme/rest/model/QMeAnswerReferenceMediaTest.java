/**
 * Name      : com.malcolm.qme.rest.model.QMeAnswerReferenceMediaTest.java
 * Date      : 4/5/16
 * Developer : Malcolm
 * Purpose   : QMeAnswerReferenceMedia Model for REST Service Controller Test
 */
package com.malcolm.qme.rest.model;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author malcolm
 */
public class QMeAnswerReferenceMediaTest {

    @Test
    public void testGetAnswerRefMediaID() throws Exception {
        QMeAnswerReferenceMedia qMeAnswerReferenceMedia = new QMeAnswerReferenceMedia();
        qMeAnswerReferenceMedia.setAnswerRefMediaID(1L);
        assertThat(qMeAnswerReferenceMedia.getAnswerRefMediaID(), equalTo(1L));
    }

    @Test
    public void testSetAnswerRefMediaID() throws Exception {
        QMeAnswerReferenceMedia qMeAnswerReferenceMedia = new QMeAnswerReferenceMedia();
        qMeAnswerReferenceMedia.setAnswerRefMediaID(2L);
        assertThat(qMeAnswerReferenceMedia.getAnswerRefMediaID(), equalTo(2L));
    }

    @Test
    public void testGetQuestionID() throws Exception {
        QMeAnswerReferenceMedia qMeAnswerReferenceMedia = new QMeAnswerReferenceMedia();
        qMeAnswerReferenceMedia.setQuestionID(1L);
        assertThat(qMeAnswerReferenceMedia.getQuestionID(), equalTo(1L));
    }

    @Test
    public void testSetQuestionID() throws Exception {
        QMeAnswerReferenceMedia qMeAnswerReferenceMedia = new QMeAnswerReferenceMedia();
        qMeAnswerReferenceMedia.setQuestionID(2L);
        assertThat(qMeAnswerReferenceMedia.getQuestionID(), equalTo(2L));
    }

    @Test
    public void testGetMediaTypeID() throws Exception {
        QMeAnswerReferenceMedia qMeAnswerReferenceMedia = new QMeAnswerReferenceMedia();
        qMeAnswerReferenceMedia.setMediaTypeID(1);
        assertThat(qMeAnswerReferenceMedia.getMediaTypeID(), equalTo(1));
    }

    @Test
    public void testSetMediaTypeID() throws Exception {
        QMeAnswerReferenceMedia qMeAnswerReferenceMedia = new QMeAnswerReferenceMedia();
        qMeAnswerReferenceMedia.setMediaTypeID(2);
        assertThat(qMeAnswerReferenceMedia.getMediaTypeID(), equalTo(2));
    }

    @Test
    public void testGetMedia() throws Exception {
        QMeAnswerReferenceMedia qMeAnswerReferenceMedia = new QMeAnswerReferenceMedia();
        qMeAnswerReferenceMedia.setMedia("Hello Media".getBytes());
        assertThat(new String(qMeAnswerReferenceMedia.getMedia()), equalTo("Hello Media"));
    }

    @Test
    public void testSetMedia() throws Exception {
        QMeAnswerReferenceMedia qMeAnswerReferenceMedia = new QMeAnswerReferenceMedia();
        qMeAnswerReferenceMedia.setMedia("Hello Media Set".getBytes());
        assertThat(new String(qMeAnswerReferenceMedia.getMedia()), equalTo("Hello Media Set"));
    }
}