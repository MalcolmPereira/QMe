/**
 * Name      : com.malcolm.qme.security.config.QMeLoginUser.java
 * Date      : 9/30/15
 * Developer : Malcolm
 * Purpose   : QMe Login User
 */
package com.malcolm.qme.security.config;

/**
 * @author Malcolm
 */
public class QMeLoginUser {
    /**
     * User Name
     */
    private String username;

    /**
     * User Password
     */
    private String password;

    /**
     * Get User Name
     * @return User Name
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set User Name
     * @param username User Name
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get Password
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password
     * @param password Password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
