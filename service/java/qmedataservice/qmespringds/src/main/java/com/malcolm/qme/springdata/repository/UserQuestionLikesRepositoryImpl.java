/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuestionLikesRepositoryImpl.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : QMe User Question Likes Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.malcolm.qme.core.domain.UserQuestionLikes;
import com.malcolm.qme.core.repository.UserQuestionLikesRepository;
import com.malcolm.qme.springdata.entity.UserQuestionLikesEntity;
import com.malcolm.qme.springdata.entity.UserQuestionLikesEntityId;

/**
 * @Author: Malcolm
 */
@Repository("UserQuestionLikesRepository")
public class UserQuestionLikesRepositoryImpl implements UserQuestionLikesRepository {
	
	/**
     * Spring Data UserQuestionLikesEntity Repository
     */
    @Autowired
    private UserQuestionLikesSpringDataRepository userQuestionLikesSpringDataRepository;
	
    @Override
	public List<UserQuestionLikes> findAll() {
    	return(getUserQuestionLikes(userQuestionLikesSpringDataRepository.findAll()));
	}

	@Override
	public UserQuestionLikes findById(Long id) {
		UserQuestionLikesEntity userQuestionLikesEntity = userQuestionLikesSpringDataRepository.findOne(id);  
		if(userQuestionLikesEntity != null){
            return getUserQuestionLikes(userQuestionLikesEntity);
        }
        return null;
	}

	@Override
	public UserQuestionLikes save(UserQuestionLikes userQuestionLikes) {
		UserQuestionLikesEntity userQuestionLikesEntity = getUserQuestionLikesEntity(userQuestionLikes);
		userQuestionLikesEntity =  userQuestionLikesSpringDataRepository.save(userQuestionLikesEntity);
		return getUserQuestionLikes(userQuestionLikesEntity);
	}

	@Override
	public UserQuestionLikes update(UserQuestionLikes userQuestionLikes, Long updateUserId) {
		UserQuestionLikesEntity userQuestionLikesEntity = getUserQuestionLikesEntity(userQuestionLikes);
		userQuestionLikesEntity =  userQuestionLikesSpringDataRepository.save(userQuestionLikesEntity);
		return getUserQuestionLikes(userQuestionLikesEntity);
	}

	@Override
	public void delete(Long id) {
		userQuestionLikesSpringDataRepository.delete(id);
	}

	@Override
	public List<UserQuestionLikes> findByUserId(Long userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserQuestionLikes> findByQuestionId(Long questionID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Map UserQuestionLikes Domain Object to UserQuestionLikesEntity 
	 * 
	 * @param userQuestionLikes
	 * @return
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
	 * @param userQuestionLikesEntities
	 * @return
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
	 * @param userQuestionLikesEntity
	 * @return
	 */
	private UserQuestionLikes getUserQuestionLikes(UserQuestionLikesEntity userQuestionLikesEntity){
		return new UserQuestionLikes(
			userQuestionLikesEntity.getId().getUserId(),
			userQuestionLikesEntity.getId().getQuestionId()
		);
	}
}
