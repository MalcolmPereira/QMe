/**
 * Name      : com.malcolm.qme.rest.model.QMeUser.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : QMe User
 */

package com.malcolm.qme.rest.model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        QMeUser qMeUser = (QMeUser) o;

        if (!getUserName().equals(qMeUser.getUserName())) return false;
        if (!getUserPassword().equals(qMeUser.getUserPassword())) return false;
        if (getUserFirstName() != null ? !getUserFirstName().equals(qMeUser.getUserFirstName()) : qMeUser.getUserFirstName() != null)
            return false;
        if (getUserLastName() != null ? !getUserLastName().equals(qMeUser.getUserLastName()) : qMeUser.getUserLastName() != null)
            return false;
        return getUserEmail().equals(qMeUser.getUserEmail());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getUserPassword().hashCode();
        result = 31 * result + (getUserFirstName() != null ? getUserFirstName().hashCode() : 0);
        result = 31 * result + (getUserLastName() != null ? getUserLastName().hashCode() : 0);
        result = 31 * result + getUserEmail().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "QMeUser{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
