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
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepo;

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRoleRepository")
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            User user = userRepo.findByUserName(username);
            if(user == null){
                throw new UsernameNotFoundException("User " + username + " not found ");
            }
            List<UserRole> userRoles   =  userRoleRepository.findByUserId(user.getUserID());
            String[]       userRoleArr = new String[userRoles.size()];
            int roleCounter = 0;
            for(UserRole userRole : userRoles){
                userRoleArr[roleCounter] = userRole.getRoleName();
                roleCounter++;
            }
            if(userRoleArr.length == 0  ){
                throw new UsernameNotFoundException("User " + username + " has associated roles");
            }

            QMeUserDetails userDetails = (QMeUserDetails) QMeUserDetails.create(user.getUserID(), user.getUserName(), user.getUserPassword(), userRoleArr);
            userDetails.setUserFirstName(user.getUserFirstName());
            userDetails.setUserLastName(user.getUserLastName());
            userDetails.setUserEmail(user.getUserEmail());
            userDetails.setUserRegisteredDate(user.getUserRegisteredDate());
            userDetails.setUserUpdateDate(user.getUserUpdateDate());
            userDetails.setUpdateUserID(user.getUpdateUserID());
            return userDetails;

        }catch(QMeException err){
            throw new UsernameNotFoundException("Error Validating User " + username + " please retry request ",err);
        }
    }
}
