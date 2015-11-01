/**
 * Name      : com.malcolm.qme.rest.api.QMeAPI.java
 * Date      : 9/28/15
 * Developer : Malcolm
 * Purpose   : QME REST API Implementation
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.CategoryAPI;
import com.malcolm.qme.rest.api.QMeAppAPI;
import com.malcolm.qme.rest.api.UserAPI;
import com.malcolm.qme.rest.exception.QMeResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<Resources> api() throws QMeResourceException {

        List<Resource> qmeResources = new ArrayList<>();

        //QMe User API
        Resource<String> qmeUserResource = new Resource(QME_USER_API,new Link(endpointURL+ UserAPI.ROOT_PATH.replaceAll(":.+","}"),USER));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.ID_PATH.replaceAll(":.+","}"),USER_BY_ID));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.NAME_PATH.replaceAll(":.+","}"),USER_BY_NAME));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.EMAIL_PATH.replaceAll(":.+","}"),USER_BY_EMAIL));
        qmeUserResource.add(new Link( endpointURL+ UserAPI.REGISTER_PATH.replaceAll(":.+","}"),REGISTER_USER));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.STAGING_PATH.replaceAll(":.+","}"),STAGE_USER));
        qmeUserResource.add(new Link( endpointURL+ UserAPI.REGISTER_CONFIRM_PATH.replaceAll(":.+","}"),CONFIRM_STAGE_USER));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.ROOT_PATH.replaceAll(":.+","}"),UPDATE_USER));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.ID_PATH.replaceAll(":.+","}"),DELETE_USER));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.FORGOT_USERNAME_PATH.replaceAll(":.+","}"),FORGOT_USER_NAME));
        qmeUserResource.add(new Link( endpointURL+ UserAPI.FORGOT_PASSWORD_PATH.replaceAll(":.+","}"),FORGOT_USER_PASSWORD));
        qmeUserResource.add(new Link( endpointURL+ UserAPI.RESET_PASSWORD_PATH.replaceAll(":.+","}"),RESET_USER_PASSWORD));
        qmeResources.add(qmeUserResource);

        //QMe Category API
        Resource<String> qmeCategoryResource = new Resource(QME_CATEGORY_API,new Link(endpointURL+ CategoryAPI.ROOT_PATH.replaceAll(":.+","}"),CATEGORY));
        qmeResources.add(qmeCategoryResource);


        Resources qmeResourceList = new Resources(qmeResources,new Link(endpointURL+ROOT_PATH,QME_API));


        return new ResponseEntity<>(qmeResourceList,HttpStatus.OK);

    }

}
