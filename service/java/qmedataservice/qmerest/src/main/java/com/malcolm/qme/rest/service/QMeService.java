/**
 * Name      : com.malcolm.qme.rest.service.QMeService.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QMe Service Interface
 */
package com.malcolm.qme.rest.service;

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
     */
    List<D> list() throws QMeResourceException;

    /**
     * Search By Id
     *
     * @param id Id of Resource
     * @return Resource
     */
    D searchById(I id) throws QMeResourceException ;

    /**
     * Save
     *
     * @param t Resource to creates
     * @return Resource
     */
    D save(T t, Long userId) throws QMeResourceException;

    /**
     * Update
     *
     * @param t Resource to creates
     * @return Resource
     */
    D update(T t, I id, Long userId) throws QMeResourceException;

    /**
     * Delete
     *
     * @param id Id of Resource to delete
     */
    void delete(I id) throws QMeResourceException;
}