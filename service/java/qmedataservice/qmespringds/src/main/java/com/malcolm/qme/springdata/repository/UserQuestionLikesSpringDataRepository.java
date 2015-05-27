/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuestionLikesSpringDataRepository.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuestionLikesEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserQuestionLikesEntity;
import com.malcolm.qme.springdata.entity.UserQuestionLikesEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: Malcolm
 */
interface UserQuestionLikesSpringDataRepository extends JpaRepository<UserQuestionLikesEntity, UserQuestionLikesEntityId> {
	/**
	 * Find By User ID
	 * @param userId By User ID
	 * @return UserQuestionLikesEntity List
	 */
	@Query(value = "SELECT * FROM USER_QUESTION_LIKES WHERE USER_ID = ?1", nativeQuery = true)
	List<UserQuestionLikesEntity> findByUserId(Long userId);

	/**
	 * Find By Question ID
	 *
	 * @param questionId Question ID
	 * @return UserQuestionLikesEntity List
	 */
	@Query(value = "SELECT * FROM USER_QUESTION_LIKES WHERE QUESTION_ID = ?1", nativeQuery = true)
	List<UserQuestionLikesEntity> findByQuestionId(Long questionId);
	
}
