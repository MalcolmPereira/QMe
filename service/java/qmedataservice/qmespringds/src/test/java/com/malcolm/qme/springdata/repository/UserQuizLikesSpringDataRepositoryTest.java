/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizLikesSpringDataRepositoryTest.java
 * Date      : May 17, 2015
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA UserQuizLikesEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class UserQuizLikesSpringDataRepositoryTest {
    /**
     * UserEntity Repository
     */
    @Autowired
    private UserSpringDataRepository userSpringDataRepo;

    /**
     * QuizEntity Repository
     */
    @Autowired
    private QuizSpringDataRepository quizSpringDataRepository;

    /**
     * UserQuizLikesEntity Repository
     */
    @Autowired
    private UserQuizLikesSpringDataRepository userQuizLikesSpringDataRepository;

    /**
     * CategoryEntity Repository
     */
    @Autowired
    private CategorySpringDataRepository categorySpringDataRepository;

    @Test
    public void testFindAll() {
        assertNotNull(userQuizLikesSpringDataRepository);
        final List<UserQuizLikesEntity> userQuizLikesEntities = userQuizLikesSpringDataRepository.findAll();
        assertNotNull(userQuizLikesEntities);
        assertThat(userQuizLikesEntities.size(), greaterThan(0));
    }

    @Test
    public void testFindById() {
        assertNotNull(userQuizLikesSpringDataRepository);
        final UserQuizLikesEntityId id = new UserQuizLikesEntityId();
        id.setUserId(1L);
        id.setQuizId(1L);
        final UserQuizLikesEntity userQuizLikesEntity = userQuizLikesSpringDataRepository.findOne(id);
        assertNotNull(userQuizLikesEntity);
        assertThat(userQuizLikesEntity.getId().getUserId(), equalTo(1L));
        assertThat(userQuizLikesEntity.getId().getQuizId(), equalTo(1L));
    }

    @Test
    public void testCRUD() {
        assertNotNull(userSpringDataRepo);

        assertNotNull(categorySpringDataRepository);

        assertNotNull(quizSpringDataRepository);

        assertNotNull(userQuizLikesSpringDataRepository);

        UserEntity userEntity = new UserEntity("UserQuizLikesSpringDataRepositoryTest", "Test", "Test", "UserQuizLikesSpringDataRepositoryTest@test.com", "Test", LocalDateTime.now(), LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        final Long userID = userEntity.getUserId();

        CategoryEntity categoryEntity = new CategoryEntity("UserQuizLikesSpringDataRepositoryTest", 0L, LocalDateTime.now(), userID);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), greaterThan(0L));
        final Long catID = categoryEntity.getCatId();

        QuizEntity quizEntity = new QuizEntity(
                "UserQuizLikesSpringDataRepositoryTest Quiz", "UserQuizLikesSpringDataRepositoryTest Quiz Desc", catID, 0L, 0L,
                0, LocalDateTime.now(), userID, LocalDateTime.now(), userID);
        quizEntity = quizSpringDataRepository.save(quizEntity);
        assertNotNull(quizEntity);
        assertThat(quizEntity.getQuizId(), greaterThan(0L));
        final Long quizID = quizEntity.getQuizId();

        UserQuizLikesEntity userQuizLikesEntity = new UserQuizLikesEntity();
        final UserQuizLikesEntityId id = new UserQuizLikesEntityId();
        id.setUserId(userID);
        id.setQuizId(quizID);
        userQuizLikesEntity.setId(id);
        userQuizLikesEntity = userQuizLikesSpringDataRepository.save(userQuizLikesEntity);
        assertNotNull(userQuizLikesEntity);
        assertThat(userQuizLikesEntity.getId().getUserId(), equalTo(userID));
        assertThat(userQuizLikesEntity.getId().getQuizId(), equalTo(quizID));

        userQuizLikesSpringDataRepository.delete(userQuizLikesEntity.getId());
        userQuizLikesEntity = userQuizLikesSpringDataRepository.findOne(id);
        assertNull(userQuizLikesEntity);

        quizSpringDataRepository.delete(quizID);
        quizEntity = quizSpringDataRepository.findOne(quizID);
        assertNull(quizEntity);

        categorySpringDataRepository.delete(catID);
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNull(categoryEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }

    @Test
    public void testFindByUserId() {
        assertNotNull(userSpringDataRepo);

        assertNotNull(categorySpringDataRepository);

        assertNotNull(quizSpringDataRepository);

        assertNotNull(userQuizLikesSpringDataRepository);

        UserEntity userEntity = new UserEntity("UserQuizLikesSpringDataRepositoryTestByUserID", "Test", "Test", "UserQuizLikesSpringDataRepositoryTestByUserID@test.com", "Test", LocalDateTime.now(), LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        final Long userID = userEntity.getUserId();

        CategoryEntity categoryEntity = new CategoryEntity("UserQuizLikesSpringDataRepositoryTestByUserID", 0L, LocalDateTime.now(), userID);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), greaterThan(0L));
        final Long catID = categoryEntity.getCatId();

        QuizEntity quizEntity = new QuizEntity(
                "UserQuizLikesSpringDataRepositoryTestByUserID Quiz", "UserQuizLikesSpringDataRepositoryTestByUserID Quiz Desc", catID, 0L, 0L,
                0, LocalDateTime.now(), userID, LocalDateTime.now(), userID);
        quizEntity = quizSpringDataRepository.save(quizEntity);
        assertNotNull(quizEntity);
        assertThat(quizEntity.getQuizId(), greaterThan(0L));
        final Long quizID = quizEntity.getQuizId();

        UserQuizLikesEntity userQuizLikesEntity = new UserQuizLikesEntity();
        final UserQuizLikesEntityId id = new UserQuizLikesEntityId();
        id.setUserId(userID);
        id.setQuizId(quizID);
        userQuizLikesEntity.setId(id);
        userQuizLikesEntity = userQuizLikesSpringDataRepository.save(userQuizLikesEntity);
        assertNotNull(userQuizLikesEntity);
        assertThat(userQuizLikesEntity.getId().getUserId(), equalTo(userID));
        assertThat(userQuizLikesEntity.getId().getQuizId(), equalTo(quizID));

        final List<UserQuizLikesEntity> userQuizList = userQuizLikesSpringDataRepository.findByUserId(userID);
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(1));
        assertThat(userQuizList.get(0).getId().getUserId(), equalTo(userID));
        assertThat(userQuizList.get(0).getId().getQuizId(), equalTo(quizID));

        userQuizLikesSpringDataRepository.delete(userQuizLikesEntity.getId());
        userQuizLikesEntity = userQuizLikesSpringDataRepository.findOne(id);
        assertNull(userQuizLikesEntity);

        quizSpringDataRepository.delete(quizID);
        quizEntity = quizSpringDataRepository.findOne(quizID);
        assertNull(quizEntity);

        categorySpringDataRepository.delete(catID);
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNull(categoryEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }

    @Test
    public void testFindByQuizId() {
        assertNotNull(userSpringDataRepo);

        assertNotNull(categorySpringDataRepository);

        assertNotNull(quizSpringDataRepository);

        assertNotNull(userQuizLikesSpringDataRepository);

        UserEntity userEntity = new UserEntity("UserQuizLikesSpringDataRepositoryTestByQuizID", "Test", "Test", "UserQuizLikesSpringDataRepositoryTestByQuizID@test.com", "Test", LocalDateTime.now(), LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        final Long userID = userEntity.getUserId();

        CategoryEntity categoryEntity = new CategoryEntity("UserQuizLikesSpringDataRepositoryTestByQuizID", 0L, LocalDateTime.now(), userID);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), greaterThan(0L));
        final Long catID = categoryEntity.getCatId();

        QuizEntity quizEntity = new QuizEntity(
                "UserQuizLikesSpringDataRepositoryTestByQuizID Quiz", "UserQuizLikesSpringDataRepositoryTestByQuizID Quiz Desc", catID, 0L, 0L,
                0, LocalDateTime.now(), userID, LocalDateTime.now(), userID);
        quizEntity = quizSpringDataRepository.save(quizEntity);
        assertNotNull(quizEntity);
        assertThat(quizEntity.getQuizId(), greaterThan(0L));
        final Long quizID = quizEntity.getQuizId();

        UserQuizLikesEntity userQuizLikesEntity = new UserQuizLikesEntity();
        final UserQuizLikesEntityId id = new UserQuizLikesEntityId();
        id.setUserId(userID);
        id.setQuizId(quizID);
        userQuizLikesEntity.setId(id);
        userQuizLikesEntity = userQuizLikesSpringDataRepository.save(userQuizLikesEntity);
        assertNotNull(userQuizLikesEntity);
        assertThat(userQuizLikesEntity.getId().getUserId(), equalTo(userID));
        assertThat(userQuizLikesEntity.getId().getQuizId(), equalTo(quizID));

        final List<UserQuizLikesEntity> userQuizList = userQuizLikesSpringDataRepository.findByQuizId(quizID);
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(1));
        assertThat(userQuizList.get(0).getId().getUserId(), equalTo(userID));
        assertThat(userQuizList.get(0).getId().getQuizId(), equalTo(quizID));

        userQuizLikesSpringDataRepository.delete(userQuizLikesEntity.getId());
        userQuizLikesEntity = userQuizLikesSpringDataRepository.findOne(id);
        assertNull(userQuizLikesEntity);

        quizSpringDataRepository.delete(quizID);
        quizEntity = quizSpringDataRepository.findOne(quizID);
        assertNull(quizEntity);

        categorySpringDataRepository.delete(catID);
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNull(categoryEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }
}
