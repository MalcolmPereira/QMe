/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizLikesRepositoryImplTest.java
 * Date      : May 17, 2015
 * Developer : Malcolm
 * Purpose   : Tests for UserQuizLikes Repository
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.malcolm.qme.core.domain.UserQuizLikes;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.core.repository.QuizRepository;
import com.malcolm.qme.core.repository.UserQuizLikesRepository;
import com.malcolm.qme.core.repository.UserRepository;
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
public class UserQuizLikesRepositoryImplTest {
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

	/**
	 * Category Repository
	 */
	@Autowired
	@Qualifier("CategoryRepository")
	private CategoryRepository categoryRepo;

	/**
	 * UserQuizLikes Repository
	 */
	@Autowired
	@Qualifier("UserQuizLikesRepository")
	private UserQuizLikesRepository userQuizLikesRepository;

	@Test
	public void testFindAll(){
		assertNotNull(userQuizLikesRepository);
		final List<UserQuizLikes> userQuizLikesList = userQuizLikesRepository.findAll();
		assertNotNull(userQuizLikesList);
		assertThat(userQuizLikesList.size(), greaterThan(0));
	}

	@Test
	public void testFindById(){
		assertNotNull(userQuizLikesRepository);
		UserQuizLikes userQuizLikes = new UserQuizLikes(1L,1L);
		userQuizLikes = userQuizLikesRepository.findById(userQuizLikes);
		assertNotNull(userQuizLikes);
		assertThat(userQuizLikes.getUserID(), equalTo(1L));
		assertThat(userQuizLikes.getQuizID(), equalTo(1L));
	}

}
