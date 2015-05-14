/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerOptionMediaRepositoryImpl.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : QMe AnswerOptionMediaEntity Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.AnswerOptionMedia;
import com.malcolm.qme.core.domain.MediaTypeEnum;
import com.malcolm.qme.core.repository.AnswerOptionMediaRepository;
import com.malcolm.qme.springdata.entity.AnswerOptionMediaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Malcolm
 */
@Repository("AnswerOptionMediaRepository")
public class AnswerOptionMediaRepositoryImpl implements AnswerOptionMediaRepository {

    /**
     * Spring Data AnswerOptionMediaEntity Repository
     */
    @Autowired
    private AnswerOptionMediaSpringDataRepository answerOptionMediaSpringDataRepository;

    @Override
    public List<AnswerOptionMedia> findByAnswerOptionId(Long answerOptionID) {
        return(getAnswerOptionMedia(answerOptionMediaSpringDataRepository.findByOptionId(answerOptionID)));
    }

    @Override
    public List<AnswerOptionMedia> findAll() {
        return(getAnswerOptionMedia(answerOptionMediaSpringDataRepository.findAll()));
    }

    @Override
    public AnswerOptionMedia findById(Long id) {
        AnswerOptionMediaEntity answerOptionMediaEntity = answerOptionMediaSpringDataRepository.findOne(id);
        if(answerOptionMediaEntity != null){
            return getAnswerOptionMedia(answerOptionMediaEntity);
        }
        return null;
    }

    @Override
    public AnswerOptionMedia save(AnswerOptionMedia answerOptionMedia) {
        AnswerOptionMediaEntity answerOptionMediaEntity = getAnswerOptionMediaEntity(answerOptionMedia);
        answerOptionMediaEntity = answerOptionMediaSpringDataRepository.save(answerOptionMediaEntity);
        return getAnswerOptionMedia(answerOptionMediaEntity);
    }

    @Override
    public AnswerOptionMedia update(AnswerOptionMedia answerOptionMedia, Long updateUserId) {
        AnswerOptionMediaEntity answerOptionMediaEntity = getAnswerOptionMediaEntity(answerOptionMedia);
        answerOptionMediaEntity = answerOptionMediaSpringDataRepository.save(answerOptionMediaEntity);
        return getAnswerOptionMedia(answerOptionMediaEntity);
    }

    @Override
    public void delete(Long id) {
        answerOptionMediaSpringDataRepository.delete(id);
    }

    /**
     * Map AnswerOptionMedia Domain Object to AnswerOptionMediaEntity
     *
     * @param answerOptionMedia
     * @return
     */
    private AnswerOptionMediaEntity getAnswerOptionMediaEntity(AnswerOptionMedia answerOptionMedia){
        AnswerOptionMediaEntity answerOptionMediaEntity = new AnswerOptionMediaEntity();

        if(answerOptionMedia.getAnswerOptionMediaID() > 0){
            answerOptionMediaEntity.setOptionMediaId(answerOptionMedia.getAnswerOptionMediaID());
        }
        if(answerOptionMedia.getAnswerOptionID() > 0){
            answerOptionMediaEntity.setOptionId(answerOptionMedia.getAnswerOptionID());
        }
        if(answerOptionMedia.getMediaType() != null) {
            answerOptionMediaEntity.setMediaTypeId(answerOptionMedia.getMediaType().getId());
        }
        if(answerOptionMedia.getMedia() != null) {
            answerOptionMediaEntity.setOptionMedia(answerOptionMedia.getMedia());
        }
        return answerOptionMediaEntity;
    }

    /**
     * Map AnswerOptionMediaEntity to AnswerOptionMedia Domain Object
     *
     * @param answerOptionMediaEntities
     * @return
     */
    private List<AnswerOptionMedia> getAnswerOptionMedia(List<AnswerOptionMediaEntity> answerOptionMediaEntities){
        List<AnswerOptionMedia> optionMediaList = new ArrayList<AnswerOptionMedia>();
        if(answerOptionMediaEntities == null){
            return optionMediaList;
        }
        for (AnswerOptionMediaEntity answerOptionMediaEntity : answerOptionMediaEntities){
            optionMediaList.add(getAnswerOptionMedia(answerOptionMediaEntity));
        }
        return optionMediaList;
    }

    /**
     * Map AnswerOptionMediaEntity to AnswerOptionMedia Domain Object
     * @param answerOptionMediaEntity
     * @return
     */
    private AnswerOptionMedia getAnswerOptionMedia(AnswerOptionMediaEntity answerOptionMediaEntity){
        return new AnswerOptionMedia(
                answerOptionMediaEntity.getOptionMediaId(),
                answerOptionMediaEntity.getOptionId(),
                MediaTypeEnum.fromId(answerOptionMediaEntity.getMediaTypeId()),
                answerOptionMediaEntity.getOptionMedia()
        );
    }
}
