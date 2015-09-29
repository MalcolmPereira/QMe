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
 * @author Malcolm
 */
public interface UserCategoryRepository extends QMeRepository<UserCategory,Long> {

    /**
     * Find By User ID
     * @param userID  User ID
     * @return UserCategory by User ID
     * @throws QMeException
     */
    List<UserCategory> findByUserID(Long userID) throws QMeException;
}
