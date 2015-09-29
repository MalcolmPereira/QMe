/**
 * Name      : com.malcolm.qme.rest.model.QMeStageUser.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : QMe Staging User
 */
package com.malcolm.qme.rest.model;

/**
 * @author Malcolm
 */
public class QMeStageUser extends QMeResource {
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
     * Staging Confirm URL
     */
    private String confirmURL;

    /**
     * Get User Name
     * @return userName
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
     * @return userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Set User Password
     * @param userPassword  UserPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Get User First Name
     * @return UserFirstName
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * Set User First Name
     * @param userFirstName UserFirstName
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
     * @return UserEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Set User Email
     * @param userEmail userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Get Confirm URL
     * @return Confirm URL
     */
    public String getConfirmURL() {
        return confirmURL;
    }

    /**
     * Set Confirm URL
     * @param confirmURL
     */
    public void setConfirmURL(String confirmURL) {
        this.confirmURL = confirmURL;
    }
}
