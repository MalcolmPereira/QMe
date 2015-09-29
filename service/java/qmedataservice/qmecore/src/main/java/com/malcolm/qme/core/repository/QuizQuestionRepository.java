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
 * @author Malcolm
 */
public interface QuizQuestionRepository extends QMeRepository<QuizQuestion,Long> {
    /**
     * Find By Quiz Id
     * @param quizID Quiz Id
     * @return QuizQuestion List for Quiz ID
     * @throws QMeException
     */
    List<QuizQuestion> findByQuizId(Long quizID) throws QMeException;

    /**
     * Find By Question Id
     * @param questionID Question Id
     * @return QuizQuestion List for Question ID
     * @throws QMeException
     */
    List<QuizQuestion> findByQuestionId(Long questionID) throws QMeException;
}
