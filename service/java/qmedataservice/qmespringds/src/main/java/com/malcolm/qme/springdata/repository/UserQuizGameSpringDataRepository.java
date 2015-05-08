/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizGameSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuizGameEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserQuizGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Malcolm
 */
public interface UserQuizGameSpringDataRepository extends JpaRepository<UserQuizGameEntity, Long> {
}
