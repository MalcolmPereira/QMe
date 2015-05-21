/**
 * Name      : com.malcolm.qme.core.repository.UserRoleRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Roles Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.UserRole;

import java.util.List;

/**
 * @author Malcolm
 */
public interface UserRoleRepository extends QMeRepository<UserRole, Long> {

    /**
     * Find By User ID
     *
     * @param userID User ID
     * @return UserRole for User ID
     */
    List<UserRole> findByUserId(Long userID);

    /**
     * Find By Role ID
     *
     * @param roleID Role ID
     * @return UserRole for Role ID
     */
    List<UserRole> findByRoleId(Integer roleID);
}
