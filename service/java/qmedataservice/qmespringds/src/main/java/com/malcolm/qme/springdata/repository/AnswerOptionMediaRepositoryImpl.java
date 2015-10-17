/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerOptionMediaRepositoryImpl.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : QMe AnswerOptionMediaEntity Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.AnswerOptionMedia;
import com.malcolm.qme.core.repository.AnswerOptionMediaRepository;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.springdata.entity.AnswerOptionMediaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Repository(value = "AnswerOptionMediaRepository")
public class AnswerOptionMediaRepositoryImpl implements AnswerOptionMediaRepository {

	/**
	 * Spring Data AnswerOptionMediaEntity Repository
	 */
	@Autowired
	private AnswerOptionMediaSpringDataRepository answerOptionMediaSpringDataRepository;

	@Override
	public Long count() throws QMeException {
		return answerOptionMediaSpringDataRepository.count();
	}

	@Override
	public List<AnswerOptionMedia> findByAnswerOptionId(Long answerOptionID) throws QMeException {
		try {
			return (getAnswerOptionMedia(answerOptionMediaSpringDataRepository.findByOptionId(answerOptionID)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<AnswerOptionMedia> findAll() throws QMeException {
        try{
		    return(getAnswerOptionMedia(answerOptionMediaSpringDataRepository.findAll()));
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public List<AnswerOptionMedia> findAll(PageSort pageSort) throws QMeException {
		return null;
	}

	@Override
	public AnswerOptionMedia findById(Long id) throws QMeException {
		try{
            final AnswerOptionMediaEntity answerOptionMediaEntity = answerOptionMediaSpringDataRepository.findOne(id);
		    if(answerOptionMediaEntity != null){
			    return getAnswerOptionMedia(answerOptionMediaEntity);
		    }
		    return null;
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public AnswerOptionMedia save(AnswerOptionMedia answerOptionMedia) throws QMeException {
        try{
		    AnswerOptionMediaEntity answerOptionMediaEntity = getAnswerOptionMediaEntity(answerOptionMedia);
		    answerOptionMediaEntity = answerOptionMediaSpringDataRepository.save(answerOptionMediaEntity);
		    return getAnswerOptionMedia(answerOptionMediaEntity);
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public AnswerOptionMedia update(AnswerOptionMedia answerOptionMedia, Long updateUserId) throws QMeException {
        try{
		    AnswerOptionMediaEntity answerOptionMediaEntity = getAnswerOptionMediaEntity(answerOptionMedia);
		    answerOptionMediaEntity = answerOptionMediaSpringDataRepository.save(answerOptionMediaEntity);
		    return getAnswerOptionMedia(answerOptionMediaEntity);
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public void delete(Long id) throws QMeException {
        try{
		    answerOptionMediaSpringDataRepository.delete(id);
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	/**
	 * Map AnswerOptionMedia Domain Object to AnswerOptionMediaEntity
	 *
	 * @param answerOptionMedia AnswerOptionMedia
	 * @return AnswerOptionMedia Entity
	 */
	private AnswerOptionMediaEntity getAnswerOptionMediaEntity(AnswerOptionMedia answerOptionMedia){
		final AnswerOptionMediaEntity answerOptionMediaEntity = new AnswerOptionMediaEntity();

		if(answerOptionMedia.getAnswerOptionMediaID() > 0){
			answerOptionMediaEntity.setOptionMediaId(answerOptionMedia.getAnswerOptionMediaID());
		}
		if(answerOptionMedia.getAnswerOptionID() > 0){
			answerOptionMediaEntity.setOptionId(answerOptionMedia.getAnswerOptionID());
		}
		answerOptionMediaEntity.setMediaTypeId(answerOptionMedia.getMediaTypeID());
		if(answerOptionMedia.getMedia() != null) {
			answerOptionMediaEntity.setOptionMedia(answerOptionMedia.getMedia());
		}
		return answerOptionMediaEntity;
	}

	/**
	 * Map AnswerOptionMediaEntity to AnswerOptionMedia Domain Object
	 *
	 * @param answerOptionMediaEntities AnswerOptionMediaEntity List
	 * @return AnswerOptionMedia List
	 */
	private List<AnswerOptionMedia> getAnswerOptionMedia(List<AnswerOptionMediaEntity> answerOptionMediaEntities){
		final List<AnswerOptionMedia> optionMediaList = new ArrayList<>();
		if(answerOptionMediaEntities == null){
			return optionMediaList;
		}
		optionMediaList.addAll(answerOptionMediaEntities.stream().map(this::getAnswerOptionMedia).collect(Collectors.toList()));
		return optionMediaList;
	}

	/**
	 * Map AnswerOptionMediaEntity to AnswerOptionMedia Domain Object
	 * @param answerOptionMediaEntity AnswerOptionMediaEntity
	 * @return AnswerOptionMedia
	 */
	private AnswerOptionMedia getAnswerOptionMedia(AnswerOptionMediaEntity answerOptionMediaEntity){
		return new AnswerOptionMedia(
				answerOptionMediaEntity.getOptionMediaId(),
				answerOptionMediaEntity.getOptionId(),
				answerOptionMediaEntity.getMediaTypeId(),
				answerOptionMediaEntity.getOptionMedia()
				);
	}
}
