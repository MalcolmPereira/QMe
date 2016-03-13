/**
 * Name      : com.malcolm.qme.rest.service.impl.QuestionServiceImpl.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : QMeQuestion Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.Question;
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
        return null;
    }

    @Override
    public QMeQuestionDetail searchById(Long id) throws QMeResourceNotFoundException, QMeServerException {
        return null;
    }

    @Override
    public QMeQuestionDetail save(QMeQuestion qMeQuestion, Long userId) throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeResourceNotFoundException {
        return null;
    }

    @Override
    public QMeQuestionDetail update(QMeQuestion qMeQuestion, Long id, Long userId) throws QMeResourceNotFoundException, QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException {
        return null;
    }

    @Override
    public void delete(Long id) throws QMeResourceNotFoundException, QMeServerException {

    }

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
        qmeQuestionDetail.setQuestionId(qmeQuestionDetail.getQuestionId());
        qmeQuestionDetail.setCategoryId(qmeQuestionDetail.getCategoryId());
        qmeQuestionDetail.setQuestionText(qmeQuestionDetail.getQuestionText());
        qmeQuestionDetail.setQuestionPoint(qmeQuestionDetail.getQuestionPoint());
        qmeQuestionDetail.setLikes(qmeQuestionDetail.getLikes());
        qmeQuestionDetail.setQuestionCreateDate(qmeQuestionDetail.getQuestionCreateDate());
        qmeQuestionDetail.setCreateUserID(qmeQuestionDetail.getCreateUserID());
        qmeQuestionDetail.setQuestionUpdateDate(qmeQuestionDetail.getQuestionUpdateDate());
        qmeQuestionDetail.setUpdateUserID(qmeQuestionDetail.getUpdateUserID());
        return qmeQuestionDetail;
    }
}
