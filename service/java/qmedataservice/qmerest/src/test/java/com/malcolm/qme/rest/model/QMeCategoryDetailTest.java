/**
 * Name      : com.malcolm.qme.rest.model.QMeCategoryDetailTest.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : QMeCategory Details Model for REST Service Controller Test
 */
package com.malcolm.qme.rest.model;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author malcolm
 */
public class QMeCategoryDetailTest {

    @Test
    public void testGetCategoryId() throws Exception {
        QMeCategoryDetail qMeCategoryDetail = new QMeCategoryDetail();
        qMeCategoryDetail.setCategoryId(1L);
        assertThat(qMeCategoryDetail.getCategoryId(), equalTo(1L));
    }

    @Test
    public void testSetCategoryId() throws Exception {
        QMeCategoryDetail qMeCategoryDetail = new QMeCategoryDetail();
        qMeCategoryDetail.setCategoryId(1L);
        assertThat(qMeCategoryDetail.getCategoryId(), equalTo(1L));
    }

    @Test
    public void testGetCategoryLikes() throws Exception {
        QMeCategoryDetail qMeCategoryDetail = new QMeCategoryDetail();
        qMeCategoryDetail.setCategoryLikes(1L);
        assertThat(qMeCategoryDetail.getCategoryLikes(), equalTo(1L));

    }

    @Test
    public void testSetCategoryLikes() throws Exception {
        QMeCategoryDetail qMeCategoryDetail = new QMeCategoryDetail();
        qMeCategoryDetail.setCategoryLikes(1L);
        assertThat(qMeCategoryDetail.getCategoryLikes(), equalTo(1L));
    }

    @Test
    public void testGetCreatedDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QMeCategoryDetail qMeCategoryDetail = new QMeCategoryDetail();
        qMeCategoryDetail.setCreatedDate(now);
        assertThat(qMeCategoryDetail.getCreatedDate(), equalTo(now));
    }

    @Test
    public void testSetCreatedDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QMeCategoryDetail qMeCategoryDetail = new QMeCategoryDetail();
        qMeCategoryDetail.setCreatedDate(now);
        assertThat(qMeCategoryDetail.getCreatedDate(), equalTo(now));
    }

    @Test
    public void testGetCreatedUser() throws Exception {
        QMeCategoryDetail qMeCategoryDetail = new QMeCategoryDetail();
        qMeCategoryDetail.setCreatedUser(1L);
        assertThat(qMeCategoryDetail.getCreatedUser(), equalTo(1L));
    }

    @Test
    public void testSetCreatedUser() throws Exception {
        QMeCategoryDetail qMeCategoryDetail = new QMeCategoryDetail();
        qMeCategoryDetail.setCreatedUser(1L);
        assertThat(qMeCategoryDetail.getCreatedUser(), equalTo(1L));
    }

    @Test
    public void testGetCreatedUserName() throws Exception {
        QMeCategoryDetail qMeCategoryDetail = new QMeCategoryDetail();
        qMeCategoryDetail.setCreatedUserName("Some User");
        assertThat(qMeCategoryDetail.getCreatedUserName(), equalTo("Some User"));
    }

    @Test
    public void testSetCreatedUserName() throws Exception {
        QMeCategoryDetail qMeCategoryDetail = new QMeCategoryDetail();
        qMeCategoryDetail.setCreatedUserName("Some User");
        assertThat(qMeCategoryDetail.getCreatedUserName(), equalTo("Some User"));
    }

}