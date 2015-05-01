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
    private final long questionID;

    /**
     * Category Id
     */
    private final long categoryID;

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
    private final long likes;

    /**
     * Question Create Date
     */
    private final Date questionCreateDate;
    /**
     * Question Create User Id
     */
    private final long  createUserID;

    /**
     * Question Update Date
     */
    private final Date questionUpdateDate;
    /**
     * Update User Id
     */
    private final long  updateUserID;

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
    public Question(long questionID, long categoryID, String questionText, String answer, long likes, Date questionCreateDate, long createUserID, Date questionUpdateDate, long updateUserID) {
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
    public Question(long categoryID, String questionText, String answer, long createUserID) {
        this.questionID = 0;
        this.categoryID = categoryID;
        this.questionText = questionText;
        this.answer = answer;
        this.likes = 0;
        this.questionCreateDate = null;
        this.createUserID = createUserID;
        this.questionUpdateDate = null;
        this.updateUserID = 0;
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

        if (getQuestionID() != question.getQuestionID()) return false;
        if (getCategoryID() != question.getCategoryID()) return false;
        if (getLikes() != question.getLikes()) return false;
        if (getCreateUserID() != question.getCreateUserID()) return false;
        if (getUpdateUserID() != question.getUpdateUserID()) return false;
        if (!getQuestionText().equals(question.getQuestionText())) return false;
        if (!getAnswer().equals(question.getAnswer())) return false;
        if (!getQuestionCreateDate().equals(question.getQuestionCreateDate())) return false;
        return getQuestionUpdateDate().equals(question.getQuestionUpdateDate());

    }

    @Override
    public int hashCode() {
        int result = (int) (getQuestionID() ^ (getQuestionID() >>> 32));
        result = 31 * result + (int) (getCategoryID() ^ (getCategoryID() >>> 32));
        result = 31 * result + getQuestionText().hashCode();
        result = 31 * result + getAnswer().hashCode();
        result = 31 * result + (int) (getLikes() ^ (getLikes() >>> 32));
        result = 31 * result + getQuestionCreateDate().hashCode();
        result = 31 * result + (int) (getCreateUserID() ^ (getCreateUserID() >>> 32));
        result = 31 * result + getQuestionUpdateDate().hashCode();
        result = 31 * result + (int) (getUpdateUserID() ^ (getUpdateUserID() >>> 32));
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
