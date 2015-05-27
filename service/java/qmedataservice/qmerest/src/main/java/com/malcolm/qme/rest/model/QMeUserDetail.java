/**
 * Name      : com.malcolm.qme.rest.model.QMeUserDetail.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : QMeUser Details Model for REST Service Controller
 */

package com.malcolm.qme.rest.model;

import java.time.LocalDateTime;

/**
 * @author Malcolm
 */
public class QMeUserDetail extends QMeUser {
    /**
     * User Id
     */
    private Long userID;
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

        QMeUserDetail that = (QMeUserDetail) o;

        if (!getUserID().equals(that.getUserID())) return false;
        if (getUserRegisteredDate() != null ? !getUserRegisteredDate().equals(that.getUserRegisteredDate()) : that.getUserRegisteredDate() != null)
            return false;
        if (getUserUpdateDate() != null ? !getUserUpdateDate().equals(that.getUserUpdateDate()) : that.getUserUpdateDate() != null)
            return false;
        if (getUpdateUserID() != null ? !getUpdateUserID().equals(that.getUpdateUserID()) : that.getUpdateUserID() != null)
            return false;
        return !(getUpdateUserName() != null ? !getUpdateUserName().equals(that.getUpdateUserName()) : that.getUpdateUserName() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getUserID().hashCode();
        result = 31 * result + (getUserRegisteredDate() != null ? getUserRegisteredDate().hashCode() : 0);
        result = 31 * result + (getUserUpdateDate() != null ? getUserUpdateDate().hashCode() : 0);
        result = 31 * result + (getUpdateUserID() != null ? getUpdateUserID().hashCode() : 0);
        result = 31 * result + (getUpdateUserName() != null ? getUpdateUserName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QMeUserDetail{" +
                "userID=" + userID +
                ", userRegisteredDate=" + userRegisteredDate +
                ", userUpdateDate=" + userUpdateDate +
                ", updateUserID=" + updateUserID +
                ", updateUserName='" + updateUserName + '\'' +
                '}';
    }
}
