/**
 * Name      : com.malcolm.qme.rest.controller.UserQuizControllerTest.java
 * Date      : 10/12/2017
 * Developer : Malcolm
 * Purpose   : Test Case for User Quiz Controller
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.model.QMeUserQuiz;
import com.malcolm.qme.rest.model.fixtures.QMeQuizDetailFixture;
import com.malcolm.qme.rest.model.fixtures.QMeUserFixtures;
import com.malcolm.qme.rest.model.fixtures.QMeUserQuizFixture;
import com.malcolm.qme.rest.service.QuizService;
import com.malcolm.qme.rest.service.UserQuizService;
import com.malcolm.qme.rest.service.UserService;
import com.malcolm.qme.security.service.QMeUserDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test Case for User Quiz Controller
 *
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class UserQuizControllerTest extends QMeControllerTest {

    @Mock
    private UserQuizService userQuizService;

    @Mock
    private QuizService quizService;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserQuizController userQuizController;

    @Override
    protected MockMvc getMockMVCInstance(ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver) {
        return MockMvcBuilders
                .standaloneSetup(userQuizController)
                .setHandlerExceptionResolvers(exceptionHandlerExceptionResolver)
                .build();
    }

    @Before
    public void setContext() {
        SecurityContextHolder.getContext().setAuthentication((QMeUserDetails) QMeUserDetails.create(1L, "user", "password", "USER"));
    }

    @Test
    public void testCount() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userQuizService, notNullValue());

        when(userQuizService.count()).thenReturn(1L);

        mockMvc.perform(
                get("/qme/userquiz/count")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content", is(1)))
        ;

        verify(userQuizService).count();
    }


    @Test
    public void testList() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userQuizService, notNullValue());

        when(userQuizService.list()).thenReturn(QMeUserQuizFixture.simpleQMeQuizDetailList());

        mockMvc.perform(
                get("/qme/userquiz")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].userQuizID", is(1)))
                .andExpect(jsonPath("$[1].userQuizID", is(2)))
                .andExpect(jsonPath("$[2].userQuizID", is(3)))
                .andExpect(jsonPath("$[3].userQuizID", is(4)))
                .andExpect(jsonPath("$[4].userQuizID", is(5)))
        ;
        verify(userQuizService).list();
    }

    @Test
    public void testFindQuizzesForUser() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userQuizService, notNullValue());

        when(userQuizService.findQuizzesForUser(1L,0,10,true,"MAXSCORE")).thenReturn(QMeUserQuizFixture.simpleQMeQuizDetailList());


        mockMvc.perform(
                get("/qme/userquiz/list?page=0&pagesize=10&sorttype=true&sortfields=MAXSCORE")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].userQuizID", is(1)))
                .andExpect(jsonPath("$[1].userQuizID", is(2)))
                .andExpect(jsonPath("$[2].userQuizID", is(3)))
                .andExpect(jsonPath("$[3].userQuizID", is(4)))
                .andExpect(jsonPath("$[4].userQuizID", is(5)))
        ;
        verify(userQuizService).findQuizzesForUser(1L,0,10,true,"MAXSCORE");
    }

    @Test
    public void testFindPendingQuizzesForUser() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userQuizService, notNullValue());

        when(userQuizService.findPendingByUserId(1L,0,10,true,"MAXSCORE")).thenReturn(QMeUserQuizFixture.simpleQMeQuizDetailList());


        mockMvc.perform(
                get("/qme/userquiz/listpending?page=0&pagesize=10&sorttype=true&sortfields=MAXSCORE")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].userQuizID", is(1)))
                .andExpect(jsonPath("$[1].userQuizID", is(2)))
                .andExpect(jsonPath("$[2].userQuizID", is(3)))
                .andExpect(jsonPath("$[3].userQuizID", is(4)))
                .andExpect(jsonPath("$[4].userQuizID", is(5)))
        ;
        verify(userQuizService).findPendingByUserId(1L,0,10,true,"MAXSCORE");
    }

    @Test
    public void testFindCompletedQuizzesForUser() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userQuizService, notNullValue());

        when(userQuizService.findCompletedByUserId(1L,0,10,true,"MAXSCORE")).thenReturn(QMeUserQuizFixture.simpleQMeQuizDetailList());


        mockMvc.perform(
                get("/qme/userquiz/listcompleted?page=0&pagesize=10&sorttype=true&sortfields=MAXSCORE")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].userQuizID", is(1)))
                .andExpect(jsonPath("$[1].userQuizID", is(2)))
                .andExpect(jsonPath("$[2].userQuizID", is(3)))
                .andExpect(jsonPath("$[3].userQuizID", is(4)))
                .andExpect(jsonPath("$[4].userQuizID", is(5)))
        ;
        verify(userQuizService).findCompletedByUserId(1L,0,10,true,"MAXSCORE");
    }

    @Test
    public void testRegisterForQuiz() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userQuizService, notNullValue());
        assertThat(quizService, notNullValue());
        assertThat(userService, notNullValue());

        when(quizService.searchById(1L)).thenReturn(QMeQuizDetailFixture.simpleQMeQuizDetail());
        when(userService.searchById(1L)).thenReturn(QMeUserFixtures.simpleQMeUserDetailsWithId());
        when(userQuizService.findPendingForUserByQuizId(1L, 1L)).thenReturn(false);
        when(userQuizService.save(any(QMeUserQuiz.class),eq(1L))).thenReturn(QMeUserQuizFixture.simpleQMeQuizDetail());

        mockMvc.perform(
                post("/qme/userquiz/register/1")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.userQuizID", is(1)))
                    .andExpect(jsonPath("$.quizID", is(1)))
                    .andExpect(jsonPath("$.categoryID", is(1)))
        ;
        verify(quizService).searchById(1L);
        verify(userService).searchById(1L);
        verify(userQuizService).findPendingForUserByQuizId(1L,1L);
        verify(userQuizService).save(anyObject(),eq(1L));
    }

    @Test
    public void testRegisterForQuizWhilePending() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userQuizService, notNullValue());
        assertThat(quizService, notNullValue());
        assertThat(userService, notNullValue());

        when(quizService.searchById(1L)).thenReturn(QMeQuizDetailFixture.simpleQMeQuizDetail());
        when(userService.searchById(1L)).thenReturn(QMeUserFixtures.simpleQMeUserDetailsWithId());
        when(userQuizService.findPendingForUserByQuizId(1L, 1L)).thenReturn(true);

        mockMvc.perform(
                post("/qme/userquiz/register/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(409))
                .andDo(print())
        ;
        verify(quizService).searchById(1L);
        verify(userService).searchById(1L);
        verify(userQuizService).findPendingForUserByQuizId(1L,1L);
    }

    @Test
    public void testUnRegisterForQuiz() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userQuizService, notNullValue());
        assertThat(userService, notNullValue());
        when(userService.searchById(1L)).thenReturn(QMeUserFixtures.simpleQMeUserDetailsWithId());
        when(userQuizService.searchById(1L)).thenReturn(QMeUserQuizFixture.simpleQMeQuizDetail());
        doNothing().when(userQuizService).delete(1L);
        mockMvc.perform(
                post("/qme/userquiz/unregister/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
        ;
        verify(userQuizService).searchById(1L);
        verify(userService).searchById(1L);
        verify(userQuizService).delete(eq(1L));
    }

    @Test
    public void testStartQuiz() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userQuizService, notNullValue());
        assertThat(quizService, notNullValue());

        when(userQuizService.searchById(1L)).thenReturn(QMeUserQuizFixture.simpleQMeQuizDetail());
        when(quizService.searchById(1L)).thenReturn(QMeQuizDetailFixture.qMeQuizDetailWithQuestionsIdsAndDetails());
        when(userQuizService.update(any(QMeUserQuiz.class),eq(1L),eq(1L))).thenReturn(QMeUserQuizFixture.simpleQMeQuizDetail());

        mockMvc.perform(
                post("/qme/userquiz/start/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
        ;

        verify(userQuizService).searchById(1L);
        verify(quizService).searchById(1L);
        verify(userQuizService).update(any(QMeUserQuiz.class),eq(1L),eq(1L));
    }
}