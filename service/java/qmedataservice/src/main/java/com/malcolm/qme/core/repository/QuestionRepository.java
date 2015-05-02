/**
 * Name      : com.malcolm.qme.core.repository.QuestionRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Question Repository Class
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.Question;

import java.util.List;

/**
 * @Author: Malcolm
 */
public interface QuestionRepository extends QMeRepository<Question> {

    /**
     * Find By Category
     *
     * @param categoryID
     * @return
     */
    public List<Question> findByCategoryId(long categoryID);

    /**
     * Find By Most Liked
     *
     * @return
     */
    public List<Question> findByMostLiked();
}
