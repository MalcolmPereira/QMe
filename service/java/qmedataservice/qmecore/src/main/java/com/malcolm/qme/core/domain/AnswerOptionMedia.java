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
    private final long answerOptionMediaID;

    /**
     * Answer Option Id
     */
    private final long answerOptionID;

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
    public AnswerOptionMedia(long answerOptionMediaID, long answerOptionID, MediaTypeEnum mediaType, byte[] media) {
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
    public AnswerOptionMedia(long answerOptionID, MediaTypeEnum mediaType, byte[] media) {
        this.answerOptionMediaID = 0;
        this.answerOptionID = answerOptionID;
        this.mediaType = mediaType;
        this.media = media;
    }

    /**
     * Get Answer Option Media ID
     * @return
     */
    public long getAnswerOptionMediaID() {
        return answerOptionMediaID;
    }

    /**
     * Get Answer Option ID
     * @return
     */
    public long getAnswerOptionID() {
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

        if (answerOptionID != that.answerOptionID) return false;
        if (answerOptionMediaID != that.answerOptionMediaID) return false;
        if (mediaType != that.mediaType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (answerOptionMediaID ^ (answerOptionMediaID >>> 32));
        result = 31 * result + (int) (answerOptionID ^ (answerOptionID >>> 32));
        result = 31 * result + mediaType.hashCode();
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

