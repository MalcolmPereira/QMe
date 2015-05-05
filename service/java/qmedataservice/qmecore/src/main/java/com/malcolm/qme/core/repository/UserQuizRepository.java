/**
 * Name      : com.malcolm.qme.core.repository.UserQuizRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.UserQuiz;

import java.util.List;

/**
 * @Author: Malcolm
 */
public interface UserQuizRepository extends QMeRepository<UserQuiz> {

    /**
     * Find By User ID
     *
     * @param userID
     * @return
     */
    public List<UserQuiz> findByUserId(Long userID);

    /**
     * Find Completed Quiz By User ID
     *
     * @param userID
     * @return
     */
    public List<UserQuiz> findCompletedByUserId(Long userID);

    /**
     * Find Pending Quiz By User ID
     *
     * @param userID
     * @return
     */
    public List<UserQuiz> findPendingByUserId(Long userID);

    /**
     * Find By Quiz ID
     *
     * @param quizID
     * @return
     */
    public List<UserQuiz> findByQuizId(Long quizID);
}
