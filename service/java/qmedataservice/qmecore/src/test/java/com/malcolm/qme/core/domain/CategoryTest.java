/**
 * Name      : com.malcolm.qme.core.domain.CategoryTest.java
 * Date      : 9/22/15
 * Developer : Malcolm
 * Purpose   : QMe Category Domain Class Test
 */
package com.malcolm.qme.core.domain;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Malcolm
 */
public class CategoryTest {

    @Test
    public void testGetCategoryID() throws Exception {
        Category category = new Category(1L, 0L, "Some Category Name",1L, LocalDateTime.now(), 1L);
        assertThat(category.getCategoryID(), equalTo(1L));
        category = new Category(1L, 1L, "Some Category Name With Parent",1L, LocalDateTime.now(), 1L);
        assertThat(category.getCategoryID(), equalTo(1L));
        category = new Category(1L, "Some Parent Category Name", 1L);
        assertThat(category.getCategoryID(), equalTo(0L));
        category = new Category("Some Category Name", 1L);
        assertThat(category.getCategoryID(), equalTo(0L));
    }

    @Test
    public void testGetCategoryParentID() throws Exception {
        Category category = new Category(1L, 0L, "Some Category Name",1L, LocalDateTime.now(), 1L);
        assertThat(category.getCategoryParentID(), equalTo(0L));
        category = new Category(1L, 1L, "Some Category Name With Parent",1L, LocalDateTime.now(), 1L);
        assertThat(category.getCategoryParentID(), equalTo(1L));
        category = new Category(1L, "Some Parent Category Name", 1L);
        assertThat(category.getCategoryParentID(), equalTo(1L));
        category = new Category("Some Category Name", 1L);
        assertThat(category.getCategoryParentID(), equalTo(0L));
    }

    @Test
    public void testGetCategoryName() throws Exception {
        Category category = new Category(1L, 0L, "Some Category Name",1L, LocalDateTime.now(), 1L);
        assertThat(category.getCategoryName(), equalTo("Some Category Name"));
        category = new Category(1L, 1L, "Some Category Name With Parent",1L, LocalDateTime.now(), 1L);
        assertThat(category.getCategoryName(), equalTo("Some Category Name With Parent"));
        category = new Category(1L, "Some Parent Category Name", 1L);
        assertThat(category.getCategoryName(), equalTo("Some Parent Category Name"));
        category = new Category("Some Category Name", 1L);
        assertThat(category.getCategoryName(), equalTo("Some Category Name"));
    }

