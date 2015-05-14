/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuestionLikesSpringDataRepository.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuestionLikesEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.malcolm.qme.springdata.entity.UserQuestionLikesEntity;
import com.malcolm.qme.springdata.entity.UserQuestionLikesEntityId;

/**
 * @Author: Malcolm
 */
interface UserQuestionLikesSpringDataRepository extends JpaRepository<UserQuestionLikesEntity, UserQuestionLikesEntityId> {
	
	@Query(value = "SELECT * FROM USER_QUESTION_LIKES WHERE USER_ID = ?1", nativeQuery = true)
	List<UserQuestionLikesEntity> findByUserId(Long userId);
	
	@Query(value = "SELECT * FROM USER_QUESTION_LIKES WHERE QUESTION_ID = ?1", nativeQuery = true)
	List<UserQuestionLikesEntity> findByQuestionId(Long questionId);
	
}
