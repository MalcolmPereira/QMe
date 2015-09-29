/**
 * Name      : com.malcolm.qme.rest.api.QMeAPI.java
 * Date      : 9/28/15
 * Developer : Malcolm
 * Purpose   : QME REST API Implementation
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.QMeAppAPI;
import com.malcolm.qme.rest.api.UserAPI;
import com.malcolm.qme.rest.exception.QMeResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author malcolm
 */
@RestController
public class QMeAppAPIController implements QMeAppAPI {

    @Autowired
    private String endpointURL;

    @RequestMapping(value=ROOT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody Map<String, String> api() throws QMeResourceException {
        Map<String,String> apiMap = new LinkedHashMap<>();

        //User API
        apiMap.put(USER, endpointURL+ UserAPI.ROOT_PATH.replaceAll(":.+","}"));
        apiMap.put(USER_BY_ID, endpointURL+ UserAPI.ID_PATH.replaceAll(":.+","}"));
        apiMap.put(USER_BY_NAME, endpointURL+ UserAPI.NAME_PATH.replaceAll(":.+","}"));
        apiMap.put(USER_BY_EMAIL, endpointURL+ UserAPI.EMAIL_PATH.replaceAll(":.+","}"));
        apiMap.put(REGISTER_USER, endpointURL+ UserAPI.REGISTER_PATH.replaceAll(":.+","}"));
        apiMap.put(STAGE_USER, endpointURL+ UserAPI.STAGING_PATH.replaceAll(":.+","}"));
        apiMap.put(CONFIRM_STAGE_USER, endpointURL+ UserAPI.REGISTER_CONFIRM_PATH.replaceAll(":.+","}"));
        apiMap.put(UPDATE_USER, endpointURL+ UserAPI.ROOT_PATH.replaceAll(":.+","}"));
        apiMap.put(DELETE_USER, endpointURL+ UserAPI.ID_PATH.replaceAll(":.+","}"));
        apiMap.put(FORGOT_USER_NAME, endpointURL+ UserAPI.FORGOT_USERNAME_PATH.replaceAll(":.+","}"));
        apiMap.put(FORGOT_USER_PASSWORD, endpointURL+ UserAPI.FORGOT_PASSWORD_PATH.replaceAll(":.+","}"));
        apiMap.put(RESET_USER_PASSWORD, endpointURL+ UserAPI.RESET_PASSWORD_PATH.replaceAll(":.+","}"));

        return apiMap;
    }




}
