/**
 * Name      : com.malcolm.qme.rest.api.UserQuizAPI.java
 * Date      : 9/14/2017
 * Developer : Malcolm
 * Purpose   : QMe User Quiz API Interface which services as a Contract for the REST Service
 **/
package com.malcolm.qme.rest.api;

import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeUserQuizDetail;
import org.springframework.hateoas.Resource;

import java.util.List;

/**
 * @author Malcolm
 */
public interface UserQuizAPI extends QMeAPI {
    /**
     * QMe User Quiz API Root Path
     */
    String ROOT_PATH = APP_STRING + "/userquiz";

    /**
     * QMeUser API Resource Count
     */
    String COUNT_PATH = APP_STRING + "/userquiz/count";

    /**
     * QMeUser API Paged Path
     */
    String PAGED_PATH = APP_STRING + "/userquiz/paged";

    /**
     * UserQuizAPI API Get By ID Path
     */
    String ID_PATH = ROOT_PATH + "/{" + ID_PARAM_STRING + "}";

    /**
     * QMe User Quiz List Path
     */
    String QUIZ_PATH = ROOT_PATH + "/list";

    /**
     * QMe User List Pending Path
     */
    String QUIZ_PATH_PENDING = ROOT_PATH + "/listpending";

    /**
     * QMe User List Completed Path
     */
    String QUIZ_PATH_COMPLETED = ROOT_PATH + "/listcompleted";

    /**
     * QMe User Quiz Register
     */
    String QUIZ_PATH_REGISTER = ROOT_PATH + "/register" + "/{" + ID_PARAM_STRING + "}";

    /**
     * QMe User Quiz UnRegister
     */
    String QUIZ_PATH_UNREGISTER = ROOT_PATH + "/unregister" + "/{" + ID_PARAM_STRING + "}";

    /**
     * Get Count
     *
     * @return Count of Catgeories
     */
    Resource<Long> count() throws QMeResourceException;

    /**
     * Get All User Quiz
     *
     * @return List of User Quiz
     */
    List<QMeUserQuizDetail> list() throws QMeResourceException;

    /**
     * Get User Quiz Paged
     * @param page
     * @param pageSize
     * @param sortType
     * @param sortFields
     * @return List of User Quiz
     * @throws QMeResourceException
     */
    List<QMeUserQuizDetail> listPaged(String page,String pageSize, String sortType, String sortFields) throws QMeResourceException;

    /**
     * Get All Quizzes available for User
     * @param page
     * @param pageSize
     * @param sortType
     * @param sortFields
     * @return
     * @throws QMeResourceException
     */
    List<QMeUserQuizDetail> listQuizzes(String page,String pageSize, String sortType, String sortFields) throws QMeResourceException;

    /**
     * Get Pending Quizzes available for User
     * @param page
     * @param pageSize
     * @param sortType
     * @param sortFields
     * @return User Quiz Details
     * @throws QMeResourceException
     */
    List<QMeUserQuizDetail> listQuizzesPending(String page,String pageSize, String sortType, String sortFields) throws QMeResourceException;

    /**
     * Get Completed Quizzes available for User
     * @param page
     * @param pageSize
     * @param sortType
     * @param sortFields
     * @return User Quiz Details
     * @throws QMeResourceException
     */
    List<QMeUserQuizDetail> listQuizzesCompleted(String page,String pageSize, String sortType, String sortFields) throws QMeResourceException;

    /**
     * Register For Quiz
     * @param quizID
     * @return User Quiz Details
     * @throws QMeResourceException
     */
    QMeUserQuizDetail registerForQuiz(Long quizID) throws QMeResourceException;

    /**
     * UnRegister For Quiz
     * @param userQuizID User Quiz ID
     */
    void unRegisterFromQuiz(Long userQuizID) throws QMeResourceException;

}
