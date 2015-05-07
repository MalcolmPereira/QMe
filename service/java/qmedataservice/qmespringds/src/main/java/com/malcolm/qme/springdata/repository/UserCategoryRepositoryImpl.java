/**
 * Name      : com.malcolm.qme.springdata.repository.UserCategoryRepositoryImpl.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : QMe User Category Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.malcolm.qme.core.domain.UserCategory;
import com.malcolm.qme.core.repository.UserCategoryRepository;
import com.malcolm.qme.springdata.entity.UserCategoryEntity;

/**
 * @Author: Malcolm
 */
@Repository("UserCategoryRepository")
public class UserCategoryRepositoryImpl implements UserCategoryRepository {
	
	 /**
     * Spring Data UserCategoryEntity Repository
     */
    @Autowired
    private UserCategorySpringDataRepository userCategorySpringDataRepository;
	
	@Override
	public List<UserCategory> findAll() {
		return(getUserCategory(userCategorySpringDataRepository.findAll()));
	}
	
	@Override
	public List<UserCategory> findByUserID(Long userID) {
		return(getUserCategory(userCategorySpringDataRepository.findByUserId(userID)));
	}

	@Override
	public UserCategory findById(Long id) {
		UserCategoryEntity userCategoryEntity = userCategorySpringDataRepository.findOne(id);
		if(userCategoryEntity != null){
			return getUserCategory(userCategoryEntity);
		}
		return null;
	}

	@Override
	public UserCategory save(UserCategory userCategory) {
		UserCategoryEntity userCategoryEntity = getUserCategoryEntity(userCategory);
		userCategoryEntity = userCategorySpringDataRepository.save(userCategoryEntity);
		return getUserCategory(userCategoryEntity);
	}

	@Override
	public UserCategory update(UserCategory userCategory, Long updateUserId) {
		UserCategoryEntity userCategoryEntity = getUserCategoryEntity(userCategory);
		userCategoryEntity = userCategorySpringDataRepository.save(userCategoryEntity);
		return getUserCategory(userCategoryEntity);
	}

	@Override
	public void delete(Long id) {
		userCategorySpringDataRepository.delete(id);
	}

	/**
	 * Map UserCategory Domain Object to UserCategoryEntity 
	 * 
	 * @param userCategory
	 * @return
	 */
	private UserCategoryEntity getUserCategoryEntity(UserCategory userCategory){
		UserCategoryEntity userCategoryEntity = new UserCategoryEntity();
		if(userCategory.getUserCategoryID() > 0){
			userCategoryEntity.setUserCatId(userCategory.getUserCategoryID());
		}
		userCategoryEntity.setUserId(userCategory.getUserID());
		userCategoryEntity.setCatId(userCategory.getCategoryID());
		return userCategoryEntity;
	}
	
	/**
	 * Map UserCategoryEntity to UserCategory Domain Object
	 * 
	 * @param userCategoryEntities
	 * @return
	 */
	private List<UserCategory> getUserCategory(List<UserCategoryEntity> userCategoryEntities){
		List<UserCategory> userCategoryList = new ArrayList<UserCategory>();
		if(userCategoryEntities == null){
            return userCategoryList;
        }
        for (UserCategoryEntity userCategoryEntity : userCategoryEntities){
        	userCategoryList.add(getUserCategory(userCategoryEntity));
        }
        return userCategoryList;
	}
	
	/**
	 * Map UserCategoryEntity to UserCategory Domain Object
	 * 
	 * @param userCategoryEntity
	 * @return
	 */
	private UserCategory getUserCategory(UserCategoryEntity userCategoryEntity){
		return new UserCategory(
			userCategoryEntity.getUserCatId(),
			userCategoryEntity.getUserId(),
			userCategoryEntity.getCatId()
		);
		
	}

}
