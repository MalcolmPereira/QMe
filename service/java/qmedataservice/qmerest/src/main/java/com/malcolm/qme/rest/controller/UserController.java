/**
 * Name      : com.malcolm.qme.rest.controller.UserController.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : REST Controller for QMeUser
 */

package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.QMeAppAPI;
import com.malcolm.qme.rest.api.UserAPI;
import com.malcolm.qme.rest.exception.*;
import com.malcolm.qme.rest.model.*;
import com.malcolm.qme.rest.service.UserService;
import com.malcolm.qme.security.service.QMeUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
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

    @Autowired
    private String endpointURL;

    /**
     * Category Service
     */
    @Autowired
    private UserService userService;

    @RequestMapping(value=COUNT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public  @ResponseBody Resource<Long> count() throws QMeResourceException {
        log(getCurrentUser(), "count");
        Resource<Long> userCount = new Resource<>(userService.count(),new Link(endpointURL+ UserAPI.COUNT_PATH.replaceAll(":.+","}")));
        userCount.add(new Link(endpointURL + UserAPI.PAGED_PATH.replaceAll(":.+", "}") + "?page=0&pagesize=1&sorttype=true&sortfields=USERNAME", QMeAppAPI.USER_PAGED));
        return userCount;
    }

    @RequestMapping(value=ROOT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public @ResponseBody List<QMeUserDetail> list() throws QMeServerException {
        log(getCurrentUser(), "list");
        List<QMeUserDetail> qMeUserDetailList = userService.list();
        setUserLinks(qMeUserDetailList);
        return qMeUserDetailList;
    }

    @RequestMapping(value=PAGED_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public @ResponseBody List<QMeUserDetail> listPaged(
        @RequestParam(value=PAGE_PARAM_STRING, defaultValue="") String page,
        @RequestParam(value=PAGE_SIZE_PARAM_STRING, defaultValue="") String pageSize,
        @RequestParam(value=SORT_PARAM_STRING, defaultValue="true") String sortType,
        @RequestParam(value=SORT_FIELDS, defaultValue="") String sortFields) throws QMeResourceException {

        log(getCurrentUser(), "listPaged");

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
        List<QMeUserDetail> qMeUserDetailList;
        if(pageNumber != null && pageSizeNumber != null){
            qMeUserDetailList = userService.list(pageNumber, pageSizeNumber,sortAsc,sortOrderFields);
        }else{
            qMeUserDetailList= userService.list();
        }
        setUserLinks(qMeUserDetailList);
        return qMeUserDetailList;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public @ResponseBody QMeUserDetail searchById(@PathVariable(ID_PARAM_STRING) Long userId) throws QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), "Search By ID for  "+userId);
        QMeUserDetail qMeUserDetail = userService.searchById(userId);
        setUserLinks(qMeUserDetail);
        return qMeUserDetail;
    }

    @RequestMapping(value=NAME_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public @ResponseBody QMeUserDetail searchByUserName(@PathVariable(NAME_PARAM_STRING) String userName) throws QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), "Search By User Name for  "+userName);
        QMeUserDetail qMeUserDetail = userService.searchByUser(userName);
        setUserLinks(qMeUserDetail);
        return qMeUserDetail;
    }

    @RequestMapping(value=EMAIL_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public @ResponseBody QMeUserDetail searchByUserEmail(@PathVariable(EMAIL_PARAM_STRING) String userEmail) throws QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), "Search By User Email for  " + userEmail);
        QMeUserDetail qMeUserDetail = userService.searchByEmail(userEmail);
        setUserLinks(qMeUserDetail);
        return qMeUserDetail;
    }

    @RequestMapping(value=REGISTER_PATH,method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public @ResponseBody QMeUserDetail create(@RequestBody QMeUser user) throws QMeResourceNotFoundException,QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        log(getCurrentUser(), " Create  ");
        QMeUserDetail qMeUserDetail =  userService.save(user, getCurrentUser().getUserID());
        setUserLinks(qMeUserDetail);
        return qMeUserDetail;
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
        QMeUserDetails currentUser = getCurrentUser();
        log(currentUser, " update  ");

        boolean updateAllowed = false;
        if(currentUser == null){
            throw new AccessDeniedException("Access denied - no authenticated user in context");
        }

        if(currentUser.getAuthorities() != null){
            for(GrantedAuthority authority : currentUser.getAuthorities()) {
                if (authority.getAuthority().equalsIgnoreCase(ADMIN_ROLE)) {
                    updateAllowed = true;
                    break;
                }
            }
        }
        if(!updateAllowed){
            if(currentUser.getUserID().equals(userId)){
                updateAllowed = true;
            }
        }

        if(!updateAllowed){
            throw new AccessDeniedException("Access denied - user in context does not have required roles");
        }

        QMeUserDetail qMeUserDetail = userService.update(user, userId, getCurrentUser().getUserID());
        setUserLinks(qMeUserDetail);
        return qMeUserDetail;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public void delete(@PathVariable(ID_PARAM_STRING) Long userId) throws QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), " delete  ");
        userService.delete(userId);
    }

    @RequestMapping(value=FORGOT_USERNAME_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody Resource<String> forgotUserName(@PathVariable(EMAIL_PARAM_STRING) String userEmail) throws QMeResourceNotFoundException, QMeServerException {
        log(getCurrentUser(), " forgotUserName  ");
        QMeUserDetail qMeUserDetail  = userService.searchByEmail(userEmail);
        return new Resource<>(qMeUserDetail.getUserName(),new Link( endpointURL+ UserAPI.FORGOT_PASSWORD_PATH.replaceAll("\\{"+EMAIL_PARAM_STRING+":.+\\}",qMeUserDetail .getUserEmail()),QMeAppAPI.FORGOT_USER_PASSWORD));
    }

    @RequestMapping(value=FORGOT_PASSWORD_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void forgotPassword(@PathVariable(EMAIL_PARAM_STRING) String userEmail, @RequestBody String url) throws QMeInvalidResourceDataException,QMeResourceNotFoundException,QMeServerException {
        userService.forgotPassword(userEmail, url);
    }

    @RequestMapping(value=RESET_PASSWORD_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeUserDetail resetPassword(@PathVariable(EMAIL_PARAM_STRING) String userEmail, @RequestBody QMeResetPassword userpassword) throws QMeInvalidResourceDataException,QMeResourceNotFoundException,QMeServerException {
        QMeUserDetail qMeUserDetail =   userService.resetPassword(userEmail, userpassword);
        setUserLinks(qMeUserDetail);
        return qMeUserDetail;
    }

    /**
     * Set User Links
     * @param qmeUserList QMe User List
     */
    private void setUserLinks(List<QMeUserDetail> qmeUserList){
        qmeUserList.stream().forEach(this::setUserLinks);
    }

    /**
     * Set User Links
     * @param qmeUser QMeUSer
     */
    private void setUserLinks(QMeUserDetail qmeUser){
        qmeUser.add(new Link(endpointURL+ UserAPI.ID_PATH.replaceAll("\\{"+ID_PARAM_STRING+"\\}",qmeUser.getUserId()+""),QMeAppAPI.USER_BY_ID));
        qmeUser.add(new Link(endpointURL+ UserAPI.NAME_PATH.replaceAll("\\{"+NAME_PARAM_STRING+":.+\\}",qmeUser.getUserName()),QMeAppAPI.USER_BY_NAME));
        qmeUser.add(new Link(endpointURL+ UserAPI.EMAIL_PATH.replaceAll("\\{"+EMAIL_PARAM_STRING+":.+\\}",qmeUser.getUserEmail()),QMeAppAPI.USER_BY_EMAIL));
        qmeUser.add(new Link(endpointURL+ UserAPI.ROOT_PATH.replaceAll(":.+","}")+"/"+qmeUser.getUserId(),QMeAppAPI.UPDATE_USER));
        qmeUser.add(new Link(endpointURL+ UserAPI.ID_PATH.replaceAll("\\{"+ID_PARAM_STRING+"\\}",qmeUser.getUserId()+""),QMeAppAPI.DELETE_USER));
        qmeUser.add(new Link(endpointURL+ UserAPI.FORGOT_USERNAME_PATH.replaceAll("\\{"+EMAIL_PARAM_STRING+":.+\\}",qmeUser.getUserEmail()),QMeAppAPI.FORGOT_USER_NAME));
        qmeUser.add(new Link( endpointURL+ UserAPI.FORGOT_PASSWORD_PATH.replaceAll("\\{"+EMAIL_PARAM_STRING+":.+\\}",qmeUser.getUserEmail()),QMeAppAPI.FORGOT_USER_PASSWORD));
    }

    /**
     * Log Rest Call
     * @param user Current Logged in User
     * @param methodName Method Name
     */
    private void log(QMeUserDetails user, String methodName){
        if(user != null){
            LOG.debug("User "+methodName+" called by User ID: "+user.getUserID()+" User Name: "+user.getUsername()+" User Roles"+user.getAuthorities());
        }else{
            LOG.debug("User "+methodName+" called with no security context ");
        }
    }
}
