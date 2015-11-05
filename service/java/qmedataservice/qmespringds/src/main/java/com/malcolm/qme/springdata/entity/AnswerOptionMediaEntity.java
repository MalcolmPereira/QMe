/**
 * Name      : com.malcolm.qme.springdata.entity.AnswerOptionMediaEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : Answer Option Media Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "ANSWER_OPTION_MEDIA", catalog = "qme")
public class AnswerOptionMediaEntity implements java.io.Serializable {

	private static final long serialVersionUID = -7762543847542101433L;

	/**
	 *Option Media ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OPTION_MEDIA_ID", unique = true, nullable = false)
	private Long optionMediaId;

	/**
	 * Option ID
	 */
	@Column(name = "OPTION_ID", nullable = false)
	private Long optionId;

	/**
	 * Media Type ID
	 */
	@Column(name = "MEDIA_TYPE_ID", nullable = false)
	private Integer mediaTypeId;

	/**
	 * Option Media
	 */
	@Column(name = "OPTION_MEDIA", nullable = false)
	private byte[] optionMedia;

	/**
	 * Public Constructor
	 */
	public AnswerOptionMediaEntity() {
	}

	/**
	 * Public Constructor
	 *
	 * @param optionId Answer Option Id
	 * @param mediaTypeId Media Type ID
	 * @param optionMedia Option Media Byte Array
	 */
	public AnswerOptionMediaEntity(Long optionId, Integer mediaTypeId,
			byte[] optionMedia) {
		this.optionId = optionId;
		this.mediaTypeId = mediaTypeId;
		this.optionMedia = optionMedia;
	}

	/**
	 * @return the optionMediaId
	 */
	public Long getOptionMediaId() {
		return optionMediaId;
	}

	/**
	 * @param optionMediaId the optionMediaId to set
	 */
	public void setOptionMediaId(Long optionMediaId) {
		this.optionMediaId = optionMediaId;
	}

	/**
	 * @return the optionId
	 */
	public Long getOptionId() {
		return optionId;
	}

	/**
	 * @param optionId the optionId to set
	 */
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
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
	 * @return the optionMedia
	 */
	public byte[] getOptionMedia() {
		return optionMedia;
	}

	/**
	 * @param optionMedia the optionMedia to set
	 */
	public void setOptionMedia(byte[] optionMedia) {
		this.optionMedia = optionMedia;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerOptionMediaEntity that = (AnswerOptionMediaEntity) o;

        if (!optionId.equals(that.optionId)) return false;
        return mediaTypeId.equals(that.mediaTypeId);

    }

    @Override
    public int hashCode() {
        int result = optionId.hashCode();
        result = 31 * result + mediaTypeId.hashCode();
        return result;
    }

    /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
	@Override
	public String toString() {
		return "AnswerOptionMediaEntity [optionMediaId=" + optionMediaId
				+ ", optionId=" + optionId + ", mediaTypeId=" + mediaTypeId
				+ "]";
	}

}
