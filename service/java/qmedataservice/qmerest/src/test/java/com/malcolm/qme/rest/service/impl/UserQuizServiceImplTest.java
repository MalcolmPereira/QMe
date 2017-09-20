/**
 * Name      : com.malcolm.qme.rest.service.impl.UserQuizServiceImplTest.java
 * Date      : 9/20/2017
 * Developer : Malcolm
 * Purpose   : Test Cases for User Quiz Service Implementation
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.repository.UserQuizRepository;
import com.malcolm.qme.rest.service.UserQuizService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class UserQuizServiceImplTest {
    @Mock
    private UserQuizRepository userQuizRepo;

    @InjectMocks
    private final UserQuizService userQuizService = new UserQuizServiceImpl();
}
