/**
 * Name      : com.malcolm.qme.springdata.repository.QuizQuestionSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA QuizQuestionEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.QuizQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Malcolm
 */
@Repository
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
