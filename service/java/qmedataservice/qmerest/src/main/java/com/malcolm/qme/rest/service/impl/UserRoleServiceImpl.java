/**
 * Name      : com.malcolm.qme.rest.service.impl.UserRoleServiceImpl.java
 * Date      : 8/25/15
 * Developer : malcolm
 * Purpose   : User Role Service
 */

package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.UserRole;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.RoleRepository;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.core.repository.UserRoleRepository;
import com.malcolm.qme.rest.exception.*;
import com.malcolm.qme.rest.model.QMeUserRole;
import com.malcolm.qme.rest.service.UserRoleService;
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
public final class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    @Qualifier(value = "UserRoleRepository")
    private UserRoleRepository userRoleRepo;

    @Autowired
    @Qualifier(value = "RoleRepository")
    private RoleRepository roleRepo;

    @Autowired
    @Qualifier(value = "UserRepository")
    private UserRepository userRepo;

    @Override
    public List<QMeUserRole> findByUserId(Long userID) throws QMeResourceNotFoundException,QMeServerException {
        try{
            List<UserRole> userRoleList = userRoleRepo.findByUserId(userID);
            if(userRoleList == null || userRoleList.size() ==  0){
                throw new QMeResourceNotFoundException("No roles assigned to user "+userID+" no roles found");
            }
            return getQMeUserRoleDetail(userRoleList);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeUserRole> findByRoleId(Integer roleID) throws QMeResourceNotFoundException,QMeServerException {
        try{
            List<UserRole> userRoleList = userRoleRepo.findByRoleId(roleID);
            if(userRoleList == null || userRoleList.size() ==  0){
                throw new QMeResourceNotFoundException("No roles found for role  "+roleID+" no roles found");
            }
            return getQMeUserRoleDetail(userRoleList);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeUserRole> list() throws QMeServerException {
        try{
            return getQMeUserRoleDetail(userRoleRepo.findAll());
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeUserRole searchById(Long id) throws QMeResourceNotFoundException,QMeServerException {
        try{
            UserRole userRole = userRoleRepo.findById(id);
            if(userRole == null){
                throw new QMeResourceNotFoundException("Role with Role Id "+id+" not found");
            }
            return getQMeUserRoleDetail(userRole);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeUserRole save(QMeUserRole qMeUserRole, Long userId) throws QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        try{
            UserRole userRole;
            userRole = getUserRole(qMeUserRole);
            userRole = userRoleRepo.save(userRole);
            return getQMeUserRoleDetail(userRole);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeUserRole update(QMeUserRole qMeUserRole, Long id, Long userId) throws QMeResourceNotFoundException,QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        try{
            UserRole userRole = getUserRole(qMeUserRole);
            userRole = userRoleRepo.update(userRole, userId);
            return getQMeUserRoleDetail(userRole);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public void delete(Long id) throws QMeResourceNotFoundException,QMeServerException {
        try{
            UserRole userRole = userRoleRepo.findById(id);
            if(userRole == null){
                throw new QMeResourceNotFoundException("UserRole with User Role Id "+id+" not found");
            }
            userRoleRepo.delete(id);

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }

    }

    /**
     * Get UserRole for Create
     *
     * @param qmeUserRole Qme User Role
     * @return User Role
     * @throws QMeInvalidResourceDataException
     * @throws QMeResourceConflictException
     * @throws QMeServerException
     */
    private UserRole getUserRole(QMeUserRole qmeUserRole) throws QMeInvalidResourceDataException,QMeResourceConflictException,QMeServerException {
        try{
            if(qmeUserRole.getRoleID() == null || qmeUserRole.getRoleID() == 0){
                throw new QMeInvalidResourceDataException("Valid Role Id is required");
            }
            if(qmeUserRole.getUserID() == null || qmeUserRole.getUserID() == 0){
                throw new QMeInvalidResourceDataException("Valid User Id is required");
            }
            //FIXME:
            //TODO: Update this to controlled code to check existence when adding!!!
            /*
            Role role = roleRepo.findById(qmeUserRole.getRoleID());
            if(role == null){
                throw new QMeResourceNotFoundException("Role with Role  Id "+qmeUserRole.getRoleID()+" not found");
            }
            User user = userRepo.findById(qmeUserRole.getUserID());
            if(user == null){
                throw new QMeResourceNotFoundException("User with User Id "+qmeUserRole.getUserID()+" not found");
            }
            */
            //FIXME:

            List<UserRole> userRoleList = userRoleRepo.findByUserId(qmeUserRole.getUserID());
            if(userRoleList != null && userRoleList.size() ==  0){
                for (UserRole userRole : userRoleList){
                    if(userRole.getRoleID().equals(qmeUserRole.getRoleID())){
                        throw new QMeResourceConflictException("Role with role id "+qmeUserRole.getRoleID()+" already assigned to user "+qmeUserRole.getUserID() +", please assign unique role to user");
                    }
                }
            }
            return new UserRole(qmeUserRole.getRoleID(),qmeUserRole.getUserID());
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    /**
     * Map User Role Domain Object List to REST Model
     *
     * @param userRoleList List of Roles
     * @return QMeUserRole Detail List
     */
    private List<QMeUserRole> getQMeUserRoleDetail(List<UserRole> userRoleList) {
        List<QMeUserRole> qmeUserRoles = new ArrayList<>();
        if (userRoleList == null) {
            return qmeUserRoles;
        }
        qmeUserRoles.addAll(
                userRoleList.stream().map
                        (this::getQMeUserRoleDetail).collect(Collectors.toList())
        );
        return qmeUserRoles;
    }

    /**
     * Map User Role Domain Object to REST Model
     *
     * @param userRole User Role
     * @return QMeUserRole qme user role
     */
    private QMeUserRole getQMeUserRoleDetail(UserRole userRole) {
        QMeUserRole qmeUserRole = new QMeUserRole();
        qmeUserRole.setUserRoleID(userRole.getUserRoleID());
        qmeUserRole.setRoleID(userRole.getRoleID());
        qmeUserRole.setRoleName(userRole.getRoleName());
        qmeUserRole.setUserID(userRole.getUserID());
        return qmeUserRole;
    }
}
