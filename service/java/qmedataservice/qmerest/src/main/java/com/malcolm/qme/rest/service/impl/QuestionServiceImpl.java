/**
 * Name      : com.malcolm.qme.rest.service.impl.QuestionServiceImpl.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : QMeQuestion Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.*;
import com.malcolm.qme.core.repository.*;
import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.*;
import com.malcolm.qme.rest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    /**
     * QMeQuestion Repository
     */
    @Autowired
    @Qualifier(value = "QuestionRepository")
    private QuestionRepository questionRepo;

    /**
     * Answer Option Repository
     */
    @Autowired
    @Qualifier("AnswerOptionRepository")
    private AnswerOptionRepository answerOptionRepository;

    /**
     * Answer OptionMedia  Repository
     */
    @Autowired
    @Qualifier("AnswerOptionMediaRepository")
    private AnswerOptionMediaRepository answerOptionMediaRepository;

    /**
     * Answer Reference Media Repository
     */
    @Autowired
    @Qualifier("AnswerReferenceMediaRepository")
    private AnswerReferenceMediaRepository answerReferenceMediaRepository;


    /**
     * QMeCategory Repository
     */
    @Autowired
    @Qualifier(value = "CategoryRepository")
    private CategoryRepository categoryRepo;


    @Override
    public Long count() throws QMeServerException {
        try {
            return questionRepo.count();

        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }
    }

    @Override
    public List<QMeQuestionDetail> list() throws QMeServerException {
        try {
            return getQMeQuestionDetail(questionRepo.findAll());

        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }
    }

    @Override
    public List<QMeQuestionDetail> list(Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException {
        try {
            return getQMeQuestionDetail(questionRepo.findAll(new PageSort(pageIndex, maxRows, sortAscending, sortFields)));

        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }
    }

    @Override
    public QMeQuestionDetail searchById(Long id) throws QMeResourceNotFoundException, QMeServerException {
        try {
            Question question = questionRepo.findById(id);
            if (question == null) {
                throw new QMeResourceNotFoundException("Question with Question ID " + id + " not found");
            }
            return getQMeQuestionDetail(question);

        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }
    }

    @Override
    @Transactional
    public QMeQuestionDetail save(QMeQuestion qMeQuestion, Long userId) throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeResourceNotFoundException {
        try {
            QMeQuestionDetail qMeQuestionDetail = (QMeQuestionDetail) qMeQuestion;

            qMeQuestion.setCreateUserID(userId);
            Question question = getQuestion(qMeQuestionDetail);
            question = questionRepo.save(question);

            //Check Answer Option List
            if (qMeQuestionDetail.getAnswerOptionList() != null && qMeQuestionDetail.getAnswerOptionList().size() > 0) {
                List<QMeAnswerOption> answerOptionList = qMeQuestionDetail.getAnswerOptionList();
                for (QMeAnswerOption answerOption : answerOptionList) {
                    saveAnswerOption(question.getQuestionID(), answerOption, userId);
                }
            }

            //Check Answer Option Reference Media List
            if (qMeQuestionDetail.getAnswerReferenceMediaList() != null && qMeQuestionDetail.getAnswerReferenceMediaList().size() > 0) {
                List<QMeAnswerReferenceMedia> answerReferenceMediaList = qMeQuestionDetail.getAnswerReferenceMediaList();
                for (QMeAnswerReferenceMedia qMeAnswerReferenceMedia : answerReferenceMediaList) {
                    saveAnswerReferenceMedia(question.getQuestionID(), qMeAnswerReferenceMedia, userId);
                }
            }

            return getQMeQuestionDetail(question);

        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }
    }

    @Override
    @Transactional
    public QMeQuestionDetail update(QMeQuestion qMeQuestion, Long id, Long userId) throws QMeResourceNotFoundException, QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException {
        try {
            QMeQuestionDetail qMeQuestionDetail = (QMeQuestionDetail) qMeQuestion;

            Question question = questionRepo.findById(id);
            if (question == null) {
                throw new QMeResourceNotFoundException("Question with Question ID " + id + " not found");
            }

            qMeQuestionDetail.setQuestionId(question.getQuestionID());
            qMeQuestionDetail.setCategoryId(question.getCategoryID());
            qMeQuestionDetail.setCreateUserID(question.getCreateUserID());
            qMeQuestion.setUpdateUserID(userId);

            question = getQuestion(qMeQuestionDetail);
            question = questionRepo.update(question, userId);

            List<AnswerOption> existingAnswerOptions = getAnswerOptions(question.getQuestionID());
            List<Long> answerOptionIdList = new ArrayList<>();
            for (AnswerOption answerOption : existingAnswerOptions) {
                answerOptionIdList.add(answerOption.getAnswerOptionID());
            }

            if (qMeQuestionDetail.getAnswerOptionList() != null && qMeQuestionDetail.getAnswerOptionList().size() > 0) {
                List<QMeAnswerOption> answerOptionList = qMeQuestionDetail.getAnswerOptionList();
                for (QMeAnswerOption answerOption : answerOptionList) {
                    if (answerOption.getAnswerOptionID() != null && answerOptionIdList.contains(answerOption.getAnswerOptionID())) {
                        saveAnswerOption(question.getQuestionID(), answerOption, userId);
                        answerOptionIdList.remove(answerOption.getAnswerOptionID());
                    } else {
                        saveAnswerOption(question.getQuestionID(), answerOption, userId);
                    }
                }
            } else {
                answerOptionIdList.clear();
            }

            if (!answerOptionIdList.isEmpty()) {
                for (Long answerOptionId : answerOptionIdList) {
                    removeAnswerOption(answerOptionId);
                }
            }

            List<AnswerReferenceMedia>  existingAnswerReferenceMediaList  = getAnswerReferenceMedias(question.getQuestionID());
            List<Long> answerReferenceMediaIdList = new ArrayList<>();
            for (AnswerReferenceMedia answerReferenceMedia:existingAnswerReferenceMediaList) {
                answerReferenceMediaIdList.add(answerReferenceMedia.getAnswerRefMediaID());
            }

            if (qMeQuestionDetail.getAnswerReferenceMediaList() != null && qMeQuestionDetail.getAnswerReferenceMediaList().size() > 0) {
                List<QMeAnswerReferenceMedia> answerReferenceMediaList = qMeQuestionDetail.getAnswerReferenceMediaList();
                for (QMeAnswerReferenceMedia qMeAnswerReferenceMedia : answerReferenceMediaList) {
                    if(qMeAnswerReferenceMedia.getAnswerRefMediaID() != null && answerReferenceMediaIdList.contains(qMeAnswerReferenceMedia.getAnswerRefMediaID())){
                        saveAnswerReferenceMedia(question.getQuestionID(), qMeAnswerReferenceMedia, userId);
                        answerReferenceMediaIdList.remove(qMeAnswerReferenceMedia.getAnswerRefMediaID());
                    }else{
                        saveAnswerReferenceMedia(question.getQuestionID(), qMeAnswerReferenceMedia, userId);
                    }
                }
            }else{
                answerReferenceMediaIdList.clear();
            }

            if (!answerReferenceMediaIdList.isEmpty()) {
                for (Long answerReferenceMediaId : answerReferenceMediaIdList) {
                    removeAnswerReferenceMedia(answerReferenceMediaId);
                }
            }

            return getQMeQuestionDetail(question);

        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws QMeResourceNotFoundException, QMeServerException {
        try {
            Question question = questionRepo.findById(id);
            if (question == null) {
                throw new QMeResourceNotFoundException("Question with Question ID " + id + " not found");
            }

            List<AnswerOption> answerOptionList = getAnswerOptions(question.getQuestionID());
            for (AnswerOption answerOption : answerOptionList) {
                removeAnswerOption(answerOption.getAnswerOptionID());
            }

            List<AnswerReferenceMedia> answerReferenceMediaList = getAnswerReferenceMedias(question.getQuestionID());
            for (AnswerReferenceMedia answerReferenceMedia : answerReferenceMediaList) {
                answerReferenceMediaRepository.delete(answerReferenceMedia.getAnswerRefMediaID());
            }

            questionRepo.delete(id);
        } catch (QMeException err) {
            throw new QMeServerException(err.getMessage(), err);
        }
    }

    /**
     * Save Answer Option
     *
     * @param questionId Question ID
     * @throws QMeException
     */
    private List<AnswerOption> getAnswerOptions(Long questionId) throws QMeException {
        return answerOptionRepository.findByQuestionId(questionId);
    }

    /**
     * Save Answer Option
     *
     * @param questionId      Question ID
     * @param qmeAnswerOption QMe Answer Option
     * @param userID          User Id
     * @throws QMeException
     */
    private void saveAnswerOption(Long questionId, QMeAnswerOption qmeAnswerOption, Long userID) throws QMeException, QMeInvalidResourceDataException {
        AnswerOption answerOption = getAnswerOption(questionId, qmeAnswerOption);

        List<Long> answerOptionMediaIdList = new ArrayList<>();
        if(answerOption.getAnswerOptionID() != null && answerOption.getAnswerOptionID() > 0){
            List<AnswerOptionMedia> answerOptionMediaList = getAnswerOptionMedias(answerOption.getAnswerOptionID());
            for (AnswerOptionMedia answerOptionMedia:answerOptionMediaList) {
                answerOptionMediaIdList.add(answerOptionMedia.getAnswerOptionMediaID());
            }
        }

        answerOption = answerOptionRepository.save(answerOption);

        if (qmeAnswerOption.getAnswerOptionMediaList() != null && qmeAnswerOption.getAnswerOptionMediaList().size() > 0) {

            List<QMeAnswerOptionMedia> qmeAnswerOptionMediaList = qmeAnswerOption.getAnswerOptionMediaList();

            for (QMeAnswerOptionMedia answerOptionMedia : qmeAnswerOptionMediaList) {
                if(answerOptionMedia.getAnswerOptionMediaID() != null && answerOptionMediaIdList.contains(answerOptionMedia.getAnswerOptionMediaID())){
                    saveAnswerOptionMedia(answerOption.getAnswerOptionID(), answerOptionMedia, userID);
                    answerOptionMediaIdList.remove(answerOptionMedia.getAnswerOptionMediaID());
                }else{
                    saveAnswerOptionMedia(answerOption.getAnswerOptionID(), answerOptionMedia, userID);
                }

            }
        }else{
            answerOptionMediaIdList.clear();
        }

        if (!answerOptionMediaIdList.isEmpty()) {
            for (Long answerOptionMediaId : answerOptionMediaIdList) {
                removeAnswerOptionMedia(answerOptionMediaId);
            }
        }
    }

    /**
     * Remove Answer Option
     *
     * @param answerOptionId Answer Option Id
     * @throws QMeException
     */
    private void removeAnswerOption(Long answerOptionId) throws QMeException {
        List<AnswerOptionMedia> answerOptionMediaList = getAnswerOptionMedias(answerOptionId);
        for (AnswerOptionMedia answerOptionMedia : answerOptionMediaList) {
            answerOptionMediaRepository.delete(answerOptionMedia.getAnswerOptionMediaID());
        }
        answerOptionRepository.delete(answerOptionId);
    }

    /**
     * Get Answer Option Media List
     * @param answerOptionId Answer Option Id
     * @return AnserOption Media List
     * @throws QMeException
     */
    private List<AnswerOptionMedia> getAnswerOptionMedias(Long answerOptionId) throws QMeException {
        return answerOptionMediaRepository.findByAnswerOptionId(answerOptionId);
    }

    /**
     * Save Answer Option Media
     *
     * @param answerOptionID       Answer Option Id
     * @param qmeAnswerOptionMedia Answer Option Media
     * @param userID               User Id
     * @throws QMeException
     * @throws QMeInvalidResourceDataException
     */
    private void saveAnswerOptionMedia(Long answerOptionID, QMeAnswerOptionMedia qmeAnswerOptionMedia, Long userID) throws QMeException, QMeInvalidResourceDataException {
        AnswerOptionMedia answerOptionMedia = getAnswerOptionMedia(answerOptionID, qmeAnswerOptionMedia);
        if (answerOptionMedia.getAnswerOptionMediaID() != null && answerOptionMedia.getAnswerOptionMediaID() > 0) {
            answerOptionMediaRepository.update(answerOptionMedia, userID);
        } else {
            answerOptionMediaRepository.save(answerOptionMedia);
        }
    }

    /**
     * Remove Answer Option Media
     * @param answerOptionMediaId Answer Option Media Id
     * @throws QMeException
     */
    private void removeAnswerOptionMedia(Long answerOptionMediaId) throws QMeException {
        answerOptionMediaRepository.delete(answerOptionMediaId);
    }


    /**
     * Get Answer Reference Medias
     *
     * @param questionId Question Id
     * @return Answer Reference Medai List
     * @throws QMeException
     */
    private List<AnswerReferenceMedia> getAnswerReferenceMedias(Long questionId) throws QMeException {
        return answerReferenceMediaRepository.findByQuestionId(questionId);
    }

    /**
     * Save Answer Reference Media
     *
     * @param questionId              Question ID
     * @param qMeAnswerReferenceMedia Answer Reference Media
     * @param userId                  Update User Id
     * @throws QMeException
     * @throws QMeInvalidResourceDataException
     */
    private void saveAnswerReferenceMedia(Long questionId, QMeAnswerReferenceMedia qMeAnswerReferenceMedia, Long userId) throws QMeException, QMeInvalidResourceDataException {
        AnswerReferenceMedia answerReferenceMedia = getAnswerReferenceMedia(questionId, qMeAnswerReferenceMedia);
        if (answerReferenceMedia.getAnswerRefMediaID() != null && answerReferenceMedia.getAnswerRefMediaID() > 0) {
            answerReferenceMediaRepository.update(answerReferenceMedia, userId);
        } else {
            answerReferenceMediaRepository.save(answerReferenceMedia);
        }
    }

    /**
     * Remove Answer Reference Media
     * @param answerReferenceMediaID  Answer Reference Media Id
     * @throws QMeException
     */
    private void removeAnswerReferenceMedia(Long answerReferenceMediaID) throws QMeException {
        answerReferenceMediaRepository.delete(answerReferenceMediaID);
    }

    /**
     * Get Answer Option
     *
     * @param questionId      Question ID
     * @param qmeAnswerOption QMe Answer Option
     * @return AnswerOption
     * @throws QMeInvalidResourceDataException
     */
    private AnswerOption getAnswerOption(Long questionId, QMeAnswerOption qmeAnswerOption) throws QMeInvalidResourceDataException {
        if (qmeAnswerOption.getOptionText() == null || qmeAnswerOption.getOptionText().trim().length() == 0) {
            throw new QMeInvalidResourceDataException("Valid Answer Option Text is required");
        }
        if (qmeAnswerOption.getCorrect() == null) {
            throw new QMeInvalidResourceDataException("Valid Answer Option Correct/InCorrect Value is required");
        }
        if (qmeAnswerOption.getAnswerOptionID() != null) {
            return new AnswerOption(qmeAnswerOption.getAnswerOptionID(), questionId, qmeAnswerOption.getOptionText(), qmeAnswerOption.getCorrect());
        } else {
            return new AnswerOption(questionId, qmeAnswerOption.getOptionText(), qmeAnswerOption.getCorrect());
        }
    }

    /**
     * Get Answer Option Media
     *
     * @param answerOptionID       Answer Option Id
     * @param qmeAnswerOptionMedia Answer Option MEdia
     * @return AnswerOptionMedia
     * @throws QMeInvalidResourceDataException
     */
    private AnswerOptionMedia getAnswerOptionMedia(Long answerOptionID, QMeAnswerOptionMedia qmeAnswerOptionMedia) throws QMeInvalidResourceDataException {
        if (qmeAnswerOptionMedia.getMediaTypeID() == null || qmeAnswerOptionMedia.getMediaTypeID() == 0) {
            throw new QMeInvalidResourceDataException("Valid Answer Option Media Type Id  is required");
        }
        if (MediaTypeEnum.fromValue(qmeAnswerOptionMedia.getMediaTypeID()) == null) {
            throw new QMeInvalidResourceDataException("Valid Answer Option Media Type Id  is required - " + MediaTypeEnum.supportedMediaTypes());
        }
        if (qmeAnswerOptionMedia.getMedia() == null) {
            throw new QMeInvalidResourceDataException("Valid Answer Option Media is required");
        }
        if (qmeAnswerOptionMedia.getAnswerOptionMediaID() != null) {
            return new AnswerOptionMedia(qmeAnswerOptionMedia.getAnswerOptionMediaID(), answerOptionID, qmeAnswerOptionMedia.getMediaTypeID(), qmeAnswerOptionMedia.getMedia());
        } else {
            return new AnswerOptionMedia(answerOptionID, qmeAnswerOptionMedia.getMediaTypeID(), qmeAnswerOptionMedia.getMedia());
        }
    }

    /**
     * Get Answer Reference Media
     *
     * @param questionId              Question ID
     * @param qMeAnswerReferenceMedia Answer Reference Media
     * @return AnswerReferenceMedia
     * @throws QMeInvalidResourceDataException
     */
    private AnswerReferenceMedia getAnswerReferenceMedia(Long questionId, QMeAnswerReferenceMedia qMeAnswerReferenceMedia) throws QMeInvalidResourceDataException {
        if (qMeAnswerReferenceMedia.getMediaTypeID() == null || qMeAnswerReferenceMedia.getMediaTypeID() == 0) {
            throw new QMeInvalidResourceDataException("Valid Answer Reference Media Type Id is required");
        }
        if (MediaTypeEnum.fromValue(qMeAnswerReferenceMedia.getMediaTypeID()) == null) {
            throw new QMeInvalidResourceDataException("Valid Answer Option Media Type Id  is required - " + MediaTypeEnum.supportedMediaTypes());
        }
        if (qMeAnswerReferenceMedia.getMedia() == null) {
            throw new QMeInvalidResourceDataException("Valid Answer Option Media is required");
        }
        if (qMeAnswerReferenceMedia.getAnswerRefMediaID() != null) {
            return new AnswerReferenceMedia(qMeAnswerReferenceMedia.getAnswerRefMediaID(), questionId, qMeAnswerReferenceMedia.getMediaTypeID(), qMeAnswerReferenceMedia.getMedia());
        } else {
            return new AnswerReferenceMedia(questionId, qMeAnswerReferenceMedia.getMediaTypeID(), qMeAnswerReferenceMedia.getMedia());
        }
    }

    /**
     * Map REST Model to Question Domain Object
     *
     * @param qMeQuestion
     * @return Question Domain Model
     * @throws QMeInvalidResourceDataException
     * @throws QMeResourceConflictException
     * @throws QMeServerException
     * @throws QMeException
     */
    private Question getQuestion(QMeQuestionDetail qMeQuestion) throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        if (qMeQuestion.getQuestionText() == null || qMeQuestion.getQuestionText().trim().length() == 0) {
            throw new QMeInvalidResourceDataException("Valid Question Text is required");
        }
        if (qMeQuestion.getAnswer() == null || qMeQuestion.getAnswer().trim().length() == 0) {
            throw new QMeInvalidResourceDataException("Valid Answer Text is required");
        }
        if (qMeQuestion.getCategoryId() == null || qMeQuestion.getCategoryId() < 0) {
            throw new QMeInvalidResourceDataException("Valid Category is required");
        }
        if (qMeQuestion.getQuestionPoint() == null || qMeQuestion.getQuestionPoint() < 0) {
            throw new QMeInvalidResourceDataException("Valid Question Point is required");
        }
        if (qMeQuestion.getAnswerOptionList() == null || qMeQuestion.getAnswerOptionList().isEmpty()) {
            throw new QMeInvalidResourceDataException("Valid Question Option List is required");
        }
        Category category = categoryRepo.findById(qMeQuestion.getCategoryId());
        if (category == null) {
            throw new QMeInvalidResourceDataException("Valid Category is required, Category not found");
        }
        Question question = new Question(qMeQuestion.getCategoryId(), qMeQuestion.getQuestionText(), qMeQuestion.getAnswer(), qMeQuestion.getQuestionPoint(), qMeQuestion.getCreateUserID());
        return question;
    }

    /**
     * Map Question Domain Object List to REST Model
     *
     * @param questionList Question List
     * @return QMeQuestionDetail List
     */
    private List<QMeQuestionDetail> getQMeQuestionDetail(List<Question> questionList) {
        List<QMeQuestionDetail> qmeQuestionDetails = new ArrayList<>();
        if (questionList == null) {
            return qmeQuestionDetails;
        }
        qmeQuestionDetails.addAll(
                questionList.stream().map(this::getQMeQuestionDetail).collect(Collectors.toList())
        );
        return qmeQuestionDetails;
    }

    /**
     * Map Question Domain Object to REST Model
     *
     * @param question
     * @return QMeQuestionDetail
     */
    private QMeQuestionDetail getQMeQuestionDetail(Question question) {
        QMeQuestionDetail qmeQuestionDetail = new QMeQuestionDetail();
        qmeQuestionDetail.setQuestionId(question.getQuestionID());
        qmeQuestionDetail.setCategoryId(question.getCategoryID());
        qmeQuestionDetail.setQuestionText(question.getQuestionText());
        qmeQuestionDetail.setAnswer(question.getAnswer());
        qmeQuestionDetail.setQuestionPoint(question.getQuestionPoint());
        qmeQuestionDetail.setLikes(question.getLikes());
        qmeQuestionDetail.setQuestionCreateDate(question.getQuestionCreateDate());
        qmeQuestionDetail.setCreateUserID(question.getCreateUserID());
        qmeQuestionDetail.setQuestionUpdateDate(question.getQuestionUpdateDate());
        qmeQuestionDetail.setUpdateUserID(question.getUpdateUserID());
        if (question.getCreateUser() != null) {
            qmeQuestionDetail.setCreateUserName(question.getCreateUser().getUserName());
        }
        if (question.getUpdateUser() != null) {
            qmeQuestionDetail.setUdateUserName(question.getUpdateUser().getUserName());
        }
        if (question.getCategory() != null) {
            qmeQuestionDetail.setCategoryName(question.getCategory().getCategoryName());
        }
        return qmeQuestionDetail;
    }
}
