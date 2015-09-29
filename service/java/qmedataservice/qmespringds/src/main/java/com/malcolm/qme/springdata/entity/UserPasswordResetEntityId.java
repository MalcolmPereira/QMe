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
	private String resetToken;

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
	public UserPasswordResetEntityId(Long userId, String resetToken) {
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
	public String getResetToken() {
		return this.resetToken;
	}

    /**
     * Set Reset Token
     * @param resetToken Reset Token
     */
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPasswordResetEntityId that = (UserPasswordResetEntityId) o;

        return userId.equals(that.userId) && resetToken.equals(that.resetToken);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + resetToken.hashCode();
        return result;
    }
}