    @Test
    public void testGetCategoryCreateDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Category category = new Category(1L, 0L, "Some Category Name",1L, now, 1L);
        assertThat(category.getCategoryCreateDate(), equalTo(now));
        category = new Category(1L, 1L, "Some Category Name With Parent",1L, now, 1L);
        assertThat(category.getCategoryCreateDate(), equalTo(now));
        category = new Category(1L, "Some Parent Category Name", 1L);
        assertNull(category.getCategoryCreateDate());
        category = new Category("Some Category Name", 1L);
        assertNull(category.getCategoryCreateDate());
    }

    @Test
    public void testGetCategoryCreateUserID() throws Exception {
        Category category = new Category(1L, 0L, "Some Category Name",1L, LocalDateTime.now(), 1L);
        assertThat(category.getCategoryCreateUserID(), equalTo(1L));
        category = new Category(1L, 1L, "Some Category Name With Parent",1L, LocalDateTime.now(), 1L);
        assertThat(category.getCategoryCreateUserID(), equalTo(1L));
        category = new Category(1L, "Some Parent Category Name", 1L);
        assertThat(category.getCategoryCreateUserID(), equalTo(1L));
        category = new Category("Some Category Name", 1L);
        assertThat(category.getCategoryCreateUserID(), equalTo(1L));
    }

    @Test
    public void testGetCategoryLikes() throws Exception {
        Category category = new Category(1L, 0L, "Some Category Name",1L, LocalDateTime.now(), 1L);
        assertThat(category.getCategoryLikes(), equalTo(1L));
        category = new Category(1L, 1L, "Some Category Name With Parent",1L, LocalDateTime.now(), 1L);
        assertThat(category.getCategoryLikes(), equalTo(1L));
        category = new Category(1L, "Some Parent Category Name", 1L);
        assertThat(category.getCategoryLikes(), equalTo(0L));
        category = new Category("Some Category Name", 1L);
        assertThat(category.getCategoryLikes(), equalTo(0L));
    }

    @Test
    public void testEquals(){
        Category category1 = new Category(1L, 0L, "Some Category Name",1L, LocalDateTime.now(), 1L);
        Category category2 = new Category(1L, 0L, "Some Category Name",1L, LocalDateTime.now(), 1L);
        assertTrue(category1.equals(category2));
        category1 = new Category(1L, 0L, "Some Category Name1",1L, LocalDateTime.now(), 1L);
        category2 = new Category(2L, 0L, "Some Category Name2",2L, LocalDateTime.now(), 1L);
        assertFalse(category1.equals(category2));
        category1 = new Category(1L, 1L, "Some Category Name With Parent",1L, LocalDateTime.now(), 1L);
        category2 = new Category(1L, 1L, "Some Category Name With Parent",1L, LocalDateTime.now(), 1L);
        assertTrue(category1.equals(category2));
        category1 = new Category(1L, 1L, "Some Category Name With Parent1",1L, LocalDateTime.now(), 1L);
        category2 = new Category(2L, 2L, "Some Category Name With Parent2",2L, LocalDateTime.now(), 1L);
        assertFalse(category1.equals(category2));
        category1 = new Category(1L, "Some Parent Category Name", 1L);
        category2 = new Category(1L, "Some Parent Category Name", 1L);
        assertTrue(category1.equals(category2));
        category1 = new Category(1L, "Some Parent Category Name1", 1L);
        category2 = new Category(2L, "Some Parent Category Name2", 1L);
        assertFalse(category1.equals(category2));
        category1 = new Category("Some Category Name", 1L);
        category2 = new Category("Some Category Name", 1L);
        assertTrue(category1.equals(category2));
        category1 = new Category("Some Category Name1", 1L);
        category2 = new Category("Some Category Name2", 1L);
        assertFalse(category1.equals(category2));

    }

    @Test
    public void testHashCode(){
        Category category1 = new Category(1L, 0L, "Some Category Name",1L, LocalDateTime.now(), 1L);
        Category category2 = new Category(1L, 0L, "Some Category Name",1L, LocalDateTime.now(), 1L);
        assertThat(category1.hashCode(),equalTo(category2.hashCode()));
        category1 = new Category(1L, 0L, "Some Category Name1",1L, LocalDateTime.now(), 1L);
        category2 = new Category(2L, 0L, "Some Category Name2",2L, LocalDateTime.now(), 1L);
        assertNotEquals(category1.hashCode(),category2.hashCode());
        category1 = new Category(1L, 1L, "Some Category Name With Parent",1L, LocalDateTime.now(), 1L);
        category2 = new Category(1L, 1L, "Some Category Name With Parent",1L, LocalDateTime.now(), 1L);
        assertThat(category1.hashCode(),equalTo(category2.hashCode()));
        category1 = new Category(1L, 1L, "Some Category Name With Parent1",1L, LocalDateTime.now(), 1L);
        category2 = new Category(2L, 2L, "Some Category Name With Parent2",2L, LocalDateTime.now(), 1L);
        assertNotEquals(category1.hashCode(),category2.hashCode());
        category1 = new Category(1L, "Some Parent Category Name", 1L);
        category2 = new Category(1L, "Some Parent Category Name", 1L);
        assertThat(category1.hashCode(), equalTo(category2.hashCode()));
        category1 = new Category(1L, "Some Parent Category Name1", 1L);
        category2 = new Category(2L, "Some Parent Category Name2", 1L);
        assertNotEquals(category1.hashCode(), category2.hashCode());
        category1 = new Category("Some Category Name", 1L);
        category2 = new Category("Some Category Name", 1L);
        assertThat(category1.hashCode(), equalTo(category2.hashCode()));
        category1 = new Category("Some Category Name1", 1L);
        category2 = new Category("Some Category Name2", 1L);
        assertNotEquals(category1.hashCode(),category2.hashCode());
    }
}