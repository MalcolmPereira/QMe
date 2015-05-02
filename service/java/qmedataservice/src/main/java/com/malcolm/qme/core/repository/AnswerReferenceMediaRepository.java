/**
 * Name      : com.malcolm.qme.core.repository.AnswerReferenceMediaRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Answer Reference Media Repository Class
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.AnswerReferenceMedia;

import java.util.List;

/**
 * @Author: Malcolm
 */
public interface AnswerReferenceMediaRepository extends QMeRepository<AnswerReferenceMedia> {

    /**
     * Find By Question Id
     *
     * @param questionID
     * @return
     */
    public List<AnswerReferenceMedia> findByQuestionId(long questionID);
}
