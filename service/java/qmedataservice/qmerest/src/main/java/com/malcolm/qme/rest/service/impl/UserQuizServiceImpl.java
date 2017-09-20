/**
 * Name      : com.malcolm.qme.rest.service.impl.UserQuizServiceImpl.java
 * Date      : 9/20/17
 * Developer : malcolm
 * Purpose   : User Quiz Service
 */

package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeUserQuiz;
import com.malcolm.qme.rest.service.UserQuizService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author malcolm
 */
@Service
public class UserQuizServiceImpl implements UserQuizService {

    @Override
    public Long count() throws QMeServerException {
        return null;
    }

    @Override
    public List<QMeUserQuiz> list() throws QMeServerException {
        return null;
    }

    @Override
    public List<QMeUserQuiz> list(Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException {
        return null;
    }

    @Override
    public QMeUserQuiz searchById(Long id) throws QMeResourceNotFoundException, QMeServerException {
        return null;
    }

    @Override
    public QMeUserQuiz save(QMeUserQuiz qMeUserQuiz, Long userId) throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeResourceNotFoundException {
        return null;
    }

    @Override
    public QMeUserQuiz update(QMeUserQuiz qMeUserQuiz, Long id, Long userId) throws QMeResourceNotFoundException, QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException {
        return null;
    }

    @Override
    public void delete(Long id) throws QMeResourceNotFoundException, QMeServerException {
    }
}
