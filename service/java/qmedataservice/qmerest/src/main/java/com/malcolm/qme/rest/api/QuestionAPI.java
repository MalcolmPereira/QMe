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
     * QuestionAPI API Resource Count
     */
    String COUNT_PATH = APP_STRING + "/question/count";

    /**
     * QuestionAPI API Paged Path
     */
    String PAGED_PATH = APP_STRING + "/question/paged";

    /**
     * QuestionAPI API Get By ID Path
     */
    String ID_PATH = ROOT_PATH + "/{" + ID_PARAM_STRING + "}";

    /**
     * Get Count
     *
     * @return Count of Catgeories
     */
    Resource<Long> count() throws QMeResourceException;

    /**
     * Get All Categories
     *
     * @return List of Question Details
     */
    List<QMeQuestionDetail> list() throws QMeResourceException;


    /**
     * Get All Question Details with Pagination and Sorting
     * @param page Page
     * @param pageSize Page Size
     * @param sortType Sort Type
     * @param sortFields Sort Fields
     * @return List of Category
     * @throws QMeResourceException
     */
    List<QMeQuestionDetail> listPaged(String page,String pageSize, String sortType, String sortFields) throws QMeResourceException;


}