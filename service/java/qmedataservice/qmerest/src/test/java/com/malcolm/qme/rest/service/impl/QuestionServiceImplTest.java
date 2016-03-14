/**
 * Name      : com.malcolm.qme.rest.service.impl.QuestionServiceImplTest.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : Test Cases for Question Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.fixtures.QuestionFixtures;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeQuestionDetail;
import com.malcolm.qme.rest.service.QuestionService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepo;

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
    public void testSearchById() throws Exception {
        when(questionRepo.findById(1L)).thenReturn(QuestionFixtures.simpleQuestion());
        QMeQuestionDetail questionDetail = questionService.searchById(1L);

        verify(questionRepo).findById(1L);
        assertNotNull(questionDetail);
        assertThat(questionDetail.getQuestionId(), equalTo(1L));
        assertThat(questionDetail.getQuestionText(), equalTo("Some question text"));
    }

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }
}