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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime
				* result)
				+ ((answerRefMediaID == null) ? 0 : answerRefMediaID.hashCode());
		result = (prime * result)
				+ ((mediaTypeID == null) ? 0 : mediaTypeID.hashCode());
		result = (prime * result)
				+ ((questionID == null) ? 0 : questionID.hashCode());
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
		final AnswerReferenceMedia other = (AnswerReferenceMedia) obj;
		if (answerRefMediaID == null) {
			if (other.answerRefMediaID != null) {
				return false;
			}
		} else if (!answerRefMediaID.equals(other.answerRefMediaID)) {
			return false;
		}
		if (mediaTypeID == null) {
			if (other.mediaTypeID != null) {
				return false;
			}
		} else if (!mediaTypeID.equals(other.mediaTypeID)) {
			return false;
		}
		if (questionID == null) {
			if (other.questionID != null) {
				return false;
			}
		} else if (!questionID.equals(other.questionID)) {
			return false;
		}
		return true;
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
