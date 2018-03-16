/**
 * Name      : com.malcolm.qme.rest.service.UserQuizService.java
 * Date      : 9/20/17
 * Developer : Malcolm
 * Purpose   : User Quiz Service
 */
package com.malcolm.qme.rest.service;

import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeUserQuiz;
import com.malcolm.qme.rest.model.QMeUserQuizDetail;

import java.util.List;

/**
 * @author malcolm
 */
public interface UserQuizService extends QMeService<QMeUserQuizDetail, QMeUserQuiz, Long>{

    /**
     * Find Quizzes for User
     * @param userID
     * @param pageIndex
     * @param maxRows
     * @param sortAscending
     * @param sortFields
     * @return User Quiz List
     */
    List<QMeUserQuizDetail> findQuizzesForUser(Long userID,Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException;

    /**
     * Find Completed Quizzes for User
     * @param userID
     * @param pageIndex
     * @param maxRows
     * @param sortAscending
     * @param sortFields
     * @return User Quiz List
     */
    List<QMeUserQuizDetail> findCompletedByUserId(Long userID,Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields)throws QMeServerException;

    /**
     * Find Pending Quizzes for User
     * @param userID
     * @param pageIndex
     * @param maxRows
     * @param sortAscending
     * @param sortFields
     * @return User Quiz List
     */
    List<QMeUserQuizDetail>  findPendingByUserId(Long userID,Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields)throws QMeServerException;
}
