/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuestionLikesRepositoryImpl.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : QMe User Question Likes Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.UserQuestionLikes;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserQuestionLikesRepository;
import com.malcolm.qme.springdata.entity.UserQuestionLikesEntity;
import com.malcolm.qme.springdata.entity.UserQuestionLikesEntityId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
@Repository(value = "UserQuestionLikesRepository")
public class UserQuestionLikesRepositoryImpl implements UserQuestionLikesRepository {
	
	/**
     * Spring Data UserQuestionLikesEntity Repository
     */
    @Autowired
    private UserQuestionLikesSpringDataRepository userQuestionLikesSpringDataRepository;
	
    @Override
	public List<UserQuestionLikes> findAll() throws QMeException {
		try{
    		return(getUserQuestionLikes(userQuestionLikesSpringDataRepository.findAll()));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}
    
    @Override
	public List<UserQuestionLikes> findByUserId(Long userID) throws QMeException {
		try{
    		return(getUserQuestionLikes(userQuestionLikesSpringDataRepository.findByUserId(userID)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<UserQuestionLikes> findByQuestionId(Long questionID) throws QMeException {
		try{
			return(getUserQuestionLikes(userQuestionLikesSpringDataRepository.findByQuestionId(questionID)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public UserQuestionLikes findById(UserQuestionLikes userQuestionLikes) throws QMeException {
		try{
			UserQuestionLikesEntityId  userQuestionLikesEntityId = getUserQuestionLikesEntityId(userQuestionLikes);
			UserQuestionLikesEntity userQuestionLikesEntity = userQuestionLikesSpringDataRepository.findOne(userQuestionLikesEntityId);
			if(userQuestionLikesEntity != null){
				return getUserQuestionLikes(userQuestionLikesEntity);
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public UserQuestionLikes save(UserQuestionLikes userQuestionLikes) throws QMeException {
		try{
			UserQuestionLikesEntity userQuestionLikesEntity = getUserQuestionLikesEntity(userQuestionLikes);
			userQuestionLikesEntity =  userQuestionLikesSpringDataRepository.save(userQuestionLikesEntity);
			return getUserQuestionLikes(userQuestionLikesEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public UserQuestionLikes update(UserQuestionLikes userQuestionLikes, Long updateUserId) throws QMeException {
		try{
			UserQuestionLikesEntity userQuestionLikesEntity = getUserQuestionLikesEntity(userQuestionLikes);
			userQuestionLikesEntity =  userQuestionLikesSpringDataRepository.save(userQuestionLikesEntity);
			return getUserQuestionLikes(userQuestionLikesEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public void delete(UserQuestionLikes userQuestionLikes) throws QMeException {
		try{
			UserQuestionLikesEntityId  userQuestionLikesEntityId = getUserQuestionLikesEntityId(userQuestionLikes);
			userQuestionLikesSpringDataRepository.delete(userQuestionLikesEntityId);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	/**
	 * Map UserQuestionLikes Domain Object to UserQuestionLikesEntityId 
	 * 
	 * @param userQuestionLikes
	 * @return
	 */
	private UserQuestionLikesEntityId getUserQuestionLikesEntityId(UserQuestionLikes userQuestionLikes){
		UserQuestionLikesEntityId  userQuestionLikesEntityId = new UserQuestionLikesEntityId();
		userQuestionLikesEntityId.setUserId(userQuestionLikes.getUserID());
		userQuestionLikesEntityId.setQuestionId(userQuestionLikes.getQuestionID());
		return  userQuestionLikesEntityId;
		
	}
	
	/**
	 * Map UserQuestionLikes Domain Object to UserQuestionLikesEntity 
	 * 
	 * @param userQuestionLikes UserQuestionLikes
	 * @return UserQuestionLikesEntity
	 */
	private UserQuestionLikesEntity getUserQuestionLikesEntity(UserQuestionLikes userQuestionLikes){
		UserQuestionLikesEntity 	userQuestionLikesEntity = new UserQuestionLikesEntity();
		UserQuestionLikesEntityId   userQuestionLikesEntityId = new UserQuestionLikesEntityId();
		userQuestionLikesEntityId.setUserId(userQuestionLikes.getUserID());
		userQuestionLikesEntityId.setQuestionId(userQuestionLikes.getQuestionID());
		userQuestionLikesEntity.setId(userQuestionLikesEntityId);
		return userQuestionLikesEntity;
	}
	
	/**
	 * Map UserQuestionLikesEntity to UserQuestionLikes Domain Object
	 * 
	 * @param userQuestionLikesEntities UserQuestionLikesEntity List
	 * @return UserQuestionLikes List
	 */
	private List<UserQuestionLikes> getUserQuestionLikes(List<UserQuestionLikesEntity> userQuestionLikesEntities){
		List<UserQuestionLikes> userQuestionLikesList = new ArrayList<UserQuestionLikes>();
		if(userQuestionLikesEntities == null){
            return userQuestionLikesList;
        }
		for (UserQuestionLikesEntity userQuestionLikesEntity : userQuestionLikesEntities){
			userQuestionLikesList.add(getUserQuestionLikes(userQuestionLikesEntity));
	    }
	    return userQuestionLikesList;
	}
	
	/**
	 * Map UserQuestionLikesEntity to UserQuestionLikes Domain Object
	 * 
	 * @param userQuestionLikesEntity UserQuestionLikesEntity
	 * @return UserQuestionLikes
	 */
	private UserQuestionLikes getUserQuestionLikes(UserQuestionLikesEntity userQuestionLikesEntity){
		return new UserQuestionLikes(
			userQuestionLikesEntity.getId().getUserId(),
			userQuestionLikesEntity.getId().getQuestionId()
		);
	}
}
