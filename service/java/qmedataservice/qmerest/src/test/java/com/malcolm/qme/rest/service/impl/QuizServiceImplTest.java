/**
 * Name      : com.malcolm.qme.rest.service.impl.QuizServiceImplTest.java
 * Date      : 6/26/2017
 * Developer : Malcolm
 * Purpose   : Test Cases for Quiz Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.fixtures.QuizFixtures;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuizQuestionRepository;
import com.malcolm.qme.core.repository.QuizRepository;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeQuizDetail;
import com.malcolm.qme.rest.model.fixtures.QMeQuestionDetailFixture;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void testDelete() throws Exception {
        when(quizRepo.findById(1L)).thenReturn(QuizFixtures.simpleQuiz());
        doNothing().when(quizRepo).delete(1L);
        quizService.delete(1L);
        verify(quizRepo).findById(1L);
        verify(quizRepo).delete(1L);
    }
}
