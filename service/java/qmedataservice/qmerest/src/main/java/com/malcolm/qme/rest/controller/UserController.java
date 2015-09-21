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
import com.malcolm.qme.rest.model.QMeResetPassword;
import com.malcolm.qme.rest.model.QMeUser;
import com.malcolm.qme.rest.model.QMeUserDetail;
import com.malcolm.qme.rest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
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
    private static Logger LOG = LoggerFactory.getLogger(UserController.class);

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
        LOG.debug("User List called ");
        return userService.list();
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail searchById(@PathVariable(ID_PARAM_STRING) Long userId) throws QMeResourceNotFoundException,QMeServerException {
        LOG.debug("User Search By ID for  "+userId);
        return userService.searchById(userId);
    }

    @RequestMapping(value=NAME_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail searchByUserName(@PathVariable(NAME_PARAM_STRING) String userName) throws QMeResourceNotFoundException,QMeServerException {
        LOG.debug("User Search By User Name for  "+userName);
        return userService.searchByUser(userName);
    }

    @RequestMapping(value=EMAIL_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail searchByUserEmail(@PathVariable(EMAIL_PARAM_STRING) String userEmail) throws QMeResourceNotFoundException,QMeServerException {
        LOG.debug("User Search By User Email for  "+userEmail);
        return userService.searchByEmail(userEmail);
    }

    @RequestMapping(value=REGISTER_PATH,method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail create(@RequestBody QMeUser user) throws QMeResourceNotFoundException,QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        return userService.save(user, null);
    }

    @RequestMapping(value=STAGING_PATH,method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody Boolean stageUser(@PathVariable(REG_CONFIRM_URL_PARAM) String appUrl, @RequestBody QMeUser user) throws QMeResourceException{
       return userService.stageUser(user,appUrl);
    }

    @RequestMapping(value=REGISTER_CONFIRM_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody Boolean confirmRegistration(@RequestBody String registrationToken) throws QMeResourceException{
        return Boolean.FALSE;
    }


    @RequestMapping(value=ID_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail update(@PathVariable(ID_PARAM_STRING) Long userId, @RequestBody QMeUser user) throws QMeResourceNotFoundException,QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        //TODO:Add Security and User Id from Principal
        return userService.update(user, userId, 1L);
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody Boolean delete(@PathVariable(ID_PARAM_STRING) Long userId) throws QMeResourceNotFoundException,QMeServerException {
        userService.delete(userId);
        return Boolean.TRUE;
    }

    @RequestMapping(value=FORGOT_USERNAME_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody String forgotUserName(@PathVariable(EMAIL_PARAM_STRING) String userEmail) throws QMeResourceNotFoundException,QMeServerException {
        QMeUserDetail qMeUserDetail  = userService.searchByEmail(userEmail);
        return qMeUserDetail.getUserName();
    }

    @RequestMapping(value=FORGOT_PASSWORD_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody Boolean forgotPassword(@PathVariable(EMAIL_PARAM_STRING) String userEmail, @RequestBody String url) throws QMeInvalidResourceDataException,QMeResourceNotFoundException,QMeServerException {
        userService.forgotPassword(userEmail,url);
        return Boolean.TRUE;
    }

    @RequestMapping(value=RESET_PASSWORD_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail resetPassword(@PathVariable(EMAIL_PARAM_STRING) String userEmail, @RequestBody QMeResetPassword userpassword) throws QMeInvalidResourceDataException,QMeResourceNotFoundException,QMeServerException {
        return userService.resetPassword(userEmail, userpassword);
    }
}
