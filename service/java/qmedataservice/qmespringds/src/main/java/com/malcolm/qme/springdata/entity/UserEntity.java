/**
 * Name      : com.malcolm.qme.springdata.entity.UserEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : User Entity
 */
package com.malcolm.qme.springdata.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "USER", catalog = "qme", uniqueConstraints = {
		@UniqueConstraint(columnNames = "USER_EMAIL"),
		@UniqueConstraint(columnNames = "USER_NAME") })
public class UserEntity implements java.io.Serializable {

	/**
	 * Generated Serialized version id
	 */
	private static final long serialVersionUID = -1985435715851605163L;

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
	 * User Registered Date
	 */
	@Column(name = "USER_REGISTERED_DATE", nullable = false, length = 19)
	private LocalDateTime userRegisteredDate;

	/**
	 * User Update Date
	 */
	@Column(name = "USER_UPDATED_DATE", nullable = false, length = 19)
	private LocalDateTime userUpdatedDate;

	/**
	 * User Last Login Date
	 */
	@Column(name = "USER_LAST_LOGIN_DATE", nullable = false, length = 19)
	private LocalDateTime userLastLoginDate;

	/**
	 * User Login Date
	 */
	@Column(name = "USER_LOGIN_DATE", nullable = false, length = 19)
	private LocalDateTime userLoginDate;


	/**
	 * Update User
	 */
	@Column(name = "UPDATE_USER")
	private Long updateUser;

	/**
	 * Public Constructor
	 */
	public UserEntity() {
	}

	/**
	 * Public Constructor
	 *
	 * @param userName User Name
	 * @param userFirstName User First Name
	 * @param userLastName User Last Name
	 * @param userEmail User Email
	 * @param userPasscode User Passcode
	 * @param userRegisteredDate User Registered Date
	 * @param userUpdatedDate User Updated Date
	 */
	public UserEntity(String userName, String userFirstName,
			String userLastName, String userEmail, String userPasscode,
			LocalDateTime userRegisteredDate, LocalDateTime userUpdatedDate,LocalDateTime userLastLoginDate) {
		this.userName = userName;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userPasscode = userPasscode;
		this.userRegisteredDate = userRegisteredDate;
		this.userUpdatedDate = userUpdatedDate;
		this.userLastLoginDate = userLastLoginDate;
        this.userLoginDate = LocalDateTime.now();
	}

	/**
	 * Public Constructor
	 *
	 * @param userName User Name
	 * @param userFirstName User First Name
	 * @param userLastName User Last Name
	 * @param userEmail User Email
	 * @param userPasscode User Passcode
	 * @param userRegisteredDate User Registered Date
	 * @param userUpdatedDate User Updated Date
	 * @param userLastLoginDate User Last Login Date
	 * @param userLoginDate User Login Date
	 * @param updateUser Update User
	 */
	public UserEntity(String userName, String userFirstName, String userLastName, String userEmail, String userPasscode, LocalDateTime userRegisteredDate, LocalDateTime userUpdatedDate, LocalDateTime userLastLoginDate, LocalDateTime userLoginDate, Long updateUser) {
		this.userName = userName;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userPasscode = userPasscode;
		this.userRegisteredDate = userRegisteredDate;
		this.userUpdatedDate = userUpdatedDate;
		this.userLastLoginDate = userLastLoginDate;
		this.userLoginDate = userLoginDate;
		this.updateUser = updateUser;
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
	 * @return the userRegisteredDate
	 */
	public LocalDateTime getUserRegisteredDate() {
		return userRegisteredDate;
	}

	/**
	 * @param userRegisteredDate the userRegisteredDate to set
	 */
	public void setUserRegisteredDate(LocalDateTime userRegisteredDate) {
		this.userRegisteredDate = userRegisteredDate;
	}

	/**
	 * @return the userUpdatedDate
	 */
	public LocalDateTime getUserUpdatedDate() {
		return userUpdatedDate;
	}

	/**
	 * @param userUpdatedDate the userUpdatedDate to set
	 */
	public void setUserUpdatedDate(LocalDateTime userUpdatedDate) {
		this.userUpdatedDate = userUpdatedDate;
	}

    /**
     * @return the userLastLoginDate
     */
    public LocalDateTime getUserLastLoginDate() {
        return userLastLoginDate;
    }

    /**
     * @param userLastLoginDate the userLastLoginDate to set
     */
    public void setUserLastLoginDate(LocalDateTime userLastLoginDate) {
        this.userLastLoginDate = userLastLoginDate;
    }


    /**
     * @return the userLoginDate
     */
    public LocalDateTime getUserLoginDate() {
        return userLoginDate;
    }

    /**
     * @param userLoginDate the userLoginDate to set
     */
    public void setUserLoginDate(LocalDateTime userLoginDate) {
        this.userLoginDate = userLoginDate;
    }

    /**
	 * @return the updateUser
	 */
	public Long getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (!userName.equals(that.userName)) return false;
        if (!userFirstName.equals(that.userFirstName)) return false;
        if (!userLastName.equals(that.userLastName)) return false;
        if (!userEmail.equals(that.userEmail)) return false;
        return userPasscode.equals(that.userPasscode);

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + userName.hashCode();
        result = 31 * result + userFirstName.hashCode();
        result = 31 * result + userLastName.hashCode();
        result = 31 * result + userEmail.hashCode();
        result = 31 * result + userPasscode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPasscode='" + userPasscode + '\'' +
                ", userRegisteredDate=" + userRegisteredDate +
                ", userUpdatedDate=" + userUpdatedDate +
                ", userLastLoginDate=" + userLastLoginDate +
                ", userLoginDate=" + userLoginDate +
                ", updateUser=" + updateUser +
                '}';
    }
}