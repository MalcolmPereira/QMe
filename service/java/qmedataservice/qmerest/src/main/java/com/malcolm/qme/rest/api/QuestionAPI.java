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
}
