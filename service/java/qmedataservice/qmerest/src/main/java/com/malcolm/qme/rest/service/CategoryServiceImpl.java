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

import java.util.ArrayList;
import java.util.Date;
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
        return getQMeCategory(categoryRepo.findAll());
    }

    @Override
    public QMeCategory searchById(Long id) {
        Category category = categoryRepo.findById(id);
        if(category != null){
            return getQMeCategory(category);
        }
        return null;
    }

    @Override
    public QMeCategory create(QMeCategory qmeCategory) {
        Category category = getCategory(qmeCategory);
        category = categoryRepo.save(category);
        return getQMeCategory(category);
    }

    @Override
    public QMeCategory update(QMeCategory qmeCategory, Long updateUserId) {
        Category category = getCategory(qmeCategory);
        category = categoryRepo.update(category, updateUserId);
        return getQMeCategory(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepo.delete(id);
    }

    /**
     * Get Category
     * @param qMeCategory QMeCategory
     * @return Category
     */
    private Category getCategory(QMeCategory qMeCategory){
        return new Category(
                qMeCategory.getCategoryId(),
                qMeCategory.getParentCategoryId(),
                qMeCategory.getCategoryName(),
                qMeCategory.getCategoryLikes(),
                qMeCategory.getCreatedDate(),
                qMeCategory.getCreatedUser()
        );
    }

    /**
     *
     * @param categoryList Category List
     * @return QMeCategory List
     */
    private List<QMeCategory> getQMeCategory(List<Category> categoryList){
        List<QMeCategory> qMeCategories = new ArrayList<>();
        if(categoryList == null){
            return qMeCategories;
        }
        for (Category category: categoryList){
            qMeCategories.add(getQMeCategory(category));
        }
        return qMeCategories;
    }

    /**
     * Map Category Domain Object to REST Model
     *
     * @param category Category
     * @return QMeCategory
     */
    private QMeCategory getQMeCategory(Category category){
        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryId(category.getCategoryID());
        qmeCategory.setParentCategoryId(category.getCategoryParentID());
        qmeCategory.setCategoryName(category.getCategoryName());
        qmeCategory.setCategoryLikes(category.getCategoryLikes());
        qmeCategory.setCreatedDate(category.getCategoryCreateDate());
        qmeCategory.setCreatedUser(category.getCategoryCreateUserID());
        //Fixme: Neeed to set careted user name
        qmeCategory.setCreatedUserName("");
        return qmeCategory;
    }
}
