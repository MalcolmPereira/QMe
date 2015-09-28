/**
 * Name      : com.malcolm.qme.springdata.entity.CategoryEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Category Entity Test
 */
package com.malcolm.qme.springdata.entity;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class CategoryEntityTest {

    @Test
    public void testEquals() throws Exception {
        LocalDateTime now =  LocalDateTime.now();

        CategoryEntity categoryEntity1 = new CategoryEntity("test", 1L, now, 1L);
        CategoryEntity categoryEntity2 = new CategoryEntity("test", 1L, now, 1L);
        assertTrue(categoryEntity1.equals(categoryEntity2));
        assertTrue(categoryEntity1.equals(categoryEntity1));

        categoryEntity1 = new CategoryEntity("test", 1L,now, 1L,1L);
        categoryEntity2 = new CategoryEntity("test", 1L, now, 1L,1L);
        assertTrue(categoryEntity1.equals(categoryEntity2));

        categoryEntity1 = new CategoryEntity("test", 1L, null, 1L,1L);
        categoryEntity2 = new CategoryEntity("test", 1L, null, 1L,1L);
        assertTrue(categoryEntity1.equals(categoryEntity2));


        categoryEntity1 = new CategoryEntity("test", 1L, now, 1L,1L);
        categoryEntity1.setCatId(1L);
        categoryEntity2 = new CategoryEntity("test", 1L, now, 1L,1L);
        categoryEntity2.setCatId(1L);
        assertTrue(categoryEntity1.equals(categoryEntity2));

        categoryEntity1 = new CategoryEntity("test1", 1L, now, 1L);
        categoryEntity2 = new CategoryEntity("test2", 1L, now, 1L);
        assertFalse(categoryEntity1.equals(categoryEntity2));

        categoryEntity1 = new CategoryEntity("test1", 1L, now, 1L,1L);
        categoryEntity2 = new CategoryEntity("test2", 2L, now, 2L,2L);
        assertFalse(categoryEntity1.equals(categoryEntity2));

        categoryEntity1 = new CategoryEntity("test1", 1L, null, 1L,1L);
        categoryEntity2 = new CategoryEntity("test2", 1L, null, 2L,1L);
        assertFalse(categoryEntity1.equals(categoryEntity2));

        categoryEntity1 = new CategoryEntity("test1", 1L, now, 1L,1L);
        categoryEntity1.setCatId(1L);
        categoryEntity2 = new CategoryEntity("test2", 1L, now, 2L,2L);
        categoryEntity2.setCatId(2L);
        assertFalse(categoryEntity1.equals(categoryEntity2));

        assertFalse(categoryEntity1.equals(null));
        assertFalse(categoryEntity1.equals(""));

    }

    @Test
    public void testHashCode() throws Exception {
        LocalDateTime now =  LocalDateTime.now();

        CategoryEntity categoryEntity1 = new CategoryEntity("test", 1L, now, 1L);
        CategoryEntity categoryEntity2 = new CategoryEntity("test", 1L, now, 1L);
        assertThat(categoryEntity1.hashCode(), equalTo(categoryEntity2.hashCode()));
        assertThat(categoryEntity1.hashCode(), equalTo(categoryEntity1.hashCode()));

        categoryEntity1 = new CategoryEntity("test", 1L, now, 1L,1L);
        categoryEntity2 = new CategoryEntity("test", 1L, now, 1L,1L);
        assertThat(categoryEntity1.hashCode(), equalTo(categoryEntity2.hashCode()));

        categoryEntity1 = new CategoryEntity("test", 1L, null, 1L,1L);
        categoryEntity2 = new CategoryEntity("test", 1L, null, 1L,1L);
        assertThat(categoryEntity1.hashCode(), equalTo(categoryEntity2.hashCode()));

        categoryEntity1 = new CategoryEntity("test", 1L, now, 1L,1L);
        categoryEntity1.setCatId(1L);
        categoryEntity2 = new CategoryEntity("test", 1L, now, 1L,1L);
        categoryEntity2.setCatId(1L);
        assertThat(categoryEntity1.hashCode(), equalTo(categoryEntity2.hashCode()));

        categoryEntity1 = new CategoryEntity("test1", 1L,now, 1L);
        categoryEntity2 = new CategoryEntity("test2", 1L, now, 1L);
        assertNotEquals(categoryEntity1.hashCode(), categoryEntity2.hashCode());

        categoryEntity1 = new CategoryEntity("test1", 1L, now, 1L,1L);
        categoryEntity2 = new CategoryEntity("test2", 2L, now, 2L,2L);
        assertNotEquals(categoryEntity1.hashCode(), categoryEntity2.hashCode());

        categoryEntity1 = new CategoryEntity("test1", 1L, null, 1L,1L);
        categoryEntity2 = new CategoryEntity("test2", 1L, null, 2L,1L);
        assertNotEquals(categoryEntity1.hashCode(), categoryEntity2.hashCode());

        categoryEntity1 = new CategoryEntity("test1", 1L, now, 1L,1L);
        categoryEntity1.setCatId(1L);
        categoryEntity2 = new CategoryEntity("test2", 1L, now, 2L,2L);
        categoryEntity2.setCatId(2L);
        assertNotEquals(categoryEntity1.hashCode(), categoryEntity2.hashCode());
    }
}