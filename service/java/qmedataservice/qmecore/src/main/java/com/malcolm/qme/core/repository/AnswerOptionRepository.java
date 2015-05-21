/**
 * Name      : com.malcolm.qme.core.repository.AnswerOptionRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Answer Option Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.AnswerOption;

import java.util.List;

/**
 * @author Malcolm
 */
public interface AnswerOptionRepository extends QMeRepository<AnswerOption,Long> {

    /**
     * Find By Question Id
     *
     * @param questionID Question ID
     * @return Answer Options for Question ID
     */
    List<AnswerOption> findByQuestionId(Long questionID);
}
