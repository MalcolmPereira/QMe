/**
 * Name      : com.malcolm.qme.springdata.entity.UserQuizGameRepositoryImpl.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Game Repository Implementation
 */

package com.malcolm.qme.springdata.entity;

import com.malcolm.qme.core.domain.UserQuizGame;
import com.malcolm.qme.core.repository.UserQuizGameRepository;
import com.malcolm.qme.springdata.repository.UserQuizGameSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Malcolm
 */
@Repository("UserQuizGameRepository")
public class UserQuizGameRepositoryImpl implements UserQuizGameRepository {

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    private UserQuizGameSpringDataRepository userQuizGameSpringDataRepository;

    @Override
    public List<UserQuizGame> findByUserId(Long userID) {
        return null;
    }

    @Override
    public UserQuizGame findByGameToken(Long userGameToken) {
        return null;
    }

    @Override
    public List<UserQuizGame> findAll() {
        return(getUserQuizGame(userQuizGameSpringDataRepository.findAll()));
    }

    @Override
    public UserQuizGame findById(Long id) {
        UserQuizGameEntity userQuizGameEntity = userQuizGameSpringDataRepository.findOne(id);
        if(userQuizGameEntity != null){
           return getUserQuizGame(userQuizGameEntity);
        }
        return null;
    }

    @Override
    public UserQuizGame save(UserQuizGame userQuizGame) {
        UserQuizGameEntity userQuizGameEntity = getUserQuizGameEntity(userQuizGame);
        userQuizGameEntity.setStartDate(new Date());
        userQuizGameEntity = userQuizGameSpringDataRepository.save(userQuizGameEntity);
        return getUserQuizGame(userQuizGameEntity);
    }

    @Override
    public UserQuizGame update(UserQuizGame userQuizGame, Long updateUserId) {
        UserQuizGameEntity userQuizGameEntity = getUserQuizGameEntity(userQuizGame);
        userQuizGameEntity.setEndDate(new Date());
        userQuizGameEntity = userQuizGameSpringDataRepository.save(userQuizGameEntity);
        return getUserQuizGame(userQuizGameEntity);
    }

    @Override
    public void delete(Long id) {
        userQuizGameSpringDataRepository.delete(id);
    }

    /**
     * Map UserQuizGame Domain Object to UserQuizGameEntity
     *
     * @param userQuizGame
     * @return
     */
    private UserQuizGameEntity getUserQuizGameEntity(UserQuizGame userQuizGame){
        UserQuizGameEntity userQuizGameEntity = new UserQuizGameEntity();
        UserQuizGameEntityId userQuizGameEntityId = new UserQuizGameEntityId();
        userQuizGameEntityId.setQuizGameToken(userQuizGame.getUserGameToken());
        userQuizGameEntityId.setUserId(userQuizGame.getUserID());
        userQuizGameEntityId.setCatId(userQuizGame.getCategoryID());
        userQuizGameEntity.setUserScore(userQuizGame.getUserGameScore());
        userQuizGameEntity.setStartDate(userQuizGame.getQuizStartDate());
        userQuizGameEntity.setEndDate(userQuizGame.getQuizEndDate());
        return userQuizGameEntity;
    }

    /**
     *
     * Map UserQuizGameEntity to UserQuizGame Domain Object
     *
     * @param userQuizGameEntities
     * @return
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
     * @param userQuizGameEntity
     * @return
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
