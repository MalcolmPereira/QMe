/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizGameSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuizGameEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.malcolm.qme.springdata.entity.UserQuizGameEntity;

/**
 * @Author: Malcolm
 */
public interface UserQuizGameSpringDataRepository extends JpaRepository<UserQuizGameEntity, Long> {
	@Query(value = "SELECT * FROM USER_QUIZ_GAME WHERE USER_ID = ?1", nativeQuery = true)
	List<UserQuizGameEntity> findByUserId(Long userId);
	
	@Query(value = "SELECT * FROM USER_QUIZ_GAME WHERE QUIZ_GAME_TOKEN = ?1", nativeQuery = true)
	UserQuizGameEntity findByGameToken(Long quizGameToken);
}
