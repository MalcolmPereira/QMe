/**
 * Name      : com.malcolm.qme.rest.service.impl.CategoryServiceImplTest.java
 * Date      : 5/23/15
 * Developer : Malcolm
 * Purpose   : Test Cases for Category Service Implementation
 */

package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.domain.fixtures.CategoryFixtures;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeCategory;
import com.malcolm.qme.rest.model.QMeCategoryDetail;
import com.malcolm.qme.rest.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepo;

    @InjectMocks
    private final CategoryService categoryService = new CategoryServiceImpl();

    @Test
    public void testCount() throws QMeResourceException, QMeException {
        when(categoryRepo.count()).thenReturn(10L);
        Long categoryCount = categoryService.count();
        verify(categoryRepo).count();
        assertThat(categoryCount, equalTo(10L));
    }

    @Test(expected = QMeServerException.class)
    public void testCountQMeServerException() throws QMeResourceException, QMeException {
        when(categoryRepo.count()).thenThrow(QMeException.class);
        categoryService.count();
        verify(categoryRepo).count();
    }

    @Test
    public void testList() throws QMeResourceException, QMeException {

        when(categoryRepo.findAll()).thenReturn(CategoryFixtures.simpleCategoryList());

        List<QMeCategoryDetail> categoryList = categoryService.list();

        verify(categoryRepo).findAll();
        assertNotNull(categoryList);
        assertThat(categoryList.size(),equalTo(5));
        for (QMeCategoryDetail qmeCategory : categoryList) {
            assertThat(qmeCategory.getCategoryId(), anyOf(
                    is(1L),
                    is(2L),
                    is(3L),
                    is(4L),
                    is(5L))
            );
            assertThat(qmeCategory.getCategoryName(), anyOf(
                    is("Simple Category 1"),
                    is("Simple Category 2"),
                    is("Simple Category 3"),
                    is("Simple Category 4"),
                    is("Simple Category 5")
            ));

        }
    }

    @Test
    public void testListPaged() throws QMeResourceException, QMeException {

        when(categoryRepo.findAll()).thenReturn(CategoryFixtures.simpleCategoryList());

        List<QMeCategoryDetail> categoryList = categoryService.list(0,10,true,"CATEGORY");

        verify(categoryRepo).findAll();
        assertNotNull(categoryList);
        assertThat(categoryList.size(),equalTo(5));
        for (QMeCategoryDetail qmeCategory : categoryList) {
            assertThat(qmeCategory.getCategoryId(), anyOf(
                    is(1L),
                    is(2L),
                    is(3L),
                    is(4L),
                    is(5L))
            );
            assertThat(qmeCategory.getCategoryName(), anyOf(
                    is("Simple Category 1"),
                    is("Simple Category 2"),
                    is("Simple Category 3"),
                    is("Simple Category 4"),
                    is("Simple Category 5")
            ));

        }
    }

    @Test
    public void testListEmpty() throws QMeResourceException, QMeException {

        when(categoryRepo.findAll()).thenReturn(null);

        List<QMeCategoryDetail> categoryList = categoryService.list();

        verify(categoryRepo).findAll();
        assertNotNull(categoryList);
        assertThat(categoryList.size(),equalTo(0));
    }

    @Test(expected = QMeServerException.class)
    public void testListQMeException() throws QMeResourceException, QMeException {
        when(categoryRepo.findAll()).thenThrow(QMeException.class);
        categoryService.list();
        verify(categoryRepo).findAll();
    }

    @Test
    public void testSearchById() throws QMeResourceException, QMeException {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());

        QMeCategoryDetail qmeCategory = categoryService.searchById(1L);

        verify(categoryRepo).findById(1L);
        assertNotNull(qmeCategory);
        assertThat(qmeCategory.getCategoryId(), equalTo(1L));
        assertThat(qmeCategory.getCategoryName(), equalTo("Simple Category 1"));
    }

    @Test
    public void testSearchByParentCategory() throws QMeResourceException, QMeException {
        when(categoryRepo.findCategoryByParentId(1L)).thenReturn(CategoryFixtures.simpleCategoryList());
        List<QMeCategoryDetail> categoryList = categoryService.searchByParentCategory(1L);
        verify(categoryRepo).findCategoryByParentId(1L);
        assertNotNull(categoryList);
        assertThat(categoryList.size(),equalTo(5));
        for (QMeCategoryDetail qmeCategory : categoryList) {
            assertThat(qmeCategory.getCategoryId(), anyOf(
                    is(1L),
                    is(2L),
                    is(3L),
                    is(4L),
                    is(5L))
            );
            assertThat(qmeCategory.getCategoryName(), anyOf(
                    is("Simple Category 1"),
                    is("Simple Category 2"),
                    is("Simple Category 3"),
                    is("Simple Category 4"),
                    is("Simple Category 5")
            ));
        }
    }

    @Test(expected = QMeServerException.class)
    public void testSearchByParentCategoryQMeServerException() throws QMeResourceException, QMeException {
        when(categoryRepo.findCategoryByParentId(1L)).thenThrow(QMeException.class);
        categoryService.searchByParentCategory(1L);
        verify(categoryRepo).findCategoryByParentId(1L);
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testSearchByIdNotFound() throws QMeResourceException, QMeException {
        when(categoryRepo.findById(1L)).thenReturn(null);
        categoryService.searchById(1L);
        verify(categoryRepo).findById(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testSearchByIdQMeException() throws QMeResourceException, QMeException {
        when(categoryRepo.findById(1L)).thenThrow(QMeException.class);
        categoryService.searchById(1L);
        verify(categoryRepo).findById(1L);
    }

    @Test
    public void testSearchByName() throws QMeResourceException, QMeException  {
        when(categoryRepo.findCategoryNameLike("Simple Category 1")).thenReturn(CategoryFixtures.simpleCategoryList());

        List<QMeCategoryDetail> categoryList = categoryService.searchByName("Simple Category 1");

        verify(categoryRepo).findCategoryNameLike("Simple Category 1");

        assertNotNull(categoryList);
        assertThat(categoryList.size(), equalTo(5));
        for (QMeCategoryDetail qmeCategory : categoryList) {
            assertThat(qmeCategory.getCategoryId(), anyOf(
                            is(1L),
                            is(2L),
                            is(3L),
                            is(4L),
                            is(5L))
            );
            assertThat(qmeCategory.getCategoryName(), anyOf(
                    is("Simple Category 1"),
                    is("Simple Category 2"),
                    is("Simple Category 3"),
                    is("Simple Category 4"),
                    is("Simple Category 5")
            ));

        }
    }

    @Test(expected = QMeServerException.class)
    public void testSearchByNameQMeException() throws QMeResourceException, QMeException  {
        when(categoryRepo.findCategoryNameLike("Simple Category 1")).thenThrow(QMeException.class);
        categoryService.searchByName("Simple Category 1");
        verify(categoryRepo).findCategoryNameLike("Simple Category 1");
    }

    @Test
    public void testSave() throws QMeResourceException , QMeException {
        when(categoryRepo.findCategoryByName(Matchers.<String>anyObject())).thenReturn(null);
        when(categoryRepo.save(Matchers.<Category>anyObject())).thenReturn(CategoryFixtures.simpleCategory());

        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryName("Simple Category 1");
        QMeCategoryDetail qmeCategoryDetail = categoryService.save(qmeCategory,1L);

        verify(categoryRepo).findCategoryByName(Matchers.<String>anyObject());
        verify(categoryRepo).save(Matchers.<Category>anyObject());

        assertNotNull(qmeCategoryDetail);
        assertThat(qmeCategoryDetail.getCategoryId(), equalTo(1L));
        assertThat(qmeCategoryDetail.getCategoryName(), equalTo("Simple Category 1"));
        assertThat(qmeCategoryDetail.getCreatedUser(), equalTo(1L));
    }

    @Test(expected = QMeResourceConflictException.class)
    public void testSaveQMeResourceConflictException() throws QMeResourceException , QMeException {
        when(categoryRepo.findCategoryByName("Simple Category 1")).thenReturn(CategoryFixtures.simpleCategory());

        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryName("Simple Category 1");
        categoryService.save(qmeCategory, 1L);

        verify(categoryRepo).findCategoryByName(Matchers.<String>anyObject());
    }

    @Test(expected = QMeServerException.class)
    public void testSaveQMeException() throws QMeResourceException , QMeException {
        when(categoryRepo.findCategoryByName(Matchers.<String>anyObject())).thenReturn(null);
        when(categoryRepo.save(Matchers.<Category>anyObject())).thenThrow(QMeException.class);

        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryName("Simple Category 1");
        categoryService.save(qmeCategory, 1L);

        verify(categoryRepo).findCategoryByName(Matchers.<String>anyObject());
        verify(categoryRepo).save(Matchers.<Category>anyObject());
    }

    @Test
    public void testSaveWithParentCategory() throws QMeResourceException , QMeException {
        when(categoryRepo.findCategoryByName(Matchers.<String>anyObject())).thenReturn(null);
        when(categoryRepo.save(Matchers.<Category>anyObject())).thenReturn(CategoryFixtures.simpleCategory());
        when(categoryRepo.findById(2L)).thenReturn(CategoryFixtures.simpleCategory());

        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryName("Simple Category 1");
        qmeCategory.setParentCategoryId(2L);
        QMeCategoryDetail qmeCategoryDetail = categoryService.save(qmeCategory,1L);

        verify(categoryRepo).findCategoryByName(Matchers.<String>anyObject());
        verify(categoryRepo).findById(2L);
        verify(categoryRepo).save(Matchers.<Category>anyObject());

        assertNotNull(qmeCategoryDetail);
        assertThat(qmeCategoryDetail.getCategoryId(), equalTo(1L));
        assertThat(qmeCategoryDetail.getCategoryName(), equalTo("Simple Category 1"));
        assertThat(qmeCategoryDetail.getCreatedUser(), equalTo(1L));
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testSaveWithParentCategoryQMeResourceNotFoundException() throws QMeResourceException , QMeException {
        when(categoryRepo.findCategoryByName(Matchers.<String>anyObject())).thenReturn(null);
        when(categoryRepo.findById(2L)).thenReturn(null);

        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryName("Simple Category 1");
        qmeCategory.setParentCategoryId(2L);
        categoryService.save(qmeCategory,1L);

        verify(categoryRepo).findCategoryByName(Matchers.<String>anyObject());
        verify(categoryRepo).findById(2L);
    }

    @Test
    public void testUpdate() throws QMeResourceException , QMeException {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(categoryRepo.update(Matchers.<Category>anyObject(), eq(1L))).thenReturn(CategoryFixtures.simpleCategory());

        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryName("Simple Category 1");
        QMeCategoryDetail qmeCategoryDetail = categoryService.update(qmeCategory, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(categoryRepo).update(Matchers.<Category>anyObject(), eq(1L));

        assertNotNull(qmeCategoryDetail);
        assertThat(qmeCategoryDetail.getCategoryId(), equalTo(1L));
        assertThat(qmeCategoryDetail.getCategoryName(), equalTo("Simple Category 1"));
        assertThat(qmeCategoryDetail.getCreatedUser(), equalTo(1L));
    }

    @Test
    public void testUpdateWithParent() throws QMeResourceException , QMeException {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(categoryRepo.findById(2L)).thenReturn(CategoryFixtures.simpleCategory());
        when(categoryRepo.update(Matchers.<Category>anyObject(), eq(1L))).thenReturn(CategoryFixtures.simpleCategory());

        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryName("Simple Category 1");
        qmeCategory.setParentCategoryId(2L);
        QMeCategoryDetail qmeCategoryDetail = categoryService.update(qmeCategory, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(categoryRepo).findById(2L);
        verify(categoryRepo).update(Matchers.<Category>anyObject(), eq(1L));

        assertNotNull(qmeCategoryDetail);
        assertThat(qmeCategoryDetail.getCategoryId(), equalTo(1L));
        assertThat(qmeCategoryDetail.getCategoryName(), equalTo("Simple Category 1"));
        assertThat(qmeCategoryDetail.getCreatedUser(), equalTo(1L));
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testUpdateNotFound() throws QMeResourceException , QMeException {
        when(categoryRepo.findById(1L)).thenReturn(null);

        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryName("Simple Category 1");
        categoryService.update(qmeCategory, 1L, 1L);

        verify(categoryRepo).findById(1L);
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testUpdateParentNotFound() throws QMeResourceException , QMeException {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(categoryRepo.findById(2L)).thenReturn(null);


        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryName("Simple Category 1");
        qmeCategory.setParentCategoryId(2L);
        categoryService.update(qmeCategory, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(categoryRepo).findById(2L);
    }

    @Test(expected = QMeServerException.class)
    public void testUpdateQMeException() throws QMeResourceException , QMeException {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(categoryRepo.findById(2L)).thenReturn(CategoryFixtures.simpleCategory());
        when(categoryRepo.update(Matchers.<Category>anyObject(), eq(1L))).thenThrow(QMeException.class);

        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryName("Simple Category 1");
        qmeCategory.setParentCategoryId(2L);
        categoryService.update(qmeCategory, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(categoryRepo).findById(2L);
        verify(categoryRepo).update(Matchers.<Category>anyObject(), eq(1L));
    }

    @Test(expected = QMeServerException.class)
    public void testUpdateSearchCategoryQMeException() throws QMeResourceException , QMeException {
        when(categoryRepo.findById(1L)).thenThrow(QMeException.class);

        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryName("Simple Category 1");
        qmeCategory.setParentCategoryId(2L);
        categoryService.update(qmeCategory, 1L, 1L);

        verify(categoryRepo).findById(1L);
    }

    @Test
    public void testDelete() throws QMeResourceException , QMeException {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(categoryRepo.findCategoryByParentId(1L)).thenReturn(null);
        doNothing().when(categoryRepo).delete(1L);
        categoryService.delete(1L);
        verify(categoryRepo).findById(1L);
        verify(categoryRepo).findCategoryByParentId(1L);
        verify(categoryRepo).delete(1L);
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testDeleteNotFound() throws QMeResourceException , QMeException {
        when(categoryRepo.findById(1L)).thenReturn(null);
        categoryService.delete(1L);
        verify(categoryRepo).findById(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testDeleteHasChildren() throws QMeResourceException , QMeException {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(categoryRepo.findCategoryByParentId(1L)).thenReturn(CategoryFixtures.simpleCategoryList());
        categoryService.delete(1L);
        verify(categoryRepo).findById(1L);
        verify(categoryRepo).findCategoryByParentId(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testDeleteQMeException() throws QMeResourceException , QMeException {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        doThrow(QMeException.class).when(categoryRepo).delete(1L);
        categoryService.delete(1L);
        verify(categoryRepo).findById(1L);
        verify(categoryRepo).delete(1L);
    }
}
