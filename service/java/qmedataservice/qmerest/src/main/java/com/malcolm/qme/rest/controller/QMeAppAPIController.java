/**
 * Name      : com.malcolm.qme.rest.api.QMeAPI.java
 * Date      : 9/28/15
 * Developer : Malcolm
 * Purpose   : QME REST API Implementation
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.*;
import com.malcolm.qme.rest.exception.QMeResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody Resources api() throws QMeResourceException {

        List<Resource> qmeResources = new ArrayList<>();

        //QMe User API
        Resource<String> qmeUserResource = new Resource<>(QME_USER_API,new Link(endpointURL+ UserAPI.ROOT_PATH.replaceAll(":.+","}"),USER));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.COUNT_PATH.replaceAll(":.+","}"),USER_COUNT));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.PAGED_PATH.replaceAll(":.+","}")+"?page=0&pagesize=1&sorttype=true&sortfields=USERNAME",USER_PAGED));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.ID_PATH.replaceAll(":.+","}"),USER_BY_ID));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.NAME_PATH.replaceAll(":.+","}"),USER_BY_NAME));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.EMAIL_PATH.replaceAll(":.+","}"),USER_BY_EMAIL));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.REGISTER_PATH.replaceAll(":.+","}"),REGISTER_USER));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.STAGING_PATH.replaceAll(":.+","}"),STAGE_USER));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.REGISTER_CONFIRM_PATH.replaceAll(":.+","}"),CONFIRM_STAGE_USER));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.ID_PATH.replaceAll(":.+","}"),UPDATE_USER));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.ID_PATH.replaceAll(":.+","}"),DELETE_USER));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.FORGOT_USERNAME_PATH.replaceAll(":.+","}"),FORGOT_USER_NAME));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.FORGOT_PASSWORD_PATH.replaceAll(":.+","}"),FORGOT_USER_PASSWORD));
        qmeUserResource.add(new Link(endpointURL+ UserAPI.RESET_PASSWORD_PATH.replaceAll(":.+","}"),RESET_USER_PASSWORD));
        qmeResources.add(qmeUserResource);

        //QMe Category API
        Resource<String> qmeCategoryResource = new Resource<>(QME_CATEGORY_API,new Link(endpointURL+ CategoryAPI.ROOT_PATH.replaceAll(":.+","}"),CATEGORY));
        qmeCategoryResource.add(new Link(endpointURL+ CategoryAPI.COUNT_PATH.replaceAll(":.+","}"),CATEGORY_COUNT));
        qmeCategoryResource.add(new Link(endpointURL+ CategoryAPI.PAGED_PATH.replaceAll(":.+","}")+"?page=0&pagesize=1&sorttype=true&sortfields=CATEGORYNAME",CATEGORY_PAGED));
        qmeCategoryResource.add(new Link(endpointURL+ CategoryAPI.PARENT_PATH.replaceAll(":.+","}"),CATEGORY_BY_PARENT_ID));
        qmeCategoryResource.add(new Link(endpointURL+ CategoryAPI.ID_PATH.replaceAll(":.+","}"),CATEGORY_BY_ID));
        qmeCategoryResource.add(new Link(endpointURL+ CategoryAPI.NAME_PATH.replaceAll(":.+","}"),CATEGORY_BY_NAME));
        qmeCategoryResource.add(new Link(endpointURL+ CategoryAPI.ROOT_PATH.replaceAll(":.+","}"),CREATE_CATEGORY));
        qmeCategoryResource.add(new Link(endpointURL+ CategoryAPI.ID_PATH.replaceAll(":.+","}"),UPDATE_CATEGORY));
        qmeCategoryResource.add(new Link(endpointURL+ CategoryAPI.ID_PATH.replaceAll(":.+","}"),DELETE_CATEGORY));
        qmeResources.add(qmeCategoryResource);

        //QMe Question API
        Resource<String> qmeQuestionResource = new Resource<>(QME_QUESTION_API,new Link(endpointURL+ QuestionAPI.ROOT_PATH.replaceAll(":.+","}"),QUESTION));
        qmeQuestionResource.add(new Link(endpointURL+ QuestionAPI.COUNT_PATH.replaceAll(":.+","}"),QUESTION_COUNT));
        qmeQuestionResource.add(new Link(endpointURL+ QuestionAPI.PAGED_PATH.replaceAll(":.+","}")+"?page=0&pagesize=1&sorttype=true&sortfields=QUESTION",QUESTION_PAGED));
        qmeQuestionResource.add(new Link(endpointURL+ QuestionAPI.ID_PATH.replaceAll(":.+","}"),QUESTION_BY_ID));
        qmeQuestionResource.add(new Link( endpointURL+ QuestionAPI.ROOT_PATH.replaceAll(":.+","}"),CREATE_QUESTION));
        qmeQuestionResource.add(new Link( endpointURL+ QuestionAPI.ID_PATH.replaceAll(":.+","}"),UPDATE_QUESTION));
        qmeQuestionResource.add(new Link( endpointURL+ QuestionAPI.ID_PATH.replaceAll(":.+","}"),DELETE_QUESTION));
        qmeResources.add(qmeQuestionResource);

        //QMe Quiz API
        Resource<String> qmeQuizResource = new Resource<>(QME_QUIZ_API,new Link(endpointURL+ QuizAPI.ROOT_PATH.replaceAll(":.+","}"),QUIZ));
        qmeQuizResource.add(new Link(endpointURL+ QuizAPI.COUNT_PATH.replaceAll(":.+","}"),QUIZ_COUNT));
        qmeQuizResource.add(new Link(endpointURL+ QuizAPI.PAGED_PATH.replaceAll(":.+","}")+"?page=0&pagesize=1&sorttype=true&sortfields=QUESTION",QUIZ_PAGED));
        qmeQuizResource.add(new Link(endpointURL+ QuizAPI.ID_PATH.replaceAll(":.+","}"),QUIZ_BY_ID));
        qmeQuizResource.add(new Link( endpointURL+ QuizAPI.ROOT_PATH.replaceAll(":.+","}"),CREATE_QUIZ));
        qmeQuizResource.add(new Link( endpointURL+ QuizAPI.ID_PATH.replaceAll(":.+","}"),UPDATE_QUIZ));
        qmeQuizResource.add(new Link( endpointURL+ QuizAPI.ID_PATH.replaceAll(":.+","}"),DELETE_QUIZ));
        qmeResources.add(qmeQuizResource);

        return new Resources<>(qmeResources,new Link(endpointURL+ROOT_PATH,QME_API));
    }

}
