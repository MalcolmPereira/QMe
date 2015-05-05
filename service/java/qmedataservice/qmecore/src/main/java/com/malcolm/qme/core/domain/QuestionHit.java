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

        if (!questionID.equals(that.questionID)) return false;
        if (!categoryID.equals(that.categoryID)) return false;
        if (questionHit != null ? !questionHit.equals(that.questionHit) : that.questionHit != null) return false;
        if (rightCount != null ? !rightCount.equals(that.rightCount) : that.rightCount != null) return false;
        return !(wrongCount != null ? !wrongCount.equals(that.wrongCount) : that.wrongCount != null);

    }

    @Override
    public int hashCode() {
        int result = questionID.hashCode();
        result = 31 * result + categoryID.hashCode();
        result = 31 * result + (questionHit != null ? questionHit.hashCode() : 0);
        result = 31 * result + (rightCount != null ? rightCount.hashCode() : 0);
        result = 31 * result + (wrongCount != null ? wrongCount.hashCode() : 0);
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
