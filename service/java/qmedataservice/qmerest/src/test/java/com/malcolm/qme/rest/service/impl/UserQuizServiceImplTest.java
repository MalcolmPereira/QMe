/**
 * Name      : com.malcolm.qme.rest.service.impl.UserQuizServiceImplTest.java
 * Date      : 9/20/2017
 * Developer : Malcolm
 * Purpose   : Test Cases for User Quiz Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.fixtures.UserQuizFixtures;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserQuizRepository;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeUserQuiz;
import com.malcolm.qme.rest.service.UserQuizService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.core.Is.is;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class UserQuizServiceImplTest {
    @Mock
    private UserQuizRepository userQuizRepo;

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

        List<QMeUserQuiz> userQuizList = userQuizService.list();

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

        List<QMeUserQuiz> userQuizList = userQuizService.list(0,10,true, "QUIZNAME");

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
}
