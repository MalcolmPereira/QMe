/**
 * Name      : com.malcolm.qme.core.repository.UserQuizGameRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Game Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.UserQuizGame;

import java.util.List;

/**
 * @author Malcolm
 */
public interface UserQuizGameRepository extends QMeRepository<UserQuizGame,UserQuizGame> {
    /**
     * Find By User ID
     *
     * @param userID User ID
     * @return UserQuizGame List by User ID
     * @throws QMeException
     */
    List<UserQuizGame> findByUserId(Long userID) throws QMeException;

    /**
     * Find By Game Token
     *
     * @param userGameToken User Game Token
     * @return UserQuizGame by User Game Token
     * @throws QMeException
     */
    UserQuizGame findByGameToken(Long userGameToken)throws QMeException;
}
