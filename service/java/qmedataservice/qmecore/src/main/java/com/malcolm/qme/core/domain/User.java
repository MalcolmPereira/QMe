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
    private final Long userID;
    /**
     * User Name
     */
    private final String userName;
    /**
     * User Password
     */
    private final String userPassword;
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
    private final Long  updateUserID;

    /**
     * Public Constructor
     *
     * @param userID
     * @param userName
     * @param userPassword
     * @param userFirstName
     * @param userLastName
     * @param userEmail
     * @param userRegisteredDate
     * @param userUpdateDate
     * @param updateUserID
     */
    public User(Long userID, String userName, String userPassword, String userFirstName, String userLastName, String userEmail, Date userRegisteredDate, Date userUpdateDate, Long updateUserID) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userRegisteredDate = userRegisteredDate;
        this.userUpdateDate = userUpdateDate;
        this.updateUserID = updateUserID;
    }

    /**
     * Public Constructor
     *
     * @param userName
     * @param userPassword
     * @param userFirstName
     * @param userLastName
     * @param userEmail
     */
    public User(String userName, String userPassword, String userFirstName, String userLastName, String userEmail) {
        this.userID = 0L;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userRegisteredDate = null;
        this.userUpdateDate = null;
        this.updateUserID = 0L;
    }

    /**
     * Return User Id
     * @return
     */
    public Long getUserID() {
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
     * Get User Password
     * @return
     */
    public String getUserPassword() {
        return userPassword;
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
    public Long getUpdateUserID() {
        return updateUserID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!getUserID().equals(user.getUserID())) return false;
        if (!getUserName().equals(user.getUserName())) return false;
        if (!getUserPassword().equals(user.getUserPassword())) return false;
        if (getUserFirstName() != null ? !getUserFirstName().equals(user.getUserFirstName()) : user.getUserFirstName() != null)
            return false;
        if (getUserLastName() != null ? !getUserLastName().equals(user.getUserLastName()) : user.getUserLastName() != null)
            return false;
        if (!getUserEmail().equals(user.getUserEmail())) return false;
        if (getUserRegisteredDate() != null ? !getUserRegisteredDate().equals(user.getUserRegisteredDate()) : user.getUserRegisteredDate() != null)
            return false;
        if (getUserUpdateDate() != null ? !getUserUpdateDate().equals(user.getUserUpdateDate()) : user.getUserUpdateDate() != null)
            return false;
        return !(getUpdateUserID() != null ? !getUpdateUserID().equals(user.getUpdateUserID()) : user.getUpdateUserID() != null);

    }

    @Override
    public int hashCode() {
        int result = getUserID().hashCode();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getUserPassword().hashCode();
        result = 31 * result + (getUserFirstName() != null ? getUserFirstName().hashCode() : 0);
        result = 31 * result + (getUserLastName() != null ? getUserLastName().hashCode() : 0);
        result = 31 * result + getUserEmail().hashCode();
        result = 31 * result + (getUserRegisteredDate() != null ? getUserRegisteredDate().hashCode() : 0);
        result = 31 * result + (getUserUpdateDate() != null ? getUserUpdateDate().hashCode() : 0);
        result = 31 * result + (getUpdateUserID() != null ? getUpdateUserID().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRegisteredDate=" + userRegisteredDate +
                ", userUpdateDate=" + userUpdateDate +
                ", updateUserID=" + updateUserID +
                '}';
    }
}