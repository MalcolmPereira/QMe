/**
 * QMeAnswerReferenceMedia Model for REST Service Controller
 *
 * @author Malcolm
 */
package com.malcolm.qme.rest.model;

/**
 * QMeQuestion Model for REST Service Controller
 *
 * @author Malcolm
 */
public class QMeAnswerReferenceMedia extends QMeResource {
    /**
     * Generated Serialized Version Id
     */
    private static final long serialVersionUID = 1343257850480182832L;

    /**
     * Answer Reference Media Id
     */
    private Long answerRefMediaID;

    /**
     * Question Id
     */
    private Long questionID;

    /**
     * Media Type
     */
    private Integer mediaTypeID;

    /**
     * Media
     */
    private byte[] media;

    /**
     * Get Answer Reference Media Id
     * @return Answer Reference Media Id
     */
    public Long getAnswerRefMediaID() {
        return answerRefMediaID;
    }

    /**
     * Set Answer Reference Media Id
     * @param answerRefMediaID Answer Reference Media Id to set
     */
    public void setAnswerRefMediaID(Long answerRefMediaID) {
        this.answerRefMediaID = answerRefMediaID;
    }

    /**
     * Get Question Id
     * @return Question Id
     */
    public Long getQuestionID() {
        return questionID;
    }

    /**
     * Set Question Id
     * @param questionID Question Id to set
     */
    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    /**
     * Get Media Type Id
     * @return Media Type Id
     */
    public Integer getMediaTypeID() {
        return mediaTypeID;
    }

    /**
     * Set Media Type Id
     * @param mediaTypeID Media Type Id to set
     */
    public void setMediaTypeID(Integer mediaTypeID) {
        this.mediaTypeID = mediaTypeID;
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
