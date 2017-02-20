/**
 * Name      : com.malcolm.qme.rest.service.impl.QuestionServiceImplTest.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : Test Cases for Question Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.AnswerOption;
import com.malcolm.qme.core.domain.AnswerOptionMedia;
import com.malcolm.qme.core.domain.AnswerReferenceMedia;
import com.malcolm.qme.core.domain.Question;
import com.malcolm.qme.core.domain.fixtures.*;
import com.malcolm.qme.core.repository.*;
import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.*;
import com.malcolm.qme.rest.service.QuestionService;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepo;

    @Mock
    private CategoryRepository categoryRepo;

    @Mock
    private AnswerOptionRepository answerOptionRepo;

    @Mock
    private AnswerOptionMediaRepository answerOptionMediaRepo;

    @Mock
    private AnswerReferenceMediaRepository answerReferenceMediaRepo;

    @InjectMocks
    private final QuestionService questionService = new QuestionServiceImpl();

    @Test
    public void testCount() throws Exception {
        MatcherAssert.assertThat(questionRepo, notNullValue());
        MatcherAssert.assertThat(questionService, notNullValue());

        when(questionRepo.count()).thenReturn(10L);
        Long questionCount = questionService.count();
        assertNotNull(questionCount);
        assertThat(questionCount, equalTo(10L));
    }

    @Test(expected = QMeServerException.class)
    public void testCountQMeServerException() throws Exception {
        MatcherAssert.assertThat(questionRepo, notNullValue());
        MatcherAssert.assertThat(questionService, notNullValue());
        when(questionRepo.count()).thenThrow(QMeException.class);
        questionService.count();
        verify(questionRepo).count();
    }

    @Test
    public void testList() throws Exception {
        MatcherAssert.assertThat(questionRepo, notNullValue());
        MatcherAssert.assertThat(questionService, notNullValue());

        when(questionRepo.findAll()).thenReturn(QuestionFixtures.simpleQuestionList());

        List<QMeQuestionDetail> questionList = questionService.list();

        verify(questionRepo).findAll();
        assertNotNull(questionList);
        assertThat(questionList.size(),equalTo(5));
        for (QMeQuestionDetail qmeQuestion : questionList) {
            assertThat(qmeQuestion.getQuestionId(), anyOf(
                    is(1L),
                    is(2L),
                    is(3L),
                    is(4L),
                    is(5L))
            );
            assertThat(qmeQuestion.getQuestionText(), anyOf(
                    is("Some question text - 1"),
                    is("Some question text - 2"),
                    is("Some question text - 3"),
                    is("Some question text - 4"),
                    is("Some question text - 5")
            ));

        }

    }

    @Test
    public void testListNullReturn() throws QMeResourceException, QMeException{
        when(questionRepo.findAll()).thenReturn(null);
        List<QMeQuestionDetail> questionList = questionService.list();
        verify(questionRepo).findAll();
        assertNotNull(questionList);
        assertThat(questionList.size(), equalTo(0));
    }


    @Test(expected = QMeServerException.class)
    public void testListQMeException() throws Exception {
        when(questionRepo.findAll()).thenThrow(QMeException.class);
        questionService.list();
    }

    @Test
    public void testListWithPaging() throws Exception {
        when(questionRepo.findAll(Matchers.<PageSort>anyObject())).thenReturn(QuestionFixtures.simpleQuestionList());
        List<QMeQuestionDetail> questionList = questionService.list(0,5,true,"Question Text");
        verify(questionRepo).findAll(Matchers.<PageSort>anyObject());
        assertNotNull(questionList);
        assertThat(questionList.size(),equalTo(5));
        for (QMeQuestionDetail qmeQuestion : questionList) {
            assertThat(qmeQuestion.getQuestionId(), anyOf(
                    is(1L),
                    is(2L),
                    is(3L),
                    is(4L),
                    is(5L))
            );
            assertThat(qmeQuestion.getQuestionText(), anyOf(
                    is("Some question text - 1"),
                    is("Some question text - 2"),
                    is("Some question text - 3"),
                    is("Some question text - 4"),
                    is("Some question text - 5")
            ));
        }
    }

    @Test
    public void testListWithPagingNullReturn() throws QMeResourceException, QMeException{
        when(questionRepo.findAll(Matchers.<PageSort>anyObject())).thenReturn(null);
        List<QMeQuestionDetail> questionList = questionService.list(0,5,true,"Question Text");
        verify(questionRepo).findAll(Matchers.<PageSort>anyObject());
        assertNotNull(questionList);
        assertThat(questionList.size(), equalTo(0));
    }

    @Test(expected = QMeServerException.class)
    public void testListWithPagingQMeException() throws Exception {
        when(questionRepo.findAll(Matchers.<PageSort>anyObject())).thenThrow(QMeException.class);
        questionService.list(0,5,true,"Question Text");
    }

    @Test
    public void testSearchById() throws Exception {
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        QMeQuestionDetail questionDetail = questionService.searchById(1L);
        verify(questionRepo).findById(1L);
        assertNotNull(questionDetail);
        assertThat(questionDetail.getQuestionId(), equalTo(1L));
        assertThat(questionDetail.getQuestionText(), equalTo("Some question text"));
    }

    @Test(expected = QMeResourceException.class)
    public void testSearchByIdResourceException() throws Exception {
        when(questionRepo.findById(1L)).thenReturn(null);
        questionService.searchById(1L);
        verify(questionRepo).findById(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testSearchByIdQMeException() throws Exception {
        when(questionRepo.findById(1L)).thenThrow(QMeException.class);
        questionService.searchById(1L);
        verify(questionRepo).findById(1L);
    }


    @Test
    public void testSave() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setCategoryId(1L);
        qmeQuestion.setQuestionPoint(1);


        //Answer Option
        QMeAnswerOption answerOption = new QMeAnswerOption();
        answerOption.setOptionText("some text");
        answerOption.setCorrect(Boolean.TRUE);

        //Answer Option Media
        QMeAnswerOptionMedia answerOptionMedia = new QMeAnswerOptionMedia();
        answerOptionMedia.setMediaTypeID(1);
        answerOptionMedia.setMedia("http://google.com".getBytes());
        List<QMeAnswerOptionMedia> answerOptionMediaList =new ArrayList<>();
        answerOptionMediaList.add(answerOptionMedia);
        answerOption.setAnswerOptionMediaList(answerOptionMediaList);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        QMeAnswerReferenceMedia answerReferenceMedia = new QMeAnswerReferenceMedia();
        answerReferenceMedia.setMediaTypeID(1);
        answerReferenceMedia.setMedia("http://google.com".getBytes());
        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(answerReferenceMedia);
        qmeQuestion.setAnswerReferenceMediaList(answerReferenceMediaList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.<Question>anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.<AnswerOption>anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L,1L,answerOption.getOptionText(),answerOption.getCorrect()));
        when(answerOptionMediaRepo.save(Matchers.<AnswerOptionMedia>anyObject())).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMedia(1L, 1L,answerOptionMedia.getMediaTypeID(), answerOptionMedia.getMedia()));
        when(answerReferenceMediaRepo.save(Matchers.<AnswerReferenceMedia>anyObject())).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMedia(1L, 1L,answerOptionMedia.getMediaTypeID(), answerOptionMedia.getMedia()));

        QMeQuestionDetail questionDetail = questionService.save(qmeQuestion,1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.<Question>anyObject());
        verify(answerOptionRepo).save(Matchers.<AnswerOption>anyObject());
        verify(answerOptionMediaRepo).save(Matchers.<AnswerOptionMedia>anyObject());
        verify(answerReferenceMediaRepo).save(Matchers.<AnswerReferenceMedia>anyObject());

        assertNotNull(questionDetail);
        assertThat(questionDetail.getQuestionId(), equalTo(1L));
        assertThat(questionDetail.getQuestionText(), equalTo("Some question text"));

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        questionDetail = questionService.searchById(1L);
        verify(questionRepo).findById(1L);
        assertNotNull(questionDetail);
        assertThat(questionDetail.getQuestionId(), equalTo(1L));
        assertThat(questionDetail.getQuestionText(), equalTo("Some question text"));
    }

    @Test(expected = QMeServerException.class)
    public void testSaveQMeServerException() throws Exception {
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setCategoryId(1L);
        qmeQuestion.setQuestionPoint(1);
        QMeAnswerOption answerOption = new QMeAnswerOption();
        answerOption.setOptionText("some text");
        answerOption.setCorrect(Boolean.TRUE);
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.<Question>anyObject())).thenThrow(QMeException.class);
        QMeQuestionDetail questionDetail = questionService.save(qmeQuestion,1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.<Question>anyObject());
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidQuestionText() throws Exception {
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setCategoryId(1L);
        qmeQuestion.setQuestionPoint(1);
        questionService.save(qmeQuestion,1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidAnswerText() throws Exception {
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setCategoryId(1L);
        qmeQuestion.setQuestionPoint(1);
        questionService.save(qmeQuestion,1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidCategory() throws Exception {
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setQuestionPoint(1);
        questionService.save(qmeQuestion,1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidCategoryNotFound() throws Exception {
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setQuestionPoint(1);
        qmeQuestion.setCategoryId(1L);
        when(categoryRepo.findById(1L)).thenReturn(null);
        questionService.save(qmeQuestion,1L);
        verify(categoryRepo).findById(1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidQuestionPoint() throws Exception {
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setCategoryId(1L);
        questionService.save(qmeQuestion,1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidCreateUserId() throws Exception {
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setQuestionPoint(1);
        qmeQuestion.setCategoryId(1L);
        qmeQuestion.setCreateUserID(null);
        questionService.save(qmeQuestion,1L);
    }

    @Test
    public void testUpdate() throws Exception {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(questionRepo.update(Matchers.<Question>anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());

        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setQuestionPoint(1);
        qmeQuestion.setQuestionId(1L);
        qmeQuestion.setCategoryId(1L);
        QMeAnswerOption answerOption = new QMeAnswerOption();
        answerOption.setOptionText("some text");
        answerOption.setCorrect(Boolean.TRUE);
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        QMeQuestionDetail questionDetail = questionService.update(qmeQuestion,1L,1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(questionRepo).update(Matchers.<Question>anyObject(),Matchers.<Long>anyObject());
        assertNotNull(questionDetail);
        assertThat(questionDetail.getQuestionId(), equalTo(1L));
        assertThat(questionDetail.getQuestionText(), equalTo("Some question text"));
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testUpdateQMeResourceNotFoundException() throws Exception {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.findById(1L)).thenReturn(null);
        QMeQuestion qmeQuestion = new QMeQuestion();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setQuestionPoint(1);
        qmeQuestion.setQuestionId(1L);
        qmeQuestion.setCategoryId(1L);
        QMeQuestionDetail questionDetail = questionService.update(qmeQuestion,1L,1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testUpdateQMeServerException() throws Exception {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(questionRepo.update(Matchers.<Question>anyObject(), eq(1L))).thenThrow(QMeException.class);
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setQuestionPoint(1);
        qmeQuestion.setQuestionId(1L);
        qmeQuestion.setCategoryId(1L);
        QMeAnswerOption answerOption = new QMeAnswerOption();
        answerOption.setOptionText("some text");
        answerOption.setCorrect(Boolean.TRUE);
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);
        questionService.update(qmeQuestion,1L,1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(questionRepo).update(Matchers.<Question>anyObject(),Matchers.<Long>anyObject());
    }

    @Test
    public void testDelete() throws Exception {
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        doNothing().when(questionRepo).delete(1L);
        questionService.delete(1L);
        verify(questionRepo).findById(1L);
        verify(questionRepo).delete(1L);
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testDeleteQMeResourceNotFoundException() throws Exception {
        when(questionRepo.findById(1L)).thenReturn(null);
        questionService.delete(1L);
        verify(questionRepo).findById(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testDeleteQMeServerException() throws Exception {
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        doThrow(QMeException.class).when(questionRepo).delete(1L);
        questionService.delete(1L);
        verify(questionRepo).findById(1L);
        verify(questionRepo).delete(1L);
    }
}