/**
 * Name      : com.malcolm.qme.springdata.repository.CategoryRepositoryImplTest.java
 * Date      : 5/12/2015
 * Developer : Malcolm
 * Purpose   : QMe Category Repository Implementation Test Cases
 */
package com.malcolm.qme.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;

/**
 * @Author: malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class CategoryRepositoryImplTest {

        /**
         * Category Repository
         */
        @Autowired
        @Qualifier("CategoryRepository")
        private CategoryRepository categoryRepo;

        @Test
        public void testFindAll(){
            assertNotNull(categoryRepo);
            List<Category> categoryList = categoryRepo.findAll();
            assertNotNull(categoryList);
            assertThat(categoryList.size(), greaterThan(0));
        }

        @Test
        public void testFindById(){
            assertNotNull(categoryRepo);
            Category category = categoryRepo.findById(1L);
            assertNotNull(category);
            assertThat(category.getCategoryID(), equalTo(1L));
        }

        @Test
        public void testCRUD() {
            assertNotNull(categoryRepo);

            Category category = new Category("CategoryRepositoryImplTest", 1L);
            category = categoryRepo.save(category);
            assertNotNull(category);
            assertThat(category.getCategoryID(), greaterThan(0L));

            Long catID = category.getCategoryID();
            category = categoryRepo.findById(catID);
            assertThat(category.getCategoryID(), equalTo(catID));
            Category categoryUpdated = new Category(
                    category.getCategoryID(),
                    category.getCategoryParentID(),
                    "CategoryRepositoryImplTest UPDATED",
                    category.getCategoryLikes(),
                    category.getCategoryCreateDate(),
                    category.getCategoryCreateUserID()
            );


            categoryUpdated = categoryRepo.update(categoryUpdated,1L);
            assertNotNull(categoryUpdated);
            assertThat(categoryUpdated.getCategoryID(), equalTo(catID));
            assertThat(categoryUpdated.getCategoryName(), equalTo("CategoryRepositoryImplTest UPDATED"));


            categoryRepo.delete(catID);
            category = categoryRepo.findById(catID);
            assertNull(category);
        }

        @Test
        public void testFindCategoryNameLike(){
            assertNotNull(categoryRepo);

            Category category = new Category("CategoryRepositoryImplTest Name", 1L);
            category = categoryRepo.save(category);
            assertNotNull(category);
            assertThat(category.getCategoryID(), greaterThan(0L));

            Long catID = category.getCategoryID();
            category = categoryRepo.findById(catID);
            assertThat(category.getCategoryID(), equalTo(catID));

            List<Category> categoryList= categoryRepo.findCategoryNameLike("CategoryRepositoryImplTest Name");
            assertNotNull(categoryList);
            assertThat(categoryList.size(), equalTo(1));

            categoryList= categoryRepo.findCategoryNameLike("categoryrepositoryimpltest Name");
            assertNotNull(categoryList);
            assertThat(categoryList.size(), equalTo(1));


            Category categoryUpdated = new Category(
                    category.getCategoryID(),
                    category.getCategoryParentID(),
                    "CategoryRepositoryImplTest Name UPDATED",
                    category.getCategoryLikes(),
                    category.getCategoryCreateDate(),
                    category.getCategoryCreateUserID()
            );


            categoryUpdated = categoryRepo.update(categoryUpdated,1L);
            assertNotNull(categoryUpdated);
            assertThat(categoryUpdated.getCategoryID(), equalTo(catID));
            assertThat(categoryUpdated.getCategoryName(), equalTo("CategoryRepositoryImplTest Name UPDATED"));


            categoryRepo.delete(catID);
            category = categoryRepo.findById(catID);
            assertNull(category);
        }
}
