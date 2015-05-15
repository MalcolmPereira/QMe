/**
 * Name      : com.malcolm.qme.core.domain.MediaType.java
 * Date      : 5/141/2015
 * Developer : Malcolm
 * Purpose   : QMe MediaType Domain Class
 */
package com.malcolm.qme.core.domain;

/**
 * @Author Malcolm
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
	 * Media Type Desc
	 */
	private final String mediaTypeDesc;

	/**
	 * Public Constructor
	 *
	 * @param mediaTypeID
	 * @param mediaType
	 * @param mediaTypeDesc
	 */
	public MediaType(Integer mediaTypeID, String mediaType, String mediaTypeDesc) {
		this.mediaTypeID = mediaTypeID;
		this.mediaType = mediaType;
		this.mediaTypeDesc = mediaTypeDesc;
	}

	/**
	 * Public Constructor
	 *
	 * @param mediaType
	 * @param mediaTypeDesc
	 */
	public MediaType(String mediaType, String mediaTypeDesc) {
		this.mediaTypeID = 0;
		this.mediaType = mediaType;
		this.mediaTypeDesc = mediaTypeDesc;
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

	/**
	 * @return the mediaTypeDesc
	 */
	public String getMediaTypeDesc() {
		return mediaTypeDesc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((mediaType == null) ? 0 : mediaType.hashCode());
		result = (prime * result)
				+ ((mediaTypeDesc == null) ? 0 : mediaTypeDesc.hashCode());
		result = (prime * result)
				+ ((mediaTypeID == null) ? 0 : mediaTypeID.hashCode());
		return result;
	}

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
		if (mediaTypeDesc == null) {
			if (other.mediaTypeDesc != null) {
				return false;
			}
		} else if (!mediaTypeDesc.equals(other.mediaTypeDesc)) {
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

	@Override
	public String toString() {
		return "MediaType [mediaTypeID=" + mediaTypeID + ", mediaType="
				+ mediaType + ", mediaTypeDesc=" + mediaTypeDesc + "]";
	}
}
