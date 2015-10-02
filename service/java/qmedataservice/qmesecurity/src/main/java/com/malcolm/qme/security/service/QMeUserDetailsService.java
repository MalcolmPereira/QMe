/**
 * Name      : com.malcolm.qme.security.service.QMeUserDetailsService.java
 * Date      : 5/25/15
 * Developer : Malcolm
 * Purpose   : QMe User Details Service
 */

package com.malcolm.qme.security.service;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserRole;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.core.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Malcolm
 */
@Service
public class QMeUserDetailsService implements UserDetailsService {
    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(QMETokenAuthenticationServiceJWTImpl.class);

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier(value = "UserRepository")
    private UserRepository userRepo;

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier(value = "UserRoleRepository")
    private UserRoleRepository userRoleRepository;

    /**
     * Email Char
     */
    private static final String EMAIL_CHAR = "@";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            if(username == null || username.trim().length() == 0){
                throw new UsernameNotFoundException("User " + username + " not found ");
            }

            User user;
            if(username.contains(EMAIL_CHAR)){
                user = userRepo.findByUserEmail(username);

            }else{
                user = userRepo.findByUserName(username);
            }
            if(user == null){
                throw new UsernameNotFoundException("User " + username + " not found ");
            }
            List<UserRole> userRoles   =  userRoleRepository.findByUserId(user.getUserID());
            if(userRoles == null){
                throw new UsernameNotFoundException("User " + username + " has no associated roles");
            }
            String[]       userRoleArr = new String[userRoles.size()];
            int roleCounter = 0;
            for(UserRole userRole : userRoles){
                userRoleArr[roleCounter] = userRole.getRoleName();
                roleCounter++;
            }
            if(userRoleArr.length == 0  ){
                throw new UsernameNotFoundException("User " + username + " has no associated roles");
            }

            QMeUserDetails userDetails = (QMeUserDetails) QMeUserDetails.create(user.getUserID(), user.getUserName(), user.getUserPassword(), userRoleArr);
            userDetails.setUserFirstName(user.getUserFirstName());
            userDetails.setUserLastName(user.getUserLastName());
            userDetails.setUserEmail(user.getUserEmail());
            userDetails.setUserRegisteredDate(user.getUserRegisteredDate());
            userDetails.setUserUpdateDate(user.getUserUpdateDate());
            userDetails.setUserLastLoginDate(user.getUserLastLoginDate());
            userDetails.setUpdateUserID(user.getUpdateUserID());
            return userDetails;

        }catch(QMeException err){
            throw new UsernameNotFoundException("Error Validating User " + username + " please retry request ",err);
        }
    }

    /**
     * Update User Last Login Date
     *
     * @param userDetails
     */
    public void updateUserLastLoginDate(QMeUserDetails userDetails)  {
        try{
            User user = userRepo.updateLoginDate(userDetails.getUserID());
            userDetails.setUserLastLoginDate(user.getUserLastLoginDate());
        }catch(QMeException err){
            LOG.error("Error Updating user last login date. User Last Login Date not updated.", err);
        }
    }
}
