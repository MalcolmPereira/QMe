/**
 * Name      : com.malcolm.qme.core.repository.AnswerOptionMediaRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Answer Option Media Repository Interface
 */

package com.malcolm.qme.core.repository;



import com.malcolm.qme.core.domain.AnswerOptionMedia;

import java.util.List;

/**
 * @Author: Malcolm
 */
public interface AnswerOptionMediaRepository extends QMeRepository<AnswerOptionMedia,Long> {
    /**
     * Find By Question Id
     *
     * @param answerOptionID
     * @return
     */
    public List<AnswerOptionMedia> findByAnswerOptionId(Long answerOptionID);

}
