/**
 * Name      : com.malcolm.qme.springdata.repository.CategorySpringDataRepository.java
 * Date      : 5/5/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA CategoryEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malcolm.qme.springdata.entity.CategoryEntity;

/**
 * @author Malcolm
 */
interface CategorySpringDataRepository extends JpaRepository<CategoryEntity, Long> {

	/**
	 * Find by Category Name
	 * @param categoryName Category Name
	 * @return CategoryEntity List
	 */
	List<CategoryEntity> findByCatNameIgnoreCaseLike(String categoryName);
}
