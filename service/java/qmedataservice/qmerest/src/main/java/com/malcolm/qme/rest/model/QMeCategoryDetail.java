/**
 * Name      : com.malcolm.qme.rest.model.QMeCategoryDetail.java
 * Date      : 5/23/15
 * Developer : Malcolm
 * Purpose   : QMeCategory Details Model for REST Service Controller
 */

package com.malcolm.qme.rest.model;

import java.time.LocalDateTime;

/**
 * @author malcolm
 */
public class QMeCategoryDetail extends QMeCategory {
    /**
     * QMeCategory Id
     */
    private Long categoryId;

    /**
     * QMeCategory Likes
     */
    private Long categoryLikes;
    /**
     * QMeCategory Created Date
     */
    private LocalDateTime createdDate;
    /**
     * QMeCategory Created User
     */
    private Long createdUser;
    /**
     * QMeCategory Created User Name
     */
    private String createdUserName;

    /**
     * Get Category ID
     * @return Category ID
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Set Category ID
     * @param categoryId Category ID
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Get Category Likes
     *
     * @return Category Likes
     */
    public Long getCategoryLikes() {
        return categoryLikes;
    }

    /**
     * Get Category Likes
     * @param categoryLikes Category Likes
     */
    public void setCategoryLikes(Long categoryLikes) {
        this.categoryLikes = categoryLikes;
    }

    /**
     * Get Category Create Date
     * @return Category Create Date
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Set Category Create Date
     * @param createdDate Category Create Date
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Get Created User
     * @return Created User
     */
    public Long getCreatedUser() {
        return createdUser;
    }

    /**
     * Set Created User
     *
     * @param createdUser Created User
     */
    public void setCreatedUser(Long createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * Get Created User Name
     * @return Created User Name
     */
    public String getCreatedUserName() {
        return createdUserName;
    }

    /**
     * Set Created User Name
     * @param createdUserName Created User Name
     */
    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }

}
