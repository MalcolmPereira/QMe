/**
 * Name      : com.malcolm.qme.rest.api.CategoryAPI.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QMeCategory API Interface which services as a Contract for the REST Service
 */
package com.malcolm.qme.rest.api;

import com.malcolm.qme.rest.model.QMeCategoryDetail;
import com.malcolm.qme.rest.service.QMeResourceException;
import retrofit.http.*;
import com.malcolm.qme.rest.model.QMeCategory;

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
    String NAME_PATH = ROOT_PATH + "/search/{" + NAME_PARAM_STRING + "}";

    /**
     * Get All Categories
     *
     * @return List of Category
     */
    @GET(ROOT_PATH)
    public List<QMeCategoryDetail> list() throws QMeResourceException;

    /**
     * Search by Name
     *
     * @param categoryName Category Name
     * @return List of QMe Categories
     */
    @GET(NAME_PATH)
    public List<QMeCategoryDetail> searchByName(@Path(NAME_PARAM_STRING) String categoryName) throws QMeResourceException;

    /**
     * Search by ID
     *
     * @param categoryId Category ID
     * @return QMe Category
     */
    @GET(ID_PATH)
    public QMeCategoryDetail searchById(@Path(ID_PARAM_STRING) Long categoryId) throws QMeResourceException;

    /**
     * Create QMeCategory
     *
     * @param category Category
     * @return Category
     */
    @POST(ROOT_PATH)
    public QMeCategoryDetail create(@Body QMeCategory category) throws QMeResourceException;

    /**
     * Update QMeCategory
     *
     * @param category Category
     * @return Category
     */
    @PUT(ID_PATH)
    public QMeCategoryDetail update(@Path(ID_PARAM_STRING) Long categoryId, @Body QMeCategory category) throws QMeResourceException;

    /**
     * Delete QMeCategory
     *
     * @param categoryId Category ID
     */
    @DELETE(ID_PATH)
    public void delete(@Path(ID_PARAM_STRING) Long categoryId) throws QMeResourceException;

}
