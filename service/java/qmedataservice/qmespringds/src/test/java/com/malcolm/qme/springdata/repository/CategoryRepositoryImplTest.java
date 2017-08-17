/**
 * Name      : com.malcolm.qme.springdata.repository.CategoryRepositoryImplTest.java
 * Date      : 5/12/2015
 * Developer : Malcolm
 * Purpose   : QMe Category Repository Implementation Test Cases
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.CategoryEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class CategoryRepositoryImplTest {

    /**
     * Category Repository
     */
    @Autowired
    @Qualifier("CategoryRepository")
    private CategoryRepository categoryRepo;

    @Mock
    private CategorySpringDataRepository categorySpringDataRepositoryMOCK;

    @InjectMocks
    private CategoryRepository categoryRepositoryWithMock;

    @Before
    public void initMocks(){
        categoryRepositoryWithMock = new CategoryRepositoryImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws QMeException {
        assertNotNull(categoryRepo);
        List<Category> categoryList = categoryRepo.findAll();
        assertNotNull(categoryList);
        assertThat(categoryList.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(categoryRepo);
        Category category = categoryRepo.findById(1L);
        assertNotNull(category);
        assertThat(category.getCategoryID(), equalTo(1L));
    }

    @Test
    public void testFindCategoryWithQuestions() throws QMeException {
        assertNotNull(categoryRepo);

        List<Category> categoryList = categoryRepo.findCategoriesWithQuestions();
        assertNotNull(categoryList);
        assertThat(categoryList.size(), greaterThan(1));
    }

    @Test
    public void testCRUD() throws QMeException {
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


        categoryUpdated = categoryRepo.update(categoryUpdated, 1L);
        assertNotNull(categoryUpdated);
        assertThat(categoryUpdated.getCategoryID(), equalTo(catID));
        assertThat(categoryUpdated.getCategoryName(), equalTo("CategoryRepositoryImplTest UPDATED"));

        Category categoryChild = new Category(category.getCategoryID(), "CategoryRepositoryImplTestChild",1L);
        category = categoryRepo.save(categoryChild);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));
        assertThat(category.getCategoryParentID(), equalTo(catID));
        Long catIDChild = category.getCategoryID();

        categoryRepo.delete(catID);
        category = categoryRepo.findById(catID);
        assertNull(category);

        categoryRepo.delete(catIDChild);
        category = categoryRepo.findById(catIDChild);
        assertNull(category);
    }

    @Test
    public void testFindCategoryNameLike() throws QMeException {
        assertNotNull(categoryRepo);

        Category category = new Category("CategoryRepositoryImplTest Name", 1L);
        category = categoryRepo.save(category);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));

        Long catID = category.getCategoryID();
        category = categoryRepo.findById(catID);
        assertThat(category.getCategoryID(), equalTo(catID));

        List<Category> categoryList = categoryRepo.findCategoryNameLike("CategoryRepositoryImplTest Name");
        assertNotNull(categoryList);
        assertThat(categoryList.size(), equalTo(1));

        categoryList = categoryRepo.findCategoryNameLike("categoryrepositoryimpltest Name");
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


        categoryUpdated = categoryRepo.update(categoryUpdated, 1L);
        assertNotNull(categoryUpdated);
        assertThat(categoryUpdated.getCategoryID(), equalTo(catID));
        assertThat(categoryUpdated.getCategoryName(), equalTo("CategoryRepositoryImplTest Name UPDATED"));


        categoryRepo.delete(catID);
        category = categoryRepo.findById(catID);
        assertNull(category);
    }

    @Test
    public void testFindAllNullReturn() throws QMeException {
        when(categorySpringDataRepositoryMOCK.findAll()).thenReturn(null);
        List<Category> categoryList = categoryRepositoryWithMock.findAll();
        verify(categorySpringDataRepositoryMOCK).findAll();
        assertNotNull(categoryList);
        assertThat(categoryList.size(), equalTo(0));
    }

    @Test(expected = QMeException.class)
    public void testFindAllQMeException() throws QMeException {
        when(categorySpringDataRepositoryMOCK.findAll()).thenThrow(new RuntimeException("some error"));
        categoryRepositoryWithMock.findAll();
        verify(categorySpringDataRepositoryMOCK).findAll();
    }

    @Test(expected = QMeException.class)
    public void testFindCategoryNameLikeQMeException() throws QMeException {
        when(categorySpringDataRepositoryMOCK.findByCatNameIgnoreCaseLike("test")).thenThrow(new RuntimeException("some error"));
        categoryRepositoryWithMock.findCategoryNameLike("test");
        verify(categorySpringDataRepositoryMOCK).findByCatNameIgnoreCaseLike("test");
    }

    @Test(expected = QMeException.class)
    public void testFindByIdQMeException() throws QMeException {
        when(categorySpringDataRepositoryMOCK.findOne(1L)).thenThrow(new RuntimeException("some error"));
        categoryRepositoryWithMock.findById(1L);
        verify(categorySpringDataRepositoryMOCK).findOne(1L);
    }

    @Test(expected = QMeException.class)
    public void testSaveQMeException() throws QMeException {
        when(categorySpringDataRepositoryMOCK.save(Matchers.<CategoryEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        categoryRepositoryWithMock.save(new Category("CategoryRepositoryImplTest Name", 1L));
        verify(categorySpringDataRepositoryMOCK).save(Matchers.<CategoryEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testUpdateQMeException() throws QMeException {
        when(categorySpringDataRepositoryMOCK.save(Matchers.<CategoryEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        categoryRepositoryWithMock.update(new Category("CategoryRepositoryImplTest Name", 1L), 1L);
        verify(categorySpringDataRepositoryMOCK).save(Matchers.<CategoryEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testDeleteQMeException() throws QMeException {
        doThrow(new RuntimeException("some error")).when(categorySpringDataRepositoryMOCK).delete(1L);
        categoryRepositoryWithMock.delete(1L);
        verify(categorySpringDataRepositoryMOCK).delete(1L);
    }
}
