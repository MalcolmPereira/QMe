/**
 * Name      : com.malcolm.qme.springdata.repository.MediaTypeRepositoryImplTest.java
 * Date      : 5/14/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData MediaTypeEntity Repository
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.malcolm.qme.core.domain.MediaType;
import com.malcolm.qme.core.repository.MediaTypeRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;

/**
 * @Author: Malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { QMeSpringDataJPAConfig.class })
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
public class MediaTypeRepositoryImplTest {

	/**
	 * Question Hit Repository
	 */
	@Autowired
	@Qualifier("MediaTypeRepository")
	private MediaTypeRepository mediaTypeRepository;

	@Test
	public void testFindAll() {
		assertNotNull(mediaTypeRepository);
		final List<MediaType> mediaTypeList = mediaTypeRepository.findAll();
		assertNotNull(mediaTypeList);
		assertThat(mediaTypeList.size(), greaterThan(0));
	}

	@Test
	public void testFindById() {
		assertNotNull(mediaTypeRepository);
		final MediaType mediaType = mediaTypeRepository.findById(1);
		assertNotNull(mediaType);
		assertThat(mediaType.getMediaTypeID(),equalTo(1));
	}

	@Test
	public void testCRUD() {
		assertNotNull(mediaTypeRepository);
		MediaType mediaType = new MediaType("MediaTypeRepositoryImplTest","MediaTypeRepositoryImplTest");
		mediaType = mediaTypeRepository.save(mediaType);
		assertNotNull(mediaType);
		assertThat(mediaType.getMediaTypeID(),greaterThan(1));
		final Integer mediaTypeID = mediaType.getMediaTypeID();

		mediaType = mediaTypeRepository.findById(mediaTypeID);
		assertNotNull(mediaType);
		assertThat(mediaType.getMediaTypeID(),equalTo(mediaTypeID));

		mediaTypeRepository.delete(mediaTypeID);

		mediaType = mediaTypeRepository.findById(mediaTypeID);
		assertNull(mediaType);
	}

}
