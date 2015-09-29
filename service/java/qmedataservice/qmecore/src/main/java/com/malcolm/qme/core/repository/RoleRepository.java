/**
 * Name      : com.malcolm.qme.core.repository.RoleRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Role Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.Role;

/**
 * @author Malcolm
 */
public interface RoleRepository extends QMeRepository<Role,Integer> {
    /**
     * Find User By Role Name
     * @param roleName Role Name
     * @return Role List by role name
     * @throws QMeException
     */
    Role findByRoleName(String roleName) throws QMeException;
}
