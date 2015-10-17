/**
 * Name      : com.malcolm.qme.core.repository.QMeRepository.java
 * Date      : 10/16/15
 * Developer : Malcolm
 * Purpose   : QMe Paging and Sorting Info Deatails
 */
package com.malcolm.qme.core.repository;

/**
 * Created by Malcolm on 10/16/2015.
 */
public class PageSort {
   /**
     * Start at Index
     */
    private final Integer pageIndex;

    /**
     * Max number of Records Per Page
     */
    private final Integer maxRows;
    /**
     * Sort Ascending
     */
    private final Boolean sortAsc;

    /**
     * Sort Fields
     */
    private final String[] sortFields;

    /**
     * Public Constructor
     *
     * @param pageIndex Page index
     * @param maxRows Max Rows per page
     * @param sortAsc Sort Ascending
     * @param sortFields Sort Field
     */
    public PageSort(Integer pageIndex, Integer maxRows, Boolean sortAsc, String... sortFields) {
        this.pageIndex = pageIndex;
        this.maxRows = maxRows;
        this.sortAsc = sortAsc;
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
     * @return Sort Ascending
     */
    public Boolean getSort() {
        return sortAsc;
    }

    /**
     * Get Sort Fields
     * @return Sort Fields
     */
    public String[] getSortFields() {
        return sortFields;
    }
}
