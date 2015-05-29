/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizLikesRepositoryImpl.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Likes Repository Implementation
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.UserQuizLikes;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserQuizLikesRepository;
import com.malcolm.qme.springdata.entity.UserQuizLikesEntity;
import com.malcolm.qme.springdata.entity.UserQuizLikesEntityId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
@Repository("UserQuizLikesRepository")
public class UserQuizLikesRepositoryImpl implements UserQuizLikesRepository {

	/**
	 * Spring Data UserEntity Repository
	 */
	@Autowired
	private UserQuizLikesSpringDataRepository userQuizLikesSpringDataRepository;

	@Override
	public List<UserQuizLikes> findByUserId(Long userID) throws QMeException {
		try{
			return (getUserQuizLikes(userQuizLikesSpringDataRepository.findByUserId(userID)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<UserQuizLikes> findByQuizId(Long quizID) throws QMeException {
		try{
			return (getUserQuizLikes(userQuizLikesSpringDataRepository.findByQuizId(quizID)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<UserQuizLikes> findAll() throws QMeException {
		try{
			return (getUserQuizLikes(userQuizLikesSpringDataRepository.findAll()));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public UserQuizLikes findById(UserQuizLikes userQuizLikes) throws QMeException {
		try{
			final UserQuizLikesEntityId userQuizLikesEntityId = getUserQuizLikesEntityId(userQuizLikes);
			final UserQuizLikesEntity userQuizLikesEntity = userQuizLikesSpringDataRepository.findOne(userQuizLikesEntityId);
			if(userQuizLikesEntity != null){
				return getUserQuizLikes(userQuizLikesEntity);
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public UserQuizLikes save(UserQuizLikes userQuizLikes) throws QMeException {
		try{
			UserQuizLikesEntity userQuizLikesEntity = getUserQuizLikesEntity(userQuizLikes);
			userQuizLikesEntity = userQuizLikesSpringDataRepository.save(userQuizLikesEntity);
			return getUserQuizLikes(userQuizLikesEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public UserQuizLikes update(UserQuizLikes userQuizLikes, Long updateUserId) throws QMeException {
		try{
			UserQuizLikesEntity userQuizLikesEntity = getUserQuizLikesEntity(userQuizLikes);
			userQuizLikesEntity = userQuizLikesSpringDataRepository.save(userQuizLikesEntity);
			return getUserQuizLikes(userQuizLikesEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public void delete(UserQuizLikes userQuizLikes) throws QMeException {
		try{
			final UserQuizLikesEntityId userQuizLikesEntityId = getUserQuizLikesEntityId(userQuizLikes);
			userQuizLikesSpringDataRepository.delete(userQuizLikesEntityId);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}


	/**
	 * Map UserQuizLikes Domain Object to UserQuizLikesEntityId
	 *
	 * @param userQuizLikes User Quiz Likes
	 * @return User Quiz Likes Entity ID
	 */
	 private UserQuizLikesEntityId getUserQuizLikesEntityId(UserQuizLikes userQuizLikes) {
		 final UserQuizLikesEntityId userQuizLikesEntityId = new UserQuizLikesEntityId();
		 userQuizLikesEntityId.setUserId(userQuizLikes.getUserID());
		 userQuizLikesEntityId.setQuizId(userQuizLikes.getQuizID());
		 return userQuizLikesEntityId;
	 }

	 /**
	  * Map UserQuizLikes Domain Object to UserQuizLikesEntity
	  *
	  * @param userQuizLikes UserQuizLikes
	  * @return UserQuizLikesEntity
	  */
	 private UserQuizLikesEntity getUserQuizLikesEntity(UserQuizLikes userQuizLikes) {
		 final UserQuizLikesEntity userQuizLikesEntity = new UserQuizLikesEntity();
		 final UserQuizLikesEntityId userQuizLikesEntityId = new UserQuizLikesEntityId();
		 userQuizLikesEntityId.setUserId(userQuizLikes.getUserID());
		 userQuizLikesEntityId.setQuizId(userQuizLikes.getQuizID());
		 userQuizLikesEntity.setId(userQuizLikesEntityId);
		 return userQuizLikesEntity;
	 }

	 /**
	  * Map UserQuizLikesEntity to UserQuizLikes Domain Object
	  *
	  * @param userQuizLikesEntities UserQuizLikesEntity List
	  * @return UserQuizLikes List
	  */
	 private List<UserQuizLikes> getUserQuizLikes(List<UserQuizLikesEntity> userQuizLikesEntities) {
		 final List<UserQuizLikes> userQuizLikesList = new ArrayList<>();
		 if (userQuizLikesEntities == null) {
			 return userQuizLikesList;
		 }
		 for (final UserQuizLikesEntity userQuizLikesEntity : userQuizLikesEntities) {
			 userQuizLikesList.add(getUserQuizLikes(userQuizLikesEntity));
		 }
		 return userQuizLikesList;
	 }

	 /**
	  * Map UserQuizLikesEntity to UserQuizLikes Domain Object
	  *
	  * @param userQuizLikesEntity UserQuizLikesEntity
	  * @return UserQuizLikes
	  */
	 private UserQuizLikes getUserQuizLikes(UserQuizLikesEntity userQuizLikesEntity) {
		 return new UserQuizLikes(
				 userQuizLikesEntity.getId().getUserId(),
				 userQuizLikesEntity.getId().getQuizId()
				 );
	 }
}
