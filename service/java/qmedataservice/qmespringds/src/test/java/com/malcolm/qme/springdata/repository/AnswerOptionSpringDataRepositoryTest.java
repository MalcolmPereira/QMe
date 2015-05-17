/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerOptionSpringDataRepositoryTest.java
 * Date      : 5/11/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA AnswerOptionEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
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
import com.malcolm.qme.springdata.entity.AnswerOptionEntity;

/**
 * @Author: malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class
})
public class AnswerOptionSpringDataRepositoryTest {

	/**
	 * AnswerOptionEntity Repository
	 */
	@Autowired
	private AnswerOptionSpringDataRepository answerOptionSpringDataRepo;

	@Test
	public void testFindAll(){
		assertNotNull(answerOptionSpringDataRepo);
		final List<AnswerOptionEntity> answerOptionEntities = answerOptionSpringDataRepo.findAll();
		assertNotNull(answerOptionEntities);
		assertThat(answerOptionEntities.size(), greaterThan(0));
	}

	@Test
	public void testFindById(){
		assertNotNull(answerOptionSpringDataRepo);
		final AnswerOptionEntity answerOptionEntity = answerOptionSpringDataRepo.findOne(1L);
		assertNotNull(answerOptionEntity);
		assertThat(answerOptionEntity.getOptionId(), equalTo(1L));
	}

}
