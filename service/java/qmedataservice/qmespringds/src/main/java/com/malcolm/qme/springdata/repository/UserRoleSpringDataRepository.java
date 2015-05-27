/**
 * Name      : com.malcolm.qme.springdata.repository.UserRoleSpringDataRepository.java
 * Date      : 5/8/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserRolesEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Malcolm
 */
public interface UserRoleSpringDataRepository extends JpaRepository<UserRolesEntity, Long> {

    /**
     * Find By User ID
     * @param userId User ID
     * @return UserRolesEntity List
     */
    List<UserRolesEntity> findByUserId(Long userId);

    /**
     * Find By Role ID
     * @param roleId Role ID
     * @return UserRolesEntity List
     */
    List<UserRolesEntity> findByRoleId(Integer roleId);
}
