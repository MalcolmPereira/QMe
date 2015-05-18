/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizGameSpringDataRepositoryTest.java
 * Date      : 5/18/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA UserQuizGameEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;

/**
 * @Author: malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class UserQuizGameSpringDataRepositoryTest {
	/**
     * UserEntity Repository
     */
    @Autowired
    private UserSpringDataRepository userSpringDataRepo;
    
    /**
     * UserQuizGameEntity Repository
     */
    @Autowired
    private UserQuizGameSpringDataRepository userQuizGameSpringDataRepository;

}
