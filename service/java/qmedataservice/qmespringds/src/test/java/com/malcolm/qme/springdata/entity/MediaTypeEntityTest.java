/**
 * Name      : com.malcolm.qme.springdata.entity.MediaTypeEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : MediaType Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class MediaTypeEntityTest {

    @Test
    public void testEquals() throws Exception {
        MediaTypeEntity mediaTypeEntity1 = new MediaTypeEntity("media");
        MediaTypeEntity mediaTypeEntity2 = new MediaTypeEntity("media");
        assertTrue(mediaTypeEntity1.equals(mediaTypeEntity2));
        assertTrue(mediaTypeEntity1.equals(mediaTypeEntity1));

        mediaTypeEntity1 = new MediaTypeEntity("media");
        mediaTypeEntity1.setMediaTypeId(1);
        mediaTypeEntity2 = new MediaTypeEntity("media");
        mediaTypeEntity2.setMediaTypeId(1);
        assertTrue(mediaTypeEntity1.equals(mediaTypeEntity2));


        mediaTypeEntity1 = new MediaTypeEntity("media1");
        mediaTypeEntity2 = new MediaTypeEntity("media2");
        assertFalse(mediaTypeEntity1.equals(mediaTypeEntity2));

        mediaTypeEntity1 = new MediaTypeEntity("media1");
        mediaTypeEntity1.setMediaTypeId(1);
        mediaTypeEntity2 = new MediaTypeEntity("media2");
        mediaTypeEntity2.setMediaTypeId(2);
        assertFalse(mediaTypeEntity1.equals(mediaTypeEntity2));

        assertFalse(mediaTypeEntity1.equals(null));
        assertFalse(mediaTypeEntity1.equals(""));
    }

    @Test
    public void testHashCode() throws Exception {
        MediaTypeEntity mediaTypeEntity1 = new MediaTypeEntity("media");
        MediaTypeEntity mediaTypeEntity2 = new MediaTypeEntity("media");
        assertThat(mediaTypeEntity1.hashCode(), equalTo(mediaTypeEntity2.hashCode()));
        assertThat(mediaTypeEntity1.hashCode(), equalTo(mediaTypeEntity1.hashCode()));

        mediaTypeEntity1 = new MediaTypeEntity("media");
        mediaTypeEntity1.setMediaTypeId(1);
        mediaTypeEntity2 = new MediaTypeEntity("media");
        mediaTypeEntity2.setMediaTypeId(1);
        assertThat(mediaTypeEntity1.hashCode(), equalTo(mediaTypeEntity2.hashCode()));


        mediaTypeEntity1 = new MediaTypeEntity("media1");
        mediaTypeEntity2 = new MediaTypeEntity("media2");
        assertNotEquals(mediaTypeEntity1.hashCode(), mediaTypeEntity2.hashCode());

        mediaTypeEntity1 = new MediaTypeEntity("media1");
        mediaTypeEntity1.setMediaTypeId(1);
        mediaTypeEntity2 = new MediaTypeEntity("media2");
        mediaTypeEntity2.setMediaTypeId(2);
        assertNotEquals(mediaTypeEntity1.hashCode(), mediaTypeEntity2.hashCode());
    }
}