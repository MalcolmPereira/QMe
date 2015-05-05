/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerReferenceMediaRepositoryImpl.java
 * Date      : 5/5/2015
 * Developer : Malcolm
 * Purpose   : QMe Answer Reference Media Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.AnswerReferenceMedia;
import com.malcolm.qme.core.repository.AnswerReferenceMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Malcolm
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
        return null;
    }

    @Override
    public List<AnswerReferenceMedia> findAll() {
        return null;
    }

    @Override
    public AnswerReferenceMedia findById(Long id) {
        return null;
    }

    @Override
    public AnswerReferenceMedia save(AnswerReferenceMedia answerReferenceMedia) {
        return null;
    }

    @Override
    public AnswerReferenceMedia update(AnswerReferenceMedia answerReferenceMedia, Long updateUserId) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
