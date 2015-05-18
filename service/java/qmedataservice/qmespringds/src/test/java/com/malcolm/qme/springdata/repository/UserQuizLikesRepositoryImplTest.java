/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizLikesRepositoryImplTest.java
 * Date      : May 17, 2015
 * Developer : Malcolm
 * Purpose   : Tests for UserQuizLikes Repository
 */
package com.malcolm.qme.springdata.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

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


}
