/**
 * Name      : com.malcolm.qme.springdata.repository.MediaTypeRepositoryImpl.java
 * Date      : 5/12/15
 * Developer : Malcolm
 * Purpose   : QMe MediaType Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.MediaType;
import com.malcolm.qme.core.repository.MediaTypeRepository;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.springdata.entity.MediaTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author malcolm
 *
 */
@Repository("MediaTypeRepository")
public class MediaTypeRepositoryImpl implements MediaTypeRepository {

	/**
	 * Spring Data QuestionEntity Repository
	 */
	@Autowired
	private MediaTypeSpringDataRepository mediaTypeSpringDataRepository;

	@Override
	public List<MediaType> findAll() throws QMeException {
		try{
			return(getMediaType(mediaTypeSpringDataRepository.findAll()));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public MediaType findById(Integer id) throws QMeException {
		try{
			final MediaTypeEntity mediaTypeEntity = mediaTypeSpringDataRepository.findOne(id);
			if(mediaTypeEntity != null){
				return getMediaType(mediaTypeEntity);
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public MediaType save(MediaType mediaType) throws QMeException {
		try{
			MediaTypeEntity mediaTypeEntity = getMediaTypeEntity(mediaType);
			mediaTypeEntity = mediaTypeSpringDataRepository.save(mediaTypeEntity);
			return getMediaType(mediaTypeEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public MediaType update(MediaType mediaType, Long updateUserId) throws QMeException {
		try{
			MediaTypeEntity mediaTypeEntity = getMediaTypeEntity(mediaType);
			mediaTypeEntity = mediaTypeSpringDataRepository.save(mediaTypeEntity);
			return getMediaType(mediaTypeEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public void delete(Integer id) throws QMeException {
		try{
			mediaTypeSpringDataRepository.delete(id);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	/**
	 * Map MediaType Domain Object to MediaTypeEntity
	 *
	 * @param mediaType MediaType
	 * @return MediaTypeEntity
	 */
	private MediaTypeEntity getMediaTypeEntity(MediaType mediaType){
		final MediaTypeEntity mediaTypeEntity = new MediaTypeEntity();
		if(mediaType.getMediaTypeID() > 0 ){
			mediaTypeEntity.setMediaTypeId(mediaType.getMediaTypeID());
		}
		mediaTypeEntity.setMediaMimeType(mediaType.getMediaType());
		return mediaTypeEntity;
	}

	/**
	 * Map MediaTypeEntity to MediaType Domain Object
	 *
	 * @param mediaTypeEntities MediaTypeEntity List
	 * @return MediaType List
	 */
	private List<MediaType> getMediaType(List<MediaTypeEntity> mediaTypeEntities){
		final List<MediaType> mediaTypeList = new ArrayList<MediaType>();
		if(mediaTypeEntities == null){
			return mediaTypeList;
		}
		for (final MediaTypeEntity mediaTypeEntity : mediaTypeEntities){
			mediaTypeList.add(getMediaType(mediaTypeEntity));
		}
		return mediaTypeList;

	}

	/**
	 * Map MediaTypeEntity to MediaType Domain Object
	 *
	 * @param mediaTypeEntity MediaTypeEntity
	 * @return MediaType
	 */
	private MediaType getMediaType(MediaTypeEntity mediaTypeEntity){
		return new MediaType(mediaTypeEntity.getMediaTypeId(),mediaTypeEntity.getMediaMimeType());
	}

}
