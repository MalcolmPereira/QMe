/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizRepositoryImplTest.java
 * Date      : 5/18/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData UserQuiz Repository
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.malcolm.qme.core.domain.Quiz;
import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserQuiz;
import com.malcolm.qme.core.repository.QuizRepository;
import com.malcolm.qme.core.repository.UserQuizRepository;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;


/**
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class UserQuizRepositoryImplTest {
	
	/**
	 * User Quiz Repository
	 */
	@Autowired
	@Qualifier("UserQuizRepository")
	private UserQuizRepository userQuizRepository;
	
	 /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepo;
    
    
    /**
	 * Quiz Repository
	 */
	@Autowired
	@Qualifier("QuizRepository")
	private QuizRepository quizRepository;
	
	@Test
	public void testFindAll() {
		assertNotNull(userQuizRepository);
		List<UserQuiz> userQuizList = userQuizRepository.findAll();
		assertNotNull(userQuizList);
		assertThat(userQuizList.size(), greaterThan(0));
	}

	@Test
	public void testFindById() {
		assertNotNull(userQuizRepository);
		UserQuiz  userQuiz = userQuizRepository.findById(1L);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), equalTo(1L));
	}
	
	@Test
	public void testCRUD() {
		
		assertNotNull(userQuizRepository);
		
		assertNotNull(userRepo);
		
		assertNotNull(quizRepository);

        User user = new User("UserQuizRepositoryImplTest", "Test", "Test", "Test","UserQuizRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));
        Long userID = user.getUserID();
        
		Quiz quiz = new Quiz(
				"UserQuizRepositoryImplTest Quiz","UserQuizRepositoryImplTest Quiz Desc", 1L, 0,1L);
		quiz = quizRepository.save(quiz);
		assertNotNull(quiz);
		assertThat(quiz.getQuizID(), greaterThan(0L));
		final Long quizID = quiz.getQuizID();
		
		UserQuiz userQuiz = new UserQuiz(userID,quizID, 1L, 10, "Some token"); 
		userQuiz = userQuizRepository.save(userQuiz);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), greaterThan(0L));
		final Long userQuizID = userQuiz.getUserQuizID();
		
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuiz.getUserID(), equalTo(userID));
		assertThat(userQuiz.getQuizID(), equalTo(quizID));
		assertThat(userQuiz.getQuizMaxScore(), equalTo(10));
		assertThat(userQuiz.getQuizComplete(), equalTo(false));
        
		UserQuiz userQuizUpdate = new UserQuiz(
					userQuiz.getUserQuizID(),
					userQuiz.getUserID(),
					userQuiz.getQuizID(),
					userQuiz.getCategoryID(),
					userQuiz.getQuizStartDate(),
					new Date(),
					userQuiz.getUserQuizToken(),
					10,
					userQuiz.getQuizMaxScore(),
					true
				);
		userQuizUpdate = userQuizRepository.update(userQuizUpdate, userID);
		assertNotNull(userQuizUpdate);
		assertThat(userQuizUpdate.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuizUpdate.getUserID(), equalTo(userID));
		assertThat(userQuizUpdate.getQuizID(), equalTo(quizID));
		assertThat(userQuizUpdate.getQuizMaxScore(), equalTo(10));
		assertThat(userQuizUpdate.getQuizComplete(), equalTo(true));
		
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuiz.getUserID(), equalTo(userID));
		assertThat(userQuiz.getQuizID(), equalTo(quizID));
		assertThat(userQuiz.getQuizMaxScore(), equalTo(10));
		assertThat(userQuiz.getQuizComplete(), equalTo(true));
		
		userQuizRepository.delete(userQuizID);
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNull(userQuiz);
		
		userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
        
        quizRepository.delete(quizID);
		quiz = quizRepository.findById(quizID);
		assertNull(quiz);
	}

	@Test
	public void testFindByUserId() {
		
		assertNotNull(userQuizRepository);
		
		assertNotNull(userRepo);
		
		assertNotNull(quizRepository);

        User user = new User("UserQuizRepositoryImplTest", "Test", "Test", "Test","UserQuizRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));
        Long userID = user.getUserID();
        
		Quiz quiz = new Quiz(
				"UserQuizRepositoryImplTest Quiz","UserQuizRepositoryImplTest Quiz Desc", 1L, 0,1L);
		quiz = quizRepository.save(quiz);
		assertNotNull(quiz);
		assertThat(quiz.getQuizID(), greaterThan(0L));
		final Long quizID = quiz.getQuizID();
		
		UserQuiz userQuiz = new UserQuiz(userID,quizID, 1L, 10, "Some token"); 
		userQuiz = userQuizRepository.save(userQuiz);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), greaterThan(0L));
		final Long userQuizID = userQuiz.getUserQuizID();
		
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuiz.getUserID(), equalTo(userID));
		assertThat(userQuiz.getQuizID(), equalTo(quizID));
		assertThat(userQuiz.getQuizMaxScore(), equalTo(10));
		assertThat(userQuiz.getQuizComplete(), equalTo(false));
        
		List<UserQuiz> userQuizList = userQuizRepository.findByUserId(userID);
		assertNotNull(userQuizList);
		assertThat(userQuizList.size(), equalTo(1));
		assertThat(userQuizList.get(0).getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuizList.get(0).getUserID(), equalTo(userID));
		assertThat(userQuizList.get(0).getQuizID(), equalTo(quizID));
		assertThat(userQuizList.get(0).getQuizMaxScore(), equalTo(10));
		assertThat(userQuizList.get(0).getQuizComplete(), equalTo(false));
		
		UserQuiz userQuizUpdate = new UserQuiz(
					userQuiz.getUserQuizID(),
					userQuiz.getUserID(),
					userQuiz.getQuizID(),
					userQuiz.getCategoryID(),
					userQuiz.getQuizStartDate(),
					new Date(),
					userQuiz.getUserQuizToken(),
					10,
					userQuiz.getQuizMaxScore(),
					true
				);
		userQuizUpdate = userQuizRepository.update(userQuizUpdate, userID);
		assertNotNull(userQuizUpdate);
		assertThat(userQuizUpdate.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuizUpdate.getUserID(), equalTo(userID));
		assertThat(userQuizUpdate.getQuizID(), equalTo(quizID));
		assertThat(userQuizUpdate.getQuizMaxScore(), equalTo(10));
		assertThat(userQuizUpdate.getQuizComplete(), equalTo(true));
		
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuiz.getUserID(), equalTo(userID));
		assertThat(userQuiz.getQuizID(), equalTo(quizID));
		assertThat(userQuiz.getQuizMaxScore(), equalTo(10));
		assertThat(userQuiz.getQuizComplete(), equalTo(true));
		
		userQuizList = userQuizRepository.findByUserId(userID);
		assertNotNull(userQuizList);
		assertThat(userQuizList.size(), equalTo(1));
		assertThat(userQuizList.get(0).getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuizList.get(0).getUserID(), equalTo(userID));
		assertThat(userQuizList.get(0).getQuizID(), equalTo(quizID));
		assertThat(userQuizList.get(0).getQuizMaxScore(), equalTo(10));
		assertThat(userQuizList.get(0).getQuizComplete(), equalTo(true));
		
		
		userQuizRepository.delete(userQuizID);
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNull(userQuiz);
		
		userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
        
        quizRepository.delete(quizID);
		quiz = quizRepository.findById(quizID);
		assertNull(quiz);
	}
	
	@Test
	public void testFindByQuizId() {
		
		assertNotNull(userQuizRepository);
		
		assertNotNull(userRepo);
		
		assertNotNull(quizRepository);

        User user = new User("UserQuizRepositoryImplTest", "Test", "Test", "Test","UserQuizRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));
        Long userID = user.getUserID();
        
		Quiz quiz = new Quiz(
				"UserQuizRepositoryImplTest Quiz","UserQuizRepositoryImplTest Quiz Desc", 1L, 0,1L);
		quiz = quizRepository.save(quiz);
		assertNotNull(quiz);
		assertThat(quiz.getQuizID(), greaterThan(0L));
		final Long quizID = quiz.getQuizID();
		
		UserQuiz userQuiz = new UserQuiz(userID,quizID, 1L, 10, "Some token"); 
		userQuiz = userQuizRepository.save(userQuiz);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), greaterThan(0L));
		final Long userQuizID = userQuiz.getUserQuizID();
		
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuiz.getUserID(), equalTo(userID));
		assertThat(userQuiz.getQuizID(), equalTo(quizID));
		assertThat(userQuiz.getQuizMaxScore(), equalTo(10));
		assertThat(userQuiz.getQuizComplete(), equalTo(false));
        
		List<UserQuiz> userQuizList = userQuizRepository.findByQuizId(quizID);
		assertNotNull(userQuizList);
		assertThat(userQuizList.size(), equalTo(1));
		assertThat(userQuizList.get(0).getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuizList.get(0).getUserID(), equalTo(userID));
		assertThat(userQuizList.get(0).getQuizID(), equalTo(quizID));
		assertThat(userQuizList.get(0).getQuizMaxScore(), equalTo(10));
		assertThat(userQuizList.get(0).getQuizComplete(), equalTo(false));
		
		UserQuiz userQuizUpdate = new UserQuiz(
					userQuiz.getUserQuizID(),
					userQuiz.getUserID(),
					userQuiz.getQuizID(),
					userQuiz.getCategoryID(),
					userQuiz.getQuizStartDate(),
					new Date(),
					userQuiz.getUserQuizToken(),
					10,
					userQuiz.getQuizMaxScore(),
					true
				);
		userQuizUpdate = userQuizRepository.update(userQuizUpdate, userID);
		assertNotNull(userQuizUpdate);
		assertThat(userQuizUpdate.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuizUpdate.getUserID(), equalTo(userID));
		assertThat(userQuizUpdate.getQuizID(), equalTo(quizID));
		assertThat(userQuizUpdate.getQuizMaxScore(), equalTo(10));
		assertThat(userQuizUpdate.getQuizComplete(), equalTo(true));
		
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuiz.getUserID(), equalTo(userID));
		assertThat(userQuiz.getQuizID(), equalTo(quizID));
		assertThat(userQuiz.getQuizMaxScore(), equalTo(10));
		assertThat(userQuiz.getQuizComplete(), equalTo(true));
		
		userQuizList = userQuizRepository.findByQuizId(quizID);
		assertNotNull(userQuizList);
		assertThat(userQuizList.size(), equalTo(1));
		assertThat(userQuizList.get(0).getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuizList.get(0).getUserID(), equalTo(userID));
		assertThat(userQuizList.get(0).getQuizID(), equalTo(quizID));
		assertThat(userQuizList.get(0).getQuizMaxScore(), equalTo(10));
		assertThat(userQuizList.get(0).getQuizComplete(), equalTo(true));
		
		
		userQuizRepository.delete(userQuizID);
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNull(userQuiz);
		
		userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
        
        quizRepository.delete(quizID);
		quiz = quizRepository.findById(quizID);
		assertNull(quiz);
	}
	
	@Test
	public void testFindCompletedByUserId() {
		
		assertNotNull(userQuizRepository);
		
		assertNotNull(userRepo);
		
		assertNotNull(quizRepository);

        User user = new User("UserQuizRepositoryImplTest", "Test", "Test", "Test","UserQuizRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));
        Long userID = user.getUserID();
        
		Quiz quiz = new Quiz(
				"UserQuizRepositoryImplTest Quiz","UserQuizRepositoryImplTest Quiz Desc", 1L, 0,1L);
		quiz = quizRepository.save(quiz);
		assertNotNull(quiz);
		assertThat(quiz.getQuizID(), greaterThan(0L));
		final Long quizID = quiz.getQuizID();
		
		UserQuiz userQuiz = new UserQuiz(userID,quizID, 1L, 10, "Some token"); 
		userQuiz = userQuizRepository.save(userQuiz);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), greaterThan(0L));
		final Long userQuizID = userQuiz.getUserQuizID();
		
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuiz.getUserID(), equalTo(userID));
		assertThat(userQuiz.getQuizID(), equalTo(quizID));
		assertThat(userQuiz.getQuizMaxScore(), equalTo(10));
		assertThat(userQuiz.getQuizComplete(), equalTo(false));
        
		List<UserQuiz> userQuizList = userQuizRepository.findCompletedByUserId(userID);
		assertNotNull(userQuizList);
		assertThat(userQuizList.size(), equalTo(0));
		
		
		UserQuiz userQuizUpdate = new UserQuiz(
					userQuiz.getUserQuizID(),
					userQuiz.getUserID(),
					userQuiz.getQuizID(),
					userQuiz.getCategoryID(),
					userQuiz.getQuizStartDate(),
					new Date(),
					userQuiz.getUserQuizToken(),
					10,
					userQuiz.getQuizMaxScore(),
					true
				);
		userQuizUpdate = userQuizRepository.update(userQuizUpdate, userID);
		assertNotNull(userQuizUpdate);
		assertThat(userQuizUpdate.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuizUpdate.getUserID(), equalTo(userID));
		assertThat(userQuizUpdate.getQuizID(), equalTo(quizID));
		assertThat(userQuizUpdate.getQuizMaxScore(), equalTo(10));
		assertThat(userQuizUpdate.getQuizComplete(), equalTo(true));
		
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuiz.getUserID(), equalTo(userID));
		assertThat(userQuiz.getQuizID(), equalTo(quizID));
		assertThat(userQuiz.getQuizMaxScore(), equalTo(10));
		assertThat(userQuiz.getQuizComplete(), equalTo(true));
		
		userQuizList = userQuizRepository.findCompletedByUserId(userID);
		assertNotNull(userQuizList);
		assertThat(userQuizList.size(), equalTo(1));
		assertThat(userQuizList.get(0).getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuizList.get(0).getUserID(), equalTo(userID));
		assertThat(userQuizList.get(0).getQuizID(), equalTo(quizID));
		assertThat(userQuizList.get(0).getQuizMaxScore(), equalTo(10));
		assertThat(userQuizList.get(0).getQuizComplete(), equalTo(true));
		
		
		userQuizRepository.delete(userQuizID);
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNull(userQuiz);
		
		userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
        
        quizRepository.delete(quizID);
		quiz = quizRepository.findById(quizID);
		assertNull(quiz);
	}
	
	@Test
	public void testFindPendingByUserId() {
		
		assertNotNull(userQuizRepository);
		
		assertNotNull(userRepo);
		
		assertNotNull(quizRepository);

        User user = new User("UserQuizRepositoryImplTest", "Test", "Test", "Test","UserQuizRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));
        Long userID = user.getUserID();
        
		Quiz quiz = new Quiz(
				"UserQuizRepositoryImplTest Quiz","UserQuizRepositoryImplTest Quiz Desc", 1L, 0,1L);
		quiz = quizRepository.save(quiz);
		assertNotNull(quiz);
		assertThat(quiz.getQuizID(), greaterThan(0L));
		final Long quizID = quiz.getQuizID();
		
		UserQuiz userQuiz = new UserQuiz(userID,quizID, 1L, 10, "Some token"); 
		userQuiz = userQuizRepository.save(userQuiz);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), greaterThan(0L));
		final Long userQuizID = userQuiz.getUserQuizID();
		
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuiz.getUserID(), equalTo(userID));
		assertThat(userQuiz.getQuizID(), equalTo(quizID));
		assertThat(userQuiz.getQuizMaxScore(), equalTo(10));
		assertThat(userQuiz.getQuizComplete(), equalTo(false));
        
		List<UserQuiz> userQuizList = userQuizRepository.findPendingByUserId(userID);
		assertNotNull(userQuizList);
		assertThat(userQuizList.size(), equalTo(1));
		assertThat(userQuizList.get(0).getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuizList.get(0).getUserID(), equalTo(userID));
		assertThat(userQuizList.get(0).getQuizID(), equalTo(quizID));
		assertThat(userQuizList.get(0).getQuizMaxScore(), equalTo(10));
		assertThat(userQuizList.get(0).getQuizComplete(), equalTo(false));
		
		UserQuiz userQuizUpdate = new UserQuiz(
					userQuiz.getUserQuizID(),
					userQuiz.getUserID(),
					userQuiz.getQuizID(),
					userQuiz.getCategoryID(),
					userQuiz.getQuizStartDate(),
					new Date(),
					userQuiz.getUserQuizToken(),
					10,
					userQuiz.getQuizMaxScore(),
					true
				);
		userQuizUpdate = userQuizRepository.update(userQuizUpdate, userID);
		assertNotNull(userQuizUpdate);
		assertThat(userQuizUpdate.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuizUpdate.getUserID(), equalTo(userID));
		assertThat(userQuizUpdate.getQuizID(), equalTo(quizID));
		assertThat(userQuizUpdate.getQuizMaxScore(), equalTo(10));
		assertThat(userQuizUpdate.getQuizComplete(), equalTo(true));
		
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNotNull(userQuiz);
		assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
		assertThat(userQuiz.getUserID(), equalTo(userID));
		assertThat(userQuiz.getQuizID(), equalTo(quizID));
		assertThat(userQuiz.getQuizMaxScore(), equalTo(10));
		assertThat(userQuiz.getQuizComplete(), equalTo(true));
		
		userQuizList = userQuizRepository.findPendingByUserId(userID);
		assertNotNull(userQuizList);
		assertThat(userQuizList.size(), equalTo(0));
		
		userQuizRepository.delete(userQuizID);
		userQuiz = userQuizRepository.findById(userQuizID);
		assertNull(userQuiz);
		
		userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
        
        quizRepository.delete(quizID);
		quiz = quizRepository.findById(quizID);
		assertNull(quiz);
	}
	
}