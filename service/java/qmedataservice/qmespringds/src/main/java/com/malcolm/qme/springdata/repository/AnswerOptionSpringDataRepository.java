/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerOptionSpringDataRepository.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : SpringData JPAAnswerOptionEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.AnswerOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Malcolm
 */
public interface AnswerOptionSpringDataRepository extends JpaRepository<AnswerOptionEntity, Long> {
}
