/**
 * Name      : com.malcolm.qme.rest.api.CategoryAPI.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QMe Category API Interface which services as a Contract for the REST Service
 */
package com.malcolm.qme.rest.api;

import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeCategory;
import com.malcolm.qme.rest.model.QMeCategoryDetail;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Malcolm
 */
public interface CategoryAPI extends QMeAPI {
    /**
     * QMeCategory API Root Path
     */
    String ROOT_PATH = APP_STRING + "/category";

    /**
     * QMeCategory API Get By ID Path
     */
    String ID_PATH = ROOT_PATH + "/{" + ID_PARAM_STRING + "}";

    /**
     * QMeCategory API Search By Name
     */
    String NAME_PATH = ROOT_PATH + "/search/{" + NAME_PARAM_STRING + ":.+}";

    /**
     * Get All Categories
     *
     * @return List of Category
     */
    List<QMeCategoryDetail> list() throws QMeResourceException;

    /**
     * Search by Name
     *
     * @param categoryName Category Name
     * @return List of QMe Categories
     */
    List<QMeCategoryDetail> searchByName(@PathVariable(NAME_PARAM_STRING) String categoryName) throws QMeResourceException;

    /**
     * Search by ID
     *
     * @param categoryId Category ID
     * @return QMe Category
     */
    QMeCategoryDetail searchById(@PathVariable(ID_PARAM_STRING) Long categoryId) throws QMeResourceException;

    /**
     * Create QMeCategory
     *
     * @param category Category
     * @return Category
     */
    QMeCategoryDetail create(QMeCategory category) throws QMeResourceException;

    /**
     * Update QMeCategory
     *
     * @param category Category
     * @return Category
     */
    QMeCategoryDetail update(@PathVariable(ID_PARAM_STRING) Long categoryId, QMeCategory category) throws QMeResourceException;

    /**
     * Delete QMeCategory
     *
     * @param categoryId Category ID
     */
    void delete(@PathVariable(ID_PARAM_STRING) Long categoryId) throws QMeResourceException;

}
