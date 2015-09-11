/**
 * Name      : com.malcolm.qme.rest.controller.CategoryControllerTest.java
 * Date      : 5/24/15
 * Developer : Malcolm
 * Purpose   : Test Case for Category Controller
 */

package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeCategory;
import com.malcolm.qme.rest.model.fixtures.QMeCategoryDetailFixtures;
import com.malcolm.qme.rest.model.fixtures.QMeCategoryFixtures;
import com.malcolm.qme.rest.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest extends QMeControllerTest{

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Override
    public MockMvc getMockMVCInstance(final ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver) {
        return MockMvcBuilders.standaloneSetup(categoryController).setHandlerExceptionResolvers(exceptionHandlerExceptionResolver).build();
    }

    @Test
    public void testList() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        when(categoryService.list()).thenReturn(QMeCategoryDetailFixtures.simpleQMeCategoryDetailList());

        mockMvc.perform(
                get("/qme/category")
                    .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$", hasSize(5)))
                        .andExpect(jsonPath("$[0].categoryId", is(1)))
                        .andExpect(jsonPath("$[1].categoryId", is(2)))
                        .andExpect(jsonPath("$[2].categoryId", is(3)))
                        .andExpect(jsonPath("$[3].categoryId", is(4)))
                        .andExpect(jsonPath("$[4].categoryId", is(5)))
        ;

    }

    @Test
    public void testListQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        when(categoryService.list()).thenThrow(new QMeServerException("Some Error in the Service"));

        mockMvc.perform(
                get("/qme/category")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())

        ;


    }

    @Test
    public void testSearchByName() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        when(categoryService.searchByName("Simple Category 1")).thenReturn(QMeCategoryDetailFixtures.simpleQMeCategoryDetailList());

        mockMvc.perform(
                get("/qme/category/search/Simple Category 1")
                    .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$", hasSize(5)))
                        .andExpect(jsonPath("$[0].categoryId", is(1)))
                        .andExpect(jsonPath("$[1].categoryId", is(2)))
                        .andExpect(jsonPath("$[2].categoryId", is(3)))
                        .andExpect(jsonPath("$[3].categoryId", is(4)))
                        .andExpect(jsonPath("$[4].categoryId", is(5)))
        ;

    }

    @Test
    public void testSearchByNamQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        when(categoryService.searchByName("Simple Category 1")).thenThrow(new QMeServerException("Some Error in the Service"));

        mockMvc.perform(
                get("/qme/category/search/Simple Category 1")
                        .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isInternalServerError())
                            .andDo(print())

        ;


    }

    @Test
    public void testSearchById() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        when(categoryService.searchById(1L)).thenReturn(QMeCategoryDetailFixtures.simpleQMeCategoryDetail());

        mockMvc.perform(
                get("/qme/category/1")
                    .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.categoryId", is(1)))
                        .andExpect(jsonPath("$.categoryName", is("Simple Category 1")))
        ;
    }

    @Test
    public void testSearchByIdQMeResourceNotFoundException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        when(categoryService.searchById(1L)).thenThrow(new QMeResourceNotFoundException("Some Error in the Service"));

        mockMvc.perform(
                get("/qme/category/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
        ;
    }

    @Test
    public void testSearchByIdQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        when(categoryService.searchById(1L)).thenThrow(new QMeServerException("Some Error in the Service"));

        mockMvc.perform(
                get("/qme/category/1")
                        .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isInternalServerError())
                            .andDo(print())

        ;
    }

    @Test
    public void testCreate() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        when(categoryService.save(anyObject(),eq(1L))).thenReturn(QMeCategoryDetailFixtures.simpleQMeCategoryDetail());

        QMeCategory qmeCategory = QMeCategoryFixtures.simpleQMeCategory();


        mockMvc.perform(
                    post("/qme/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeCategoryFixtures.toJson(qmeCategory))
                            .accept(MediaType.APPLICATION_JSON))
                                .andDo(print())
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.categoryId", is(1)))
                                .andExpect(jsonPath("$.categoryName", is("Simple Category 1")))
        ;

    }

    @Test
    public void testCreateQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        when(categoryService.save(anyObject(),eq(1L))).thenThrow(new QMeServerException("Some Error in the Service"));

        QMeCategory qmeCategory = QMeCategoryFixtures.simpleQMeCategory();


        mockMvc.perform(
                post("/qme/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeCategoryFixtures.toJson(qmeCategory))
                            .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isInternalServerError())
                                .andDo(print())
        ;

    }

    @Test
    public void testUpdate() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        when(categoryService.update(anyObject(),eq(1L),eq(1L))).thenReturn(QMeCategoryDetailFixtures.simpleQMeCategoryDetail());

        QMeCategory qmeCategory = QMeCategoryFixtures.simpleQMeCategory();


        mockMvc.perform(
                put("/qme/category/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(QMeCategoryFixtures.toJson(qmeCategory))
                        .accept(MediaType.APPLICATION_JSON))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.categoryId", is(1)))
                            .andExpect(jsonPath("$.categoryName", is("Simple Category 1")))
        ;

    }

    @Test
    public void testUpdateQMeResourceNotFoundException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        when(categoryService.update(anyObject(),eq(1L),eq(1L))).thenThrow(new QMeResourceNotFoundException("Some Error in the Service"));

        QMeCategory qmeCategory = QMeCategoryFixtures.simpleQMeCategory();


        mockMvc.perform(
                put("/qme/category/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeCategoryFixtures.toJson(qmeCategory))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
        ;
    }

    @Test
    public void testUpdateQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        when(categoryService.update(anyObject(),eq(1L),eq(1L))).thenThrow(new QMeServerException("Some Error in the Service"));

        QMeCategory qmeCategory = QMeCategoryFixtures.simpleQMeCategory();


        mockMvc.perform(
                put("/qme/category/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeCategoryFixtures.toJson(qmeCategory))
                        .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isInternalServerError())
                            .andDo(print())
        ;
    }

    @Test
    public void testDelete() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        doNothing().when(categoryService).delete(1L);


        mockMvc.perform(
                delete("/qme/category/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
        ;
    }

    @Test
    public void testDeleteQMeResourceNotFoundException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        doThrow(new QMeResourceNotFoundException("Some Error in the Service")).when(categoryService).delete(1L);

        mockMvc.perform(
                delete("/qme/category/1"))
                    .andExpect(status().isNotFound())
                    .andDo(print())
        ;
    }

    @Test
    public void testDeleteQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(categoryService, notNullValue());

        doThrow(new QMeServerException("Some Error in the Service")).when(categoryService).delete(1L);

        mockMvc.perform(
                delete("/qme/category/1"))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;
    }
}
