/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizLikesSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuizLikesEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserQuizLikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Malcolm
 */
public interface UserQuizLikesSpringDataRepository extends JpaRepository<UserQuizLikesEntity, Long> {
}
