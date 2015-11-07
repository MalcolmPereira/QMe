/**
 * Name      : com.malcolm.qme.rest.controller.CategoryController.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : REST Controller for QMeCategory
 */

package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.CategoryAPI;
import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeCategory;
import com.malcolm.qme.rest.model.QMeCategoryDetail;
import com.malcolm.qme.rest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author malcolm
 */
@RestController
public class CategoryController implements CategoryAPI {

    /**
     * Category Service
     */
    @Autowired
    private CategoryService categoryService;

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