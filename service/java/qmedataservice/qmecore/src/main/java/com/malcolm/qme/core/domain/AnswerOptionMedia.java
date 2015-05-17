/**
 * Name      : com.malcolm.qme.core.domain.AnswerOptionMedia.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Answer Option Media Domain Class
 */

package com.malcolm.qme.core.domain;


/**
 * @Author: malcolm
 */
public final class AnswerOptionMedia {

	/**
	 * Answer Option Media Id
	 */
	private final Long answerOptionMediaID;

	/**
	 * Answer Option Id
	 */
	private final Long answerOptionID;

	/**
	 * Media Type
	 */
	private final Integer mediaTypeID;

	/**
	 * Media
	 */
	private final byte[] media;

	/**
	 * Public Constructor
	 *
	 * @param answerOptionMediaID
	 * @param answerOptionID
	 * @param mediaTypeID
	 * @param media
	 */
	public AnswerOptionMedia(Long answerOptionMediaID, Long answerOptionID, Integer mediaTypeID, byte[] media) {
		this.answerOptionMediaID = answerOptionMediaID;
		this.answerOptionID = answerOptionID;
		this.mediaTypeID = mediaTypeID;
		this.media = media;
	}

	/**
	 * Public Constructor
	 *
	 * @param answerOptionID
	 * @param mediaTypeID
	 * @param media
	 */
	public AnswerOptionMedia(Long answerOptionID, Integer mediaTypeID, byte[] media) {
		this.answerOptionMediaID = 0L;
		this.answerOptionID = answerOptionID;
		this.mediaTypeID = mediaTypeID;
		this.media = media;
	}

	/**
	 * Get Answer Option Media Id
	 * @return
	 */
	public Long getAnswerOptionMediaID() {
		return answerOptionMediaID;
	}

	/**
	 * Get Answer Option ID
	 * @return
	 */
	public Long getAnswerOptionID() {
		return answerOptionID;
	}

	/**
	 * Get Media
	 * @return
	 */
	public byte[] getMedia() {
		return media;
	}

	/**
	 * @return the mediaTypeID
	 */
	public Integer getMediaTypeID() {
		return mediaTypeID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((answerOptionID == null) ? 0 : answerOptionID.hashCode());
		result = (prime
				* result)
				+ ((answerOptionMediaID == null) ? 0 : answerOptionMediaID
						.hashCode());
		result = (prime * result)
				+ ((mediaTypeID == null) ? 0 : mediaTypeID.hashCode());
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
		final AnswerOptionMedia other = (AnswerOptionMedia) obj;
		if (answerOptionID == null) {
			if (other.answerOptionID != null) {
				return false;
			}
		} else if (!answerOptionID.equals(other.answerOptionID)) {
			return false;
		}
		if (answerOptionMediaID == null) {
			if (other.answerOptionMediaID != null) {
				return false;
			}
		} else if (!answerOptionMediaID.equals(other.answerOptionMediaID)) {
			return false;
		}
		if (mediaTypeID == null) {
			if (other.mediaTypeID != null) {
				return false;
			}
		} else if (!mediaTypeID.equals(other.mediaTypeID)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AnswerOptionMedia [answerOptionMediaID=" + answerOptionMediaID
				+ ", answerOptionID=" + answerOptionID + ", mediaTypeID="
				+ mediaTypeID + "]";
	}

}

