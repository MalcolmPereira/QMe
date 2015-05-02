/**
 * Name      : com.malcolm.qme.core.repository.QuizRepository.java
 * Date      : 5/2/15
 * Developer : malcolm
 * Purpose   : TODO:
 */
package com.malcolm.qme.core.repository;/**
 * Created by malcolm on 5/2/15.
 */

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.domain.Quiz;

import java.util.List;

/**
 * @Author: malcolm
 */
public interface QuizRepository extends QMeRepository<Quiz> {

    /**
     * Find By Category
     *
     * @param categoryID
     * @return
     */
    public List<Quiz> findByCategoryId(long categoryID);

    /**
     * Find By Most Liked
     *
     * @return
     */
    public List<Quiz> findByMostLiked();

    /**
     * Find By Quiz Name Like
     * @param quizName
     * @return
     */
    public List<Quiz> findQuizNameLike(String quizName);
}
