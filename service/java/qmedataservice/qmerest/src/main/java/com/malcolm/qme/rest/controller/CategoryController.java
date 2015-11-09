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
        for (QMeCategoryDetail qmeCategoryDetail : categoryDetails) {
            qmeCategoryDetail.add(linkTo(methodOn(CategoryController.class).searchByName(qmeCategoryDetail.getCategoryName())).withSelfRel());
            qmeCategoryDetail.add(linkTo(methodOn(CategoryController.class).searchById(qmeCategoryDetail.getCategoryId())).withSelfRel());
        }
        return categoryDetails;
    }

    @RequestMapping(value=PAGED_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('"+ADMIN_ROLE+"')")
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
        List<QMeCategoryDetail> qMeUserDetailList;
        if(pageNumber != null && pageSizeNumber != null){
            qMeUserDetailList = categoryService.list(pageNumber, pageSizeNumber,sortAsc,sortOrderFields);
        }else{
            qMeUserDetailList= categoryService.list();
        }
        return qMeUserDetailList;
    }

            @RequestMapping(value=NAME_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody List<QMeCategoryDetail> searchByName(@PathVariable(NAME_PARAM_STRING) String categoryName) throws QMeInvalidResourceDataException,QMeResourceConflictException,QMeResourceNotFoundException,QMeServerException {
       log(getCurrentUser(), "Category - searchByName");

       List<QMeCategoryDetail> categoryDetails = categoryService.searchByName(categoryName);
       for (QMeCategoryDetail qmeCategoryDetail : categoryDetails) {
            qmeCategoryDetail.add(linkTo(methodOn(CategoryController.class).searchByName(qmeCategoryDetail.getCategoryName())).withSelfRel());
            qmeCategoryDetail.add(linkTo(methodOn(CategoryController.class).searchById(qmeCategoryDetail.getCategoryId())).withSelfRel());
       }
       return categoryDetails;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeCategoryDetail searchById(@PathVariable(ID_PARAM_STRING) Long categoryId) throws QMeInvalidResourceDataException,QMeResourceConflictException,QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), "Category - searchById");

        QMeCategoryDetail qmeCategoryDetail = categoryService.searchById(categoryId);
        qmeCategoryDetail.add(linkTo(methodOn(CategoryController.class).searchByName(qmeCategoryDetail.getCategoryName())).withSelfRel());
        qmeCategoryDetail.add(linkTo(methodOn(CategoryController.class).searchById(qmeCategoryDetail.getCategoryId())).withSelfRel());
        qmeCategoryDetail.add(linkTo(methodOn(CategoryController.class).create(null)).withSelfRel());
        qmeCategoryDetail.add(linkTo(methodOn(CategoryController.class).update(qmeCategoryDetail.getCategoryId(), null)).withSelfRel());

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
        return categoryService.save(category,getCurrentUser().getUserID());
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeCategoryDetail update(@PathVariable(ID_PARAM_STRING) Long categoryId,@RequestBody QMeCategory category) throws QMeResourceNotFoundException,QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        log(getCurrentUser(), "Category - update");
        return categoryService.update(category, categoryId,getCurrentUser().getUserID());
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void delete(@PathVariable(ID_PARAM_STRING) Long categoryId) throws QMeResourceNotFoundException,QMeServerException {
        log(getCurrentUser(), "Category - delete");
        categoryService.delete(categoryId);
    }
}