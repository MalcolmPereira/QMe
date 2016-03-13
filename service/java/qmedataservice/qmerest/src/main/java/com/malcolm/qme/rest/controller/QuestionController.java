/**
 * Name      : com.malcolm.qme.rest.controller.QuestionController.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : REST Controller for QMeQuestion
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.QuestionAPI;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeQuestionDetail;
import com.malcolm.qme.rest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value=ROOT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody List<QMeQuestionDetail> list() throws QMeResourceException {
        log(getCurrentUser(), "Question - list");
        return null;
    }

    @Override
    public List<QMeQuestionDetail> listPaged(String page, String pageSize, String sortType, String sortFields) throws QMeResourceException {
        return null;
    }
}
