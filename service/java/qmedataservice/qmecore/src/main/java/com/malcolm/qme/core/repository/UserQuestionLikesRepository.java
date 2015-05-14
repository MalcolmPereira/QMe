/**
 * Name      : com.malcolm.qme.core.repository.UserQuestionLikesRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Question Likes Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.UserQuestionLikes;

import java.util.List;

/**
 * @Author: malcolm
 */
public interface UserQuestionLikesRepository extends QMeRepository<UserQuestionLikes,UserQuestionLikes> {

    /**
     * Find By User ID
     *
     * @param userID
     * @return
     */
    public List<UserQuestionLikes> findByUserId(Long userID);

    /**
     * Find By Question ID
     *
     * @param questionID
     * @return
     */
    public List<UserQuestionLikes> findByQuestionId(Long questionID);
}
