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
 * @author Malcolm
 */
public interface AnswerOptionMediaRepository extends QMeRepository<AnswerOptionMedia,Long> {
    /**
     * Find By Question Id
     *
     * @param answerOptionID Answer Option ID
     * @return AnswerOptionMedia List for Answer Option ID
     * @throws QMeException
     */
     List<AnswerOptionMedia> findByAnswerOptionId(Long answerOptionID) throws QMeException;

}
