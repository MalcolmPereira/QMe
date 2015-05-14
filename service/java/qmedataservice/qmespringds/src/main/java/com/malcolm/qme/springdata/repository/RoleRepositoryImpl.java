/**
 * Name      : com.malcolm.qme.springdata.repository.RoleRepositoryImpl.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : QMe Role Repository Implementation
 */
package com.malcolm.qme.springdata.repository;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.malcolm.qme.core.domain.Role;
import com.malcolm.qme.core.repository.RoleRepository;
import com.malcolm.qme.springdata.entity.RoleEntity;

/**
 * @Author: Malcolm
 */
@Repository("RoleRepository")
public class RoleRepositoryImpl implements RoleRepository {
	
	/**
     * Spring Data QuizRepository Repository
     */
    @Autowired
    private RoleSpringDataRepository roleSpringDataRepository;
	
	@Override
	public List<Role> findAll() {
		return(getRole(roleSpringDataRepository.findAll()));
	}

	@Override
	public Role findByRoleName(String roleName) {
		RoleEntity roleEntity = roleSpringDataRepository.findByRoleNameIgnoreCase(roleName);
		if(roleEntity != null){
            return getRole(roleEntity);
        }
        return null;
		
	}
	
	@Override
	public Role findById(Integer id) {
		RoleEntity roleEntity = roleSpringDataRepository.findOne(id);
		if(roleEntity != null){
            return getRole(roleEntity);
        }
        return null;
	}

	@Override
	public Role save(Role role) {
		RoleEntity roleEntity = getRoleEntity(role);
		roleEntity = roleSpringDataRepository.save(roleEntity);
		return getRole(roleEntity);
	}

	@Override
	public Role update(Role role, Long updateUserId) {
		RoleEntity roleEntity = getRoleEntity(role);
		roleEntity = roleSpringDataRepository.save(roleEntity);
		return getRole(roleEntity);
	}

	@Override
	public void delete(Integer id) {
		roleSpringDataRepository.delete(id);

	}
	
	/**
	 * Map Role Domain Object to RoleEntity
	 * 
	 * @param role
	 * @return
	 */
	private RoleEntity getRoleEntity(Role role){
		RoleEntity roleEntity = new RoleEntity();
		if(role.getRoleID() > 0){
			roleEntity.setRoleId(role.getRoleID());
		}
		roleEntity.setRoleName(role.getRoleName());
		roleEntity.setRoleDesc(role.getRoleDesc());
		return roleEntity;
	}
	
	/**
	 * Map RoleEntity to Role Domain Object
	 * 
	 * @param roleEntities
	 * @return
	 */
	private List<Role> getRole(List<RoleEntity> roleEntities){
		List<Role> roleList = new ArrayList<Role>();
        if(roleEntities == null){
            return roleList;
        }
        for (RoleEntity roleEntity : roleEntities){
        	roleList.add(getRole(roleEntity));
        }
        return roleList;
	}
	
	/**
	 * Map RoleEntity to Role Domain Object
	 * 
	 * @param roleEntity
	 * @return
	 */
	private Role getRole(RoleEntity roleEntity){
		return new Role(
				roleEntity.getRoleId(),
				roleEntity.getRoleName(),
		roleEntity.getRoleDesc());
	}

	


}
