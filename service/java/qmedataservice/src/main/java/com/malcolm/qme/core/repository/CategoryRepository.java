/**
 * Name      : com.malcolm.qme.core.repository.CategoryRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Category Repository Class
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.Category;

import java.util.List;

/**
 * @Author: Malcolm
 */
public interface CategoryRepository extends QMeRepository<Category> {

    /**
     * Find By Category Name Like
     * @param categoryName
     * @return
     */
    public List<Category> findCategoryNameLike(String categoryName);
}
