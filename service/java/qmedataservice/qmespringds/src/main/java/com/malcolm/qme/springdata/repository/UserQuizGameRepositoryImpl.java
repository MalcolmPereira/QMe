/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizGameRepositoryImpl.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Game Repository Implementation
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.UserQuizGame;
import com.malcolm.qme.core.repository.UserQuizGameRepository;
import com.malcolm.qme.springdata.entity.UserQuizGameEntity;
import com.malcolm.qme.springdata.entity.UserQuizGameEntityId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
@Repository("UserQuizGameRepository")
public class UserQuizGameRepositoryImpl implements UserQuizGameRepository {
    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    private UserQuizGameSpringDataRepository userQuizGameSpringDataRepository;

    @Override
    public List<UserQuizGame> findAll() {
        return(getUserQuizGame(userQuizGameSpringDataRepository.findAll()));
    }
    
    @Override
    public List<UserQuizGame> findByUserId(Long userID) {
    	return(getUserQuizGame(userQuizGameSpringDataRepository.findByUserId(userID)));
    }

    @Override
    public UserQuizGame findByGameToken(Long userGameToken) {
    	return(getUserQuizGame(userQuizGameSpringDataRepository.findByGameToken(userGameToken)));
    }

    @Override
    public UserQuizGame findById(UserQuizGame userQuizGame) {
    	UserQuizGameEntityId id = getUserQuizGameEntityId(userQuizGame);
    	UserQuizGameEntity userQuizGameEntity = userQuizGameSpringDataRepository.findOne(id);
        if(userQuizGameEntity != null){
           return getUserQuizGame(userQuizGameEntity);
        }
        return null;
    }

    @Override
    public UserQuizGame save(UserQuizGame userQuizGame) {
        UserQuizGameEntity userQuizGameEntity = getUserQuizGameEntity(userQuizGame);
        userQuizGameEntity.setStartDate(LocalDateTime.now());
        userQuizGameEntity = userQuizGameSpringDataRepository.save(userQuizGameEntity);
        return getUserQuizGame(userQuizGameEntity);
    }

    @Override
    public UserQuizGame update(UserQuizGame userQuizGame, Long updateUserId) {
        UserQuizGameEntity userQuizGameEntity = getUserQuizGameEntity(userQuizGame);
        userQuizGameEntity.setEndDate(LocalDateTime.now());
        userQuizGameEntity = userQuizGameSpringDataRepository.save(userQuizGameEntity);
        return getUserQuizGame(userQuizGameEntity);
    }

    @Override
    public void delete(UserQuizGame userQuizGame) {
    	UserQuizGameEntityId id = getUserQuizGameEntityId(userQuizGame);
        userQuizGameSpringDataRepository.delete(id);
    }
    
    /**
     * Map UserQuizGame Domain Object to UserQuizGameEntity
     *
     * @param userQuizGame
     * @return
     */
    private UserQuizGameEntityId getUserQuizGameEntityId(UserQuizGame userQuizGame){
        UserQuizGameEntityId userQuizGameEntityId = new UserQuizGameEntityId();
        userQuizGameEntityId.setQuizGameToken(userQuizGame.getUserGameToken());
        userQuizGameEntityId.setUserId(userQuizGame.getUserID());
        userQuizGameEntityId.setCatId(userQuizGame.getCategoryID());
        return userQuizGameEntityId;
    }
    
    /**
     * Map UserQuizGame Domain Object to UserQuizGameEntity
     *
     * @param userQuizGame UserQuizGame
     * @return UserQuizGameEntity
     */
    private UserQuizGameEntity getUserQuizGameEntity(UserQuizGame userQuizGame){
        UserQuizGameEntity userQuizGameEntity = new UserQuizGameEntity();
        UserQuizGameEntityId userQuizGameEntityId = getUserQuizGameEntityId(userQuizGame);
        userQuizGameEntity.setId(userQuizGameEntityId);
        userQuizGameEntity.setUserScore(userQuizGame.getUserGameScore());
        userQuizGameEntity.setStartDate(userQuizGame.getQuizStartDate());
        userQuizGameEntity.setEndDate(userQuizGame.getQuizEndDate());
        return userQuizGameEntity;
    }

    /**
     *
     * Map UserQuizGameEntity to UserQuizGame Domain Object
     *
     * @param userQuizGameEntities UserQuizGameEntity List
     * @return UserQuizGame List
     */
    private List<UserQuizGame> getUserQuizGame(List<UserQuizGameEntity> userQuizGameEntities){
        List<UserQuizGame> UserQuizGameList = new ArrayList<UserQuizGame>();
        if(userQuizGameEntities == null){
            return UserQuizGameList;
        }
        for (UserQuizGameEntity userQuizGameEntity : userQuizGameEntities){
            UserQuizGameList.add(getUserQuizGame(userQuizGameEntity));
        }
        return UserQuizGameList;
    }

    /**
     * Map UserQuizGameEntity to UserQuizGame Domain Object
     *
     * @param userQuizGameEntity UserQuizGameEntity
     * @return UserQuizGame
     */
    private UserQuizGame getUserQuizGame(UserQuizGameEntity userQuizGameEntity){
        return new UserQuizGame(
                userQuizGameEntity.getId().getQuizGameToken(),
                userQuizGameEntity.getId().getUserId(),
                userQuizGameEntity.getId().getCatId(),
                userQuizGameEntity.getUserScore(),
                userQuizGameEntity.getStartDate(),
                userQuizGameEntity.getEndDate()
        );

    }
}
