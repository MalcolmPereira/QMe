/**
 * Name      : com.malcolm.qme.core.domain.Quiz.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Domain Class
 */

package com.malcolm.qme.core.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Malcolm
 */
public final class Quiz {
    /**
     * Quiz Id
     */
    private final Long quizID;

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
    private final Long categoryID;

    /**
     * Quiz Likes
     */
    private final Long likes;

    /**
     * Quiz Hits
     */
    private final Long quizHit;

    /**
     * Quiz Max Attempts
     */
    private final Integer quizMaxAttempts;

    /**
     * Quiz Create Date
     */
    private final LocalDateTime quizCreateDate;

    /**
     * Quiz Create User Id
     */
    private final Long  createUserID;

    /**
     * Quiz Update Date
     */
    private final LocalDateTime quizUpdateDate;

    /**
     * Update User Id
     */
    private final Long  updateUserID;

    /**
     * Create User
     */
    private User createUser;
    /**
     * Update User
     */
    private User updateUser;
    /**
     * Question Category
     */
    private Category category;

    /**
     * Public Constructor
     *
     * @param quizID Quiz ID
     * @param quizName Quiz Name
     * @param quizDesc Quiz Desc
     * @param categoryID Category ID
     * @param likes Likes
     * @param quizHit Quiz Hit
     * @param quizMaxAttempts Quiz Max Attempts
     * @param quizCreateDate Quiz Create Date
     * @param createUserID Quiz Create User ID
     * @param quizUpdateDate Quiz Update Date
     * @param updateUserID Quiz Update User Id
     */
    public Quiz(Long quizID, String quizName, String quizDesc, Long categoryID, Long likes, Long quizHit, Integer quizMaxAttempts, LocalDateTime quizCreateDate, Long createUserID, LocalDateTime quizUpdateDate, Long updateUserID) {
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
     * @param quizName Quiz Name
     * @param quizDesc Quiz Desc
     * @param categoryID Category ID
     * @param quizMaxAttempts Quiz Max Attempts
     * @param createUserID Quiz Create User ID
     */
    public Quiz(String quizName, String quizDesc, Long categoryID, Integer quizMaxAttempts, Long createUserID) {
        this.quizID = 0L;
        this.quizName = quizName;
        this.quizDesc = quizDesc;
        this.categoryID = categoryID;
        this.likes = 0L;
        this.quizHit = 0L;
        this.quizMaxAttempts = quizMaxAttempts;
        this.quizCreateDate = LocalDateTime.now();
        this.createUserID = createUserID;
        this.quizUpdateDate = LocalDateTime.now();
        this.updateUserID = 0L;
    }

    /**
     * Get Quiz ID
     * @return Quiz ID
     */
    public Long getQuizID() {
        return quizID;
    }

    /**
     * Get Quiz Name
     * @return Quiz Name
     */
    public String getQuizName() {
        return quizName;
    }

    /**
     * Get Quiz Desc
     * @return Quiz Desc
     */
    public String getQuizDesc() {
        return quizDesc;
    }

    /**
     * Get Category ID
     * @return Category ID
     */
    public Long getCategoryID() {
        return categoryID;
    }

    /**
     * Get Quiz Likes
     * @return Quiz Likes
     */
    public Long getLikes() {
        return likes;
    }

    /**
     * Get Quiz Hits
     * @return Quiz Hits
     */
    public Long getQuizHit() {
        return quizHit;
    }

    /**
     * Get Quiz Max Attempts
     * @return Quiz Max Attempts
     */
    public Integer getQuizMaxAttempts() {
        return quizMaxAttempts;
    }

    /**
     * Get Quiz Create Date
     * @return Quiz Create Date
     */
    public LocalDateTime getQuizCreateDate() {
        return quizCreateDate;
    }

    /**
     * Get Quiz Create User
     * @return Quiz Create User
     */
    public Long getCreateUserID() {
        return createUserID;
    }

    /**
     * Get Quiz Update Date
     * @return Quiz Update Date
     */
    public LocalDateTime getQuizUpdateDate() {
        return quizUpdateDate;
    }

    /**
     * Get Quiz Update User
     * @return Quiz Update User
     */
    public Long getUpdateUserID() {
        return updateUserID;
    }

    /**
     * Get Create User
     * @return Create User
     */
    public User getCreateUser() {
        return createUser;
    }

    /**
     * Set Create User
     * @param createUser Create User
     */
    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    /**
     * Get Update User
     * @return Update User
     */
    public User getUpdateUser() {
        return updateUser;
    }

    /**
     * Set Update User
     * @param updateUser Update User
     */
    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * Get Category
     * @return Category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Set Category
     * @param category Category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(quizID, quiz.quizID) &&
                Objects.equals(quizName, quiz.quizName) &&
                Objects.equals(categoryID, quiz.categoryID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizID, quizName, categoryID);
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


