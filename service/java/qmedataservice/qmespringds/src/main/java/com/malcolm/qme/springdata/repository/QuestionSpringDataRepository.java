/**
 * Name      : com.malcolm.qme.springdata.repository.QuestionHitSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA QuestionEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malcolm.qme.springdata.entity.QuestionEntity;

/**
 * @Author: Malcolm
 */
public interface QuestionSpringDataRepository extends JpaRepository<QuestionEntity, Long> {
	
	 /**
     * Find By Category ID
     * @param userName
     * @return
     */
    public List<QuestionEntity> findByCatId(Long catId);
    
    /**
     * Find Most Liked
     * @param userName
     * @return
     */
    public List<QuestionEntity> findTop50ByOrderByLikes();
	
}	
