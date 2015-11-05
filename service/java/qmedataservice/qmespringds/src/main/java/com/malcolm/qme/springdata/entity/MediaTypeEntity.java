/**
 * Name      : com.malcolm.qme.springdata.entity.MediaTypeEntity.java
 * Date      : 5/18/2015
 * Developer : Malcolm
 * Purpose   : MediaType Entity
 */
package com.malcolm.qme.springdata.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Malcolm
 */
@Entity
@Table(name = "MEDIA_TYPE", catalog = "qme", uniqueConstraints = @UniqueConstraint(columnNames = "MEDIA_MIME_TYPE"))
public class MediaTypeEntity implements java.io.Serializable {


	private static final long serialVersionUID = -3293971500327209006L;

	/**
	 * Media Type ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEDIA_TYPE_ID", unique = true, nullable = false)
	private Integer mediaTypeId;

	/**
	 * Media Mime Type
	 */
	@Column(name = "MEDIA_MIME_TYPE", unique = true, nullable = false, length = 50)
	private String mediaMimeType;

	/**
	 * Public Constructor
	 */
	public MediaTypeEntity() {
	}

	/**
	 * Public Constructor
	 *
	 * @param mediaMimeType Media Mime Type
	 */
	public MediaTypeEntity(String mediaMimeType) {
		this.mediaMimeType = mediaMimeType;
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
	 * @return the mediaMimeType
	 */
	public String getMediaMimeType() {
		return mediaMimeType;
	}

	/**
	 * @param mediaMimeType the mediaMimeType to set
	 */
	public void setMediaMimeType(String mediaMimeType) {
		this.mediaMimeType = mediaMimeType;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaTypeEntity that = (MediaTypeEntity) o;
        return Objects.equals(mediaTypeId, that.mediaTypeId) &&
                Objects.equals(mediaMimeType, that.mediaMimeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediaTypeId, mediaMimeType);
    }

    /* (non-Javadoc)
             * @see java.lang.Object#toString()
             */
	@Override
	public String toString() {
		return "MediaTypeEntity [mediaTypeId=" + mediaTypeId
				+ ", mediaMimeType=" + mediaMimeType + "]";
	}

}
