/**
 * Name      : com.malcolm.qme.springdata.repository.CategoryRepositoryImpl.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : QMe CategoryEntity Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.springdata.entity.CategoryEntity;

/**
 * @Author: Malcolm
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category save(Category t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category update(Category t, Long updateUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Category> findCategoryNameLike(String categoryName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Map CategoryEntity to Category Domain Object
	 * 
	 * @param categoryEntity
	 * @return
	 */
	private Category getCategory(CategoryEntity categoryEntity){
		/*return new Category(categoryEntity.getCatId(), 
				categoryEntity.getCatParentId(),
				categoryEntity.getCatName(),
		);
		
		public Category(Long categoryID, Long categoryParentID, String categoryName, Date categoryCreateDate, Long categoryCreateUserID) {
		*/
		return null;
	}

}
