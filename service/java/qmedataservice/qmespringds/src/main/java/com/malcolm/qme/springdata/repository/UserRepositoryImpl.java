/**
 * Name      : com.malcolm.qme.springdata.repository.UserRepositoryImpl.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : QMe UserEntity Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.springdata.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
@Repository("UserRepository")
public class UserRepositoryImpl implements UserRepository {
	/**
	 * Spring Data UserEntity Repository
	 */
	@Autowired
	private UserSpringDataRepository userSpringDataRepo;

	@Override
	public User findByUserName(String userName) throws QMeException {
		try{
			UserEntity userEntity = userSpringDataRepo
					.findByUserNameIgnoreCase(userName);
			if (userEntity != null) {
				return getUser(userEntity);
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public User findByUserEmail(String userEmail) throws QMeException {
		try{
			UserEntity userEntity = userSpringDataRepo
					.findByUserEmailIgnoreCase(userEmail);
			if (userEntity != null) {
				return getUser(userEntity);
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<User> findAll() throws QMeException {
		try{
			return (getUsers(userSpringDataRepo.findAll()));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public User findById(Long id) throws QMeException {
		try{
			UserEntity userEntity = userSpringDataRepo.findOne(id);
			if (userEntity != null) {
				return getUser(userEntity);
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public User save(User user) throws QMeException {
		try{
			UserEntity userEntity = getUserEntity(user);
			userEntity.setUserRegisteredDate(LocalDateTime.now());
			userEntity.setUserUpdatedDate(LocalDateTime.now());
			userEntity = userSpringDataRepo.save(userEntity);
			userEntity.setUpdateUser(userEntity.getUserId());
			userEntity = userSpringDataRepo.save(userEntity);
			return getUser(userEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public User update(User user, Long updateUserId) throws QMeException {
		try{
			UserEntity userEntity = getUserEntity(user);
			userEntity.setUserUpdatedDate(LocalDateTime.now());
			userEntity.setUpdateUser(updateUserId);
			userEntity = userSpringDataRepo.save(userEntity);
			return getUser(userEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public void delete(Long id) throws QMeException {
		try{
			userSpringDataRepo.delete(id);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	/**
	 * Map User Domain Object to UserEntity
	 * 
	 * @param user User
	 * @return UserEntity
	 */
	private UserEntity getUserEntity(User user) {
		UserEntity userEntity = new UserEntity();
		if (user.getUserID() > 0) {
			userEntity.setUserId(user.getUserID());
		}
		if (user.getUserName() != null
				&& user.getUserName().trim().length() != 0) {
			userEntity.setUserName(user.getUserName());
		}
		userEntity.setUserPasscode(user.getUserPassword());
		userEntity.setUserFirstName(user.getUserFirstName());
		userEntity.setUserLastName(user.getUserLastName());
		userEntity.setUserEmail(user.getUserEmail());
		if (user.getUserRegisteredDate() != null) {
			userEntity.setUserRegisteredDate(user.getUserRegisteredDate());
		}
		if (user.getUserUpdateDate() != null) {
			userEntity.setUserUpdatedDate(user.getUserUpdateDate());
		}
		if (user.getUpdateUserID() > 0) {
			userEntity.setUpdateUser(user.getUpdateUserID());
		}
		return userEntity;
	}

	/**
	 * Map UserEntity to User Domain Object
	 * 
	 * @param userEntities UserEntity List
	 * @return User List
	 */
	private List<User> getUsers(List<UserEntity> userEntities) {
		List<User> userList = new ArrayList<User>();
		if (userEntities == null) {
			return userList;
		}
		for (UserEntity userEntity : userEntities) {
			userList.add(getUser(userEntity));
		}
		return userList;
	}

	/**
	 * Map UserEntity to User Domain Object
	 * 
	 * @param userEntity UserEntity
	 * @return User
	 */
	private User getUser(UserEntity userEntity) {
		return new User(userEntity.getUserId(), userEntity.getUserName(),
				userEntity.getUserPasscode(), userEntity.getUserFirstName(),
				userEntity.getUserLastName(), userEntity.getUserEmail(),
				userEntity.getUserRegisteredDate(),
				userEntity.getUserUpdatedDate(), userEntity.getUpdateUser());
	}
}
