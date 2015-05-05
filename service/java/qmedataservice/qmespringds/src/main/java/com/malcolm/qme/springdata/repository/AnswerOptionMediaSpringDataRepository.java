/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerOptionMediaSpringDataRepository.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : SpringData JPA AnswerOptionMediaEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.AnswerOptionMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Malcolm
 */
public interface AnswerOptionMediaSpringDataRepository extends JpaRepository<AnswerOptionMediaEntity, Long> {
}
