/**
 * Name      : com.malcolm.qme.rest.model.QMeUser.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : QMe User
 */

package com.malcolm.qme.rest.model;

import java.time.LocalDateTime;

/**
 * @author Malcolm
 */
public class QMeUser extends QMeResource {
    /**
     * User Id
     */
    private Long userID;
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
     * User Registered Date
     */
    private LocalDateTime userRegisteredDate;
    /**
     * User Update Date
     */
    private  LocalDateTime userUpdateDate;
    /**
     * Update User Id
     */
    private Long  updateUserID;
    /**
     * QMeCategory Created User Name
     */
    private String updateUserName;

    /**
     * Get User ID
     * @return User ID
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Set User ID
     * @param userID User ID
     */
    public void setUserID(Long userID) {
        this.userID = userID;
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
     * Get User Registration Date
     * @return User Registration Date
     */
    public LocalDateTime getUserRegisteredDate() {
        return userRegisteredDate;
    }

    /**
     * Set User Registration Date
     * @param userRegisteredDate User Registration Date
     */
    public void setUserRegisteredDate(LocalDateTime userRegisteredDate) {
        this.userRegisteredDate = userRegisteredDate;
    }

    /**
     * Get User Update Date
     * @return User Update Date
     */
    public LocalDateTime getUserUpdateDate() {
        return userUpdateDate;
    }

    /**
     * Set User Update Date
     * @param userUpdateDate User Update Date
     */
    public void setUserUpdateDate(LocalDateTime userUpdateDate) {
        this.userUpdateDate = userUpdateDate;
    }

    /**
     * Get Update User ID
     * @return  Update User ID
     */
    public Long getUpdateUserID() {
        return updateUserID;
    }

    /**
     * Set Update User ID
     * @param updateUserID Update User ID
     */
    public void setUpdateUserID(Long updateUserID) {
        this.updateUserID = updateUserID;
    }

    /**
     * Get Update User Name
     * @return Update User Name
     */
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * set Update User Name
     * @param updateUserName Update User Name
     */
    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        QMeUser qMeUser = (QMeUser) o;

        if (!getUserID().equals(qMeUser.getUserID())) return false;
        if (!getUserName().equals(qMeUser.getUserName())) return false;
        if (!getUserPassword().equals(qMeUser.getUserPassword())) return false;
        if (!getUserFirstName().equals(qMeUser.getUserFirstName())) return false;
        if (!getUserLastName().equals(qMeUser.getUserLastName())) return false;
        if (!getUserEmail().equals(qMeUser.getUserEmail())) return false;
        if (getUserRegisteredDate() != null ? !getUserRegisteredDate().equals(qMeUser.getUserRegisteredDate()) : qMeUser.getUserRegisteredDate() != null)
            return false;
        if (getUserUpdateDate() != null ? !getUserUpdateDate().equals(qMeUser.getUserUpdateDate()) : qMeUser.getUserUpdateDate() != null)
            return false;
        if (getUpdateUserID() != null ? !getUpdateUserID().equals(qMeUser.getUpdateUserID()) : qMeUser.getUpdateUserID() != null)
            return false;
        return !(getUpdateUserName() != null ? !getUpdateUserName().equals(qMeUser.getUpdateUserName()) : qMeUser.getUpdateUserName() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getUserID().hashCode();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getUserPassword().hashCode();
        result = 31 * result + getUserFirstName().hashCode();
        result = 31 * result + getUserLastName().hashCode();
        result = 31 * result + getUserEmail().hashCode();
        result = 31 * result + (getUserRegisteredDate() != null ? getUserRegisteredDate().hashCode() : 0);
        result = 31 * result + (getUserUpdateDate() != null ? getUserUpdateDate().hashCode() : 0);
        result = 31 * result + (getUpdateUserID() != null ? getUpdateUserID().hashCode() : 0);
        result = 31 * result + (getUpdateUserName() != null ? getUpdateUserName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QMeUser{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRegisteredDate=" + userRegisteredDate +
                ", userUpdateDate=" + userUpdateDate +
                ", updateUserID=" + updateUserID +
                ", updateUserName='" + updateUserName + '\'' +
                '}';
    }
}
