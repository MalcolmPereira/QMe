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
			RoleSpringDataRepositoryTest.class,
			RoleRepositoryImplTest.class,
			UserSpringDataRepositoryTest.class, 
			UserRepositoryImplTest.class, 
			UserRoleSpringDataRepositoryTest.class,
			CategorySpringDataRepositoryTest.class, 
			CategoryRepositoryImplTest.class,
			UserRoleRepositoryImplTest.class,
			UserCategorySpringDataRepositoryTest.class,
			UserCategoryRepositoryImplTest.class,
			UserCategoryLikesSpringDataRepositoryTest.class
	}
)
public class AllRepositoryTests {

}
