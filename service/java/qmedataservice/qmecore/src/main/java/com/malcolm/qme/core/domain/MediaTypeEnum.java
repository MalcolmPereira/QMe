package com.malcolm.qme.core.domain;

/**
 * Created by malcolm on 5/2/15.
 */
public enum MediaTypeEnum {
    //TODO: Add Media Type by mime-types
    LINK("LINK"),
    IMAGE("IMAGE"),
    VIDEO("VIDEO"),
    FILE("FILE")
    ;

    /**
     * Media Type Value
     */
    private final String value;

    /**
     * Enum Constructor
     *
     * @param value
     */
    MediaTypeEnum( final String value ) {

        this.value = value;
    }

    /**
     * Gets the String value
     *
     * @return the value
     */
    public String getValue() {

        return this.value;
    }


    /**
     * Convert a string value to a ConfigType
     *
     * @param value the value
     * @return the config type
     */
    public static MediaTypeEnum fromValue( final String value ) {
        for ( final MediaTypeEnum type : values() ) {
            if ( type.getValue().equals( value ) ) {
                return type;
            }
        }
        return null;
    }
}
