/**
 * Name      : com.malcolm.qme.rest.service.QMeService.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QMe Service Interface
 */
package com.malcolm.qme.rest.service;

import com.malcolm.qme.rest.exception.*;

import java.util.List;

/**
 * @author: Malcolm
 * D - Resource Details
 * T - Resource for Create and Update
 * I - ID for Resource
 */
public interface QMeService<D,T,I> {
    /**
     * List All
     *
     * @return List of Resources
     * @throws QMeServerException
     */
    List<D> list() throws QMeServerException;

    /**
     * Search By Id
     *
     * @param id id Id of Resource
     * @return Resource
     * @throws QMeResourceNotFoundException
     * @throws QMeServerException
     */
    D searchById(I id) throws QMeResourceNotFoundException,QMeServerException;

    /**
     * Save Resource
     *
     * @param t Resource to creates
     * @param userId
     * @return Resource
     * @throws QMeInvalidResourceDataException
     * @throws QMeResourceConflictException
     * @throws QMeServerException
     */
    D save(T t, Long userId) throws QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException;

    /**
     * Update Resource
     *
     * @param t Resource to update
     * @param id id for resource
     * @param userId update user id
     * @return
     * @throws QMeResourceNotFoundException
     * @throws QMeInvalidResourceDataException
     * @throws QMeResourceConflictException
     * @throws QMeServerException
     */
    D update(T t, I id, Long userId) throws QMeResourceNotFoundException,QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException;

    /**
     * Delete Resource
     * @param id Id of Resource to delete
     * @throws QMeResourceNotFoundException
     * @throws QMeServerException
     */
    void delete(I id) throws QMeResourceNotFoundException,QMeServerException;
}