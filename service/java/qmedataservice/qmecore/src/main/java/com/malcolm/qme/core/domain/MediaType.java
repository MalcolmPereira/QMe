/**
 * Name      : com.malcolm.qme.core.domain.MediaType.java
 * Date      : 5/141/2015
 * Developer : Malcolm
 * Purpose   : QMe MediaType Domain Class
 */
package com.malcolm.qme.core.domain;

import java.util.Objects;

/**
 * @author Malcolm
 */
public final class MediaType {
	/**
	 * Media Type Id
	 */
	private final Integer mediaTypeID;
	/**
	 * Media Type Name
	 */
	private final String mediaType;

	/**
	 * Public Constructor
	 *
	 * @param mediaTypeID Media Type ID
	 * @param mediaType Media Mime Type
	 */
	public MediaType(Integer mediaTypeID, String mediaType) {
		super();
		this.mediaTypeID = mediaTypeID;
		this.mediaType = mediaType;
	}

	/**
	 * Public Constructor
	 *
	 * @param mediaType Media Mime Type
	 */
	public MediaType(String mediaType) {
		super();
		this.mediaTypeID = 0;
		this.mediaType = mediaType;
	}

	/**
	 * @return the mediaTypeID
	 */
	public Integer getMediaTypeID() {
		return mediaTypeID;
	}

	/**
	 * @return the mediaType
	 */
	public String getMediaType() {
		return mediaType;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MediaType mediaType1 = (MediaType) o;
		return Objects.equals(mediaTypeID, mediaType1.mediaTypeID) &&
				Objects.equals(mediaType, mediaType1.mediaType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mediaTypeID, mediaType);
	}

	@Override
	public String toString() {
		return "MediaType [mediaTypeID=" + mediaTypeID + ", mediaType="
				+ mediaType + "]";
	}

}
