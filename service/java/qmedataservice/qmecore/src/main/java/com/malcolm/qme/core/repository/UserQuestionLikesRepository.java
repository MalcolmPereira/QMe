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
 * @author malcolm
 */
public interface UserQuestionLikesRepository extends QMeRepository<UserQuestionLikes,UserQuestionLikes> {

    /**
     * Find By User ID
     *
     * @param userID User ID
     * @return UserQuestionLikes by User ID
     * @throws QMeException
     */
    List<UserQuestionLikes> findByUserId(Long userID) throws QMeException;

    /**
     * Find By Question ID
     *
     * @param questionID Question ID
     * @return UserQuestionLikes by Question ID
     * @throws QMeException
     */
    List<UserQuestionLikes> findByQuestionId(Long questionID) throws QMeException;
}
