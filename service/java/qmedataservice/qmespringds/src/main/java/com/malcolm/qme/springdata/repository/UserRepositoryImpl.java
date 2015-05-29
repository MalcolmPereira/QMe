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
import com.malcolm.qme.springdata.entity.UserPasswordResetEntity;
import com.malcolm.qme.springdata.entity.UserPasswordResetEntityId;
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

	/**
	 * Spring Data UserPassword Reset Entity Repository
	 */
	@Autowired
	private UserPasswordResetSpringDataRepository userPasswordResetDataRepo;

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

	@Override
	public void addResetToken(Long resetToken, Long userId) throws QMeException{
		try{
			UserPasswordResetEntityId id = new UserPasswordResetEntityId();
			id.setUserId(userId);
			id.setResetToken(resetToken);
			UserPasswordResetEntity userPasswordResetEntity = new UserPasswordResetEntity();
			userPasswordResetEntity.setId(id);
			userPasswordResetEntity.setCreatedTimestamp(LocalDateTime.now());
			userPasswordResetDataRepo.save(userPasswordResetEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public LocalDateTime getResetTokenCreateTime(Long resetToken, Long userId) throws QMeException{
		try{
			UserPasswordResetEntityId id = new UserPasswordResetEntityId();
			id.setUserId(userId);
			id.setResetToken(resetToken);
			UserPasswordResetEntity userPasswordResetEntity = userPasswordResetDataRepo.findOne(id);
			if(userPasswordResetEntity != null){
				return userPasswordResetEntity.getCreatedTimestamp();
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public void deleteResetToken(Long resetToken, Long userId) throws QMeException{
		try{
			UserPasswordResetEntityId id = new UserPasswordResetEntityId();
			id.setUserId(userId);
			id.setResetToken(resetToken);
			userPasswordResetDataRepo.delete(id);
		}catch(Exception err){
			throw new QMeException(err);
		}

	}

	@Override
	public User resetUserPassword(Long resetToken, Long userId, String userPassword) throws QMeException {

		UserPasswordResetEntityId id = new UserPasswordResetEntityId();
		id.setUserId(userId);
		id.setResetToken(resetToken);
		UserPasswordResetEntity userPasswordResetEntity = userPasswordResetDataRepo.findOne(id);

		if(userPasswordResetEntity != null){
			Long resetTokenUserID = userPasswordResetEntity.getId().getUserId();
			if(!resetTokenUserID.equals(userId)){
				throw new QMeException("Invalid Reset token, User Does Not Match Found", new Exception("Invalid Reset token, User Does Not Match Found"));
			}

			UserEntity userEntity = userSpringDataRepo.findOne(userId);

			if (userEntity != null) {
				User existingUser = getUser(userEntity);

				User updateUser = new User(
						existingUser.getUserID(),
						existingUser.getUserName(),
						userPassword,
						existingUser.getUserFirstName(),
						existingUser.getUserLastName(),
						existingUser.getUserEmail(),
						existingUser.getUserRegisteredDate(),
						LocalDateTime.now(),
						existingUser.getUserID()
				);
				UserEntity updateUserEntity = getUserEntity(updateUser);
				updateUserEntity.setUserUpdatedDate(LocalDateTime.now());
				updateUserEntity.setUpdateUser(existingUser.getUserID());
				updateUserEntity = userSpringDataRepo.save(updateUserEntity);

				userPasswordResetDataRepo.delete(id);

				return getUser(updateUserEntity);

			}
			throw new QMeException("Invalid Reset token, User Not Found", new Exception("Invalid Reset token, User Not Found"));

		}else{
			throw new QMeException("Invalid Reset token", new Exception("Reset Token Invalid"));
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
