/**
 * Name      : com.malcolm.qme.rest.controller.UserControllerTest.java
 * Date      : 5/27/2015
 * Developer : Malcolm
 * Purpose   : Test Case for User Controller
 **/
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.model.QMeUser;
import com.malcolm.qme.rest.model.fixtures.QMeUserDetailFixtures;
import com.malcolm.qme.rest.model.fixtures.QMeUserFixtures;
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
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest extends QMeControllerTest {

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

    @Test
    public void testSearchById() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchById(1L)).thenReturn(QMeUserDetailFixtures.simpleQMeUserDetail());

        mockMvc.perform(
                get("/qme/user/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.userName", is("suser1")))
                .andExpect(jsonPath("$.userFirstName", is("Simple 1")))
                .andExpect(jsonPath("$.userLastName", is("Simple User 1")))
                .andExpect(jsonPath("$.userEmail", is("SimpleUser1@User.com")))
        ;

    }

    @Test
    public void testSearchByUserName() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchByUser("suser1")).thenReturn(QMeUserDetailFixtures.simpleQMeUserDetail());

        mockMvc.perform(
                get("/qme/user/search/suser1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.userName", is("suser1")))
                .andExpect(jsonPath("$.userFirstName", is("Simple 1")))
                .andExpect(jsonPath("$.userLastName", is("Simple User 1")))
                .andExpect(jsonPath("$.userEmail", is("SimpleUser1@User.com")))
        ;

    }

    @Test
    public void testSearchByUserEmail() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchByEmail("SimpleUser1@User.com")).thenReturn(QMeUserDetailFixtures.simpleQMeUserDetail());

        mockMvc.perform(
                get("/qme/user/search/email/SimpleUser1@User.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.userName", is("suser1")))
                .andExpect(jsonPath("$.userFirstName", is("Simple 1")))
                .andExpect(jsonPath("$.userLastName", is("Simple User 1")))
                .andExpect(jsonPath("$.userEmail", is("SimpleUser1@User.com")))
        ;

    }

    @Test
    public void testCreate() throws Exception {

        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.save(anyObject(), eq((Long) null))).thenReturn(QMeUserDetailFixtures.simpleQMeUserDetail());

        QMeUser qmeUser = QMeUserFixtures.simpleQMeUser();

        mockMvc.perform(
                post("/qme/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.userName", is("suser1")))
                .andExpect(jsonPath("$.userFirstName", is("Simple 1")))
                .andExpect(jsonPath("$.userLastName", is("Simple User 1")))
                .andExpect(jsonPath("$.userEmail", is("SimpleUser1@User.com")))
        ;
    }

    @Test
    public void testUpdate() throws Exception {

        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.update(anyObject(), eq(1L), eq(1L))).thenReturn(QMeUserDetailFixtures.simpleQMeUserDetail());

        QMeUser qmeUser = QMeUserFixtures.simpleQMeUser();

        mockMvc.perform(
                put("/qme/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.userName", is("suser1")))
                .andExpect(jsonPath("$.userFirstName", is("Simple 1")))
                .andExpect(jsonPath("$.userLastName", is("Simple User 1")))
                .andExpect(jsonPath("$.userEmail", is("SimpleUser1@User.com")))
        ;
    }

    @Test
    public void testDelete() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doNothing().when(userService).delete(1L);

        mockMvc.perform(
                delete("/qme/user/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
        ;

    }
}