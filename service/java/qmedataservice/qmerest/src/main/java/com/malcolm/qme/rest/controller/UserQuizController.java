/**
 * Name      : com.malcolm.qme.rest.controller.UserQuizController.java
 * Date      : 9/20/2017
 * Developer : Malcolm
 * Purpose   : REST Controller for QMe User Quiz
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.UserQuizAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for QMeQuiz
 *
 * @author malcolm
 */
@RestController
public class UserQuizController implements UserQuizAPI  {

    @Autowired
    private String endpointURL;

}
