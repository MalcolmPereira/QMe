/**
 * Name      : com.malcolm.qme.rest.model.QMeCategoryTest.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QMeCategory Model for REST Service Controller Test
 */

package com.malcolm.qme.rest.model;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * QMeCategory Model for REST Service Controller Test
 *
 * @author Malcolm
 */
public class QMeCategoryTest {

    @Test
    public void testGetCategoryName()  {
        QMeCategory qMeCategory = new QMeCategory();
        qMeCategory.setCategoryName("Some CategoryName");
        assertThat(qMeCategory.getCategoryName(), equalTo("Some CategoryName"));

    }

    @Test
    public void testSetCategoryName() {
        QMeCategory qMeCategory = new QMeCategory();
        qMeCategory.setCategoryName("Some CategoryName");
        assertThat(qMeCategory.getCategoryName(), equalTo("Some CategoryName"));

    }

    @Test
    public void testGetParentCategoryId() {
        QMeCategory qMeCategory = new QMeCategory();
        qMeCategory.setCategoryName("Some CategoryName");
        qMeCategory.setParentCategoryId(1L);
        assertThat(qMeCategory.getCategoryName(), equalTo("Some CategoryName"));
        assertThat(qMeCategory.getParentCategoryId(), equalTo(1L));
    }

    @Test
    public void testSetParentCategoryId() {
        QMeCategory qMeCategory = new QMeCategory();
        qMeCategory.setCategoryName("Some CategoryName");
        qMeCategory.setParentCategoryId(1L);
        assertThat(qMeCategory.getCategoryName(), equalTo("Some CategoryName"));
        assertThat(qMeCategory.getParentCategoryId(), equalTo(1L));
    }

}