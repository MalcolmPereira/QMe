/**
 * Name      : com.malcolm.qme.rest.api.QMeAPI.java
 * Date      : 9/28/15
 * Developer : Malcolm
 * Purpose   : QME REST API Implementation Test
 */
package com.malcolm.qme.rest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QMeAppAPIControllerTest extends QMeControllerTest{

    @InjectMocks
    private QMeAppAPIController qMeAppAPIController;

    @Override
    protected MockMvc getMockMVCInstance(ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver) {
        return MockMvcBuilders.standaloneSetup(qMeAppAPIController).setHandlerExceptionResolvers(exceptionHandlerExceptionResolver).build();
    }

    @Test
    public void testApi() throws Exception {
        assertThat(mockMvc, notNullValue());

        mockMvc.perform(
                get("/qme/api")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("Get_User_list", is("null/qme/user")))
                .andExpect(jsonPath("Get_User_by_id", is("null/qme/user/{id}")))
                .andExpect(jsonPath("Get_User_by_name", is("null/qme/user/search/{name}")))
                .andExpect(jsonPath("Get_User_by_email", is("null/qme/user/searchemail/{email}")))
                .andExpect(jsonPath("Post_New_User", is("null/qme/user/register")))
                .andExpect(jsonPath("Post_Stage_New_User", is("null/qme/user/stage")))
                .andExpect(jsonPath("Post_Confirm_Stage_User", is("null/qme/user/confirm")))
                .andExpect(jsonPath("Put_Update_User", is("null/qme/user")))
                .andExpect(jsonPath("Delete_User", is("null/qme/user/{id}")))
                .andExpect(jsonPath("Get_Forgot_User_Name", is("null/qme/user/reset/forgotusername/{email}")))
                .andExpect(jsonPath("Put_Forgot_User_Password", is("null/qme/user/reset/forgotpassword/{email}")))
                .andExpect(jsonPath("Put_Reset_User_Password", is("null/qme/user/reset/resetpassword/{email}")))

        ;
    }


}