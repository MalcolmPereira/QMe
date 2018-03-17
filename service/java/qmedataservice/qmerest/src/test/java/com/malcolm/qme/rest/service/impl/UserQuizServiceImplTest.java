/**
 * Name      : com.malcolm.qme.rest.service.impl.UserQuizServiceImplTest.java
 * Date      : 9/20/2017
 * Developer : Malcolm
 * Purpose   : Test Cases for User Quiz Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.UserQuiz;
import com.malcolm.qme.core.domain.fixtures.UserQuizFixtures;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserQuizRepository;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeUserQuiz;
import com.malcolm.qme.rest.model.QMeUserQuizDetail;
import com.malcolm.qme.rest.model.fixtures.QMeQuizDetailFixture;
import com.malcolm.qme.rest.model.fixtures.QMeUserQuizFixture;
import com.malcolm.qme.rest.service.QuizService;
import com.malcolm.qme.rest.service.UserQuizService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class UserQuizServiceImplTest {
    @Mock
    private UserQuizRepository userQuizRepo;

    @Mock
    private QuizService quizService;

    @InjectMocks
    private final UserQuizService userQuizService = new UserQuizServiceImpl();

    @Test
    public void testCount() throws QMeResourceException, QMeException {
        when(userQuizRepo.count()).thenReturn(10L);
        Long userQuizCount = userQuizService.count();
        verify(userQuizRepo).count();
        assertNotNull(userQuizCount);
        assertThat(userQuizCount, equalTo(10L));
    }

    @Test(expected = QMeServerException.class)
    public void testCountQMeServerException() throws QMeResourceException, QMeException {
        when(userQuizRepo.count()).thenThrow(QMeException.class);
        userQuizService.count();
        verify(userQuizRepo).count();
    }


    @Test
    public void testList() throws QMeResourceException, QMeException {
        when(userQuizRepo.findAll()).thenReturn(UserQuizFixtures.simpleUserQuizList());

        List<QMeUserQuizDetail> userQuizList = userQuizService.list();

        verify(userQuizRepo).findAll();

        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(5));

        for (QMeUserQuiz qmeUserQuiz : userQuizList) {
            assertThat(qmeUserQuiz.getUserID(), equalTo(1L));
            assertThat(qmeUserQuiz.getUserQuizID(), anyOf(
                    is(1L),
                    is(2L),
                    is(3L),
                    is(4L),
                    is(5L)
            ));
        }
    }

    @Test
    public void testListPaged() throws QMeResourceException, QMeException {
        when(userQuizRepo.findAll(Matchers.<PageSort>anyObject())).thenReturn(UserQuizFixtures.simpleUserQuizList());

        List<QMeUserQuizDetail> userQuizList = userQuizService.list(0,10,true, "QUIZNAME");

        verify(userQuizRepo).findAll(Matchers.<PageSort>anyObject());
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(5));
        for (QMeUserQuiz qmeUserQuiz : userQuizList) {
            assertThat(qmeUserQuiz.getUserID(), equalTo(1L));
            assertThat(qmeUserQuiz.getUserQuizID(), anyOf(
                    is(1L),
                    is(2L),
                    is(3L),
                    is(4L),
                    is(5L)
            ));
        }
    }

    @Test(expected = QMeServerException.class)
    public void testListPagedQMeServerException() throws QMeResourceException, QMeException {
        when(userQuizRepo.findAll(Matchers.<PageSort>anyObject())).thenThrow(QMeException.class);
        List<QMeUserQuizDetail> userQuizList = userQuizService.list(0,10,true, "QUIZNAME");
        verify(userQuizRepo).findAll(Matchers.<PageSort>anyObject());
    }

    @Test
    public void testListNullReturn() throws QMeResourceException, QMeException{
        when(userQuizRepo.findAll()).thenReturn(null);
        List<QMeUserQuizDetail> userQuizList = userQuizService.list();
        verify(userQuizRepo).findAll();
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(0));
    }

    @Test(expected = QMeServerException.class)
    public void testListQMeException() throws QMeResourceException, QMeException{
        when(userQuizRepo.findAll()).thenThrow(QMeException.class);
        userQuizService.list();
        verify(userQuizRepo).findAll();
    }

    @Test
    public void testSearchById() throws QMeResourceException, QMeException {
        when(userQuizRepo.findById(1L)).thenReturn(UserQuizFixtures.simpleUserQuiz());

        when(quizService.searchById(1L)).thenReturn(QMeQuizDetailFixture.qMeQuizDetailWithQuestions());

        QMeUserQuiz userQuiz = userQuizService.searchById(1L);

        verify(userQuizRepo).findById(1L);

        assertNotNull(userQuiz);

        assertThat(userQuiz.getUserQuizID(), equalTo(1L));
    }

    @Test
    public void testFindQuizzesForUser() throws QMeResourceException, QMeException {
        when(userQuizRepo.findQuizzesForUser(eq(1L),any(PageSort.class))).thenReturn(UserQuizFixtures.simpleUserQuizList());
        List<QMeUserQuizDetail> userQuizList = userQuizService.findQuizzesForUser(1L,0,10,true, "MAXSCORE");
        verify(userQuizRepo).findQuizzesForUser(eq(1L),any(PageSort.class));
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(5));
        for (QMeUserQuiz qmeUserQuiz : userQuizList) {
            assertThat(qmeUserQuiz.getUserID(), equalTo(1L));
            assertThat(qmeUserQuiz.getUserQuizID(), anyOf(
                    is(1L),
                    is(2L),
                    is(3L),
                    is(4L),
                    is(5L)
            ));
        }
    }

    @Test
    public void testFindCompletedByUserId() throws QMeResourceException, QMeException {
        when(userQuizRepo.findCompletedByUserId(eq(1L),any(PageSort.class))).thenReturn(UserQuizFixtures.simpleUserQuizList());
        List<QMeUserQuizDetail> userQuizList = userQuizService.findCompletedByUserId(1L,0,10,true, "MAXSCORE");
        verify(userQuizRepo).findCompletedByUserId(eq(1L),any(PageSort.class));
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(5));
        for (QMeUserQuiz qmeUserQuiz : userQuizList) {
            assertThat(qmeUserQuiz.getUserID(), equalTo(1L));
            assertThat(qmeUserQuiz.getUserQuizID(), anyOf(
                    is(1L),
                    is(2L),
                    is(3L),
                    is(4L),
                    is(5L)
            ));
        }
    }

    @Test
    public void testFindPendingByUserId() throws QMeResourceException, QMeException {
        when(userQuizRepo.findPendingByUserId(eq(1L),any(PageSort.class))).thenReturn(UserQuizFixtures.simpleUserQuizList());
        List<QMeUserQuizDetail> userQuizList = userQuizService.findPendingByUserId(1L,0,10,true, "MAXSCORE");
        verify(userQuizRepo).findPendingByUserId(eq(1L),any(PageSort.class));
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(5));
        for (QMeUserQuiz qmeUserQuiz : userQuizList) {
            assertThat(qmeUserQuiz.getUserID(), equalTo(1L));
            assertThat(qmeUserQuiz.getUserQuizID(), anyOf(
                    is(1L),
                    is(2L),
                    is(3L),
                    is(4L),
                    is(5L)
            ));
        }
    }

    @Test
    public void testFindPendingByUserIdAndQuizID() throws QMeResourceException, QMeException {
        when(quizService.searchById(1L)).thenReturn(QMeQuizDetailFixture.qMeQuizDetailWithQuestions());
        when(userQuizRepo.findPendingForUserByQuizId(eq(1L),eq(1L))).thenReturn(false);
        assertFalse(userQuizService.findPendingForUserByQuizId(1L, 1L));
        verify(userQuizRepo).findPendingForUserByQuizId(eq(1L),eq(1L));

        when(quizService.searchById(1L)).thenReturn(QMeQuizDetailFixture.qMeQuizDetailWithQuestions());
        when(userQuizRepo.findPendingForUserByQuizId(eq(1L),eq(1L))).thenReturn(true);
        assertTrue(userQuizService.findPendingForUserByQuizId(1L, 1L));
        verify(userQuizRepo, times(2)).findPendingForUserByQuizId(eq(1L),eq(1L));
    }

    @Test
    public void testStartQuiz() throws QMeResourceException, QMeException {
        when(userQuizRepo.findById(1L)).thenReturn(UserQuizFixtures.simpleUserQuiz());
        when(quizService.searchById(1L)).thenReturn(QMeQuizDetailFixture.qMeQuizDetailWithQuestionsIdsAndDetails());
        when(userQuizRepo.update(any(UserQuiz.class),eq(1L))).thenReturn(UserQuizFixtures.simpleUserQuiz());
        QMeUserQuizDetail qMeUserQuizDetail = userQuizService.startQuiz(1L,1L);
        verify(userQuizRepo).findById(1L);
        verify(quizService, times(2)).searchById(1L);
        verify(userQuizRepo).update(any(UserQuiz.class),eq(1L));
        assertNotNull(qMeUserQuizDetail);
        assertNotNull(qMeUserQuizDetail.getUserQuizToken());
        assertNotNull(qMeUserQuizDetail.getQuizMaxScore());
        assertTrue(qMeUserQuizDetail.getQuizMaxScore() == 2);
        assertNotNull(qMeUserQuizDetail.getQuizMaxAttempts());
    }

    @Test
    public void testCompleteQuiz() throws QMeResourceException, QMeException {
        when(userQuizRepo.findById(1L)).thenReturn(UserQuizFixtures.simpleUserQuiz());
        when(quizService.searchById(1L)).thenReturn(QMeQuizDetailFixture.qMeQuizDetailWithQuestionsIdsAndDetails());
        when(userQuizRepo.update(any(UserQuiz.class),eq(1L))).thenReturn(UserQuizFixtures.simpleUserQuiz());
        QMeUserQuizDetail userQuizDetail = userQuizService.completeQuiz(1L,1L, QMeUserQuizFixture.getQMeUserQuizDetailWithQuestions());
        verify(userQuizRepo).findById(1L);
        verify(quizService,times(3)).searchById(1L);
        verify(userQuizRepo).update(any(UserQuiz.class),eq(1L));
        assertNotNull(userQuizDetail);
        assertNotNull(userQuizDetail.getUserQuizToken());
        assertNotNull(userQuizDetail.getQuizMaxScore());
        assertNotNull(userQuizDetail.getQuizUserScore());
        assertTrue(userQuizDetail.getQuizUserScore() == 2);
    }
}
