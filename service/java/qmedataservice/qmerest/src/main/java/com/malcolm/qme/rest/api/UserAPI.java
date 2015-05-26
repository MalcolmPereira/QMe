/**
 * Name      : com.malcolm.qme.rest.api.UserAPI.java
 * Date      : 5/26/2015
 * Developer : Malcolm
 * Purpose   : QMe User API Interface which services as a Contract for the REST Service
 **/
package com.malcolm.qme.rest.api;

/**
 * @author Malcolm
 */
public interface UserAPI extends QMeAPI {
    /**
     * QMeCategory API Root Path
     */
    String ROOT_PATH = APP_STRING + "/user";

    /**
     * QMeCategory API Get By ID Path
     */
    String ID_PATH = ROOT_PATH + "/{" + ID_PARAM_STRING + "}";

    /**
     * QMeCategory API Search By Name
     */
    String NAME_PATH = ROOT_PATH + "/search/{" + NAME_PARAM_STRING + "}";


}
