/**
 * Name      : com.malcolm.qme.springdata.repository.QuestionSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA QuestionEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Malcolm
 */
@Repository
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


    /**
     * Find By Category ID
     * @return QuestionEntity List
     */
    Page<QuestionEntity> findByCatId(Long catId, PageRequest pageRequest);
}	
