/**
 * Name      : com.malcolm.qme.rest.service.RoleService.java
 * Date      : 8/25/15
 * Developer : malcolm
 * Purpose   : QMe Role Service
 */
package com.malcolm.qme.rest.service;

import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeRole;

/**
 * @author malcolm
 */
public interface RoleService extends QMeService<QMeRole, QMeRole, Integer>{
    /**
     * Find Role By Role Name
     *
     * @param roleName Role Name
     * @return Role List by role name
     * @throws QMeResourceNotFoundException
     * @throws QMeServerException
     */
    QMeRole findByRoleName(String roleName) throws QMeResourceNotFoundException,QMeServerException;
}
