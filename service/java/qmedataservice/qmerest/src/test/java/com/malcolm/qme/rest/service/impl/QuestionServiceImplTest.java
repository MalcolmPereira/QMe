/**
 * Name      : com.malcolm.qme.rest.service.impl.QuestionServiceImplTest.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : Test Cases for Question Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.MediaTypeEnum;
import com.malcolm.qme.core.domain.fixtures.*;
import com.malcolm.qme.core.repository.*;
import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeAnswerOption;
import com.malcolm.qme.rest.model.QMeAnswerOptionMedia;
import com.malcolm.qme.rest.model.QMeAnswerReferenceMedia;
import com.malcolm.qme.rest.model.QMeQuestionDetail;
import com.malcolm.qme.rest.service.QuestionService;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        assertThat(questionList.size(), equalTo(5));
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
    public void testListNullReturn() throws QMeResourceException, QMeException {
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
        when(questionRepo.findAll(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestionList());
        List<QMeQuestionDetail> questionList = questionService.list(0, 5, true, "Question Text");
        verify(questionRepo).findAll(Matchers.anyObject());
        assertNotNull(questionList);
        assertThat(questionList.size(), equalTo(5));
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
    public void testListWithPagingNullReturn() throws QMeResourceException, QMeException {
        when(questionRepo.findAll(Matchers.anyObject())).thenReturn(null);
        List<QMeQuestionDetail> questionList = questionService.list(0, 5, true, "Question Text");
        verify(questionRepo).findAll(Matchers.anyObject());
        assertNotNull(questionList);
        assertThat(questionList.size(), equalTo(0));
    }

    @Test(expected = QMeServerException.class)
    public void testListWithPagingQMeException() throws Exception {
        when(questionRepo.findAll(Matchers.anyObject())).thenThrow(QMeException.class);
        questionService.list(0, 5, true, "Question Text");
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

    @Test
    public void testSearchByIdWithAnswerOptions() throws Exception {
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, "Some Option Text", Boolean.TRUE));
        when(answerOptionMediaRepo.findByAnswerOptionId(1L)).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMediaList(1L, 1L, 1, getStringBase64("http://www.google.com")));
        when(answerReferenceMediaRepo.findByQuestionId(1L)).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMediaList(1L, 1L, 1, getStringBase64("http://www.google.com")));
        QMeQuestionDetail questionDetail = questionService.searchById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(answerOptionMediaRepo).findByAnswerOptionId(1L);
        verify(answerReferenceMediaRepo).findByQuestionId(1L);
        assertNotNull(questionDetail);
        assertThat(questionDetail.getQuestionId(), equalTo(1L));
        assertThat(questionDetail.getQuestionText(), equalTo("Some question text"));
        assertNotNull(questionDetail.getAnswerOptionList());
        assertThat(questionDetail.getAnswerOptionList().size(), equalTo(1));
        assertThat(questionDetail.getAnswerOptionList().get(0).getAnswerOptionMediaList().size(), equalTo(1));
        assertThat(questionDetail.getAnswerReferenceMediaList().size(), equalTo(1));
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
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option Media
        QMeAnswerOptionMedia answerOptionMedia = new QMeAnswerOptionMedia();
        answerOptionMedia.setMediaType(MediaTypeEnum.LINK.getMediaType());
        answerOptionMedia.setMedia(getStringBase64("http://google.com"));
        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        answerOptionMediaList.add(answerOptionMedia);
        answerOption.setAnswerOptionMediaList(answerOptionMediaList);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Reference Media
        QMeAnswerReferenceMedia answerReferenceMedia = new QMeAnswerReferenceMedia();
        answerReferenceMedia.setMediaTypeID(1);
        answerReferenceMedia.setMedia(getStringBase64("http://google.com"));
        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(answerReferenceMedia);
        qmeQuestion.setAnswerReferenceMediaList(answerReferenceMediaList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMedia(1L, 1L,   MediaTypeEnum.fromValue(answerOptionMedia.getMediaType()).getMediaTypeId() , answerOptionMedia.getMedia()));
        when(answerReferenceMediaRepo.save(Matchers.anyObject())).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMedia(1L, 1L, answerReferenceMedia.getMediaTypeID(), answerReferenceMedia.getMedia()));

        QMeQuestionDetail questionDetail = questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
        verify(answerOptionRepo).save(Matchers.anyObject());
        verify(answerOptionMediaRepo).save(Matchers.anyObject());
        verify(answerReferenceMediaRepo).save(Matchers.anyObject());

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
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();
        QMeAnswerOption answerOption = getQMeAnswerOption();
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenThrow(QMeException.class);
        questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidQuestionText() throws Exception {
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setCategoryId(1L);
        qmeQuestion.setQuestionPoint(1);
        questionService.save(qmeQuestion, 1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidAnswerText() throws Exception {
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setCategoryId(1L);
        qmeQuestion.setQuestionPoint(1);
        questionService.save(qmeQuestion, 1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidCategory() throws Exception {
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setQuestionPoint(1);
        questionService.save(qmeQuestion, 1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidCategoryNotFound() throws Exception {
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setQuestionPoint(1);
        qmeQuestion.setCategoryId(1L);
        when(categoryRepo.findById(1L)).thenReturn(null);
        questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidQuestionPoint() throws Exception {
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setCategoryId(1L);
        questionService.save(qmeQuestion, 1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidCreateUserId() throws Exception {
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setQuestionPoint(1);
        qmeQuestion.setCategoryId(1L);
        qmeQuestion.setCreateUserID(null);
        questionService.save(qmeQuestion, 1L);
    }

    @Test
    public void testSaveAnswerOptions() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));

        QMeQuestionDetail questionDetail = questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
        verify(answerOptionRepo).save(Matchers.anyObject());
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

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveAnswerOptionsInvalid() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setOptionText(null);
        answerOption.setCorrect(Boolean.TRUE);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));

        questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
    }

    @Test(expected = QMeServerException.class)
    public void testSaveAnswerOptionsQMeServerException() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenThrow(QMeException.class);

        questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveAnswerOptionsWithMediaInvalid() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option Media
        QMeAnswerOptionMedia answerOptionMedia = new QMeAnswerOptionMedia();
        answerOptionMedia.setMediaType(null);
        answerOptionMedia.setMedia(getStringBase64("http://google.com"));
        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        answerOptionMediaList.add(answerOptionMedia);
        answerOption.setAnswerOptionMediaList(answerOptionMediaList);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMedia(1L, 1L, MediaTypeEnum.LINK.getMediaTypeId(), answerOptionMedia.getMedia()));

        questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
        verify(answerOptionRepo).save(Matchers.anyObject());
        verify(answerOptionMediaRepo).save(Matchers.anyObject());
    }

    @Test
    public void testSaveAnswerOptionsWithMediaLink() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Option Media
        QMeAnswerOptionMedia answerOptionMedia = new QMeAnswerOptionMedia();
        answerOptionMedia.setMediaType(MediaTypeEnum.LINK.getMediaType());
        answerOptionMedia.setMedia(getStringBase64("http://google.com"));
        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        answerOptionMediaList.add(answerOptionMedia);
        answerOption.setAnswerOptionMediaList(answerOptionMediaList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMedia(1L, 1L, MediaTypeEnum.fromValue(answerOptionMedia.getMediaType()).getMediaTypeId(), answerOptionMedia.getMedia()));

        QMeQuestionDetail questionDetail = questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
        verify(answerOptionRepo).save(Matchers.anyObject());
        verify(answerOptionMediaRepo).save(Matchers.anyObject());

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
    public void testSaveAnswerOptionsWithMediaLinkQMeServerException() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Option Media
        QMeAnswerOptionMedia answerOptionMedia = new QMeAnswerOptionMedia();
        answerOptionMedia.setMediaType(MediaTypeEnum.LINK.getMediaType());
        answerOptionMedia.setMedia(getStringBase64("http://google.com"));
        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        answerOptionMediaList.add(answerOptionMedia);
        answerOption.setAnswerOptionMediaList(answerOptionMediaList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.save(Matchers.anyObject())).thenThrow(QMeException.class);

        questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
        verify(answerOptionRepo).save(Matchers.anyObject());
        verify(answerOptionMediaRepo).save(Matchers.anyObject());
    }

    @Test
    public void testSaveAnswerOptionsWithMediaImagePng() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Option Media
        QMeAnswerOptionMedia answerOptionMedia = new QMeAnswerOptionMedia();
        answerOptionMedia.setMediaType(MediaTypeEnum.IMAGE_PNG.getMediaType());
        answerOptionMedia.setMedia(getImageBase64("cube.png"));
        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        answerOptionMediaList.add(answerOptionMedia);
        answerOption.setAnswerOptionMediaList(answerOptionMediaList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMedia(1L, 1L, MediaTypeEnum.fromValue(answerOptionMedia.getMediaType()).getMediaTypeId(), answerOptionMedia.getMedia()));

        QMeQuestionDetail questionDetail = questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
        verify(answerOptionRepo).save(Matchers.anyObject());
        verify(answerOptionMediaRepo).save(Matchers.anyObject());

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

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveAnswerOptionsWithMediaImagePngInvalid() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Option Media
        QMeAnswerOptionMedia answerOptionMedia = new QMeAnswerOptionMedia();
        answerOptionMedia.setMediaType(MediaTypeEnum.LINK.getMediaType());
        answerOptionMedia.setMedia(null);
        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        answerOptionMediaList.add(answerOptionMedia);
        answerOption.setAnswerOptionMediaList(answerOptionMediaList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMedia(1L, 1L, MediaTypeEnum.fromValue( answerOptionMedia.getMediaType()).getMediaTypeId(), answerOptionMedia.getMedia()));

        questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
        verify(answerOptionRepo).save(Matchers.anyObject());
        verify(answerOptionMediaRepo).save(Matchers.anyObject());
    }

    @Test(expected = QMeServerException.class)
    public void testSaveAnswerOptionsWithMediaImagePngQMeServerException() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Option Media
        QMeAnswerOptionMedia answerOptionMedia = new QMeAnswerOptionMedia();
        answerOptionMedia.setMediaType(MediaTypeEnum.IMAGE_PNG.getMediaType());
        answerOptionMedia.setMedia(getImageBase64("cube.png"));
        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        answerOptionMediaList.add(answerOptionMedia);
        answerOption.setAnswerOptionMediaList(answerOptionMediaList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.save(Matchers.anyObject())).thenThrow(QMeException.class);

        questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
        verify(answerOptionRepo).save(Matchers.anyObject());
        verify(answerOptionMediaRepo).save(Matchers.anyObject());
    }

    @Test
    public void testSaveAnswerReferenceMedia() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Reference Media
        QMeAnswerReferenceMedia answerReferenceMedia = new QMeAnswerReferenceMedia();
        answerReferenceMedia.setMediaTypeID(1);
        answerReferenceMedia.setMedia(getStringBase64("http://google.com"));
        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(answerReferenceMedia);
        qmeQuestion.setAnswerReferenceMediaList(answerReferenceMediaList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.save(Matchers.anyObject())).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMedia(1L, 1L, answerReferenceMedia.getMediaTypeID(), answerReferenceMedia.getMedia()));

        QMeQuestionDetail questionDetail = questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
        verify(answerOptionRepo).save(Matchers.anyObject());
        verify(answerReferenceMediaRepo).save(Matchers.anyObject());

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

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveAnswerReferenceMediaInvalid() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Reference Media
        QMeAnswerReferenceMedia answerReferenceMedia = new QMeAnswerReferenceMedia();
        answerReferenceMedia.setMediaTypeID(null);
        answerReferenceMedia.setMedia(null);
        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(answerReferenceMedia);
        qmeQuestion.setAnswerReferenceMediaList(answerReferenceMediaList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.save(Matchers.anyObject())).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMedia(1L, 1L, answerReferenceMedia.getMediaTypeID(), answerReferenceMedia.getMedia()));

        questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
        verify(answerOptionRepo).save(Matchers.anyObject());
        verify(answerReferenceMediaRepo).save(Matchers.anyObject());
    }

    @Test(expected = QMeServerException.class)
    public void testSaveAnswerReferenceMediaQMeServerException() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Reference Media
        QMeAnswerReferenceMedia answerReferenceMedia = new QMeAnswerReferenceMedia();
        answerReferenceMedia.setMediaTypeID(1);
        answerReferenceMedia.setMedia(getStringBase64("http://google.com"));
        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(answerReferenceMedia);
        qmeQuestion.setAnswerReferenceMediaList(answerReferenceMediaList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.save(Matchers.anyObject())).thenThrow(QMeException.class);

        questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
        verify(answerOptionRepo).save(Matchers.anyObject());
        verify(answerReferenceMediaRepo).save(Matchers.anyObject());
    }

    @Test
    public void testSaveAnswerReferenceMediaImagePNG() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Reference Media
        QMeAnswerReferenceMedia answerReferenceMedia = new QMeAnswerReferenceMedia();
        answerReferenceMedia.setMediaTypeID(2);
        answerReferenceMedia.setMedia(getImageBase64("cube.png"));
        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(answerReferenceMedia);
        qmeQuestion.setAnswerReferenceMediaList(answerReferenceMediaList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.save(Matchers.anyObject())).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMedia(1L, 1L, answerReferenceMedia.getMediaTypeID(), answerReferenceMedia.getMedia()));

        QMeQuestionDetail questionDetail = questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
        verify(answerOptionRepo).save(Matchers.anyObject());
        verify(answerReferenceMediaRepo).save(Matchers.anyObject());

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

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveAnswerReferenceMediaImagePNGInvalid() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Reference Media
        QMeAnswerReferenceMedia answerReferenceMedia = new QMeAnswerReferenceMedia();
        answerReferenceMedia.setMediaTypeID(2);
        answerReferenceMedia.setMedia(null);
        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(answerReferenceMedia);
        qmeQuestion.setAnswerReferenceMediaList(answerReferenceMediaList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.save(Matchers.anyObject())).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMedia(1L, 1L, answerReferenceMedia.getMediaTypeID(), answerReferenceMedia.getMedia()));

        questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
        verify(answerOptionRepo).save(Matchers.anyObject());
        verify(answerReferenceMediaRepo).save(Matchers.anyObject());
    }

    @Test(expected = QMeServerException.class)
    public void testSaveAnswerReferenceMediaImagePNGQMeServerException() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Reference Media
        QMeAnswerReferenceMedia answerReferenceMedia = new QMeAnswerReferenceMedia();
        answerReferenceMedia.setMediaTypeID(2);
        answerReferenceMedia.setMedia(getImageBase64("cube.png"));
        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(answerReferenceMedia);
        qmeQuestion.setAnswerReferenceMediaList(answerReferenceMediaList);

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.save(Matchers.anyObject())).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.save(Matchers.anyObject())).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.save(Matchers.anyObject())).thenThrow(QMeException.class);

        questionService.save(qmeQuestion, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).save(Matchers.anyObject());
        verify(answerOptionRepo).save(Matchers.anyObject());
        verify(answerReferenceMediaRepo).save(Matchers.anyObject());
    }

    @Test
    public void testUpdate() throws Exception {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());

        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        QMeAnswerOption answerOption = getQMeAnswerOption();
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        QMeQuestionDetail questionDetail = questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.<Long>anyObject());
        assertNotNull(questionDetail);
        assertThat(questionDetail.getQuestionId(), equalTo(1L));
        assertThat(questionDetail.getQuestionText(), equalTo("Some question text"));
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testUpdateQMeResourceNotFoundException() throws Exception {
        when(questionRepo.findById(1L)).thenReturn(null);
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();
        questionService.update(qmeQuestion, 1L, 1L);
        verify(questionRepo).findById(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testUpdateQMeServerException() throws Exception {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenThrow(QMeException.class);

        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        QMeAnswerOption answerOption = getQMeAnswerOption();
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        questionService.update(qmeQuestion, 1L, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.<Long>anyObject());
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testUpdateInvalidQuestionText() throws Exception {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());

        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();
        qmeQuestion.setQuestionText(null);

        questionService.update(qmeQuestion, 1L, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testUpdateInvalidAnswerText() throws Exception {
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());

        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();
        qmeQuestion.setAnswer(null);

        questionService.update(qmeQuestion, 1L, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testUpdateInvalidCategory() throws Exception {
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());

        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();
        qmeQuestion.setCategoryId(null);

        questionService.update(qmeQuestion, 1L, 1L);
        verify(questionRepo).findById(1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testUpdateInvalidCategoryNotFound() throws Exception {
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(null);

        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();
        questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testUpdateInvalidQuestionPoint() throws Exception {
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(null);

        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();
        qmeQuestion.setQuestionPoint(null);

        questionService.update(qmeQuestion, 1L, 1L);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testUpdateInvalidUpdateUserId() throws Exception {
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(null);

        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        questionService.update(qmeQuestion, 1L, null);
        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
    }

    @Test
    public void testUpdateAnswerOptions() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));

        QMeQuestionDetail questionDetail = questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionRepo).update(Matchers.anyObject(), Matchers.anyObject());

        assertNotNull(questionDetail);
        assertThat(questionDetail.getQuestionId(), equalTo(1L));
        assertThat(questionDetail.getQuestionText(), equalTo("Some question text"));
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testUpdateAnswerOptionsInvalid() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);
        answerOption.setOptionText(null);
        answerOption.setCorrect(null);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
    }

    @Test(expected = QMeServerException.class)
    public void testUpdateAnswerOptionsQMeServerException() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.update(Matchers.anyObject(), eq(1L))).thenThrow(QMeException.class);

        questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionRepo).update(Matchers.anyObject(), Matchers.anyObject());
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testUpdateAnswerOptionsWithMediaInvalid() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);

        //Answer Option Media
        QMeAnswerOptionMedia answerOptionMedia = new QMeAnswerOptionMedia();
        answerOptionMedia.setAnswerOptionID(1L);
        answerOptionMedia.setAnswerOptionMediaID(1L);
        answerOptionMedia.setMediaType(null);
        answerOptionMedia.setMedia(null);
        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        answerOptionMediaList.add(answerOptionMedia);
        answerOption.setAnswerOptionMediaList(answerOptionMediaList);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.findByAnswerOptionId(1L)).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMediaList(1L, 1L, MediaTypeEnum.LINK.getMediaTypeId(), answerOptionMedia.getMedia()));
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));

        questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(answerOptionMediaRepo).findByAnswerOptionId(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
    }

    @Test
    public void testUpdateAnswerOptionsWithMediaLink() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);

        //Answer Option Media
        QMeAnswerOptionMedia answerOptionMedia = new QMeAnswerOptionMedia();
        answerOptionMedia.setAnswerOptionID(1L);
        answerOptionMedia.setAnswerOptionMediaID(1L);
        answerOptionMedia.setMediaType(MediaTypeEnum.LINK.getMediaType());
        answerOptionMedia.setMedia(getStringBase64("http://google.com"));

        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        answerOptionMediaList.add(answerOptionMedia);
        answerOption.setAnswerOptionMediaList(answerOptionMediaList);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.findByAnswerOptionId(1L)).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMediaList(1L, 1L, MediaTypeEnum.fromValue(answerOptionMedia.getMediaType()).getMediaTypeId(), answerOptionMedia.getMedia()));
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMedia(1L, 1L, MediaTypeEnum.fromValue(answerOptionMedia.getMediaType()).getMediaTypeId(), answerOptionMedia.getMedia()));

        questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(answerOptionMediaRepo).findByAnswerOptionId(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionMediaRepo).update(Matchers.anyObject(), Matchers.anyObject());
    }

    @Test(expected = QMeServerException.class)
    public void testUpdateAnswerOptionsWithMediaLinkQMeServerException() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);

        //Answer Option Media
        QMeAnswerOptionMedia answerOptionMedia = new QMeAnswerOptionMedia();
        answerOptionMedia.setAnswerOptionID(1L);
        answerOptionMedia.setAnswerOptionMediaID(1L);
        answerOptionMedia.setMediaType(MediaTypeEnum.LINK.getMediaType());
        answerOptionMedia.setMedia(getStringBase64("http://google.com"));

        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        answerOptionMediaList.add(answerOptionMedia);
        answerOption.setAnswerOptionMediaList(answerOptionMediaList);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.findByAnswerOptionId(1L)).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMediaList(1L, 1L, MediaTypeEnum.fromValue(answerOptionMedia.getMediaType()).getMediaTypeId(), answerOptionMedia.getMedia()));
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.update(Matchers.anyObject(), eq(1L))).thenThrow(QMeException.class);

        questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(answerOptionMediaRepo).findByAnswerOptionId(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionMediaRepo).update(Matchers.anyObject(), Matchers.anyObject());
    }

    @Test
    public void testUpdateAnswerOptionsWithMediaImagePng() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);

        //Answer Option Media
        QMeAnswerOptionMedia answerOptionMedia = new QMeAnswerOptionMedia();
        answerOptionMedia.setAnswerOptionID(1L);
        answerOptionMedia.setAnswerOptionMediaID(1L);
        answerOptionMedia.setMediaType(MediaTypeEnum.IMAGE_PNG.getMediaType());
        answerOptionMedia.setMedia(getImageBase64("cube.png"));

        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        answerOptionMediaList.add(answerOptionMedia);
        answerOption.setAnswerOptionMediaList(answerOptionMediaList);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.findByAnswerOptionId(1L)).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMediaList(1L, 1L, MediaTypeEnum.fromValue(answerOptionMedia.getMediaType()).getMediaTypeId(), answerOptionMedia.getMedia()));
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMedia(1L, 1L, MediaTypeEnum.fromValue(answerOptionMedia.getMediaType()).getMediaTypeId(), answerOptionMedia.getMedia()));

        questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(answerOptionMediaRepo).findByAnswerOptionId(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionMediaRepo).update(Matchers.anyObject(), Matchers.anyObject());
    }


    @Test(expected = QMeInvalidResourceDataException.class)
    public void testUpdateAnswerOptionsWithMediaImagePngInvalid() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);

        //Answer Option Media
        QMeAnswerOptionMedia answerOptionMedia = new QMeAnswerOptionMedia();
        answerOptionMedia.setAnswerOptionID(1L);
        answerOptionMedia.setAnswerOptionMediaID(1L);
        answerOptionMedia.setMediaType(MediaTypeEnum.IMAGE_PNG.getMediaType());
        answerOptionMedia.setMedia(null);
        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        answerOptionMediaList.add(answerOptionMedia);
        answerOption.setAnswerOptionMediaList(answerOptionMediaList);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.findByAnswerOptionId(1L)).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMediaList(1L, 1L, MediaTypeEnum.fromValue(answerOptionMedia.getMediaType()).getMediaTypeId(), answerOptionMedia.getMedia()));
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));

        questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(answerOptionMediaRepo).findByAnswerOptionId(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
    }

    @Test(expected = QMeServerException.class)
    public void testUpdateAnswerOptionsWithMediaImagePngQMeServerException() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);

        //Answer Option Media
        QMeAnswerOptionMedia answerOptionMedia = new QMeAnswerOptionMedia();
        answerOptionMedia.setAnswerOptionID(1L);
        answerOptionMedia.setAnswerOptionMediaID(1L);
        answerOptionMedia.setMediaType(MediaTypeEnum.IMAGE_PNG.getMediaType());
        answerOptionMedia.setMedia(getImageBase64("cube.png"));

        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        answerOptionMediaList.add(answerOptionMedia);
        answerOption.setAnswerOptionMediaList(answerOptionMediaList);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.findByAnswerOptionId(1L)).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMediaList(1L, 1L, MediaTypeEnum.fromValue(answerOptionMedia.getMediaType()).getMediaTypeId(), answerOptionMedia.getMedia()));
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerOptionMediaRepo.update(Matchers.anyObject(), eq(1L))).thenThrow(QMeException.class);

        questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(answerOptionMediaRepo).findByAnswerOptionId(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionMediaRepo).update(Matchers.anyObject(), Matchers.anyObject());
    }

    @Test
    public void testUpdateAnswerReferenceMedia() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Reference Media
        QMeAnswerReferenceMedia answerReferenceMedia = new QMeAnswerReferenceMedia();
        answerReferenceMedia.setAnswerRefMediaID(1L);
        answerReferenceMedia.setMediaTypeID(1);
        answerReferenceMedia.setMedia("http://google.com".getBytes());
        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(answerReferenceMedia);
        qmeQuestion.setAnswerReferenceMediaList(answerReferenceMediaList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.findByQuestionId(1L)).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMediaList(1L, 1L, answerReferenceMedia.getMediaTypeID(), answerReferenceMedia.getMedia()));
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMedia(1L, 1L, answerReferenceMedia.getMediaTypeID(), answerReferenceMedia.getMedia()));

        QMeQuestionDetail questionDetail = questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(answerReferenceMediaRepo).findByQuestionId(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerReferenceMediaRepo).update(Matchers.anyObject(), Matchers.anyObject());

        assertNotNull(questionDetail);
        assertThat(questionDetail.getQuestionId(), equalTo(1L));
        assertThat(questionDetail.getQuestionText(), equalTo("Some question text"));
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testUpdateAnswerReferenceMediaInvalid() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Reference Media
        QMeAnswerReferenceMedia answerReferenceMedia = new QMeAnswerReferenceMedia();
        answerReferenceMedia.setAnswerRefMediaID(1L);
        answerReferenceMedia.setMediaTypeID(null);
        answerReferenceMedia.setMedia(null);
        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(answerReferenceMedia);
        qmeQuestion.setAnswerReferenceMediaList(answerReferenceMediaList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.findByQuestionId(1L)).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMediaList(1L, 1L, answerReferenceMedia.getMediaTypeID(), answerReferenceMedia.getMedia()));
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));

        questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionRepo).update(Matchers.anyObject(), Matchers.anyObject());
    }

    @Test(expected = QMeServerException.class)
    public void testUpdateAnswerReferenceMediaQMeServerException() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Reference Media
        QMeAnswerReferenceMedia answerReferenceMedia = new QMeAnswerReferenceMedia();
        answerReferenceMedia.setAnswerRefMediaID(1L);
        answerReferenceMedia.setMediaTypeID(1);
        answerReferenceMedia.setMedia("http://google.com".getBytes());
        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(answerReferenceMedia);
        qmeQuestion.setAnswerReferenceMediaList(answerReferenceMediaList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.findByQuestionId(1L)).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMediaList(1L, 1L, answerReferenceMedia.getMediaTypeID(), answerReferenceMedia.getMedia()));
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.update(Matchers.anyObject(), eq(1L))).thenThrow(QMeException.class);

        questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerReferenceMediaRepo).update(Matchers.anyObject(), Matchers.anyObject());
    }

    @Test
    public void testUpdateAnswerReferenceMediaImagePNG() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Reference Media
        QMeAnswerReferenceMedia answerReferenceMedia = new QMeAnswerReferenceMedia();
        answerReferenceMedia.setAnswerRefMediaID(1L);
        answerReferenceMedia.setMediaTypeID(2);
        answerReferenceMedia.setMedia(getImageBase64("cube.png"));
        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(answerReferenceMedia);
        qmeQuestion.setAnswerReferenceMediaList(answerReferenceMediaList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.findByQuestionId(1L)).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMediaList(1L, 1L, answerReferenceMedia.getMediaTypeID(), answerReferenceMedia.getMedia()));
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMedia(1L, 1L, answerReferenceMedia.getMediaTypeID(), answerReferenceMedia.getMedia()));

        QMeQuestionDetail questionDetail = questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerReferenceMediaRepo).update(Matchers.anyObject(), Matchers.anyObject());

        assertNotNull(questionDetail);
        assertThat(questionDetail.getQuestionId(), equalTo(1L));
        assertThat(questionDetail.getQuestionText(), equalTo("Some question text"));
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testUpdateAnswerReferenceMediaImagePNGInvalid() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Reference Media
        QMeAnswerReferenceMedia answerReferenceMedia = new QMeAnswerReferenceMedia();
        answerReferenceMedia.setAnswerRefMediaID(1L);
        answerReferenceMedia.setMediaTypeID(2);
        answerReferenceMedia.setMedia(null);
        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(answerReferenceMedia);
        qmeQuestion.setAnswerReferenceMediaList(answerReferenceMediaList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.findByQuestionId(1L)).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMediaList(1L, 1L, answerReferenceMedia.getMediaTypeID(), answerReferenceMedia.getMedia()));
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));

        questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionRepo).update(Matchers.anyObject(), Matchers.anyObject());
    }

    @Test(expected = QMeServerException.class)
    public void testUpdateAnswerReferenceMediaImagePNGQMeServerException() throws Exception {
        //Question
        QMeQuestionDetail qmeQuestion = getQMeQuestionDetail();

        //Answer Option
        QMeAnswerOption answerOption = getQMeAnswerOption();
        answerOption.setAnswerOptionID(1L);

        //Answer Option List
        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(answerOption);
        qmeQuestion.setAnswerOptionList(answerOptionList);

        //Answer Reference Media
        QMeAnswerReferenceMedia answerReferenceMedia = new QMeAnswerReferenceMedia();
        answerReferenceMedia.setAnswerRefMediaID(1L);
        answerReferenceMedia.setMediaTypeID(2);
        answerReferenceMedia.setMedia(getImageBase64("cube.png"));
        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(answerReferenceMedia);
        qmeQuestion.setAnswerReferenceMediaList(answerReferenceMediaList);

        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.findByQuestionId(1L)).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMediaList(1L, 1L, answerReferenceMedia.getMediaTypeID(), answerReferenceMedia.getMedia()));
        when(questionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.update(Matchers.anyObject(), eq(1L))).thenReturn(AnswerOptionFixtures.simpleAnswerOption(1L, 1L, answerOption.getOptionText(), answerOption.getCorrect()));
        when(answerReferenceMediaRepo.update(Matchers.anyObject(), eq(1L))).thenThrow(QMeException.class);

        questionService.update(qmeQuestion, 1L, 1L);

        verify(categoryRepo).findById(1L);
        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(questionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerOptionRepo).update(Matchers.anyObject(), Matchers.anyObject());
        verify(answerReferenceMediaRepo).update(Matchers.anyObject(), Matchers.anyObject());
    }

    @Test
    public void testDelete() throws Exception {
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        doNothing().when(questionRepo).delete(1L);
        questionService.delete(1L);
        verify(questionRepo).findById(1L);
        verify(questionRepo).delete(1L);
    }

    @Test
    public void testDeleteWithAnswerOptionsAnswerOptionsMediaAndAnswerReferenceMedia() throws Exception {
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        when(answerOptionRepo.findByQuestionId(1L)).thenReturn(AnswerOptionFixtures.simpleAnswerOptionList(1L, 1L, "Some Option Text", Boolean.TRUE));
        when(answerOptionMediaRepo.findByAnswerOptionId(1L)).thenReturn(AnswerOptionMediaFixtures.simpleAnswerOptionMediaList(1L, 1L, 1, "http://www.google.com".getBytes()));
        when(answerReferenceMediaRepo.findByQuestionId(1L)).thenReturn(AnswerReferenceMediaFixtures.simpleAnswerReferenceMediaList(1L, 1L, 1, "http://www.google.com".getBytes()));

        doNothing().when(answerOptionMediaRepo).delete(1L);
        doNothing().when(answerOptionRepo).delete(1L);
        doNothing().when(answerReferenceMediaRepo).delete(1L);
        doNothing().when(questionRepo).delete(1L);

        questionService.delete(1L);

        verify(questionRepo).findById(1L);
        verify(answerOptionRepo).findByQuestionId(1L);
        verify(answerOptionMediaRepo).findByAnswerOptionId(1L);
        verify(answerReferenceMediaRepo).findByQuestionId(1L);
        verify(answerOptionMediaRepo).delete(1L);
        verify(answerOptionRepo).delete(1L);
        verify(answerReferenceMediaRepo).delete(1L);
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

    /**
     * Get QMe Question Detail
     *
     * @return QMeQuestionDetail
     */
    private QMeQuestionDetail getQMeQuestionDetail() {
        QMeQuestionDetail qmeQuestion = new QMeQuestionDetail();
        qmeQuestion.setQuestionText("Some Question Text");
        qmeQuestion.setAnswer("Some Answer");
        qmeQuestion.setCategoryId(1L);
        qmeQuestion.setQuestionPoint(1);
        return qmeQuestion;
    }

    /**
     * Get QMe Answer Option
     *
     * @return QMeAnswerOption
     */
    private QMeAnswerOption getQMeAnswerOption() {
        QMeAnswerOption answerOption = new QMeAnswerOption();
        answerOption.setOptionText("some text");
        answerOption.setCorrect(Boolean.TRUE);
        return answerOption;
    }

    /**
     * Get Image Base 64 String
     *
     * @param filePath
     * @return Base64 Encoded String
     * @throws IOException
     */
    private byte[] getImageBase64(String filePath) throws IOException, URISyntaxException {
        byte byteArray[] = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(filePath).toURI()));
        return Base64Utils.encodeToString(byteArray).getBytes();
    }

    /**
     * Get String Base 64 String
     *
     * @param string
     * @return Base64 Encoded String
     * @throws IOException
     */
    private byte[] getStringBase64(String string) throws IOException, URISyntaxException {
        return Base64Utils.encodeToString(string.getBytes()).getBytes();
    }
}
