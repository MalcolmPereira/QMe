/**
 * Name      : com.malcolm.qme.rest.controller.UserController.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : REST Controller for QMeUser
 */

package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.AtomicTokenGenerator;
import com.malcolm.qme.rest.api.UserAPI;
import com.malcolm.qme.rest.exception.QMeResourceException;
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
    public @ResponseBody List<QMeUserDetail> list() throws QMeResourceException {
        LOG.debug("User List called ");
        return userService.list();
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail searchById(@PathVariable(ID_PARAM_STRING) Long userId) throws QMeResourceException {
        LOG.debug("User Search By ID for  "+userId);

        return userService.searchById(userId);
    }

    @RequestMapping(value=NAME_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail searchByUserName(@PathVariable(NAME_PARAM_STRING) String userName) throws QMeResourceException {
        LOG.debug("User Search By User Name for  "+userName);
        return userService.searchByUser(userName);
    }

    @RequestMapping(value=EMAIL_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail searchByUserEmail(@PathVariable(EMAIL_PARAM_STRING) String userEmail) throws QMeResourceException {
        LOG.debug("User Search By User Email for  "+userEmail);
        return userService.searchByEmail(userEmail);
    }

    @RequestMapping(value=REGISTER_PATH,method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail create(@RequestBody QMeUser user) throws QMeResourceException {
        LOG.debug("Create User called for  "+user); //TODO: Need to remove this debug
        return userService.save(user,null);
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail update(@PathVariable(ID_PARAM_STRING) Long userId, @RequestBody QMeUser user) throws QMeResourceException {
        LOG.debug("Update User called for  "+user);  //TODO: Need to remove this debug
        //TODO:Add Security and User Id from Principal
        return userService.update(user, userId, 1L);
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void delete(@PathVariable(ID_PARAM_STRING) Long userId) throws QMeResourceException {
        LOG.debug("Delete User called for  "+userId);
        userService.delete(userId);
    }


    @RequestMapping(value=FORGOT_USERNAME_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody String forgotUserName(@PathVariable(EMAIL_PARAM_STRING) String userEmail) throws QMeResourceException {
        QMeUserDetail qMeUserDetail  = userService.searchByEmail(userEmail);
        return qMeUserDetail.getUserName();
    }

    @RequestMapping(value=FORGOT_PASSWORD_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void forgotPassword(@PathVariable(EMAIL_PARAM_STRING) String userEmail, @RequestBody String url) throws QMeResourceException {
        userService.forgotPassword(userEmail,url);
    }

    @RequestMapping(value=RESET_PASSWORD_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail resetPassword(@PathVariable(EMAIL_PARAM_STRING) String userEmail, @RequestBody QMeResetPassword userpassword) throws QMeResourceException {
        return userService.resetPassword(userEmail, userpassword);
    }
}
