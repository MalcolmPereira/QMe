/**
 * Name      : com.malcolm.qme.rest.service.impl.QuizServiceImpl.java
 * Date      : 6/22/2017
 * Developer : Malcolm
 * Purpose   : QMeQuiz Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.Quiz;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.core.repository.QuizRepository;
import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeQuiz;
import com.malcolm.qme.rest.model.QMeQuizDetail;
import com.malcolm.qme.rest.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 *
 * QMeQuiz Service Implementation
 */
@Service
public class QuizServiceImpl  implements QuizService{
    /**
     * QMeQuiz Repository
     */
    @Autowired
    @Qualifier(value = "QuizRepository")
    private QuizRepository quizRepo;

    /**
     * QMeQuestion Repository
     */
    @Autowired
    @Qualifier(value = "QuestionRepository")
    private QuestionRepository questionRepo;

    @Override
    public Long count() throws QMeServerException {
        try {
            return quizRepo.count();

        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }
    }

    @Override
    public List<QMeQuizDetail> list() throws QMeServerException {
        try {
            return getQMeQuizDetail(quizRepo.findAll());

        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }
    }

    @Override
    public List<QMeQuizDetail> list(Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException {
        try {
            return getQMeQuizDetail(quizRepo.findAll(new PageSort(pageIndex, maxRows, sortAscending, sortFields)));

        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }
    }

    @Override
    public QMeQuizDetail searchById(Long id) throws QMeResourceNotFoundException, QMeServerException {
        try {
            Quiz quiz = quizRepo.findById(id);
            if (quiz == null) {
                throw new QMeResourceNotFoundException("Quiz with Quiz ID " + id + " not found");
            }
            QMeQuizDetail quizDetail = getQMeQuizDetail(quiz);

            //TODO:
            //Get Quiz Questions

            return quizDetail;

        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }
    }

    @Override
    public QMeQuizDetail save(QMeQuiz qMeQuiz, Long userId) throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeResourceNotFoundException {
        return null;
    }

    @Override
    public QMeQuizDetail update(QMeQuiz qMeQuiz, Long id, Long userId) throws QMeResourceNotFoundException, QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException {
        return null;
    }

    @Override
    public void delete(Long id) throws QMeResourceNotFoundException, QMeServerException {
    }

    /**
     * Map Quiz Domain Object List to REST Model
     *
     * @param quizList Quiz List
     * @return QMeQuizDetail List
     */
    private List<QMeQuizDetail> getQMeQuizDetail(List<Quiz> quizList) {
        List<QMeQuizDetail> qmeQuizDetails = new ArrayList<>();
        if (quizList == null) {
            return qmeQuizDetails;
        }
        qmeQuizDetails.addAll(
                quizList.stream().map(this::getQMeQuizDetail).collect(Collectors.toList())
        );
        return qmeQuizDetails;
    }

    /**
     * Map Quiz Domain Object to REST Model
     *
     * @param quiz
     * @return QMeQuizDetail
     */
    private QMeQuizDetail getQMeQuizDetail(Quiz quiz) {
        QMeQuizDetail qMeQuizDetail = new QMeQuizDetail();
        qMeQuizDetail.setQuizID(quiz.getQuizID());
        qMeQuizDetail.setQuizName(quiz.getQuizName());
        qMeQuizDetail.setQuizDesc(quiz.getQuizDesc());
        qMeQuizDetail.setCategoryID(quiz.getCategoryID());
        qMeQuizDetail.setLikes(quiz.getLikes());
        qMeQuizDetail.setQuizHit(quiz.getQuizHit());
        qMeQuizDetail.setQuizMaxAttempts(quiz.getQuizMaxAttempts());
        qMeQuizDetail.setQuizCreateDate(quiz.getQuizCreateDate());
        qMeQuizDetail.setCreateUserID(quiz.getCreateUserID());
        qMeQuizDetail.setQuizUpdateDate(quiz.getQuizUpdateDate());
        qMeQuizDetail.setUpdateUserID(quiz.getUpdateUserID());
        if(quiz.getCreateUser() != null){
            qMeQuizDetail.setCreateUserName(quiz.getCreateUser().getUserName());
        }
        if(quiz.getUpdateUser() != null){
            qMeQuizDetail.setUpdateUserName(quiz.getUpdateUser().getUserName());
        }
        if(quiz.getCategory() != null){
            qMeQuizDetail.setCategoryName(quiz.getCategory().getCategoryName());
        }
        return qMeQuizDetail;
    }
}
