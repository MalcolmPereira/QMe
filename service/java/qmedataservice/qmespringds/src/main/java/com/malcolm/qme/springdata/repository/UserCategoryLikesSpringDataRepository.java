/**
 * Name      : com.malcolm.qme.springdata.repository.UserCategoryLikesSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserCategoryLikesEntity Repository
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserCategoryLikesEntity;
import com.malcolm.qme.springdata.entity.UserCategoryLikesEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Malcolm
 */
@Repository
interface UserCategoryLikesSpringDataRepository extends JpaRepository<UserCategoryLikesEntity, UserCategoryLikesEntityId> {
	/**
	 * Find Category Likes By User ID
	 * @param userId User ID
	 * @return UserCategoryLikesEntity List
	 */
	@Query(value = "SELECT * FROM USER_CATEGORY_LIKES WHERE USER_ID = ?1", nativeQuery = true)
	List<UserCategoryLikesEntity> findByUserId(Long userId);
}
