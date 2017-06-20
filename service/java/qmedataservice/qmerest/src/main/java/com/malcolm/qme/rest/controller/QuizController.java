/**
 * Name      : com.malcolm.qme.rest.controller.QuizController.java
 * Date      : 6/20/2017
 * Developer : Malcolm
 * Purpose   : REST Controller for QMeQuiz
 */
package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.api.QuizAPI;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeQuizDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for QMeQuiz
 *
 * @author malcolm
 */
@RestController
public class QuizController implements QuizAPI {

    @Autowired
    private String endpointURL;

    @Override
    public Resource<Long> count() throws QMeResourceException {
        return null;
    }

    @Override
    public List<QMeQuizDetail> list() throws QMeResourceException {
        return null;
    }

    @Override
    public List<QMeQuizDetail> listPaged(String page, String pageSize, String sortType, String sortFields) throws QMeResourceException {
        return null;
    }

    @Override
    public QMeQuizDetail searchById(Long quizId) throws QMeResourceException {
        return null;
    }

    @Override
    public QMeQuizDetail create(QMeQuizDetail quiz) throws QMeResourceException {
        return null;
    }

    @Override
    public QMeQuizDetail update(Long quizId, QMeQuizDetail quiz) throws QMeResourceException {
        return null;
    }

    @Override
    public void delete(Long quizId) throws QMeResourceException {

    }
}
