/**
 * Name      : com.malcolm.qme.rest.api.CategoryAPI.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QMeCategory API Interface which services as a Contract for the REST Service
 */
package com.malcolm.qme.rest.api;

import retrofit.http.*;
import com.malcolm.qme.rest.model.QMeCategory;

import java.util.List;

/**
 * @Author: malcolm
 */
public interface CategoryAPI extends QMeAPI {
    /**
     * QMeCategory API Root Path
     */
    String ROOT_PATH = APP_STRING + "category/";

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
     * @return
     */
    @GET(ROOT_PATH)
    public List<QMeCategory> list();

    /**
     * Search by Name
     *
     * @param categoryName
     * @return
     */
    @GET(NAME_PATH)
    public List<QMeCategory> searchByName(@Path(NAME_PARAM_STRING) String categoryName);

    /**
     * Search by ID
     *
     * @param categoryId
     * @return
     */
    @GET(ID_PATH)
    public QMeCategory searchById(@Path(ID_PARAM_STRING) long categoryId);

    /**
     * Create QMeCategory
     *
     * @param category
     * @return
     */
    @POST(ROOT_PATH)
    public QMeCategory create(@Body QMeCategory category);

    /**
     * Update QMeCategory
     *
     * @param category
     * @return
     */
    @PUT(ROOT_PATH)
    public QMeCategory update(@Body QMeCategory category);


    /**
     * Delete QMeCategory
     *
     * @param categoryId
     */
    @DELETE(ID_PATH)
    public void delete(@Path(ID_PARAM_STRING) long categoryId);

}
