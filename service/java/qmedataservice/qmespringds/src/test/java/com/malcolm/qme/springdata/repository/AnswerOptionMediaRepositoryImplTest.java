/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerOptionMediaRepositoryImplTest.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : Tests for AnswerOptionMedia Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.AnswerOption;
import com.malcolm.qme.core.domain.AnswerOptionMedia;
import com.malcolm.qme.core.domain.Question;
import com.malcolm.qme.core.repository.AnswerOptionMediaRepository;
import com.malcolm.qme.core.repository.AnswerOptionRepository;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.AnswerOptionMediaEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class AnswerOptionMediaRepositoryImplTest {

    /**
     * Answer OptionMedia  Repository
     */
    @Autowired
    @Qualifier("AnswerOptionMediaRepository")
    private AnswerOptionMediaRepository answerOptionMediaRepository;

    /**
     * Answer Option Repository
     */
    @Autowired
    @Qualifier("AnswerOptionRepository")
    private AnswerOptionRepository answerOptionRepository;

    @Mock
    private AnswerOptionMediaSpringDataRepository answerOptionMediaSpringDataRepositoryMOCK;

    @InjectMocks
    private AnswerOptionMediaRepository answerOptionRepositoryWithMock;

    @Before
    public void initMocks(){
        answerOptionRepositoryWithMock = new AnswerOptionMediaRepositoryImpl();
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Question Repository
     */
    @Autowired
    @Qualifier("QuestionRepository")
    private QuestionRepository questionRepository;

    @Test
    public void testFetchAll() throws QMeException {
        assertNotNull(answerOptionMediaRepository);
        List<AnswerOptionMedia> answerOptionMediaList = answerOptionMediaRepository.findAll();
        assertNotNull(answerOptionMediaList);
        assertThat(answerOptionMediaList.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(answerOptionMediaRepository);
        final AnswerOptionMedia answerOptionMedia = answerOptionMediaRepository.findById(1L);
        assertNotNull(answerOptionMedia);
        assertThat(answerOptionMedia.getAnswerOptionMediaID(), equalTo(1L));
    }

    @Test
    public void testCRUD() throws QMeException {

        assertNotNull(answerOptionMediaRepository);

        assertNotNull(answerOptionRepository);

        assertNotNull(questionRepository);

        Question question = new Question(1L, "AnswerOptionMediaRepositoryImplTest Question", "AnswerOptionMediaRepositoryImplTest Answer", 1L);

        question = questionRepository.save(question);
        assertNotNull(question);
        assertThat(question.getQuestionID(), greaterThan(0L));
        final Long questionID = question.getQuestionID();

        AnswerOption answerOption = new AnswerOption(questionID, "Option 1", false);
        answerOption = answerOptionRepository.save(answerOption);
        assertNotNull(answerOption);
        assertThat(answerOption.getAnswerOptionID(), greaterThan(0L));
        final Long answerOptionID = answerOption.getAnswerOptionID();

        AnswerOptionMedia answerOptionMedia = new AnswerOptionMedia(answerOptionID, 1, "testing".getBytes());
        answerOptionMedia = answerOptionMediaRepository.save(answerOptionMedia);
        assertNotNull(answerOptionMedia);
        assertThat(answerOptionMedia.getAnswerOptionMediaID(), greaterThan(0L));
        final Long answerOptionMediaID = answerOptionMedia.getAnswerOptionMediaID();

        answerOptionMedia = answerOptionMediaRepository.findById(answerOptionMediaID);
        assertNotNull(answerOptionMedia);
        assertThat(answerOptionMedia.getAnswerOptionMediaID(), equalTo(answerOptionMediaID));
        assertThat(new String(answerOptionMedia.getMedia()), equalTo("testing"));

        AnswerOptionMedia answerOptionMediaUpdates = new AnswerOptionMedia(
                answerOptionMedia.getAnswerOptionMediaID(),
                answerOptionMedia.getAnswerOptionID(),
                answerOptionMedia.getMediaTypeID(),
                "testing updates".getBytes());
        answerOptionMediaUpdates = answerOptionMediaRepository.save(answerOptionMediaUpdates);
        assertNotNull(answerOptionMediaUpdates);
        assertThat(answerOptionMediaUpdates.getAnswerOptionMediaID(), equalTo(answerOptionMediaID));
        assertThat(new String(answerOptionMediaUpdates.getMedia()), equalTo("testing updates"));

        answerOptionMediaRepository.delete(answerOptionMediaID);
        answerOptionMedia = answerOptionMediaRepository.findById(answerOptionMediaID);
        assertNull(answerOptionMedia);

        answerOptionRepository.delete(answerOptionID);
        answerOption = answerOptionRepository.findById(answerOptionID);
        assertNull(answerOption);

        questionRepository.delete(questionID);
        question = questionRepository.findById(questionID);
        assertNull(question);

    }

    @Test
    public void testFindByAnswerOptionId() throws QMeException {

        assertNotNull(answerOptionMediaRepository);

        assertNotNull(answerOptionRepository);

        assertNotNull(questionRepository);

        Question question = new Question(1L, "AnswerOptionMediaRepositoryImplTest Question", "AnswerOptionMediaRepositoryImplTest Answer", 1L);

        question = questionRepository.save(question);
        assertNotNull(question);
        assertThat(question.getQuestionID(), greaterThan(0L));
        final Long questionID = question.getQuestionID();

        AnswerOption answerOption = new AnswerOption(questionID, "Option 1", false);
        answerOption = answerOptionRepository.save(answerOption);
        assertNotNull(answerOption);
        assertThat(answerOption.getAnswerOptionID(), greaterThan(0L));
        final Long answerOptionID = answerOption.getAnswerOptionID();

        AnswerOptionMedia answerOptionMedia = new AnswerOptionMedia(answerOptionID, 1, "testing".getBytes());
        answerOptionMedia = answerOptionMediaRepository.save(answerOptionMedia);
        assertNotNull(answerOptionMedia);
        assertThat(answerOptionMedia.getAnswerOptionMediaID(), greaterThan(0L));
        final Long answerOptionMediaID = answerOptionMedia.getAnswerOptionMediaID();

        answerOptionMedia = answerOptionMediaRepository.findById(answerOptionMediaID);
        assertNotNull(answerOptionMedia);
        assertThat(answerOptionMedia.getAnswerOptionMediaID(), equalTo(answerOptionMediaID));
        assertThat(new String(answerOptionMedia.getMedia()), equalTo("testing"));

        List<AnswerOptionMedia> answerOptionMediaList = answerOptionMediaRepository.findByAnswerOptionId(answerOptionID);
        assertNotNull(answerOptionMediaList);
        assertThat(answerOptionMediaList.size(), equalTo(1));
        assertThat(answerOptionMediaList.get(0).getAnswerOptionMediaID(), equalTo(answerOptionMediaID));
        assertThat(new String(answerOptionMediaList.get(0).getMedia()), equalTo("testing"));

        AnswerOptionMedia answerOptionMediaUpdates = new AnswerOptionMedia(
                answerOptionMedia.getAnswerOptionMediaID(),
                answerOptionMedia.getAnswerOptionID(),
                answerOptionMedia.getMediaTypeID(),
                "testing updates".getBytes());
        answerOptionMediaUpdates = answerOptionMediaRepository.save(answerOptionMediaUpdates);
        assertNotNull(answerOptionMediaUpdates);
        assertThat(answerOptionMediaUpdates.getAnswerOptionMediaID(), equalTo(answerOptionMediaID));
        assertThat(new String(answerOptionMediaUpdates.getMedia()), equalTo("testing updates"));

        answerOptionMediaList = answerOptionMediaRepository.findByAnswerOptionId(answerOptionID);
        assertNotNull(answerOptionMediaList);
        assertThat(answerOptionMediaList.size(), equalTo(1));
        assertThat(answerOptionMediaList.get(0).getAnswerOptionMediaID(), equalTo(answerOptionMediaID));
        assertThat(new String(answerOptionMediaList.get(0).getMedia()), equalTo("testing updates"));

        answerOptionMediaRepository.delete(answerOptionMediaID);
        answerOptionMedia = answerOptionMediaRepository.findById(answerOptionMediaID);
        assertNull(answerOptionMedia);

        answerOptionRepository.delete(answerOptionID);
        answerOption = answerOptionRepository.findById(answerOptionID);
        assertNull(answerOption);

        questionRepository.delete(questionID);
        question = questionRepository.findById(questionID);
        assertNull(question);
    }

    @Test
    public void testFindByAnswerNullReturn() throws QMeException {
        when(answerOptionMediaSpringDataRepositoryMOCK.findByOptionId(1L)).thenReturn(null);
        List<AnswerOptionMedia> answerOptionMediaList = answerOptionRepositoryWithMock.findByAnswerOptionId(1L);
        verify(answerOptionMediaSpringDataRepositoryMOCK).findByOptionId(1L);
        assertNotNull(answerOptionMediaList);
        assertThat(answerOptionMediaList.size(), equalTo(0));
    }

    @Test(expected = QMeException.class)
    public void testFindByAnswerOptionIdQMeException() throws QMeException {
        when(answerOptionMediaSpringDataRepositoryMOCK.findByOptionId(1L)).thenThrow(new RuntimeException("some error"));
        answerOptionRepositoryWithMock.findByAnswerOptionId(1L);
        verify(answerOptionMediaSpringDataRepositoryMOCK).findByOptionId(1L);
    }



    @Test(expected = QMeException.class)
    public void testFindAllQMeException() throws QMeException {
        when(answerOptionMediaSpringDataRepositoryMOCK.findAll()).thenThrow(new RuntimeException("some error"));
        answerOptionRepositoryWithMock.findAll();
        verify(answerOptionMediaSpringDataRepositoryMOCK).findAll();
    }


    @Test(expected = QMeException.class)
    public void testFindByIdQMeException() throws QMeException {
        when(answerOptionMediaSpringDataRepositoryMOCK.findOne(1L)).thenThrow(new RuntimeException("some error"));
        answerOptionRepositoryWithMock.findById(1L);
        verify(answerOptionMediaSpringDataRepositoryMOCK).findOne(1L);
    }


    @Test(expected = QMeException.class)
    public void testSaveQMeException() throws QMeException {
        when(answerOptionMediaSpringDataRepositoryMOCK.save(Matchers.<AnswerOptionMediaEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        answerOptionRepositoryWithMock.save(new AnswerOptionMedia(1L, 1, "testing".getBytes()));
        verify(answerOptionMediaSpringDataRepositoryMOCK).save(Matchers.<AnswerOptionMediaEntity>anyObject());
    }


    @Test(expected = QMeException.class)
    public void testUpdateQMeException() throws QMeException {
        when(answerOptionMediaSpringDataRepositoryMOCK.save(Matchers.<AnswerOptionMediaEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        answerOptionRepositoryWithMock.update(new AnswerOptionMedia(1L, 1, "testing".getBytes()), 1L);
        verify(answerOptionMediaSpringDataRepositoryMOCK).save(Matchers.<AnswerOptionMediaEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testDeleteQMeException() throws QMeException {
        doThrow(new RuntimeException("some error")).when(answerOptionMediaSpringDataRepositoryMOCK).delete(1L);
        answerOptionRepositoryWithMock.delete(1L);
        verify(answerOptionMediaSpringDataRepositoryMOCK).delete(1L);
    }
}
