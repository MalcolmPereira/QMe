/**
 * Name      : com.malcolm.qme.core.domain.AnswerOptionMedia.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Answer Option Media Domain Class
 */

package com.malcolm.qme.core.domain;

/**
 * @Author: malcolm
 */
public final class AnswerOptionMedia {

    /**
     * Answer Option Media Id
     */
    private final Long answerOptionMediaID;

    /**
     * Answer Option Id
     */
    private final Long answerOptionID;

    /**
     * Media Type
     */
    private final MediaTypeEnum mediaType;

    /**
     * Media
     */
    private final byte[] media;

    /**
     * Public Constructor
     *
     * @param answerOptionMediaID
     * @param answerOptionID
     * @param mediaType
     * @param media
     */
    public AnswerOptionMedia(Long answerOptionMediaID, Long answerOptionID, MediaTypeEnum mediaType, byte[] media) {
        this.answerOptionMediaID = answerOptionMediaID;
        this.answerOptionID = answerOptionID;
        this.mediaType = mediaType;
        this.media = media;
    }

    /**
     * Public Constructor
     *
     * @param answerOptionID
     * @param mediaType
     * @param media
     */
    public AnswerOptionMedia(Long answerOptionID, MediaTypeEnum mediaType, byte[] media) {
        this.answerOptionMediaID = 0L;
        this.answerOptionID = answerOptionID;
        this.mediaType = mediaType;
        this.media = media;
    }

    /**
     * Get Answer Option Media Id
     * @return
     */
    public Long getAnswerOptionMediaID() {
        return answerOptionMediaID;
    }

    /**
     * Get Answer Option ID
     * @return
     */
    public Long getAnswerOptionID() {
        return answerOptionID;
    }

    /**
     * Get Media Type
     * @return
     */
    public MediaTypeEnum getMediaType() {
        return mediaType;
    }

    /**
     * Get Media
     * @return
     */
    public byte[] getMedia() {
        return media;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerOptionMedia that = (AnswerOptionMedia) o;

        if (!getAnswerOptionMediaID().equals(that.getAnswerOptionMediaID())) return false;
        if (!getAnswerOptionID().equals(that.getAnswerOptionID())) return false;
        return getMediaType() == that.getMediaType();

    }

    @Override
    public int hashCode() {
        int result = getAnswerOptionMediaID().hashCode();
        result = 31 * result + getAnswerOptionID().hashCode();
        result = 31 * result + getMediaType().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AnswerOptionMedia{" +
                "answerOptionMediaID=" + answerOptionMediaID +
                ", answerOptionID=" + answerOptionID +
                ", mediaType=" + mediaType +
                '}';
    }
}

