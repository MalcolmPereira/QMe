/**
 * Name      : com.malcolm.qme.core.repository.QuizQuestionRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Question Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.QuizQuestion;

import java.util.List;

/**
 * @Author: Malcolm
 */
public interface QuizQuestionRepository extends QMeRepository<QuizQuestion,Long> {
    /**
     * Find By Quiz Id
     * @param quizID
     * @return
     */
    public List<QuizQuestion> findByQuizId(Long quizID);

    /**
     * Find By Question Id
     * @param questionID
     * @return
     */
    public List<QuizQuestion> findByQuestionId(Long questionID);
}
