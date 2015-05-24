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
import com.malcolm.qme.rest.model.QMeCategoryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<QMeCategoryDetail> searchByName(String categoryName) throws QMeResourceException{
        //TODO:Catch Specific Errors
        try{
            return getQMeCategoryDetail(categoryRepo.findCategoryNameLike(categoryName));
        }catch(Exception err){
            throw new QMeResourceException(err.getMessage(),err);
        }

    }

    @Override
    public List<QMeCategoryDetail> list() throws QMeResourceException {
        //TODO:Catch Specific Errors
        try{
            return getQMeCategoryDetail(categoryRepo.findAll());
        }catch(Exception err){
            throw new QMeResourceException(err.getMessage(),err);
        }
    }

    @Override
    public QMeCategoryDetail searchById(Long id) throws QMeResourceException {
        Category category     =  categoryRepo.findById(id);
        if(category == null){
            throw new QMeResourceNotFoundException("Category with Category ID "+id+" not found");
        }
        return getQMeCategoryDetail(category);
    }

    @Override
    public QMeCategoryDetail save(QMeCategory qMeCategory, Long userId) throws QMeResourceException {
        Category category     =  getCategory(qMeCategory,userId);
        //TODO:Catch Specific Errors
        try{
            category = categoryRepo.save(category);
            return getQMeCategoryDetail(category);
        }catch(Exception err){
            throw new QMeResourceException(err.getMessage(),err);
        }
    }

    @Override
    public QMeCategoryDetail update(QMeCategory qMeCategory, Long categoryID, Long userId) throws QMeResourceException {
        Category category     =  getCategory(qMeCategory,categoryID,userId);
        //TODO:Catch Specific Errors
        try{
            category = categoryRepo.update(category, userId);
            return getQMeCategoryDetail(category);
        }catch(Exception err){
            throw new QMeResourceException(err.getMessage(),err);
        }
    }

    @Override
    public void delete(Long id) throws QMeResourceException {
        Category category     =  categoryRepo.findById(id);
        if(category == null){
            throw new QMeResourceNotFoundException("Category with Category ID "+id+" not found");
        }
        //TODO:Catch Specific Errors
        try{
            categoryRepo.delete(id);
        }catch(Exception err){
            throw new QMeResourceException(err.getMessage(),err);
        }
    }

    /**
     * Get Category for Create
     *
     * @param qMeCategory
     * @param userID
     * @return
     */
    private Category getCategory(QMeCategory qMeCategory,Long userID) throws QMeResourceException {
        if(qMeCategory.getParentCategoryId() != null){
            Category category     =  categoryRepo.findById(qMeCategory.getParentCategoryId());
            if(category == null){
                throw new QMeResourceNotFoundException("Category with Category ID "+qMeCategory.getParentCategoryId()+" not found");
            }
        }
        return new Category(qMeCategory.getParentCategoryId(),qMeCategory.getCategoryName(),userID);
    }

    /**
     * Get Category for Update
     * @param qMeCategory
     * @param categoryID
     *  @param userID
     * @return
     * @throws QMeResourceException
     */
    private Category getCategory(QMeCategory qMeCategory, Long categoryID,Long userID) throws QMeResourceException {
       Category category     =  categoryRepo.findById(categoryID);
       if(category == null){
           throw new QMeResourceNotFoundException("Category with Category ID "+categoryID+" not found");
       }
       Long parentCategoryID = category.getCategoryParentID();
       if(qMeCategory.getParentCategoryId() != null ){
           Category parentCategory     =  categoryRepo.findById(qMeCategory.getParentCategoryId());
           if(parentCategory == null){
               throw new QMeResourceNotFoundException("Category with Category ID "+qMeCategory.getParentCategoryId()+" not found");
           }
           parentCategoryID = qMeCategory.getParentCategoryId();
       }
       String categoryName   =  (qMeCategory.getCategoryName() == null) ? category.getCategoryName() : qMeCategory.getCategoryName();
       return new Category(
               category.getCategoryID(),
               parentCategoryID,
               categoryName,
               category.getCategoryLikes(),
               LocalDateTime.now(),
               userID
       );
    }

    /**
     *
     * @param categoryList Category List
     * @return QMeCategory List
     */
    private List<QMeCategoryDetail> getQMeCategoryDetail(List<Category> categoryList){
        List<QMeCategoryDetail> qMeCategories = new ArrayList<>();
        if(categoryList == null){
            return qMeCategories;
        }
        qMeCategories.addAll(
                categoryList.stream().map
                        (this::getQMeCategoryDetail).collect(Collectors.toList())
        );
        return qMeCategories;
    }

    /**
     * Map Category Domain Object to REST Model
     *
     * @param category Category
     * @return QMeCategory
     */
    private QMeCategoryDetail getQMeCategoryDetail(Category category){
        QMeCategoryDetail qmeCategory = new QMeCategoryDetail();
        qmeCategory.setCategoryId(category.getCategoryID());
        qmeCategory.setParentCategoryId(category.getCategoryParentID());
        qmeCategory.setCategoryName(category.getCategoryName());
        qmeCategory.setCategoryLikes(category.getCategoryLikes());
        qmeCategory.setCreatedDate(category.getCategoryCreateDate());
        qmeCategory.setCreatedUser(category.getCategoryCreateUserID());
        //Fixme: Need to add created user name
        qmeCategory.setCreatedUserName("");
        return qmeCategory;
    }
}
