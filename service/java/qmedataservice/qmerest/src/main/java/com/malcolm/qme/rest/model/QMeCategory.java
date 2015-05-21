/**
 * Name      : com.malcolm.qme.rest.model.QMeCategory.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QMeCategory Model for REST Service Controller
 */

package com.malcolm.qme.rest.model;

import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
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
    private Date createdDate;
    /**
     * QMeCategory Created User
     */
    private Long createdUser;
    /**
     * QMeCategory Created User Name
     */
    private String createdUserName;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryLikes() {
        return categoryLikes;
    }

    public void setCategoryLikes(Long categoryLikes) {
        this.categoryLikes = categoryLikes;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Long createdUser) {
        this.createdUser = createdUser;
    }

    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }
}
