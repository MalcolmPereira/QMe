/**
 * Name      : com.malcolm.qme.rest.service.impl.QuestionServiceImpl.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : QMeQuestion Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.domain.Question;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeQuestion;
import com.malcolm.qme.rest.model.QMeQuestionDetail;
import com.malcolm.qme.rest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Service
public class QuestionServiceImpl implements QuestionService{
    /**
     * QMeQuestion Repository
     */
    @Autowired
    @Qualifier(value = "QuestionRepository")
    private QuestionRepository questionRepo;

    /**
     * QMeCategory Repository
     */
    @Autowired
    @Qualifier(value = "CategoryRepository")
    private CategoryRepository categoryRepo;


    @Override
    public Long count() throws QMeServerException {
        try{
            return questionRepo.count();

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeQuestionDetail> list() throws QMeServerException {
        try{
            return getQMeQuestionDetail(questionRepo.findAll());

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeQuestionDetail> list(Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException {
        try{
            return  getQMeQuestionDetail(questionRepo.findAll(new PageSort(pageIndex,maxRows,sortAscending,sortFields)));

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeQuestionDetail searchById(Long id) throws QMeResourceNotFoundException, QMeServerException {
        try{
            Question question  =  questionRepo.findById(id);
            if(question == null){
                throw new QMeResourceNotFoundException("Question with Question ID "+id+" not found");
            }
            return  getQMeQuestionDetail(question);

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeQuestionDetail save(QMeQuestion qMeQuestion, Long userId) throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeResourceNotFoundException {
        try {
            Question question = getQuestion(qMeQuestion);
            question = questionRepo.save(question);
            return  getQMeQuestionDetail(question);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeQuestionDetail update(QMeQuestion qMeQuestion, Long id, Long userId) throws QMeResourceNotFoundException, QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException {
        return null;
    }

    @Override
    public void delete(Long id) throws QMeResourceNotFoundException, QMeServerException {

    }

    /**
     * Map REST Model to Question Domain Object
     * @param qMeQuestion
     * @return Question Domain Model
     * @throws QMeInvalidResourceDataException
     * @throws QMeResourceConflictException
     * @throws QMeServerException
     * @throws QMeException
     */
    private Question getQuestion(QMeQuestion qMeQuestion) throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        if(qMeQuestion.getQuestionText() == null || qMeQuestion.getQuestionText().trim().length() == 0){
            throw new QMeInvalidResourceDataException("Valid Question Text is required");
        }
        if(qMeQuestion.getAnswer() == null || qMeQuestion.getAnswer().trim().length() == 0){
            throw new QMeInvalidResourceDataException("Valid Answer Text is required");
        }
        if(qMeQuestion.getCategoryId() == null || qMeQuestion.getCategoryId() < 0){
            throw new QMeInvalidResourceDataException("Valid Category is required");
        }
        if(qMeQuestion.getQuestionPoint() == null || qMeQuestion.getQuestionPoint() < 0){
            throw new QMeInvalidResourceDataException("Valid Question Point is required");
        }
        Category category = categoryRepo.findById(qMeQuestion.getCategoryId());
        if(category == null){
            throw new QMeInvalidResourceDataException("Valid Category is required, Category not found");
        }
        Question question = new Question(qMeQuestion.getCategoryId(), qMeQuestion.getQuestionText(),qMeQuestion.getAnswer(), qMeQuestion.getQuestionPoint(), qMeQuestion.getCreateUserID());
        return question;
    }

    /**
     * Map Question Domain Object List to REST Model
     * @param questionList Question List
     * @return QMeQuestionDetail List
     */
    private List<QMeQuestionDetail> getQMeQuestionDetail(List<Question> questionList){
        List<QMeQuestionDetail> qmeQuestionDetails = new ArrayList<>();
        if(questionList == null){
            return qmeQuestionDetails;
        }
        qmeQuestionDetails.addAll(
                questionList.stream().map(this::getQMeQuestionDetail).collect(Collectors.toList())
        );
        return qmeQuestionDetails;
    }

    /**
     * Map Question Domain Object to REST Model
     * @param question
     * @return QMeQuestionDetail
     */
    private QMeQuestionDetail getQMeQuestionDetail(Question question){
        QMeQuestionDetail qmeQuestionDetail = new QMeQuestionDetail();
        qmeQuestionDetail.setQuestionId(question.getQuestionID());
        qmeQuestionDetail.setCategoryId(question.getCategoryID());
        qmeQuestionDetail.setQuestionText(question.getQuestionText());
        qmeQuestionDetail.setQuestionPoint(question.getQuestionPoint());
        qmeQuestionDetail.setLikes(question.getLikes());
        qmeQuestionDetail.setQuestionCreateDate(question.getQuestionCreateDate());
        qmeQuestionDetail.setCreateUserID(question.getCreateUserID());
        qmeQuestionDetail.setQuestionUpdateDate(question.getQuestionUpdateDate());
        qmeQuestionDetail.setUpdateUserID(question.getUpdateUserID());
        return qmeQuestionDetail;
    }
}
