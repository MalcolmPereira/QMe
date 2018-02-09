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
     * Sort Fields Enum
     */
    enum USERQUIZSORTFIELDS {
        MAXSCORE("quizMaxScore"),
        USERSCORE("quizUserScore"),
        QUIZSTART("quizStartDate"),
        QUIZEND("quizEndDate"),
        CATEGORY("categoryID")
        ;
        /**
         * Enum Constructor
         * @param fieldName Field Name
         */
        USERQUIZSORTFIELDS(String fieldName) {
            this.userQuizSortField = fieldName;
        }

        /**
         * Sort Field Name
         */
        private final String userQuizSortField;

        /**
         * Get User Quiz Sort Field Name
         * @return User Quiz Sort Field Name
         */
        public String getUserQuizSortField() {
            return userQuizSortField;
        }
    }


    /**
     * Find By User ID
     *
     * @param userID User ID
     * @return UserQuiz by User ID
     * @throws QMeException
     */
    List<UserQuiz> findByUserId(Long userID) throws QMeException;

    /**
     * Find By Quiz ID
     *
     * @param quizID Quiz ID
     * @return UserQuiz by Quiz ID
     * @throws QMeException
     */
    List<UserQuiz> findByQuizId(Long quizID) throws QMeException;


    /**
     * Find Quizzes User ID
     *
     * @param userID User ID
     * @param pageSort PageSort
     * @return UserQuiz by User ID
     * @throws QMeException
     */
    List<UserQuiz> findQuizzesForUser(Long userID,PageSort pageSort) throws QMeException;

    /**
     * Find Completed Quiz By User ID
     *
     * @param userID User ID
     * @param pageSort PageSort
     * @return Completed UserQuiz List by User ID
     * @throws QMeException
     */
    List<UserQuiz> findCompletedByUserId(Long userID,PageSort pageSort) throws QMeException;

    /**
     * Find Pending Quiz By User ID
     *
     * @param userID User ID
     * @param pageSort PageSort
     * @return Pending UserQuiz List by User ID
     * @throws QMeException
     */
    List<UserQuiz> findPendingByUserId(Long userID,PageSort pageSort) throws QMeException;

}
