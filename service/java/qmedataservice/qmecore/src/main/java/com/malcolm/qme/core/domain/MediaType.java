/**
 * Name      : com.malcolm.qme.core.domain.MediaType.java
 * Date      : 5/141/2015
 * Developer : Malcolm
 * Purpose   : QMe MediaType Domain Class
 */
package com.malcolm.qme.core.domain;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((mediaType == null) ? 0 : mediaType.hashCode());
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
		final MediaType other = (MediaType) obj;
		if (mediaType == null) {
			if (other.mediaType != null) {
				return false;
			}
		} else if (!mediaType.equals(other.mediaType)) {
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
		return "MediaType [mediaTypeID=" + mediaTypeID + ", mediaType="
				+ mediaType + "]";
	}

}
