/**
 * Name      : com.malcolm.qme.rest.controller.CategoryController.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : REST Controller for QMeCategory
 */

package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.CategoryAPI;
import com.malcolm.qme.rest.model.QMeCategory;
import com.malcolm.qme.rest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * @author malcolm
 */
@Controller
@EnableAutoConfiguration
public class CategoryController implements CategoryAPI {

    /**
     * Category Service
     */
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value=ROOT_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody List<QMeCategory> list() {
        return categoryService.list();
    }

    @RequestMapping(value=NAME_PATH,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody List<QMeCategory> searchByName(String categoryName) {
        return null;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeCategory searchById(long categoryId) {
        return null;
    }

    @RequestMapping(value=ROOT_PATH,method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeCategory create(QMeCategory category) {
        return null;
    }

    @RequestMapping(value=ROOT_PATH,method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public @ResponseBody QMeCategory update(QMeCategory category) {
        return null;
    }

    @RequestMapping(value=ID_PATH,method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void delete(long categoryId) {
    }

}