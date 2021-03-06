/**
 * Name      : com.malcolm.qme.rest.service.CategoryService.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QMeCategory Service Interface
 */
package com.malcolm.qme.rest.service;

import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeCategory;
import com.malcolm.qme.rest.model.QMeCategoryDetail;

import java.util.List;

/**
 * @author malcolm
 */
public interface CategoryService extends QMeService<QMeCategoryDetail,QMeCategory,Long> {

    /**
     * List Categories containing questions
     *
     * @return QMeCategoryDetail List
     * @throws QMeServerException
     */
    List<QMeCategoryDetail>  listContainingQuestions() throws QMeServerException;

    /**
     * Search By Name
     *
     * @param categoryName Category Name
     * @return QMeCategoryDetail List
     * @throws QMeServerException
     */
     List<QMeCategoryDetail> searchByName(String categoryName) throws QMeServerException;

    /**
     * Search By Parent Category
     *
     * @param parentCategoryId Parent Category Id
     * @return QMeCategoryDetail List
     * @throws QMeServerException
     */
    List<QMeCategoryDetail> searchByParentCategory(Long parentCategoryId) throws QMeServerException;


}
