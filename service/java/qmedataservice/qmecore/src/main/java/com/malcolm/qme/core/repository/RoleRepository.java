/**
 * Name      : com.malcolm.qme.core.repository.RoleRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Role Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.Role;

import java.util.List;

/**
 * @Author: Malcolm
 */
public interface RoleRepository {

    /**
     * Find all
     * @return
     */
    public List<Role> findAll();

    /**
     * Find By Id
     * @param id
     * @return
     */
    public Role findById(Integer id);

    /**
     * Save
     *
     * @param role
     * @return
     */
    public Role save(Role role);

    /**
     * Update
     *
     * @param role
     * @param updateUserId
     * @return
     */
    public Role update(Role role, Long updateUserId);

    /**
     * Delete
     *
     * @param id
     */
    public void delete(Integer id);

    /**
     * Find User By Role Name
     * @param roleName
     * @return
     */
    public Role findByRoleName(String roleName);
}
