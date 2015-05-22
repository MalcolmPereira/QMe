/**
 * Name      : com.malcolm.qme.rest.service.CategoryService.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QMeCategory Service Interface
 */
package com.malcolm.qme.rest.service;

import com.malcolm.qme.rest.model.QMeCategory;

import java.util.List;

/**
 * @author malcolm
 */
public interface CategoryService extends QMeService<QMeCategory,Long> {

    /**
     * Search By Name
     *
     * @param categoryName Category Name
     * @return QMeCategory List
     */
     List<QMeCategory> searchByName(String categoryName);
}