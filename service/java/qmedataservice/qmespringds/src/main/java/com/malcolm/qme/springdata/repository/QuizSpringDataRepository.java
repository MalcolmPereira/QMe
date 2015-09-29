/**
 * Name      : com.malcolm.qme.springdata.repository.QuizSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA QuizEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Malcolm
 */
interface QuizSpringDataRepository extends JpaRepository<QuizEntity, Long> {

	/**
	 * Find By Category ID
	 * @param catId Category ID
	 * @return QuizEntity List
	 */
	List<QuizEntity> findByCatId(Long catId);

	/**
	 * Find Most Liked
	 * @return QuizEntity List
	 */
	List<QuizEntity> findTop50ByOrderByQuizLikesDesc();

	/**
	 * @param quizName Quiz Name
	 * @return QuizEntity List
	 */
	List<QuizEntity> findByQuizNameIgnoreCaseLike(String quizName);
}
