/**
 * Name      : com.malcolm.qme.core.domain.MediaTypeTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe Media Type Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class MediaTypeTest {

    @Test
    public void testGetMediaTypeID() throws Exception {
        MediaType mediaType = new MediaType(1, "some media type");
        assertThat(mediaType.getMediaTypeID(), equalTo(1));
        mediaType = new MediaType("some media type");
        assertThat(mediaType.getMediaTypeID(), equalTo(0));
    }

    @Test
    public void testGetMediaType() throws Exception {
        MediaType mediaType = new MediaType(1, "some media type");
        assertThat(mediaType.getMediaType(), equalTo("some media type"));
        mediaType = new MediaType("some media type");
        assertThat(mediaType.getMediaType(), equalTo("some media type"));
    }

    @Test
    public void testEquals(){
        MediaType mediaType1 = new MediaType(1, "some media type");
        MediaType mediaType2 = new MediaType(1, "some media type");
        assertTrue(mediaType1.equals(mediaType2));
        mediaType1 = new MediaType(1, "some media type1");
        mediaType2 = new MediaType(2, "some media type2");
        assertFalse(mediaType1.equals(mediaType2));
        mediaType1 = new MediaType("some media type");
        mediaType2 = new MediaType("some media type");
        assertTrue(mediaType1.equals(mediaType2));
        mediaType1 = new MediaType("some media type1");
        mediaType2 = new MediaType("some media type2");
        assertFalse(mediaType1.equals(mediaType2));
    }

    @Test
    public void testHashCode(){
        MediaType mediaType1 = new MediaType(1, "some media type");
        MediaType mediaType2 = new MediaType(1, "some media type");
        assertThat(mediaType1.hashCode(),equalTo(mediaType2.hashCode()));
        mediaType1 = new MediaType(1, "some media type1");
        mediaType2 = new MediaType(2, "some media type2");
        assertNotEquals(mediaType1.hashCode(),mediaType2.hashCode());
        mediaType1 = new MediaType("some media type");
        mediaType2 = new MediaType("some media type");
        assertThat(mediaType1.hashCode(),equalTo(mediaType2.hashCode()));
        mediaType1 = new MediaType("some media type1");
        mediaType2 = new MediaType("some media type2");
        assertNotEquals(mediaType1.hashCode(),mediaType2.hashCode());
    }
}