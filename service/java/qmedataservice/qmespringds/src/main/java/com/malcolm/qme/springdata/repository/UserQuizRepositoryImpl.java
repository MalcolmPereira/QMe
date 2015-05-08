package com.malcolm.qme.springdata.repository;

import java.util.List;

import com.malcolm.qme.core.domain.UserQuiz;
import com.malcolm.qme.core.repository.UserQuizRepository;

public class UserQuizRepositoryImpl implements UserQuizRepository {

	@Override
	public List<UserQuiz> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserQuiz findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserQuiz save(UserQuiz t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserQuiz update(UserQuiz t, Long updateUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserQuiz> findByUserId(Long userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserQuiz> findCompletedByUserId(Long userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserQuiz> findPendingByUserId(Long userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserQuiz> findByQuizId(Long quizID) {
		// TODO Auto-generated method stub
		return null;
	}

}
