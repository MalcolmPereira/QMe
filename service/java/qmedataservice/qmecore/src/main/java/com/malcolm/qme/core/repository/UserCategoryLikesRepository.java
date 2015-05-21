/**
 * Name      : com.malcolm.qme.core.repository.UserCategoryLikesRepository.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : QMe User Category Likes Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.UserCategoryLikes;

import java.util.List;

/**
 * @author Malcolm
 */
public interface UserCategoryLikesRepository extends QMeRepository<UserCategoryLikes,UserCategoryLikes> {
    /**
     * Find By User ID
     *
     * @param userID User ID
     * @return UserCategoryLikes by User ID
     */
    List<UserCategoryLikes> findByUserId(Long userID);

}
