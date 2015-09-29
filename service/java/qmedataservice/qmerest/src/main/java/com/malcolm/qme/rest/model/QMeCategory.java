/**
 * Name      : com.malcolm.qme.rest.model.QMeCategory.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QMeCategory Model for REST Service Controller
 */

package com.malcolm.qme.rest.model;

/**
 * QMeCategory Model for REST Service Controller
 *
 * @author Malcolm
 */
public class QMeCategory extends QMeResource {
    /**
     * Generated Serialized Version Id
     */
    private static final long serialVersionUID = 1143257850480182830L;
    /**
     * Parent QMeCategory Id
     */
    private Long parentCategoryId;
    /**
     * QMeCategory Name
     */
    private String categoryName;
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

}
