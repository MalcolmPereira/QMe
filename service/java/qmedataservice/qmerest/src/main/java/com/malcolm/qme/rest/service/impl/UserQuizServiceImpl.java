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
import com.malcolm.qme.rest.model.*;
import com.malcolm.qme.rest.service.QuizService;
import com.malcolm.qme.rest.service.UserQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
    public QMeUserQuizDetail startQuiz(Long userID, Long userQuizID) throws QMeServerException, QMeResourceNotFoundException, QMeInvalidResourceDataException, QMeResourceConflictException {
        try{
            QMeUserQuizDetail userQuiz = searchById(userQuizID);
            if(userQuiz == null){
                throw new QMeResourceNotFoundException("User Quiz with Quiz ID " + userQuizID + " not found");
            }
            QMeQuizDetail qMeQuizDetail = quizService.searchById(userQuiz.getQuizID());
            if(qMeQuizDetail == null){
                throw new QMeResourceNotFoundException("Quiz with Quiz ID " + userQuiz.getQuizID() + " not found");
            }
            QMeUserQuizDetail userQuizDetail = new QMeUserQuizDetail();
            userQuizDetail.setUserQuizID(userQuiz.getUserQuizID());
            userQuizDetail.setUserID(userID);
            userQuizDetail.setQuizID(qMeQuizDetail.getQuizID());
            userQuizDetail.setQuizName(qMeQuizDetail.getQuizName());
            userQuizDetail.setQuizDesc(qMeQuizDetail.getQuizDesc());
            userQuizDetail.setCategoryName(qMeQuizDetail.getCategoryName());
            userQuizDetail.setLikes(qMeQuizDetail.getLikes());
            userQuizDetail.setQuizHit(qMeQuizDetail.getQuizHit());
            userQuizDetail.setQuizMaxAttempts(qMeQuizDetail.getQuizMaxAttempts());
            userQuizDetail.setQuizCreateDate(qMeQuizDetail.getQuizCreateDate());
            userQuizDetail.setCreateUserID(qMeQuizDetail.getCreateUserID());
            userQuizDetail.setCreateUserName(qMeQuizDetail.getCreateUserName());
            userQuizDetail.setQuizUpdateDate(qMeQuizDetail.getQuizUpdateDate());
            userQuizDetail.setUpdateUserID(qMeQuizDetail.getUpdateUserID());
            userQuizDetail.setUpdateUserName(qMeQuizDetail.getUpdateUserName());
            userQuizDetail.setCategoryID(qMeQuizDetail.getCategoryID());
            userQuizDetail.setQuizStartDate(LocalDateTime.now());
            userQuizDetail.setQuizEndDate(null);
            userQuizDetail.setUserQuizToken(UUID.randomUUID()+"");
            userQuizDetail.setQuizUserScore(0);
            List<QMeQuizQuestionDetail> qmeQuestionDetailList = new ArrayList<>();
            List<QMeQuestionDetail> qMeQuestionDetails = qMeQuizDetail.getQmeQuestionDetailList();
            Integer quizMaxScore = 0;
            for (QMeQuestionDetail qMeQuestionDetail:qMeQuestionDetails) {
                QMeQuizQuestionDetail qMeQuizQuestionDetail = new QMeQuizQuestionDetail();
                qMeQuizQuestionDetail.setQuestionId(qMeQuestionDetail.getQuestionId());
                qMeQuizQuestionDetail.setCategoryId(qMeQuestionDetail.getCategoryId());
                qMeQuizQuestionDetail.setQuestionText(qMeQuestionDetail.getQuestionText());
                qMeQuizQuestionDetail.setQuestionPoint(qMeQuestionDetail.getQuestionPoint());
                qMeQuizQuestionDetail.setLikes(qMeQuestionDetail.getLikes());
                qMeQuizQuestionDetail.setQuestionCreateDate(qMeQuestionDetail.getQuestionCreateDate());
                qMeQuizQuestionDetail.setCreateUserID(qMeQuestionDetail.getCreateUserID());
                qMeQuizQuestionDetail.setQuestionUpdateDate(qMeQuestionDetail.getQuestionUpdateDate());
                qMeQuizQuestionDetail.setUpdateUserID(qMeQuestionDetail.getUpdateUserID());
                qMeQuizQuestionDetail.setCreateUserName(qMeQuestionDetail.getCreateUserName());
                qMeQuizQuestionDetail.setUpdateUserName(qMeQuestionDetail.getUpdateUserName());
                qMeQuizQuestionDetail.setCategoryName(qMeQuestionDetail.getCategoryName());
                qMeQuizQuestionDetail.setCorrect(Boolean.FALSE);
                List<QMeQuizAnswerOption> answerOptionList = new ArrayList<>();
                List<QMeAnswerOption> qMeAnswerOptionList = qMeQuestionDetail.getAnswerOptionList();
                for (QMeAnswerOption qMeAnswerOption:qMeAnswerOptionList) {
                    QMeQuizAnswerOption qMeQuizAnswerOption = new QMeQuizAnswerOption();
                    qMeQuizAnswerOption.setAnswerOptionID(qMeAnswerOption.getAnswerOptionID());
                    qMeQuizAnswerOption.setQuestionID(qMeAnswerOption.getQuestionID());
                    qMeQuizAnswerOption.setOptionText(qMeAnswerOption.getOptionText());
                    qMeQuizAnswerOption.setSelected(Boolean.FALSE);
                    qMeQuizAnswerOption.setAnswerOptionMediaList(qMeAnswerOption.getAnswerOptionMediaList());
                    answerOptionList.add(qMeQuizAnswerOption);
                }
                qMeQuizQuestionDetail.setAnswerOptionList(answerOptionList);
                qmeQuestionDetailList.add(qMeQuizQuestionDetail);
                quizMaxScore = quizMaxScore + qMeQuestionDetail.getQuestionPoint();
            }
            userQuizDetail.setQmeQuestionDetailList(qmeQuestionDetailList);
            userQuizDetail.setQuizMaxScore(quizMaxScore);

            update(userQuizDetail, userQuiz.getUserQuizID(), userID);

            return userQuizDetail;
        }catch(Exception err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeUserQuizDetail completeQuiz(Long userID, Long userQuizID, QMeUserQuizDetail quizDetail) throws QMeServerException, QMeResourceNotFoundException, QMeInvalidResourceDataException {
        try{
            QMeUserQuizDetail userQuiz = searchById(userQuizID);
            if(userQuiz == null){
                throw new QMeResourceNotFoundException("User Quiz with Quiz ID " + userQuizID + " not found");
            }
            if(quizDetail.getQuizID() == null || quizService.searchById(quizDetail.getQuizID()) == null){
                throw new QMeInvalidResourceDataException("Invalid Quiz ID " + quizDetail.getQuizID() + " not found");
            }
            QMeQuizDetail quiz = quizService.searchById(quizDetail.getQuizID());
            if(userQuiz.getQuizID() != quiz.getQuizID()){
                throw new QMeInvalidResourceDataException("Invalid Quiz " + quizDetail.getQuizName() + " not found");
            }
            if(!userQuiz.getUserQuizToken().equalsIgnoreCase(quizDetail.getUserQuizToken())){
                throw new QMeInvalidResourceDataException("Invalid Quiz Token " + quizDetail.getQuizName() + " not found");
            }
            Integer userScore = 0;

            List<QMeQuestionDetail> questionDetailsList = quiz.getQmeQuestionDetailList();


            List<QMeQuizQuestionDetail> quizQuestionDetailList = quizDetail.getQmeQuestionDetailList();
            for (QMeQuizQuestionDetail qMeQuizQuestionDetail :quizQuestionDetailList) {

                QMeQuestionDetail questionDetail = null;
                for (QMeQuestionDetail qMeQuestionDetail :questionDetailsList) {
                    if(qMeQuizQuestionDetail.getQuestionId() ==  qMeQuestionDetail.getQuestionId()){
                        questionDetail = qMeQuestionDetail;
                        break;
                    }
                }
                if(questionDetail == null){
                    throw new QMeInvalidResourceDataException("Invalid Quiz Submission " + quizDetail.getQuizName() + " not found");
                }

                List<QMeAnswerOption> qMeAnswerOptionList = questionDetail.getAnswerOptionList();

                boolean allCorrect = false;
                List<QMeQuizAnswerOption> answerOptionList = qMeQuizQuestionDetail.getAnswerOptionList();
                for (QMeQuizAnswerOption qMeQuizAnswerOption :answerOptionList) {
                    QMeAnswerOption answerOption = null;
                    for (QMeAnswerOption qMeAnswerOption : qMeAnswerOptionList) {
                        if(qMeAnswerOption.getAnswerOptionID() == qMeQuizAnswerOption.getAnswerOptionID() && qMeQuizAnswerOption.getQuestionID() == questionDetail.getQuestionId()){
                            answerOption = qMeAnswerOption;
                            break;
                        }
                    }
                    if(answerOption == null){
                        throw new QMeInvalidResourceDataException("Invalid Quiz Submission " + quizDetail.getQuizName() + " not found");
                    }
                    if(answerOption.getCorrect() && qMeQuizAnswerOption.getSelected()){
                        qMeQuizAnswerOption.setCorrect(Boolean.TRUE);
                        allCorrect = true;
                    }
                }
                if(allCorrect){
                    qMeQuizQuestionDetail.setCorrect(Boolean.TRUE);
                    userScore = userScore + questionDetail.getQuestionPoint();
                }
            }
            userQuiz.setQuizUserScore(userScore);
            userQuiz.setQuizEndDate(LocalDateTime.now());
            quizDetail.setQuizUserScore(userScore);
            quizDetail.setQuizEndDate(LocalDateTime.now());

            update(userQuiz, userQuiz.getUserQuizID(), userID);


            return quizDetail;
        }catch(Exception err){
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
            userQuiz = new UserQuiz(qMeUserQuiz.getUserQuizID(), userId, qMeUserQuiz.getQuizID(), qMeUserQuiz.getCategoryID(),
                    qMeUserQuiz.getQuizStartDate(), qMeUserQuiz.getQuizEndDate(), qMeUserQuiz.getUserQuizToken(),
                    qMeUserQuiz.getQuizUserScore(), qMeUserQuiz.getQuizMaxScore());
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
