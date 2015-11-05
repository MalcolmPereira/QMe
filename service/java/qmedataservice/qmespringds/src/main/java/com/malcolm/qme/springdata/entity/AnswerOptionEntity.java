/**
 * Name      : com.malcolm.qme.springdata.entity.AnswerOptionEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Answer Option Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerOptionEntity that = (AnswerOptionEntity) o;

        if (!questionId.equals(that.questionId)) return false;
        if (!optionText.equals(that.optionText)) return false;
        return iscorrect.equals(that.iscorrect);

    }

    @Override
    public int hashCode() {
        int result = questionId.hashCode();
        result = 31 * result + optionText.hashCode();
        result = 31 * result + iscorrect.hashCode();
        return result;
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
