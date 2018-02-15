/**
 * Name      : com.malcolm.qme.rest.api.UserQuizAPI.java
 * Date      : 9/14/2017
 * Developer : Malcolm
 * Purpose   : QMe User Quiz API Interface which services as a Contract for the REST Service
 **/
package com.malcolm.qme.rest.api;

import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeUserQuiz;
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
    List<QMeUserQuiz> list() throws QMeResourceException;

    /**
     * Get User Quiz Paged
     * @param page
     * @param pageSize
     * @param sortType
     * @param sortFields
     * @return List of User Quiz
     * @throws QMeResourceException
     */
    List<QMeUserQuiz> listPaged(String page,String pageSize, String sortType, String sortFields) throws QMeResourceException;

}
