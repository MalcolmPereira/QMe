/**
 * Name      : com.malcolm.qme.springdata.repository.UserCategoryLikesSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserCategoryLikesEntity Repository
 */

package com.malcolm.qme.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.malcolm.qme.springdata.entity.UserCategoryLikesEntity;
import com.malcolm.qme.springdata.entity.UserCategoryLikesEntityId;

/**
 * @Author: Malcolm
 */
interface UserCategoryLikesSpringDataRepository extends JpaRepository<UserCategoryLikesEntity, UserCategoryLikesEntityId> {
	
	@Query(value = "SELECT * FROM USER_CATEGORY_LIKES WHERE USER_ID = ?1", nativeQuery = true)
	List<UserCategoryLikesEntity> findByUserId(Long userId);
}
