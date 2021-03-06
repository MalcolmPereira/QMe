/**
 * Name      : com.malcolm.qme.core.domain.User.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe User Domain Class
 */
package com.malcolm.qme.core.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
     * User Roles
     */
    private List<UserRole> userRoles;

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

    /**
     * Get User Roles
     * @return User Roles
     */
    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    /**
     * Set User Roles
     * @param userRoles User Roles
     */
    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userID, user.userID) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userPassword, user.userPassword) &&
                Objects.equals(userFirstName, user.userFirstName) &&
                Objects.equals(userLastName, user.userLastName) &&
                Objects.equals(userEmail, user.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, userName, userPassword, userFirstName, userLastName, userEmail);
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