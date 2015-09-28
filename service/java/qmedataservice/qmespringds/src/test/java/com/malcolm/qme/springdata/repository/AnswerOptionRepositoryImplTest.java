/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerOptionRepositoryImplTest.java
 * Date      : May 17, 2015
 * Developer : Malcolm
 * Purpose   : Tests for AnswerOption Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.AnswerOption;
import com.malcolm.qme.core.domain.Question;
import com.malcolm.qme.core.repository.AnswerOptionRepository;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.AnswerOptionEntity;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class AnswerOptionRepositoryImplTest {

    /**
     * Answer Option Repository
     */
    @Autowired
    @Qualifier("AnswerOptionRepository")
    private AnswerOptionRepository answerOptionRepository;

    /**
     * Question Repository
     */
    @Autowired
    @Qualifier("QuestionRepository")
    private QuestionRepository questionRepository;


    @Mock
    private AnswerOptionSpringDataRepository answerOptionSpringDataRepositoryMOCK;

    @InjectMocks
    private AnswerOptionRepository answerOptionRepositoryWithMock;

    @Before
    public void initMocks(){
        answerOptionRepositoryWithMock = new AnswerOptionRepositoryImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws QMeException {
        assertNotNull(answerOptionRepository);
        final List<AnswerOption> answerOptions = answerOptionRepository.findAll();
        assertNotNull(answerOptions);
        assertThat(answerOptions.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(answerOptionRepository);
        final AnswerOption answerOption = answerOptionRepository.findById(1L);
        assertNotNull(answerOption);
        assertThat(answerOption.getAnswerOptionID(), equalTo(1L));
    }

    @Test
    public void testCRUD() throws QMeException {
        assertNotNull(answerOptionRepository);

        assertNotNull(questionRepository);

        Question question = new Question(1L, "QuestionRepositoryImplTest Question", "QuestionRepositoryImplTest Answer", 1L);

        question = questionRepository.save(question);
        assertNotNull(question);
        assertThat(question.getQuestionID(), greaterThan(0L));
        final Long questionID = question.getQuestionID();

        AnswerOption answerOption = new AnswerOption(questionID, "Option 1", false);
        answerOption = answerOptionRepository.save(answerOption);
        assertNotNull(answerOption);
        assertThat(answerOption.getAnswerOptionID(), greaterThan(0L));
        final Long answerOptionID = answerOption.getAnswerOptionID();

        answerOption = answerOptionRepository.findById(answerOptionID);
        assertNotNull(answerOption);
        assertThat(answerOption.getAnswerOptionID(), equalTo(answerOptionID));

        AnswerOption answerOptionUpdate = new AnswerOption(answerOption.getAnswerOptionID(), answerOption.getQuestionID(), "Option 1 Updated", true);
        answerOptionUpdate = answerOptionRepository.update(answerOptionUpdate,1L);
        assertNotNull(answerOptionUpdate);
        assertThat(answerOptionUpdate.getAnswerOptionID(), equalTo(answerOptionID));
        assertThat(answerOptionUpdate.getOptionText(), equalTo("Option 1 Updated"));

        answerOptionRepository.delete(answerOptionID);
        answerOption = answerOptionRepository.findById(answerOptionID);
        assertNull(answerOption);

        questionRepository.delete(questionID);
        question = questionRepository.findById(questionID);
        assertNull(question);
    }

    @Test
    public void testFindByQuestionId() throws QMeException {
        assertNotNull(answerOptionRepository);

        assertNotNull(questionRepository);

        Question question = new Question(1L, "QuestionRepositoryImplTest Question", "QuestionRepositoryImplTest Answer", 1L);

        question = questionRepository.save(question);
        assertNotNull(question);
        assertThat(question.getQuestionID(), greaterThan(0L));
        final Long questionID = question.getQuestionID();

        AnswerOption answerOption = new AnswerOption(questionID, "Option 1", false);
        answerOption = answerOptionRepository.save(answerOption);
        assertNotNull(answerOption);
        assertThat(answerOption.getAnswerOptionID(), greaterThan(0L));
        final Long answerOptionID = answerOption.getAnswerOptionID();

        answerOption = answerOptionRepository.findById(answerOptionID);
        assertNotNull(answerOption);
        assertThat(answerOption.getAnswerOptionID(), equalTo(answerOptionID));

        final List<AnswerOption> answerOptionList = answerOptionRepository.findByQuestionId(questionID);
        assertNotNull(answerOptionList);
        assertThat(answerOptionList.get(0).getAnswerOptionID(), equalTo(answerOptionID));
        assertThat(answerOptionList.get(0).getOptionText(), equalTo("Option 1"));

        AnswerOption answerOptionUpdate = new AnswerOption(answerOption.getAnswerOptionID(), answerOption.getQuestionID(), "Option 1 Updated", answerOption.isCorrect());
        answerOptionUpdate = answerOptionRepository.save(answerOptionUpdate);
        assertNotNull(answerOptionUpdate);
        assertThat(answerOptionUpdate.getAnswerOptionID(), equalTo(answerOptionID));
        assertThat(answerOptionUpdate.getOptionText(), equalTo("Option 1 Updated"));

        answerOptionRepository.delete(answerOptionID);
        answerOption = answerOptionRepository.findById(answerOptionID);
        assertNull(answerOption);

        questionRepository.delete(questionID);
        question = questionRepository.findById(questionID);
        assertNull(question);
    }

    @Test
    public void testFindByQuestionIdNullReturn() throws QMeException {
        when(answerOptionSpringDataRepositoryMOCK.findByQuestionId(1L)).thenReturn(null);
        List<AnswerOption> answerOptionMediaList = answerOptionRepositoryWithMock.findByQuestionId(1L);
        verify(answerOptionSpringDataRepositoryMOCK).findByQuestionId(1L);
        assertNotNull(answerOptionMediaList);
        assertThat(answerOptionMediaList.size(), equalTo(0));
    }

    @Test(expected = QMeException.class)
    public void testFindByQuestionIdQMeException() throws QMeException {
        when(answerOptionSpringDataRepositoryMOCK.findByQuestionId(1L)).thenThrow(new RuntimeException("some error"));
        answerOptionRepositoryWithMock.findByQuestionId(1L);
        verify(answerOptionSpringDataRepositoryMOCK).findByQuestionId(1L);
    }

    @Test(expected = QMeException.class)
    public void testFindAllQMeException() throws QMeException {
        when(answerOptionSpringDataRepositoryMOCK.findAll()).thenThrow(new RuntimeException("some error"));
        answerOptionRepositoryWithMock.findAll();
        verify(answerOptionSpringDataRepositoryMOCK).findAll();
    }

    @Test(expected = QMeException.class)
    public void testFindByIdQMeException() throws QMeException {
        when(answerOptionSpringDataRepositoryMOCK.findOne(1L)).thenThrow(new RuntimeException("some error"));
        answerOptionRepositoryWithMock.findById(1L);
        verify(answerOptionSpringDataRepositoryMOCK).findOne(1L);
    }

    @Test(expected = QMeException.class)
    public void testSaveQMeException() throws QMeException {
        when(answerOptionSpringDataRepositoryMOCK.save(Matchers.<AnswerOptionEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        answerOptionRepositoryWithMock.save(new AnswerOption(1L, "Option 1", false));
        verify(answerOptionSpringDataRepositoryMOCK).save(Matchers.<AnswerOptionEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testUpdateQMeException() throws QMeException {
        when(answerOptionSpringDataRepositoryMOCK.save(Matchers.<AnswerOptionEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        answerOptionRepositoryWithMock.update(new AnswerOption(1L, "Option 1", false), 1L);
        verify(answerOptionSpringDataRepositoryMOCK).save(Matchers.<AnswerOptionEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testDeleteQMeException() throws QMeException {
        doThrow(new RuntimeException("some error")).when(answerOptionSpringDataRepositoryMOCK).delete(1L);
        answerOptionRepositoryWithMock.delete(1L);
        verify(answerOptionSpringDataRepositoryMOCK).delete(1L);
    }
}
