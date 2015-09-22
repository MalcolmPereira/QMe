/**
 * Name      : com.malcolm.qme.rest.service.UserRoleService.java
 * Date      : 8/25/15
 * Developer : malcolm
 * Purpose   : User Role Service
 */
package com.malcolm.qme.rest.service;

import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeUserRole;

import java.util.List;

/**
 * @author malcolm
 */
public interface UserRoleService extends QMeService<QMeUserRole, QMeUserRole, Long>{

    /**
     * Find Roles By User ID
     *
     * @param userID User ID
     * @return UserRole List for User ID
     * @throws QMeResourceNotFoundException
     * @throws QMeServerException
     */
    List<QMeUserRole> findByUserId(Long userID) throws QMeResourceNotFoundException,QMeServerException;

    /**
     * Find By Role ID
     *
     * @param roleID Role ID
     * @return UserRole for Role ID
     * @throws QMeResourceNotFoundException
     * @throws QMeServerException
     */
    List<QMeUserRole> findByRoleId(Integer roleID) throws QMeResourceNotFoundException,QMeServerException;
}
