/**
 * Name      : com.malcolm.qme.springdata.repository.QuestionSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA QuestionEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malcolm.qme.springdata.entity.QuestionEntity;

/**
 * @author Malcolm
 */
interface QuestionSpringDataRepository extends JpaRepository<QuestionEntity, Long> {
	
	 /**
     * Find By Category ID
     * @param catId Category ID
     * @return QuestionEntity List
     */
    List<QuestionEntity> findByCatId(Long catId);
    
    /**
     * Find Most Liked
     * @return QuestionEntity List
     */
    List<QuestionEntity> findTop50ByOrderByQuestionLikesDesc();
	
}	
