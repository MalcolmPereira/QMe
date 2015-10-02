/**
 * Name      : com.malcolm.qme.security.config.QMeLoginUser.java
 * Date      : 9/30/15
 * Developer : Malcolm
 * Purpose   : QMe Login User
 */
package com.malcolm.qme.security.config;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Malcolm
 */
public class QMeLoginUser {
    /**
     * User Name
     */
    private String userName;
    /**
     * User Password
     */
    private String userPassword;
    /**
     * User Id
     */
    private Long userID;
    /**
     * User Roles
     */
    private List<String> roles;
    /**
     * User First Name
     */
    private String userFirstName;
    /**
     * User Last Name
     */
    private String userLastName;
    /**
     * User Email
     */
    private String userEmail;
    /**
     * User Registered Date
     */
    private LocalDateTime userRegisteredDate;
    /**
     * User Update Date
     */
    private LocalDateTime userUpdateDate;
    /**
     * User Last Login Date
     */
    private LocalDateTime userLastLoginDate;
    /**
     * Authentication Token
     */
    private String authToken;
    /**
     * Get User Name
     * @return userName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Set User Name
     * @param userName User Name to Set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * Set User Password
     * @return userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }
    /**
     * Set User Password
     * @param userPassword User Password to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    /**
     * Get User Id
     * @return User Id
     */
    public Long getUserID() {
        return userID;
    }
    /**
     * Set User Id
     * @param userID User Id to Set
     */
    public void setUserID(Long userID) {
        this.userID = userID;
    }
    /**
     * Set User Roles
     * @return User Roles
     */
    public List<String> getRoles() {
        return roles;
    }
    /**
     * Set User Roles
     * @param roles User Roles to Set
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    /**
     * Get User First Name
     * @return User First Name
     */
    public String getUserFirstName() {
        return userFirstName;
    }
    /**
     * Set User First Name
     * @param userFirstName User First Name to Set
     */
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }
    /**
     * Get User Last Name
     * @return User Last Name
     */
    public String getUserLastName() {
        return userLastName;
    }
    /**
     * Set User Last Name
     * @param userLastName User Last Name to Set
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
    /**
     * Get User Email
     * @return User Email
     */
    public String getUserEmail() {
        return userEmail;
    }
    /**
     * Set User Email
     * @param userEmail User Email to Set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    /**
     * Get User Registration Date
     * @return User Registration Date
     */
    public LocalDateTime getUserRegisteredDate() {
        return userRegisteredDate;
    }
    /**
     * Set User Registration Date
     * @param userRegisteredDate User Registered Date
     */
    public void setUserRegisteredDate(LocalDateTime userRegisteredDate) {
        this.userRegisteredDate = userRegisteredDate;
    }
    /**
     * Get User Update Date
     * @return userUpdateDate User Update Date
     */
    public LocalDateTime getUserUpdateDate() {
        return userUpdateDate;
    }
    /**
     * Set User Update Date
     * @param userUpdateDate User Update Date to Set
     */
    public void setUserUpdateDate(LocalDateTime userUpdateDate) {
        this.userUpdateDate = userUpdateDate;
    }
    /**
     * Get User Last Login Date
     * @return
     */
    public LocalDateTime getUserLastLoginDate() {
        return userLastLoginDate;
    }
    /**
     * Set User Last Login Date
     * @param userLastLoginDate
     */
    public void setUserLastLoginDate(LocalDateTime userLastLoginDate) {
        this.userLastLoginDate = userLastLoginDate;
    }
    /**
     * Get Authentication Token
     * @return
     */
    public String getAuthToken() {
        return authToken;
    }
    /**
     * Set Authentication Token
     * @param authToken
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}