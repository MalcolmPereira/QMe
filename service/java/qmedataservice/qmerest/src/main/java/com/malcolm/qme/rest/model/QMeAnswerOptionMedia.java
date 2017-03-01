/**
 * QMeAnswerOptionMedia Model for REST Service Controller
 *
 * @author Malcolm
 */
package com.malcolm.qme.rest.model;

/**
 * QMeAnswerOptionMedia Model for REST Service Controller
 *
 * @author Malcolm
 */
public class QMeAnswerOptionMedia extends QMeResource {
    /**
     * Generated Serialized Version Id
     */
    private static final long serialVersionUID = 1313257850480182812L;

    /**
     * Answer Option Media Id
     */
    private Long answerOptionMediaID;

    /**
     * Answer Option Id
     */
    private Long answerOptionID;

    /**
     * Media Type
     */
    private String mediaType;

    /**
     * Media
     */
    private byte[] media;

    /**
     * Get Answer Option Media Id
     * @return Answer Option Media Id
     */
    public Long getAnswerOptionMediaID() {
        return answerOptionMediaID;
    }

    /**
     * Set Answer Option Media Id
     * @param answerOptionMediaID Answer Option Media Id to set
     */
    public void setAnswerOptionMediaID(Long answerOptionMediaID) {
        this.answerOptionMediaID = answerOptionMediaID;
    }

    /**
     * Get Answer Option Id
     * @return Answer Option Id
     */
    public Long getAnswerOptionID() {
        return answerOptionID;
    }

    /**
     * Set Answer Option Id
     * @param answerOptionID Answer Option Id to set
     */
    public void setAnswerOptionID(Long answerOptionID) {
        this.answerOptionID = answerOptionID;
    }

    /**
     * Get Media Type Id
     * @return Media Type Id
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * Set Media Type Id
     * @param mediaType Media
     */
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * Get Media Byte Array
     * @return Media Byte Array
     */
    public byte[] getMedia() {
        return media;
    }

    /**
     * Set Media Byte Array
     * @param media Media Byte Array to set
     */
    public void setMedia(byte[] media) {
        this.media = media;
    }
}
