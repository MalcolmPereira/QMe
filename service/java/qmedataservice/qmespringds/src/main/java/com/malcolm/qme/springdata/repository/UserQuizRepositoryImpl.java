/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizRepositoryImpl.java
 * Date      : 5/8/2015
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.UserQuiz;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserQuizRepository;
import com.malcolm.qme.springdata.entity.UserQuizEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
@Repository("UserQuizRepository")
public class UserQuizRepositoryImpl implements UserQuizRepository {
	
	/**
	 * Spring Data UserQuizEntity Repository
	 */
	@Autowired
	private UserQuizSpringDataRepository userQuizSpringDataRepository;

	
	@Override
	public List<UserQuiz> findAll() throws QMeException {
		try{
			return (getUserQuiz(userQuizSpringDataRepository.findAll()));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<UserQuiz> findByUserId(Long userID) throws QMeException {
		try{
			return (getUserQuiz(userQuizSpringDataRepository.findByUserId(userID)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<UserQuiz> findCompletedByUserId(Long userID) throws QMeException {
		try{
			return (getUserQuiz(userQuizSpringDataRepository.findByUserIdAndQuizComplete(userID,(byte)1)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<UserQuiz> findPendingByUserId(Long userID) throws QMeException {
		try{
			return (getUserQuiz(userQuizSpringDataRepository.findByUserIdAndQuizComplete(userID,(byte)0)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<UserQuiz> findByQuizId(Long quizID) throws QMeException {
		try{
			return (getUserQuiz(userQuizSpringDataRepository.findByQuizId(quizID)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}
	
	@Override
	public UserQuiz findById(Long id) throws QMeException {
		try{
			UserQuizEntity userQuizEntity = userQuizSpringDataRepository.findOne(id);
			if(userQuizEntity != null){
				return  getUserQuiz(userQuizEntity);
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public UserQuiz save(UserQuiz userQuiz) {
		UserQuizEntity userQuizEntity = getUserQuizEntity(userQuiz);
		userQuizEntity.setQuizStartDate(LocalDateTime.now());
		userQuizEntity.setQuizUserScore(0);
		userQuizEntity.setQuizComplete((byte)0);
		userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
		return  getUserQuiz(userQuizEntity);
	}

	@Override
	public UserQuiz update(UserQuiz userQuiz, Long updateUserId) {
		UserQuizEntity userQuizEntity = getUserQuizEntity(userQuiz);
		userQuizEntity.setQuizEndDate(LocalDateTime.now());
		userQuizEntity.setQuizComplete((byte)1);
		userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
		return  getUserQuiz(userQuizEntity);
	}

	@Override
	public void delete(Long id) {
		userQuizSpringDataRepository.delete(id);
	}

	/**
	 * Map User Quiz Domain Object to UserQuizEntity
	 * 
	 * @param userQuiz UserQuiz
	 * @return UserQuizEntity
	 */
	private UserQuizEntity getUserQuizEntity(UserQuiz userQuiz) {
		UserQuizEntity userQuizEntity = new UserQuizEntity();
		if(userQuiz.getUserQuizID() > 0){
			userQuizEntity.setUserQuizId(userQuiz.getUserQuizID());
		}
		userQuizEntity.setUserId(userQuiz.getUserID());
		userQuizEntity.setQuizId(userQuiz.getQuizID());
		userQuizEntity.setCatId(userQuiz.getCategoryID());
		userQuizEntity.setQuizStartDate(userQuiz.getQuizStartDate());
		userQuizEntity.setQuizEndDate(userQuiz.getQuizEndDate());
		userQuizEntity.setQuizToken(userQuiz.getUserQuizToken());
		userQuizEntity.setQuizUserScore(userQuiz.getQuizUserScore());
		userQuizEntity.setQuizMaxScore(userQuiz.getQuizMaxScore());
		if(userQuiz.getQuizComplete()){
			userQuizEntity.setQuizComplete((byte)1);	
		}else{
			userQuizEntity.setQuizComplete((byte)0);
		}
		return userQuizEntity;
	}
	
	/**
	 * Map UserQuizEntity to User Quiz Domain Object
	 * 
	 * @param userQuizEntities UserQuizEntity List
	 * @return UserQuiz List
	 */
	private List<UserQuiz> getUserQuiz(List<UserQuizEntity> userQuizEntities) {
		List<UserQuiz> userQuizList = new ArrayList<UserQuiz>();
		if (userQuizEntities == null) {
			return userQuizList;
		}
		for (UserQuizEntity userQuizEntity : userQuizEntities) {
			userQuizList.add(getUserQuiz(userQuizEntity));
		}
		return userQuizList;
	}
	
	/**
	 * Map UserQuizEntity to User Quiz Domain Object
	 * 
	 * @param userQuizEntity UserQuizEntity
	 * @return UserQuiz
	 */
	private UserQuiz getUserQuiz(UserQuizEntity userQuizEntity) {
		return new UserQuiz(userQuizEntity.getUserQuizId(),
			userQuizEntity.getUserId(),
			userQuizEntity.getQuizId(),
			userQuizEntity.getCatId(),
			userQuizEntity.getQuizStartDate(),
			userQuizEntity.getQuizEndDate(),
			userQuizEntity.getQuizToken(),
			userQuizEntity.getQuizUserScore(),
			userQuizEntity.getQuizMaxScore(),
			userQuizEntity.getQuizComplete() == (byte)0 ? Boolean.FALSE : Boolean.TRUE
		);
	}
}
