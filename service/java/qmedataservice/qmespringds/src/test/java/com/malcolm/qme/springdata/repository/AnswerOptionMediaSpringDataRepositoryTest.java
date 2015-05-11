/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerOptionMediaSpringDataRepositoryTest.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA AnswerOptionMediaEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.AnswerOptionMediaEntity;

/**
 * @author Malcolm
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class AnswerOptionMediaSpringDataRepositoryTest {
	
	/**
     * AnswerOptionMediaEntity Repository
     */
    @Autowired
    private AnswerOptionMediaSpringDataRepository answerOptionMediaSpringDataRepository;
    
    @Test
    public void testFetchAll(){
        assertNotNull(answerOptionMediaSpringDataRepository);
        List<AnswerOptionMediaEntity> userEntities = answerOptionMediaSpringDataRepository.findAll();
        assertNotNull(userEntities);
        assertThat(userEntities.size(), greaterThan(0));
    }


}
