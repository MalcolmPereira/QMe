/**
 * Name      : com.malcolm.qme.springdata.repository.MediaTypeSpringDataRepositoryTest.java
 * Date      : 5/14/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA MediaTypeEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

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
import com.malcolm.qme.springdata.entity.MediaTypeEntity;

/**
 * @Author: Malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { QMeSpringDataJPAConfig.class })
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
public class MediaTypeSpringDataRepositoryTest {

	/**
	 * RoleEntity Repository
	 */
	@Autowired
	private MediaTypeSpringDataRepository mediaTypeSpringDataRepository;

	@Test
	public void testFindAll() {
		assertNotNull(mediaTypeSpringDataRepository);
		final List<MediaTypeEntity> mediaTypeEntities = mediaTypeSpringDataRepository.findAll();
		assertNotNull(mediaTypeEntities);
		assertThat(mediaTypeEntities.size(), greaterThan(0));
	}

	@Test
	public void testFindById() {
		assertNotNull(mediaTypeSpringDataRepository);
		final MediaTypeEntity mediaTypeEntity = mediaTypeSpringDataRepository.findOne(1);
		assertNotNull(mediaTypeEntity);
		assertThat(mediaTypeEntity.getMediaTypeId(),equalTo(1));
	}

	@Test
	public void testCRUD() {
		assertNotNull(mediaTypeSpringDataRepository);
		MediaTypeEntity mediaTypeEntity = new MediaTypeEntity("MediaTypeSpringDataRepositoryTest");
		mediaTypeEntity = mediaTypeSpringDataRepository.save(mediaTypeEntity);
		assertNotNull(mediaTypeEntity);
		assertThat(mediaTypeEntity.getMediaTypeId(),greaterThan(1));
		final Integer mediaTypeID = mediaTypeEntity.getMediaTypeId();

		mediaTypeEntity = mediaTypeSpringDataRepository.findOne(mediaTypeID);
		assertNotNull(mediaTypeEntity);
		assertThat(mediaTypeEntity.getMediaTypeId(),equalTo(mediaTypeID));

		mediaTypeSpringDataRepository.delete(mediaTypeID);

		mediaTypeEntity = mediaTypeSpringDataRepository.findOne(mediaTypeID);
		assertNull(mediaTypeEntity);
	}
}
