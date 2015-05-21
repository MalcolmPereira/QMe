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
 * @author Malcolm
 */
public interface UserQuizRepository extends QMeRepository<UserQuiz, Long> {

    /**
     * Find By User ID
     *
     * @param userID User ID
     * @return UserQuiz by User ID
     */
    List<UserQuiz> findByUserId(Long userID);

    /**
     * Find Completed Quiz By User ID
     *
     * @param userID User ID
     * @return Completed UserQuiz List by User ID
     */
    List<UserQuiz> findCompletedByUserId(Long userID);

    /**
     * Find Pending Quiz By User ID
     *
     * @param userID User ID
     * @return Pending UserQuiz List by User ID
     */
    List<UserQuiz> findPendingByUserId(Long userID);

    /**
     * Find By Quiz ID
     *
     * @param quizID Quiz ID
     * @return UserQuiz by Quiz ID
     */
    List<UserQuiz> findByQuizId(Long quizID);
}
