/**
 * Name      : com.malcolm.qme.springdata.repository.UserRoleSpringDataRepository.java
 * Date      : 5/8/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserRolesEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malcolm.qme.springdata.entity.UserRolesEntity;

import java.util.List;

/**
 * @Author: Malcolm
 */
public interface UserRoleSpringDataRepository extends JpaRepository<UserRolesEntity, Long> {

    /**
     * Find By User ID
     * @param userId
     * @return
     */
    public List<UserRolesEntity> findByUserId(Long userId);

    /**
     * Find By User ID
     * @param roleId
     * @return
     */
    public List<UserRolesEntity> findByRoleId(Integer roleId);
}
