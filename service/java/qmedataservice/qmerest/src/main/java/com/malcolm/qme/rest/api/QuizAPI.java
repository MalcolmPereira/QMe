/**
 * Name      : com.malcolm.qme.rest.api.QuizAPI.java
 * Date      : 6/20/2017
 * Developer : Malcolm
 * Purpose   : QMe Quiz API Interface which services as a Contract for the REST Service
 **/
package com.malcolm.qme.rest.api;

import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeQuizDetail;
import org.springframework.hateoas.Resource;

import java.util.List;

/**
 * Created by malcolm on 6/20/17.
 */
public interface QuizAPI extends QMeAPI {
    /**
     * QuizAPI API Root Path
     */
    String ROOT_PATH = APP_STRING + "/quiz";

    /**
     * QuizAPI API Resource Count
     */
    String COUNT_PATH = APP_STRING + "/quiz/count";

    /**
     * QuizAPI API Paged Path
     */
    String PAGED_PATH = APP_STRING + "/quiz/paged";

    /**
     * QuizAPI API Get By ID Path
     */
    String ID_PATH = ROOT_PATH + "/{" + ID_PARAM_STRING + "}";

    /**
     * Get Count
     *
     * @return Count of Catgeories
     */
    Resource<Long> count() throws QMeResourceException;

    /**
     * Get All Quiz
     *
     * @return List of Quiz Details
     */
    List<QMeQuizDetail> list() throws QMeResourceException;

    /**
     * Get All Quiz Details with Pagination and Sorting
     * @param page Page
     * @param pageSize Page Size
     * @param sortType Sort Type
     * @param sortFields Sort Fields
     * @return List of Quiz
     * @throws QMeResourceException
     */
    List<QMeQuizDetail> listPaged(String page,String pageSize, String sortType, String sortFields) throws QMeResourceException;

    /**
     * Search by ID
     *
     * @param quizId Quiz ID
     * @return QMe Quiz Detail
     */
    QMeQuizDetail searchById(Long quizId) throws QMeResourceException;

    /**
     * Create New Quiz
     *
     * @param quiz
     * @return QMe Quiz Detail
     * @throws QMeResourceException
     */
    QMeQuizDetail create(QMeQuizDetail quiz) throws QMeResourceException;

    /**
     * Update Question
     *
     * @param quizId
     * @param quiz
     * @return QMe Quiz Detail
     * @throws QMeResourceException
     */
    QMeQuizDetail update(Long quizId, QMeQuizDetail quiz) throws QMeResourceException;

    /**
     * Delete Quiz
     * @param quizId
     * @throws QMeResourceException
     */
    void delete(Long quizId) throws QMeResourceException;

}
