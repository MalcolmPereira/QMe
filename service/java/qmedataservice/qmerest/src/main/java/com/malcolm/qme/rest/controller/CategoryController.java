/**
 * Name      : com.malcolm.qme.rest.controller.CategoryController.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : REST Controller for QMeCategory
 */

package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.CategoryAPI;
import com.malcolm.qme.rest.exception.QMeResourceException;
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
    public @ResponseBody List<QMeCategoryDetail> list() throws QMeResourceException {
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
    public @ResponseBody List<QMeCategoryDetail> searchByName(@PathVariable(NAME_PARAM_STRING) String categoryName) throws QMeResourceException {
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
    public @ResponseBody QMeCategoryDetail searchById(@PathVariable(ID_PARAM_STRING) Long categoryId) throws QMeResourceException {
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
    public @ResponseBody QMeCategoryDetail create(QMeCategory category) throws QMeResourceException {
        //TODO:Add Security and User Id from Principal
        return categoryService.save(category,1L);
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeCategoryDetail update(@PathVariable(ID_PARAM_STRING) Long categoryId,QMeCategory category) throws QMeResourceException {
        //TODO:Add Security and User Id from Principal
        return categoryService.update(category, categoryId,1L);
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void delete(@PathVariable(ID_PARAM_STRING) Long categoryId) throws QMeResourceException {
        categoryService.delete(categoryId);
    }
}
