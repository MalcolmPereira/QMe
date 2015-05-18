/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizRepositoryImpl.java
 * Date      : 5/8/2015
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.malcolm.qme.core.domain.UserQuiz;
import com.malcolm.qme.core.repository.UserQuizRepository;
import com.malcolm.qme.springdata.entity.UserQuizEntity;

/**
 * @Author: Malcolm
 */
@Repository("UserQuizRepository")
public class UserQuizRepositoryImpl implements UserQuizRepository {
	
	/**
	 * Spring Data UserQuizEntity Repository
	 */
	@Autowired
	private UserQuizSpringDataRepository userQuizSpringDataRepository;

	
	@Override
	public List<UserQuiz> findAll() {
		return null;
	}

	@Override
	public UserQuiz findById(Long id) {
		return null;
	}

	@Override
	public UserQuiz save(UserQuiz t) {
		return null;
	}

	@Override
	public UserQuiz update(UserQuiz t, Long updateUserId) {
		return null;
	}

	@Override
	public void delete(Long id) {
	}

	@Override
	public List<UserQuiz> findByUserId(Long userID) {
		return null;
	}

	@Override
	public List<UserQuiz> findCompletedByUserId(Long userID) {
		return null;
	}

	@Override
	public List<UserQuiz> findPendingByUserId(Long userID) {
		return null;
	}

	@Override
	public List<UserQuiz> findByQuizId(Long quizID) {
		return null;
	}
}
