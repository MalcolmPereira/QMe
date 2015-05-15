package com.malcolm.qme.springdata.entity;

// Generated May 14, 2015 7:02:12 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserQuestionLikesEntityId generated by hbm2java
 */
@Embeddable
public class UserQuestionLikesEntityId implements java.io.Serializable {

	private static final long serialVersionUID = -8147819392757860065L;

	private Long userId;
	private Long questionId;

	public UserQuestionLikesEntityId() {
	}

	public UserQuestionLikesEntityId(Long userId, Long questionId) {
		this.userId = userId;
		this.questionId = questionId;
	}

	@Column(name = "USER_ID", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "QUESTION_ID", nullable = false)
	public Long getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof UserQuestionLikesEntityId)) {
			return false;
		}
		final UserQuestionLikesEntityId castOther = (UserQuestionLikesEntityId) other;

		return ((this.getUserId() == castOther.getUserId()) || ((this
				.getUserId() != null) && (castOther.getUserId() != null) && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getQuestionId().equals(castOther.getQuestionId()))|| ((this
						.getQuestionId() != null)
						&& (castOther.getQuestionId() != null) && this
						.getQuestionId().equals(castOther.getQuestionId())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = (37 * result)
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = (37
				* result)
				+ (getQuestionId() == null ? 0 : this.getQuestionId()
						.hashCode());
		return result;
	}

}
