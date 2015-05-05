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
     * Category Likes
     */
    private final Long categoryLikes;

    /**
     * Public Constructor
     *
     * @param categoryID
     * @param categoryParentID
     * @param categoryName
     * @param categoryLikes
     * @param categoryCreateDate
     * @param categoryCreateUserID
     */
    public Category(Long categoryID, Long categoryParentID, String categoryName,Long categoryLikes, Date categoryCreateDate, Long categoryCreateUserID) {
        this.categoryID = categoryID;
        this.categoryParentID = categoryParentID;
        this.categoryName = categoryName;
        this.categoryLikes = categoryLikes;
        this.categoryCreateDate = categoryCreateDate;
        this.categoryCreateUserID = categoryCreateUserID;
    }

    /**
     * Public Constructor
     *
     * @param categoryParentID
     * @param categoryName
     * @param categoryCreateUserID
     */
    public Category(Long categoryParentID, String categoryName, Long categoryCreateUserID) {
        this.categoryID = 0L;
        this.categoryParentID = categoryParentID;
        this.categoryName = categoryName;
        this.categoryLikes = 0L;
        this.categoryCreateDate = null;
        this.categoryCreateUserID = categoryCreateUserID;
    }

    /**
     * Public Constructor
     *
     * @param categoryName
     * @param categoryCreateUserID
     */
    public Category(String categoryName, Long categoryCreateUserID) {
        this.categoryID = 0L;
        this.categoryParentID = 0L;
        this.categoryName = categoryName;
        this.categoryLikes = 0L;
        this.categoryCreateDate = null;
        this.categoryCreateUserID = categoryCreateUserID;
    }

    /**
     * Get Category Id
     * @return
     */
    public Long getCategoryID() {
        return categoryID;
    }

    /**
     * Get Category Parent Id
     * @return
     */
    public Long getCategoryParentID() {
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
    public Long getCategoryCreateUserID() {
        return categoryCreateUserID;
    }
    
    /**
     * Get Category Likes
     * 
     * @return
     */
    public Long getCategoryLikes() {
		return categoryLikes;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (!getCategoryID().equals(category.getCategoryID())) return false;
        if (getCategoryParentID() != null ? !getCategoryParentID().equals(category.getCategoryParentID()) : category.getCategoryParentID() != null)
            return false;
        if (!getCategoryName().equals(category.getCategoryName())) return false;
        if (getCategoryCreateDate() != null ? !getCategoryCreateDate().equals(category.getCategoryCreateDate()) : category.getCategoryCreateDate() != null)
            return false;
        return !(getCategoryCreateUserID() != null ? !getCategoryCreateUserID().equals(category.getCategoryCreateUserID()) : category.getCategoryCreateUserID() != null);

    }

    @Override
    public int hashCode() {
        int result = getCategoryID().hashCode();
        result = 31 * result + (getCategoryParentID() != null ? getCategoryParentID().hashCode() : 0);
        result = 31 * result + getCategoryName().hashCode();
        result = 31 * result + (getCategoryCreateDate() != null ? getCategoryCreateDate().hashCode() : 0);
        result = 31 * result + (getCategoryCreateUserID() != null ? getCategoryCreateUserID().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", categoryParentID=" + categoryParentID +
                ", categoryName='" + categoryName + '\'' +
                 ",categoryLikes='" + categoryLikes + '\'' +
                ", categoryCreateDate=" + categoryCreateDate +
                ", categoryCreateUserID=" + categoryCreateUserID +
                '}';
    }
}
