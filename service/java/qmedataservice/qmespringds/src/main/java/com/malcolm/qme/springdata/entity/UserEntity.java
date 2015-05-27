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
			LocalDateTime userRegisteredDate, LocalDateTime userUpdatedDate) {
		this.userName = userName;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userPasscode = userPasscode;
		this.userRegisteredDate = userRegisteredDate;
		this.userUpdatedDate = userUpdatedDate;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((updateUser == null) ? 0 : updateUser.hashCode());
		result = (prime * result)
				+ ((userEmail == null) ? 0 : userEmail.hashCode());
		result = (prime * result)
				+ ((userFirstName == null) ? 0 : userFirstName.hashCode());
		result = (prime * result) + ((userId == null) ? 0 : userId.hashCode());
		result = (prime * result)
				+ ((userLastName == null) ? 0 : userLastName.hashCode());
		result = (prime * result)
				+ ((userName == null) ? 0 : userName.hashCode());
		result = (prime * result)
				+ ((userPasscode == null) ? 0 : userPasscode.hashCode());
		result = (prime
				* result)
				+ ((userRegisteredDate == null) ? 0 : userRegisteredDate
						.hashCode());
		result = (prime * result)
				+ ((userUpdatedDate == null) ? 0 : userUpdatedDate.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UserEntity other = (UserEntity) obj;
		if (updateUser == null) {
			if (other.updateUser != null) {
				return false;
			}
		} else if (!updateUser.equals(other.updateUser)) {
			return false;
		}
		if (userEmail == null) {
			if (other.userEmail != null) {
				return false;
			}
		} else if (!userEmail.equals(other.userEmail)) {
			return false;
		}
		if (userFirstName == null) {
			if (other.userFirstName != null) {
				return false;
			}
		} else if (!userFirstName.equals(other.userFirstName)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (userLastName == null) {
			if (other.userLastName != null) {
				return false;
			}
		} else if (!userLastName.equals(other.userLastName)) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		if (userPasscode == null) {
			if (other.userPasscode != null) {
				return false;
			}
		} else if (!userPasscode.equals(other.userPasscode)) {
			return false;
		}
		if (userRegisteredDate == null) {
			if (other.userRegisteredDate != null) {
				return false;
			}
		} else if (!userRegisteredDate.equals(other.userRegisteredDate)) {
			return false;
		}
		if (userUpdatedDate == null) {
			if (other.userUpdatedDate != null) {
				return false;
			}
		} else if (!userUpdatedDate.equals(other.userUpdatedDate)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", userName=" + userName
				+ ", userFirstName=" + userFirstName + ", userLastName="
				+ userLastName + ", userEmail=" + userEmail + ", userPasscode="
				+ userPasscode + ", userRegisteredDate=" + userRegisteredDate
				+ ", userUpdatedDate=" + userUpdatedDate + ", updateUser="
				+ updateUser + "]";
	}

}
