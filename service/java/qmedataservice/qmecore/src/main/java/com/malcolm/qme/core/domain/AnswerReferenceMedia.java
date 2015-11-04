/**
 * Name      : com.malcolm.qme.core.domain.AnswerReferenceMedia.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Answer Reference Media Domain Class
 */

package com.malcolm.qme.core.domain;

/**
 * @author Malcolm
 */
public final class AnswerReferenceMedia {

	/**
	 * Answer Reference Media Id
	 */
	private final Long answerRefMediaID;

	/**
	 * Question Id
	 */
	private final Long questionID;

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
	 * @param answerRefMediaID Answer Reference Media ID
	 * @param mediaTypeID Media Type ID
	 * @param media Media Byte Array
	 */
	public AnswerReferenceMedia(Long answerRefMediaID, Long questionID, Integer mediaTypeID, byte[] media) {
		this.answerRefMediaID = answerRefMediaID;
		this.questionID = questionID;
		this.mediaTypeID = mediaTypeID;
		this.media = media;
	}

	/**
	 * Public Constructor
	 *
	 * @param questionID Question ID
	 * @param mediaTypeID Media Type ID
	 * @param media Media Byte Array
	 */
	public AnswerReferenceMedia(Long questionID, Integer mediaTypeID,byte[] media) {
		this.answerRefMediaID = 0L;
		this.questionID = questionID;
		this.mediaTypeID = mediaTypeID;
		this.media = media;
	}

	/**
	 * Get Answer Option Media ID
	 * @return Answer Option Media ID
	 */
	public Long getAnswerRefMediaID() {
		return answerRefMediaID;
	}

	/**
	 * Get Answer Option ID
	 * @return Answer Option ID
	 */
	public Long getQuestionID() {
		return questionID;
	}

	/**
	 * Get Media
	 * @return Media byte Array
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

		AnswerReferenceMedia that = (AnswerReferenceMedia) o;

		if (!answerRefMediaID.equals(that.answerRefMediaID)) return false;
		if (!questionID.equals(that.questionID)) return false;
		return mediaTypeID.equals(that.mediaTypeID);

	}

	@Override
	public int hashCode() {
		int result = answerRefMediaID.hashCode();
		result = 31 * result + questionID.hashCode();
		result = 31 * result + mediaTypeID.hashCode();
		return result;
	}

	/* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
	@Override
	public String toString() {
		return "AnswerReferenceMedia [answerRefMediaID=" + answerRefMediaID
				+ ", questionID=" + questionID + ", mediaTypeID=" + mediaTypeID
				+ "]";
	}
}
