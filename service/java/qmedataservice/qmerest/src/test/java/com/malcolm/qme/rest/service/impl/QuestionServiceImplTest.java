/**
 * Name      : com.malcolm.qme.rest.service.impl.QuestionServiceImplTest.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : Test Cases for Question Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.rest.service.QuestionService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * @author Malcolm
 */
public class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepo;

    @InjectMocks
    private final QuestionService questionService = new QuestionServiceImpl();

    @Test
    public void testCount() throws Exception {

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