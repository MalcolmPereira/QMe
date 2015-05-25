/**
 * Name      : com.malcolm.qme.rest.controller.CategoryController.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : REST Controller for QMeCategory
 */

package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.CategoryAPI;
import com.malcolm.qme.rest.model.QMeCategory;
import com.malcolm.qme.rest.model.QMeCategoryDetail;
import com.malcolm.qme.rest.service.CategoryService;
import com.malcolm.qme.rest.service.QMeResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return categoryService.list();
    }

    @RequestMapping(value=NAME_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody List<QMeCategoryDetail> searchByName(@PathVariable(NAME_PARAM_STRING) String categoryName) throws QMeResourceException {
       return categoryService.searchByName(categoryName);
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeCategoryDetail searchById(@PathVariable(ID_PARAM_STRING) Long categoryId) throws QMeResourceException {
        return categoryService.searchById(categoryId);
    }

    @RequestMapping(value=ROOT_PATH,method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeCategoryDetail create(QMeCategory category) throws QMeResourceException {
        //TODO:Add Security and User Id from Principal
        return categoryService.save(category,1L);
    }

    @RequestMapping(value=ROOT_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeCategoryDetail update(@PathVariable(ID_PARAM_STRING) Long categoryId,QMeCategory category) throws QMeResourceException {
        return categoryService.update(category, categoryId,1L);
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void delete(@PathVariable(ID_PARAM_STRING) Long categoryId) throws QMeResourceException {
        categoryService.delete(categoryId);
    }
}
