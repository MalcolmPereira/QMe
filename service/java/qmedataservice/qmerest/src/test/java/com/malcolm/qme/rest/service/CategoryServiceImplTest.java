/**
 * Name      : com.malcolm.qme.rest.service.CategoryServiceImplTest.java
 * Date      : 5/23/15
 * Developer : Malcolm
 * Purpose   : Test Cases for Category Service Imple
 */

package com.malcolm.qme.rest.service;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.domain.fixtures.CategoryFixtures;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeCategory;
import com.malcolm.qme.rest.model.QMeCategoryDetail;
import com.malcolm.qme.rest.service.impl.CategoryServiceImpl;
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
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @Mock
    CategoryRepository categoryRepo;

    @InjectMocks
    private final CategoryService categoryService = new CategoryServiceImpl();


    @Test
    public void testList() throws QMeResourceException {

        when(categoryRepo.findAll()).thenReturn((List)CategoryFixtures.simpleCategoryList());

        List<QMeCategoryDetail> categoryList = categoryService.list();
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
    public void testSearchById() throws QMeResourceException {

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());

        QMeCategoryDetail qmeCategory = categoryService.searchById(1L);
        assertNotNull(qmeCategory);
        assertThat(qmeCategory.getCategoryId(),equalTo(1L));
        assertThat(qmeCategory.getCategoryName(),equalTo("Simple Category 1"));

    }

    @Test
    public void testSearchByName() throws QMeResourceException {
        when(categoryRepo.findCategoryNameLike("Simple Category 1")).thenReturn(CategoryFixtures.simpleCategoryList());

        List<QMeCategoryDetail> categoryList = categoryService.searchByName("Simple Category 1");
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
    public void testCreate() throws QMeResourceException {
        when(categoryRepo.save(Matchers.<Category>anyObject())).thenReturn(CategoryFixtures.simpleCategory());

        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryName("Simple Category 1");
        QMeCategoryDetail qmeCategoryDetail = categoryService.save(qmeCategory,1L);

        assertNotNull(qmeCategoryDetail);
        assertThat(qmeCategoryDetail.getCategoryId(),equalTo(1L));
        assertThat(qmeCategoryDetail.getCategoryName(),equalTo("Simple Category 1"));
        assertThat(qmeCategoryDetail.getCreatedUser(),equalTo(1L));
    }

    @Test
    public void testUpdate() throws QMeResourceException {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(categoryRepo.update(Matchers.<Category>anyObject(), eq(1L))).thenReturn(CategoryFixtures.simpleCategory());

        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryName("Simple Category 1");
        QMeCategoryDetail qmeCategoryDetail = categoryService.update(qmeCategory, 1L, 1L);

        assertNotNull(qmeCategoryDetail);
        assertThat(qmeCategoryDetail.getCategoryId(),equalTo(1L));
        assertThat(qmeCategoryDetail.getCategoryName(),equalTo("Simple Category 1"));
        assertThat(qmeCategoryDetail.getCreatedUser(),equalTo(1L));
    }

    @Test
    public void testDelete() throws QMeResourceException {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        doNothing().when(categoryRepo).delete(1L);
        categoryService.delete(1L);
    }
}
