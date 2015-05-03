/**
 * Name      : com.malcolm.qme.springdata.repository.UserSpringDataRepository.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA User Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Malcolm
 */
public interface UserSpringDataRepository extends JpaRepository<User, Integer> {
}
