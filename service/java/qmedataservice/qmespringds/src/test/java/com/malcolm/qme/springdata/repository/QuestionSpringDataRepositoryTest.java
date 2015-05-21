/**
 * Name      : com.malcolm.qme.springdata.repository.QuestionSpringDataRepositoryTest.java
 * Date      : 5/12/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA QuestionEntity Repository
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
import com.malcolm.qme.springdata.entity.CategoryEntity;
import com.malcolm.qme.springdata.entity.QuestionEntity;

/**
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class
})
public class QuestionSpringDataRepositoryTest {
	/**
	 * QuestionEntity Repository
	 */
	@Autowired
	private QuestionSpringDataRepository questionSpringDataRepository;

	/**
	 * CategoryEntity Repository
	 */
	@Autowired
	private CategorySpringDataRepository categorySpringDataRepository;

	@Test
	public void testFindAll(){
		assertNotNull(questionSpringDataRepository);
		final List<QuestionEntity> questionEntities = questionSpringDataRepository.findAll();
		assertNotNull(questionEntities);
		assertThat(questionEntities.size(), greaterThan(0));
	}

	@Test
	public void testFindById(){
		assertNotNull(questionSpringDataRepository);
		final QuestionEntity questionEntity = questionSpringDataRepository.findOne(1L);
		assertNotNull(questionEntity);
		assertThat(questionEntity.getQuestionId(), equalTo(1L));
	}

	@Test
	public void testCRUD(){
		assertNotNull(questionSpringDataRepository);

		QuestionEntity questionEntity = new QuestionEntity(1L,
				"QuestionSpringDataRepositoryTest Question",
				"QuestionSpringDataRepositoryTest Answer",
				1,0L,new Date(),1L,new Date(),1L);

		questionEntity = questionSpringDataRepository.save(questionEntity);
		assertNotNull(questionEntity);
		assertThat(questionEntity.getQuestionId(), greaterThan(0L));

		final Long questionID = questionEntity.getQuestionId();

		questionEntity = questionSpringDataRepository.findOne(questionID);
		assertNotNull(questionEntity);
		assertThat(questionEntity.getQuestionId(), equalTo(questionID));

		questionEntity.setQuestionText("QuestionSpringDataRepositoryTest Question UPDATE");
		questionEntity.setQuestionAnswer("QuestionSpringDataRepositoryTest Answer UPDATE");
		questionEntity = questionSpringDataRepository.save(questionEntity);
		questionEntity = questionSpringDataRepository.findOne(questionID);
		assertNotNull(questionEntity);
		assertThat(questionEntity.getQuestionText(), equalTo("QuestionSpringDataRepositoryTest Question UPDATE"));
		assertThat(questionEntity.getQuestionAnswer(), equalTo("QuestionSpringDataRepositoryTest Answer UPDATE"));

		questionSpringDataRepository.delete(questionID);
		questionEntity = questionSpringDataRepository.findOne(questionID);
		assertNull(questionEntity);

	}

	@Test
	public void testFindByCatId(){

		assertNotNull(questionSpringDataRepository);

		assertNotNull(categorySpringDataRepository);

		CategoryEntity categoryEntity = new CategoryEntity("QuestionSpringDataRepositoryTest", 0L, new Date(), 1L);
		categoryEntity = categorySpringDataRepository.save(categoryEntity);
		assertNotNull(categoryEntity);
		assertThat(categoryEntity.getCatId(), greaterThan(0L));
		final Long catID = categoryEntity.getCatId();

		QuestionEntity questionEntity = new QuestionEntity(catID,
				"QuestionSpringDataRepositoryTest Question",
				"QuestionSpringDataRepositoryTest Answer",
				1,0L,new Date(),1L,new Date(),1L);
		questionEntity = questionSpringDataRepository.save(questionEntity);
		assertNotNull(questionEntity);
		assertThat(questionEntity.getQuestionId(), greaterThan(0L));
		final Long question1ID = questionEntity.getQuestionId();

		questionEntity = new QuestionEntity(catID,
				"QuestionSpringDataRepositoryTest Question",
				"QuestionSpringDataRepositoryTest Answer",
				1,0L,new Date(),1L,new Date(),1L);
		questionEntity = questionSpringDataRepository.save(questionEntity);
		assertNotNull(questionEntity);
		assertThat(questionEntity.getQuestionId(), greaterThan(0L));
		final Long question2ID = questionEntity.getQuestionId();

		questionEntity = new QuestionEntity(catID,
				"QuestionSpringDataRepositoryTest Question",
				"QuestionSpringDataRepositoryTest Answer",
				1,0L,new Date(),1L,new Date(),1L);
		questionEntity = questionSpringDataRepository.save(questionEntity);
		assertNotNull(questionEntity);
		assertThat(questionEntity.getQuestionId(), greaterThan(0L));
		final Long question3ID = questionEntity.getQuestionId();

		final List<QuestionEntity> questionEntityList = questionSpringDataRepository.findByCatId(catID);
		assertNotNull(questionEntityList);
		assertThat(questionEntityList.size(), equalTo(3));
		for (final QuestionEntity question : questionEntityList) {
			assertThat(question.getCatId(), equalTo(catID));
		}

		questionSpringDataRepository.delete(question1ID);
		questionEntity = questionSpringDataRepository.findOne(question1ID);
		assertNull(questionEntity);

		questionSpringDataRepository.delete(question2ID);
		questionEntity = questionSpringDataRepository.findOne(question2ID);
		assertNull(questionEntity);

		questionSpringDataRepository.delete(question3ID);
		questionEntity = questionSpringDataRepository.findOne(question3ID);
		assertNull(questionEntity);

		categorySpringDataRepository.delete(catID);
		categoryEntity = categorySpringDataRepository.findOne(catID);
		assertNull(categoryEntity);
	}

	@Test
	public void testFindTop50ByOrderByQuestionLikes(){

		assertNotNull(questionSpringDataRepository);

		assertNotNull(categorySpringDataRepository);

		CategoryEntity categoryEntity = new CategoryEntity("QuestionSpringDataRepositoryTest", 0L, new Date(), 1L);
		categoryEntity = categorySpringDataRepository.save(categoryEntity);
		assertNotNull(categoryEntity);
		assertThat(categoryEntity.getCatId(), greaterThan(0L));
		final Long catID = categoryEntity.getCatId();

		QuestionEntity questionEntity = new QuestionEntity(catID,
				"QuestionSpringDataRepositoryTest Question1",
				"QuestionSpringDataRepositoryTest Answer",
				1,0L,new Date(),1L,new Date(),1L);
		questionEntity = questionSpringDataRepository.save(questionEntity);
		assertNotNull(questionEntity);
		assertThat(questionEntity.getQuestionId(), greaterThan(0L));
		final Long question1ID = questionEntity.getQuestionId();
		for(int i = 0 ; i < 100 ; i ++){
			if(questionEntity.getQuestionLikes() == null){
				questionEntity.setQuestionLikes(1L);
			}else{
				questionEntity.setQuestionLikes(questionEntity.getQuestionLikes() + 1);
			}
			questionEntity = questionSpringDataRepository.save(questionEntity);
		}

		questionEntity = new QuestionEntity(catID,
				"QuestionSpringDataRepositoryTest Question2",
				"QuestionSpringDataRepositoryTest Answer",
				1,0L,new Date(),1L,new Date(),1L);
		questionEntity = questionSpringDataRepository.save(questionEntity);
		assertNotNull(questionEntity);
		assertThat(questionEntity.getQuestionId(), greaterThan(0L));
		final Long question2ID = questionEntity.getQuestionId();
		for(int i = 0 ; i < 10 ; i ++){
			if(questionEntity.getQuestionLikes() == null){
				questionEntity.setQuestionLikes(1L);
			}else{
				questionEntity.setQuestionLikes(questionEntity.getQuestionLikes() + 1);
			}
			questionEntity = questionSpringDataRepository.save(questionEntity);
		}

		questionEntity = new QuestionEntity(catID,
				"QuestionSpringDataRepositoryTest Question3",
				"QuestionSpringDataRepositoryTest Answer",
				1,0L,new Date(),1L,new Date(),1L);
		questionEntity = questionSpringDataRepository.save(questionEntity);
		assertNotNull(questionEntity);
		assertThat(questionEntity.getQuestionId(), greaterThan(0L));
		final Long question3ID = questionEntity.getQuestionId();

		List<QuestionEntity> questionEntityList = questionSpringDataRepository.findByCatId(catID);
		assertNotNull(questionEntityList);
		assertThat(questionEntityList.size(), equalTo(3));
		for (final QuestionEntity question : questionEntityList) {
			assertThat(question.getCatId(), equalTo(catID));
		}

		questionEntityList = questionSpringDataRepository.findTop50ByOrderByQuestionLikesDesc();
		assertNotNull(questionEntityList);
		assertThat(questionEntityList.size(), greaterThan(0));
		assertThat((questionEntityList.get(0)).getCatId(),equalTo(catID));
		assertThat((questionEntityList.get(0)).getQuestionText(),equalTo("QuestionSpringDataRepositoryTest Question1"));

		questionSpringDataRepository.delete(question1ID);
		questionEntity = questionSpringDataRepository.findOne(question1ID);
		assertNull(questionEntity);

		questionSpringDataRepository.delete(question2ID);
		questionEntity = questionSpringDataRepository.findOne(question2ID);
		assertNull(questionEntity);

		questionSpringDataRepository.delete(question3ID);
		questionEntity = questionSpringDataRepository.findOne(question3ID);
		assertNull(questionEntity);

		categorySpringDataRepository.delete(catID);
		categoryEntity = categorySpringDataRepository.findOne(catID);
		assertNull(categoryEntity);

	}

}
