/**
 * Name      : com.malcolm.qme.rest.api.UserAPI.java
 * Date      : 5/26/2015
 * Developer : Malcolm
 * Purpose   : QMe User API Interface which services as a Contract for the REST Service
 **/
package com.malcolm.qme.rest.api;

import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeUser;
import com.malcolm.qme.rest.model.QMeUserDetail;
import org.springframework.web.bind.annotation.PathVariable;

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
     * QMeUser API Reset Path
     */
    String RESET_PATH = ROOT_PATH + "/reset";

    /**
     * QMeUser API Forgot User Name
     */
    String FORGOT_USERNAME_PATH = RESET_PATH + "/forgotusername";

    /**
     * QMeUser API Forgot Password
     */
    String FORGOT_PASSWORD_PATH = RESET_PATH + "/forgotpassword";

    /**
     * QMeUser API Validate Token Path
     */
    String RESET_PASSWORD_PATH = RESET_PATH + "/resetpassword";

    /**
     * Token Parameter
     */
    String TOKEN_PARAM_STRING 	= "token";
    /**
     * Token Parameter
     */
    String EMAIL_RETURN_URL_PARAM_STRING 	= "emailReturnURL";
    /**
     * Password Parameter
     */
    String PASSWORD_PARAM_STRING 	= "passwordParam";

    /**
     * Get All Users
     *
     * @return List of Users
     */
    List<QMeUserDetail> list() throws QMeResourceException;

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
    public QMeUserDetail create(QMeUser user) throws QMeResourceException;

    /**
     * Update QMe User
     *
     * @param user QMe User
     * @return QMe User
     */
    public QMeUserDetail update(@PathVariable(ID_PARAM_STRING) Long userId, QMeUser user) throws QMeResourceException;

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
    public String forgotUserName(@PathVariable(EMAIL_PARAM_STRING) String userEmail) throws QMeResourceException;

    /**
     * Forgot Passsword
     *
     * @param userEmail User Email
     * @param url Reset URL that will be sent to user email
     * @throws QMeResourceException
     */
    public void forgotPassword(@PathVariable(EMAIL_PARAM_STRING) String userEmail, @PathVariable(EMAIL_RETURN_URL_PARAM_STRING)  String url) throws QMeResourceException;

   /**
     * Reset Password
     *
     * @param userEmail User Email
     * @param token valid token
     * @param userPassword reset password
     * @throws QMeResourceException
     */
    public void checkToken(@PathVariable(EMAIL_PARAM_STRING) String userEmail, @PathVariable(TOKEN_PARAM_STRING)  Long token, @PathVariable(PASSWORD_PARAM_STRING) String userPassword) throws QMeResourceException;

}
