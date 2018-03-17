/**
 * Name      : com.malcolm.qme.rest.service.UserQuizService.java
 * Date      : 9/20/17
 * Developer : Malcolm
 * Purpose   : User Quiz Service
 */
package com.malcolm.qme.rest.service;

import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeUserQuiz;
import com.malcolm.qme.rest.model.QMeUserQuizDetail;

import java.util.List;

/**
 * @author malcolm
 */
public interface UserQuizService extends QMeService<QMeUserQuizDetail, QMeUserQuiz, Long>{

    /**
     * Find if user has pending quiz in which case do not allow to register for quiz until it is deleted
     * @param userID User ID
     * @param quizID Quiz ID
     * @return boolean True if there is a pending quiz
     * @throws QMeServerException
     */
    boolean findPendingForUserByQuizId(Long userID, Long quizID) throws QMeServerException, QMeResourceNotFoundException;

    /**
     * Find Quizzes for User
     * @param userID User ID
     * @param pageIndex Page Index
     * @param maxRows Max Rows
     * @param sortAscending Sort Ascending
     * @param sortFields Sort Field
     * @return QMeUserQuizDetail QMe User Quiz Details
     * @throws QMeServerException Server Exception
     */
    List<QMeUserQuizDetail> findQuizzesForUser(Long userID,Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException;

    /**
     * Find Completed Quizzes for User
     * @param userID User ID
     * @param pageIndex Page Index
     * @param maxRows Max Rows
     * @param sortAscending Sort Ascending
     * @param sortFields Sort Fields
     * @return User Quiz List
     * @throws QMeServerException Server Exception
     */
    List<QMeUserQuizDetail> findCompletedByUserId(Long userID,Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields)throws QMeServerException;

    /**
     * Find Pending Quizzes for User
     * @param userID User Id
     * @param pageIndex Page Index
     * @param maxRows Max Rows
     * @param sortAscending Sort Ascending
     * @param sortFields Sort Fields
     * @return User Quiz List User Quiz List
     * @throws QMeServerException Server Exception
     */
    List<QMeUserQuizDetail>  findPendingByUserId(Long userID,Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields)throws QMeServerException;

    /**
     * Start User Quiz
     * @param userID User ID
     * @param userQuizID User Quiz ID
     * @return QMeUserQuizDetail User Quiz Detail
     * @throws QMeServerException Server Exception
     * @throws QMeResourceNotFoundException Resource Not Found
     * @throws QMeInvalidResourceDataException Invalid Resource
     * @throws QMeResourceConflictException Resource Conflict
     */
    QMeUserQuizDetail startQuiz(Long userID, Long userQuizID) throws QMeServerException, QMeResourceNotFoundException, QMeInvalidResourceDataException, QMeResourceConflictException;

    /**
     * Complete User Quiz
     * @param userID User ID
     * @param userQuizID User Quiz ID
     * @param quizDetail User Quiz Detail
     * @throws QMeServerException Server Exception
     * @throws QMeResourceNotFoundException Resource Not Found
     * @throws QMeInvalidResourceDataException Invalid Resource
     */
    QMeUserQuizDetail completeQuiz(Long userID, Long userQuizID,QMeUserQuizDetail quizDetail) throws QMeServerException, QMeResourceNotFoundException, QMeInvalidResourceDataException;
}
