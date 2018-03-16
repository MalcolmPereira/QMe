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
import com.malcolm.qme.rest.model.QMeUserQuizDetail;
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
    public List<QMeUserQuizDetail> list() throws QMeServerException {
        try{
            return  getQMeUserQuiz(userQuizRepo.findAll());

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeUserQuizDetail> list(Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException {
        try{
            return  getQMeUserQuiz(userQuizRepo.findAll(new PageSort(pageIndex,maxRows,sortAscending,sortFields)));

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeUserQuizDetail searchById(Long id) throws QMeResourceNotFoundException, QMeServerException {
        try{
            QMeUserQuizDetail qMeUserQuiz = getQMeUserQuiz(userQuizRepo.findById(id));
            if(qMeUserQuiz == null){
                throw new QMeResourceNotFoundException("User Quiz with User Quiz ID " + id + " not found");
            }
            QMeQuizDetail qMeQuizDetail = quizService.searchById(qMeUserQuiz.getQuizID());
            if(qMeQuizDetail == null){
                throw new QMeResourceNotFoundException("Quiz with Quiz ID " + qMeUserQuiz.getQuizID() + " not found");
            }
            return qMeUserQuiz;
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public boolean findPendingForUserByQuizId(Long userID, Long quizID) throws QMeServerException, QMeResourceNotFoundException {
        try{
            QMeQuizDetail qMeQuizDetail = quizService.searchById(quizID);
            if(qMeQuizDetail == null){
                throw new QMeResourceNotFoundException("Quiz with Quiz ID " + quizID + " not found");
            }
            return userQuizRepo.findPendingForUserByQuizId(userID,quizID);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeUserQuizDetail> findQuizzesForUser(Long userID, Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException {
        try{
            return getQMeUserQuiz(userQuizRepo.findQuizzesForUser(userID, new PageSort(pageIndex,maxRows,sortAscending,sortFields)));

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeUserQuizDetail> findCompletedByUserId(Long userID, Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException {
        try{
            return  getQMeUserQuiz(userQuizRepo.findCompletedByUserId(userID, new PageSort(pageIndex,maxRows,sortAscending,sortFields)));

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeUserQuizDetail> findPendingByUserId(Long userID, Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException {
        try{
            return  getQMeUserQuiz(userQuizRepo.findPendingByUserId(userID, new PageSort(pageIndex,maxRows,sortAscending,sortFields)));

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeUserQuizDetail save(QMeUserQuiz qMeUserQuiz, Long userId) throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeResourceNotFoundException {
        try{
            UserQuiz userQuiz = getUserQuiz(qMeUserQuiz,userId);
            userQuiz = userQuizRepo.save(userQuiz);
            return getQMeUserQuiz(userQuiz);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }



    @Override
    public QMeUserQuizDetail update(QMeUserQuiz qMeUserQuiz, Long id, Long userId) throws QMeResourceNotFoundException, QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException {
        try{
            UserQuiz userQuiz = getUserQuiz(qMeUserQuiz,userId);
            userQuiz = userQuizRepo.update(userQuiz,userId);
            return getQMeUserQuiz(userQuiz);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public void delete(Long id) throws QMeResourceNotFoundException, QMeServerException {
        try{
           userQuizRepo.delete(id);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    /**
     * Get User Quiz
     * @param qMeUserQuiz
     * @return
     */
    private UserQuiz getUserQuiz(QMeUserQuiz qMeUserQuiz,Long userId) {
        UserQuiz userQuiz = null;
        if(qMeUserQuiz.getUserQuizID() != null){

        }else{
            userQuiz = new UserQuiz(userId,qMeUserQuiz.getQuizID(), qMeUserQuiz.getCategoryID(),0, null);
        }
        return userQuiz;
    }

    /**
     * Map User Domain Object to REST Model
     *
     * @param userQuizList List of User Quiz
     * @return QMeUserQuiz List
     */
    private List<QMeUserQuizDetail> getQMeUserQuiz(List<UserQuiz> userQuizList) {
        List<QMeUserQuizDetail> qMeUserQuizzes = new ArrayList<>();
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
    private QMeUserQuizDetail getQMeUserQuiz(UserQuiz userQuiz) {
        QMeUserQuizDetail qMeUserQuiz = new QMeUserQuizDetail();
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
            qMeUserQuiz.setQuizName(quiz.getQuizName());
            qMeUserQuiz.setQuizDesc(quiz.getQuizDesc());
            qMeUserQuiz.setLikes(quiz.getLikes());
            qMeUserQuiz.setQuizHit(quiz.getQuizHit());
            qMeUserQuiz.setQuizMaxAttempts(quiz.getQuizMaxAttempts());
            qMeUserQuiz.setQuizCreateDate(quiz.getQuizCreateDate());
            qMeUserQuiz.setCreateUserID(quiz.getCreateUserID());
            qMeUserQuiz.setQuizUpdateDate(quiz.getQuizUpdateDate());
            qMeUserQuiz.setUpdateUserID(quiz.getUpdateUserID());
            if(quiz.getCategory() != null){
                qMeUserQuiz.setCategoryName(quiz.getCategory().getCategoryName());
                qMeUserQuiz.setCategoryID(quiz.getCategoryID());
            }
        }
        return qMeUserQuiz;
    }
}
