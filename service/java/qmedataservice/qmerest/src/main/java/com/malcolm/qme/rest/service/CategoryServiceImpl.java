/**
 * Name      : com.malcolm.qme.rest.service.CategoryServiceImpl.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QMeCategory Service Implementation
 */

package com.malcolm.qme.rest.service;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.rest.model.QMeCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: malcolm
 */
@Service
public final class CategoryServiceImpl implements CategoryService  {

    /**
     * QMeCategory Repository
     */
    @Autowired
    @Qualifier("CategoryRepository")
    private CategoryRepository categoryRepo;

    @Override
    public List<QMeCategory> searchByName(String categoryName) {
        return null;
    }

    @Override
    public List<QMeCategory> list() {
        return null;
    }

    @Override
    public QMeCategory searchById(Long id) {
        return null;
    }

    @Override
    public QMeCategory create(QMeCategory category) {
        return null;
    }

    @Override
    public QMeCategory update(QMeCategory category, Long updateUserId) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }

    /**
     * Map Category Domain Object to REST Model
     *
     * @param category
     * @return
     */
    private QMeCategory getCategory(Category category){
        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryId(category.getCategoryID());
        qmeCategory.setParentCategoryId(category.getCategoryParentID());
        qmeCategory.setCategoryName(category.getCategoryName());
        qmeCategory.setCategoryLikes(category.getCategoryLikes());
        qmeCategory.setCreatedDate(category.getCategoryCreateDate());
        qmeCategory.setCreatedUser(category.getCategoryCreateUserID());
        return qmeCategory;
    }
}
