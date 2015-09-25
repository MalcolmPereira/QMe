/**
 * Name      : com.malcolm.qme.springdata.repository.MediaTypeRepositoryImplTest.java
 * Date      : 5/14/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData MediaTypeEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.MediaType;
import com.malcolm.qme.core.repository.MediaTypeRepository;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.MediaTypeEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class MediaTypeRepositoryImplTest {

    /**
     * Question Hit Repository
     */
    @Autowired
    @Qualifier("MediaTypeRepository")
    private MediaTypeRepository mediaTypeRepository;

    @Mock
    private MediaTypeSpringDataRepository mediaTypeSpringDataRepositoryMOCK;

    @InjectMocks
    private MediaTypeRepository mediaTypeRepositoryWithMock;

    @Before
    public void initMocks(){
        mediaTypeRepositoryWithMock = new MediaTypeRepositoryImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws QMeException {
        assertNotNull(mediaTypeRepository);
        final List<MediaType> mediaTypeList = mediaTypeRepository.findAll();
        assertNotNull(mediaTypeList);
        assertThat(mediaTypeList.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(mediaTypeRepository);
        final MediaType mediaType = mediaTypeRepository.findById(1);
        assertNotNull(mediaType);
        assertThat(mediaType.getMediaTypeID(), equalTo(1));
    }

    @Test
    public void testCRUD() throws QMeException {
        assertNotNull(mediaTypeRepository);
        MediaType mediaType = new MediaType("MediaTypeRepositoryImplTest");
        mediaType = mediaTypeRepository.save(mediaType);
        assertNotNull(mediaType);
        assertThat(mediaType.getMediaTypeID(), greaterThan(1));
        final Integer mediaTypeID = mediaType.getMediaTypeID();

        mediaType = mediaTypeRepository.findById(mediaTypeID);
        assertNotNull(mediaType);
        assertThat(mediaType.getMediaTypeID(), equalTo(mediaTypeID));

        mediaTypeRepository.delete(mediaTypeID);

        mediaType = mediaTypeRepository.findById(mediaTypeID);
        assertNull(mediaType);
    }

    @Test
    public void testFindAllNullReturn() throws QMeException {
        when(mediaTypeSpringDataRepositoryMOCK.findAll()).thenReturn(null);
        List<MediaType> mediaTypeList = mediaTypeRepositoryWithMock.findAll();
        verify(mediaTypeSpringDataRepositoryMOCK).findAll();
        assertNotNull(mediaTypeList);
        assertThat(mediaTypeList.size(), equalTo(0));
    }

    @Test(expected = QMeException.class)
    public void testFindAllQMeException() throws QMeException {
        when(mediaTypeSpringDataRepositoryMOCK.findAll()).thenThrow(new RuntimeException("some error"));
        mediaTypeRepositoryWithMock.findAll();
        verify(mediaTypeSpringDataRepositoryMOCK).findAll();
    }

    @Test(expected = QMeException.class)
    public void testFindByIDQMeException() throws QMeException {
        when(mediaTypeSpringDataRepositoryMOCK.findOne(1)).thenThrow(new RuntimeException("some error"));
        mediaTypeRepositoryWithMock.findById(1);
        verify(mediaTypeSpringDataRepositoryMOCK).findOne(1);
    }

    @Test(expected = QMeException.class)
    public void testSaveQMeException() throws QMeException {
        when(mediaTypeSpringDataRepositoryMOCK.save(Matchers.<MediaTypeEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        mediaTypeRepositoryWithMock.save(new MediaType("MediaTypeRepositoryImplTest"));
        verify(mediaTypeSpringDataRepositoryMOCK).save(Matchers.<MediaTypeEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testUpdateQMeException() throws QMeException {
        when(mediaTypeSpringDataRepositoryMOCK.save(Matchers.<MediaTypeEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        mediaTypeRepositoryWithMock.update(new MediaType("MediaTypeRepositoryImplTest"), 1L);
        verify(mediaTypeSpringDataRepositoryMOCK).save(Matchers.<MediaTypeEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testDeleteQMeException() throws QMeException {
        doThrow(new RuntimeException("some error")).when(mediaTypeSpringDataRepositoryMOCK).delete(1);
        mediaTypeRepositoryWithMock.delete(1);
        verify(mediaTypeSpringDataRepositoryMOCK).delete(1);
    }
}
