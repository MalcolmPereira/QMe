/**
 * Name      : com.malcolm.qme.rest.controller.QuizControllerTest.java
 * Date      : 6/29/2017
 * Developer : Malcolm
 * Purpose   : Test Case for Quiz Controller
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeQuizDetail;
import com.malcolm.qme.rest.model.fixtures.QMeQuestionDetailFixture;
import com.malcolm.qme.rest.model.fixtures.QMeQuizDetailFixture;
import com.malcolm.qme.rest.service.QuizService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test Case for Quiz Controller
 *
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QuizControllerTest extends QMeControllerTest {
    @Mock
    private QuizService quizService;

    @InjectMocks
    private QuizController quizController;

    @Override
    protected MockMvc getMockMVCInstance(ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver) {
        return MockMvcBuilders
                .standaloneSetup(quizController)
                .setHandlerExceptionResolvers(exceptionHandlerExceptionResolver)
                .build();
    }

    @Before
    public void setContext() {
        SecurityContextHolder.getContext().setAuthentication((QMeUserDetails) QMeUserDetails.create(1L, "admin", "password", "USER", "ADMIN"));
    }

    @Test
    public void testCount() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(quizService, notNullValue());

        when(quizService.count()).thenReturn(1L);

        mockMvc.perform(
                get("/qme/quiz/count")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content", is(1)))
        ;

        verify(quizService).count();
    }

    @Test
    public void testList() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(quizService, notNullValue());

        when(quizService.list()).thenReturn(QMeQuizDetailFixture.simpleQMeQuizDetailList());

        mockMvc.perform(
                get("/qme/quiz")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].quizID", is(1)))
                .andExpect(jsonPath("$[1].quizID", is(2)))
                .andExpect(jsonPath("$[2].quizID", is(3)))
                .andExpect(jsonPath("$[3].quizID", is(4)))
                .andExpect(jsonPath("$[4].quizID", is(5)))
        ;
        verify(quizService).list();
    }

    @Test
    public void testListPaged() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(quizService, notNullValue());

        when(quizService.list(0, 10, true, "QUIZ")).thenReturn(QMeQuizDetailFixture.simpleQMeQuizDetailList());

        mockMvc.perform(
                get("/qme/quiz/paged?page=0&pagesize=10&sorttype=true&sortfields=QUIZ")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].quizID", is(1)))
                .andExpect(jsonPath("$[1].quizID", is(2)))
                .andExpect(jsonPath("$[2].quizID", is(3)))
                .andExpect(jsonPath("$[3].quizID", is(4)))
                .andExpect(jsonPath("$[4].quizID", is(5)))
        ;
        verify(quizService).list(0, 10, true, "QUIZ");
    }

    @Test
    public void testListPagedInvalidPage() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(quizService, notNullValue());

        when(quizService.list()).thenReturn(QMeQuizDetailFixture.simpleQMeQuizDetailList());

        mockMvc.perform(
                get("/qme/quiz/paged?page=&pagesize=10&sorttype=true&sortfields=QUIZ")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].quizID", is(1)))
                .andExpect(jsonPath("$[1].quizID", is(2)))
                .andExpect(jsonPath("$[2].quizID", is(3)))
                .andExpect(jsonPath("$[3].quizID", is(4)))
                .andExpect(jsonPath("$[4].quizID", is(5)))
        ;
        verify(quizService).list();
    }

    @Test
    public void testListQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(quizService, notNullValue());

        when(quizService.list()).thenThrow(new QMeServerException("Some Error in the Service"));

        mockMvc.perform(
                get("/qme/quiz")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;
    }

    @Test
    public void testSearchById() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(quizService, notNullValue());

        when(quizService.searchById(1L)).thenReturn(QMeQuizDetailFixture.qMeQuizDetailWithQuestions());

        mockMvc.perform(
                get("/qme/quiz/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.quizID", is(1)))
                .andExpect(jsonPath("$.categoryID", is(1)))
                .andExpect(jsonPath("$.quizName", is("Some Quiz Name")))
        ;

        verify(quizService).searchById(1L);
    }

    @Test
    public void testCreate() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(quizService, notNullValue());

        when(quizService.save(Matchers.<QMeQuizDetail>anyObject(), eq(1L))).thenReturn(QMeQuizDetailFixture.qMeQuizDetailWithQuestions());

        QMeQuizDetail qMeQuizDetail = QMeQuizDetailFixture.qMeQuizDetailWithQuestions();


        mockMvc.perform(
                post("/qme/quiz")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeQuestionDetailFixture.toJson(qMeQuizDetail))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quizID", is(1)))
                .andExpect(jsonPath("$.quizName", is("Some Quiz Name")))
        ;

        verify(quizService).save(Matchers.<QMeQuizDetail>anyObject(), eq(1L));
    }

    @Test
    public void testUpdate() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(quizService, notNullValue());

        when(quizService.update(Matchers.<QMeQuizDetail>anyObject(),eq(1L),eq(1L))).thenReturn(QMeQuizDetailFixture.qMeQuizDetailWithQuestions());

        QMeQuizDetail qMeQuizDetail = QMeQuizDetailFixture.qMeQuizDetailWithQuestions();

        mockMvc.perform(
                put("/qme/quiz/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeQuestionDetailFixture.toJson(qMeQuizDetail))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quizID", is(1)))
                .andExpect(jsonPath("$.quizName", is("Some Quiz Name")))
        ;

        verify(quizService).update(Matchers.<QMeQuizDetail>anyObject(), eq(1L), eq(1L));
    }

    @Test
    public void testDelete() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(quizService, notNullValue());

        doNothing().when(quizService).delete(1L);

        mockMvc.perform(
                delete("/qme/quiz/1"))
                .andDo(print())
                .andExpect(status().isOk())
        ;

        verify(quizService).delete(1L);
    }

}
