/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerOptionMediaSpringDataRepositoryTest.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA AnswerOptionMediaEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Date;
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
import com.malcolm.qme.springdata.entity.AnswerOptionMediaEntity;
import com.malcolm.qme.springdata.entity.QuestionEntity;

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

	/**
	 * AnswerOptionEntity Repository
	 */
	@Autowired
	private AnswerOptionSpringDataRepository answerOptionSpringDataRepo;

	/**
	 * QuestionEntity Repository
	 */
	@Autowired
	private QuestionSpringDataRepository questionSpringDataRepository;

	@Test
	public void testFetchAll(){
		assertNotNull(answerOptionMediaSpringDataRepository);
		final List<AnswerOptionMediaEntity> answerOptionMediaEntities = answerOptionMediaSpringDataRepository.findAll();
		assertNotNull(answerOptionMediaEntities);
		assertThat(answerOptionMediaEntities.size(), greaterThan(0));
	}

	@Test
	public void testFindById(){
		assertNotNull(answerOptionMediaSpringDataRepository);
		final AnswerOptionMediaEntity answerOptionMediaEntity = answerOptionMediaSpringDataRepository.findOne(1L);
		assertNotNull(answerOptionMediaEntity);
		assertThat(answerOptionMediaEntity.getOptionMediaId(), equalTo(1L));
	}

	@Test
	public void testCRUD(){

		assertNotNull(answerOptionSpringDataRepo);

		assertNotNull(questionSpringDataRepository);

		assertNotNull(answerOptionMediaSpringDataRepository);

		QuestionEntity questionEntity = new QuestionEntity(1L,
				"AnswerOptionMediaSpringDataRepositoryTest Question",
				"AnswerOptionMediaSpringDataRepositoryTest Answer",
				1,
				0L,
				new Date(),
				1L,
				new Date(),
				1L);
		questionEntity = questionSpringDataRepository.save(questionEntity);
		assertNotNull(questionEntity);
		assertThat(questionEntity.getQuestionId(), greaterThan(0L));
		final Long questionID = questionEntity.getQuestionId();

		AnswerOptionEntity answerOptionEntity = new AnswerOptionEntity(questionID, "Option 1", (byte) 0);
		answerOptionEntity = answerOptionSpringDataRepo.save(answerOptionEntity);
		assertNotNull(answerOptionEntity);
		assertThat(answerOptionEntity.getOptionId(), greaterThan(0L));
		final Long answerOptionID  = answerOptionEntity.getOptionId();

		AnswerOptionMediaEntity answerOptionMediaEntity = new AnswerOptionMediaEntity(answerOptionID,1,new String("testing").getBytes());
		answerOptionMediaEntity = answerOptionMediaSpringDataRepository.save(answerOptionMediaEntity);
		assertNotNull(answerOptionMediaEntity);
		assertThat(answerOptionMediaEntity.getOptionMediaId(), greaterThan(0L));
		final Long answerOptionMediaID  = answerOptionMediaEntity.getOptionMediaId();

		answerOptionMediaEntity = answerOptionMediaSpringDataRepository.findOne(answerOptionMediaID);
		assertNotNull(answerOptionMediaEntity);
		assertThat(answerOptionMediaEntity.getOptionMediaId(), equalTo(answerOptionMediaID));
		assertThat(new String(answerOptionMediaEntity.getOptionMedia()), equalTo("testing"));

		answerOptionMediaEntity.setOptionMedia(new String("testing updates").getBytes());
		answerOptionMediaEntity = answerOptionMediaSpringDataRepository.save(answerOptionMediaEntity);
		assertNotNull(answerOptionMediaEntity);
		assertThat(answerOptionMediaEntity.getOptionMediaId(), equalTo(answerOptionMediaID));

		answerOptionMediaEntity = answerOptionMediaSpringDataRepository.findOne(answerOptionMediaID);
		assertNotNull(answerOptionMediaEntity);
		assertThat(answerOptionMediaEntity.getOptionMediaId(), equalTo(answerOptionMediaID));
		assertThat(new String(answerOptionMediaEntity.getOptionMedia()), equalTo("testing updates"));

		answerOptionMediaSpringDataRepository.delete(answerOptionMediaID);
		answerOptionMediaEntity = answerOptionMediaSpringDataRepository.findOne(answerOptionMediaID);
		assertNull(answerOptionMediaEntity);

		answerOptionSpringDataRepo.delete(answerOptionID);
		answerOptionEntity = answerOptionSpringDataRepo.findOne(answerOptionID);
		assertNull(answerOptionEntity);

		questionSpringDataRepository.delete(questionID);
		questionEntity = questionSpringDataRepository.findOne(questionID);
		assertNull(questionEntity);
	}

	@Test
	public void testFindByOptionId(){

		assertNotNull(answerOptionSpringDataRepo);

		assertNotNull(questionSpringDataRepository);

		assertNotNull(answerOptionMediaSpringDataRepository);

		QuestionEntity questionEntity = new QuestionEntity(
				1L,
				"AnswerOptionMediaSpringDataRepositoryTest Question",
				"AnswerOptionMediaSpringDataRepositoryTest Answer",
				1,
				0L,new Date(),1L,new Date(),1L);
		questionEntity = questionSpringDataRepository.save(questionEntity);
		assertNotNull(questionEntity);
		assertThat(questionEntity.getQuestionId(), greaterThan(0L));
		final Long questionID = questionEntity.getQuestionId();

		AnswerOptionEntity answerOptionEntity = new AnswerOptionEntity(questionID, "Option 1", (byte) 0);
		answerOptionEntity = answerOptionSpringDataRepo.save(answerOptionEntity);
		assertNotNull(answerOptionEntity);
		assertThat(answerOptionEntity.getOptionId(), greaterThan(0L));
		final Long answerOptionID  = answerOptionEntity.getOptionId();

		AnswerOptionMediaEntity answerOptionMediaEntity = new AnswerOptionMediaEntity(answerOptionID,1,new String("testing").getBytes());
		answerOptionMediaEntity = answerOptionMediaSpringDataRepository.save(answerOptionMediaEntity);
		assertNotNull(answerOptionMediaEntity);
		assertThat(answerOptionMediaEntity.getOptionMediaId(), greaterThan(0L));
		final Long answerOptionMediaID  = answerOptionMediaEntity.getOptionMediaId();

		answerOptionMediaEntity = answerOptionMediaSpringDataRepository.findOne(answerOptionMediaID);
		assertNotNull(answerOptionMediaEntity);
		assertThat(answerOptionMediaEntity.getOptionMediaId(), equalTo(answerOptionMediaID));
		assertThat(new String(answerOptionMediaEntity.getOptionMedia()), equalTo("testing"));

		List<AnswerOptionMediaEntity> answerOptionMediaEntityList = answerOptionMediaSpringDataRepository.findByOptionId(answerOptionID);
		assertNotNull(answerOptionMediaEntityList);
		assertThat(answerOptionMediaEntityList.size(), equalTo(1));
		assertThat(answerOptionMediaEntityList.get(0).getOptionMediaId(), equalTo(answerOptionMediaID));
		assertThat(new String(answerOptionMediaEntityList.get(0).getOptionMedia()), equalTo("testing"));

		answerOptionMediaEntity.setOptionMedia(new String("testing updates").getBytes());
		answerOptionMediaEntity = answerOptionMediaSpringDataRepository.save(answerOptionMediaEntity);
		assertNotNull(answerOptionMediaEntity);
		assertThat(answerOptionMediaEntity.getOptionMediaId(), equalTo(answerOptionMediaID));

		answerOptionMediaEntity = answerOptionMediaSpringDataRepository.findOne(answerOptionMediaID);
		assertNotNull(answerOptionMediaEntity);
		assertThat(answerOptionMediaEntity.getOptionMediaId(), equalTo(answerOptionMediaID));
		assertThat(new String(answerOptionMediaEntity.getOptionMedia()), equalTo("testing updates"));

		answerOptionMediaEntityList = answerOptionMediaSpringDataRepository.findByOptionId(answerOptionID);
		assertNotNull(answerOptionMediaEntityList);
		assertThat(answerOptionMediaEntityList.size(), equalTo(1));
		assertThat(answerOptionMediaEntityList.get(0).getOptionMediaId(), equalTo(answerOptionMediaID));
		assertThat(new String(answerOptionMediaEntityList.get(0).getOptionMedia()), equalTo("testing updates"));

		answerOptionMediaSpringDataRepository.delete(answerOptionMediaID);
		answerOptionMediaEntity = answerOptionMediaSpringDataRepository.findOne(answerOptionMediaID);
		assertNull(answerOptionMediaEntity);

		answerOptionSpringDataRepo.delete(answerOptionID);
		answerOptionEntity = answerOptionSpringDataRepo.findOne(answerOptionID);
		assertNull(answerOptionEntity);

		questionSpringDataRepository.delete(questionID);
		questionEntity = questionSpringDataRepository.findOne(questionID);
		assertNull(questionEntity);
	}
}
