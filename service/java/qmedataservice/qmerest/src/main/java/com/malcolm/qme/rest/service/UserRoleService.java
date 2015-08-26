/**
 * Name      : com.malcolm.qme.rest.service.UserRoleService.java
 * Date      : 8/25/15
 * Developer : malcolm
 * Purpose   : User Role Service
 */
package com.malcolm.qme.rest.service;

import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeUserRole;

import java.util.List;

/**
 * @author malcolm
 */
public interface UserRoleService extends QMeService<QMeUserRole, QMeUserRole, Long>{

    /**
     * Find By User ID
     *
     * @param userID User ID
     * @return UserRole for User ID
     * @throws QMeResourceException
     */
    List<QMeUserRole> findByUserId(Long userID) throws QMeResourceException;

    /**
     * Find By Role ID
     *
     * @param roleID Role ID
     * @return UserRole for Role ID
     * @throws QMeResourceException
     */
    List<QMeUserRole> findByRoleId(Integer roleID) throws QMeResourceException;
}
