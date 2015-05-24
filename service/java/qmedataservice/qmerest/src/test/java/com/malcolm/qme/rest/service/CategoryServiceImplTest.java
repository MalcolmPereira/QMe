/**
 * Name      : com.malcolm.qme.rest.service.CategoryServiceImplTest.java
 * Date      : 5/23/15
 * Developer : Malcolm
 * Purpose   : Test Cases for Category Service Imple
 */

package com.malcolm.qme.rest.service;

import com.malcolm.qme.core.domain.fixtures.CategoryFixtures;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.rest.model.QMeCategory;
import com.malcolm.qme.rest.model.QMeCategoryDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
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
    public void testCreate(){
        QMeCategory qmeCategory = new QMeCategory();
        qmeCategory.setCategoryName("New Simple Category");
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());

    }
}