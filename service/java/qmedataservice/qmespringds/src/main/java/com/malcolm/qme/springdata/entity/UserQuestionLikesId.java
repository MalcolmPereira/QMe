/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuestionLikesId.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : UserQuestionLikesId Entity Class
 */
// Generated May 3, 2015 6:11:19 PM by Hibernate Tools 4.3.1

package com.malcolm.qme.springdata.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserQuestionLikesId generated by hbm2java
 */
@Embeddable
public class UserQuestionLikesId implements java.io.Serializable {

	private static final long serialVersionUID = -7288214736112411597L;
	
	private int userId;
	private int questionId;

	public UserQuestionLikesId() {
	}

	public UserQuestionLikesId(int userId, int questionId) {
		this.userId = userId;
		this.questionId = questionId;
	}

	@Column(name = "USER_ID", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "QUESTION_ID", nullable = false)
	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserQuestionLikesId))
			return false;
		UserQuestionLikesId castOther = (UserQuestionLikesId) other;

		return (this.getUserId() == castOther.getUserId())
				&& (this.getQuestionId() == castOther.getQuestionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getUserId();
		result = 37 * result + this.getQuestionId();
		return result;
	}

}
