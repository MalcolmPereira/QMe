/**
 * Name      : com.malcolm.qme.springdata.repository.CategorySpringDataRepositoryTest.java
 * Date      : 5/12/2015
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA CategoryEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.CategoryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @Author: malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class CategorySpringDataRepositoryTest {
    /**
     * CategoryEntity Repository
     */
    @Autowired
    private CategorySpringDataRepository categorySpringDataRepository;

    @Test
    public void testFindAll(){
        assertNotNull(categorySpringDataRepository);
        List<CategoryEntity> categoryEntities = categorySpringDataRepository.findAll();
        assertNotNull(categoryEntities);
        assertThat(categoryEntities.size(), greaterThan(0));
    }

    @Test
    public void testFindById(){
        assertNotNull(categorySpringDataRepository);
        CategoryEntity categoryEntity = categorySpringDataRepository.findOne(1L);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), equalTo(1L));
    }

    @Test
    public void testCRUD() {
        assertNotNull(categorySpringDataRepository);

        CategoryEntity categoryEntity = new CategoryEntity("CategorySpringDataRepositoryTest", 0L, new Date(), 1L);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), greaterThan(0L));

        Long catID = categoryEntity.getCatId();
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), equalTo(catID));
        categoryEntity.setCatName("CategorySpringDataRepositoryTest UPDATED");

        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), equalTo(catID));
        assertThat(categoryEntity.getCatName(), equalTo("CategorySpringDataRepositoryTest UPDATED"));


        categorySpringDataRepository.delete(catID);
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNull(categoryEntity);
    }

    @Test
    public void testFindCategoryNameLike(){

        assertNotNull(categorySpringDataRepository);

        CategoryEntity categoryEntity = new CategoryEntity("CategorySpringDataRepositoryTest Name", 0L, new Date(), 1L);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), greaterThan(0L));

        Long catID = categoryEntity.getCatId();
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), equalTo(catID));

        List<CategoryEntity> categoryEntityList= categorySpringDataRepository.findByCatNameIgnoreCaseLike("CategorySpringDataRepositoryTest Name");
        assertNotNull(categoryEntityList);
        assertThat(categoryEntityList.size(), equalTo(1));

        categoryEntityList= categorySpringDataRepository.findByCatNameIgnoreCaseLike("categoryspringdatarepositorytest name");
        assertNotNull(categoryEntityList);
        assertThat(categoryEntityList.size(), equalTo(1));

        categoryEntity.setCatName("CategorySpringDataRepositoryTest Name UPDATED");

        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), equalTo(catID));
        assertThat(categoryEntity.getCatName(), equalTo("CategorySpringDataRepositoryTest Name UPDATED"));


        categorySpringDataRepository.delete(catID);
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNull(categoryEntity);
    }
}
