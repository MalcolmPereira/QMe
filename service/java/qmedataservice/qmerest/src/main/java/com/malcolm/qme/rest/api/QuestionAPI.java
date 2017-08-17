/**
 * Name      : com.malcolm.qme.rest.api.QuestionAPI.java
 * Date      : 1/6/2016
 * Developer : Malcolm
 * Purpose   : QMe Question API Interface which services as a Contract for the REST Service
 **/
package com.malcolm.qme.rest.api;

import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeQuestionDetail;
import org.springframework.hateoas.Resource;

import java.util.List;

/**
 * @author Malcolm
 */
@SuppressWarnings("ALL")
public interface QuestionAPI extends QMeAPI {
    /**
     * QuestionAPI API Root Path
     */
    String ROOT_PATH = APP_STRING + "/question";

    /**
     * QuestionAPI API Questions By Category
     */
    String ROOT_PATH_BY_CATID = APP_STRING + "/question/category" + "/{" + ID_PARAM_STRING + "}";

    /**
     * QuestionAPI API Resource Count
     */
    String COUNT_PATH = APP_STRING + "/question/count";

    /**
     * QuestionAPI API Resource Count By Category Id
     */
    String COUNT_PATH_BY_CATID = APP_STRING + "/question/category"+"/{" + ID_PARAM_STRING + "}"+"/count";

    /**
     * QuestionAPI API Paged Path
     */
    String PAGED_PATH = APP_STRING + "/question/paged";

    /**
     * QuestionAPI API Paged Path By Category
     */
    String PAGED_PATH_BY_CATID = APP_STRING + "/question/category"+ "/{" + ID_PARAM_STRING + "}/paged";

    /**
     * QuestionAPI API Get By ID Path
     */
    String ID_PATH = ROOT_PATH + "/{" + ID_PARAM_STRING + "}";

    /**
     * Get Count
     *
     * @return Count of Catgeories
     * @throws QMeResourceException
     */
    Resource<Long> count() throws QMeResourceException;

    /**
     * Get Count By Category ID
     * @param categoryId
     * @return Long count of Question by Category
     * @throws QMeResourceException
     */
    Resource<Long> countByCategoryId(Long categoryId) throws QMeResourceException;

    /**
     * Get All Question
     *
     * @return List of Question Details
     * @throws QMeResourceException
     */
    List<QMeQuestionDetail> list() throws QMeResourceException;

    /**
     *  Get All Question By Category Id
     * @param categoryId
     * @return List of Question Details
     * @throws QMeResourceException
     */
    List<QMeQuestionDetail> list(Long categoryId) throws QMeResourceException;


    /**
     * Get All Question Details with Pagination and Sorting
     * @param page Page
     * @param pageSize Page Size
     * @param sortType Sort Type
     * @param sortFields Sort Fields
     * @return List of Question
     * @throws QMeResourceException
     */
    List<QMeQuestionDetail> listPaged(String page,String pageSize, String sortType, String sortFields) throws QMeResourceException;

    /**
     * Get All Question Details by Category Id with Pagination and Sorting
     *
     * @param categoryId
     * @param page Page
     * @param pageSize Page Size
     * @param sortType Sort Type
     * @param sortFields Sort Fields
     * @return List of Question
     * @throws QMeResourceException
     */
    List<QMeQuestionDetail> listPaged(Long categoryId, String page,String pageSize, String sortType, String sortFields) throws QMeResourceException;

    /**
     * Search by ID
     *
     * @param questionId Question ID
     * @return QMe Question Detail
     * @throws QMeResourceException
     */
    QMeQuestionDetail searchById(Long questionId) throws QMeResourceException;

    /**
     * Create New Question
     *
     * @param question
     * @return QMe Question Detail
     * @throws QMeResourceException
     */
    QMeQuestionDetail create(QMeQuestionDetail question) throws QMeResourceException;

    /**
     * Update Question
     *
     * @param questionId
     * @param question
     * @return QMe Question Detail
     * @throws QMeResourceException
     */
    QMeQuestionDetail update(Long questionId, QMeQuestionDetail question) throws QMeResourceException;

    /**
     * Delete Question
     * @param questionId
     * @throws QMeResourceException
     */
    void delete(Long questionId) throws QMeResourceException;
}
