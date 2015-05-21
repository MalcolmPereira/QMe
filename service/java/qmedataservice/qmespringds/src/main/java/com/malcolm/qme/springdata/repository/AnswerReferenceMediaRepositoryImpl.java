/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerReferenceMediaRepositoryImpl.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : QMe Answer Reference Media Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.malcolm.qme.core.domain.AnswerReferenceMedia;
import com.malcolm.qme.core.repository.AnswerReferenceMediaRepository;
import com.malcolm.qme.springdata.entity.AnswerReferenceMediaEntity;

/**
 * @author Malcolm
 */
@Repository("AnswerReferenceMediaRepository")
public class AnswerReferenceMediaRepositoryImpl implements AnswerReferenceMediaRepository {

	/**
	 * Spring Data AnswerReferenceMediaEntity Repository
	 */
	@Autowired
	private AnswerReferenceMediaSpringDataRepository answerReferenceMediaSpringDataRepository;


	@Override
	public List<AnswerReferenceMedia> findByQuestionId(Long questionID) {
		return(getAnswerReferenceMedia(answerReferenceMediaSpringDataRepository.findByQuestionId(questionID)));
	}

	@Override
	public List<AnswerReferenceMedia> findAll() {
		return(getAnswerReferenceMedia(answerReferenceMediaSpringDataRepository.findAll()));
	}

	@Override
	public AnswerReferenceMedia findById(Long id) {
		final AnswerReferenceMediaEntity answerReferenceMediaEntity = answerReferenceMediaSpringDataRepository.findOne(id);
		if(answerReferenceMediaEntity != null){
			return getAnswerReferenceMedia(answerReferenceMediaEntity);
		}
		return null;
	}

	@Override
	public AnswerReferenceMedia save(AnswerReferenceMedia answerReferenceMedia) {
		AnswerReferenceMediaEntity answerReferenceMediaEntity  = getAnswerReferenceMediaEntity(answerReferenceMedia);
		answerReferenceMediaEntity = answerReferenceMediaSpringDataRepository.save(answerReferenceMediaEntity);
		return getAnswerReferenceMedia(answerReferenceMediaEntity);
	}

	@Override
	public AnswerReferenceMedia update(AnswerReferenceMedia answerReferenceMedia, Long updateUserId) {
		AnswerReferenceMediaEntity answerReferenceMediaEntity  = getAnswerReferenceMediaEntity(answerReferenceMedia);
		answerReferenceMediaEntity = answerReferenceMediaSpringDataRepository.save(answerReferenceMediaEntity);
		return getAnswerReferenceMedia(answerReferenceMediaEntity);
	}

	@Override
	public void delete(Long id) {
		answerReferenceMediaSpringDataRepository.delete(id);
	}

	/**
	 * Map AnswerReferenceMedia Domain Object to AnswerReferenceMediaEntity
	 *
	 * @param answerReferenceMedia AnswerReferenceMedia
	 * @return AnswerReferenceMediaEntity
	 */
	private AnswerReferenceMediaEntity getAnswerReferenceMediaEntity(AnswerReferenceMedia answerReferenceMedia){
		final AnswerReferenceMediaEntity answerReferenceMediaEntity = new AnswerReferenceMediaEntity();
		if(answerReferenceMedia.getAnswerRefMediaID() > 0 ){
			answerReferenceMediaEntity.setAnswerRefMediaId(answerReferenceMedia.getAnswerRefMediaID());
		}
		if(answerReferenceMedia.getQuestionID() > 0 ){
			answerReferenceMediaEntity.setQuestionId(answerReferenceMedia.getQuestionID());
		}
		answerReferenceMediaEntity.setMediaTypeId(answerReferenceMedia.getMediaTypeID());
		if(answerReferenceMedia.getMedia() != null) {
			answerReferenceMediaEntity.setRefMedia(answerReferenceMedia.getMedia());
		}
		return answerReferenceMediaEntity;
	}

	/**
	 * Map AnswerReferenceMediaEntity to AnswerReferenceMedia Domain Object
	 *
	 * @param answerReferenceMediaEntities AnswerReferenceMediaEntity List
	 * @return AnswerReferenceMedia List
	 */
	private List<AnswerReferenceMedia> getAnswerReferenceMedia(List<AnswerReferenceMediaEntity> answerReferenceMediaEntities){
		final List<AnswerReferenceMedia> answerReferenceMediaList = new ArrayList<AnswerReferenceMedia>();
		if(answerReferenceMediaEntities == null){
			return answerReferenceMediaList;
		}
		for (final AnswerReferenceMediaEntity answerReferenceMediaEntity : answerReferenceMediaEntities){
			answerReferenceMediaList.add(getAnswerReferenceMedia(answerReferenceMediaEntity));
		}
		return answerReferenceMediaList;
	}

	/**
	 * Map AnswerReferenceMediaEntity to AnswerReferenceMedia Domain Object
	 *
	 * @param answerReferenceMediaEntity AnswerReferenceMediaEntity
	 * @return AnswerReferenceMedia
	 */
	private AnswerReferenceMedia getAnswerReferenceMedia(AnswerReferenceMediaEntity answerReferenceMediaEntity){
		return new AnswerReferenceMedia(answerReferenceMediaEntity.getAnswerRefMediaId(),
				answerReferenceMediaEntity.getQuestionId(),
				answerReferenceMediaEntity.getMediaTypeId(),
				answerReferenceMediaEntity.getRefMedia());
	}

}
