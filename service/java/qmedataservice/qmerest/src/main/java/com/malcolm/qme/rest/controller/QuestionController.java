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
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
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
        log(getCurrentUser(), "Question - count");
        Resource<Long> questionCount = new Resource<>(questionService.count(),new Link(endpointURL+ QuestionAPI.COUNT_PATH.replaceAll(":.+","}")));
        questionCount.add(new Link(endpointURL + QuestionAPI.PAGED_PATH.replaceAll(":.+", "}") + "?page=0&pagesize=1&sorttype=true&sortfields=QUESTION", QMeAppAPI.QUESTION_PAGED));
        return questionCount;
    }

    @RequestMapping(value=ROOT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public @ResponseBody List<QMeQuestionDetail> list() throws QMeResourceException {
        log(getCurrentUser(), "Question - list");
        List<QMeQuestionDetail> qMeQuestionDetailList = questionService.list();
        setQuestionLinks(qMeQuestionDetailList);
        return qMeQuestionDetailList;
    }

    @RequestMapping(value=ROOT_PATH_BY_CATID,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public @ResponseBody List<QMeQuestionDetail> list(@PathVariable(ID_PARAM_STRING) Long categoryId) throws QMeResourceException {
        log(getCurrentUser(), "Question - list by category id");
    List<QMeQuestionDetail> qMeQuestionDetailList = questionService.list(categoryId);
        setQuestionLinks(qMeQuestionDetailList);
        return qMeQuestionDetailList;
    }


    @RequestMapping(value=PAGED_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public List<QMeQuestionDetail> listPaged(
            @RequestParam(value=PAGE_PARAM_STRING, defaultValue="") String page,
            @RequestParam(value=PAGE_SIZE_PARAM_STRING, defaultValue="") String pageSize,
            @RequestParam(value=SORT_PARAM_STRING, defaultValue="true") String sortType,
            @RequestParam(value=SORT_FIELDS, defaultValue="") String sortFields) throws QMeResourceException {
        log(getCurrentUser(), "Question - list");
        Integer     pageNumber      = getPageNumber(page);
        Integer     pageSizeNumber  = getPageSizeNumber(pageSize);
        String[]    sortOrderFields = getSortOrderFields(sortFields);
        boolean     sortAsc         = getSortAsc(sortType);
        List<QMeQuestionDetail> qMeQuestionDetailList;
        if(pageNumber != null && pageSizeNumber != null){
            qMeQuestionDetailList = questionService.list(pageNumber, pageSizeNumber,sortAsc,sortOrderFields);
        }else{
            qMeQuestionDetailList= questionService.list();
        }
        setQuestionLinks(qMeQuestionDetailList);
        return qMeQuestionDetailList;
    }

    @RequestMapping(value=PAGED_PATH_BY_CATID,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public List<QMeQuestionDetail> listPaged(
            @PathVariable(ID_PARAM_STRING) Long categoryId,
            @RequestParam(value=PAGE_PARAM_STRING, defaultValue="") String page,
            @RequestParam(value=PAGE_SIZE_PARAM_STRING, defaultValue="") String pageSize,
            @RequestParam(value=SORT_PARAM_STRING, defaultValue="true") String sortType,
            @RequestParam(value=SORT_FIELDS, defaultValue="") String sortFields) throws QMeResourceException {
        log(getCurrentUser(), "Question - listPaged by Category Id");
        Integer     pageNumber      = getPageNumber(page);
        Integer     pageSizeNumber  = getPageSizeNumber(pageSize);
        String[]    sortOrderFields = getSortOrderFields(sortFields);
        boolean     sortAsc         = getSortAsc(sortType);
        List<QMeQuestionDetail> qMeQuestionDetailList;
        if(pageNumber != null && pageSizeNumber != null){
            qMeQuestionDetailList = questionService.list(categoryId,pageNumber, pageSizeNumber,sortAsc,sortOrderFields);
        }else{
            qMeQuestionDetailList= questionService.list(categoryId);
        }
        setQuestionLinks(qMeQuestionDetailList);
        return qMeQuestionDetailList;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public @ResponseBody QMeQuestionDetail searchById(@PathVariable(ID_PARAM_STRING) Long questionId) throws QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), "User - Search By ID for questionId "+questionId);
        QMeQuestionDetail qMeQuestionDetail = questionService.searchById(questionId);
        setQuestionLinks(qMeQuestionDetail);
        return qMeQuestionDetail;
    }

    @RequestMapping(value=ROOT_PATH,method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public QMeQuestionDetail create(@RequestBody QMeQuestionDetail question) throws QMeResourceException {
        log(getCurrentUser(), "Question - create");
        QMeQuestionDetail  qMeQuestionDetail = questionService.save(question,getCurrentUser().getUserID());
        setQuestionLinks(qMeQuestionDetail);
        return qMeQuestionDetail;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public QMeQuestionDetail update(@PathVariable(ID_PARAM_STRING) Long questionId,@RequestBody QMeQuestionDetail question) throws QMeResourceException {
        log(getCurrentUser(), "Question - update");
        QMeQuestionDetail  qMeQuestionDetail =  questionService.update(question, questionId,getCurrentUser().getUserID());
        setQuestionLinks(qMeQuestionDetail);
        return qMeQuestionDetail;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void delete(@PathVariable(ID_PARAM_STRING) Long questionId) throws QMeResourceException {
        log(getCurrentUser(), "Question - delete");
        questionService.delete(questionId);
    }

    /**
    * Set Question Links
    * @param qmeQuestionList QMe Question List
    */
    private void setQuestionLinks(List<QMeQuestionDetail> qmeQuestionList){
        qmeQuestionList.stream().forEach(this::setQuestionLinks);
    }

    /**
     * Set Question Links
     * @param qMeQuestionDetail QMeQuestionDetail
     */
    private void setQuestionLinks(QMeQuestionDetail qMeQuestionDetail){
        qMeQuestionDetail.add(new Link(endpointURL+ QuestionAPI.ID_PATH.replaceAll("\\{"+ID_PARAM_STRING+"\\}",qMeQuestionDetail.getQuestionId()+""),QMeAppAPI.QUESTION_BY_ID));
        qMeQuestionDetail.add(new Link(endpointURL+ QuestionAPI.ID_PATH.replaceAll("\\{"+ID_PARAM_STRING+"\\}",qMeQuestionDetail.getQuestionId()+""),QMeAppAPI.UPDATE_QUESTION));
        qMeQuestionDetail.add(new Link(endpointURL+ QuestionAPI.ID_PATH.replaceAll("\\{"+ID_PARAM_STRING+"\\}",qMeQuestionDetail.getQuestionId()+""),QMeAppAPI.DELETE_QUESTION));
    }
}

