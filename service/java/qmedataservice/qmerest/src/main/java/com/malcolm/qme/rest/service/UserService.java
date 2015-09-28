/**
 * Name      : com.malcolm.qme.rest.service.UserService.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : QMeUser Service Interface
 */
package com.malcolm.qme.rest.service;

import com.malcolm.qme.rest.exception.*;
import com.malcolm.qme.rest.model.QMeResetPassword;
import com.malcolm.qme.rest.model.QMeStageUser;
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
     * Maximum Days to Confirm User Registration
     */
    int REGISTRATION_CONFIRMATION_MAX_DAYS = 5;

    /**
     * Token Validity date pattern
     */
    String DATE_PATTERN = "MM-dd-YYYY HH:mm:ss";

    /**
     * Search By User Name
     * @param userName User Name
     * @return QMe User Detail
     * @throws QMeResourceNotFoundException
     * @throws QMeServerException
     */
    QMeUserDetail searchByUser(String userName) throws QMeResourceNotFoundException,QMeServerException;

    /**
     * Search By Email
     *
     * @param userEmail User Email
     * @return QMe User Detail
     * @throws QMeResourceNotFoundException
     * @throws QMeServerException
     */
    QMeUserDetail searchByEmail(String userEmail) throws QMeResourceNotFoundException,QMeServerException;

    /**
     * Stage User in Staging Table for User Registration.
     *
     * @param qMeUser QMeUser model
     * @return Boolean - Staging successful/unsuccessful
     * @throws QMeServerException
     */
    Boolean stageUser(QMeStageUser qMeUser) throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException;

    /**
     * Confirm User Registration
     *
     * @param stagingToken User Staging Token
     * @return  Boolean - Staging successful/unsuccessful
     * @throws QMeInvalidResourceDataException
     * @throws QMeResourceNotFoundException
     * @throws QMeServerException
     */
    Boolean confirmUserRegistration(String stagingToken) throws QMeInvalidResourceDataException,QMeResourceNotFoundException,QMeServerException;

    /**
     * Forgot Password
     *
     * @param userEmail User Email for whom password reset is requested
     * @param url Redirect URL for resetting user password
     * @throws QMeInvalidResourceDataException
     * @throws QMeResourceNotFoundException
     * @throws QMeServerException
     */
    Boolean forgotPassword(String userEmail, String url) throws QMeInvalidResourceDataException,QMeResourceNotFoundException,QMeServerException;

    /**
     * Reset User Password
     *
     * @param userEmail User Email for whom password reset is requested
     * @param qMeResetPassword QMe Reset Password
     * @return QMe User Detail
     * @throws QMeInvalidResourceDataException
     * @throws QMeResourceNotFoundException
     * @throws QMeServerException
     */
    QMeUserDetail resetPassword(String userEmail, QMeResetPassword qMeResetPassword) throws QMeInvalidResourceDataException,QMeResourceNotFoundException,QMeServerException;

}
