/**
 * Name      : com.malcolm.qme.rest.service.impl.CategoryServiceImpl.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QMeCategory Service Implementation
 */

package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.rest.exception.*;
import com.malcolm.qme.rest.model.QMeCategory;
import com.malcolm.qme.rest.model.QMeCategoryDetail;
import com.malcolm.qme.rest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Service
public final class CategoryServiceImpl implements CategoryService {
    /**
     * QMeCategory Repository
     */
    @Autowired
    @Qualifier(value = "CategoryRepository")
    private CategoryRepository categoryRepo;


    @Override
    public List<QMeCategoryDetail> searchByName(String categoryName) throws QMeResourceNotFoundException,QMeServerException {
        try{
            return getQMeCategoryDetail(categoryRepo.findCategoryNameLike(categoryName));

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeCategoryDetail> list() throws QMeServerException {
        try{
            return getQMeCategoryDetail(categoryRepo.findAll());

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeCategoryDetail> list(Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException {
        return null;
    }

    @Override
    public QMeCategoryDetail searchById(Long id) throws QMeResourceNotFoundException,QMeServerException {
        try{
            Category category     =  categoryRepo.findById(id);
            if(category == null){
                throw new QMeResourceNotFoundException("Category with Category ID "+id+" not found");
            }
            return getQMeCategoryDetail(category);

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeCategoryDetail save(QMeCategory qMeCategory, Long userId) throws QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        try{
            Category category     =  getCategory(qMeCategory,userId);
            category = categoryRepo.save(category);
            return getQMeCategoryDetail(category);

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeCategoryDetail update(QMeCategory qMeCategory, Long categoryID, Long userId) throws QMeResourceNotFoundException,QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        try{
            Category category =  getCategory(qMeCategory,categoryID,userId);
            category = categoryRepo.update(category, userId);
            return getQMeCategoryDetail(category);

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);

        }
    }

    @Override
    public void delete(Long id) throws QMeResourceNotFoundException,QMeServerException {
        try{
            Category category     =  categoryRepo.findById(id);

            if(category == null){
                throw new QMeResourceNotFoundException("Category with Category ID "+id+" not found");
            }

            categoryRepo.delete(id);

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }

    }

    /**
     * Get Category for Create
     *
     * @param qMeCategory Category
     * @param userID User Id
     * @return Category domain object
     */
    private Category getCategory(QMeCategory qMeCategory,Long userID)  {
        return new Category(qMeCategory.getParentCategoryId(),qMeCategory.getCategoryName(),userID);
    }

    /**
     * Get Category for Update
     *
     * @param qMeCategory Category
     * @param categoryID Category Id
     * @param userID User Id
     * @return Category
     * @throws QMeResourceNotFoundException
     * @throws QMeServerException
     */
    private Category getCategory(QMeCategory qMeCategory, Long categoryID,Long userID) throws QMeResourceNotFoundException, QMeServerException {
        try{
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
        }catch(QMeException err){
           throw new QMeServerException(err.getMessage(),err);
        }
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
