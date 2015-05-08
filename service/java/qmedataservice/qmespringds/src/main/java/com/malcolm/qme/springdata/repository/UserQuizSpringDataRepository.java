/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizSpringDataRepository.java
 * Date      : 5/8/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuizEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malcolm.qme.springdata.entity.UserQuizEntity;

/**
 * @Author: Malcolm
 */
public interface UserQuizSpringDataRepository extends JpaRepository<UserQuizEntity, Long> {
}
