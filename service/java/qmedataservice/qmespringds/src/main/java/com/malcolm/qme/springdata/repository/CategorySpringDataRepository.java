/**
 * Name      : com.malcolm.qme.springdata.repository.CategorySpringDataRepository.java
 * Date      : 5/5/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA CategoryEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
	CategoryEntity findByCatName(String categoryName);

	@Query(value = "SELECT CAT_ID,CAT_NAME, CAT_PARENT_ID,CAT_CREATE_DATE,CAT_CREATE_USER,CAT_LIKES,CAT_CREATE_USER FROM CATEGORY WHERE CAT_ID in (SELECT DISTINCT CAT_ID from QUESTION)", nativeQuery = true)
	List<CategoryEntity> findCategoriesWithQuestions();

	/**
	 * Find by Category Name Like
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
