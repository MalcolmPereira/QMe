/**
 * Name      : com.malcolm.qme.springdata.repository.UserRoleRepositoryImpl.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : QMe User Roles Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.malcolm.qme.core.domain.UserRole;
import com.malcolm.qme.core.repository.UserRoleRepository;

/**
 * @Author: Malcolm
 */
@Repository("UserRoleRepository")
public class UserRoleRepositoryImpl implements UserRoleRepository {

	@Override
	public List<UserRole> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRole findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRole save(UserRole t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRole update(UserRole t, Long updateUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserRole> findByUserId(Long userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRole> findByRoleId(Integer roleID) {
		// TODO Auto-generated method stub
		return null;
	}

}
