/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizSpringDataRepositoryTest.java
 * Date      : 5/18/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA UserQuizEntity Repository
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
import com.malcolm.qme.springdata.entity.QuizEntity;
import com.malcolm.qme.springdata.entity.UserEntity;
import com.malcolm.qme.springdata.entity.UserQuizEntity;

/**
 * @Author: malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class UserQuizSpringDataRepositoryTest {
	/**
	 * UserQuizEntity Repository
	 */
	@Autowired
	private UserQuizSpringDataRepository userQuizSpringDataRepository;
	
	/**
     * UserEntity Repository
     */
    @Autowired
    private UserSpringDataRepository userSpringDataRepo;
    
    /**
	 * QuizEntity Repository
	 */
	@Autowired
	private QuizSpringDataRepository quizSpringDataRepository;
	
	@Test
	public void testFindAll() {
		assertNotNull(userQuizSpringDataRepository);
		List<UserQuizEntity> userQuizEntities = userQuizSpringDataRepository.findAll();
		assertNotNull(userQuizEntities);
		assertThat(userQuizEntities.size(), greaterThan(0));
	}

	@Test
	public void testFindById() {
		assertNotNull(userQuizSpringDataRepository);
		UserQuizEntity userQuizEntity = userQuizSpringDataRepository.findOne(1L);
		assertNotNull(userQuizEntity);
		assertThat(userQuizEntity.getUserId(), equalTo(1L));
	}
	
	@Test
	public void testCRUD() {
		
		assertNotNull(userQuizSpringDataRepository);
		
		assertNotNull(userSpringDataRepo);
		
		assertNotNull(quizSpringDataRepository);

		UserEntity userEntity = new UserEntity("UserQuizSpringDataRepositoryTest",
				"Test", "Test", "UserQuizSpringDataRepositoryTest@test.com",
				"Test", new Date(), new Date());
		userEntity = userSpringDataRepo.save(userEntity);
		assertNotNull(userEntity);
		assertThat(userEntity.getUserId(), greaterThan(0L));
		Long userID = userEntity.getUserId();
		
		
		QuizEntity quizEntity = new QuizEntity(
				"UserQuizSpringDataRepositoryTest Quiz","UserQuizSpringDataRepositoryTest Quiz Desc",1L, 0L,0L,
				0, new Date(), 1L,new Date(), 1L
				);
		quizEntity = quizSpringDataRepository.save(quizEntity);
		assertNotNull(quizEntity);
		assertThat(quizEntity.getQuizId(), greaterThan(0L));
		final Long quizID = quizEntity.getQuizId();
		
		UserQuizEntity userQuizEntity = new UserQuizEntity(userID,quizID, 1L, new Date(), 0, 10, (byte)0); 
		userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
		assertNotNull(userQuizEntity);
		assertThat(userQuizEntity.getUserQuizId(), greaterThan(0L));
		final Long userQuizID = userQuizEntity.getUserQuizId();
		
		userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
		assertNotNull(userQuizEntity);
		assertThat(userQuizEntity.getUserQuizId(), equalTo(userQuizID));
		assertThat(userQuizEntity.getUserId(), equalTo(userID));
		assertThat(userQuizEntity.getQuizId(), equalTo(quizID));
		assertThat(userQuizEntity.getQuizMaxScore(), equalTo(10));
		assertThat(userQuizEntity.getQuizComplete(), equalTo((byte)0));
		
		userQuizEntity.setQuizComplete((byte)1);
		userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
		assertNotNull(userQuizEntity);
		
		userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
		assertNotNull(userQuizEntity);
		assertThat(userQuizEntity.getUserQuizId(), equalTo(userQuizID));
		assertThat(userQuizEntity.getUserId(), equalTo(userID));
		assertThat(userQuizEntity.getQuizId(), equalTo(quizID));
		assertThat(userQuizEntity.getQuizMaxScore(), equalTo(10));
		assertThat(userQuizEntity.getQuizComplete(), equalTo((byte)1));
		
		userQuizSpringDataRepository.delete(userQuizID);
		userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
		assertNull(userQuizEntity);
		
		userSpringDataRepo.delete(userID);
	    userEntity = userSpringDataRepo.findOne(userID);
	    assertNull(userEntity);
	    
	    quizSpringDataRepository.delete(quizID);
		quizEntity = quizSpringDataRepository.findOne(quizID);
		assertNull(quizEntity);
	}
}
