/**
 * Name      : com.malcolm.qme.core.repository.UserQuizGameRepository.java
 * Date      : 5/2/15
 * Developer : malcolm
 * Purpose   : QMe User Quiz Game Repository Class
 */
package com.malcolm.qme.core.repository;/**
 * Created by malcolm on 5/2/15.
 */

import com.malcolm.qme.core.domain.UserQuizGame;

import java.util.List;

/**
 * @Author: malcolm
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
