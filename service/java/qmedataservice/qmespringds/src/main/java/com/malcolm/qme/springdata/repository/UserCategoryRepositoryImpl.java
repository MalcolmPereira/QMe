/**
 * Name      : com.malcolm.qme.springdata.repository.UserCategoryRepositoryImpl.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : QMe User Category Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.UserCategory;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserCategoryRepository;
import com.malcolm.qme.springdata.entity.UserCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Repository(value = "UserCategoryRepository")
public class UserCategoryRepositoryImpl implements UserCategoryRepository {
	
	 /**
     * Spring Data UserCategoryEntity Repository
     */
    @Autowired
    private UserCategorySpringDataRepository userCategorySpringDataRepository;

	@Override
	public Long count() throws QMeException {
		return userCategorySpringDataRepository.count();
	}

	@Override
	public List<UserCategory> findAll() throws QMeException {
		try{
			return(getUserCategory(userCategorySpringDataRepository.findAll()));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public List<UserCategory> findAll(PageSort pageSort) throws QMeException {
		return null;
	}

	@Override
	public List<UserCategory> findByUserID(Long userID) throws QMeException {
		try{
			return(getUserCategory(userCategorySpringDataRepository.findByUserId(userID)));
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public UserCategory findById(Long id) throws QMeException {
		try{
			UserCategoryEntity userCategoryEntity = userCategorySpringDataRepository.findOne(id);
			if(userCategoryEntity != null){
				return getUserCategory(userCategoryEntity);
			}
			return null;
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public UserCategory update(UserCategory userCategory, Long updateUserId) throws QMeException {
		return save(userCategory);
    }

	@Override
	public UserCategory save(UserCategory userCategory) throws QMeException {
		try{
			UserCategoryEntity userCategoryEntity = getUserCategoryEntity(userCategory);
			userCategoryEntity = userCategorySpringDataRepository.save(userCategoryEntity);
			return getUserCategory(userCategoryEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public void delete(Long id) throws QMeException {
		try{
			userCategorySpringDataRepository.delete(id);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	/**
	 * Map UserCategory Domain Object to UserCategoryEntity 
	 * 
	 * @param userCategory UserCategory
	 * @return UserCategoryEntity
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
	 * @param userCategoryEntities UserCategoryEntity List
	 * @return UserCategory List
	 */
	private List<UserCategory> getUserCategory(List<UserCategoryEntity> userCategoryEntities){
		List<UserCategory> userCategoryList = new ArrayList<>();
		if(userCategoryEntities == null){
            return userCategoryList;
        }
		userCategoryList.addAll(userCategoryEntities.stream().map(this::getUserCategory).collect(Collectors.toList()));
        return userCategoryList;
	}
	
	/**
	 * Map UserCategoryEntity to UserCategory Domain Object
	 * 
	 * @param userCategoryEntity UserCategoryEntity
	 * @return UserCategory
	 */
	private UserCategory getUserCategory(UserCategoryEntity userCategoryEntity){
		return new UserCategory(
			userCategoryEntity.getUserCatId(),
			userCategoryEntity.getUserId(),
			userCategoryEntity.getCatId()
		);
		
	}

}
