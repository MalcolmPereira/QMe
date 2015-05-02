/**
 * Name      : com.malcolm.qme.core.repository.RoleRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Role Repository Class
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.Role;

/**
 * @Author: Malcolm
 */
public interface RoleRepository extends QMeRepository<Role> {

    /**
     * Find User By Role Name
     * @param roleName
     * @return
     */
    public Role findByRoleName(String roleName);
}
