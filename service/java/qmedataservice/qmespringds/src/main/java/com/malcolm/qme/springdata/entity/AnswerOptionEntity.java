/**
 * Name      : com.malcolm.qme.springdata.entity.AnswerOptionEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Answer Option Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "ANSWER_OPTION", catalog = "qme")
public final class AnswerOptionEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1005970152725681621L;

	/**
	 * Answer Option ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OPTION_ID", unique = true, nullable = false)
	private Long optionId;

	/**
	 * Question ID
	 */
	@Column(name = "QUESTION_ID", nullable = false)
	private Long questionId;

	/**
	 * Option Text
	 */
	@Column(name = "OPTION_TEXT", nullable = false)
	private String optionText;

	/**
	 * Option Correct
	 */
	@Column(name = "ISCORRECT")
	private Byte iscorrect;

	/**
	 * Public Constructor
	 */
	public AnswerOptionEntity() {
	}

	/**
	 * Public Constructor
	 *
	 * @param questionId Question ID
	 * @param optionText Answer Option Text
	 * @param iscorrect Answer Option is Correct
	 */
	public AnswerOptionEntity(Long questionId, String optionText, Byte iscorrect) {
		this.questionId = questionId;
		this.optionText = optionText;
		this.iscorrect = iscorrect;
	}

	/**
	 * @return the optionId
	 */
	public Long getOptionId() {
		return optionId;
	}

	/**
	 * @param optionId
	 *            the optionId to set
	 */
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	/**
	 * @return the questionId
	 */
	public Long getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId
	 *            the questionId to set
	 */
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the optionText
	 */
	public String getOptionText() {
		return optionText;
	}

	/**
	 * @param optionText
	 *            the optionText to set
	 */
	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	/**
	 * @return the iscorrect
	 */
	public Byte getIscorrect() {
		return iscorrect;
	}

	/**
	 * @param iscorrect
	 *            the iscorrect to set
	 */
	public void setIscorrect(Byte iscorrect) {
		this.iscorrect = iscorrect;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((iscorrect == null) ? 0 : iscorrect.hashCode());
		result = (prime * result)
				+ ((optionId == null) ? 0 : optionId.hashCode());
		result = (prime * result)
				+ ((optionText == null) ? 0 : optionText.hashCode());
		result = (prime * result)
				+ ((questionId == null) ? 0 : questionId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
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
		final AnswerOptionEntity other = (AnswerOptionEntity) obj;
		if (iscorrect == null) {
			if (other.iscorrect != null) {
				return false;
			}
		} else if (!iscorrect.equals(other.iscorrect)) {
			return false;
		}
		if (optionId == null) {
			if (other.optionId != null) {
				return false;
			}
		} else if (!optionId.equals(other.optionId)) {
			return false;
		}
		if (optionText == null) {
			if (other.optionText != null) {
				return false;
			}
		} else if (!optionText.equals(other.optionText)) {
			return false;
		}
		if (questionId == null) {
			if (other.questionId != null) {
				return false;
			}
		} else if (!questionId.equals(other.questionId)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AnswerOptionEntity [optionId=" + optionId + ", questionId="
				+ questionId + ", optionText=" + optionText + ", iscorrect="
				+ iscorrect + "]";
	}
}
