/**
 * Name      : com.malcolm.qme.springdata.entity.UserPasswordResetEntity.java
 * Date      : 5/27/2015
 * Developer : Malcolm
 * Purpose   : User Password Reset Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "USER_PASSWORD_RESET", catalog = "qme", uniqueConstraints = @UniqueConstraint(columnNames = "RESET_TOKEN"))
public class UserPasswordResetEntity implements java.io.Serializable {

	private static final long serialVersionUID = 7552728125078079438L;

    /**
     * UserPasswordResetEntityId Id
     */
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false)),
            @AttributeOverride(name = "resetToken", column = @Column(name = "RESET_TOKEN", unique = true, nullable = false)) })
    private UserPasswordResetEntityId id;

    /**
     * Password Reset Token Generated
     */
    @Column(name = "CREATED_TIMESTAMP", nullable = false, length = 19)
    private LocalDateTime createdTimestamp;

    /**
     * Public Constructor
     */
	public UserPasswordResetEntity() {
	}

    /**
     * Public Constructor
     *
     * @param id UserPasswordResetEntityId
     * @param createdTimestamp Created Time Stamp
     */
	public UserPasswordResetEntity(UserPasswordResetEntityId id,LocalDateTime createdTimestamp) {
		this.id = id;
		this.createdTimestamp = createdTimestamp;
	}

    /**
     * Get Id
     * @return UserPasswordResetEntityId
     */
	public UserPasswordResetEntityId getId() {
		return this.id;
	}

    /**
     * Set UserPasswordResetEntityId
     * @param id UserPasswordResetEntityId
     */
	public void setId(UserPasswordResetEntityId id) {
		this.id = id;
	}

    /**
     * Get Created TimeStamp
     * @return LocalDateTime
     */
	public LocalDateTime getCreatedTimestamp() {
		return this.createdTimestamp;
	}

    /**
     * set Created TimeStamp
     * @param createdTimestamp LocalDateTime
     */
	public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

}
