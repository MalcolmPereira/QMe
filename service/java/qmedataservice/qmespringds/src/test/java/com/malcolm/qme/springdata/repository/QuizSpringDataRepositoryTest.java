/**
 * Name      : com.malcolm.qme.springdata.repository.QuizSpringDataRepositoryTest.java
 * Date      : May 17, 2015
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA QuizEntity Repository
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
import com.malcolm.qme.springdata.entity.QuizEntity;

/**
 * @Author: malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { QMeSpringDataJPAConfig.class })
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
public class QuizSpringDataRepositoryTest {

	/**
	 * QuizEntity Repository
	 */
	@Autowired
	private QuizSpringDataRepository quizSpringDataRepository;

	/**
	 * CategoryEntity Repository
	 */
	@Autowired
	private CategorySpringDataRepository categorySpringDataRepository;


	@Test
	public void testFindAll() {
		assertNotNull(quizSpringDataRepository);
		final List<QuizEntity> quizEntities = quizSpringDataRepository.findAll();
		assertNotNull(quizEntities);
		assertThat(quizEntities.size(), greaterThan(0));
	}

	@Test
	public void testFindById() {
		assertNotNull(quizSpringDataRepository);
		final QuizEntity quizEntity = quizSpringDataRepository.findOne(1L);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), equalTo(1L));
	}

	@Test
	public void testCRUD(){
		assertNotNull(quizSpringDataRepository);

		QuizEntity quizEntity = new QuizEntity(
				"QuizSpringDataRepositoryTest Quiz","QuizSpringDataRepositoryTest Quiz Desc",1L, 0L,0L,
				0, new Date(), 1L,new Date(), 1L
				);
		quizEntity = quizSpringDataRepository.save(quizEntity);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), greaterThan(0L));

		final Long quizID = quizEntity.getQuizId();

		quizEntity = quizSpringDataRepository.findOne(quizID);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), equalTo(quizID));
		assertThat(quizEntity.getQuizName(), equalTo("QuizSpringDataRepositoryTest Quiz"));

		quizEntity.setQuizName("QuizSpringDataRepositoryTest Quiz Update");

		quizEntity = quizSpringDataRepository.save(quizEntity);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), equalTo(quizID));

		quizEntity = quizSpringDataRepository.findOne(quizID);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), equalTo(quizID));
		assertThat(quizEntity.getQuizName(), equalTo("QuizSpringDataRepositoryTest Quiz Update"));

		quizSpringDataRepository.delete(quizID);
		quizEntity = quizSpringDataRepository.findOne(quizID);
		assertNull(quizEntity);
	}

	@Test
	public void testFindByCatId(){
		assertNotNull(quizSpringDataRepository);

		CategoryEntity categoryEntity = new CategoryEntity("QuizSpringDataRepositoryTest", 0L, new Date(), 1L);
		categoryEntity = categorySpringDataRepository.save(categoryEntity);
		assertNotNull(categoryEntity);
		assertThat(categoryEntity.getCatId(), greaterThan(0L));

		final Long catID = categoryEntity.getCatId();

		QuizEntity quizEntity = new QuizEntity(
				"QuizSpringDataRepositoryTestByCat Quiz","QuizSpringDataRepositoryTestByCat Quiz Desc",catID, 0L,0L,
				0, new Date(), 1L,new Date(), 1L
				);

		quizEntity = quizSpringDataRepository.save(quizEntity);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), greaterThan(0L));

		final Long quizID = quizEntity.getQuizId();

		quizEntity = quizSpringDataRepository.findOne(quizID);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), equalTo(quizID));
		assertThat(quizEntity.getQuizName(), equalTo("QuizSpringDataRepositoryTestByCat Quiz"));

		List<QuizEntity> quizList = quizSpringDataRepository.findByCatId(catID);
		assertNotNull(quizList);
		assertThat(quizList.size(), equalTo(1));
		assertThat(quizList.get(0).getQuizId(), equalTo(quizID));
		assertThat(quizList.get(0).getQuizName(), equalTo("QuizSpringDataRepositoryTestByCat Quiz"));

		quizEntity.setQuizName("QuizSpringDataRepositoryTestByCat Quiz Update");

		quizEntity = quizSpringDataRepository.save(quizEntity);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), equalTo(quizID));

		quizEntity = quizSpringDataRepository.findOne(quizID);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), equalTo(quizID));
		assertThat(quizEntity.getQuizName(), equalTo("QuizSpringDataRepositoryTestByCat Quiz Update"));

		quizList = quizSpringDataRepository.findByCatId(catID);
		assertNotNull(quizList);
		assertThat(quizList.size(), equalTo(1));
		assertThat(quizList.get(0).getQuizId(), equalTo(quizID));
		assertThat(quizList.get(0).getQuizName(), equalTo("QuizSpringDataRepositoryTestByCat Quiz Update"));

		quizSpringDataRepository.delete(quizID);
		quizEntity = quizSpringDataRepository.findOne(quizID);
		assertNull(quizEntity);

		categorySpringDataRepository.delete(catID);
		categoryEntity = categorySpringDataRepository.findOne(catID);
		assertNull(categoryEntity);
	}

	@Test
	public void testFindByQuizNameIgnoreCaseLike(){
		assertNotNull(quizSpringDataRepository);

		QuizEntity quizEntity = new QuizEntity(
				"QuizSpringDataRepositoryTestByName Quiz","QuizSpringDataRepositoryTestByName Quiz Desc",1L, 0L,0L,
				0, new Date(), 1L,new Date(), 1L
				);

		quizEntity = quizSpringDataRepository.save(quizEntity);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), greaterThan(0L));

		final Long quizID = quizEntity.getQuizId();

		quizEntity = quizSpringDataRepository.findOne(quizID);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), equalTo(quizID));
		assertThat(quizEntity.getQuizName(), equalTo("QuizSpringDataRepositoryTestByName Quiz"));

		List<QuizEntity> quizList = quizSpringDataRepository.findByQuizNameIgnoreCaseLike("QuizSpringDataRepositoryTestByName Quiz");
		assertNotNull(quizList);
		assertThat(quizList.size(), equalTo(1));
		assertThat(quizList.get(0).getQuizId(), equalTo(quizID));
		assertThat(quizList.get(0).getQuizName(), equalTo("QuizSpringDataRepositoryTestByName Quiz"));

		quizList = quizSpringDataRepository.findByQuizNameIgnoreCaseLike("quizspringdatarepositorytestbyname quiz");
		assertNotNull(quizList);
		assertThat(quizList.size(), equalTo(1));
		assertThat(quizList.get(0).getQuizId(), equalTo(quizID));
		assertThat(quizList.get(0).getQuizName(), equalTo("QuizSpringDataRepositoryTestByName Quiz"));


		quizEntity.setQuizName("QuizSpringDataRepositoryTestByName Quiz Update");

		quizEntity = quizSpringDataRepository.save(quizEntity);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), equalTo(quizID));

		quizEntity = quizSpringDataRepository.findOne(quizID);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), equalTo(quizID));
		assertThat(quizEntity.getQuizName(), equalTo("QuizSpringDataRepositoryTestByName Quiz Update"));

		quizList = quizSpringDataRepository.findByQuizNameIgnoreCaseLike("QuizSpringDataRepositoryTestByName Quiz Update");
		assertNotNull(quizList);
		assertThat(quizList.size(), equalTo(1));
		assertThat(quizList.get(0).getQuizId(), equalTo(quizID));
		assertThat(quizList.get(0).getQuizName(), equalTo("QuizSpringDataRepositoryTestByName Quiz Update"));

		quizList = quizSpringDataRepository.findByQuizNameIgnoreCaseLike("quizspringdatarepositorytestbyname quiz update");
		assertNotNull(quizList);
		assertThat(quizList.size(), equalTo(1));
		assertThat(quizList.get(0).getQuizId(), equalTo(quizID));
		assertThat(quizList.get(0).getQuizName(), equalTo("QuizSpringDataRepositoryTestByName Quiz Update"));

		quizSpringDataRepository.delete(quizID);
		quizEntity = quizSpringDataRepository.findOne(quizID);
		assertNull(quizEntity);
	}

	@Test
	public void testFindTop50ByOrderByQuizLikesDesc(){
		assertNotNull(quizSpringDataRepository);

		QuizEntity quizEntity = new QuizEntity(
				"QuizSpringDataRepositoryTestByLikes Quiz1","QuizSpringDataRepositoryTestByLikes Quiz1 Desc",1L, 0L,0L,
				0, new Date(), 1L,new Date(), 1L
				);

		quizEntity = quizSpringDataRepository.save(quizEntity);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), greaterThan(0L));
		final Long quizID1 = quizEntity.getQuizId();
		quizEntity = quizSpringDataRepository.findOne(quizID1);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), equalTo(quizID1));
		assertThat(quizEntity.getQuizName(), equalTo("QuizSpringDataRepositoryTestByLikes Quiz1"));
		for(int i =0 ; i < 100 ; i ++){
			if(quizEntity.getQuizLikes() == null){
				quizEntity.setQuizLikes(1L);
			}else{
				quizEntity.setQuizLikes(quizEntity.getQuizLikes() + 1);
			}
			quizEntity = quizSpringDataRepository.save(quizEntity);
		}

		quizEntity = new QuizEntity(
				"QuizSpringDataRepositoryTestByLikes Quiz2","QuizSpringDataRepositoryTestByLikes Quiz2 Desc",1L, 0L,0L,
				0, new Date(), 1L,new Date(), 1L
				);

		quizEntity = quizSpringDataRepository.save(quizEntity);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), greaterThan(0L));
		final Long quizID2 = quizEntity.getQuizId();
		quizEntity = quizSpringDataRepository.findOne(quizID2);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), equalTo(quizID2));
		assertThat(quizEntity.getQuizName(), equalTo("QuizSpringDataRepositoryTestByLikes Quiz2"));
		for(int i =0 ; i < 10 ; i ++){
			if(quizEntity.getQuizLikes() == null){
				quizEntity.setQuizLikes(1L);
			}else{
				quizEntity.setQuizLikes(quizEntity.getQuizLikes() + 1);
			}
			quizEntity = quizSpringDataRepository.save(quizEntity);
		}

		quizEntity = new QuizEntity(
				"QuizSpringDataRepositoryTestByLikes Quiz3","QuizSpringDataRepositoryTestByLikes Quiz3 Desc",1L, 0L,0L,
				0, new Date(), 1L,new Date(), 1L
				);

		quizEntity = quizSpringDataRepository.save(quizEntity);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), greaterThan(0L));
		final Long quizID3 = quizEntity.getQuizId();
		quizEntity = quizSpringDataRepository.findOne(quizID3);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), equalTo(quizID3));
		assertThat(quizEntity.getQuizName(), equalTo("QuizSpringDataRepositoryTestByLikes Quiz3"));

		final List<QuizEntity> quizList = quizSpringDataRepository.findTop50ByOrderByQuizLikesDesc();
		assertNotNull(quizList);
		assertThat(quizList.size(), greaterThan(0));
		assertThat((quizList.get(0)).getQuizId(),equalTo(quizID1));
		assertThat((quizList.get(0)).getQuizName(),equalTo("QuizSpringDataRepositoryTestByLikes Quiz1"));

		quizSpringDataRepository.delete(quizID1);
		quizEntity = quizSpringDataRepository.findOne(quizID1);
		assertNull(quizEntity);

		quizSpringDataRepository.delete(quizID2);
		quizEntity = quizSpringDataRepository.findOne(quizID2);
		assertNull(quizEntity);

		quizSpringDataRepository.delete(quizID3);
		quizEntity = quizSpringDataRepository.findOne(quizID3);
		assertNull(quizEntity);
	}
}
