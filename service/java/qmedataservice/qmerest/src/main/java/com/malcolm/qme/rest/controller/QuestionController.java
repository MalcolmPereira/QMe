/**
 * Name      : com.malcolm.qme.rest.controller.QuestionController.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : REST Controller for QMeQuestion
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.QuestionAPI;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeQuestion;
import com.malcolm.qme.rest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author malcolm
 */
@RestController
public class QuestionController implements QuestionAPI {

    @Autowired
    private String endpointURL;

    /**
     * Question Service
     */
    @Autowired
    private QuestionService questionService;

    @Override
    public Resource<Long> count() throws QMeResourceException {
        return null;
    }

    @Override
    public List<QMeQuestion> list() throws QMeResourceException {
        return null;
    }

    @Override
    public List<QMeQuestion> listPaged(String page, String pageSize, String sortType, String sortFields) throws QMeResourceException {
        return null;
    }
}
