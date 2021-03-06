/**
 * Name      : com.malcolm.qme.rest.controller.UserControllerTest.java
 * Date      : 5/27/2015
 * Developer : Malcolm
 * Purpose   : Test Case for User Controller
 **/
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeResetPassword;
import com.malcolm.qme.rest.model.QMeStageUser;
import com.malcolm.qme.rest.model.QMeUser;
import com.malcolm.qme.rest.model.fixtures.QMeResetPasswordFixtures;
import com.malcolm.qme.rest.model.fixtures.QMeUserDetailFixtures;
import com.malcolm.qme.rest.model.fixtures.QMeUserFixtures;
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
import static org.mockito.Mockito.*;
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

    @Before
    public void setContext(){
        SecurityContextHolder.getContext().setAuthentication((QMeUserDetails)QMeUserDetails.create(1L, "admin", "password", "USER","ADMIN"));
    }


    @Override
    public MockMvc getMockMVCInstance(ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver) {
        MockMvc mockMVC =  MockMvcBuilders.standaloneSetup(userController).setHandlerExceptionResolvers(exceptionHandlerExceptionResolver).build();
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        SecurityContextHolder.getContext().setAuthentication(qMeUserDetails);
        return mockMVC;
    }


    @Test
    public void testCount() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.count()).thenReturn(10L);

        mockMvc.perform(
                get("/qme/user/count")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content", is(10)))
        ;

        verify(userService).count();
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

        verify(userService).list();
    }

    @Test
    public void testListPaged() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());


        when(userService.list(0,10,true,"FIRSTNAME")).thenReturn(QMeUserDetailFixtures.simpleQMeUserDetailList());

        mockMvc.perform(
                get("/qme/user/paged?page=0&pagesize=10&sorttype=true&sortfields=FIRSTNAME")
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

        verify(userService).list(0,10,true,"FIRSTNAME");
    }

    @Test
    public void testListPagedNoPageNumber() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());


        when(userService.list()).thenReturn(QMeUserDetailFixtures.simpleQMeUserDetailList());

        mockMvc.perform(
                get("/qme/user/paged?page=&pagesize=10&sorttype=true&sortfields=FIRSTNAME")
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

        verify(userService).list();
    }


    @Test
    public void testListQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.list()).thenThrow(new QMeServerException("Some Error in the Service"));

        mockMvc.perform(
                get("/qme/user")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;

        verify(userService).list();
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

        verify(userService).searchById(1L);

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

        verify(userService).searchById(1L);
    }

    @Test
    public void testSearchByIdQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchById(1L)).thenThrow(new QMeServerException("Some Error in the Service"));

        mockMvc.perform(
                get("/qme/user/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;

        verify(userService).searchById(1L);

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

        verify(userService).searchByUser("suser1");

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

        verify(userService).searchByUser("suser1");
    }

    @Test
    public void testSearchByUserNameQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchByUser("suser1")).thenThrow(new QMeServerException("Some Error in the Service"));

        mockMvc.perform(
                get("/qme/user/search/suser1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;

        verify(userService).searchByUser("suser1");
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

        verify(userService).searchByEmail("SimpleUser1@User.com");
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

        verify(userService).searchByEmail("SimpleUser1@User.com");
    }

    @Test
    public void testSearchByUserEmailQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchByEmail("SimpleUser1@User.com")).thenThrow(new QMeServerException("Some Error in the Service"));

        mockMvc.perform(
                get("/qme/user/searchemail/SimpleUser1@User.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;

        verify(userService).searchByEmail("SimpleUser1@User.com");

    }

    @Test
    public void testCreate() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.save(anyObject(), eq((Long) 1L))).thenReturn(QMeUserDetailFixtures.simpleQMeUserDetail());

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

        verify(userService).save(anyObject(), eq((Long) 1L));
    }

    @Test
    public void testStageUser() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doNothing().when(userService).stageUser(anyObject());

        QMeStageUser qmeUser = QMeUserFixtures.simpleQMeStagedUser();

        mockMvc.perform(
                post("/qme/user/stage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        ;

        verify(userService).stageUser(anyObject());
    }

    @Test
    public void testStageUserUnSuccessful() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doThrow(new QMeInvalidResourceDataException("Some Invalid data error in the Service")).when(userService).stageUser(anyObject());

        QMeStageUser qmeUser = QMeUserFixtures.simpleQMeStagedUser();

        mockMvc.perform(
                post("/qme/user/stage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void testStageUserQMeResourceConflictException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());


        doThrow(new QMeResourceConflictException("Some conflicting data error in the Service")).when(userService).stageUser(anyObject());

        QMeStageUser qmeUser = QMeUserFixtures.simpleQMeStagedUser();

        mockMvc.perform(
                post("/qme/user/stage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict())
        ;
    }

    @Test
    public void testStageUserQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());


        doThrow(new QMeServerException("Some Error in the Service")).when(userService).stageUser(anyObject());

        QMeStageUser qmeUser = QMeUserFixtures.simpleQMeStagedUser();

        mockMvc.perform(
                post("/qme/user/stage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError())
        ;
    }

    @Test
    public void testConfirmRegistration() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doNothing().when(userService).confirmUserRegistration("sometoken");

        mockMvc.perform(
                post("/qme/user/confirm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("sometoken")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        ;

        verify(userService).confirmUserRegistration("sometoken");

    }

    @Test
    public void testConfirmRegistrationQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doThrow(new QMeServerException("Some Error in the Service")).when(userService).confirmUserRegistration("sometoken");

        mockMvc.perform(
                post("/qme/user/confirm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("sometoken")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError())
        ;
    }

    @Test
    public void testCreateQMeInvalidResourceDataException() throws Exception {

        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.save(anyObject(), eq((Long) 1L))).thenThrow(new QMeInvalidResourceDataException("Some Invalid data error in the Service"));

        QMeUser qmeUser = QMeUserFixtures.simpleQMeUser();

        mockMvc.perform(
                post("/qme/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;

        verify(userService).save(anyObject(), eq((Long) 1L));
    }

    @Test
    public void testCreateQMeResourceConflictException() throws Exception {

        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.save(anyObject(), eq((Long) 1L))).thenThrow(new QMeResourceConflictException("Some conflicting data error in the Service"));

        QMeUser qmeUser = QMeUserFixtures.simpleQMeUser();

        mockMvc.perform(
                post("/qme/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andDo(print())
        ;

        verify(userService).save(anyObject(), eq((Long) 1L));
    }

    @Test
    public void testCreateQMeResourceException() throws Exception {

        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.save(anyObject(), eq((Long) 1L))).thenThrow(new QMeServerException("Some Error in the Service"));

        QMeUser qmeUser = QMeUserFixtures.simpleQMeUser();

        mockMvc.perform(
                post("/qme/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;

        verify(userService).save(anyObject(), eq((Long) 1L));
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

        verify(userService).update(anyObject(), eq(1L), eq(1L));
    }

    @Test
    public void testUpdateNullCurrentUser() throws Exception {

        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        SecurityContextHolder.getContext().setAuthentication(null);

        QMeUser qmeUser = QMeUserFixtures.simpleQMeUser();

        mockMvc.perform(
                put("/qme/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
        ;

    }

    @Test
    public void testUpdateNoUserRoleUser() throws Exception {

        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        SecurityContextHolder.getContext().setAuthentication((QMeUserDetails)QMeUserDetails.create(2L, "admin", "password", "USER"));

        QMeUser qmeUser = QMeUserFixtures.simpleQMeUser();

        mockMvc.perform(
                put("/qme/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
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

        verify(userService).update(anyObject(), eq(1L), eq(1L));
    }

    @Test
    public void testUpdateQMeResourceException() throws Exception {

        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.update(anyObject(), eq(1L), eq(1L))).thenThrow(new QMeServerException("Some Error in the Service"));

        QMeUser qmeUser = QMeUserFixtures.simpleQMeUser();

        mockMvc.perform(
                put("/qme/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeUserFixtures.toJson(qmeUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;

        verify(userService).update(anyObject(), eq(1L), eq(1L));
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

        verify(userService).delete(1L);
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

        verify(userService).delete(1L);

    }

    @Test
    public void testDeleteQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doThrow(new QMeServerException("Some Error in the Service")).when(userService).delete(1L);

        mockMvc.perform(
                delete("/qme/user/1"))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;

        verify(userService).delete(1L);
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
                .andExpect(jsonPath("content", is("suser1")))
                .andDo(print())
        ;

        verify(userService).searchByEmail("SimpleUser1@User.com");
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

        verify(userService).searchByEmail("SimpleUser1@User.com");
    }

    @Test
    public void testForgotUserNameQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        when(userService.searchByEmail("SimpleUser1@User.com")).thenThrow(new QMeServerException("Some Error in the Service "));

        mockMvc.perform(
                get("/qme/user/reset/forgotusername/SimpleUser1@User.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print())
        ;

        verify(userService).searchByEmail("SimpleUser1@User.com");
    }

    @Test
    public void testForgotPassword() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doNothing().when(userService).forgotPassword("SimpleUser1@User.com", "http://localhost:8080");

        mockMvc.perform(
                put("/qme/user/reset/forgotpassword/SimpleUser1@User.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("http://localhost:8080")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        ;

        verify(userService).forgotPassword("SimpleUser1@User.com", "http://localhost:8080");
    }

    @Test
    public void testForgotPasswordQMeInvalidResourceDataException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doThrow(new QMeInvalidResourceDataException("some invalid err")).when(userService).forgotPassword("SimpleUser1@User.com", "http://localhost:8080");

        mockMvc.perform(
                put("/qme/user/reset/forgotpassword/SimpleUser1@User.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("http://localhost:8080")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;

        verify(userService).forgotPassword("SimpleUser1@User.com", "http://localhost:8080");
    }

    @Test
    public void testForgotPasswordQMeResourceNotFoundException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doThrow(new QMeResourceNotFoundException("some resource not found  err")).when(userService).forgotPassword("SimpleUser1@User.com", "http://localhost:8080");

        mockMvc.perform(
                put("/qme/user/reset/forgotpassword/SimpleUser1@User.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("http://localhost:8080")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        verify(userService).forgotPassword("SimpleUser1@User.com", "http://localhost:8080");
    }

    @Test
    public void testForgotPasswordQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        doThrow(new QMeServerException("some resource err")).when(userService).forgotPassword("SimpleUser1@User.com", "http://localhost:8080");

        mockMvc.perform(
                put("/qme/user/reset/forgotpassword/SimpleUser1@User.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("http://localhost:8080")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError())
        ;

        verify(userService).forgotPassword("SimpleUser1@User.com", "http://localhost:8080");
    }

    @Test
    public void testResetPassword() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        QMeResetPassword qMeResetPassword  = QMeResetPasswordFixtures.simpleQMeResetPassword();

        when(userService.resetPassword(any(String.class), any(QMeResetPassword.class))).thenReturn(QMeUserDetailFixtures.simpleQMeUserDetail());

        mockMvc.perform(
                put("/qme/user/reset/resetpassword/SimpleUser1@User.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeResetPasswordFixtures.toJson(qMeResetPassword))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        ;

        verify(userService).resetPassword(any(String.class), any(QMeResetPassword.class));

    }

    @Test
    public void testResetPasswordQMeInvalidResourceDataException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        QMeResetPassword qMeResetPassword  = QMeResetPasswordFixtures.simpleQMeResetPassword();

        when(userService.resetPassword(any(String.class), any(QMeResetPassword.class))).thenThrow(new QMeInvalidResourceDataException("some invalid err"));

        mockMvc.perform(
                put("/qme/user/reset/resetpassword/SimpleUser1@User.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeResetPasswordFixtures.toJson(qMeResetPassword))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;

        verify(userService).resetPassword(any(String.class), any(QMeResetPassword.class));

    }

    @Test
    public void testResetPasswordQMeResourceNotFoundException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        QMeResetPassword qMeResetPassword  = QMeResetPasswordFixtures.simpleQMeResetPassword();

        when(userService.resetPassword(any(String.class), any(QMeResetPassword.class))).thenThrow(new QMeResourceNotFoundException("Resource Not Found Error "));

        mockMvc.perform(
                put("/qme/user/reset/resetpassword/SimpleUser1@User.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeResetPasswordFixtures.toJson(qMeResetPassword))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        verify(userService).resetPassword(any(String.class), any(QMeResetPassword.class));

    }

    @Test
    public void testResetPasswordQMeResourceException() throws Exception {
        assertThat(mockMvc, notNullValue());
        assertThat(userService, notNullValue());

        QMeResetPassword qMeResetPassword  = QMeResetPasswordFixtures.simpleQMeResetPassword();

        when(userService.resetPassword(any(String.class), any(QMeResetPassword.class))).thenThrow(new QMeServerException("Some Server Error "));

        mockMvc.perform(
                put("/qme/user/reset/resetpassword/SimpleUser1@User.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(QMeResetPasswordFixtures.toJson(qMeResetPassword))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError())
        ;

        verify(userService).resetPassword(any(String.class), any(QMeResetPassword.class));
    }
}


