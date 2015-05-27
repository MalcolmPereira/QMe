/**
 * Name      : com.malcolm.qme.rest.controller.UserControllerTest.java
 * Date      : 5/27/2015
 * Developer : Malcolm
 * Purpose   : Test Case for User Controller
 **/
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.model.fixtures.QMeCategoryDetailFixtures;
import com.malcolm.qme.rest.model.fixtures.QMeUserDetailFixtures;
import com.malcolm.qme.rest.service.UserService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest extends QMeControllerTest{

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @Override
    public MockMvc getMockMVCInstance(ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver) {
        return MockMvcBuilders.standaloneSetup(userController).setHandlerExceptionResolvers(exceptionHandlerExceptionResolver).build();
    }

    @Test
    public void testList() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.list()).thenReturn(QMeUserDetailFixtures.simpleQMeUserDetailList());

        mockMvc.perform(
                get("/qme/user")
                        .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andDo(print())
                            .andExpect(jsonPath("$", hasSize(5)))
                            .andExpect(jsonPath("$[0].userId", is(1)))
                            .andExpect(jsonPath("$[1].userId", is(2)))
                            .andExpect(jsonPath("$[2].userId", is(3)))
                            .andExpect(jsonPath("$[3].userId", is(4)))
                            .andExpect(jsonPath("$[4].userId", is(5)))
        ;
    }
}
