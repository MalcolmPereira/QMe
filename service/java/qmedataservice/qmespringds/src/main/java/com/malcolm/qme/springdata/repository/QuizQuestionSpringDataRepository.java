/**
 * Name      : com.malcolm.qme.springdata.repository.QuizQuestionSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA QuizQuestionEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malcolm.qme.springdata.entity.QuizQuestionEntity;

/**
 * @Author: Malcolm
 */
interface QuizQuestionSpringDataRepository extends JpaRepository<QuizQuestionEntity, Long> {

	/**
	 * Find By Quiz ID
	 * @param quizId
	 * @return
	 */
	public List<QuizQuestionEntity> findByQuizId(Long quizId);

	/**
	 * Find By Question ID
	 * @param questionId
	 * @return
	 */
	public List<QuizQuestionEntity> findByQuestionId(Long questionId);

}
