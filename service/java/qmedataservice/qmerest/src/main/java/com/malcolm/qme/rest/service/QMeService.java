/**
 * Name      : com.malcolm.qme.rest.service.QMeService.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QMe Service Interface
 */
package com.malcolm.qme.rest.service;

import java.util.List;

/**
 * @Author: malcolm
 */
public interface QMeService<T, I> {
    /**
     * List All
     *
     * @return
     */
    List<T> list();

    /**
     * Search By Id
     *
     * @param id
     * @return
     */
    T searchById(I id);

    /**
     * Save
     *
     * @param t
     * @return
     */
    T create(T t);

    /**
     * Update
     *
     * @param t
     * @param updateUserId
     * @return
     */
    T update(T t, Long updateUserId);

    /**
     * Delete
     *
     * @param id
     */
    void delete(I id);
}