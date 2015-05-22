/**
 * Name      : com.malcolm.qme.core.domain.Category.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe Category Domain Class
 */
package com.malcolm.qme.core.domain;

import java.time.LocalDateTime;

/**
 * @author Malcolm
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
    private final LocalDateTime categoryCreateDate;

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
     * @param categoryID Category ID
     * @param categoryParentID Parent Category ID
     * @param categoryName Category Name
     * @param categoryLikes Category Likes
     * @param categoryCreateDate Category Create Date
     * @param categoryCreateUserID Category Create User ID
     */
    public Category(Long categoryID, Long categoryParentID, String categoryName,Long categoryLikes, LocalDateTime categoryCreateDate, Long categoryCreateUserID) {
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
     * @param categoryParentID Parent Category ID
     * @param categoryName Category Name
     * @param categoryCreateUserID Category Create User ID
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
     * @param categoryName Category Name
     * @param categoryCreateUserID Category Create User ID
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
     * @return Category Id
     */
    public Long getCategoryID() {
        return categoryID;
    }

    /**
     * Get Category Parent Id
     * @return Category Parent Id
     */
    public Long getCategoryParentID() {
        return categoryParentID;
    }

    /**
     * Get Category Name
     * @return Category Name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Get Category Create Date
     * @return Category Create Date
     */
    public LocalDateTime getCategoryCreateDate() {
        return categoryCreateDate;
    }

    /**
     * Get Category Create User Id
     * @return Category Create User Id
     */
    public Long getCategoryCreateUserID() {
        return categoryCreateUserID;
    }
    
    /**
     * Get Category Likes
     * 
     * @return Category Likes
     */
    public Long getCategoryLikes() {
		return categoryLikes;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return getCategoryID().equals(category.getCategoryID()) &&
               !(getCategoryParentID() != null ? !getCategoryParentID().equals(category.getCategoryParentID()) : category.getCategoryParentID() != null) &&
                getCategoryName().equals(category.getCategoryName()) && !(getCategoryCreateDate() != null ? !getCategoryCreateDate().equals(category.getCategoryCreateDate()) : category.getCategoryCreateDate() != null) &&
                !(getCategoryCreateUserID() != null ? !getCategoryCreateUserID().equals(category.getCategoryCreateUserID()) : category.getCategoryCreateUserID() != null);

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
