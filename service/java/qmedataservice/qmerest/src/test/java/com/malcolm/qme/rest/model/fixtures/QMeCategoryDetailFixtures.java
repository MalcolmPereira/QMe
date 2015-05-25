/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeCategoryDetailFixtures.java
 * Date      : 5/24/15
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe Category Details
 */

package com.malcolm.qme.rest.model.fixtures;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malcolm.qme.rest.model.QMeCategoryDetail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
public final class QMeCategoryDetailFixtures extends QMeResourceFixture<QMeCategoryDetail>{

    /**
     * Simple QMe Category Detail
     *
     * @return  QMeCategoryDetail
     */
    public static QMeCategoryDetail simpleQMeCategoryDetail(){
        QMeCategoryDetail qmeCategoryDetail = new QMeCategoryDetail();
        qmeCategoryDetail.setCategoryId(1L);
        qmeCategoryDetail.setParentCategoryId(null);
        qmeCategoryDetail.setCategoryName("Simple Category 1");
        qmeCategoryDetail.setCategoryLikes(0L);
        qmeCategoryDetail.setCreatedDate(LocalDateTime.now());
        qmeCategoryDetail.setCreatedUser(1L);
        qmeCategoryDetail.setCreatedUserName("Test User");
        return qmeCategoryDetail;
    }

    /**
     * Simple Category
     *

     * @return Category List
     */
    public static List<QMeCategoryDetail> simpleQMeCategoryDetailList(){
        List<QMeCategoryDetail> categoryList = new ArrayList<>();

        QMeCategoryDetail qmeCategoryDetail = new QMeCategoryDetail();
        qmeCategoryDetail.setCategoryId(1L);
        qmeCategoryDetail.setParentCategoryId(null);
        qmeCategoryDetail.setCategoryName("Simple Category 1");
        qmeCategoryDetail.setCategoryLikes(0L);
        qmeCategoryDetail.setCreatedDate(LocalDateTime.now());
        qmeCategoryDetail.setCreatedUser(1L);
        qmeCategoryDetail.setCreatedUserName("Test User");
        categoryList.add(qmeCategoryDetail);

        qmeCategoryDetail = new QMeCategoryDetail();
        qmeCategoryDetail.setCategoryId(2L);
        qmeCategoryDetail.setParentCategoryId(null);
        qmeCategoryDetail.setCategoryName("Simple Category 2");
        qmeCategoryDetail.setCategoryLikes(0L);
        qmeCategoryDetail.setCreatedDate(LocalDateTime.now());
        qmeCategoryDetail.setCreatedUser(1L);
        qmeCategoryDetail.setCreatedUserName("Test User");
        categoryList.add(qmeCategoryDetail);

        qmeCategoryDetail = new QMeCategoryDetail();
        qmeCategoryDetail.setCategoryId(3L);
        qmeCategoryDetail.setParentCategoryId(null);
        qmeCategoryDetail.setCategoryName("Simple Category 3");
        qmeCategoryDetail.setCategoryLikes(0L);
        qmeCategoryDetail.setCreatedDate(LocalDateTime.now());
        qmeCategoryDetail.setCreatedUser(1L);
        qmeCategoryDetail.setCreatedUserName("Test User");
        categoryList.add(qmeCategoryDetail);

        qmeCategoryDetail = new QMeCategoryDetail();
        qmeCategoryDetail.setCategoryId(4L);
        qmeCategoryDetail.setParentCategoryId(null);
        qmeCategoryDetail.setCategoryName("Simple Category 4");
        qmeCategoryDetail.setCategoryLikes(0L);
        qmeCategoryDetail.setCreatedDate(LocalDateTime.now());
        qmeCategoryDetail.setCreatedUser(1L);
        qmeCategoryDetail.setCreatedUserName("Test User");
        categoryList.add(qmeCategoryDetail);

        qmeCategoryDetail = new QMeCategoryDetail();
        qmeCategoryDetail.setCategoryId(5L);
        qmeCategoryDetail.setParentCategoryId(null);
        qmeCategoryDetail.setCategoryName("Simple Category 5");
        qmeCategoryDetail.setCategoryLikes(0L);
        qmeCategoryDetail.setCreatedDate(LocalDateTime.now());
        qmeCategoryDetail.setCreatedUser(1L);
        qmeCategoryDetail.setCreatedUserName("Test User");
        categoryList.add(qmeCategoryDetail);

        return categoryList;
    }

}
