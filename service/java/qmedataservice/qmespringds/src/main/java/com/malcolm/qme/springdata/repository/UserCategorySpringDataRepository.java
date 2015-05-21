/**
 * Name      : com.malcolm.qme.springdata.repository.UserCategorySpringDataRepository.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserCategoryEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malcolm.qme.springdata.entity.UserCategoryEntity;

/**
 * @author Malcolm
 */
interface UserCategorySpringDataRepository extends JpaRepository<UserCategoryEntity, Long> {
	/**
     * Find By User ID
     * @param userId User ID
     * @return UserCategoryEntity List
     */
    List<UserCategoryEntity> findByUserId(Long userId);
}
