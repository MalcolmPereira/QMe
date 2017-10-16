/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizSpringDataRepository.java
 * Date      : 5/8/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuizEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserQuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Malcolm
 */
@Repository
public interface UserQuizSpringDataRepository extends JpaRepository<UserQuizEntity, Long> {
	/**
     * Find By User ID
     * @param userId User ID
     * @return UserQuizEntity List
     */
    List<UserQuizEntity> findByUserId(Long userId);

    /**
     * Find Completed By User Id
     * @param userId User ID
     * @return UserQuizEntity List
     */
    @Query(value = "SELECT USER_QUIZ_ID,USER_ID,QUIZ_ID,CAT_ID,QUIZ_START_DATE,QUIZ_END_DATE,QUIZ_USER_SCORE,QUIZ_MAX_SCORE,QUIZ_TOKEN FROM USER_QUIZ WHERE USER_ID  = ?1 AND QUIZ_END_DATE IS NOT NULL", nativeQuery = true)
    List<UserQuizEntity> findCompletedByUserId(Long userId);

    /**
     * Find Pending By User Id
     * @param userId User ID
     * @return UserQuizEntity List
     */
    @Query(value = "SELECT USER_QUIZ_ID,USER_ID,QUIZ_ID,CAT_ID,QUIZ_START_DATE,QUIZ_END_DATE,QUIZ_USER_SCORE,QUIZ_MAX_SCORE,QUIZ_TOKEN FROM USER_QUIZ WHERE USER_ID  = ?1 AND QUIZ_END_DATE IS NULL", nativeQuery = true)
    List<UserQuizEntity> findPendingByUserId(Long userId);

    /**
     * Find By Quiz ID
     * @param quizId Quiz ID
     * @return UserQuizEntity List
     */
    List<UserQuizEntity> findByQuizId(Long quizId);
}
