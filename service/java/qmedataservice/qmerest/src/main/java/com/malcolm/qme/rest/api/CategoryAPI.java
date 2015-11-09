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
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Malcolm
 */
@SuppressWarnings("ALL")
public interface CategoryAPI extends QMeAPI {
    /**
     * QMeCategory API Root Path
     */
    String ROOT_PATH = APP_STRING + "/category";

    /**
     * QMeCategory API Resource Count
     */
    String COUNT_PATH = APP_STRING + "/category/count";

    /**
     * QMeCategory API Paged Path
     */
    String PAGED_PATH = APP_STRING + "/category/paged";

    /**
     * QMeCategory API Get By ID Path
     */
    String ID_PATH = ROOT_PATH + "/{" + ID_PARAM_STRING + "}";

    /**
     * QMeCategory API Search By Name
     */
    String NAME_PATH = ROOT_PATH + "/search/{" + NAME_PARAM_STRING + ":.+}";

    /**
     * Get Count
     *
     * @return Count of Catgeories
     */
    Resource<Long> count() throws QMeResourceException;

    /**
     * Get All Categories
     *
     * @return List of Category
     */
    List<QMeCategoryDetail> list() throws QMeResourceException;

    /**
     * Get All Categories with Pagination and Sorting
     * @param page Page
     * @param pageSize Page Size
     * @param sortType Sort Type
     * @param sortFields Sort Fields
     * @return List of Category
     * @throws QMeResourceException
     */
    List<QMeCategoryDetail> listPaged(String page,String pageSize, String sortType, String sortFields) throws QMeResourceException;

    /**
     * Search by Name
     *
     * @param categoryName Category Name
     * @return List of QMe Categories
     */
    List<QMeCategoryDetail> searchByName(String categoryName) throws QMeResourceException;

    /**
     * Search by ID
     *
     * @param categoryId Category ID
     * @return QMe Category
     */
    QMeCategoryDetail searchById(Long categoryId) throws QMeResourceException;

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
    QMeCategoryDetail update(Long categoryId, QMeCategory category) throws QMeResourceException;

    /**
     * Delete QMeCategory
     *
     * @param categoryId Category ID
     */
    void delete(Long categoryId) throws QMeResourceException;

}
