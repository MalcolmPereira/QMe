/**
 * Name      : com.malcolm.qme.rest.controller.CategoryController.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : REST Controller for QMeCategory
 */

package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.CategoryAPI;
import com.malcolm.qme.rest.api.QMeAppAPI;
import com.malcolm.qme.rest.exception.*;
import com.malcolm.qme.rest.model.QMeCategory;
import com.malcolm.qme.rest.model.QMeCategoryDetail;
import com.malcolm.qme.rest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author malcolm
 */
@RestController
public class CategoryController implements CategoryAPI {

    @Autowired
    private String endpointURL;

    /**
     * Category Service
     */
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value=COUNT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
    @Override
    public  @ResponseBody
    Resource<Long> count() throws QMeResourceException {
        log(getCurrentUser(), "Category - count");
        Resource<Long> userCount = new Resource<>(categoryService.count(),new Link(endpointURL+ CategoryAPI.COUNT_PATH.replaceAll(":.+","}")));
        userCount.add(new Link(endpointURL + CategoryAPI.PAGED_PATH.replaceAll(":.+", "}") + "?page=0&pagesize=1&sorttype=true&sortfields=CATEGORYNAME", QMeAppAPI.CATEGORY_PAGED));
        return userCount;
    }

    @RequestMapping(value=ROOT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody List<QMeCategoryDetail> list() throws QMeInvalidResourceDataException,QMeResourceConflictException,QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), "Category - list");
        List<QMeCategoryDetail> categoryDetails = categoryService.list();
        setCategoryLinks(categoryDetails);
        return categoryDetails;
    }

    @RequestMapping(value=ROOT_PATH_WITH_QUESTIONS,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<QMeCategoryDetail> listContainingQuestions() throws QMeResourceException {
        log(getCurrentUser(), "Category - list containing questions");
        List<QMeCategoryDetail> categoryDetails = categoryService.listContainingQuestions();
        setCategoryLinks(categoryDetails);
        return categoryDetails;
    }

    @RequestMapping(value=PAGED_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody List<QMeCategoryDetail> listPaged(
            @RequestParam(value=PAGE_PARAM_STRING, defaultValue="") String page,
            @RequestParam(value=PAGE_SIZE_PARAM_STRING, defaultValue="") String pageSize,
            @RequestParam(value=SORT_PARAM_STRING, defaultValue="true") String sortType,
            @RequestParam(value=SORT_FIELDS, defaultValue="") String sortFields) throws QMeResourceException {

        log(getCurrentUser(), "Category - listPaged");

        Integer     pageNumber      = getPageNumber(page);
        Integer     pageSizeNumber  = getPageSizeNumber(pageSize);
        String[]    sortOrderFields = getSortOrderFields(sortFields);
        boolean     sortAsc         = getSortAsc(sortType);
        List<QMeCategoryDetail> categoryDetails;
        if(pageNumber != null && pageSizeNumber != null){
            categoryDetails = categoryService.list(pageNumber, pageSizeNumber,sortAsc,sortOrderFields);
        }else{
            categoryDetails = categoryService.list();
        }
        setCategoryLinks(categoryDetails);
        return categoryDetails;
    }

    @RequestMapping(value=PARENT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<QMeCategoryDetail> listByParent(@PathVariable(ID_PARAM_STRING) Long categoryId) throws QMeResourceException {
        log(getCurrentUser(), "Category - listByParent");
        List<QMeCategoryDetail> categoryDetails = categoryService.searchByParentCategory(categoryId);
        setCategoryLinks(categoryDetails);
        return categoryDetails;
    }

    @RequestMapping(value=NAME_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody List<QMeCategoryDetail> searchByName(@PathVariable(NAME_PARAM_STRING) String categoryName) throws QMeInvalidResourceDataException,QMeResourceConflictException,QMeResourceNotFoundException,QMeServerException {
       log(getCurrentUser(), "Category - searchByName");
       List<QMeCategoryDetail> categoryDetails = categoryService.searchByName(categoryName);
       setCategoryLinks(categoryDetails);
       return categoryDetails;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeCategoryDetail searchById(@PathVariable(ID_PARAM_STRING) Long categoryId) throws QMeInvalidResourceDataException,QMeResourceConflictException,QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), "Category - searchById");
        QMeCategoryDetail qmeCategoryDetail = categoryService.searchById(categoryId);
        setCategoryLinks(qmeCategoryDetail);
        return qmeCategoryDetail;
    }

    @RequestMapping(value=ROOT_PATH,method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeCategoryDetail create(@RequestBody QMeCategory category) throws QMeResourceNotFoundException,QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        log(getCurrentUser(), "Category - create");
        if(category.getParentCategoryId() != null){
            QMeCategoryDetail qmeCategoryDetail = categoryService.searchById(category.getParentCategoryId());
            if(qmeCategoryDetail == null){
                throw new QMeResourceNotFoundException("Category with Category ID "+category.getParentCategoryId()+" not found");
            }
        }
        QMeCategoryDetail  qmeCategoryDetail = categoryService.save(category,getCurrentUser().getUserID());
        setCategoryLinks(qmeCategoryDetail);
        return qmeCategoryDetail;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeCategoryDetail update(@PathVariable(ID_PARAM_STRING) Long categoryId,@RequestBody QMeCategory category) throws QMeResourceNotFoundException,QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        log(getCurrentUser(), "Category - update");
        QMeCategoryDetail  qmeCategoryDetail =  categoryService.update(category, categoryId,getCurrentUser().getUserID());
        setCategoryLinks(qmeCategoryDetail);
        return qmeCategoryDetail;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void delete(@PathVariable(ID_PARAM_STRING) Long categoryId) throws QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), "Category - delete");
        categoryService.delete(categoryId);
    }

    /**
     * Set Category Links
     * @param qmeCategoryList QMe Category List
     */
    private void setCategoryLinks(List<QMeCategoryDetail> qmeCategoryList){
        qmeCategoryList.stream().forEach(this::setCategoryLinks);
    }

    /**
     * Set Category Links
     * @param qmeCategory QMeCategory
     */
    private void setCategoryLinks(QMeCategoryDetail qmeCategory){
        qmeCategory.add(new Link(endpointURL+ CategoryAPI.ID_PATH.replaceAll("\\{"+ID_PARAM_STRING+"\\}",qmeCategory.getCategoryId()+""),QMeAppAPI.CATEGORY_BY_ID));
        qmeCategory.add(new Link(endpointURL+ CategoryAPI.NAME_PATH.replaceAll("\\{"+NAME_PARAM_STRING+":.+\\}",qmeCategory.getCategoryName()),QMeAppAPI.CATEGORY_BY_NAME));
        qmeCategory.add(new Link(endpointURL+ CategoryAPI.ID_PATH.replaceAll("\\{"+ID_PARAM_STRING+"\\}",qmeCategory.getCategoryId()+""),QMeAppAPI.UPDATE_CATEGORY));
        qmeCategory.add(new Link(endpointURL+ CategoryAPI.ID_PATH.replaceAll("\\{"+ID_PARAM_STRING+"\\}",qmeCategory.getCategoryId()+""),QMeAppAPI.DELETE_CATEGORY));
    }
}