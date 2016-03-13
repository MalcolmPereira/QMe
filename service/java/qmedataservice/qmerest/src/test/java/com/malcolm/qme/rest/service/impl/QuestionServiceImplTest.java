/**
 * Name      : com.malcolm.qme.rest.service.impl.QuestionServiceImplTest.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : Test Cases for Question Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.rest.service.QuestionService;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
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

    }

    @Test
    public void testList1() throws Exception {

    }

    @Test
    public void testSearchById() throws Exception {

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