/**
 * Name      : com.malcolm.qme.core.repository.QMeRepository.java
 * Date      : 10/16/15
 * Developer : Malcolm
 * Purpose   : QMe Paging and Sorting Info Deatails
 */
package com.malcolm.qme.core.repository;

import java.util.List;

/**
 * Created by Malcolm on 10/16/2015.
 */
public class PageSort {
    /**
     * Enum for Sort Direction
     */
    public enum SORT{
        ASC,
        DSC
        ;
    }
    /**
     * Sort Type
     */
    private final SORT sort;

    /**
     * Start at Index
     */
    private final Integer pageIndex;

    /**
     * Max number of Records Per Page
     */
    private final Integer maxRows;

    /**
     * Sort Fields
     */
    private final List<String> sortFields;

    /**
     * Public Constructor
     *
     * @param pageIndex Page index
     * @param maxRows Max Rows per page
     * @param sort Sort Type
     * @param sortFields Sort Field
     */
    public PageSort(Integer pageIndex, Integer maxRows, SORT sort, List<String> sortFields) {
        this.pageIndex = pageIndex;
        this.maxRows = maxRows;
        this.sort = sort;
        this.sortFields = sortFields;
    }

   /**
     * Get Page Index
     * @return Page Index
     */
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     * Get Max Rows Per Page
     * @return Max Rows Per Page
     */
    public Integer getMaxRows() {
        return maxRows;
    }

    /**
     * Get Sort
     * @return Sort Type
     */
    public SORT getSort() {
        return sort;
    }

    /**
     * Get Sort Fields
     * @return Sort Fields
     */
    public List<String> getSortFields() {
        return sortFields;
    }
}
