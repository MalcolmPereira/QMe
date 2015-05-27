/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizGameSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuizGameEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserQuizGameEntity;
import com.malcolm.qme.springdata.entity.UserQuizGameEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Malcolm
 */
public interface UserQuizGameSpringDataRepository extends JpaRepository<UserQuizGameEntity, UserQuizGameEntityId> {
	/**
	 * Find By User ID
	 * @param userId User ID
	 * @return UserQuizGameEntity List
	 */
	@Query(value = "SELECT * FROM USER_QUIZ_GAME WHERE USER_ID = ?1", nativeQuery = true)
	List<UserQuizGameEntity> findByUserId(Long userId);

	/**
	 * Find by Game Token
	 *
	 * @param quizGameToken Game Token
	 * @return UserQuizGameEntity List
	 */
	@Query(value = "SELECT * FROM USER_QUIZ_GAME WHERE QUIZ_GAME_TOKEN = ?1", nativeQuery = true)
	UserQuizGameEntity findByGameToken(Long quizGameToken);
}
