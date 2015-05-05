/**
 * Name      : com.malcolm.qme.core.domain.QuestionHit.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe Question Hit Domain Class
 */
package com.malcolm.qme.core.domain;

/**
 * @Author Malcolm
 */
public final class QuestionHit {

    /**
     * Question Id
     */
    private final Long questionID;

    /**
     * Category Id
     */
    private final Long categoryID;

    /**
     * Question Hit
     */
    private final Long questionHit;

    /**
     * Right Count
     */
    private final Long rightCount;

    /**
     * Wrong Count
     */
    private final Long wrongCount;

    /**
     * Public Constructor
     *
     * @param questionID
     * @param categoryID
     * @param questionHit
     * @param rightCount
     * @param wrongCount
     */
    public QuestionHit(Long questionID, Long categoryID, Long questionHit, Long rightCount, Long wrongCount) {
        this.questionID = questionID;
        this.categoryID = categoryID;
        this.questionHit = questionHit;
        this.rightCount = rightCount;
        this.wrongCount = wrongCount;
    }

    /**
     * Get Question ID
     * @return
     */
    public Long getQuestionID() {
        return questionID;
    }

    /**
     * Get Category ID
     * @return
     */
    public Long getCategoryID() {
        return categoryID;
    }

    /**
     * Get Question Hit
     * @return
     */
    public Long getQuestionHit() {
        return questionHit;
    }

    /**
     * Get Right Count
     * @return
     */
    public Long getRightCount() {
        return rightCount;
    }

    /**
     * Get Wrong Count
     * @return
     */
    public Long getWrongCount() {
        return wrongCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionHit that = (QuestionHit) o;

        if (!getQuestionID().equals(that.getQuestionID())) return false;
        if (!getCategoryID().equals(that.getCategoryID())) return false;
        if (getQuestionHit() != null ? !getQuestionHit().equals(that.getQuestionHit()) : that.getQuestionHit() != null)
            return false;
        if (getRightCount() != null ? !getRightCount().equals(that.getRightCount()) : that.getRightCount() != null)
            return false;
        return !(getWrongCount() != null ? !getWrongCount().equals(that.getWrongCount()) : that.getWrongCount() != null);

    }

    @Override
    public int hashCode() {
        int result = getQuestionID().hashCode();
        result = 31 * result + getCategoryID().hashCode();
        result = 31 * result + (getQuestionHit() != null ? getQuestionHit().hashCode() : 0);
        result = 31 * result + (getRightCount() != null ? getRightCount().hashCode() : 0);
        result = 31 * result + (getWrongCount() != null ? getWrongCount().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuestionHit{" +
                "questionID=" + questionID +
                ", categoryID=" + categoryID +
                ", questionHit=" + questionHit +
                ", rightCount=" + rightCount +
                ", wrongCount=" + wrongCount +
                '}';
    }
}
