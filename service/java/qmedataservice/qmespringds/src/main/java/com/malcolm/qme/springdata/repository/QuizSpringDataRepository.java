/**
 * Name      : com.malcolm.qme.springdata.repository.QuizSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA QuizEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malcolm.qme.springdata.entity.QuizEntity;

/**
 * @Author: Malcolm
 */
public interface QuizSpringDataRepository extends JpaRepository<QuizEntity, Long> {
	
	/**
     * Find By Category ID
     * @param catId
     * @return
     */
    public List<QuizEntity> findByCatId(Long catId);
    
    /**
     * Find Most Liked
     * @return
     */
    public List<QuizEntity> findTop50ByOrderByLikes();
    
    /**
     * @param quizName
     * @return
     */
    public List<QuizEntity> findByQuizNameIgnoreCaseLike(String quizName);
}
