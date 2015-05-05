/**
 * Name      : com.malcolm.qme.core.repository.QMeRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Repository Interface
 */

package com.malcolm.qme.core.repository;

import java.util.List;

/**
 * @Author: Malcolm
 */
public interface QMeRepository<T> {
    /**
     * Find all
     * @return
     */
    public List<T> findAll();

    /**
     * Find By Id
     * @param id
     * @return
     */
    public T findById(Long id);

    /**
     * Save
     *
     * @param t
     * @return
     */
    public T save(T t);

    /**
     * Update
     *
     * @param t
     * @param updateUserId
     * @return
     */
    public T update(T t, Long updateUserId);

    /**
     * Delete
     *
     * @param id
     */
    public void delete(Long id);
}
