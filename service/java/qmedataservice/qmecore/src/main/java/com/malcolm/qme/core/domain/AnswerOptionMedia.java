/**
 * Name      : com.malcolm.qme.core.domain.AnswerOptionMedia.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Answer Option Media Domain Class
 */

package com.malcolm.qme.core.domain;


/**
 * @author malcolm
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
	 * @param answerOptionMediaID AnswerOption Media ID
	 * @param answerOptionID AnswerOption ID
	 * @param mediaTypeID Media Type ID
	 * @param media Media Bytes
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
	 * @param answerOptionID AnswerOption ID
	 * @param mediaTypeID Media Type ID
	 * @param media Media Bytes
	 */
	public AnswerOptionMedia(Long answerOptionID, Integer mediaTypeID, byte[] media) {
		this.answerOptionMediaID = 0L;
		this.answerOptionID = answerOptionID;
		this.mediaTypeID = mediaTypeID;
		this.media = media;
	}

	/**
	 * Get Answer Option Media Id
	 * @return Answer Option Media Id
	 */
	public Long getAnswerOptionMediaID() {
		return answerOptionMediaID;
	}

	/**
	 * Get Answer Option ID
	 * @return Answer Option ID
	 */
	public Long getAnswerOptionID() {
		return answerOptionID;
	}

	/**
	 * Get Media
	 * @return Get Media Bytes
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AnswerOptionMedia that = (AnswerOptionMedia) o;

		if (!answerOptionMediaID.equals(that.answerOptionMediaID)) return false;
		if (!answerOptionID.equals(that.answerOptionID)) return false;
		return mediaTypeID.equals(that.mediaTypeID);

	}

	@Override
	public int hashCode() {
		int result = answerOptionMediaID.hashCode();
		result = 31 * result + answerOptionID.hashCode();
		result = 31 * result + mediaTypeID.hashCode();
		return result;
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

