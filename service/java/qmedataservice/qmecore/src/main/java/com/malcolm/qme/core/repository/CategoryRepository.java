/**
 * Name      : com.malcolm.qme.core.repository.CategoryRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Category Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.Category;

import java.util.List;

/**
 * @author Malcolm
 */
public interface CategoryRepository extends QMeRepository<Category,Long> {

    /**
     * Find Categories with questions
     * @return Category List matching category name like
     * @throws QMeException
     */
    List<Category> findCategoriesWithQuestions()throws QMeException;

    /**
     * Find By Category Name
     * @param categoryName Category Name
     * @return Category matching category name
     * @throws QMeException
     */
    Category findCategoryByName(String categoryName) throws QMeException;

    /**
     * Find By Category Name Like
     * @param categoryName Category Name Like
     * @return Category List matching category name like
     * @throws QMeException
     */
    List<Category> findCategoryNameLike(String categoryName) throws QMeException;

    /**
     * Find By Category By Parent Id
     * @param categoryParentID Category Parent Id
     * @return Category List for Category Parent
     * @throws QMeException
     */
    List<Category> findCategoryByParentId(Long categoryParentID) throws QMeException;
}
