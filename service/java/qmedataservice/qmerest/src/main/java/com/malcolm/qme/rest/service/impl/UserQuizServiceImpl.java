/**
 * Name      : com.malcolm.qme.rest.service.impl.UserQuizServiceImpl.java
 * Date      : 9/20/17
 * Developer : malcolm
 * Purpose   : User Quiz Service
 */

package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.Quiz;
import com.malcolm.qme.core.domain.UserQuiz;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserQuizRepository;
import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeQuizDetail;
import com.malcolm.qme.rest.model.QMeUserQuiz;
import com.malcolm.qme.rest.service.QuizService;
import com.malcolm.qme.rest.service.UserQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author malcolm
 */
@Service
public class UserQuizServiceImpl implements UserQuizService {

    @Autowired
    @Qualifier(value = "UserQuizRepository")
    private UserQuizRepository userQuizRepo;

    @Autowired
    private QuizService quizService;

    @Override
    public Long count() throws QMeServerException {
        try{
            return  userQuizRepo.count();

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeUserQuiz> list() throws QMeServerException {
        try{
            return  getQMeUserQuiz(userQuizRepo.findAll());

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeUserQuiz> list(Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException {
        try{
            return  getQMeUserQuiz(userQuizRepo.findAll(new PageSort(pageIndex,maxRows,sortAscending,sortFields)));

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeUserQuiz searchById(Long id) throws QMeResourceNotFoundException, QMeServerException {
        try{
            QMeUserQuiz qMeUserQuiz = getQMeUserQuiz(userQuizRepo.findById(id));
            QMeQuizDetail qMeQuizDetail = quizService.searchById(qMeUserQuiz.getQuizID());
            qMeUserQuiz.setQuiz(qMeQuizDetail);
            return qMeUserQuiz;
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeUserQuiz> findQuizzesForUser(Long userID, Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException {
        try{
            return  getQMeUserQuiz(userQuizRepo.findQuizzesForUser(userID, new PageSort(pageIndex,maxRows,sortAscending,sortFields)));

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeUserQuiz> findCompletedByUserId(Long userID, Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException {
        try{
            return  getQMeUserQuiz(userQuizRepo.findCompletedByUserId(userID, new PageSort(pageIndex,maxRows,sortAscending,sortFields)));

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeUserQuiz> findPendingByUserId(Long userID, Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException {
        try{
            return  getQMeUserQuiz(userQuizRepo.findPendingByUserId(userID, new PageSort(pageIndex,maxRows,sortAscending,sortFields)));

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
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

    /**
     * Map User Domain Object to REST Model
     *
     * @param userQuizList List of User Quiz
     * @return QMeUserQuiz List
     */
    private List<QMeUserQuiz> getQMeUserQuiz(List<UserQuiz> userQuizList) {
        List<QMeUserQuiz> qMeUserQuizzes = new ArrayList<>();
        if (userQuizList == null) {
            return qMeUserQuizzes;
        }
        qMeUserQuizzes.addAll(
                userQuizList.stream().map
                        (this::getQMeUserQuiz).collect(Collectors.toList())
        );
        return qMeUserQuizzes;
    }

    /**
     * Map User Domain Object to REST Model
     *
     * @param userQuiz UserQuiz
     * @return QMeUserQuiz QMeUserQuiz Detail
     */
    private QMeUserQuiz getQMeUserQuiz(UserQuiz userQuiz) {
        QMeUserQuiz qMeUserQuiz = new QMeUserQuiz();
        qMeUserQuiz.setUserQuizID(userQuiz.getUserQuizID());
        qMeUserQuiz.setUserID(userQuiz.getUserID());
        qMeUserQuiz.setQuizID(userQuiz.getQuizID());
        qMeUserQuiz.setCategoryID(userQuiz.getCategoryID());
        qMeUserQuiz.setQuizStartDate(userQuiz.getQuizStartDate());
        qMeUserQuiz.setQuizEndDate(userQuiz.getQuizEndDate());
        qMeUserQuiz.setUserQuizToken(userQuiz.getUserQuizToken());
        qMeUserQuiz.setQuizUserScore(userQuiz.getQuizUserScore());
        qMeUserQuiz.setQuizMaxScore(userQuiz.getQuizMaxScore());
        Quiz quiz = userQuiz.getQuiz();
        if(quiz != null){
            QMeQuizDetail quizDetail = new QMeQuizDetail();
            quizDetail.setQuizID(quiz.getQuizID());
            quizDetail.setQuizName(quiz.getQuizName());
            quizDetail.setQuizDesc(quiz.getQuizDesc());
            quizDetail.setLikes(quiz.getLikes());
            quizDetail.setQuizHit(quiz.getQuizHit());
            quizDetail.setQuizMaxAttempts(quiz.getQuizMaxAttempts());
            quizDetail.setQuizCreateDate(quiz.getQuizCreateDate());
            quizDetail.setCreateUserID(quiz.getCreateUserID());
            quizDetail.setQuizUpdateDate(quiz.getQuizUpdateDate());
            quizDetail.setUpdateUserID(quiz.getUpdateUserID());
            if(quiz.getCategory() != null){
                quizDetail.setCategoryName(quiz.getCategory().getCategoryName());
                quizDetail.setCategoryID(quiz.getCategoryID());
            }
            qMeUserQuiz.setQuiz(quizDetail);
        }


        return qMeUserQuiz;
    }
}
