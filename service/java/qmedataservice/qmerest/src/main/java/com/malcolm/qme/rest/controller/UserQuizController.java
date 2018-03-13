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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @RequestMapping(value=PAGED_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+USER_ROLE+"')")
    @Override
    public @ResponseBody
    List<QMeUserQuiz> listPaged(@RequestParam(value=PAGE_PARAM_STRING, defaultValue="") String page,
            @RequestParam(value=PAGE_SIZE_PARAM_STRING, defaultValue="") String pageSize,
            @RequestParam(value=SORT_PARAM_STRING, defaultValue="true") String sortType,
            @RequestParam(value=SORT_FIELDS, defaultValue="") String sortFields) throws QMeResourceException {
        log(getCurrentUser(), "User Quiz - listPaged");

        Integer     pageNumber      = getPageNumber(page);
        Integer     pageSizeNumber  = getPageSizeNumber(pageSize);
        String[]    sortOrderFields = getSortOrderFields(sortFields);
        boolean     sortAsc         = getSortAsc(sortType);
        List<QMeUserQuiz> qMeUserQuizList;
        if(pageNumber != null && pageSizeNumber != null){
            qMeUserQuizList = userQuizService.list(pageNumber, pageSizeNumber,sortAsc,sortOrderFields);
        }else{
            qMeUserQuizList= userQuizService.list();
        }
        setUserQuizLinks(qMeUserQuizList);
        return qMeUserQuizList;
    }

    @RequestMapping(value=QUIZ_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+USER_ROLE+"')")
    @Override
    public @ResponseBody List<QMeUserQuiz> listQuizzes(@RequestParam(value=PAGE_PARAM_STRING, defaultValue="") String page,
                                         @RequestParam(value=PAGE_SIZE_PARAM_STRING, defaultValue="") String pageSize,
                                         @RequestParam(value=SORT_PARAM_STRING, defaultValue="true") String sortType,
                                         @RequestParam(value=SORT_FIELDS, defaultValue="") String sortFields) throws QMeResourceException {
        log(getCurrentUser(), "User Quiz - listQuizzes");
        Integer     pageNumber      = getPageNumber(page);
        Integer     pageSizeNumber  = getPageSizeNumber(pageSize);
        String[]    sortOrderFields = getSortOrderFields(sortFields);
        boolean     sortAsc         = getSortAsc(sortType);
        List<QMeUserQuiz> qMeUserQuizList;
        if(pageNumber != null && pageSizeNumber != null){
            qMeUserQuizList = userQuizService.findQuizzesForUser(getCurrentUser().getUserID(),pageNumber, pageSizeNumber,sortAsc,sortOrderFields);
            setUserQuizLinks(qMeUserQuizList);
        }else{
            qMeUserQuizList = new ArrayList<>();
        }
        return qMeUserQuizList;
    }

    @RequestMapping(value=QUIZ_PATH_PENDING,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+USER_ROLE+"')")
    @Override
    public @ResponseBody List<QMeUserQuiz> listQuizzesPending(@RequestParam(value=PAGE_PARAM_STRING, defaultValue="") String page,
                                                @RequestParam(value=PAGE_SIZE_PARAM_STRING, defaultValue="") String pageSize,
                                                @RequestParam(value=SORT_PARAM_STRING, defaultValue="true") String sortType,
                                                @RequestParam(value=SORT_FIELDS, defaultValue="") String sortFields) throws QMeResourceException {
        log(getCurrentUser(), "User Quiz - listQuizzesPending");
        Integer     pageNumber      = getPageNumber(page);
        Integer     pageSizeNumber  = getPageSizeNumber(pageSize);
        String[]    sortOrderFields = getSortOrderFields(sortFields);
        boolean     sortAsc         = getSortAsc(sortType);
        List<QMeUserQuiz> qMeUserQuizList;
        if(pageNumber != null && pageSizeNumber != null){
            qMeUserQuizList = userQuizService.findPendingByUserId(getCurrentUser().getUserID(),pageNumber, pageSizeNumber,sortAsc,sortOrderFields);
            setUserQuizLinks(qMeUserQuizList);
        }else{
            qMeUserQuizList = new ArrayList<>();
        }
        return qMeUserQuizList;
    }

    @RequestMapping(value=QUIZ_PATH_COMPLETED,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+USER_ROLE+"')")
    @Override
    public @ResponseBody List<QMeUserQuiz> listQuizzesCompleted(@RequestParam(value=PAGE_PARAM_STRING, defaultValue="") String page,
                                                  @RequestParam(value=PAGE_SIZE_PARAM_STRING, defaultValue="") String pageSize,
                                                  @RequestParam(value=SORT_PARAM_STRING, defaultValue="true") String sortType,
                                                  @RequestParam(value=SORT_FIELDS, defaultValue="") String sortFields) throws QMeResourceException {
        log(getCurrentUser(), "User Quiz - listQuizzesCompleted");
        Integer     pageNumber      = getPageNumber(page);
        Integer     pageSizeNumber  = getPageSizeNumber(pageSize);
        String[]    sortOrderFields = getSortOrderFields(sortFields);
        boolean     sortAsc         = getSortAsc(sortType);
        List<QMeUserQuiz> qMeUserQuizList;
        if(pageNumber != null && pageSizeNumber != null){
            qMeUserQuizList = userQuizService.findCompletedByUserId(getCurrentUser().getUserID(),pageNumber, pageSizeNumber,sortAsc,sortOrderFields);
            setUserQuizLinks(qMeUserQuizList);
        }else{
            qMeUserQuizList = new ArrayList<>();
        }
        return qMeUserQuizList;
    }

    @Override
    public QMeUserQuiz registerForQuiz(Long userID, Long quizID) throws QMeResourceException {
        return null;
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
