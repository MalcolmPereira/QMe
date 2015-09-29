/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizLikesSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuizLikesEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserQuizLikesEntity;
import com.malcolm.qme.springdata.entity.UserQuizLikesEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Malcolm
 */
public interface UserQuizLikesSpringDataRepository extends JpaRepository<UserQuizLikesEntity, UserQuizLikesEntityId> {
	/**
	 * Find By User ID
	 *
	 * @param userId User ID
	 * @return UserQuizLikesEntity List
	 */
	@Query(value = "SELECT * FROM USER_QUIZ_LIKES WHERE USER_ID = ?1", nativeQuery = true)
	List<UserQuizLikesEntity> findByUserId(Long userId);

	/**
	 * Find By Quiz ID
	 *
	 * @param quizId Quiz ID
	 * @return UserQuizLikesEntity List
	 */
	@Query(value = "SELECT * FROM USER_QUIZ_LIKES WHERE QUIZ_ID = ?1", nativeQuery = true)
	List<UserQuizLikesEntity> findByQuizId(Long quizId);
}
