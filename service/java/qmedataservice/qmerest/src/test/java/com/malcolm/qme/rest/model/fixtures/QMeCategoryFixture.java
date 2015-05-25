/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeCategoryFixture.java
 * Date      : 5/25/15
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe Category
 */

package com.malcolm.qme.rest.model.fixtures;

import com.malcolm.qme.rest.model.QMeCategory;


/**
 * @author Malcolm
 */
public class QMeCategoryFixture extends QMeResourceFixture<QMeCategory>{

    /**
     * Simple QMe Category
     *
     * @return  QMeCategory
     */
    public static QMeCategory simpleQMeCategory(){
        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setParentCategoryId(null);
        qmeCategory.setCategoryName("Simple Category 1");
        return qmeCategory;
    }


}
