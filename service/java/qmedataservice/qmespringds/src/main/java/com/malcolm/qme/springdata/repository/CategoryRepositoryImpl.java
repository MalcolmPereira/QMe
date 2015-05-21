/**
 * Name      : com.malcolm.qme.springdata.repository.CategoryRepositoryImpl.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : QMe CategoryEntity Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.springdata.entity.CategoryEntity;

/**
 * @author Malcolm
 */
@Repository("CategoryRepository")
public class CategoryRepositoryImpl implements CategoryRepository {
	
	/**
     * Spring Data CategoryEntity Repository
     */
    @Autowired
    private CategorySpringDataRepository categorySpringDataRepository;
	
	@Override
	public List<Category> findAll() {
        return(getCategory(categorySpringDataRepository.findAll()));
	}

    @Override
    public List<Category> findCategoryNameLike(String categoryName) {
        return(getCategory(categorySpringDataRepository.findByCatNameIgnoreCaseLike(categoryName)));
    }

	@Override
	public Category findById(Long id) {
        CategoryEntity categoryEntity = categorySpringDataRepository.findOne(id);
        if(categoryEntity != null){
            return getCategory(categoryEntity);
        }
        return null;


    }

	@Override
	public Category save(Category category) {
        CategoryEntity categoryEntity = getCategoryEntity(category);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        return getCategory(categoryEntity);
	}

	@Override
	public Category update(Category category, Long updateUserId) {
        CategoryEntity categoryEntity = getCategoryEntity(category);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        return getCategory(categoryEntity);
	}

	@Override
	public void delete(Long id) {
        categorySpringDataRepository.delete(id);

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
            categoryEntity.setCatCreateDate(new Date());
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
		List<Category> categoryList = new ArrayList<Category>();
		if(categoryEntities == null){
			return categoryList;
		}
		for (CategoryEntity categoryEntity : categoryEntities){
			categoryList.add(getCategory(categoryEntity));
		}
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
