/**
 * Name      : com.malcolm.qme.rest.controller.QuizController.java
 * Date      : 6/20/2017
 * Developer : Malcolm
 * Purpose   : REST Controller for QMeQuiz
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.QMeAppAPI;
import com.malcolm.qme.rest.api.QuizAPI;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeQuizDetail;
import com.malcolm.qme.rest.service.QuizService;
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
public class QuizController implements QuizAPI {

    @Autowired
    private String endpointURL;

    /**
     * Question Service
     */
    @Autowired
    private QuizService quizService;


    @RequestMapping(value=COUNT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public Resource<Long> count() throws QMeResourceException {
        log(getCurrentUser(), "Quiz - count");
        Resource<Long> quizCount = new Resource<>(quizService.count(),new Link(endpointURL+ QuizAPI.COUNT_PATH.replaceAll(":.+","}")));
        quizCount.add(new Link(endpointURL + QuizAPI.PAGED_PATH.replaceAll(":.+", "}") + "?page=0&pagesize=1&sorttype=true&sortfields=QUESTION", QMeAppAPI.QUIZ_PAGED));
        return quizCount;
    }

    @RequestMapping(value=ROOT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public List<QMeQuizDetail> list() throws QMeResourceException {
        log(getCurrentUser(), "Quiz - list");
        List<QMeQuizDetail> qMeQuizDetailList = quizService.list();
        setQuizLinks(qMeQuizDetailList);
        return qMeQuizDetailList;
    }

    @Override
    public List<QMeQuizDetail> listPaged(String page, String pageSize, String sortType, String sortFields) throws QMeResourceException {
        return null;
    }

    @Override
    public QMeQuizDetail searchById(Long quizId) throws QMeResourceException {
        return null;
    }

    @Override
    public QMeQuizDetail create(QMeQuizDetail quiz) throws QMeResourceException {
        return null;
    }

    @Override
    public QMeQuizDetail update(Long quizId, QMeQuizDetail quiz) throws QMeResourceException {
        return null;
    }

    @Override
    public void delete(Long quizId) throws QMeResourceException {

    }

    /**
     * Set Quiz Links
     * @param qMeQuizDetailList QMe Quiz List
     */
    private void setQuizLinks(List<QMeQuizDetail> qMeQuizDetailList) {
        qMeQuizDetailList.stream().forEach(this::setQuizLinks);
    }

    /**
     * Set Quiz Links
     * @param qMeQuizDetail QMeQuizDetail
     */
    private void setQuizLinks(QMeQuizDetail qMeQuizDetail){
        qMeQuizDetail.add(new Link(endpointURL+ QuizAPI.ID_PATH.replaceAll("\\{"+ID_PARAM_STRING+"\\}",qMeQuizDetail.getQuizID()+""),QMeAppAPI.QUIZ_BY_ID));
        qMeQuizDetail.add(new Link(endpointURL+ QuizAPI.ID_PATH.replaceAll("\\{"+ID_PARAM_STRING+"\\}",qMeQuizDetail.getQuizID()+""),QMeAppAPI.UPDATE_QUIZ));
        qMeQuizDetail.add(new Link(endpointURL+ QuizAPI.ID_PATH.replaceAll("\\{"+ID_PARAM_STRING+"\\}",qMeQuizDetail.getQuizID()+""),QMeAppAPI.DELETE_QUIZ));
    }
}
