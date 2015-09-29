/**
 * Name      : com.malcolm.qme.rest.service.impl.RoleServiceImpl.java
 * Date      : 8/25/15
 * Developer : malcolm
 * Purpose   : Role Service Implementation
 */

package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.Role;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.RoleRepository;
import com.malcolm.qme.rest.exception.*;
import com.malcolm.qme.rest.model.QMeRole;
import com.malcolm.qme.rest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author malcolm
 */
@Service
public final class RoleServiceImpl implements RoleService {

    @Autowired
    @Qualifier(value = "RoleRepository")
    private RoleRepository roleRepo;

    @Override
    public QMeRole findByRoleName(String roleName) throws QMeResourceNotFoundException,QMeServerException {
        try{
            Role role = roleRepo.findByRoleName(roleName);
            if(role == null){
                throw new QMeResourceNotFoundException("Role with Role  Name "+roleName+" not found");
            }
            return getQMeRoleDetail(role);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeRole> list() throws QMeServerException {
        try{
            return getQMeRoleDetail(roleRepo.findAll());
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeRole searchById(Integer id) throws QMeResourceNotFoundException,QMeServerException {
        try{
            Role role = roleRepo.findById(id);
            if(role == null){
                throw new QMeResourceNotFoundException("Role with Role  Id "+id+" not found");
            }
            return getQMeRoleDetail(role);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeRole save(QMeRole qMeRole, Long userId) throws QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        try{
            Role role = getRole(qMeRole);
            role = roleRepo.save(role);
            return getQMeRoleDetail(role);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }



    @Override
    public QMeRole update(QMeRole qMeRole, Integer id, Long userId) throws QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        try{
            Role role = getRole(qMeRole);
            role = roleRepo.update(role,userId);
            return getQMeRoleDetail(role);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);

        }
    }

    @Override
    public void delete(Integer id) throws QMeResourceNotFoundException,QMeServerException {
        try{
            Role role = roleRepo.findById(id);
            if(role == null){
                throw new QMeResourceNotFoundException("Role with Role Id "+id+" not found");
            }
            roleRepo.delete(id);

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    /**
     * Get Role for Create
     *
     * @param qMeRole QMe User
     * @return Role
     * @throws QMeInvalidResourceDataException
     * @throws QMeResourceConflictException
     * @throws QMeServerException
     */
    private Role getRole(QMeRole qMeRole) throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException {
        try{
            if(qMeRole.getRoleName() == null || qMeRole.getRoleName().trim().length() == 0){
                throw new QMeInvalidResourceDataException("Valid Role Name is required");
            }
            Role role = roleRepo.findByRoleName(qMeRole.getRoleName());
            if(role != null){
                throw new QMeResourceConflictException("Role with role name already exists, please use valid unique role name");
            }
            return new Role(qMeRole.getRoleName(), qMeRole.getRoleDesc());
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    /**
     * Map Role Domain Object List to REST Model
     *
     * @param roleList List of Roles
     * @return QMeRole Detail List
     */
    private List<QMeRole> getQMeRoleDetail(List<Role> roleList) {
        List<QMeRole> qmeRoles = new ArrayList<>();
        if (roleList == null) {
            return qmeRoles;
        }
        qmeRoles.addAll(
                roleList.stream().map
                        (this::getQMeRoleDetail).collect(Collectors.toList())
        );
        return qmeRoles;
    }

    /**
     * Map Role Domain Object to REST Model
     *
     * @param role Role
     * @return QMeRole QMeRole Detail
     */
    private QMeRole getQMeRoleDetail(Role role) {
        QMeRole qmeRole = new QMeRole();
        qmeRole.setRoleID(role.getRoleID());
        qmeRole.setRoleName(role.getRoleName());
        qmeRole.setRoleDesc(role.getRoleDesc());
        return qmeRole;
    }
}
