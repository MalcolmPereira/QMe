/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizGameSpringDataRepositoryTest.java
 * Date      : 5/18/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA UserQuizGameEntity Repository
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
import com.malcolm.qme.springdata.entity.UserEntity;
import com.malcolm.qme.springdata.entity.UserQuizGameEntity;
import com.malcolm.qme.springdata.entity.UserQuizGameEntityId;

/**
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class
})
public class UserQuizGameSpringDataRepositoryTest {
	/**
	 * UserEntity Repository
	 */
	@Autowired
	private UserSpringDataRepository userSpringDataRepo;

	/**
	 * UserQuizGameEntity Repository
	 */
	@Autowired
	private UserQuizGameSpringDataRepository userQuizGameSpringDataRepository;

	@Test
	public void testFindAll(){
		assertNotNull(userQuizGameSpringDataRepository);
		final List<UserQuizGameEntity> userQuizGameEntities = userQuizGameSpringDataRepository.findAll();
		assertNotNull(userQuizGameEntities);
		assertThat(userQuizGameEntities.size(), greaterThan(0));
	}

	@Test
	public void testFindById(){
		assertNotNull(userQuizGameSpringDataRepository);
		final UserQuizGameEntityId id = new UserQuizGameEntityId();
		id.setUserId(1L);
		id.setCatId(1L);
		id.setQuizGameToken(1L);
		final UserQuizGameEntity userQuizGameEntity = userQuizGameSpringDataRepository.findOne(id);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(1L));
	}

	@Test
	public void testCRUD() {

		assertNotNull(userQuizGameSpringDataRepository);

		assertNotNull(userSpringDataRepo);

		UserEntity userEntity = new UserEntity("UserQuizGameSpringDataRepositoryTest",
				"Test", "Test", "UserQuizGameSpringDataRepositoryTest@test.com",
				"Test", new Date(), new Date());
		userEntity = userSpringDataRepo.save(userEntity);
		assertNotNull(userEntity);
		assertThat(userEntity.getUserId(), greaterThan(0L));
		final Long userID = userEntity.getUserId();

		UserQuizGameEntity userQuizGameEntity = new UserQuizGameEntity();
		final UserQuizGameEntityId id = new UserQuizGameEntityId();
		id.setUserId(userID);
		id.setCatId(1L);
		id.setQuizGameToken(1234L);
		userQuizGameEntity.setId(id);
		userQuizGameEntity.setStartDate(new Date());
		userQuizGameEntity.setUserScore(0);

		userQuizGameEntity = userQuizGameSpringDataRepository.save(userQuizGameEntity);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(userID));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(1234L));

		userQuizGameEntity = userQuizGameSpringDataRepository.findOne(id);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(userID));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(1234L));

		userQuizGameEntity.setEndDate(new Date());
		userQuizGameEntity.setUserScore(10);
		userQuizGameEntity = userQuizGameSpringDataRepository.save(userQuizGameEntity);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(userID));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(1234L));

		userQuizGameEntity = userQuizGameSpringDataRepository.findOne(id);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(userID));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(1234L));

		userQuizGameSpringDataRepository.delete(id);
		userQuizGameEntity = userQuizGameSpringDataRepository.findOne(id);
		assertNull(userQuizGameEntity);

		userSpringDataRepo.delete(userID);
		userEntity = userSpringDataRepo.findOne(userID);
		assertNull(userEntity);
	}

	@Test
	public void testFindByUserId() {

		assertNotNull(userQuizGameSpringDataRepository);

		assertNotNull(userSpringDataRepo);

		UserEntity userEntity = new UserEntity("UserQuizGameSpringDataRepositoryTestByID",
				"Test", "Test", "UserQuizGameSpringDataRepositoryTestByID@test.com",
				"Test", new Date(), new Date());
		userEntity = userSpringDataRepo.save(userEntity);
		assertNotNull(userEntity);
		assertThat(userEntity.getUserId(), greaterThan(0L));
		final Long userID = userEntity.getUserId();

		UserQuizGameEntity userQuizGameEntity = new UserQuizGameEntity();
		final UserQuizGameEntityId id = new UserQuizGameEntityId();
		id.setUserId(userID);
		id.setCatId(1L);
		id.setQuizGameToken(12345L);
		userQuizGameEntity.setId(id);
		userQuizGameEntity.setStartDate(new Date());
		userQuizGameEntity.setUserScore(0);

		userQuizGameEntity = userQuizGameSpringDataRepository.save(userQuizGameEntity);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(userID));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(12345L));

		userQuizGameEntity = userQuizGameSpringDataRepository.findOne(id);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(userID));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(12345L));

		List<UserQuizGameEntity> userGameList = userQuizGameSpringDataRepository.findByUserId(userID);
		assertNotNull(userGameList);
		assertThat(userGameList.size(), equalTo(1));
		assertThat(userGameList.get(0).getId().getUserId(), equalTo(userID));
		assertThat(userGameList.get(0).getId().getCatId(), equalTo(1L));
		assertThat(userGameList.get(0).getId().getQuizGameToken(), equalTo(12345L));

		userQuizGameEntity.setEndDate(new Date());
		userQuizGameEntity.setUserScore(10);
		userQuizGameEntity = userQuizGameSpringDataRepository.save(userQuizGameEntity);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(userID));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(12345L));

		userQuizGameEntity = userQuizGameSpringDataRepository.findOne(id);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(userID));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(12345L));

		userGameList = userQuizGameSpringDataRepository.findByUserId(userID);
		assertNotNull(userGameList);
		assertThat(userGameList.size(), equalTo(1));
		assertThat(userGameList.get(0).getId().getUserId(), equalTo(userID));
		assertThat(userGameList.get(0).getId().getCatId(), equalTo(1L));
		assertThat(userGameList.get(0).getId().getQuizGameToken(), equalTo(12345L));

		userQuizGameSpringDataRepository.delete(id);
		userQuizGameEntity = userQuizGameSpringDataRepository.findOne(id);
		assertNull(userQuizGameEntity);

		userSpringDataRepo.delete(userID);
		userEntity = userSpringDataRepo.findOne(userID);
		assertNull(userEntity);

	}

	@Test
	public void testFndByGameToken() {

		assertNotNull(userQuizGameSpringDataRepository);

		assertNotNull(userSpringDataRepo);

		UserEntity userEntity = new UserEntity("UserQuizGameSpringDataRepositoryTestByTK",
				"Test", "Test", "UserQuizGameSpringDataRepositoryTestByTK@test.com",
				"Test", new Date(), new Date());
		userEntity = userSpringDataRepo.save(userEntity);
		assertNotNull(userEntity);
		assertThat(userEntity.getUserId(), greaterThan(0L));
		final Long userID = userEntity.getUserId();

		UserQuizGameEntity userQuizGameEntity = new UserQuizGameEntity();
		final UserQuizGameEntityId id = new UserQuizGameEntityId();
		id.setUserId(userID);
		id.setCatId(1L);
		id.setQuizGameToken(123456L);
		userQuizGameEntity.setId(id);
		userQuizGameEntity.setStartDate(new Date());
		userQuizGameEntity.setUserScore(0);

		userQuizGameEntity = userQuizGameSpringDataRepository.save(userQuizGameEntity);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(userID));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(123456L));

		userQuizGameEntity = userQuizGameSpringDataRepository.findOne(id);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(userID));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(123456L));

		userQuizGameEntity = userQuizGameSpringDataRepository.findByGameToken(123456L);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(userID));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(123456L));


		userQuizGameEntity.setEndDate(new Date());
		userQuizGameEntity.setUserScore(10);
		userQuizGameEntity = userQuizGameSpringDataRepository.save(userQuizGameEntity);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(userID));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(123456L));

		userQuizGameEntity = userQuizGameSpringDataRepository.findOne(id);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(userID));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(123456L));

		userQuizGameEntity = userQuizGameSpringDataRepository.findByGameToken(123456L);
		assertNotNull(userQuizGameEntity);
		assertThat(userQuizGameEntity.getId().getUserId(), equalTo(userID));
		assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
		assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(123456L));

		userQuizGameSpringDataRepository.delete(id);
		userQuizGameEntity = userQuizGameSpringDataRepository.findOne(id);
		assertNull(userQuizGameEntity);

		userSpringDataRepo.delete(userID);
		userEntity = userSpringDataRepo.findOne(userID);
		assertNull(userEntity);
	}
}
