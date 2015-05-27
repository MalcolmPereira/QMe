/**
 * Name      : com.malcolm.qme.springdata.repository.UserCategoryLikesRepositoryImpl.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : QMe User Category Likes Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.UserCategoryLikes;
import com.malcolm.qme.core.repository.UserCategoryLikesRepository;
import com.malcolm.qme.springdata.entity.UserCategoryLikesEntity;
import com.malcolm.qme.springdata.entity.UserCategoryLikesEntityId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
@Repository("UserCategoryLikesRepository")
public class UserCategoryLikesRepositoryImpl implements UserCategoryLikesRepository {
	
	/**
     * Spring Data UserEntity Repository
     */
    @Autowired
    private UserCategoryLikesSpringDataRepository userCategoryLikesSpringDataRepository;
	
	@Override
	public List<UserCategoryLikes> findAll() {
		return(getUserCategoryLikes(userCategoryLikesSpringDataRepository.findAll()));
	}
	
	@Override
	public List<UserCategoryLikes> findByUserId(Long userID) {
		return(getUserCategoryLikes(userCategoryLikesSpringDataRepository.findByUserId(userID)));
	}

	@Override
	public UserCategoryLikes findById(UserCategoryLikes userCategoryLikes) {
		UserCategoryLikesEntityId userCategoryLikesEntityId = getUserCategoryLikesEntityId(userCategoryLikes);
		UserCategoryLikesEntity userCategoryLikesEntity = userCategoryLikesSpringDataRepository.findOne(userCategoryLikesEntityId);
		if(userCategoryLikesEntity != null){
			return getUserCategoryLikes(userCategoryLikesEntity);
		}
		return null;
	}

	@Override
	public UserCategoryLikes save(UserCategoryLikes userCategoryLikes) {
		UserCategoryLikesEntity userCategoryLikesEntity = getUserCategoryLikesEntity(userCategoryLikes);
		userCategoryLikesEntity = userCategoryLikesSpringDataRepository.save(userCategoryLikesEntity);
		return getUserCategoryLikes(userCategoryLikesEntity);
	}

	@Override
	public UserCategoryLikes update(UserCategoryLikes userCategoryLikes, Long updateUserId) {
		UserCategoryLikesEntity userCategoryLikesEntity = getUserCategoryLikesEntity(userCategoryLikes);
		userCategoryLikesEntity = userCategoryLikesSpringDataRepository.save(userCategoryLikesEntity);
		return getUserCategoryLikes(userCategoryLikesEntity);
	}

	@Override
	public void delete(UserCategoryLikes userCategoryLikes) {
		UserCategoryLikesEntityId userCategoryLikesEntityId = getUserCategoryLikesEntityId(userCategoryLikes);
		userCategoryLikesSpringDataRepository.delete(userCategoryLikesEntityId);
	}

	/**
	 * Map UserCategoryLikes Domain Object to UserCategoryLikesEntityId
	 * 
	 * @param userCategoryLikes
	 * @return
	 */
	private UserCategoryLikesEntityId getUserCategoryLikesEntityId(UserCategoryLikes userCategoryLikes){
		UserCategoryLikesEntityId userCategoryLikesEntityId = new UserCategoryLikesEntityId();
		userCategoryLikesEntityId.setUserId(userCategoryLikes.getUserID());
		userCategoryLikesEntityId.setCatId(userCategoryLikes.getCategoryID());
		return userCategoryLikesEntityId;
	}
	
	/**
	 * Map UserCategoryLikes Domain Object to UserCategoryLikesEntity
	 * 
	 * @param userCategoryLikes UserCategoryLikes
	 * @return UserCategoryLikesEntity
	 */
	private UserCategoryLikesEntity getUserCategoryLikesEntity(UserCategoryLikes userCategoryLikes){
		UserCategoryLikesEntity userCategoryLikesEntity = new UserCategoryLikesEntity();
		UserCategoryLikesEntityId id = new UserCategoryLikesEntityId();
		id.setUserId(userCategoryLikes.getUserID());
		id.setCatId(userCategoryLikes.getCategoryID());
		userCategoryLikesEntity.setId(id);
		return userCategoryLikesEntity;
	}
	
	/**
	 * Map UserCategoryLikesEntity to UserCategoryLikes Domain Object
	 * 
	 * @param userCategoryLikesEntities UserCategoryLikesEntity List
	 * @return UserCategoryLikes List
	 */
	private List<UserCategoryLikes> getUserCategoryLikes(List<UserCategoryLikesEntity> userCategoryLikesEntities){
		List<UserCategoryLikes> userCategoryLikesList = new ArrayList<UserCategoryLikes>();
        if(userCategoryLikesEntities == null){
            return userCategoryLikesList;
        }
        for (UserCategoryLikesEntity userCategoryLikesEntity : userCategoryLikesEntities){
        	userCategoryLikesList.add(getUserCategoryLikes(userCategoryLikesEntity));
        }
        return userCategoryLikesList;
	}
	
	/**
	 * Map UserCategoryLikesEntity to UserCategoryLikes Domain Object
	 * 
	 * @param userCategoryLikesEntity UserCategoryLikesEntity
	 * @return UserCategoryLikes
	 */
	private UserCategoryLikes getUserCategoryLikes(UserCategoryLikesEntity userCategoryLikesEntity){
		return new UserCategoryLikes(
			userCategoryLikesEntity.getId().getUserId(),
			userCategoryLikesEntity.getId().getCatId()
		);
	}

}
