/**
 * Name      : com.malcolm.qme.rest.controller.UserController.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : REST Controller for QMeUser
 */

package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.AtomicTokenGenerator;
import com.malcolm.qme.rest.api.UserAPI;
import com.malcolm.qme.rest.exception.*;
import com.malcolm.qme.rest.model.*;
import com.malcolm.qme.rest.service.UserService;
import com.malcolm.qme.security.service.QMeUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Malcolm
 */
@RestController
public class UserController implements UserAPI {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    /**
     * Category Service
     */
    @Autowired
    private UserService userService;

    /**
     * AtomicTokenGenerator
     */
    @Autowired
    private AtomicTokenGenerator atomicTokenGenerator;

    @RequestMapping(value=ROOT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody List<QMeUserDetail> list() throws QMeServerException {
        log(getCurrentUser(), "list");
        return userService.list();
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail searchById(@PathVariable(ID_PARAM_STRING) Long userId) throws QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), "Search By ID for  "+userId);
        return userService.searchById(userId);
    }

    @RequestMapping(value=NAME_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail searchByUserName(@PathVariable(NAME_PARAM_STRING) String userName) throws QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), "Search By User Name for  "+userName);
        return userService.searchByUser(userName);
    }

    @RequestMapping(value=EMAIL_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail searchByUserEmail(@PathVariable(EMAIL_PARAM_STRING) String userEmail) throws QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), "Search By User Email for  " + userEmail);
        return userService.searchByEmail(userEmail);
    }

    @RequestMapping(value=REGISTER_PATH,method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail create(@RequestBody QMeUser user) throws QMeResourceNotFoundException,QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        log(getCurrentUser(), " Create  ");
        return userService.save(user, getCurrentUser().getUserID());
    }

    @RequestMapping(value=STAGING_PATH,method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void stageUser(@RequestBody QMeStageUser user) throws QMeResourceException{
       log(getCurrentUser(), " stageUser  ");
       userService.stageUser(user);
    }

    @RequestMapping(value=REGISTER_CONFIRM_PATH,method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void confirmRegistration(@RequestBody String registrationToken) throws QMeResourceException{
        log(getCurrentUser(), " confirmRegistration  ");
        userService.confirmUserRegistration(registrationToken);
    }


    @RequestMapping(value=ID_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail update(@PathVariable(ID_PARAM_STRING) Long userId, @RequestBody QMeUpdateUser user) throws QMeResourceNotFoundException,QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        log(getCurrentUser(), " update  ");
        return userService.update(user, userId, getCurrentUser().getUserID());
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void delete(@PathVariable(ID_PARAM_STRING) Long userId) throws QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), " delete  ");
        userService.delete(userId);
    }

    @RequestMapping(value=FORGOT_USERNAME_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody String forgotUserName(@PathVariable(EMAIL_PARAM_STRING) String userEmail) throws QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), " forgotUserName  ");
        QMeUserDetail qMeUserDetail  = userService.searchByEmail(userEmail);
        return qMeUserDetail.getUserName();
    }

    @RequestMapping(value=FORGOT_PASSWORD_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void forgotPassword(@PathVariable(EMAIL_PARAM_STRING) String userEmail, @RequestBody String url) throws QMeInvalidResourceDataException,QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), " forgotPassword  ");
        userService.forgotPassword(userEmail,url);
    }

    @RequestMapping(value=RESET_PASSWORD_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail resetPassword(@PathVariable(EMAIL_PARAM_STRING) String userEmail, @RequestBody QMeResetPassword userpassword) throws QMeInvalidResourceDataException,QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), " resetPassword  ");
        return userService.resetPassword(userEmail, userpassword);
    }

    /**
     * Log Rest Call
     * @param user Current Logged in User
     * @param methodName Method Name
     */
    private final void log(QMeUserDetails user, String methodName){
        if(user != null){
            LOG.debug("User "+methodName+" called by User ID: "+user.getUserID()+" User Name: "+user.getUsername());
        }else{
            LOG.debug("User "+methodName+" called with no security context ");
        }


    }
}
