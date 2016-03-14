/**
 * Name      : com.malcolm.qme.rest.controller.QuestionControllerTest.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : Test Case for Question Controller
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.service.QuestionService;
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
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


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

    }
}


