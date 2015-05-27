/**
 * Name      : com.malcolm.qme.springdata.repository.RoleSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA RoleEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Malcolm
 */
interface RoleSpringDataRepository extends JpaRepository<RoleEntity, Integer> {
	
	/**
	 * Find by Role Name
	 * 
	 * @param roleName Role Name
	 * @return RoleEntity
	 */
	RoleEntity findByRoleNameIgnoreCase(String roleName);
}
