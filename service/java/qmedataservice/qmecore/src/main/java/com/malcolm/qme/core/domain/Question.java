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
    public long getLikes() {
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
    public long getCreateUserID() {
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
    public long getUpdateUserID() {
        return updateUserID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (!questionID.equals(question.questionID)) return false;
        if (!categoryID.equals(question.categoryID)) return false;
        if (!questionText.equals(question.questionText)) return false;
        if (!answer.equals(question.answer)) return false;
        if (likes != null ? !likes.equals(question.likes) : question.likes != null) return false;
        if (questionCreateDate != null ? !questionCreateDate.equals(question.questionCreateDate) : question.questionCreateDate != null)
            return false;
        if (!createUserID.equals(question.createUserID)) return false;
        if (questionUpdateDate != null ? !questionUpdateDate.equals(question.questionUpdateDate) : question.questionUpdateDate != null)
            return false;
        return updateUserID.equals(question.updateUserID);

    }

    @Override
    public int hashCode() {
        int result = questionID.hashCode();
        result = 31 * result + categoryID.hashCode();
        result = 31 * result + questionText.hashCode();
        result = 31 * result + answer.hashCode();
        result = 31 * result + (likes != null ? likes.hashCode() : 0);
        result = 31 * result + (questionCreateDate != null ? questionCreateDate.hashCode() : 0);
        result = 31 * result + createUserID.hashCode();
        result = 31 * result + (questionUpdateDate != null ? questionUpdateDate.hashCode() : 0);
        result = 31 * result + updateUserID.hashCode();
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
