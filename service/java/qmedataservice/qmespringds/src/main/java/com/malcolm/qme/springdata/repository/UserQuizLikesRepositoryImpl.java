/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizLikesRepositoryImpl.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : QMe User Quiz Likes Repository Implementation
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.UserQuizLikes;
import com.malcolm.qme.core.repository.UserQuizLikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Malcolm
 */
@Repository("UserQuizLikesRepository")
public class UserQuizLikesRepositoryImpl implements UserQuizLikesRepository {

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    private UserQuizLikesSpringDataRepository userQuizLikesSpringDataRepository;

    @Override
    public List<UserQuizLikes> findByUserId(Long userID) {
        return null;
    }

    @Override
    public List<UserQuizLikes> findByQuizId(Long quizID) {
        return null;
    }

    @Override
    public List<UserQuizLikes> findAll() {
        return null;
    }

    @Override
    public UserQuizLikes findById(Long id) {
        return null;
    }

    @Override
    public UserQuizLikes save(UserQuizLikes userQuizLikes) {
        return null;
    }

    @Override
    public UserQuizLikes update(UserQuizLikes userQuizLikes, Long updateUserId) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
