/**
 * Name      : com.malcolm.qme.rest.controller.QuestionControllerTest.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : Test Case for Question Controller
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeQuestionDetail;
import com.malcolm.qme.rest.model.fixtures.QMeQuestionDetailFixture;
import com.malcolm.qme.rest.service.QuestionService;
import com.malcolm.qme.security.service.QMeUserDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest extends QMeControllerTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionController questionController;

    @Override
    protected MockMvc getMockMVCInstance(ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver) {
        return MockMvcBuilders
                .standaloneSetup(questionController)
                .setHandlerExceptionResolvers(exceptionHandlerExceptionResolver)
                .build();
    }

    @Before
    public void setContext(){
        SecurityContextHolder.getContext().setAuthentication((QMeUserDetails)QMeUserDetails.create(1L, "admin", "password", "USER","ADMIN"));
    }

    @Test
    public void testCount() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(questionService, notNullValue());

        when(questionService.count()).thenReturn(5L);

        mockMvc.perform(
                get("/qme/question/count")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content", is(5)))
        ;

        verify(questionService).count();
    }

    @Test
    public void testList() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(questionService, notNullValue());

        when(questionService.list()).thenReturn(QMeQuestionDetailFixture.simpleQMeQuestionDetailList());

        mockMvc.perform(
                get("/qme/question")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].questionId", is(1)))
                .andExpect(jsonPath("$[1].questionId", is(2)))
                .andExpect(jsonPath("$[2].questionId", is(3)))
                .andExpect(jsonPath("$[3].questionId", is(4)))
                .andExpect(jsonPath("$[4].questionId", is(5)))
        ;

        verify(questionService).list();
    }

    @Test
    public void testListPaged() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(questionService, notNullValue());

        when(questionService.list(0,10,true,"QUESTION")).thenReturn(QMeQuestionDetailFixture.simpleQMeQuestionDetailList());

        mockMvc.perform(
                get("/qme/question/paged?page=0&pagesize=10&sorttype=true&sortfields=QUESTION")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].questionId", is(1)))
                .andExpect(jsonPath("$[1].questionId", is(2)))
                .andExpect(jsonPath("$[2].questionId", is(3)))
                .andExpect(jsonPath("$[3].questionId", is(4)))
                .andExpect(jsonPath("$[4].questionId", is(5)))
        ;

        verify(questionService).list(0,10,true,"QUESTION");
    }

    @Test
    public void testListQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(questionService, notNullValue());

        when(questionService.list()).thenThrow(new QMeServerException("Some Error in the Service"));

        mockMvc.perform(
                get("/qme/question")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;
    }

    @Test
    public void testSearchById() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(questionService, notNullValue());

        when(questionService.searchById(1L)).thenReturn(QMeQuestionDetailFixture.simpleQMeQuestionDetail());

        mockMvc.perform(
                get("/qme/question/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.questionId", is(1)))
                .andExpect(jsonPath("$.categoryId", is(1)))
                .andExpect(jsonPath("$.questionText", is("Some Question")))
                .andExpect(jsonPath("$.answer", is("Some Answer")))
        ;

        verify(questionService).searchById(1L);
    }

    @Test
    public void testCreate() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(questionService, notNullValue());

        when(questionService.save(Matchers.<QMeQuestionDetail>anyObject(),eq(1L))).thenReturn(QMeQuestionDetailFixture.simpleQMeQuestionDetail());

        QMeQuestionDetail questionDetail = QMeQuestionDetailFixture.simpleQMeQuestionDetail();

        mockMvc.perform(
                post("/qme/question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeQuestionDetailFixture.toJson(questionDetail))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questionId", is(1)))
                .andExpect(jsonPath("$.questionText", is("Some Question")))
        ;

        verify(questionService).save(Matchers.<QMeQuestionDetail>anyObject(),eq(1L));
    }

}


