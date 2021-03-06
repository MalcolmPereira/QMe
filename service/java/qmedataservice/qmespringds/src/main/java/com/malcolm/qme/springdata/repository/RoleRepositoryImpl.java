/**
 * Name      : com.malcolm.qme.springdata.repository.RoleRepositoryImpl.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : QMe Role Repository Implementation
 */
package com.malcolm.qme.springdata.repository;


import com.malcolm.qme.core.domain.Role;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.RoleRepository;
import com.malcolm.qme.springdata.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Repository(value = "RoleRepository")
public class RoleRepositoryImpl implements RoleRepository {
	
	/**
     * Spring Data QuizRepository Repository
     */
    @Autowired
    private RoleSpringDataRepository roleSpringDataRepository;

	@Override
	public Long count() throws QMeException {
		return roleSpringDataRepository.count();
	}

	@Override
	public List<Role> findAll() throws QMeException {
		try{
			return(getRole(roleSpringDataRepository.findAll()));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<Role> findAll(PageSort pageSort) throws QMeException {
		return null;
	}

	@Override
	public Role findByRoleName(String roleName) throws QMeException {
		try{
			RoleEntity roleEntity = roleSpringDataRepository.findByRoleNameIgnoreCase(roleName);
			if(roleEntity != null){
				return getRole(roleEntity);
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
		
	}
	
	@Override
	public Role findById(Integer id) throws QMeException {
		try{
			RoleEntity roleEntity = roleSpringDataRepository.findOne(id);
			if(roleEntity != null){
				return getRole(roleEntity);
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public Role update(Role role, Long updateUserId) throws QMeException {
		return save(role);
	}

	@Override
	public Role save(Role role) throws QMeException {
		try{
			RoleEntity roleEntity = getRoleEntity(role);
			roleEntity = roleSpringDataRepository.save(roleEntity);
			return getRole(roleEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public void delete(Integer id) throws QMeException {
		try{
			roleSpringDataRepository.delete(id);
		}catch(Exception err){
			throw new QMeException(err);
		}

	}
	
	/**
	 * Map Role Domain Object to RoleEntity
	 * 
	 * @param role Role
	 * @return RoleEntity
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
	 * @param roleEntities RoleEntity List
	 * @return Role List
	 */
	private List<Role> getRole(List<RoleEntity> roleEntities){
		List<Role> roleList = new ArrayList<>();
        if(roleEntities == null){
            return roleList;
        }
		roleList.addAll(roleEntities.stream().map(this::getRole).collect(Collectors.toList()));
        return roleList;
	}
	
	/**
	 * Map RoleEntity to Role Domain Object
	 * 
	 * @param roleEntity RoleEntity
	 * @return Role
	 */
	private Role getRole(RoleEntity roleEntity){
		return new Role(
				roleEntity.getRoleId(),
				roleEntity.getRoleName(),
		roleEntity.getRoleDesc());
	}

}
