/**
 * Name      : com.malcolm.qme.core.domain.AnswerReferenceMedia.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Answer Reference Media Domain Class
 */

package com.malcolm.qme.core.domain;

/**
 * @Author: Malcolm
 */
public final class AnswerReferenceMedia {

    /**
     * Answer Reference Media Id
     */
    private final long answerRefMediaID;

    /**
     * Question Id
     */
    private final long questionID;

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
     * @param answerRefMediaID
     * @param questionID
     * @param mediaType
     * @param media
     */
    public AnswerReferenceMedia(long answerRefMediaID, long questionID, MediaTypeEnum mediaType, byte[] media) {
        this.answerRefMediaID = answerRefMediaID;
        this.questionID = questionID;
        this.mediaType = mediaType;
        this.media = media;
    }

    /**
     * Public Constructor
     *
     * @param questionID
     * @param mediaType
     * @param media
     */
    public AnswerReferenceMedia(long questionID, MediaTypeEnum mediaType, byte[] media) {
        this.answerRefMediaID = 0;
        this.questionID = questionID;
        this.mediaType = mediaType;
        this.media = media;
    }

    /**
     * Get Answer Ref Media
     * @return
     */
    public long getAnswerRefMediaID() {
        return answerRefMediaID;
    }

    /**
     * Get Question ID
     * @return
     */
    public long getQuestionID() {
        return questionID;
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

        AnswerReferenceMedia that = (AnswerReferenceMedia) o;

        if (answerRefMediaID != that.answerRefMediaID) return false;
        if (questionID != that.questionID) return false;
        if (mediaType != that.mediaType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (answerRefMediaID ^ (answerRefMediaID >>> 32));
        result = 31 * result + (int) (questionID ^ (questionID >>> 32));
        result = 31 * result + mediaType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AnswerReferenceMedia{" +
                "answerRefMediaID=" + answerRefMediaID +
                ", questionID=" + questionID +
                ", mediaType=" + mediaType +
                '}';
    }
}
