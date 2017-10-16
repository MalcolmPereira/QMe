/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizSpringDataRepository.java
 * Date      : 5/8/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuizEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserQuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
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
     * Find By Quiz ID
     * @param quizId Quiz ID
     * @return UserQuizEntity List
     */
    List<UserQuizEntity> findByQuizId(Long quizId);
    
}
