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
        return getUser(userSpringDataRepo.findByUserNameIgnoreCase(userName));
    }

    @Override
    public User findByUserEmail(String userEmail) {
        return getUser(userSpringDataRepo.findByUserEmailIgnoreCase(userEmail));
    }

    @Override
    public List<User> findAll() {
        return(getUsers(userSpringDataRepo.findAll()));
    }

    @Override
    public User findById(long id) {
        return(getUser(userSpringDataRepo.findOne(Long.valueOf(id).intValue())));
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user, long updateUserId) {
        return null;
    }

    @Override
    public void delete(long id) {
        userSpringDataRepo.delete(Long.valueOf(id).intValue());
    }

   /**
     * Map to QMe UserEntity Domain Object
     * @param userEntities
     * @return
     */
   private List<User> getUsers(List<UserEntity> userEntities){
        List<User> userList = new ArrayList<User>();
        for (UserEntity userEntity : userEntities){
            userList.add(getUser(userEntity));
        }
        return userList;
   }

    /**
     * Map to QMe UserEntity Domain Object
     * @param userEntity
     * @return
     */
   private User getUser(UserEntity userEntity){
        return new User(
                    userEntity.getUserId(), userEntity.getUserName(), userEntity.getUserFirstName(), userEntity.getUserLastName(),
                    userEntity.getUserEmail(), userEntity.getUserPasscode(), userEntity.getUserRegisteredDate(),
                    userEntity.getUserUpdatedDate(), userEntity.getUpdateUser()
        );
   }
}
