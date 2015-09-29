/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerOptionSpringDataRepository.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : SpringData JPA AnswerOptionEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.AnswerOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Malcolm
 */
interface AnswerOptionSpringDataRepository extends JpaRepository<AnswerOptionEntity, Long> {

    /**
     * Find By Question Id
     * @param questionId Question Id
     * @return AnswerOptionEntity List
     */
    List<AnswerOptionEntity> findByQuestionId(Long questionId);

}
