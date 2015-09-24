/**
 * Name      : com.malcolm.qme.rest.model.QMeUser.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : QMe User
 */

package com.malcolm.qme.rest.model;

import java.util.List;

/**
 * @author Malcolm
 */
public class QMeUser extends QMeResource {
    /**
     * User Name
     */
    private String userName;
    /**
     * User Password
     */
    private String userPassword;
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
     * QMe User Roles
     */
    private List<String> userRoles;

    /**
     * Get User Name
     * @return User Name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set User Name
     * @param userName User Name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get User Password
     * @return User Password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Set User Password
     * @param userPassword User Password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
     * @param userFirstName User First Name
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
     * @param userLastName User Last Name
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
     * @param userEmail User Email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Get User Roles
     * @return userRoles for user
     */
    public List<String> getUserRoles() {
        return userRoles;
    }

    /**
     * Set User Roles
     * @param userRoles to be set
     */
    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

}
