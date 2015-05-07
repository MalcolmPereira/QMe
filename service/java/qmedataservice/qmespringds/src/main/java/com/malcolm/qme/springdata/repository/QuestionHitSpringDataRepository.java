/**
 * Name      : com.malcolm.qme.springdata.repository.QuestionHitSpringDataRepository.java
 * Date      : 5/5/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA QuestionHitEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.QuestionHitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Malcolm
 */
interface QuestionHitSpringDataRepository extends JpaRepository<QuestionHitEntity, Long> {
}
