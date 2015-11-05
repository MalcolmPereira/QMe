/**
 * Name      : com.malcolm.qme.springdata.entity.AnswerReferenceMediaEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Answer Reference Media Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "ANSWER_REFERENCE_MEDIA", catalog = "qme")
public class AnswerReferenceMediaEntity implements java.io.Serializable {

	private static final long serialVersionUID = -1526844399566983846L;

	/**
	 * Answer Reference Media
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ANSWER_REF_MEDIA_ID", unique = true, nullable = false)
	private Long answerRefMediaId;

	/**
	 * Question ID
	 */
	@Column(name = "QUESTION_ID", nullable = false)
	private Long questionId;

	/**
	 * Media Type ID
	 */
	@Column(name = "MEDIA_TYPE_ID", nullable = false)
	private Integer mediaTypeId;

	/**
	 * Answer Reference Media
	 */
	@Column(name = "REF_MEDIA", nullable = false)
	private byte[] refMedia;

	/**
	 * Public Constructor
	 */
	public AnswerReferenceMediaEntity() {
	}

    /**
     * Public Constructor
     *
     * @param questionId Question Id
     * @param mediaTypeId Media Type Id
     * @param refMedia Media Reference
     */
    public AnswerReferenceMediaEntity(Long questionId, Integer mediaTypeId, byte[] refMedia) {
        this.questionId = questionId;
        this.mediaTypeId = mediaTypeId;
        this.refMedia = refMedia;
    }

    /**
	 * @return the answerRefMediaId
	 */
	public Long getAnswerRefMediaId() {
		return answerRefMediaId;
	}

	/**
	 * @param answerRefMediaId the answerRefMediaId to set
	 */
	public void setAnswerRefMediaId(Long answerRefMediaId) {
		this.answerRefMediaId = answerRefMediaId;
	}

	/**
	 * @return the questionId
	 */
	public Long getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the mediaTypeId
	 */
	public Integer getMediaTypeId() {
		return mediaTypeId;
	}

	/**
	 * @param mediaTypeId the mediaTypeId to set
	 */
	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	/**
	 * @return the refMedia
	 */
	public byte[] getRefMedia() {
		return refMedia;
	}

	/**
	 * @param refMedia the refMedia to set
	 */
	public void setRefMedia(byte[] refMedia) {
		this.refMedia = refMedia;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerReferenceMediaEntity that = (AnswerReferenceMediaEntity) o;

        if (!questionId.equals(that.questionId)) return false;
        return mediaTypeId.equals(that.mediaTypeId);

    }

    @Override
    public int hashCode() {
        int result = questionId.hashCode();
        result = 31 * result + mediaTypeId.hashCode();
        return result;
    }

    /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
	@Override
	public String toString() {
		return "AnswerReferenceMediaEntity [answerRefMediaId="
				+ answerRefMediaId + ", questionId=" + questionId
				+ ", mediaTypeId=" + mediaTypeId + "]";
	}

}
