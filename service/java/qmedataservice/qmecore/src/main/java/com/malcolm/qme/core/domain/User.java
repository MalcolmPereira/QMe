/**
 * Name      : com.malcolm.qme.core.domain.User.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe User Domain Class
 */
package com.malcolm.qme.core.domain;

import java.time.LocalDateTime;

/**
 * @author Malcolm
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
    private final LocalDateTime userRegisteredDate;
    /**
     * User Update Date
     */
    private final LocalDateTime userUpdateDate;
    /**
     * Update User Id
     */
    private final Long  updateUserID;

    /**
     * Public Constructor
     *
     * @param userID User ID
     * @param userName Unique User Name
     * @param userPassword Encrypted User Password
     * @param userFirstName User First Name
     * @param userLastName User Last Name
     * @param userEmail Unique User Email
     * @param userRegisteredDate User Registered Date
     * @param userUpdateDate User Update Date
     * @param updateUserID Update User ID
     */
    public User(Long userID, String userName, String userPassword, String userFirstName, String userLastName, String userEmail, LocalDateTime userRegisteredDate, LocalDateTime userUpdateDate, Long updateUserID) {
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
     * @param userName Unique User Name
     * @param userPassword Encrypted User Password
     * @param userFirstName User First Name
     * @param userLastName User Last Name
     * @param userEmail Unique User Email
     */
    public User(String userName, String userPassword, String userFirstName, String userLastName, String userEmail) {
        this.userID = 0L;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userRegisteredDate = LocalDateTime.now();
        this.userUpdateDate = LocalDateTime.now();
        this.updateUserID = 0L;
    }

    /**
     * Return User Id
     * @return User Id
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Return User Name
     *
     * @return User Name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get User Password
     * @return User Password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Return User First Name
     * @return User First Name
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * Return User Last Name
     * @return User Last Name
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * Return User Email
     * @return User Email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Return User Registered Date
     * @return User Registered Date
     */
    public LocalDateTime getUserRegisteredDate() {
        return userRegisteredDate;
    }

    /**
     * Return User Update Date
     * @return User Update Date
     */
    public LocalDateTime getUserUpdateDate() {
        return userUpdateDate;
    }

    /**
     * Return Update User ID
     * @return Update User ID
     */
    public Long getUpdateUserID() {
        return updateUserID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getUserID().equals(user.getUserID()) && getUserName().equals(user.getUserName()) && getUserPassword().equals(user.getUserPassword()) && !(getUserFirstName() != null ? !getUserFirstName().equals(user.getUserFirstName()) : user.getUserFirstName() != null) && !(getUserLastName() != null ? !getUserLastName().equals(user.getUserLastName()) : user.getUserLastName() != null) && getUserEmail().equals(user.getUserEmail()) && !(getUserRegisteredDate() != null ? !getUserRegisteredDate().equals(user.getUserRegisteredDate()) : user.getUserRegisteredDate() != null) && !(getUserUpdateDate() != null ? !getUserUpdateDate().equals(user.getUserUpdateDate()) : user.getUserUpdateDate() != null) && !(getUpdateUserID() != null ? !getUpdateUserID().equals(user.getUpdateUserID()) : user.getUpdateUserID() != null);

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