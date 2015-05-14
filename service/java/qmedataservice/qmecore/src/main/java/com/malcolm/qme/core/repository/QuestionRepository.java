/**
 * Name      : com.malcolm.qme.core.repository.QuestionRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Question Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.Question;

import java.util.List;

/**
 * @Author: Malcolm
 */
public interface QuestionRepository extends QMeRepository<Question,Long> {

    /**
     * Find By Category
     *
     * @param categoryID
     * @return
     */
    public List<Question> findByCategoryId(Long categoryID);

    /**
     * Find By Most Liked
     *
     * @return
     */
    public List<Question> findByMostLiked();
}
