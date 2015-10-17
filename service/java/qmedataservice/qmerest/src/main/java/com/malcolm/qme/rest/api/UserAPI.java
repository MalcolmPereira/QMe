/**
 * Name      : com.malcolm.qme.rest.api.UserAPI.java
 * Date      : 5/26/2015
 * Developer : Malcolm
 * Purpose   : QMe User API Interface which services as a Contract for the REST Service
 **/
package com.malcolm.qme.rest.api;

import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Malcolm
 */
public interface UserAPI extends QMeAPI {
    /**
     * QMeUser API Root Path
     */
    String ROOT_PATH = APP_STRING + "/user";

    /**
     * QMeUser API Paged Path
     */
    String PAGED_PATH = APP_STRING + "/user/paged";

    /**
     * QMeUser API Get By ID Path
     */
    String ID_PATH = ROOT_PATH + "/{" + ID_PARAM_STRING + "}";

    /**
     * QMeUser API Search By Name
     */
    String NAME_PATH = ROOT_PATH + "/search/{" + NAME_PARAM_STRING + ":.+}";

    /**
     * QMeUser API Search By Email
     */
    String EMAIL_PATH = ROOT_PATH + "/searchemail/{" + EMAIL_PARAM_STRING + ":.+}";

    /**
     * QMeUser API Register User Path
     */
    String REGISTER_PATH = ROOT_PATH + "/register";

    /**
     * QMeUser API Stage User Path
     */
    String STAGING_PATH = ROOT_PATH + "/stage";

    /**
     * QMeUser API User Registration Confirm User Path
     */
    String REGISTER_CONFIRM_PATH = ROOT_PATH + "/confirm";

    /**
     * QMeUser API Reset Path
     */
    String RESET_PATH = ROOT_PATH + "/reset";

    /**
     * QMeUser API Forgot User Name
     */
    String FORGOT_USERNAME_PATH = RESET_PATH + "/forgotusername/{" + EMAIL_PARAM_STRING + ":.+}";

    /**
     * QMeUser API Forgot Password
     */
    String FORGOT_PASSWORD_PATH = RESET_PATH + "/forgotpassword/{" + EMAIL_PARAM_STRING + ":.+}";

    /**
     * QMeUser API Validate Token Path
     */
    String RESET_PASSWORD_PATH = RESET_PATH + "/resetpassword/{" + EMAIL_PARAM_STRING + ":.+}";

    /**
     * Get All Users
     *
     * @return List of Users
     */
    List<QMeUserDetail> list() throws QMeResourceException;

    /**
     * Get All Users with Pagination and Sorting
     * @param page Page
     * @param pageSize Page Size
     * @param sortType Sort Type
     * @param sortFields Sort Fields
     * @return List of Users
     * @throws QMeResourceException
     */
    List<QMeUserDetail> listPaged(String page,String pageSize, String sortType, String sortFields) throws QMeResourceException;

    /**
     * Search by ID
     *
     * @param userId User ID
     * @return QMe User
     */
    QMeUserDetail searchById(@PathVariable(ID_PARAM_STRING) Long userId) throws QMeResourceException;

    /**
     * Search by User Name
     *
     * @param userName User Name
     * @return QMe User
     */
    QMeUserDetail searchByUserName(@PathVariable(NAME_PARAM_STRING) String userName) throws QMeResourceException;

    /**
     * Search by User Email
     *
     * @param userEmail User Email
     * @return QMe User
     */
    QMeUserDetail searchByUserEmail(@PathVariable(EMAIL_PARAM_STRING) String userEmail) throws QMeResourceException;

    /**
     * Register New User
     *
     * @param user QMe User
     * @return QMe User
     * @throws QMeResourceException
     */
    QMeUserDetail create(@RequestBody QMeUser user) throws QMeResourceException;

    /**
     * Stage User
     *
     * @param user - Staging User
     * @return Boolean - Staging successful
     * @throws QMeResourceException
     */
    void stageUser(@RequestBody QMeStageUser user) throws QMeResourceException;

    /**
     * Confirm User Registration
     *
     * @param registrationToken Registration Token
     * @return Boolean - User Registration Completed
     * @throws QMeResourceException
     */
    void confirmRegistration(@RequestBody String registrationToken) throws QMeResourceException;

    /**
     * Update QMe User
     *
     * @param user QMe User
     * @return QMe User Updated User
     */
    QMeUserDetail update(@PathVariable(ID_PARAM_STRING) Long userId, @RequestBody QMeUpdateUser user) throws QMeResourceException;

    /**
     * Delete QMe User
     *
     * @param userId User Is
     */
    void delete(@PathVariable(ID_PARAM_STRING) Long userId) throws QMeResourceException;


    /**
     * Forgot User Name
     *
     * @param userEmail User Email
     * @return QMeUser User Details
     */
    String forgotUserName(@PathVariable(EMAIL_PARAM_STRING) String userEmail) throws QMeResourceException;

    /**
     * Forgot Password
     *
     * @param userEmail User Email
     * @param url Reset URL that will be sent to user email
     * @throws QMeResourceException
     */
    void forgotPassword(@PathVariable(EMAIL_PARAM_STRING) String userEmail, @RequestBody String url) throws QMeResourceException;

   /**
     * Reset Password
     *
     * @param userEmail User Email
     * @param userpassword User Password reset token
     * @throws QMeResourceException
     */
    QMeUserDetail resetPassword(@PathVariable(EMAIL_PARAM_STRING) String userEmail, @RequestBody QMeResetPassword userpassword) throws QMeResourceException;

}
