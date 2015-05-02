/**
 * Name      : com.malcolm.qme.core.domain.User.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe User Domain Class
 */
package com.malcolm.qme.core.domain;

import java.util.Date;

/**
 * @Author Malcolm
 */
public final class User {
    /**
     * User Id
     */
    private final long userID;
    /**
     * User Name
     */
    private final String userName;
    /**
     * User First Name
     */
    private final String userFirstName;
    /**
     * User Last Name
     */
    private final String userLastName;
    /**
     * User Email
     */
    private final String userEmail;
    /**
     * User Registered Date
     */
    private final Date userRegisteredDate;
    /**
     * User Update Date
     */
    private final Date userUpdateDate;
    /**
     * Update User Id
     */
    private final long  updateUserID;

    /**
     * Public Constructor
     * @param userID
     * @param userName
     * @param userFirstName
     * @param userLastName
     * @param userEmail
     * @param userRegisteredDate
     * @param userUpdateDate
     * @param updateUserID
     */
    public User(long  userID, String userName, String userFirstName, String userLastName, String userEmail, Date userRegisteredDate, Date userUpdateDate, long updateUserID) {
        this.userID                 = userID;
        this.userName               = userName;
        this.userFirstName          = userFirstName;
        this.userLastName           = userLastName;
        this.userEmail              = userEmail;
        this.userRegisteredDate     = userRegisteredDate;
        this.userUpdateDate         = userUpdateDate;
        this.updateUserID           = updateUserID;
    }

    /**
     * Public Constructor
     * @param userName
     * @param userFirstName
     * @param userLastName
     * @param userEmail
     */
    public User(String userName, String userFirstName, String userLastName, String userEmail) {
        this.userName               = userName;
        this.userFirstName          = userFirstName;
        this.userLastName           = userLastName;
        this.userEmail              = userEmail;
        this.userID                 = 0;
        this.userRegisteredDate     = null;
        this.userUpdateDate         = null;
        this.updateUserID           = 0;
    }

    /**
     * Return User Id
     * @return
     */
    public long getUserID() {
        return userID;
    }

    /**
     * Return User Name
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Return User First Name
     * @return
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * Return User Last Name
     * @return
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * Return User Email
     * @return
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Return User Registered Date
     * @return
     */
    public Date getUserRegisteredDate() {
        return userRegisteredDate;
    }

    /**
     * Return User Update Date
     * @return
     */
    public Date getUserUpdateDate() {
        return userUpdateDate;
    }

    /**
     * Return Update User ID
     * @return
     */
    public long getUpdateUserID() {
        return updateUserID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (updateUserID != user.updateUserID) return false;
        if (userID != user.userID) return false;
        if (!userEmail.equals(user.userEmail)) return false;
        if (!userFirstName.equals(user.userFirstName)) return false;
        if (!userLastName.equals(user.userLastName)) return false;
        if (!userName.equals(user.userName)) return false;
        if (userRegisteredDate != null ? !userRegisteredDate.equals(user.userRegisteredDate) : user.userRegisteredDate != null)
            return false;
        if (userUpdateDate != null ? !userUpdateDate.equals(user.userUpdateDate) : user.userUpdateDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userID ^ (userID >>> 32));
        result = 31 * result + userName.hashCode();
        result = 31 * result + userFirstName.hashCode();
        result = 31 * result + userLastName.hashCode();
        result = 31 * result + userEmail.hashCode();
        result = 31 * result + (userRegisteredDate != null ? userRegisteredDate.hashCode() : 0);
        result = 31 * result + (userUpdateDate != null ? userUpdateDate.hashCode() : 0);
        result = 31 * result + (int) (updateUserID ^ (updateUserID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRegisteredDate=" + userRegisteredDate +
                ", userUpdateDate=" + userUpdateDate +
                ", updateUserID=" + updateUserID +
                '}';
    }
}