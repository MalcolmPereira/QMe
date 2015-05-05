/**
 * Name      : com.malcolm.qme.core.repository.UserCategoryRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Category Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.UserCategory;

import java.util.List;

/**
 * @Author: Malcolm
 */
public interface UserCategoryRepository extends QMeRepository<UserCategory> {

    /**
     * Find By User ID
     * @return
     */
    public List<UserCategory> findByUserID(Long userID);
}
