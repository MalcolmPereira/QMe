/**
 * Name      : com.malcolm.qme.springdata.repository.UserRepositoryImpl.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : QMe UserEntity Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserRole;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.springdata.entity.UserEntity;
import com.malcolm.qme.springdata.entity.UserPasswordResetEntity;
import com.malcolm.qme.springdata.entity.UserPasswordResetEntityId;
import com.malcolm.qme.springdata.entity.UserStagingEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Repository(value = "UserRepository")
public class UserRepositoryImpl implements UserRepository {
    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);

    /**
	 * Spring Data UserEntity Repository
	 */
	@Autowired
	private UserSpringDataRepository userSpringDataRepo;

    /**
     * Spring Data UserStagingEntity Repository
     */
    @Autowired
    private UserStagingSpringDataRepository userStagingSpringDataRepository;

	/**
	 * Spring Data UserPassword Reset Entity Repository
	 */
	@Autowired
	private UserPasswordResetSpringDataRepository userPasswordResetDataRepo;

    /**
     * Registration Token Key Length
     */
    private static final int TOKEN_KEY_LENGTH = 32;

    /**
     * Maximum Days to Stage User to Confirm User Registration
     */
    private static final int MAX_STAGING_DAYS = 5;

    @Override
    public Long count() throws QMeException {
        return userSpringDataRepo.count();
    }

	@Override
	public User findByUserName(String userName) throws QMeException {
		try{
			UserEntity userEntity = userSpringDataRepo.findByUserNameIgnoreCase(userName);
			if (userEntity != null) {
				return getUser(userEntity);
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

    @Override
    public User findStagedUserByUserName(String userName) throws QMeException {
        try{
            UserStagingEntity userStagingEntity = userStagingSpringDataRepository.findByUserNameIgnoreCase(userName);
            if (userStagingEntity != null) {
                return getUser(userStagingEntity);
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
    public User findStagedUserByUserEmail(String userEmail) throws QMeException {
        try{
            UserStagingEntity userStagingEntity =userStagingSpringDataRepository.findByUserEmailIgnoreCase(userEmail);
            if (userStagingEntity != null) {
                return getUser(userStagingEntity);
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
    public List<User> findAll(PageSort pageSort) throws QMeException {
		Sort.Direction direction = (pageSort.getSort())? Sort.Direction.ASC:Sort.Direction.DESC;
        PageRequest pageRequest;
        List<String> sortFieldList = new ArrayList<>();
        if(pageSort.getSortFields() != null && pageSort.getSortFields().length > 0) {
            String[] sortFields = pageSort.getSortFields();
            for (String sortField : sortFields) {
                try {
                    sortFieldList.add(USERSORTFIELDS.valueOf(sortField.toUpperCase()).getUserSortField());

                } catch (IllegalArgumentException err) {
                    LOG.debug("Invalid Sort Field "+sortField.toUpperCase()+" Will be ignored");
                }
            }
        }
        if(!sortFieldList.isEmpty()){
            pageRequest  =  new PageRequest(pageSort.getPageIndex(), pageSort.getMaxRows(), direction,sortFieldList.toArray(new String[sortFieldList.size()]));
        }else{
            pageRequest  =  new PageRequest(pageSort.getPageIndex(), pageSort.getMaxRows());
        }
        Page<UserEntity> userList = userSpringDataRepo.findAll(pageRequest);
        return (getUsers(userList.getContent()));
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
    @Transactional
	public User save(User user) throws QMeException {
		try{
			UserEntity userEntity = getUserEntity(user);
			userEntity.setUserRegisteredDate(LocalDateTime.now());
			userEntity.setUserUpdatedDate(LocalDateTime.now());
            userEntity.setUserLastLoginDate(LocalDateTime.now());
            userEntity.setUserLoginDate(LocalDateTime.now());
            userEntity = userSpringDataRepo.save(userEntity);
			userEntity.setUpdateUser(userEntity.getUserId());
			userEntity = userSpringDataRepo.save(userEntity);
			return getUser(userEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

    @Override
    public String stageUserRegistration(User user) throws QMeException {
        try{
            UserStagingEntity userEntity = getUserStagingEntity(user);
			userEntity = userStagingSpringDataRepository.save(userEntity);
            return userEntity.getStagingToken();

        }catch(Exception err){
            throw new QMeException(err);
        }
    }

	@Override
	public void deleteStagingToken(String stagingToken) throws QMeException{
		try{
            UserStagingEntity userStagingEntity = userStagingSpringDataRepository.findByStagingTokenIgnoreCase(stagingToken);
            long stagingUserID = userStagingEntity.getUserId();
            userStagingSpringDataRepository.delete(stagingUserID);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

    @Override
    @Transactional
    public User confirmUserRegistration(String userRegistrationToken) throws QMeException {
        try{
            UserStagingEntity userStagingEntity = userStagingSpringDataRepository.findByStagingTokenIgnoreCase(userRegistrationToken);
            if(userStagingEntity == null){
                throw new QMeException("Invalid user registration staging token");
            }
            long stagingUserID = userStagingEntity.getUserId();
            userStagingEntity.setUserId(null);

            User user = getUser(userStagingEntity);
			UserEntity userEntity = getUserEntity(user);
            userEntity = userSpringDataRepo.save(userEntity);
            userEntity.setUpdateUser(userEntity.getUserId());
            userEntity = userSpringDataRepo.save(userEntity);

            userStagingSpringDataRepository.delete(stagingUserID);
            userStagingSpringDataRepository.deleteByUserStagingDateLessThan(LocalDateTime.now().minusDays(MAX_STAGING_DAYS));

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
    @Transactional
	public void addResetToken(String resetToken, Long userId) throws QMeException{
		try{
			List<UserPasswordResetEntity> resetTokenList = userPasswordResetDataRepo.findByUserId(userId);
			for (UserPasswordResetEntity userPasswordResetEntity : resetTokenList){
				userPasswordResetDataRepo.delete(userPasswordResetEntity.getId());
			}

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
	public LocalDateTime getResetTokenCreateTime(String resetToken, Long userId) throws QMeException{
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
	public void deleteResetToken(String resetToken, Long userId) throws QMeException{
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
    public User updateLoginDate(Long userId) throws QMeException {
        try{
            UserEntity userEntity = userSpringDataRepo.findOne(userId);
            if (userEntity != null) {
                userEntity.setUserLastLoginDate(userEntity.getUserLoginDate());
                userEntity.setUserLoginDate(LocalDateTime.now());
                userEntity = userSpringDataRepo.save(userEntity);
                return getUser(userEntity);
            }
            return null;
        }catch(Exception err){
            throw new QMeException(err);
        }
    }

    @Override
	public User resetUserPassword(String resetToken, Long userId, String userPassword) throws QMeException {
		try {
			UserPasswordResetEntityId id = new UserPasswordResetEntityId();
			id.setUserId(userId);
			id.setResetToken(resetToken);
			UserPasswordResetEntity userPasswordResetEntity = userPasswordResetDataRepo.findOne(id);

			if (userPasswordResetEntity != null) {

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
							LocalDateTime.now(),
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

			} else {
				throw new QMeException("Invalid Reset token", new Exception("Reset Token Invalid"));
			}
		}catch(QMeException err){
			throw err;
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
        if (user.getUserLastLoginDate() != null) {
            userEntity.setUserLastLoginDate(user.getUserLastLoginDate());
        }
        if (user.getUserLoginDate() != null) {
            userEntity.setUserLoginDate(user.getUserLoginDate());
        }
		if (user.getUpdateUserID() > 0) {
			userEntity.setUpdateUser(user.getUpdateUserID());
		}
		return userEntity;
	}

    /**
     * Map User Domain Object to UserStagingEntity
     *
     * @param user User
     * @return UserStagingEntity
     */
    private UserStagingEntity getUserStagingEntity(User user) {
        UserStagingEntity userStagingEntity = new UserStagingEntity();
        if (user.getUserName() != null
                && user.getUserName().trim().length() != 0) {
            userStagingEntity.setUserName(user.getUserName());
        }
        userStagingEntity.setUserPasscode(user.getUserPassword());
        userStagingEntity.setUserFirstName(user.getUserFirstName());
        userStagingEntity.setUserLastName(user.getUserLastName());
        userStagingEntity.setUserEmail(user.getUserEmail());
        userStagingEntity.setUserStagingDate(LocalDateTime.now());
        userStagingEntity.setStagingToken(new String(Hex.encode(KeyGenerators.secureRandom(TOKEN_KEY_LENGTH).generateKey())));
        return userStagingEntity;
    }

	/**
	 * Map UserEntity to User Domain Object
	 * 
	 * @param userEntities UserEntity List
	 * @return User List
	 */
	private List<User> getUsers(List<UserEntity> userEntities) {
		List<User> userList = new ArrayList<>();
		if (userEntities == null) {
			return userList;
		}
		userList.addAll(userEntities.stream().map(this::getUser).collect(Collectors.toList()));
		return userList;
	}

	/**
	 * Map UserEntity to User Domain Object
	 * 
	 * @param userEntity UserEntity
	 * @return User
	 */
	private User getUser(UserEntity userEntity) {
		User user = new User(
                userEntity.getUserId(), userEntity.getUserName(),
				userEntity.getUserPasscode(), userEntity.getUserFirstName(),
				userEntity.getUserLastName(), userEntity.getUserEmail(),
				userEntity.getUserRegisteredDate(),
				userEntity.getUserUpdatedDate(),
                userEntity.getUserLastLoginDate(),
				userEntity.getUserLoginDate(),
                userEntity.getUpdateUser());

        if(userEntity.getUserRoles() != null) {
            user.setUserRoles(
                    userEntity.getUserRoles().stream().map(

                            userRolesEntity -> new UserRole(
                                    userRolesEntity.getUserRoleId(),
                                    userRolesEntity.getRoleId(),
                                    userRolesEntity.getRoleEntity().getRoleName(),
                                    userRolesEntity.getUserId()
                            )

                    ).collect(Collectors.toList())
            );
        }
        return user;
	}

    /**
     * Map UserStagingEntity to User Domain Object
     *
     * @param userStagingEntity UserStagingEntity
     * @return User
     */
    private User getUser(UserStagingEntity userStagingEntity) {
        if (userStagingEntity.getUserId() != null && userStagingEntity.getUserId() > 0) {
            return new User(userStagingEntity.getUserId(),
                    userStagingEntity.getUserName(),
                    userStagingEntity.getUserPasscode(),
                    userStagingEntity.getUserFirstName(),
                    userStagingEntity.getUserLastName(),
                    userStagingEntity.getUserEmail(),
                    userStagingEntity.getUserStagingDate(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    userStagingEntity.getUserId()
            );
        }else {
            return new User(
                    userStagingEntity.getUserName(),
                    userStagingEntity.getUserPasscode(), userStagingEntity.getUserFirstName(),
                    userStagingEntity.getUserLastName(), userStagingEntity.getUserEmail(),
                    userStagingEntity.getUserStagingDate());
        }
    }
}
