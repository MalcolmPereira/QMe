/**
 * Name      : com.malcolm.qme.rest.service.UserService.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : QMeUser Service Interface
 */
package com.malcolm.qme.rest.service;

import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeResetPassword;
import com.malcolm.qme.rest.model.QMeUser;
import com.malcolm.qme.rest.model.QMeUserDetail;

/**
 * @author Malcolm
 */
public interface UserService extends QMeService<QMeUserDetail, QMeUser, Long>{

    /**
     * Time to live for Password Reset Token Validity
     */
    int TOKEN_VALIDITY_MINUTES = 180;

    /**
     * Token Validity date pattern
     */
    String TOKEN_VALIDITY_DATE_PATTERN = "MM-dd-YYYY HH:mm:ss";

    /**
     * Search By User Name
     * @param userName User Name
     * @return QMe User Detail
     * @throws QMeResourceException
     */
    QMeUserDetail searchByUser(String userName) throws QMeResourceException;

    /**
     * Search By Email
     * @param userEmail User Email
     * @return QMe User Detail
     * @throws QMeResourceException
     */
    QMeUserDetail searchByEmail(String userEmail) throws QMeResourceException;

    /**
     * Forgot Password
     *
     * @param userEmail User Email for whom password reset is requested
     * @param url Redirect URL for reseting user password
     * @throws QMeResourceException
     */
    void forgotPassword(String userEmail, String url) throws QMeResourceException;

    /**
     * Reset User Password
     * @param userEmail User Email for whom password reset is requested
     * @param qMeResetPassword QMe Reset Password
     */
    QMeUserDetail resetPassword(String userEmail, QMeResetPassword qMeResetPassword) throws QMeResourceException;

}
