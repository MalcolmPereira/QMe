/**
 * Name      : com.malcolm.qme.springdata.repository.CategorySpringDataRepository.java
 * Date      : 5/5/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA CategoryEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Malcolm
 */
@Repository
interface CategorySpringDataRepository extends JpaRepository<CategoryEntity, Long> {

	/**
	 * Find by Category Name
	 * @param categoryName Category Name
	 * @return CategoryEntity List
	 */
	List<CategoryEntity> findByCatNameIgnoreCaseLike(String categoryName);

	/**
	 * Find by Category Name
	 * @param catParentId Category Parent Id
	 * @return CategoryEntity List
	 */
	List<CategoryEntity> findByCatParentId(Long catParentId);
}
