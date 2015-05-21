/**
 * Name      : com.malcolm.qme.core.repository.QuizRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.Quiz;

import java.util.List;

/**
 * @author Malcolm
 */
public interface QuizRepository extends QMeRepository<Quiz,Long> {

    /**
     * Find By Category
     *
     * @param categoryID Category ID
     * @return Quiz List for Category ID
     */
    List<Quiz> findByCategoryId(Long categoryID);

    /**
     * Find By Most Liked
     *
     * @return Quiz List sorted by likes.
     */
    List<Quiz> findByMostLiked();

    /**
     * Find By Quiz Name Like
     * @param quizName Quiz Name
     * @return Quiz List matching name like
     */
    List<Quiz> findQuizNameLike(String quizName);
}
