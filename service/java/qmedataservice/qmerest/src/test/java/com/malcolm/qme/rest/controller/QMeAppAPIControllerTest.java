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
                .andExpect(jsonPath("links[0].rel", is("Qme API")))
                .andExpect(jsonPath("links[0].href", is("null/qme/api")))
                .andExpect(jsonPath("content[0].content", is("Qme User API")))
                .andExpect(jsonPath("content[0].links[0].rel", is("Get_User_list")))
                .andExpect(jsonPath("content[0].links[0].href", is("null/qme/user")))
                .andExpect(jsonPath("content[0].links[1].rel", is("Get_User_by_id")))
                .andExpect(jsonPath("content[0].links[1].href", is("null/qme/user/{id}")))
                .andExpect(jsonPath("content[0].links[2].rel", is("Get_User_by_name")))
                .andExpect(jsonPath("content[0].links[2].href", is("null/qme/user/search/{name}")))
                .andExpect(jsonPath("content[0].links[3].rel", is("Get_User_by_email")))
                .andExpect(jsonPath("content[0].links[3].href", is("null/qme/user/searchemail/{email}")))
                .andExpect(jsonPath("content[0].links[4].rel", is("Post_New_User")))
                .andExpect(jsonPath("content[0].links[4].href", is("null/qme/user/register")))
                .andExpect(jsonPath("content[0].links[5].rel", is("Post_Stage_New_User")))
                .andExpect(jsonPath("content[0].links[5].href", is("null/qme/user/stage")))
                .andExpect(jsonPath("content[0].links[6].rel", is("Post_Confirm_Stage_User")))
                .andExpect(jsonPath("content[0].links[6].href", is("null/qme/user/confirm")))
                .andExpect(jsonPath("content[0].links[7].rel", is("Put_Update_User")))
                .andExpect(jsonPath("content[0].links[7].href", is("null/qme/user")))
                .andExpect(jsonPath("content[0].links[8].rel", is("Delete_User")))
                .andExpect(jsonPath("content[0].links[8].href", is("null/qme/user/{id}")))
                .andExpect(jsonPath("content[0].links[9].rel", is("Get_Forgot_User_Name")))
                .andExpect(jsonPath("content[0].links[9].href", is("null/qme/user/reset/forgotusername/{email}")))
                .andExpect(jsonPath("content[0].links[10].rel", is("Put_Forgot_User_Password")))
                .andExpect(jsonPath("content[0].links[10].href", is("null/qme/user/reset/forgotpassword/{email}")))
                .andExpect(jsonPath("content[0].links[11].rel", is("Put_Reset_User_Password")))
                .andExpect(jsonPath("content[0].links[11].href", is("null/qme/user/reset/resetpassword/{email}")))
        ;
    }


}