/**
 * Name      : com.malcolm.qme.rest.model.QMeUserDetail.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : QMeUser Details Model for REST Service Controller
 */

package com.malcolm.qme.rest.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Malcolm
 */
public class QMeUserDetail extends QMeUser {
    /**
     * User Id
     */
    private Long userId;
    /**
     * User Registered Date
     */
    private LocalDateTime userRegisteredDate;
    /**
     * User Update Date
     */
    private  LocalDateTime userUpdateDate;
    /**
     * User Last Login Date
     */
    private  LocalDateTime userLastLoginDate;
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
    public Long getUserId() {
        return userId;
    }
    /**
     * Set User ID
     * @param userId User ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
     * Get User Last Login Date
     * @return User Last Login Date
     */
    public LocalDateTime getUserLastLoginDate() {
        return userLastLoginDate;
    }

    /**
     * Set User Last Login Date
     * @param userLastLoginDate User Last Login Date
     */
    public void setUserLastLoginDate(LocalDateTime userLastLoginDate) {
        this.userLastLoginDate = userLastLoginDate;
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

        if (!userId.equals(that.userId)) return false;
        if (!userRegisteredDate.equals(that.userRegisteredDate)) return false;
        if (!userUpdateDate.equals(that.userUpdateDate)) return false;
        if (!userLastLoginDate.equals(that.userLastLoginDate)) return false;
        if (!updateUserID.equals(that.updateUserID)) return false;
        return updateUserName.equals(that.updateUserName);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + userRegisteredDate.hashCode();
        result = 31 * result + userUpdateDate.hashCode();
        result = 31 * result + userLastLoginDate.hashCode();
        result = 31 * result + updateUserID.hashCode();
        result = 31 * result + updateUserName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "QMeUserDetail{" +
                "userId=" + userId +
                ", userRegisteredDate=" + userRegisteredDate +
                ", userUpdateDate=" + userUpdateDate +
                ", userLastLoginDate=" + userLastLoginDate +
                ", updateUserID=" + updateUserID +
                ", updateUserName='" + updateUserName + '\'' +
                '}';
    }
}
