/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizSpringDataRepository.java
 * Date      : 5/8/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuizEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malcolm.qme.springdata.entity.UserQuizEntity;

/**
 * @Author: Malcolm
 */
public interface UserQuizSpringDataRepository extends JpaRepository<UserQuizEntity, Long> {
	/**
     * Find By User ID
     * @param userId
     * @return
     */
    public List<UserQuizEntity> findByUserId(Long userId);
    
    /**
     * Find By Quiz ID
     * @param quizId
     * @return
     */
    public List<UserQuizEntity> findByQuizId(Long quizId);
    
    /**
     * Find By User ID and Complete Status
     * 
     * @param userId
     * @param quizComplete
     * @return
     */
    public List<UserQuizEntity> findByUserIdAndQuizComplete(Long userId, byte quizComplete);
	
}
