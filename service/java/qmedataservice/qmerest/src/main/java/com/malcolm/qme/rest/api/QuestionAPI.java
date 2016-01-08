/**
 * Name      : com.malcolm.qme.rest.api.QuestionAPI.java
 * Date      : 1/6/2016
 * Developer : Malcolm
 * Purpose   : QMe Question API Interface which services as a Contract for the REST Service
 **/
package com.malcolm.qme.rest.api;

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


}
