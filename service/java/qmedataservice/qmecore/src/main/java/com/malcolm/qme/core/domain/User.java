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
     * User Last Login Date
     */
    private final LocalDateTime userLastLoginDate;
    /**
     * User Login Date
     */
    private final LocalDateTime userLoginDate;
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
     * @param userLastLoginDate User Last Login Date
     * @param userLoginDate User Login Date
     * @param updateUserID Update User ID
     */
    public User(Long userID, String userName, String userPassword, String userFirstName, String userLastName, String userEmail, LocalDateTime userRegisteredDate, LocalDateTime userUpdateDate,LocalDateTime userLastLoginDate, LocalDateTime userLoginDate, Long updateUserID) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userRegisteredDate = userRegisteredDate;
        this.userUpdateDate = userUpdateDate;
        this.userLastLoginDate = userLastLoginDate;
        this.userLoginDate = userLoginDate;
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
        this.userLastLoginDate = LocalDateTime.now();
        this.userLoginDate  = LocalDateTime.now();
        this.updateUserID = 0L;
    }

    /**
     * Public Constructor
     *
     * @param userName Unique User Name
     * @param userPassword Encrypted User Password
     * @param userFirstName User First Name
     * @param userLastName User Last Name
     * @param userEmail Unique User Email
     * @param userStagedDate Staged Date
     */
    public User(String userName, String userPassword, String userFirstName, String userLastName, String userEmail, LocalDateTime userStagedDate) {
        this.userID = 0L;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userRegisteredDate = userStagedDate;
        this.userUpdateDate = LocalDateTime.now();
        this.userLastLoginDate = LocalDateTime.now();
        this.userLoginDate  = LocalDateTime.now();
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
     * Return User Last Login Date
     * @return User Last Login Date
     */
    public LocalDateTime getUserLastLoginDate() {
        return userLastLoginDate;
    }

    /**
     * Return User Login Date
     * @return User Login Date
     */
    public LocalDateTime getUserLoginDate() {
        return userLoginDate;
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

        if (!userID.equals(user.userID)) return false;
        if (!userName.equals(user.userName)) return false;
        if (!userPassword.equals(user.userPassword)) return false;
        if (!userFirstName.equals(user.userFirstName)) return false;
        if (!userLastName.equals(user.userLastName)) return false;
        if (!userEmail.equals(user.userEmail)) return false;
        if (!userRegisteredDate.equals(user.userRegisteredDate)) return false;
        if (!userUpdateDate.equals(user.userUpdateDate)) return false;
        if (!userLastLoginDate.equals(user.userLastLoginDate)) return false;
        if (!userLoginDate.equals(user.userLoginDate)) return false;
        return !(updateUserID != null ? !updateUserID.equals(user.updateUserID) : user.updateUserID != null);

    }

    @Override
    public int hashCode() {
        int result = userID.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + userPassword.hashCode();
        result = 31 * result + userFirstName.hashCode();
        result = 31 * result + userLastName.hashCode();
        result = 31 * result + userEmail.hashCode();
        result = 31 * result + userRegisteredDate.hashCode();
        result = 31 * result + userUpdateDate.hashCode();
        result = 31 * result + userLastLoginDate.hashCode();
        result = 31 * result + userLoginDate.hashCode();
        result = 31 * result + (updateUserID != null ? updateUserID.hashCode() : 0);
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
                ", userLastLoginDate=" + userLastLoginDate +
                ", userLoginDate=" + userLoginDate +
                ", updateUserID=" + updateUserID +
                '}';
    }
}