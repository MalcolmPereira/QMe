/**
 * Name      : com.malcolm.qme.rest.service.impl.UserServiceImpl.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : QMeUser Service Implementation
 */

package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.model.QMeUser;
import com.malcolm.qme.rest.model.QMeUserDetail;
import com.malcolm.qme.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Service
public final class UserServiceImpl implements UserService {

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passcodeEncoder;

    @Override
    public QMeUserDetail searchByUser(String userName) throws QMeResourceException {
        User user = userRepo.findByUserName(userName);
        if(user == null){
            throw new QMeResourceNotFoundException("User with User  Name "+userName+" not found");
        }
        return getQMeUserDetail(user);
    }

    @Override
    public QMeUserDetail searchByEmail(String userEmail) throws QMeResourceException {
        User user = userRepo.findByUserEmail(userEmail);
        if(user == null){
            throw new QMeResourceNotFoundException("User with User Email "+userEmail+" not found");
        }
        return getQMeUserDetail(user);
    }

    @Override
    public List<QMeUserDetail> list() throws QMeResourceException {
        return  getQMeUserDetail(userRepo.findAll());
    }

    @Override
    public QMeUserDetail searchById(Long id) throws QMeResourceException {
        User user = userRepo.findById(id);
        if(user == null){
            throw new QMeResourceNotFoundException("User with User Id "+id+" not found");
        }
        return getQMeUserDetail(user);
    }

    @Override
    public QMeUserDetail save(QMeUser qMeUser, Long userId) throws QMeResourceException {
        User user = getUser(qMeUser);
        user = userRepo.save(user);
        return getQMeUserDetail(user);
    }

    @Override
    public QMeUserDetail update(QMeUser qMeUser, Long id, Long userId) throws QMeResourceException {
        User user = getUser(qMeUser,id,userId);
        user = userRepo.update(user,userId);
        return getQMeUserDetail(user);
    }

    @Override
    public void delete(Long id) throws QMeResourceException {
        User user = userRepo.findById(id);
        if(user == null){
            throw new QMeResourceNotFoundException("User with User Id "+id+" not found");
        }
        userRepo.delete(id);
    }

    /**
     * Get Use for Create
     *
     * @param qMeuser QMe User
     * @return User
     * @throws QMeResourceException
     */
    private User getUser(QMeUser qMeuser) throws QMeResourceException {
        if(qMeuser.getUserName() == null || qMeuser.getUserName().trim().length() == 0){
            throw new QMeInvalidResourceDataException("Valid User Name is required");
        }
        if(qMeuser.getUserPassword() == null || qMeuser.getUserPassword().trim().length() == 0){
            throw new QMeInvalidResourceDataException("Valid User Password is required");
        }
        if(qMeuser.getUserEmail() == null || qMeuser.getUserEmail().trim().length() == 0){
            throw new QMeInvalidResourceDataException("Valid User Email is required");
        }
        if(qMeuser.getUserFirstName() == null || qMeuser.getUserFirstName().trim().length() == 0){
            throw new QMeInvalidResourceDataException("Valid User First Name is required");
        }
        User user = userRepo.findByUserName(qMeuser.getUserName());
        if(user != null){
            throw new QMeResourceConflictException("User with username already exists, please use valid user name");
        }
        user = userRepo.findByUserEmail(qMeuser.getUserEmail());
        if(user != null){
            throw new QMeResourceConflictException("User with email address already exists, please use valid unique user email address");
        }
        return new User(
                qMeuser.getUserName(),
                passcodeEncoder.encode(qMeuser.getUserPassword()),
                qMeuser.getUserFirstName(),
                qMeuser.getUserLastName(),
                qMeuser.getUserEmail()
        );
    }

    /**
     * Get Use for Update
     *
     * @param qMeuser QMe User
     * @return User
     * @throws QMeResourceException
     */
    private User getUser(QMeUser qMeuser, Long userId, Long updateUserId) throws QMeResourceException {
        User currentUser = userRepo.findById(userId);
        if(currentUser == null){
            throw new QMeResourceNotFoundException("User with User  ID "+userId+" not found");
        }
        String firstName = currentUser.getUserFirstName();
        if(qMeuser.getUserFirstName() != null && qMeuser.getUserFirstName().trim().length() > 0){
            firstName = qMeuser.getUserFirstName();
        }
        String lastName = currentUser.getUserLastName();
        if(qMeuser.getUserLastName() != null){
            lastName = qMeuser.getUserLastName();
        }
        return new User(
                currentUser.getUserID(),
                currentUser.getUserName(),
                currentUser.getUserPassword(),
                firstName,
                lastName,
                currentUser.getUserEmail(),
                currentUser.getUserRegisteredDate(),
                LocalDateTime.now(),
                updateUserId
        );

    }

    /**
     * Map User Domain Object to REST Model
     *
     * @param userList List of User
     * @return QMeUser Detail List
     */
    private List<QMeUserDetail> getQMeUserDetail(List<User> userList) {
        List<QMeUserDetail> qMeUsers = new ArrayList<>();
        if (userList == null) {
            return qMeUsers;
        }
        qMeUsers.addAll(
                userList.stream().map
                        (this::getQMeUserDetail).collect(Collectors.toList())
        );
        return qMeUsers;
    }

    /**
     * Map User Domain Object to REST Model
     *
     * @param user User
     * @return QMeUserDetail QMeUser Detail
     */
    private QMeUserDetail getQMeUserDetail(User user) {
        QMeUserDetail qmeUserDetail = new QMeUserDetail();
        qmeUserDetail.setUserID(user.getUserID());
        qmeUserDetail.setUserName(user.getUserName());
        qmeUserDetail.setUserFirstName(user.getUserFirstName());
        qmeUserDetail.setUserLastName(user.getUserLastName());
        qmeUserDetail.setUserEmail(user.getUserEmail());
        qmeUserDetail.setUserRegisteredDate(user.getUserRegisteredDate());
        qmeUserDetail.setUserUpdateDate(user.getUserUpdateDate());
        qmeUserDetail.setUpdateUserID(user.getUpdateUserID());
        //Fixme: Need to add updated  user name
        qmeUserDetail.setUpdateUserName("");
        return qmeUserDetail;
    }
}