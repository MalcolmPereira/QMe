/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizLikesSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuizLikesEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.malcolm.qme.springdata.entity.UserQuizLikesEntity;
import com.malcolm.qme.springdata.entity.UserQuizLikesEntityId;

/**
 * @Author: Malcolm
 */
public interface UserQuizLikesSpringDataRepository extends JpaRepository<UserQuizLikesEntity, UserQuizLikesEntityId> {
	@Query(value = "SELECT * FROM USER_QUIZ_LIKES WHERE USER_ID = ?1", nativeQuery = true)
	List<UserQuizLikesEntity> findByUserId(Long userId);

	@Query(value = "SELECT * FROM USER_QUIZ_LIKES WHERE QUIZ_ID = ?1", nativeQuery = true)
	List<UserQuizLikesEntity> findByQuizId(Long quizId);
}
