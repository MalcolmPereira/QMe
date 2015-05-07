/**
 * Name      : com.malcolm.qme.springdata.repository.UserCategoryLikesSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserCategoryLikesEntity Repository
 */

package com.malcolm.qme.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malcolm.qme.springdata.entity.UserCategoryLikesEntity;

/**
 * @Author: Malcolm
 */
public interface UserCategoryLikesSpringDataRepository extends JpaRepository<UserCategoryLikesEntity, Long> {
}
