/**
 * Name      : com.malcolm.qme.core.domain.UserCategoryLikes.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : QMe User Category Likes Domain Class
 */
package com.malcolm.qme.core.domain;

/**
 * @Author Malcolm
 */
public class UserCategoryLikes {
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
     * @param userID
     * @param categoryID
     */
    public UserCategoryLikes(Long userID, Long categoryID) {
        this.userID = userID;
        this.categoryID = categoryID;
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

        UserCategoryLikes that = (UserCategoryLikes) o;

        if (!getUserID().equals(that.getUserID())) return false;
        return getCategoryID().equals(that.getCategoryID());

    }

    @Override
    public int hashCode() {
        int result = getUserID().hashCode();
        result = 31 * result + getCategoryID().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserCategoryLikes{" +
                "userID=" + userID +
                ", categoryID=" + categoryID +
                '}';
    }
}
