/**
 * Name      : com.malcolm.qme.core.domain.Category.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : TODO:
 */
package com.malcolm.qme.core.domain;

import java.util.Date;

/**
 * Created by Malcolm on 5/1/2015.
 */
public final class Category {
    /**
     * Category Id
     */
    private final long categoryID;

    /**
     * Category Parent Id
     */
    private final long categoryParentID;

    /**
     * Category Name
     */
    private final String categoryName;

    /**
     * Category Create Date
     */
    private final Date categoryCreateDate;

    /**
     * Category Create User Id
     */
    private final long  categoryCreateUserID;

    /**
     * Public Constructor
     * @param categoryID
     * @param categoryParentID
     * @param categoryName
     * @param categoryCreateDate
     * @param categoryCreateUserID
     */
    public Category(long categoryID, long categoryParentID, String categoryName, Date categoryCreateDate, long categoryCreateUserID) {
        this.categoryID             = categoryID;
        this.categoryParentID       = categoryParentID;
        this.categoryName           = categoryName;
        this.categoryCreateDate     = categoryCreateDate;
        this.categoryCreateUserID   = categoryCreateUserID;
    }

    /**
     * Public Constructor

     * @param categoryParentID
     * @param categoryName
     * @param categoryCreateUserID
     */
    public Category(long categoryParentID, String categoryName, long categoryCreateUserID) {
        this.categoryID             = 0;
        this.categoryParentID       = categoryParentID;
        this.categoryName           = categoryName;
        this.categoryCreateUserID   = categoryCreateUserID;
        this.categoryCreateDate     = null;
    }

    /**
     * Public Constructor

     * @param categoryName
     * @param categoryCreateUserID
     */
    public Category(String categoryName, long categoryCreateUserID) {
        this.categoryID             = 0;
        this.categoryParentID       = 0;
        this.categoryName           = categoryName;
        this.categoryCreateUserID   = categoryCreateUserID;
        this.categoryCreateDate     = null;
    }

    /**
     * Get Category Id
     * @return
     */
    public long getCategoryID() {
        return categoryID;
    }

    /**
     * Get Category Parent Id
     * @return
     */
    public long getCategoryParentID() {
        return categoryParentID;
    }

    /**
     * Get Category Name
     * @return
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Get Category Create Date
     * @return
     */
    public Date getCategoryCreateDate() {
        return categoryCreateDate;
    }

    /**
     * Get Category Create User Id
     * @return
     */
    public long getCategoryCreateUserID() {
        return categoryCreateUserID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (getCategoryID() != category.getCategoryID()) return false;
        if (getCategoryParentID() != category.getCategoryParentID()) return false;
        if (getCategoryCreateUserID() != category.getCategoryCreateUserID()) return false;
        if (!getCategoryName().equals(category.getCategoryName())) return false;
        return getCategoryCreateDate().equals(category.getCategoryCreateDate());

    }

    @Override
    public int hashCode() {
        int result = (int) (getCategoryID() ^ (getCategoryID() >>> 32));
        result = 31 * result + (int) (getCategoryParentID() ^ (getCategoryParentID() >>> 32));
        result = 31 * result + getCategoryName().hashCode();
        result = 31 * result + getCategoryCreateDate().hashCode();
        result = 31 * result + (int) (getCategoryCreateUserID() ^ (getCategoryCreateUserID() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", categoryParentID=" + categoryParentID +
                ", categoryName='" + categoryName + '\'' +
                ", categoryCreateDate=" + categoryCreateDate +
                ", categoryCreateUserID=" + categoryCreateUserID +
                '}';
    }
}
