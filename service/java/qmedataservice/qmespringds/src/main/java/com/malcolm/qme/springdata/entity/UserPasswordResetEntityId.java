/**
 * Name      : com.malcolm.qme.springdata.entity.UserPasswordResetEntityId.java
 * Date      : 5/27/2015
 * Developer : Malcolm
 * Purpose   : User Password Reset Entity Id
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Malcolm
 */
@Embeddable
public class UserPasswordResetEntityId implements java.io.Serializable {

	private static final long serialVersionUID = 4901322327951965573L;

    /**
     * User ID
     */
    @Column(name = "USER_ID", nullable = false)
	private Long userId;

    /**
     * Reset Token
     */
    @Column(name = "RESET_TOKEN", unique = true, nullable = false)
	private Long resetToken;

    /**
     * Public Constructor
     */
	public UserPasswordResetEntityId() {
	}

    /**
     * Public Constructor
     *
     * @param userId User Id
     * @param resetToken Reset Token
     */
	public UserPasswordResetEntityId(Long userId, Long resetToken) {
		this.userId = userId;
		this.resetToken = resetToken;
	}

    /**
     * Get User ID
     * @return User ID
     */
	public Long getUserId() {
		return this.userId;
	}

    /**
     * Set User ID
     * @param userId User ID
     */
    public void setUserId(Long userId) {
		this.userId = userId;
	}

    /**
     * Get Reset Token
     * @return Reset Token
     */
	public Long getResetToken() {
		return this.resetToken;
	}

    /**
     * Set Reset Token
     * @param resetToken Reset Token
     */
	public void setResetToken(Long resetToken) {
		this.resetToken = resetToken;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof UserPasswordResetEntityId)) {
			return false;
		}
		final UserPasswordResetEntityId castOther = (UserPasswordResetEntityId) other;

		return ((this.getUserId() == castOther.getUserId()) || ((this
				.getUserId() != null) && (castOther.getUserId() != null) && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getResetToken() == castOther.getResetToken()) || ((this
						.getResetToken() != null)
						&& (castOther.getResetToken() != null) && this
						.getResetToken().equals(castOther.getResetToken())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = (37 * result)
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = (37
				* result)
				+ (getResetToken() == null ? 0 : this.getResetToken()
						.hashCode());
		return result;
	}

}
