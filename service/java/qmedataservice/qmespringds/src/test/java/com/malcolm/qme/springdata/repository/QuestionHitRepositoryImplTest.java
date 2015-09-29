/**
 * Name      : com.malcolm.qme.springdata.repository.QuestionHitRepositoryImplTest.java
 * Date      : 5/14/15
 * Developer : Malcolm
 * Purpose   : Tests for QuestionHit Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.domain.Question;
import com.malcolm.qme.core.domain.QuestionHit;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuestionHitRepository;
import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.QuestionHitEntity;
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
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class QuestionHitRepositoryImplTest {

    /**
     * Question Hit Repository
     */
    @Autowired
    @Qualifier("QuestionHitRepository")
    private QuestionHitRepository questionHitRepository;

    /**
     * Question Repository
     */
    @Autowired
    @Qualifier("QuestionRepository")
    private QuestionRepository questionRepository;

    /**
     * Category Repository
     */
    @Autowired
    @Qualifier("CategoryRepository")
    private CategoryRepository categoryRepo;

    @Mock
    private QuestionHitSpringDataRepository questionHitSpringDataRepositoryMOCK;

    @InjectMocks
    private QuestionHitRepository questionHitRepositoryWithMock;

    @Before
    public void initMocks(){
        questionHitRepositoryWithMock = new QuestionHitRepositoryImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws QMeException {
        assertNotNull(questionHitRepository);
        List<QuestionHit> questionHitList = questionHitRepository.findAll();
        assertNotNull(questionHitList);
        assertThat(questionHitList.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(questionHitRepository);
        QuestionHit questionHit = questionHitRepository.findById(1L);
        assertNotNull(questionHit);
        assertThat(questionHit.getQuestionID(), equalTo(1L));
        assertThat(questionHit.getCategoryID(), equalTo(1L));
    }

    @Test
    public void testCRUD() throws QMeException {

        assertNotNull(questionHitRepository);

        assertNotNull(categoryRepo);

        assertNotNull(questionRepository);

        Category category = new Category("QuestionHitRepositoryImplTest", 1L);
        category = categoryRepo.save(category);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));
        Long catID = category.getCategoryID();

        Question question = new Question(catID, "QuestionHitRepositoryImplTest Question1", "QuestionHitRepositoryImplTest Answer", 1L);
        question = questionRepository.save(question);
        assertNotNull(question);
        assertThat(question.getQuestionID(), greaterThan(0L));
        Long questionID = question.getQuestionID();

        QuestionHit questionHit = new QuestionHit(questionID, catID, 0L, 0L, 0L);
        questionHit = questionHitRepository.save(questionHit);
        assertNotNull(questionHit);
        assertThat(questionHit.getQuestionID(), equalTo(questionID));
        assertThat(questionHit.getCategoryID(), equalTo(catID));

        questionHit = questionHitRepository.findById(questionID);
        assertNotNull(questionHit);
        assertThat(questionHit.getQuestionID(), equalTo(questionID));
        assertThat(questionHit.getCategoryID(), equalTo(catID));

        QuestionHit questionHitUpdate = new QuestionHit(questionHit.getQuestionID(), questionHit.getCategoryID(), 1L, 1L, 0L);
        questionHit = questionHitRepository.update(questionHitUpdate,1L);
        assertNotNull(questionHit);
        assertThat(questionHit.getQuestionID(), equalTo(questionID));
        assertThat(questionHit.getCategoryID(), equalTo(catID));
        assertThat(questionHit.getQuestionHit(), equalTo(1L));
        assertThat(questionHit.getRightCount(), equalTo(1L));

        questionHitRepository.delete(questionID);
        questionHit = questionHitRepository.findById(questionID);
        assertNull(questionHit);

        questionRepository.delete(questionID);
        question = questionRepository.findById(questionID);
        assertNull(question);

        categoryRepo.delete(catID);
        category = categoryRepo.findById(catID);
        assertNull(category);
    }

    @Test
    public void testFindAllNullReturn() throws QMeException {
        when(questionHitSpringDataRepositoryMOCK.findAll()).thenReturn(null);
        List<QuestionHit> questionHitList = questionHitRepositoryWithMock.findAll();
        verify(questionHitSpringDataRepositoryMOCK).findAll();
        assertNotNull(questionHitList);
        assertThat(questionHitList.size(), equalTo(0));
    }

    @Test(expected = QMeException.class)
    public void testFindAllQMeException() throws QMeException {
        when(questionHitSpringDataRepositoryMOCK.findAll()).thenThrow(new RuntimeException("some error"));
        questionHitRepositoryWithMock.findAll();
        verify(questionHitSpringDataRepositoryMOCK).findAll();
    }

    @Test(expected = QMeException.class)
    public void testFindByIDQMeException() throws QMeException {
        when(questionHitSpringDataRepositoryMOCK.findOne(1L)).thenThrow(new RuntimeException("some error"));
        questionHitRepositoryWithMock.findById(1L);
        verify(questionHitSpringDataRepositoryMOCK).findOne(1L);
    }

    @Test(expected = QMeException.class)
    public void testSaveQMeException() throws QMeException {
        when(questionHitSpringDataRepositoryMOCK.save(Matchers.<QuestionHitEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        questionHitRepositoryWithMock.save(new QuestionHit(1L, 1L, 0L, 0L, 0L));
        verify(questionHitSpringDataRepositoryMOCK).save(Matchers.<QuestionHitEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testUpdateQMeException() throws QMeException {
        when(questionHitSpringDataRepositoryMOCK.save(Matchers.<QuestionHitEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        questionHitRepositoryWithMock.update(new QuestionHit(1L, 1L, 0L, 0L, 0L), 1L);
        verify(questionHitSpringDataRepositoryMOCK).save(Matchers.<QuestionHitEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testDeleteQMeException() throws QMeException {
        doThrow(new RuntimeException("some error")).when(questionHitSpringDataRepositoryMOCK).delete(1L);
        questionHitRepositoryWithMock.delete(1L);
        verify(questionHitSpringDataRepositoryMOCK).delete(1L);
    }
}
