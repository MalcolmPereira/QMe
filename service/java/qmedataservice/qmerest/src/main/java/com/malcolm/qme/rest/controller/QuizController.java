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
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private QuizService quizService;

    @RequestMapping(value=COUNT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public Resource<Long> count() throws QMeResourceException {
        log(getCurrentUser(), "Quiz - count");
        Resource<Long> quizCount = new Resource<>(quizService.count(),new Link(endpointURL+ QuizAPI.COUNT_PATH.replaceAll(":.+","}")));
        quizCount.add(new Link(endpointURL + QuizAPI.PAGED_PATH.replaceAll(":.+", "}") + "?page=0&pagesize=1&sorttype=true&sortfields=QUIZ", QMeAppAPI.QUIZ_PAGED));
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

    @RequestMapping(value=PAGED_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public List<QMeQuizDetail> listPaged(
            @RequestParam(value=PAGE_PARAM_STRING, defaultValue="") String page,
            @RequestParam(value=PAGE_SIZE_PARAM_STRING, defaultValue="") String pageSize,
            @RequestParam(value=SORT_PARAM_STRING, defaultValue="true") String sortType,
            @RequestParam(value=SORT_FIELDS, defaultValue="") String sortFields) throws QMeResourceException {
        log(getCurrentUser(), "Quiz  - list");
        Integer     pageNumber      = getPageNumber(page);
        Integer     pageSizeNumber  = getPageSizeNumber(pageSize);
        String[]    sortOrderFields = getSortOrderFields(sortFields);
        boolean     sortAsc         = getSortAsc(sortType);
        List<QMeQuizDetail> qMeQuizDetailList = null;
        if(pageNumber != null && pageSizeNumber != null){
            qMeQuizDetailList = quizService.list(pageNumber, pageSizeNumber,sortAsc,sortOrderFields);
        }else{
            qMeQuizDetailList= quizService.list();
        }
        setQuizLinks(qMeQuizDetailList);
        return qMeQuizDetailList;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public QMeQuizDetail searchById(@PathVariable(ID_PARAM_STRING) Long quizId) throws QMeResourceException {
        log(getCurrentUser(), "User - Search By ID for questionId "+quizId);
        QMeQuizDetail qMeQuizDetail = quizService.searchById(quizId);
        setQuizLinks(qMeQuizDetail);
        return qMeQuizDetail;
    }

    @RequestMapping(value=ROOT_PATH,method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public QMeQuizDetail create(@RequestBody QMeQuizDetail quiz) throws QMeResourceException {
        log(getCurrentUser(), "Quiz  - create");
        QMeQuizDetail qMeQuizDetail =  quizService.save(quiz,getCurrentUser().getUserID());
        setQuizLinks(qMeQuizDetail);
        return qMeQuizDetail;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public QMeQuizDetail update(@PathVariable(ID_PARAM_STRING)Long quizId, @RequestBody QMeQuizDetail quiz) throws QMeResourceException {
        log(getCurrentUser(), "Quiz - update");
        QMeQuizDetail qMeQuizDetail =  quizService.update(quiz,quizId,getCurrentUser().getUserID());
        setQuizLinks(qMeQuizDetail);
        return qMeQuizDetail;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void delete(@PathVariable(ID_PARAM_STRING) Long quizId) throws QMeResourceException {
        log(getCurrentUser(), "Quiz - delete");
        quizService.delete(quizId);
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
