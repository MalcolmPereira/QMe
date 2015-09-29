/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizGameRepositoryImpl.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Game Repository Implementation
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.UserQuizGame;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserQuizGameRepository;
import com.malcolm.qme.springdata.entity.UserQuizGameEntity;
import com.malcolm.qme.springdata.entity.UserQuizGameEntityId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Repository(value = "UserQuizGameRepository")
public class UserQuizGameRepositoryImpl implements UserQuizGameRepository {
    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    private UserQuizGameSpringDataRepository userQuizGameSpringDataRepository;

    @Override
    public List<UserQuizGame> findAll() throws QMeException {
        try{
            return(getUserQuizGame(userQuizGameSpringDataRepository.findAll()));
        }catch(Exception err){
            throw new QMeException(err);
        }
    }
    
    @Override
    public List<UserQuizGame> findByUserId(Long userID) throws QMeException {
        try{
    	    return(getUserQuizGame(userQuizGameSpringDataRepository.findByUserId(userID)));
        }catch(Exception err){
            throw new QMeException(err);
        }
    }

    @Override
    public UserQuizGame findByGameToken(Long userGameToken) throws QMeException {
        try{
    	    return(getUserQuizGame(userQuizGameSpringDataRepository.findByGameToken(userGameToken)));
        }catch(Exception err){
            throw new QMeException(err);
        }
    }

    @Override
    public UserQuizGame findById(UserQuizGame userQuizGame) throws QMeException {
        try{
            UserQuizGameEntityId id = getUserQuizGameEntityId(userQuizGame);
            UserQuizGameEntity userQuizGameEntity = userQuizGameSpringDataRepository.findOne(id);
            if(userQuizGameEntity != null){
               return getUserQuizGame(userQuizGameEntity);
            }
            return null;
        }catch(Exception err){
            throw new QMeException(err);
        }
    }

    @Override
    public UserQuizGame save(UserQuizGame userQuizGame) throws QMeException {
        try{
            UserQuizGameEntity userQuizGameEntity = getUserQuizGameEntity(userQuizGame);
            userQuizGameEntity.setStartDate(LocalDateTime.now());
            userQuizGameEntity = userQuizGameSpringDataRepository.save(userQuizGameEntity);
            return getUserQuizGame(userQuizGameEntity);
        }catch(Exception err){
            throw new QMeException(err);
        }
    }

    @Override
    public UserQuizGame update(UserQuizGame userQuizGame, Long updateUserId) throws QMeException {
        try{
            UserQuizGameEntity userQuizGameEntity = getUserQuizGameEntity(userQuizGame);
            userQuizGameEntity.setEndDate(LocalDateTime.now());
            userQuizGameEntity = userQuizGameSpringDataRepository.save(userQuizGameEntity);
            return getUserQuizGame(userQuizGameEntity);
        }catch(Exception err){
            throw new QMeException(err);
        }
    }

    @Override
    public void delete(UserQuizGame userQuizGame) throws QMeException {
        try{
            UserQuizGameEntityId id = getUserQuizGameEntityId(userQuizGame);
            userQuizGameSpringDataRepository.delete(id);
        }catch(Exception err){
            throw new QMeException(err);
        }
    }
    
    /**
     * Map UserQuizGame Domain Object to UserQuizGameEntity
     *
     * @param userQuizGame User Quiz Game
     * @return UserQuizGameEntityId
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
        List<UserQuizGame> UserQuizGameList = new ArrayList<>();
        if(userQuizGameEntities == null){
            return UserQuizGameList;
        }
        UserQuizGameList.addAll(userQuizGameEntities.stream().map(this::getUserQuizGame).collect(Collectors.toList()));
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
