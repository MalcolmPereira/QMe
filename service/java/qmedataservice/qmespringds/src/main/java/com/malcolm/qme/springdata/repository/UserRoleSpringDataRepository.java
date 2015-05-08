/**
 * Name      : com.malcolm.qme.springdata.repository.UserRoleSpringDataRepository.java
 * Date      : 5/8/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserRolesEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malcolm.qme.springdata.entity.UserRolesEntity;

/**
 * @Author: Malcolm
 */
public interface UserRoleSpringDataRepository extends JpaRepository<UserRolesEntity, Long> {
}
