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
 * @Author: Malcolm
 */
@RunWith(Suite.class)
@SuiteClasses(
		{ 
			//AnswerOptionMediaSpringDataRepositoryTest.class,
			CategoryRepositoryImplTest.class,
			CategorySpringDataRepositoryTest.class, 
			RoleRepositoryImplTest.class,
			RoleSpringDataRepositoryTest.class,
			UserCategoryRepositoryImplTest.class,
			UserCategorySpringDataRepositoryTest.class,
			UserRoleRepositoryImplTest.class,
			UserRoleSpringDataRepositoryTest.class,
			UserRepositoryImplTest.class, 
			UserSpringDataRepositoryTest.class 
	}
)
public class AllRepositoryTests {

}
