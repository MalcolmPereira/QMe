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
 * @author Malcolm
 */
interface QuizQuestionSpringDataRepository extends JpaRepository<QuizQuestionEntity, Long> {

	/**
	 * Find By Quiz ID
	 * @param quizId Quiz ID
	 * @return QuizQuestionEntity
	 */
	List<QuizQuestionEntity> findByQuizId(Long quizId);

	/**
	 * Find By Question ID
	 * @param questionId Question ID
	 * @return QuizQuestionEntity
	 */
	List<QuizQuestionEntity> findByQuestionId(Long questionId);

}
