/**
 * Name      : com.malcolm.qme.core.repository.AnswerReferenceMediaRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Answer Reference Media Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.AnswerReferenceMedia;

import java.util.List;

/**
 * @author Malcolm
 */
public interface AnswerReferenceMediaRepository extends QMeRepository<AnswerReferenceMedia,Long> {

    /**
     * Find By Question Id
     *
     * @param questionID Question ID
     * @return Answer Reference Media List for Question ID
     */
    List<AnswerReferenceMedia> findByQuestionId(Long questionID);
}
