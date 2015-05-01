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
    private final long questionID;

    /**
     * Category Id
     */
    private final long categoryID;

    /**
     * Question Hit
     */
    private final long questionHit;

    /**
     * Right Count
     */
    private final long rightCount;

    /**
     * Wrong Count
     */
    private final long wrongCount;

    /**
     *
     * @param questionID
     * @param categoryID
     * @param questionHit
     * @param rightCount
     * @param wrongCount
     */
    public QuestionHit(long questionID, long categoryID, long questionHit, long rightCount, long wrongCount) {
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
    public long getQuestionID() {
        return questionID;
    }

    /**
     * Get Category ID
     * @return
     */
    public long getCategoryID() {
        return categoryID;
    }

    /**
     * Get Question Hit
     * @return
     */
    public long getQuestionHit() {
        return questionHit;
    }

    /**
     * Get Right Count
     * @return
     */
    public long getRightCount() {
        return rightCount;
    }

    /**
     * Get Wrong Count
     * @return
     */
    public long getWrongCount() {
        return wrongCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionHit that = (QuestionHit) o;

        if (getQuestionID() != that.getQuestionID()) return false;
        if (getCategoryID() != that.getCategoryID()) return false;
        if (getQuestionHit() != that.getQuestionHit()) return false;
        if (getRightCount() != that.getRightCount()) return false;
        return getWrongCount() == that.getWrongCount();

    }

    @Override
    public int hashCode() {
        int result = (int) (getQuestionID() ^ (getQuestionID() >>> 32));
        result = 31 * result + (int) (getCategoryID() ^ (getCategoryID() >>> 32));
        result = 31 * result + (int) (getQuestionHit() ^ (getQuestionHit() >>> 32));
        result = 31 * result + (int) (getRightCount() ^ (getRightCount() >>> 32));
        result = 31 * result + (int) (getWrongCount() ^ (getWrongCount() >>> 32));
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
