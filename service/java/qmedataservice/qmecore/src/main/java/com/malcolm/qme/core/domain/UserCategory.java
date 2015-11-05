/**
 * Name      : com.malcolm.qme.core.domain.UserCategory.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe User Category Domain Class
 */
package com.malcolm.qme.core.domain;

import java.util.Objects;

/**
 * @author Malcolm
 */
public final class UserCategory {

    /**
     * User Category Id
     */
    private final Long userCategoryID;

    /**
     * User Id
     */
    private final Long userID;

    /**
     * Category Id
     */
    private final Long categoryID;

    /**
     * Public Constructor
     *
     * @param userCategoryID User Category ID
     * @param userID User ID
     * @param categoryID Category ID
     */
    public UserCategory(Long userCategoryID, Long userID, Long categoryID) {
        this.userCategoryID = userCategoryID;
        this.userID = userID;
        this.categoryID = categoryID;
    }

    /**
     * Public Constructor
     *

     * @param userID  User ID
     * @param categoryID Category ID
     */
    public UserCategory(Long userID, Long categoryID) {
        this.userCategoryID = 0L;
        this.userID = userID;
        this.categoryID = categoryID;
    }

    /**
     * Get User Category Id
     * @return User Category Id
     */
    public Long getUserCategoryID() {
        return userCategoryID;
    }

    /**
     * Get User Id
     * @return User Id
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Get Category Id
     * @return Category Id
     */
    public Long getCategoryID() {
        return categoryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCategory that = (UserCategory) o;
        return Objects.equals(userCategoryID, that.userCategoryID) &&
                Objects.equals(userID, that.userID) &&
                Objects.equals(categoryID, that.categoryID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userCategoryID, userID, categoryID);
    }

    @Override
    public String toString() {
        return "UserCategory{" +
                "userCategoryID=" + userCategoryID +
                ", userID=" + userID +
                ", categoryID=" + categoryID +
                '}';
    }
}
