/**
 * Name      : com.malcolm.qme.rest.service.UserService.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : QMeUser Service Interface
 */
package com.malcolm.qme.rest.service;

import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeUser;
import com.malcolm.qme.rest.model.QMeUserDetail;

/**
 * @author Malcolm
 */
public interface UserService extends QMeService<QMeUserDetail, QMeUser, Long>{
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

}