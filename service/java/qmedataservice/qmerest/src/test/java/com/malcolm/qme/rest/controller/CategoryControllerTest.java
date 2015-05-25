/**
 * Name      : com.malcolm.qme.rest.controller.CategoryControllerTest.java
 * Date      : 5/24/15
 * Developer : Malcolm
 * Purpose   : Test Case for Controller
 */

package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.model.fixtures.QMeCategoryDetailFixtures;
import com.malcolm.qme.rest.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testList() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());
        when(categoryService.list()).thenReturn(QMeCategoryDetailFixtures.simpleQMeCategoryDetailList());
        mockMvc.perform(
           get("/qme/category").accept(MediaType.APPLICATION_JSON)
        )
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(5)))
           .andExpect(jsonPath("$[0].categoryId", is(1)))
           .andExpect(jsonPath("$[1].categoryId", is(2)))
           .andExpect(jsonPath("$[2].categoryId", is(3)))
           .andExpect(jsonPath("$[3].categoryId", is(4)))
           .andExpect(jsonPath("$[4].categoryId", is(5)))
        ;

    }

    @Test
    public void testSearchByName() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());
        when(categoryService.searchByName("Simple Category 1")).thenReturn(QMeCategoryDetailFixtures.simpleQMeCategoryDetailList());
        mockMvc.perform(
                get("/qme/category/search/Simple Category 1").accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].categoryId", is(1)))
                .andExpect(jsonPath("$[1].categoryId", is(2)))
                .andExpect(jsonPath("$[2].categoryId", is(3)))
                .andExpect(jsonPath("$[3].categoryId", is(4)))
                .andExpect(jsonPath("$[4].categoryId", is(5)))
        ;

    }

    @Test
    public void testSearchById() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());
        when(categoryService.searchById(1L)).thenReturn(QMeCategoryDetailFixtures.simpleQMeCategoryDetail());
        mockMvc.perform(
                get("/qme/category/1").accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId", is(1)))
                .andExpect(jsonPath("$.categoryName", is("Simple Category 1")))
        ;
    }

}
