/**
 * Name      : com.malcolm.qme.rest.service.impl.QuizServiceImplTest.java
 * Date      : 6/26/2017
 * Developer : Malcolm
 * Purpose   : Test Cases for Quiz Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.fixtures.CategoryFixtures;
import com.malcolm.qme.core.domain.fixtures.QuizFixtures;
import com.malcolm.qme.core.domain.fixtures.QuizQuestionFixtures;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuizQuestionRepository;
import com.malcolm.qme.core.repository.QuizRepository;
import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeQuizDetail;
import com.malcolm.qme.rest.model.fixtures.QMeQuestionDetailFixture;
import com.malcolm.qme.rest.model.fixtures.QMeQuizDetailFixture;
import com.malcolm.qme.rest.service.QuestionService;
import com.malcolm.qme.rest.service.QuizService;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QuizServiceImplTest {
    @Mock
    private QuizRepository quizRepo;

    @Mock
    private QuizQuestionRepository quizQuestionRepo;

    @Mock
    private QuestionService questionService;

    @Mock
    private CategoryRepository categoryRepo;

    @InjectMocks
    private final QuizService quizService = new QuizServiceImpl();

    @Test
    public void testCount() throws Exception {
        MatcherAssert.assertThat(quizRepo, notNullValue());
        MatcherAssert.assertThat(quizService, notNullValue());
        when(quizService.count()).thenReturn(10L);
        Long quizCount = quizService.count();
        assertNotNull(quizCount);
        assertThat(quizCount, equalTo(10L));
    }

    @Test(expected = QMeServerException.class)
    public void testCountQMeServerException() throws Exception {
        MatcherAssert.assertThat(quizRepo, notNullValue());
        MatcherAssert.assertThat(quizService, notNullValue());
        when(quizRepo.count()).thenThrow(QMeException.class);
        quizService.count();
        verify(quizService).count();
    }

    @Test
    public void testList() throws Exception {
        MatcherAssert.assertThat(quizRepo, notNullValue());
        MatcherAssert.assertThat(quizService, notNullValue());

        when(quizRepo.findAll()).thenReturn(QuizFixtures.simpleQuizList());

        List<QMeQuizDetail> quizList = quizService.list();

        verify(quizRepo).findAll();
        assertNotNull(quizList);
        assertThat(quizList.size(), equalTo(5));
        for (QMeQuizDetail qmeQuiz : quizList) {
            assertThat(qmeQuiz.getQuizID(), anyOf(
                    is(1L),
                    is(2L),
                    is(3L),
                    is(4L),
                    is(5L))
            );
            assertThat(qmeQuiz.getQuizName(), anyOf(
                    is("Some quiz - 1"),
                    is("Some quiz - 2"),
                    is("Some quiz - 3"),
                    is("Some quiz - 4"),
                    is("Some quiz - 5")
            ));
        }
    }

    @Test
    public void testListNullReturn() throws QMeResourceException, QMeException {
        when(quizRepo.findAll()).thenReturn(null);
        List<QMeQuizDetail> quizList = quizService.list();
        verify(quizRepo).findAll();
        assertNotNull(quizList);
        assertThat(quizList.size(), equalTo(0));
    }


    @Test(expected = QMeServerException.class)
    public void testListQMeException() throws Exception {
        when(quizRepo.findAll()).thenThrow(QMeException.class);
        quizService.list();
    }

    @Test
    public void testListWithPaging() throws Exception {
        when(quizRepo.findAll(Matchers.anyObject())).thenReturn(QuizFixtures.simpleQuizList());
        List<QMeQuizDetail> questionList = quizService.list(0, 5, true, "Quiz Text");
        verify(quizRepo).findAll(Matchers.anyObject());
        assertNotNull(questionList);
        assertThat(questionList.size(), equalTo(5));
        for (QMeQuizDetail qmeQuiz : questionList) {
            assertThat(qmeQuiz.getQuizID(), anyOf(
                    is(1L),
                    is(2L),
                    is(3L),
                    is(4L),
                    is(5L))
            );
            assertThat(qmeQuiz.getQuizName(), anyOf(
                    is("Some quiz - 1"),
                    is("Some quiz - 2"),
                    is("Some quiz - 3"),
                    is("Some quiz - 4"),
                    is("Some quiz - 5")
            ));
        }
    }

    @Test
    public void testListWithPagingNullReturn() throws QMeResourceException, QMeException {
        when(quizRepo.findAll(Matchers.anyObject())).thenReturn(null);
        List<QMeQuizDetail> quizList = quizService.list(0, 5, true, "Quiz Text");
        verify(quizRepo).findAll(Matchers.anyObject());
        assertNotNull(quizList);
        assertThat(quizList.size(), equalTo(0));
    }

    @Test(expected = QMeServerException.class)
    public void testListWithPagingQMeException() throws Exception {
        when(quizRepo.findAll(Matchers.anyObject())).thenThrow(QMeException.class);
        quizService.list(0, 5, true, "Quiz Text");
    }

    @Test
    public void testSearchById() throws Exception {
        MatcherAssert.assertThat(quizRepo, notNullValue());
        MatcherAssert.assertThat(quizQuestionRepo, notNullValue());
        MatcherAssert.assertThat(quizService, notNullValue());
        MatcherAssert.assertThat(questionService, notNullValue());

        when(quizRepo.findById(1L)).thenReturn(QuizFixtures.simpleQuiz());
        when(quizQuestionRepo.findByQuizId(1L)).thenReturn(QuizFixtures.simpleQuizQuestionList());
        when(questionService.searchById(1L)).thenReturn(QMeQuestionDetailFixture.simpleQMeQuestionDetail());

        QMeQuizDetail quizDetail = quizService.searchById(1L);
        verify(quizRepo).findById(1L);
        assertNotNull(quizDetail);
        assertThat(quizDetail.getQuizID(), equalTo(1L));
        assertThat(quizDetail.getQuizName(), equalTo("Some simple quiz"));
    }

    @Test
    public void testSave() throws Exception {
        QMeQuizDetail qmeQuiz = QMeQuizDetailFixture.qMeQuizDetailWithQuestions();

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(quizRepo.save(Matchers.anyObject())).thenReturn(QuizFixtures.simpleQuiz());
        when(quizQuestionRepo.save(Matchers.anyObject())).thenReturn(QuizQuestionFixtures.simpleQuizQuestion());

        qmeQuiz = quizService.save(qmeQuiz, 1L);

        verify(categoryRepo).findById(1L);
        verify(quizRepo).save(Matchers.anyObject());
        verify(quizQuestionRepo, times(5)).save(Matchers.anyObject());

        assertNotNull(qmeQuiz);
        assertThat(qmeQuiz.getQuizID(), equalTo(1L));
        assertThat(qmeQuiz.getQuizName(), equalTo("Some Quiz Name"));
    }

    @Test(expected = QMeServerException.class)
    public void testSaveQMeServerException() throws Exception {
        QMeQuizDetail qmeQuiz = QMeQuizDetailFixture.qMeQuizDetailWithQuestions();

        when(categoryRepo.findById(1L)).thenReturn(CategoryFixtures.simpleCategory());
        when(quizRepo.save(Matchers.anyObject())).thenThrow(QMeException.class);

        quizService.save(qmeQuiz, 1L);

        verify(categoryRepo).findById(1L);
        verify(quizRepo).save(Matchers.anyObject());
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidQuizName() throws Exception {
        QMeQuizDetail qmeQuiz = QMeQuizDetailFixture.simpleQMeQuizDetail();
        qmeQuiz.setQuizName(null);
        quizService.save(qmeQuiz, 1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidQuizDesc() throws Exception {
        QMeQuizDetail qmeQuiz = QMeQuizDetailFixture.simpleQMeQuizDetail();
        qmeQuiz.setQuizDesc(null);
        quizService.save(qmeQuiz, 1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidCategory() throws Exception {
        QMeQuizDetail qmeQuiz = QMeQuizDetailFixture.simpleQMeQuizDetail();
        qmeQuiz.setCategoryID(null);
        quizService.save(qmeQuiz, 1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidCategoryNotFound() throws Exception {
        QMeQuizDetail qmeQuiz = QMeQuizDetailFixture.qMeQuizDetailWithQuestions();
        when(categoryRepo.findById(1L)).thenReturn(null);
        quizService.save(qmeQuiz, 1L);
        verify(categoryRepo).findById(1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidQuizAttempts() throws Exception {
        QMeQuizDetail qmeQuiz = QMeQuizDetailFixture.simpleQMeQuizDetail();
        qmeQuiz.setQuizMaxAttempts(null);
        quizService.save(qmeQuiz, 1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidQuizQuestion() throws Exception {
        QMeQuizDetail qmeQuiz = QMeQuizDetailFixture.simpleQMeQuizDetail();
        quizService.save(qmeQuiz, 1L);
    }

    @Test
    public void testDelete() throws Exception {
        when(quizRepo.findById(1L)).thenReturn(QuizFixtures.simpleQuiz());
        doNothing().when(quizRepo).delete(1L);
        quizService.delete(1L);
        verify(quizRepo).findById(1L);
        verify(quizRepo).delete(1L);
    }
}
