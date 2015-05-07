/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuestionLikesSpringDataRepository.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuestionLikesEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malcolm.qme.springdata.entity.UserQuestionLikesEntity;

/**
 * @Author: Malcolm
 */
interface UserQuestionLikesSpringDataRepository extends JpaRepository<UserQuestionLikesEntity, Long> {
}
