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

    @RequestMapping(value=ROOT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody List<QMeUserDetail> list(
        @RequestParam(value=PAGE_PARAM_STRING, defaultValue="") String page,
        @RequestParam(value=PAGE_SIZE_PARAM_STRING, defaultValue="") String pageSize,
        @RequestParam(value=SORT_PARAM_STRING, defaultValue="true") String sortType,
        @RequestParam(value=SORT_FIELDS, defaultValue="") String sortFields) throws QMeResourceException {

        log(getCurrentUser(), "list");

        //Check if Pagination is required
        Integer     pageNumber      = null;
        Integer     pageSizeNumber  = null;
        String[]    sortOrderFields = null;
        boolean     sortAsc         = true;
        if(page != null && page.trim().length() > 0 && pageSize != null && page.trim().length() > 0){
            try{
                pageNumber = Integer.valueOf(page);
            }catch(NumberFormatException numErr){
                pageNumber      = null;
            }
            try{
                pageSizeNumber = Integer.valueOf(pageSize);
            }catch(NumberFormatException numErr){
                pageSizeNumber      = null;
            }
            if(sortType != null && sortType.trim().length() > 0){
                sortAsc = Boolean.valueOf(sortType);
            }
            if(sortFields != null && sortFields.trim().length() > 0){
                sortOrderFields = sortFields.split(SORT_FIELDS_SEPARATOR);
            }
        }
        if(pageNumber != null && pageSizeNumber != null){
            return userService.list();
        }else{
            return userService.list(pageNumber, pageSizeNumber,sortAsc,sortOrderFields);
        }
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
       userService.stageUser(user);
    }

    @RequestMapping(value=REGISTER_CONFIRM_PATH,method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void confirmRegistration(@RequestBody String registrationToken) throws QMeResourceException{
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
        userService.forgotPassword(userEmail,url);
    }

    @RequestMapping(value=RESET_PASSWORD_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail resetPassword(@PathVariable(EMAIL_PARAM_STRING) String userEmail, @RequestBody QMeResetPassword userpassword) throws QMeInvalidResourceDataException,QMeResourceNotFoundException,QMeServerException {
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
