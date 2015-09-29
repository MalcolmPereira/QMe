/**
 * Name      : com.malcolm.qme.rest.model.QMeResetPassword.java
 * Date      : 5/31/15
 * Developer : Malcolm
 * Purpose   : QMe Reset Password Token
 */

package com.malcolm.qme.rest.model;

/**
 * @author Malcolm
 */
public class QMeResetPassword {
     /**
     * Reset Token
     */
    private String token;

    /**
     * User Name
     */
    private String userName;

    /**
     * User Password
     */
    private String userPassword;

    /**
     * Get Token
     * @return User Token
     */
    public String getToken() {
        return token;
    }

    /**
     * Set User Token
     * @param token User Token
     */
    public void setToken(String token) {
        this.token = token;
    }

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
}
