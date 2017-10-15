/**
 * Name      : com.malcolm.qme.rest.controller.UserQuizController.java
 * Date      : 9/20/2017
 * Developer : Malcolm
 * Purpose   : REST Controller for QMe User Quiz
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.QMeAppAPI;
import com.malcolm.qme.rest.api.UserQuizAPI;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeUserQuiz;
import com.malcolm.qme.rest.service.UserQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for QMeQuiz
 *
 * @author malcolm
 */
@RestController
public class UserQuizController implements UserQuizAPI  {

    @Autowired
    private String endpointURL;

    @Autowired
    private UserQuizService userQuizService;

    @RequestMapping(value=COUNT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+USER_ROLE+"')")
    @Override
    public Resource<Long> count() throws QMeResourceException {
        log(getCurrentUser(), "User Quiz - count");
        Resource<Long> userQuizCount = new Resource<>(userQuizService.count(),new Link(endpointURL+ UserQuizAPI.COUNT_PATH.replaceAll(":.+","}")));
        userQuizCount.add(new Link(endpointURL + UserQuizAPI.PAGED_PATH.replaceAll(":.+", "}") + "?page=0&pagesize=1&sorttype=true&sortfields=QUIZ", QMeAppAPI.USER_QUIZ_PAGED));
        return userQuizCount;
    }

    @RequestMapping(value=ROOT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+USER_ROLE+"')")
    @Override
    public List<QMeUserQuiz> list() throws QMeResourceException {
        log(getCurrentUser(), "User Quiz - list");
        List<QMeUserQuiz> qMeUserQuizList = userQuizService.list();
        setUserQuizLinks(qMeUserQuizList);
        return qMeUserQuizList;
    }

    /**
     * Set User Quiz Links
     * @param qMeUserQuizList QMe User Quiz List
     */
    private void setUserQuizLinks(List<QMeUserQuiz> qMeUserQuizList) {
        qMeUserQuizList.stream().forEach(this::setUserQuizLinks);
    }


    /**
     * Set User Quiz Links
     * @param qMeUserQuiz QMeUserQuiz
     */
    private void setUserQuizLinks(QMeUserQuiz qMeUserQuiz) {
        qMeUserQuiz.add(new Link(endpointURL+ UserQuizAPI.ID_PATH.replaceAll("\\{"+ID_PARAM_STRING+"\\}",qMeUserQuiz.getUserQuizID()+""),QMeAppAPI.USER_QUIZ_BY_ID));
    }
}
