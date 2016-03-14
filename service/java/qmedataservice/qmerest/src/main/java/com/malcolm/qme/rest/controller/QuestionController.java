/**
 * Name      : com.malcolm.qme.rest.controller.QuestionController.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : REST Controller for QMeQuestion
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.QMeAppAPI;
import com.malcolm.qme.rest.api.QuestionAPI;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeQuestionDetail;
import com.malcolm.qme.rest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @RequestMapping(value=COUNT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public Resource<Long> count() throws QMeResourceException {
        log(getCurrentUser(), "User - count");
        Resource<Long> questionCount = new Resource<>(questionService.count(),new Link(endpointURL+ QuestionAPI.COUNT_PATH.replaceAll(":.+","}")));
        questionCount.add(new Link(endpointURL + QuestionAPI.PAGED_PATH.replaceAll(":.+", "}") + "?page=0&pagesize=1&sorttype=true&sortfields=QUESTION", QMeAppAPI.QUESTION_PAGED));
        return questionCount;
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
