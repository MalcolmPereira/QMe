/**
 * Name      : com.malcolm.qme.rest.api.UserQuizAPI.java
 * Date      : 9/14/2017
 * Developer : Malcolm
 * Purpose   : QMe User Quiz API Interface which services as a Contract for the REST Service
 **/
package com.malcolm.qme.rest.api;

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
}
