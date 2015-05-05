/**
 * Name      : com.malcolm.qme.core.domain.UserCategory.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe User Category Domain Class
 */
package com.malcolm.qme.core.domain;

/**
 * @Author Malcolm
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
     * @param userCategoryID
     * @param userID
     * @param categoryID
     */
    public UserCategory(Long userCategoryID, Long userID, Long categoryID) {
        this.userCategoryID = userCategoryID;
        this.userID = userID;
        this.categoryID = categoryID;
    }

    /**
     * Public Constructor
     *

     * @param userID
     * @param categoryID
     */
    public UserCategory(Long userID, Long categoryID) {
        this.userCategoryID = 0L;
        this.userID = userID;
        this.categoryID = categoryID;
    }

    /**
     * Get User Category Id
     * @return
     */
    public Long getUserCategoryID() {
        return userCategoryID;
    }

    /**
     * Get User Id
     * @return
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Get Category Id
     * @return
     */
    public Long getCategoryID() {
        return categoryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCategory that = (UserCategory) o;

        if (!getUserCategoryID().equals(that.getUserCategoryID())) return false;
        if (!getUserID().equals(that.getUserID())) return false;
        return getCategoryID().equals(that.getCategoryID());

    }

    @Override
    public int hashCode() {
        int result = getUserCategoryID().hashCode();
        result = 31 * result + getUserID().hashCode();
        result = 31 * result + getCategoryID().hashCode();
        return result;
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
