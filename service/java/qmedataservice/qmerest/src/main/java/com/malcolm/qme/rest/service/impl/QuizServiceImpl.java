/**
 * Name      : com.malcolm.qme.rest.service.impl.QuizServiceImpl.java
 * Date      : 6/22/2017
 * Developer : Malcolm
 * Purpose   : QMeQuiz Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.domain.Quiz;
import com.malcolm.qme.core.domain.QuizQuestion;
import com.malcolm.qme.core.repository.*;
import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeQuiz;
import com.malcolm.qme.rest.model.QMeQuizDetail;
import com.malcolm.qme.rest.service.QuestionService;
import com.malcolm.qme.rest.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
     * QMe Quiz Question Repository
     */
    @Autowired
    @Qualifier(value = "QuizQuestionRepository")
    private QuizQuestionRepository quizQuestionRepo;

    /**
     * QMeCategory Repository
     */
    @Autowired
    @Qualifier(value = "CategoryRepository")
    private CategoryRepository categoryRepo;

    /**
     * Question Service
     */
    @Autowired
    private QuestionService questionService;

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

            List<QuizQuestion> quizQuestionList = quizQuestionRepo.findByQuizId(quiz.getQuizID());

            if(quizQuestionList != null && !quizQuestionList.isEmpty()){

                for(QuizQuestion quizQuestion : quizQuestionList){
                    quizDetail.addQmeQuestionDetailList(questionService.searchById(quizQuestion.getQuestionID()));
                }

            }


            return quizDetail;

        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QMeQuizDetail save(QMeQuiz qMeQuiz, Long userId) throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeResourceNotFoundException {
        try{
            QMeQuizDetail quizDetail = (QMeQuizDetail) qMeQuiz;
            quizDetail.setCreateUserID(userId);
            Quiz quiz = getQuiz(quizDetail);
            quiz = quizRepo.save(quiz);

            for (Long questionId : quizDetail.getQuestionIdList()) {
                QuizQuestion quizQuestion = getQuizQuestion(quiz.getQuizID(), questionId);
                quizQuestionRepo.save(quizQuestion);
            }

            quizDetail.setQuizID(quiz.getQuizID());
            return quizDetail;
        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QMeQuizDetail update(QMeQuiz qMeQuiz, Long id, Long userId) throws QMeResourceNotFoundException, QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException {
        try{
            Quiz quiz = quizRepo.findById(id);
            if (quiz == null) {
                throw new QMeResourceNotFoundException("Quiz with Quiz ID " + id + " not found");
            }

            QMeQuizDetail quizDetail = (QMeQuizDetail) qMeQuiz;
            quizDetail.setQuizID(quiz.getQuizID());
            quizDetail.setCategoryID(quiz.getCategoryID());
            quizDetail.setCreateUserID(quiz.getCreateUserID());
            quizDetail.setQuizCreateDate(quiz.getQuizCreateDate());
            quizDetail.setLikes(quiz.getLikes());
            quizDetail.setQuizHit(quiz.getQuizHit());
            quizDetail.setQuizUpdateDate(LocalDateTime.now());
            quizDetail.setUpdateUserID(userId);

            quiz = getQuiz(quizDetail);
            quiz = quizRepo.update(quiz,userId);

            List<QuizQuestion> existingQuizQuestionList = quizQuestionRepo.findByQuizId(quiz.getQuizID());
            List<Long> quizQuestionIdList = new ArrayList<>();
            if(existingQuizQuestionList != null && !existingQuizQuestionList.isEmpty()){
                for(QuizQuestion quizQuestion : existingQuizQuestionList){
                    quizQuestionIdList.add(quizQuestion.getQuizQuestionID());
                }
            }



        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) throws QMeResourceNotFoundException, QMeServerException {
        try {
            Quiz quiz = quizRepo.findById(id);
            if (quiz == null) {
                throw new QMeResourceNotFoundException("Quiz with Quiz ID " + id + " not found");
            }

            List<QuizQuestion> quizQuestionIdList = quizQuestionRepo.findByQuizId(id);
            if(quizQuestionIdList != null && !quizQuestionIdList.isEmpty()){
                for (QuizQuestion quizQuestion:quizQuestionIdList) {
                    quizQuestionRepo.delete(quizQuestion.getQuizQuestionID());
                }
            }

            quizRepo.delete(id);

        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }
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

    /**
     * Map Quiz REST Model to Quiz Domain Object
     * @param quizDetail
     * @return Quiz
     */
    private Quiz getQuiz(QMeQuizDetail quizDetail) throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        if (quizDetail.getQuizName() == null || quizDetail.getQuizName().trim().length() == 0) {
            throw new QMeInvalidResourceDataException("Valid Quiz Name is required");
        }
        if (quizDetail.getQuizDesc() == null || quizDetail.getQuizDesc().trim().length() == 0) {
            throw new QMeInvalidResourceDataException("Valid Quiz Description is required");
        }
        if (quizDetail.getCategoryID() == null || quizDetail.getCategoryID() < 0) {
            throw new QMeInvalidResourceDataException("Valid Category is required");
        }
        if (quizDetail.getQuizMaxAttempts() == null || quizDetail.getQuizMaxAttempts() < 0) {
            throw new QMeInvalidResourceDataException("Valid Quiz Maximum attempt is required");
        }
        Category category = categoryRepo.findById(quizDetail.getCategoryID());
        if (category == null) {
            throw new QMeInvalidResourceDataException("Valid Category is required, Category not found");
        }
        if (quizDetail.getQuestionIdList() == null || quizDetail.getQuestionIdList().isEmpty()) {
            throw new QMeInvalidResourceDataException("Valid Quiz Questions required");
        }
        if (quizDetail.getQuizID() == null || quizDetail.getQuizID() == 0) {
            return new Quiz(quizDetail.getQuizName(), quizDetail.getQuizDesc(), quizDetail.getCategoryID() , quizDetail.getQuizMaxAttempts(), quizDetail.getCreateUserID());
        } else {
            return new Quiz(quizDetail.getQuizID(),quizDetail.getQuizName(), quizDetail.getQuizDesc(), quizDetail.getCategoryID(),quizDetail.getLikes(), quizDetail.getQuizHit(),quizDetail.getQuizMaxAttempts(), quizDetail.getQuizCreateDate(), quizDetail.getCreateUserID(), quizDetail.getQuizUpdateDate(), quizDetail.getUpdateUserID());
        }
    }

    /**
     * Get Quiz Question
     * @param quizID Quiz ID
     * @param questionId Question ID
     * @return QuizQuestion Quiz Question
     */
    private QuizQuestion getQuizQuestion(Long quizID, Long questionId) {
        return new QuizQuestion(quizID,questionId);
    }
}
