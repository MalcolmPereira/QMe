/**
 * Name      : com.malcolm.qme.core.domain.Category.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe Category Domain Class
 */
package com.malcolm.qme.core.domain;

import java.util.Date;

/**
 * @Author Malcolm
 */
public final class Category {
    /**
     * Category Id
     */
    private final Long categoryID;

    /**
     * Category Parent Id
     */
    private final Long categoryParentID;

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
    private final Long  categoryCreateUserID;

    /**
     * Public Constructor
     * @param categoryID
     * @param categoryParentID
     * @param categoryName
     * @param categoryCreateDate
     * @param categoryCreateUserID
     */
    public Category(Long categoryID, Long categoryParentID, String categoryName, Date categoryCreateDate, Long categoryCreateUserID) {
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
    public Category(Long categoryParentID, String categoryName, long categoryCreateUserID) {
        this.categoryID             = 0L;
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
    public Category(String categoryName, Long categoryCreateUserID) {
        this.categoryID             = 0L;
        this.categoryParentID       = 0L;
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

        if (!categoryID.equals(category.categoryID)) return false;
        if (categoryParentID != null ? !categoryParentID.equals(category.categoryParentID) : category.categoryParentID != null)
            return false;
        if (!categoryName.equals(category.categoryName)) return false;
        if (categoryCreateDate != null ? !categoryCreateDate.equals(category.categoryCreateDate) : category.categoryCreateDate != null)
            return false;
        return !(categoryCreateUserID != null ? !categoryCreateUserID.equals(category.categoryCreateUserID) : category.categoryCreateUserID != null);

    }

    @Override
    public int hashCode() {
        int result = categoryID.hashCode();
        result = 31 * result + (categoryParentID != null ? categoryParentID.hashCode() : 0);
        result = 31 * result + categoryName.hashCode();
        result = 31 * result + (categoryCreateDate != null ? categoryCreateDate.hashCode() : 0);
        result = 31 * result + (categoryCreateUserID != null ? categoryCreateUserID.hashCode() : 0);
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
