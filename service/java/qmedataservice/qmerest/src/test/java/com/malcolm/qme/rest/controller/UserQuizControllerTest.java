/**
 * Name      : com.malcolm.qme.rest.controller.UserQuizControllerTest.java
 * Date      : 10/12/2017
 * Developer : Malcolm
 * Purpose   : Test Case for User Quiz Controller
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.model.fixtures.QMeUserQuizFixture;
import com.malcolm.qme.rest.service.UserQuizService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}
