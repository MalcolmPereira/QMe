/**
 * Name      : com.malcolm.qme.rest.controller.UserControllerTest.java
 * Date      : 5/27/2015
 * Developer : Malcolm
 * Purpose   : Test Case for User Controller
 **/
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    public void testListQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.list()).thenThrow(new QMeResourceException("Some Error in the Service"));

        mockMvc.perform(
                get("/qme/user")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
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
    public void testSearchByIdQMeResourceNotFoundException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchById(1L)).thenThrow(new QMeResourceNotFoundException("Resource Not Found Error "));

        mockMvc.perform(
                get("/qme/user/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
        ;

    }

    @Test
    public void testSearchByIdQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchById(1L)).thenThrow(new QMeResourceException("Some Error in the Service"));

        mockMvc.perform(
                get("/qme/user/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
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
    public void testSearchByUserNameQMeResourceNotFoundException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchByUser("suser1")).thenThrow(new QMeResourceNotFoundException("Resource Not Found Error "));

        mockMvc.perform(
                get("/qme/user/search/suser1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
        ;

    }

    @Test
    public void testSearchByUserNameQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchByUser("suser1")).thenThrow(new QMeResourceException("Some Error in the Service"));

        mockMvc.perform(
                get("/qme/user/search/suser1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;

    }

    @Test
    public void testSearchByUserEmail() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchByEmail("SimpleUser1@User.com")).thenReturn(QMeUserDetailFixtures.simpleQMeUserDetail());

        mockMvc.perform(
                get("/qme/user/searchemail/SimpleUser1@User.com")
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
    public void testSearchByUserEmailQMeResourceNotFoundException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchByEmail("SimpleUser1@User.com")).thenThrow(new QMeResourceNotFoundException("Resource Not Found Error "));

        mockMvc.perform(
                get("/qme/user/searchemail/SimpleUser1@User.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
        ;

    }

    @Test
    public void testSearchByUserEmailQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchByEmail("SimpleUser1@User.com")).thenThrow(new QMeResourceException("Some Error in the Service"));

        mockMvc.perform(
                get("/qme/user/searchemail/SimpleUser1@User.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
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
    public void testCreateQMeInvalidResourceDataException() throws Exception {

        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.save(anyObject(), eq((Long) null))).thenThrow(new QMeInvalidResourceDataException("Some Invalid data error in the Service"));

        QMeUser qmeUser = QMeUserFixtures.simpleQMeUser();

        mockMvc.perform(
                post("/qme/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;
    }

    @Test
    public void testCreateQMeResourceConflictException() throws Exception {

        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.save(anyObject(), eq((Long) null))).thenThrow(new QMeResourceConflictException("Some conflicting data error in the Service"));

        QMeUser qmeUser = QMeUserFixtures.simpleQMeUser();

        mockMvc.perform(
                post("/qme/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andDo(print())
        ;
    }

    @Test
    public void testCreateQMeResourceException() throws Exception {

        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.save(anyObject(), eq((Long) null))).thenThrow(new QMeResourceException("Some Error in the Service"));

        QMeUser qmeUser = QMeUserFixtures.simpleQMeUser();

        mockMvc.perform(
                post("/qme/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
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
    public void testUpdateQMeResourceNotFoundException() throws Exception {

        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.update(anyObject(), eq(1L), eq(1L))).thenThrow(new QMeResourceNotFoundException("Resource Not Found Error "));

        QMeUser qmeUser = QMeUserFixtures.simpleQMeUser();

        mockMvc.perform(
                put("/qme/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
        ;
    }

    @Test
    public void testUpdateQMeResourceException() throws Exception {

        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.update(anyObject(), eq(1L), eq(1L))).thenThrow(new QMeResourceException("Some Error in the Service"));

        QMeUser qmeUser = QMeUserFixtures.simpleQMeUser();

        mockMvc.perform(
                put("/qme/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
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

    @Test
    public void testDeleteQMeResourceNotFoundException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doThrow(new QMeResourceNotFoundException("Resource Not Found Error ")).when(userService).delete(1L);

        mockMvc.perform(
                delete("/qme/user/1"))
                .andExpect(status().isNotFound())
                .andDo(print())
        ;

    }

    @Test
    public void testDeleteQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doThrow(new QMeResourceException("Some Error in the Service")).when(userService).delete(1L);

        mockMvc.perform(
                delete("/qme/user/1"))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;
    }

    @Test
    public void testForgotUserName() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchByEmail("SimpleUser1@User.com")).thenReturn(QMeUserDetailFixtures.simpleQMeUserDetail());

        mockMvc.perform(
                get("/qme/user/reset/forgotusername/SimpleUser1@User.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("suser1")))
                .andDo(print())
        ;
        ;

    }

    @Test
    public void testForgotUserNameQMeResourceNotFoundException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchByEmail("SimpleUser1@User.com")).thenThrow(new QMeResourceNotFoundException("Resource Not Found Error "));

        mockMvc.perform(
                get("/qme/user/reset/forgotusername/SimpleUser1@User.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
        ;
    }

    @Test
    public void testForgotUserNameQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchByEmail("SimpleUser1@User.com")).thenThrow(new QMeResourceException("Some Error in the Service "));

        mockMvc.perform(
                get("/qme/user/reset/forgotusername/SimpleUser1@User.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;
    }

    @Test
    public void testForgotPassword() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doNothing().when(userService).forgotPassword("SimpleUser1@User.com", "someurl");

        mockMvc.perform(
                put("/qme/user/reset/forgotpassword/SimpleUser1@User.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("http://localhost:8080")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void testForgotPasswordQMeInvalidResourceDataException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doThrow(new QMeInvalidResourceDataException("some invalid err")).when(userService).forgotPassword("SimpleUser1@User.com", "someurl");

        mockMvc.perform(
                put("/qme/user/reset/forgotpassword/SimpleUser1@User.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("http://localhost:8080")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void testForgotPasswordQMeResourceNotFoundException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doThrow(new QMeResourceNotFoundException("some resource not found  err")).when(userService).forgotPassword("SimpleUser1@User.com", "someurl");

        mockMvc.perform(
                put("/qme/user/reset/forgotpassword/SimpleUser1@User.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("http://localhost:8080")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    public void testForgotPasswordQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doThrow(new QMeResourceException("some resource err")).when(userService).forgotPassword("SimpleUser1@User.com", "someurl");

        mockMvc.perform(
                put("/qme/user/reset/forgotpassword/SimpleUser1@User.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("http://localhost:8080")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError())
        ;
    }
}