/**
 * Name      : com.malcolm.qme.core.domain.Question.java
 * Date      : 5/1/2015
 * Developer : Malcolm
 * Purpose   : QMe Question Domain Class
 */
package com.malcolm.qme.core.domain;

import java.util.Date;

/**
 * @Author Malcolm
 */
public final class Question {
    /**
     * Question Id
     */
    private final Long questionID;

    /**
     * Category Id
     */
    private final Long categoryID;

    /**
     * Question Text
     */
    private final String questionText;

    /**
     * Question Answer
     */
    private final String answer;

    /**
     * Question Likes
     */
    private final Long likes;

    /**
     * Question Create Date
     */
    private final Date questionCreateDate;
    /**
     * Question Create User Id
     */
    private final Long  createUserID;

    /**
     * Question Update Date
     */
    private final Date questionUpdateDate;
    /**
     * Update User Id
     */
    private final Long  updateUserID;

    /**
     * Public Constructor
     *
     * @param questionID
     * @param categoryID
     * @param questionText
     * @param answer
     * @param likes
     * @param questionCreateDate
     * @param createUserID
     * @param questionUpdateDate
     * @param updateUserID
     */
    public Question(Long questionID, Long categoryID, String questionText, String answer, Long likes, Date questionCreateDate, Long createUserID, Date questionUpdateDate, Long updateUserID) {
        this.questionID = questionID;
        this.categoryID = categoryID;
        this.questionText = questionText;
        this.answer = answer;
        this.likes = likes;
        this.questionCreateDate = questionCreateDate;
        this.createUserID = createUserID;
        this.questionUpdateDate = questionUpdateDate;
        this.updateUserID = updateUserID;
    }

    /**
     * Public Constructor
     *
     * @param categoryID
     * @param questionText
     * @param answer
     * @param createUserID
     */
    public Question(Long categoryID, String questionText, String answer, Long createUserID) {
        this.questionID = 0L;
        this.categoryID = categoryID;
        this.questionText = questionText;
        this.answer = answer;
        this.likes = 0L;
        this.questionCreateDate = null;
        this.createUserID = createUserID;
        this.questionUpdateDate = null;
        this.updateUserID = 0L;
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
     * Get Question Text
     * @return
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Get Asnwer
     * @return
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Get Likes
     * @return
     */
    public Long getLikes() {
        return likes;
    }

    /**
     * Get Question Create Date
     * @return
     */
    public Date getQuestionCreateDate() {
        return questionCreateDate;
    }

    /**
     * Get Question Create User
     * @return
     */
    public Long getCreateUserID() {
        return createUserID;
    }

    /**
     * Get Question Update Date
     * @return
     */
    public Date getQuestionUpdateDate() {
        return questionUpdateDate;
    }

    /**
     * Get Question Update User
     * @return
     */
    public Long getUpdateUserID() {
        return updateUserID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (!getQuestionID().equals(question.getQuestionID())) return false;
        if (!getCategoryID().equals(question.getCategoryID())) return false;
        if (!getQuestionText().equals(question.getQuestionText())) return false;
        if (!getAnswer().equals(question.getAnswer())) return false;
        if (getLikes() != null ? !getLikes().equals(question.getLikes()) : question.getLikes() != null) return false;
        if (getQuestionCreateDate() != null ? !getQuestionCreateDate().equals(question.getQuestionCreateDate()) : question.getQuestionCreateDate() != null)
            return false;
        if (!getCreateUserID().equals(question.getCreateUserID())) return false;
        if (getQuestionUpdateDate() != null ? !getQuestionUpdateDate().equals(question.getQuestionUpdateDate()) : question.getQuestionUpdateDate() != null)
            return false;
        return !(getUpdateUserID() != null ? !getUpdateUserID().equals(question.getUpdateUserID()) : question.getUpdateUserID() != null);

    }

    @Override
    public int hashCode() {
        int result = getQuestionID().hashCode();
        result = 31 * result + getCategoryID().hashCode();
        result = 31 * result + getQuestionText().hashCode();
        result = 31 * result + getAnswer().hashCode();
        result = 31 * result + (getLikes() != null ? getLikes().hashCode() : 0);
        result = 31 * result + (getQuestionCreateDate() != null ? getQuestionCreateDate().hashCode() : 0);
        result = 31 * result + getCreateUserID().hashCode();
        result = 31 * result + (getQuestionUpdateDate() != null ? getQuestionUpdateDate().hashCode() : 0);
        result = 31 * result + (getUpdateUserID() != null ? getUpdateUserID().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionID=" + questionID +
                ", categoryID=" + categoryID +
                ", questionText='" + questionText + '\'' +
                ", answer='" + answer + '\'' +
                ", likes=" + likes +
                ", questionCreateDate=" + questionCreateDate +
                ", createUserID=" + createUserID +
                ", questionUpdateDate=" + questionUpdateDate +
                ", updateUserID=" + updateUserID +
                '}';
    }
}
