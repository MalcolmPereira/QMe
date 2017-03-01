/**
 * Name      : com.malcolm.qme.core.domain.MediaTypeEnum.java
 * Date      : 2/20/2017
 * Developer : Malcolm
 * Purpose   : QMe MediaType Enum
 */
package com.malcolm.qme.core.domain;

/**
 * @author Malcolm
 */
public enum MediaTypeEnum {

    LINK(1, "text/plain"),
    IMAGE_PNG(2, "image/png")
    ;

    /**
     * Media Type Id
     */
    private Integer mediaTypeId;

    /**
     * Media Type
     */
    private String mediaType;

    /**
     * Media Type Enum Constructor
     * @param mediaTypeId media type Id
     * @param mediaType media type
     */
    MediaTypeEnum(Integer mediaTypeId, String mediaType){
        this.mediaTypeId  = mediaTypeId;
        this.mediaType = mediaType;
    }

    /**
     * Get Media Type Id
     * @return mediaTypeId
     */
    public Integer getMediaTypeId() {
        return mediaTypeId;
    }

    /**
     * Get Media Type
     * @return mediaType
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * Get Media Type Enum from String
     * @param mediaType
     * @return
     */
    public static MediaTypeEnum fromValue(String mediaType){
        for (MediaTypeEnum mediaTypeEnum:MediaTypeEnum.values()) {
            if(mediaTypeEnum.getMediaType().equalsIgnoreCase(mediaType)){
                return mediaTypeEnum;
            }
        }
        return null;
    }

    /**
     * Get Media Type Enum from String
     * @param mediaType
     * @return
     */
    public static MediaTypeEnum fromValue(Integer mediaType){
        for (MediaTypeEnum mediaTypeEnum:MediaTypeEnum.values()) {
            if(mediaTypeEnum.getMediaTypeId() == mediaType){
                return mediaTypeEnum;
            }
        }
        return null;
    }

    /**
     * Return Supported Media Types
     * @return String
     */
    public static String supportedMediaTypes(){
        StringBuilder strBuilder = new StringBuilder();
        for (MediaTypeEnum mediaTypeEnum : MediaTypeEnum.values()) {
            strBuilder.append(mediaTypeEnum.getMediaTypeId());
            strBuilder.append(" - ");
            strBuilder.append(mediaTypeEnum.getMediaType());
        }
        return strBuilder.toString();
    }


}
