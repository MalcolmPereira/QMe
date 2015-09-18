/**
 * Name      : com.malcolm.qme.springdata.entity.UserStagingEntity.java
 * Date      : 9/18/2015
 * Developer : Malcolm
 * Purpose   : User Staging Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "USER_STAGING", catalog = "qme", uniqueConstraints = {
        @UniqueConstraint(columnNames = "USER_EMAIL"),
        @UniqueConstraint(columnNames = "USER_NAME") })
public class UserStagingEntity implements java.io.Serializable {

    /**
     * Generated Serialized version id
     */
    private static final long serialVersionUID = -1935435615851615163L;

    /**
     * User Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true, nullable = false)
    private Long userId;

    /**
     * User Name
     */
    @Column(name = "USER_NAME", unique = true, nullable = false, length = 45)
    private String userName;

    /**
     * User First Name
     */
    @Column(name = "USER_FIRST_NAME", nullable = false, length = 128)
    private String userFirstName;

    /**
     * User Last Name
     */
    @Column(name = "USER_LAST_NAME", nullable = false, length = 128)
    private String userLastName;

    /**
     * User Email
     */
    @Column(name = "USER_EMAIL", unique = true, nullable = false, length = 128)
    private String userEmail;

    /**
     * User Pass Code
     */
    @Column(name = "USER_PASSCODE", nullable = false, length = 512)
    private String userPasscode;

    /**
     * User Staging Date
     */
    @Column(name = "USER_STAGING_DATE", nullable = false, length = 19)
    private LocalDateTime userStagingDate;

    /**
     * Reset Token
     */
    @Column(name = "USER_STAGING_TOKEN", unique = true, nullable = false)
    private String stagingToken;

    /**
     * Public Constructor
     */
    public UserStagingEntity() {
    }

    /**
     *  Public Constructor
     *
     * @param userName User Name
     * @param userFirstName User First Name
     * @param userLastName User Last Name
     * @param userEmail User Email
     * @param userPasscode User Passcode
     * @param userStagingDate User Staging Date
     * @param stagingToken User Staging Token
     */
    public UserStagingEntity(String userName, String userFirstName, String userLastName, String userEmail, String userPasscode, LocalDateTime userStagingDate, String stagingToken) {
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPasscode = userPasscode;
        this.userStagingDate = userStagingDate;
        this.stagingToken = stagingToken;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userFirstName
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * @param userFirstName the userFirstName to set
     */
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    /**
     * @return the userLastName
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * @param userLastName the userLastName to set
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     * @return the userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @return the userPasscode
     */
    public String getUserPasscode() {
        return userPasscode;
    }

    /**
     * @param userPasscode the userPasscode to set
     */
    public void setUserPasscode(String userPasscode) {
        this.userPasscode = userPasscode;
    }

    /**
     * @return the userStagingDate
     */
    public LocalDateTime getUserStagingDate() {
        return userStagingDate;
    }

    /**
     * @param userStagingDate the userStagingDate to set
     */
    public void setUserStagingDate(LocalDateTime userStagingDate) {
        this.userStagingDate = userStagingDate;
    }

    /**
     * @return the stagingToken
     */
    public String getStagingToken() {
        return stagingToken;
    }

    /**
     * @param stagingToken the stagingToken to set
     */
    public void setStagingToken(String stagingToken) {
        this.stagingToken = stagingToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserStagingEntity that = (UserStagingEntity) o;

        if (!userId.equals(that.userId)) return false;
        if (!userName.equals(that.userName)) return false;
        if (!userFirstName.equals(that.userFirstName)) return false;
        if (!userLastName.equals(that.userLastName)) return false;
        if (!userEmail.equals(that.userEmail)) return false;
        if (!userPasscode.equals(that.userPasscode)) return false;
        if (!userStagingDate.equals(that.userStagingDate)) return false;
        return stagingToken.equals(that.stagingToken);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + userFirstName.hashCode();
        result = 31 * result + userLastName.hashCode();
        result = 31 * result + userEmail.hashCode();
        result = 31 * result + userPasscode.hashCode();
        result = 31 * result + userStagingDate.hashCode();
        result = 31 * result + stagingToken.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserStagingEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPasscode='" + userPasscode + '\'' +
                ", userStagingDate=" + userStagingDate +
                ", stagingToken='" + stagingToken + '\'' +
                '}';
    }
}