/**
 * Name      : com.malcolm.qme.springdata.repository.UserRepositoryImpl.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : QMe UserEntity Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.springdata.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Malcolm on 5/4/2015.
 */
@Repository("UserRepository")
public class UserRepositoryImpl implements UserRepository {
    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    private UserSpringDataRepository userSpringDataRepo;

    @Override
    public User findByUserName(String userName) {
        UserEntity userEntity = userSpringDataRepo.findByUserNameIgnoreCase(userName);
        if(userEntity != null){
            return getUser(userEntity);
        }
        return null;
    }

    @Override
    public User findByUserEmail(String userEmail) {
        UserEntity userEntity = userSpringDataRepo.findByUserEmailIgnoreCase(userEmail);
        if(userEntity != null){
            return getUser(userEntity);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return(getUsers(userSpringDataRepo.findAll()));
    }

    @Override
    public User findById(Long id) {
        UserEntity userEntity = userSpringDataRepo.findOne(id);
        if(userEntity != null){
            return getUser(userEntity);
        }
        return null;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = getUserEntity(user);
        userEntity.setUserRegisteredDate(new Date());
        userEntity.setUserUpdatedDate(new Date());
        userEntity = userSpringDataRepo.save(userEntity);
        userEntity.setUpdateUser(userEntity.getUserId());
        userEntity = userSpringDataRepo.save(userEntity);
        return getUser(userEntity);
    }

    @Override
    public User update(User user, Long updateUserId) {
        UserEntity userEntity = getUserEntity(user);
        userEntity.setUserUpdatedDate(new Date());
        userEntity.setUpdateUser(updateUserId);
        userEntity = userSpringDataRepo.save(userEntity);
        return getUser(userEntity);
    }

    @Override
    public void delete(Long id) {
        userSpringDataRepo.delete(id);
    }

    /**
     * Map User Domain Object to UserEntity o
     * @param user
     * @return
     */
    private UserEntity getUserEntity(User user){
        UserEntity userEntity = new UserEntity();
        if(user.getUserID() > 0){
            userEntity.setUserId(user.getUserID());
        }
        if(user.getUserName() != null && user.getUserName().trim().length() != 0){
            userEntity.setUserName(user.getUserName());
        }
        userEntity.setUserPasscode(user.getUserPassword());
        userEntity.setUserFirstName(user.getUserFirstName());
        userEntity.setUserLastName(user.getUserLastName());
        userEntity.setUserEmail(user.getUserEmail());
        if(user.getUserRegisteredDate() != null){
            userEntity.setUserRegisteredDate(user.getUserRegisteredDate());
        }
        if(user.getUserUpdateDate() != null){
            userEntity.setUserUpdatedDate(user.getUserUpdateDate());
        }
        if(user.getUpdateUserID() > 0){
            userEntity.setUpdateUser(user.getUpdateUserID());
        }
        return userEntity;
    }

   /**
     * Map UserEntity to User Domain Object
     * @param userEntities
     * @return
     */
   private List<User> getUsers(List<UserEntity> userEntities){
        List<User> userList = new ArrayList<User>();
        if(userEntities == null){
            return userList;
        }
        for (UserEntity userEntity : userEntities){
            userList.add(getUser(userEntity));
        }
        return userList;
   }

   /**
     * Map UserEntity to User Domain Object
     * @param userEntity
     * @return
   */
   private User getUser(UserEntity userEntity){
        return new User(
             userEntity.getUserId(),
             userEntity.getUserName(),
             userEntity.getUserPasscode(),
             userEntity.getUserFirstName(),
             userEntity.getUserLastName(),
             userEntity.getUserEmail(),
             userEntity.getUserRegisteredDate(),
             userEntity.getUserUpdatedDate(),
             userEntity.getUpdateUser()
        );
   }
}
