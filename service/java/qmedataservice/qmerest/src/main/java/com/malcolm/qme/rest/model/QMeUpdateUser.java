/**
 * Name      : com.malcolm.qme.rest.model.QMeUpdateUser.java
 * Date      : 10/08/15
 * Developer : Malcolm
 * Purpose   : QMe Update User (Update User Details Including User Password)
 */
package com.malcolm.qme.rest.model;

/**
 * @author Malcolm
 */
public class QMeUpdateUser extends QMeUserDetail {
    /**
     * Updated User Password
     */
    private String updatedUserPassword;

    /**
     * Get Updated User Password
     * @return
     */
    public String getUpdatedUserPassword() {
        return updatedUserPassword;
    }

    /**
     * Set Updated User Password
     * @param updatedUserPassword
     */
    public void setUpdatedUserPassword(String updatedUserPassword) {
        this.updatedUserPassword = updatedUserPassword;
    }
}
