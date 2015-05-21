/**
 * Name      : com.malcolm.qme.core.repository.QMeRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Repository Interface
 */

package com.malcolm.qme.core.repository;

import java.util.List;

/**
 * @author Malcolm
 */
interface QMeRepository<T,I> {
    /**
     * Find all
     * @return List of T
     */
     List<T> findAll();

    /**
     * Find By Id
     * @param id ID of Entity
     * @return One Object of type  T
     */
     T findById(I id);

    /**
     * Save
     *
     * @param t Object of type  T
     * @return Object of type  T
     */
     T save(T t);

    /**
     * Update
     *
     * @param t Object of type  T
     * @param updateUserId Update User ID
     * @return Object of type  T
     */
     T update(T t, Long updateUserId);

    /**
     * Delete
     *
     * @param id ID of Entity
     */
     void delete(I id);
}
