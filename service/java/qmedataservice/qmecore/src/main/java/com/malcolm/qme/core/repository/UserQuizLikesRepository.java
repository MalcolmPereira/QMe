/**
 * Name      : com.malcolm.qme.core.repository.UserQuizLikesRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Likes Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.UserQuizLikes;

import java.util.List;

/**
 * @author Malcolm
 */
public interface UserQuizLikesRepository extends QMeRepository<UserQuizLikes,UserQuizLikes> {

    /**
     * Find By User ID
     *
     * @param userID User ID
     * @return UserQuizLikes by User ID
     * @throws QMeException
     */
    List<UserQuizLikes> findByUserId(Long userID) throws QMeException;

    /**
     * Find By Quiz ID
     *
     * @param quizID Quiz ID
     * @return UserQuizLikes by Quiz ID
     * @throws QMeException
     */
    List<UserQuizLikes> findByQuizId(Long quizID) throws QMeException;

}
