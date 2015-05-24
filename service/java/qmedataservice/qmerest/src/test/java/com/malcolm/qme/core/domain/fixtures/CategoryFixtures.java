/**
 * Name      : com.malcolm.qme.core.domain.fixtures.CategoryFixtures.java
 * Date      : 5/23/15
 * Developer : Malcolm
 * Purpose   : Test Fixtures for Category
 */

package com.malcolm.qme.core.domain.fixtures;

import com.malcolm.qme.core.domain.Category;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
public class CategoryFixtures {

    /**
     * Simple Category
     *
     * @return Category
     */
    public static Category simpleCategory(){
        return new Category(1L,null,"Simple Category 1",0L,LocalDateTime.now(),1L);
    }

    /**
     * Simple Category
     *

     * @return Category List
     */
    public static List<Category> simpleCategoryList(){
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1L,null,"Simple Category 1",0L,LocalDateTime.now(),1L));
        categoryList.add(new Category(2L,null,"Simple Category 2",0L,LocalDateTime.now(),1L));
        categoryList.add(new Category(3L,null,"Simple Category 3",0L,LocalDateTime.now(),1L));
        categoryList.add(new Category(4L,null,"Simple Category 4",0L,LocalDateTime.now(),1L));
        categoryList.add(new Category(5L,null,"Simple Category 5",0L,LocalDateTime.now(),1L));
        return categoryList;
    }

    /**
     * Simple Category
     *

     * @return Category List
     */
    public static List<Category> simpleCategoryList(String categoryName){
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1L,null,"Simple Category 1",0L,LocalDateTime.now(),1L));
        categoryList.add(new Category(2L,null,"Simple Category 2",0L,LocalDateTime.now(),1L));
        categoryList.add(new Category(3L,null,"Simple Category 3",0L,LocalDateTime.now(),1L));
        categoryList.add(new Category(4L,null,"Simple Category 4",0L,LocalDateTime.now(),1L));
        categoryList.add(new Category(5L,null,"Simple Category 5",0L,LocalDateTime.now(),1L));
        return categoryList;
    }
}
