/**
 * Name      : com.malcolm.qme.rest.model.QMeCategory.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QMeCategory Model for REST Service Controller
 */

package com.malcolm.qme.rest.model;

import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * QMeCategory Model for REST Service Controller
 *
 * @author Malcolm
 */
public class QMeCategory extends ResourceSupport implements Serializable {
    /**
     * Generated Serialized Version Id
     */
    private static final long serialVersionUID = 1143257850480182830L;
    /**
     * QMeCategory Id
     */
    private Long categoryId;
    /**
     * Parent QMeCategory Id
     */
    private Long parentCategoryId;
    /**
     * QMeCategory Name
     */
    private String categoryName;
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
     * Get Parent Category ID
     * @return Parent Category ID
     */
    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    /**
     * Set Parent Category ID
     * @param parentCategoryId Parent Category ID
     */
    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    /**
     * Get Category Name
     *
     * @return Category Name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Set Category Name
     * @param categoryName Category Name
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
