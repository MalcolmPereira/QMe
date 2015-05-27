/**
 * Name      : com.malcolm.qme.springdata.repository.UserRoleRepositoryImpl.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : QMe User Roles Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.UserRole;
import com.malcolm.qme.core.repository.UserRoleRepository;
import com.malcolm.qme.springdata.entity.UserRolesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
@Repository("UserRoleRepository")
public class UserRoleRepositoryImpl implements UserRoleRepository {

	/**
	 * Spring Data UserRolesEntity Repository
	 */
	@Autowired
	private UserRoleSpringDataRepository userRoleSpringDataRepository;

	@Override
	public List<UserRole> findAll() {
		return (getUserRole(userRoleSpringDataRepository.findAll()));
	}

	@Override
	public List<UserRole> findByUserId(Long userID) {
		return (getUserRole(userRoleSpringDataRepository.findByUserId(userID)));
	}

	@Override
	public List<UserRole> findByRoleId(Integer roleID) {
		return (getUserRole(userRoleSpringDataRepository.findByRoleId(roleID)));
	}

	@Override
	public UserRole findById(Long id) {
		UserRolesEntity userRolesEntity = userRoleSpringDataRepository.findOne(id);
		if(userRolesEntity != null){
			return getUserRole(userRolesEntity);
		}
		return null;
	}

	@Override
	public UserRole save(UserRole userRole) {
		UserRolesEntity userRolesEntity = getUserRoleEntity(userRole);
		userRoleSpringDataRepository.save(userRolesEntity);
		return getUserRole(userRolesEntity);
	}

	@Override
	public UserRole update(UserRole userRole, Long updateUserId) {
		UserRolesEntity userRolesEntity = getUserRoleEntity(userRole);
		userRoleSpringDataRepository.save(userRolesEntity);
		return getUserRole(userRolesEntity);
	}

	@Override
	public void delete(Long id) {
		userRoleSpringDataRepository.delete(id);
	}

	/**
	 * Map UserRole Domain Object to UserRolesEntity
	 *
	 * @param userRole UserRole
	 * @return UserRolesEntity
	 */
	private UserRolesEntity getUserRoleEntity(UserRole userRole) {
		UserRolesEntity userRolesEntity = new UserRolesEntity();
		if(userRole.getUserRoleID() > 0){
			userRolesEntity.setUserRoleId(userRolesEntity.getUserRoleId());
		}
		userRolesEntity.setRoleId(userRole.getRoleID());
		userRolesEntity.setUserId(userRole.getUserID());
		return userRolesEntity;
	}

	/**
	 * Map UserRolesEntity to UserRole Domain Object
	 *
	 * @param userRolesEntities UserRolesEntity List
	 * @return UserRole List
	 */
	private List<UserRole> getUserRole(List<UserRolesEntity> userRolesEntities) {
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		if (userRolesEntities == null) {
			return userRoleList;
		}
		for (UserRolesEntity userRolesEntity : userRolesEntities) {
			userRoleList.add(getUserRole(userRolesEntity));
		}
		return userRoleList;

	}
	/**
	 * Map UserRolesEntity to UserRole Domain Object
	 *
	 * @param userRolesEntity UserRolesEntity
	 * @return UserRole
	 */
	private UserRole getUserRole(UserRolesEntity userRolesEntity) {
        System.out.println(userRolesEntity.getRoleEntity());
        if(userRolesEntity.getRoleEntity() != null){
            return new UserRole(
                    userRolesEntity.getUserRoleId(),
                    userRolesEntity.getRoleId(),
                    userRolesEntity.getRoleEntity().getRoleName(),
                    userRolesEntity.getUserId()
            );
        }else {

            return new UserRole(
                    userRolesEntity.getUserRoleId(),
                    userRolesEntity.getRoleId(),
                    userRolesEntity.getUserId()
            );
        }
	}

}
