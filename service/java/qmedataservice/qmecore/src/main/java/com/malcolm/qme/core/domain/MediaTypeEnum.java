package com.malcolm.qme.core.domain;

/**
 * Created by malcolm on 5/2/15.
 */
public enum MediaTypeEnum {
	IMAGE(1,"IMAGE"),
    VIDEO(2,"VIDEO"),
    AUDIO(3,"AUDIO"),
    FILE(4,"FILE")
	;
	/**
     * Media Type ID
     */
    private final Integer id;
	/**
     * Media Type Code
     */
    private final String value;
    
    /**
     * Get Media Type Id
     * 
     * @return
     */
    public Integer getId() {
		return id;
	}

	/**
	 * Get Media Type Value
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
     * Enum Constructor
     * 
     * @param id
     * @param value
     */
    MediaTypeEnum( final Integer id , final String value ) {

    	this.id = id; 
        
    	this.value = value;
    }
    
    /**
     * Convert a string value to Enum
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
    

    /**
     * Convert a id value to a Enum
     *
     * @param value the value
     * @return the config type
     */
    public static MediaTypeEnum fromId( final Integer id ) {
        for ( final MediaTypeEnum type : values() ) {
            if ( type.getId().equals( id ) ) {
                return type;
            }
        }
        return null;
    }
}
