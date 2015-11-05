/**
 * Name      : com.malcolm.qme.core.domain.QuestionHit.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe Question Hit Domain Class
 */
package com.malcolm.qme.core.domain;

import java.util.Objects;

/**
 * @author Malcolm
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
     * @param questionID Question ID
     * @param categoryID Category ID
     * @param questionHit Question Hit
     * @param rightCount Right Count
     * @param wrongCount Wrong Count
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
     * @return Question ID
     */
    public Long getQuestionID() {
        return questionID;
    }

    /**
     * Get Category ID
     * @return Category ID
     */
    public Long getCategoryID() {
        return categoryID;
    }

    /**
     * Get Question Hit
     * @return Question Hit
     */
    public Long getQuestionHit() {
        return questionHit;
    }

    /**
     * Get Right Count
     * @return Right Count
     */
    public Long getRightCount() {
        return rightCount;
    }

    /**
     * Get Wrong Count
     * @return Wrong Count
     */
    public Long getWrongCount() {
        return wrongCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionHit that = (QuestionHit) o;
        return Objects.equals(questionID, that.questionID) &&
                Objects.equals(categoryID, that.categoryID) &&
                Objects.equals(questionHit, that.questionHit) &&
                Objects.equals(rightCount, that.rightCount) &&
                Objects.equals(wrongCount, that.wrongCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionID, categoryID, questionHit, rightCount, wrongCount);
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
