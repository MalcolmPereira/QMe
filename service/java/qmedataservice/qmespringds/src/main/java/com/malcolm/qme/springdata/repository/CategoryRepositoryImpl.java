/**
 * Name      : com.malcolm.qme.springdata.repository.CategoryRepositoryImpl.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : QMe CategoryEntity Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.springdata.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Repository(value = "CategoryRepository")
public class CategoryRepositoryImpl implements CategoryRepository {
	
	/**
     * Spring Data CategoryEntity Repository
     */
    @Autowired
    private CategorySpringDataRepository categorySpringDataRepository;
	
	@Override
	public List<Category> findAll() throws QMeException {
        try{
            return(getCategory(categorySpringDataRepository.findAll()));
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

    @Override
    public List<Category> findAll(PageSort pageSort) throws QMeException {
        return null;
    }

    @Override
    public List<Category> findCategoryNameLike(String categoryName) throws QMeException {
        try{
            return(getCategory(categorySpringDataRepository.findByCatNameIgnoreCaseLike(categoryName)));
        }catch(Exception err){
            throw new QMeException(err);
        }
    }

	@Override
	public Category findById(Long id) throws QMeException {
        try{
            CategoryEntity categoryEntity = categorySpringDataRepository.findOne(id);
            if(categoryEntity != null){
                return getCategory(categoryEntity);
            }
            return null;
        }catch(Exception err){
            throw new QMeException(err);
        }


    }

	@Override
	public Category save(Category category) throws QMeException {
        try{
            CategoryEntity categoryEntity = getCategoryEntity(category);
            categoryEntity = categorySpringDataRepository.save(categoryEntity);
            return getCategory(categoryEntity);
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public Category update(Category category, Long updateUserId) throws QMeException {
        try{
            CategoryEntity categoryEntity = getCategoryEntity(category);
            categoryEntity = categorySpringDataRepository.save(categoryEntity);
            return getCategory(categoryEntity);
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public void delete(Long id) throws QMeException {
        try{
            categorySpringDataRepository.delete(id);
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	/**
     * Map Category Domain Object to CategoryEntity
     *
     * @param category Category
     * @return CategoryEntity
     */
    private CategoryEntity getCategoryEntity(Category category){
        CategoryEntity categoryEntity = new CategoryEntity();
        if(category.getCategoryID() > 0){
            categoryEntity.setCatId(category.getCategoryID());
        }
        if(category.getCategoryParentID() > 0){
            categoryEntity.setCatParentId(category.getCategoryParentID());
        }else{
            categoryEntity.setCatParentId(0L);
        }
        categoryEntity.setCatName(category.getCategoryName());
        categoryEntity.setCatLikes(category.getCategoryLikes());
        if(category.getCategoryCreateDate() != null)    {
            categoryEntity.setCatCreateDate(category.getCategoryCreateDate());
        }else{
            categoryEntity.setCatCreateDate(LocalDateTime.now());
        }
        categoryEntity.setCatCreateUser(category.getCategoryCreateUserID());
        return categoryEntity;
    }

	/**
	 * Map CategoryEntity to Category Domain Object
	 *
	 * @param categoryEntities CategoryEntity List
	 * @return Category List
	 */
	private List<Category> getCategory(List<CategoryEntity> categoryEntities){
		List<Category> categoryList = new ArrayList<>();
		if(categoryEntities == null){
			return categoryList;
		}
        categoryList.addAll(categoryEntities.stream().map(this::getCategory).collect(Collectors.toList()));
		return categoryList;

	}

	/**
	 * Map CategoryEntity to Category Domain Object
	 * 
	 * @param categoryEntity CategoryEntity
	 * @return Category
	 */
	private Category getCategory(CategoryEntity categoryEntity){
       return new Category(categoryEntity.getCatId(),
				categoryEntity.getCatParentId(),
				categoryEntity.getCatName(),
				categoryEntity.getCatLikes(),
                categoryEntity.getCatCreateDate(),
				categoryEntity.getCatCreateUser()

	   );
	}

}
