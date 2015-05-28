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
     * QMeUser API Reset Password
     */
    String RESET_PASSWORD_PATH = ROOT_PATH + "/reset/password/";

    /**
     * QMeUser API Register User Path
     */
    String REGISTER_PATH = ROOT_PATH + "/register";

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
     * Update QMe User
     *
     * @param user QMe User
     * @return QMe User
     */
    public QMeUserDetail resetPassword(@PathVariable(ID_PARAM_STRING) Long userId, QMeUser user) throws QMeResourceException;


    /**
     * Delete QMe User
     *
     * @param userId User Is
     */
    void delete(@PathVariable(ID_PARAM_STRING) Long userId) throws QMeResourceException;
}
