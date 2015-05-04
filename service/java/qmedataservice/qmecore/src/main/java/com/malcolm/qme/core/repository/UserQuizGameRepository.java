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
 * @Author: Malcolm
 */
public interface UserQuizGameRepository extends QMeRepository<UserQuizGame> {
    /**
     * Find By User ID
     *
     * @param userID
     * @return
     */
    public List<UserQuizGame> findByUserId(long userID);

    /**
     * Find By Game Token
     *
     * @param userGameToken
     * @return
     */
    public UserQuizGame findByGameToken(long userGameToken);
}
