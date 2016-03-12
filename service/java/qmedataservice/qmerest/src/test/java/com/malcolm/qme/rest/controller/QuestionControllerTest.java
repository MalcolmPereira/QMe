/**
 * Name      : com.malcolm.qme.rest.controller.QuestionControllerTest.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : Test Case for Question Controller
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.service.QuestionService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

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
        return null;
    }

}


