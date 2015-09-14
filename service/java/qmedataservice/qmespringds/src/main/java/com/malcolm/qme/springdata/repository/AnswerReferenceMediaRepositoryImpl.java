/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerReferenceMediaRepositoryImpl.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : QMe Answer Reference Media Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.AnswerReferenceMedia;
import com.malcolm.qme.core.repository.AnswerReferenceMediaRepository;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.springdata.entity.AnswerReferenceMediaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
@Repository(value = "AnswerReferenceMediaRepository")
public class AnswerReferenceMediaRepositoryImpl implements AnswerReferenceMediaRepository {

	/**
	 * Spring Data AnswerReferenceMediaEntity Repository
	 */
	@Autowired
	private AnswerReferenceMediaSpringDataRepository answerReferenceMediaSpringDataRepository;


	@Override
	public List<AnswerReferenceMedia> findByQuestionId(Long questionID) throws QMeException {
		try{

            return(getAnswerReferenceMedia(answerReferenceMediaSpringDataRepository.findByQuestionId(questionID)));

        }catch(Exception err){
            throw new QMeException(err);
        }
    }

	@Override
	public List<AnswerReferenceMedia> findAll() throws QMeException {
        try{
		    return(getAnswerReferenceMedia(answerReferenceMediaSpringDataRepository.findAll()));
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public AnswerReferenceMedia findById(Long id) throws QMeException {
        try{
		    final AnswerReferenceMediaEntity answerReferenceMediaEntity = answerReferenceMediaSpringDataRepository.findOne(id);
		    if(answerReferenceMediaEntity != null){
			    return getAnswerReferenceMedia(answerReferenceMediaEntity);
		    }
		    return null;
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public AnswerReferenceMedia save(AnswerReferenceMedia answerReferenceMedia) throws QMeException {
		try{
            AnswerReferenceMediaEntity answerReferenceMediaEntity  = getAnswerReferenceMediaEntity(answerReferenceMedia);
		    answerReferenceMediaEntity = answerReferenceMediaSpringDataRepository.save(answerReferenceMediaEntity);
		    return getAnswerReferenceMedia(answerReferenceMediaEntity);
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public AnswerReferenceMedia update(AnswerReferenceMedia answerReferenceMedia, Long updateUserId) throws QMeException {
		try{
            AnswerReferenceMediaEntity answerReferenceMediaEntity  = getAnswerReferenceMediaEntity(answerReferenceMedia);
		    answerReferenceMediaEntity = answerReferenceMediaSpringDataRepository.save(answerReferenceMediaEntity);
		    return getAnswerReferenceMedia(answerReferenceMediaEntity);
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public void delete(Long id) throws QMeException {
        try{
		    answerReferenceMediaSpringDataRepository.delete(id);
        }catch(Exception err){
            throw new QMeException(err);
        }
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
