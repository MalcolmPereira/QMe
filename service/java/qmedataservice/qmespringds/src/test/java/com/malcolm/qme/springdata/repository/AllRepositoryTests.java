/**
 * Name      : com.malcolm.qme.springdata.repository.UserCategorySpringDataRepository.java
 * Date      : 5/13/15
 * Developer : Malcolm
 * Purpose   : JPA Repository Tests
 */
package com.malcolm.qme.springdata.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Malcolm
 */
@RunWith(Suite.class)
@SuiteClasses(
		{
			MediaTypeSpringDataRepositoryTest.class,
			MediaTypeRepositoryImplTest.class,
			RoleSpringDataRepositoryTest.class,
			RoleRepositoryImplTest.class,
			UserSpringDataRepositoryTest.class,
			UserRepositoryImplTest.class,
			UserRoleSpringDataRepositoryTest.class,
			UserRoleRepositoryImplTest.class,
			CategorySpringDataRepositoryTest.class,
			CategoryRepositoryImplTest.class,
			UserCategorySpringDataRepositoryTest.class,
			UserCategoryRepositoryImplTest.class,
			UserCategoryLikesSpringDataRepositoryTest.class,
			UserCategoryLikesRepositoryImplTest.class,
			QuestionSpringDataRepositoryTest.class,
			QuestionRepositoryImplTest.class,
			QuestionHitSpringDataRepositoryTest.class,
			QuestionHitRepositoryImplTest.class,
			UserQuestionLikesSpringDataRepositoryTest.class,
			UserQuestionLikesRepositoryImplTest.class,
			AnswerReferenceMediaSpringDataRepositoryTest.class,
			AnswerReferenceMediaRepositoryImplTest.class,
			AnswerOptionSpringDataRepositoryTest.class,
			AnswerOptionRepositoryImplTest.class,
			AnswerOptionMediaSpringDataRepositoryTest.class,
			AnswerOptionMediaRepositoryImplTest.class,
			QuizSpringDataRepositoryTest.class,
			QuizRepositoryImplTest.class,
			QuizQuestionSpringDataRepositoryTest.class,
			QuizQuestionRepositoryImplTest.class,
			UserQuizLikesSpringDataRepositoryTest.class,
			UserQuizLikesRepositoryImplTest.class,
			UserQuizSpringDataRepositoryTest.class,
			UserQuizRepositoryImplTest.class,
			UserQuizGameSpringDataRepositoryTest.class,
			UserQuizGameRepositoryImplTest.class
		}
		)
public class AllRepositoryTests {

}
