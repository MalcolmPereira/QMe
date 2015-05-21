/**
 * Name      : com.malcolm.qme.springdata.repository.QuestionHitRepositoryImpl.java
 * Date      : 5/5/15
 * Developer : Malcolm
 * Purpose   : QMe Question Hit Repository Implementation
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.QuestionHit;
import com.malcolm.qme.core.repository.QuestionHitRepository;
import com.malcolm.qme.springdata.entity.QuestionHitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
@Repository("QuestionHitRepository")
public class QuestionHitRepositoryImpl implements QuestionHitRepository {

    /**
     * Spring Data CategoryEntity Repository
     */
    @Autowired
    private QuestionHitSpringDataRepository questionHitSpringDataRepository;

    @Override
    public List<QuestionHit> findAll() {
        return(getQuestionHit(questionHitSpringDataRepository.findAll()));
    }

    @Override
    public QuestionHit findById(Long id) {
        QuestionHitEntity questionHitEntity = questionHitSpringDataRepository.findOne(id);
        if(questionHitEntity != null){
            return getQuestionHit(questionHitEntity);
        }
        return null;
    }

    @Override
    public QuestionHit save(QuestionHit questionHit) {
        QuestionHitEntity questionHitEntity =  getQuestionHitEntity(questionHit);
        questionHitEntity = questionHitSpringDataRepository.save(questionHitEntity);
        return getQuestionHit(questionHitEntity);
    }

    @Override
    public QuestionHit update(QuestionHit questionHit, Long updateUserId) {
        QuestionHitEntity questionHitEntity =  getQuestionHitEntity(questionHit);
        questionHitEntity = questionHitSpringDataRepository.save(questionHitEntity);
        return getQuestionHit(questionHitEntity);
    }

    @Override
    public void delete(Long id) {
        questionHitSpringDataRepository.delete(id);
    }

    /**
     * Map QuestionHit Domain Object to QuestionHitEntity
     *
     * @param questionHit QuestionHit
     * @return QuestionHitEntity
     */
    private QuestionHitEntity getQuestionHitEntity(QuestionHit questionHit){
        QuestionHitEntity questionHitEntity = new QuestionHitEntity();
        if(questionHit.getQuestionID() > 0){
            questionHitEntity.setQuestionId(questionHit.getQuestionID());
        }
        if(questionHit.getCategoryID() > 0){
            questionHitEntity.setCatId(questionHit.getCategoryID());
        }
        questionHitEntity.setQuestionHit(questionHit.getQuestionHit());
        questionHitEntity.setRightCount(questionHit.getRightCount());
        questionHitEntity.setWrongCount(questionHit.getWrongCount());
        return questionHitEntity;
    }

    /**
     * Map QuestionHitEntity to QuestionHit Domain Object
     *
     * @param questionHitEntities QuestionHitEntity List
     * @return QuestionHit
     */
    private List<QuestionHit> getQuestionHit(List<QuestionHitEntity> questionHitEntities){
        List<QuestionHit> questionHitList = new ArrayList<QuestionHit>();
        if(questionHitEntities == null){
            return questionHitList;
        }
        for (QuestionHitEntity questionHitEntity : questionHitEntities){
            questionHitList.add(getQuestionHit(questionHitEntity));
        }
        return questionHitList;

    }

    /**
     * Map QuestionHitEntity to QuestionHit Domain Object
     *
     * @param questionHitEntity QuestionHitEntity
     * @return QuestionHit
     */
    private QuestionHit getQuestionHit(QuestionHitEntity questionHitEntity){
        return new QuestionHit(
             questionHitEntity.getQuestionId(),
             questionHitEntity.getCatId(),
             questionHitEntity.getQuestionHit(),
             questionHitEntity.getRightCount(),
             questionHitEntity.getWrongCount()
        );
    }
}
