/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerReferenceMediaSpringDataRepository.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : SpringData JPA AnswerReferenceMediaEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malcolm.qme.springdata.entity.AnswerReferenceMediaEntity;

/**
 * @author Malcolm
 */
interface AnswerReferenceMediaSpringDataRepository extends JpaRepository<AnswerReferenceMediaEntity, Long> {
	
	 /**
     * Find By Question Id
     * @param questionId Question Id
     * @return AnswerReferenceMediaEntity List
     */
    List<AnswerReferenceMediaEntity> findByQuestionId(Long questionId);

	
}
