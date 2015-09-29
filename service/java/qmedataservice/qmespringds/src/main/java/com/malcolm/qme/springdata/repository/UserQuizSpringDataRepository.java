/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizSpringDataRepository.java
 * Date      : 5/8/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuizEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserQuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Malcolm
 */
public interface UserQuizSpringDataRepository extends JpaRepository<UserQuizEntity, Long> {
	/**
     * Find By User ID
     * @param userId User ID
     * @return UserQuizEntity List
     */
    List<UserQuizEntity> findByUserId(Long userId);
    
    /**
     * Find By Quiz ID
     * @param quizId Quiz ID
     * @return UserQuizEntity List
     */
    List<UserQuizEntity> findByQuizId(Long quizId);
    
    /**
     * Find By User ID and Complete Status
     * 
     * @param userId  User ID
     * @param quizComplete Quiz Complete Flag 1 - Complete, 0 - InComplete
     * @return UserQuizEntity List
     */
    List<UserQuizEntity> findByUserIdAndQuizComplete(Long userId, byte quizComplete);
	
}
