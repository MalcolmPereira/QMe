/**
 * Name      : com.malcolm.qme.core.domain.Quiz.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Domain Class
 */

package com.malcolm.qme.core.domain;

import java.util.Date;

/**
 * @Author: Malcolm
 */
public final class Quiz {
    /**
     * Quiz Id
     */
    private final long quizID;

    /**
     * Quiz Name
     */
    private final String quizName;

    /**
     * Quiz Desc
     */
    private final String quizDesc;

    /**
     * Category Id
     */
    private final long categoryID;

    /**
     * Quiz Likes
     */
    private final long likes;

    /**
     * Quiz Hits
     */
    private final long quizHit;

    /**
     * Quiz Max Attempts
     */
    private final long quizMaxAttempts;

    /**
     * Quiz Create Date
     */
    private final Date quizCreateDate;

    /**
     * Quiz Create User Id
     */
    private final long  createUserID;

    /**
     * Quiz Update Date
     */
    private final Date quizUpdateDate;

    /**
     * Update User Id
     */
    private final long  updateUserID;

    /**
     * Public Constructor
     *
     * @param quizID
     * @param quizName
     * @param quizDesc
     * @param categoryID
     * @param likes
     * @param quizHit
     * @param quizMaxAttempts
     * @param quizCreateDate
     * @param createUserID
     * @param quizUpdateDate
     * @param updateUserID
     */
    public Quiz(long quizID, String quizName, String quizDesc, long categoryID, long likes, long quizHit, long quizMaxAttempts, Date quizCreateDate, long createUserID, Date quizUpdateDate, long updateUserID) {
        this.quizID = quizID;
        this.quizName = quizName;
        this.quizDesc = quizDesc;
        this.categoryID = categoryID;
        this.likes = likes;
        this.quizHit = quizHit;
        this.quizMaxAttempts = quizMaxAttempts;
        this.quizCreateDate = quizCreateDate;
        this.createUserID = createUserID;
        this.quizUpdateDate = quizUpdateDate;
        this.updateUserID = updateUserID;
    }

    /**
     * Public Constructor
     *
     * @param quizName
     * @param quizDesc
     * @param categoryID
     * @param quizMaxAttempts
     * @param createUserID
     */
    public Quiz(String quizName, String quizDesc, long categoryID, long quizMaxAttempts, long createUserID) {
        this.quizID = 0;
        this.quizName = quizName;
        this.quizDesc = quizDesc;
        this.categoryID = categoryID;
        this.likes = 0;
        this.quizHit = 0;
        this.quizMaxAttempts = quizMaxAttempts;
        this.quizCreateDate = null;
        this.createUserID = createUserID;
        this.quizUpdateDate = null;
        this.updateUserID = 0;
    }

    /**
     * Get Quiz Id
     * @return
     */
    public long getQuizID() {
        return quizID;
    }

    /**
     * Get Quiz Name
     * @return
     */
    public String getQuizName() {
        return quizName;
    }

    /**
     * Get Quiz Desc
     * @return
     */
    public String getQuizDesc() {
        return quizDesc;
    }

    /**
     * Get Category Id
     * @return
     */
    public long getCategoryID() {
        return categoryID;
    }

    /**
     * Get Quiz Likes
     * @return
     */
    public long getLikes() {
        return likes;
    }

    /**
     * Get Quiz Hits
     * @return
     */
    public long getQuizHit() {
        return quizHit;
    }

    /**
     * Get Quiz Max Attempts
     * @return
     */
    public long getQuizMaxAttempts() {
        return quizMaxAttempts;
    }

    /**
     * Get Quiz Create Date
     * @return
     */
    public Date getQuizCreateDate() {
        return quizCreateDate;
    }

    /**
     * Get Quiz Create User ID
     * @return
     */
    public long getCreateUserID() {
        return createUserID;
    }

    /**
     * Get Quiz Update Date
     * @return
     */
    public Date getQuizUpdateDate() {
        return quizUpdateDate;
    }

    /**
     * Get Quiz Update User
     * @return
     */
    public long getUpdateUserID() {
        return updateUserID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz quiz = (Quiz) o;

        if (categoryID != quiz.categoryID) return false;
        if (createUserID != quiz.createUserID) return false;
        if (likes != quiz.likes) return false;
        if (quizHit != quiz.quizHit) return false;
        if (quizID != quiz.quizID) return false;
        if (quizMaxAttempts != quiz.quizMaxAttempts) return false;
        if (updateUserID != quiz.updateUserID) return false;
        if (quizCreateDate != null ? !quizCreateDate.equals(quiz.quizCreateDate) : quiz.quizCreateDate != null)
            return false;
        if (!quizDesc.equals(quiz.quizDesc)) return false;
        if (!quizName.equals(quiz.quizName)) return false;
        if (quizUpdateDate != null ? !quizUpdateDate.equals(quiz.quizUpdateDate) : quiz.quizUpdateDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (quizID ^ (quizID >>> 32));
        result = 31 * result + quizName.hashCode();
        result = 31 * result + quizDesc.hashCode();
        result = 31 * result + (int) (categoryID ^ (categoryID >>> 32));
        result = 31 * result + (int) (likes ^ (likes >>> 32));
        result = 31 * result + (int) (quizHit ^ (quizHit >>> 32));
        result = 31 * result + (int) (quizMaxAttempts ^ (quizMaxAttempts >>> 32));
        result = 31 * result + (quizCreateDate != null ? quizCreateDate.hashCode() : 0);
        result = 31 * result + (int) (createUserID ^ (createUserID >>> 32));
        result = 31 * result + (quizUpdateDate != null ? quizUpdateDate.hashCode() : 0);
        result = 31 * result + (int) (updateUserID ^ (updateUserID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizID=" + quizID +
                ", quizName='" + quizName + '\'' +
                ", quizDesc='" + quizDesc + '\'' +
                ", categoryID=" + categoryID +
                ", likes=" + likes +
                ", quizHit=" + quizHit +
                ", quizMaxAttempts=" + quizMaxAttempts +
                ", quizCreateDate=" + quizCreateDate +
                ", createUserID=" + createUserID +
                ", quizUpdateDate=" + quizUpdateDate +
                ", updateUserID=" + updateUserID +
                '}';
    }
}


