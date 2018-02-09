/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizRepositoryImpl.java
 * Date      : 5/8/2015
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.UserQuiz;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserQuizRepository;
import com.malcolm.qme.springdata.entity.UserQuizEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Repository(value = "UserQuizRepository")
public class UserQuizRepositoryImpl implements UserQuizRepository {
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(UserQuizRepositoryImpl.class);

	/**
	 * Spring Data UserQuizEntity Repository
	 */
	@Autowired
	private UserQuizSpringDataRepository userQuizSpringDataRepository;


	@Override
	public Long count() throws QMeException {
		return userQuizSpringDataRepository.count();
	}

	@Override
	public List<UserQuiz> findAll() throws QMeException {
        try{
		    return getUserQuiz(userQuizSpringDataRepository.findAll());
	    }catch(Exception err) {
            throw new QMeException(err);
        }
    }

	@Override
	public List<UserQuiz> findAll(PageSort pageSort) throws QMeException {
        try{
	        Sort.Direction direction = (pageSort.getSort())? Sort.Direction.ASC:Sort.Direction.DESC;
            PageRequest pageRequest;
            List<String> sortFieldList = new ArrayList<>();
            setSortFields(pageSort, sortFieldList);
            if(!sortFieldList.isEmpty()){
                pageRequest  =  new PageRequest(pageSort.getPageIndex(), pageSort.getMaxRows(), direction,sortFieldList.toArray(new String[sortFieldList.size()]));
            }else{
                pageRequest  =  new PageRequest(pageSort.getPageIndex(), pageSort.getMaxRows());
            }
            Page<UserQuizEntity> userQuizList = userQuizSpringDataRepository.findAll(pageRequest);
            return (getUserQuiz(userQuizList.getContent()));
        }catch(Exception err){
            throw new QMeException(err);
        }
    }

    @Override
	public List<UserQuiz> findQuizzesForUser(Long userID,PageSort pageSort) throws QMeException {
		try{
	        Sort.Direction direction = (pageSort.getSort())? Sort.Direction.ASC:Sort.Direction.DESC;
		    PageRequest pageRequest;
		    List<String> sortFieldList = new ArrayList<>();
            setSortFields(pageSort, sortFieldList);
            if(!sortFieldList.isEmpty()){
			    pageRequest  =  new PageRequest(pageSort.getPageIndex(), pageSort.getMaxRows(), direction,sortFieldList.toArray(new String[sortFieldList.size()]));
		    }else{
			    pageRequest  =  new PageRequest(pageSort.getPageIndex(), pageSort.getMaxRows());
		    }
            Page<UserQuizEntity> userQuizList = userQuizSpringDataRepository.findQuizzesForUser(userID,pageRequest);
		    return getUserQuiz(userQuizList.getContent());
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
	public List<UserQuiz> findCompletedByUserId(Long userID,PageSort pageSort) throws QMeException {
        try{
	        Sort.Direction direction = (pageSort.getSort())? Sort.Direction.ASC:Sort.Direction.DESC;
            PageRequest pageRequest;
            List<String> sortFieldList = new ArrayList<>();
            setSortFields(pageSort, sortFieldList);
            if(!sortFieldList.isEmpty()){
                pageRequest  =  new PageRequest(pageSort.getPageIndex(), pageSort.getMaxRows(), direction,sortFieldList.toArray(new String[sortFieldList.size()]));
            }else{
                pageRequest  =  new PageRequest(pageSort.getPageIndex(), pageSort.getMaxRows());
            }
            Page<UserQuizEntity> userQuizList = userQuizSpringDataRepository.findCompletedByUserId(userID,pageRequest);
            return getUserQuiz(userQuizList.getContent());
        }catch(Exception err){
            throw new QMeException(err);
        }
	}

	@Override
	public List<UserQuiz> findPendingByUserId(Long userID,PageSort pageSort) throws QMeException {
        try {
            Sort.Direction direction = (pageSort.getSort()) ? Sort.Direction.ASC : Sort.Direction.DESC;
            PageRequest pageRequest;
            List<String> sortFieldList = new ArrayList<>();
            setSortFields(pageSort, sortFieldList);
            if (!sortFieldList.isEmpty()) {
                pageRequest = new PageRequest(pageSort.getPageIndex(), pageSort.getMaxRows(), direction, sortFieldList.toArray(new String[sortFieldList.size()]));
            } else {
                pageRequest = new PageRequest(pageSort.getPageIndex(), pageSort.getMaxRows());
            }
            Page<UserQuizEntity> userQuizList = userQuizSpringDataRepository.findPendingByUserId(userID, pageRequest);
            return getUserQuiz(userQuizList.getContent());
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
	public UserQuiz save(UserQuiz userQuiz) throws QMeException {
		try{
			UserQuizEntity userQuizEntity = getUserQuizEntity(userQuiz);
			userQuizEntity.setQuizStartDate(LocalDateTime.now());
			userQuizEntity.setQuizUserScore(0);
			userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
			return  getUserQuiz(userQuizEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public UserQuiz update(UserQuiz userQuiz, Long updateUserId) throws QMeException {
		try{
			UserQuizEntity userQuizEntity = getUserQuizEntity(userQuiz);
			userQuizEntity.setQuizEndDate(LocalDateTime.now());
			userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
			return  getUserQuiz(userQuizEntity);
		}catch(Exception err){
			throw new QMeException(err);
		}
	}

	@Override
	public void delete(Long id) throws QMeException{
		try{
			userQuizSpringDataRepository.delete(id);
		}catch(Exception err){
			throw new QMeException(err);
		}
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
		return userQuizEntity;
	}
	
	/**
	 * Map UserQuizEntity to User Quiz Domain Object
	 * 
	 * @param userQuizEntities UserQuizEntity List
	 * @return UserQuiz List
	 */
	private List<UserQuiz> getUserQuiz(List<UserQuizEntity> userQuizEntities) {
		List<UserQuiz> userQuizList = new ArrayList<>();
		if (userQuizEntities == null) {
			return userQuizList;
		}
        userQuizList.addAll(userQuizEntities.stream().filter(userQuiz -> userQuiz != null).map(this::getUserQuiz).collect(Collectors.toList()));
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
                    userQuizEntity.getQuizMaxScore()
            );
   }

    /**
     * Set Sort Fields
     * @param pageSort
     * @param sortFieldList
     */
   private void setSortFields(PageSort pageSort, List<String> sortFieldList) {
        if(pageSort.getSortFields() != null && pageSort.getSortFields().length > 0) {
            String[] sortFields = pageSort.getSortFields();
            for (String sortField : sortFields) {
                try {
                    sortFieldList.add(USERQUIZSORTFIELDS.valueOf(sortField.toUpperCase()).getUserQuizSortField());

                } catch (IllegalArgumentException err) {
                    LOG.debug("Invalid Sort Field "+sortField.toUpperCase()+" Will be ignored");
                }
            }
        }
   }
}
