/**
 * Name      : com.malcolm.qme.springdata.repository.UserCategoryLikesRepositoryImpl.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : QMe User Category Likes Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.UserCategoryLikes;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserCategoryLikesRepository;
import com.malcolm.qme.springdata.entity.UserCategoryLikesEntity;
import com.malcolm.qme.springdata.entity.UserCategoryLikesEntityId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Repository(value = "UserCategoryLikesRepository")
public class UserCategoryLikesRepositoryImpl implements UserCategoryLikesRepository {
	
	/**
     * Spring Data UserEntity Repository
     */
    @Autowired
    private UserCategoryLikesSpringDataRepository userCategoryLikesSpringDataRepository;

	@Override
	public Long count() throws QMeException {
		return userCategoryLikesSpringDataRepository.count();
	}

	@Override
	public List<UserCategoryLikes> findAll() throws QMeException {
		try{
			return(getUserCategoryLikes(userCategoryLikesSpringDataRepository.findAll()));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<UserCategoryLikes> findAll(PageSort pageSort) throws QMeException {
		return null;
	}

	@Override
	public List<UserCategoryLikes> findByUserId(Long userID) throws QMeException {
		try{
			return(getUserCategoryLikes(userCategoryLikesSpringDataRepository.findByUserId(userID)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public UserCategoryLikes findById(UserCategoryLikes userCategoryLikes) throws QMeException {
		try{
			UserCategoryLikesEntityId userCategoryLikesEntityId = getUserCategoryLikesEntityId(userCategoryLikes);
			UserCategoryLikesEntity userCategoryLikesEntity = userCategoryLikesSpringDataRepository.findOne(userCategoryLikesEntityId);
			if(userCategoryLikesEntity != null){
				return getUserCategoryLikes(userCategoryLikesEntity);
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public UserCategoryLikes update(UserCategoryLikes userCategoryLikes, Long updateUserId) throws QMeException {
		return save(userCategoryLikes);
	}

	@Override
	public UserCategoryLikes save(UserCategoryLikes userCategoryLikes) throws QMeException {
		try{
			UserCategoryLikesEntity userCategoryLikesEntity = getUserCategoryLikesEntity(userCategoryLikes);
			userCategoryLikesEntity = userCategoryLikesSpringDataRepository.save(userCategoryLikesEntity);
			return getUserCategoryLikes(userCategoryLikesEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public void delete(UserCategoryLikes userCategoryLikes) throws QMeException {
		try{
			UserCategoryLikesEntityId userCategoryLikesEntityId = getUserCategoryLikesEntityId(userCategoryLikes);
			userCategoryLikesSpringDataRepository.delete(userCategoryLikesEntityId);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	/**
	 * Map UserCategoryLikes Domain Object to UserCategoryLikesEntityId
	 * 
	 * @param userCategoryLikes User Categories Like
	 * @return User Categories Likes Entity Id
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
		List<UserCategoryLikes> userCategoryLikesList = new ArrayList<>();
        if(userCategoryLikesEntities == null){
            return userCategoryLikesList;
        }
		userCategoryLikesList.addAll(userCategoryLikesEntities.stream().map(this::getUserCategoryLikes).collect(Collectors.toList()));
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
