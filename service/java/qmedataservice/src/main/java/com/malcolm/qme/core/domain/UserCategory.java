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
    private final long userCategoryID;

    /**
     * User Id
     */
    private final long userID;

    /**
     * Category Id
     */
    private final long categoryID;

    /**
     * Public Constructor
     * @param userCategoryID
     * @param userID
     * @param categoryID
     */
    public UserCategory(long userCategoryID, long userID, long categoryID) {
        this.userCategoryID = userCategoryID;
        this.userID = userID;
        this.categoryID = categoryID;
    }

    /**
     * Public Constructor

     * @param userID
     * @param categoryID
     */
    public UserCategory(long userID, long categoryID) {
        this.userCategoryID = 0;
        this.userID = userID;
        this.categoryID = categoryID;
    }

    /**
     * Get User Category Id
     * @return
     */
    public long getUserCategoryID() {
        return userCategoryID;
    }

    /**
     * Get User Id
     * @return
     */
    public long getUserID() {
        return userID;
    }

    /**
     * Get Category Id
     * @return
     */
    public long getCategoryID() {
        return categoryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCategory that = (UserCategory) o;

        if (getUserCategoryID() != that.getUserCategoryID()) return false;
        if (getUserID() != that.getUserID()) return false;
        return getCategoryID() == that.getCategoryID();

    }

    @Override
    public int hashCode() {
        int result = (int) (getUserCategoryID() ^ (getUserCategoryID() >>> 32));
        result = 31 * result + (int) (getUserID() ^ (getUserID() >>> 32));
        result = 31 * result + (int) (getCategoryID() ^ (getCategoryID() >>> 32));
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
