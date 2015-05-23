/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuestionLikesSpringDataRepositoryTest.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA UserQuestionLikesEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.QuestionEntity;
import com.malcolm.qme.springdata.entity.UserEntity;
import com.malcolm.qme.springdata.entity.UserQuestionLikesEntity;
import com.malcolm.qme.springdata.entity.UserQuestionLikesEntityId;
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
public class UserQuestionLikesSpringDataRepositoryTest {

    /**
     * UserQuestionLikesEntity Repository
     */
    @Autowired
    private UserQuestionLikesSpringDataRepository userQuestionLikesSpringDataRepo;

    /**
     * UserEntity Repository
     */
    @Autowired
    private UserSpringDataRepository userSpringDataRepo;

    /**
     * QuestionEntity Repository
     */
    @Autowired
    private QuestionSpringDataRepository questionSpringDataRepository;


    @Test
    public void testFindAll() {
        assertNotNull(userQuestionLikesSpringDataRepo);
        final List<UserQuestionLikesEntity> userQuestionLikesEntities = userQuestionLikesSpringDataRepo.findAll();
        assertNotNull(userQuestionLikesEntities);
        assertThat(userQuestionLikesEntities.size(), greaterThan(0));
    }

    @Test
    public void testFindById() {
        assertNotNull(userQuestionLikesSpringDataRepo);
        final UserQuestionLikesEntityId userQuestionLikesEntityId = new UserQuestionLikesEntityId();
        userQuestionLikesEntityId.setUserId(1L);
        userQuestionLikesEntityId.setQuestionId(1L);
        final UserQuestionLikesEntity userQuestionLikesEntity = userQuestionLikesSpringDataRepo.findOne(userQuestionLikesEntityId);
        assertNotNull(userQuestionLikesEntity);
        assertThat(userQuestionLikesEntity.getId().getUserId(), equalTo(1L));
        assertThat(userQuestionLikesEntity.getId().getQuestionId(), equalTo(1L));
    }

    @Test
    public void testCRUD() {

        assertNotNull(userQuestionLikesSpringDataRepo);

        assertNotNull(userSpringDataRepo);

        assertNotNull(questionSpringDataRepository);

        UserEntity userEntity = new UserEntity("UserQuestionLikesSpringDataRepositoryTest", "Test", "Test", "UserQuestionLikesSpringDataRepositoryTest@test.com", "Test", LocalDateTime.now(), LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        final Long userID = userEntity.getUserId();


        QuestionEntity questionEntity = new QuestionEntity(1L,
                "QuestionSpringDataRepositoryTest Question",
                "QuestionSpringDataRepositoryTest Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long questionID = questionEntity.getQuestionId();

        final UserQuestionLikesEntityId userQuestionLikesEntityId = new UserQuestionLikesEntityId();
        userQuestionLikesEntityId.setUserId(userID);
        userQuestionLikesEntityId.setQuestionId(questionID);
        UserQuestionLikesEntity userQuestionLikesEntity = new UserQuestionLikesEntity();
        userQuestionLikesEntity.setId(userQuestionLikesEntityId);
        userQuestionLikesEntity = userQuestionLikesSpringDataRepo.save(userQuestionLikesEntity);
        assertNotNull(userQuestionLikesEntity);
        assertThat(userQuestionLikesEntity.getId().getUserId(), equalTo(userID));
        assertThat(userQuestionLikesEntity.getId().getQuestionId(), equalTo(questionID));

        userQuestionLikesEntity = userQuestionLikesSpringDataRepo.findOne(userQuestionLikesEntityId);
        assertNotNull(userQuestionLikesEntity);
        assertThat(userQuestionLikesEntity.getId().getUserId(), equalTo(userID));
        assertThat(userQuestionLikesEntity.getId().getQuestionId(), equalTo(questionID));

        userQuestionLikesSpringDataRepo.delete(userQuestionLikesEntityId);
        userQuestionLikesEntity = userQuestionLikesSpringDataRepo.findOne(userQuestionLikesEntityId);
        assertNull(userQuestionLikesEntity);

        questionSpringDataRepository.delete(questionID);
        questionEntity = questionSpringDataRepository.findOne(questionID);
        assertNull(questionEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }

    @Test
    public void testFindByUserId() {

        assertNotNull(userQuestionLikesSpringDataRepo);

        assertNotNull(userSpringDataRepo);

        assertNotNull(questionSpringDataRepository);

        UserEntity userEntity = new UserEntity("UQuestLikesSpringDataRepoTestByUserID", "Test", "Test", "UQuestLikesSpringDataRepoTestByUserID@test.com", "Test", LocalDateTime.now(), LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        final Long userID = userEntity.getUserId();


        QuestionEntity questionEntity = new QuestionEntity(1L,
                "UserQuestionLikesSpringDataRepositoryTestByUserID Question",
                "UserQuestionLikesSpringDataRepositoryTestByUserID Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long questionID = questionEntity.getQuestionId();

        final UserQuestionLikesEntityId userQuestionLikesEntityId = new UserQuestionLikesEntityId();
        userQuestionLikesEntityId.setUserId(userID);
        userQuestionLikesEntityId.setQuestionId(questionID);
        UserQuestionLikesEntity userQuestionLikesEntity = new UserQuestionLikesEntity();
        userQuestionLikesEntity.setId(userQuestionLikesEntityId);
        userQuestionLikesEntity = userQuestionLikesSpringDataRepo.save(userQuestionLikesEntity);
        assertNotNull(userQuestionLikesEntity);
        assertThat(userQuestionLikesEntity.getId().getUserId(), equalTo(userID));
        assertThat(userQuestionLikesEntity.getId().getQuestionId(), equalTo(questionID));

        userQuestionLikesEntity = userQuestionLikesSpringDataRepo.findOne(userQuestionLikesEntityId);
        assertNotNull(userQuestionLikesEntity);
        assertThat(userQuestionLikesEntity.getId().getUserId(), equalTo(userID));
        assertThat(userQuestionLikesEntity.getId().getQuestionId(), equalTo(questionID));

        List<UserQuestionLikesEntity> userQuestionLikesEntityList = userQuestionLikesSpringDataRepo.findByUserId(userID);
        assertNotNull(userQuestionLikesEntityList);
        assertThat(userQuestionLikesEntityList.size(), equalTo(1));

        userQuestionLikesEntityList = userQuestionLikesSpringDataRepo.findByQuestionId(questionID);
        assertNotNull(userQuestionLikesEntityList);
        assertThat(userQuestionLikesEntityList.size(), equalTo(1));

        userQuestionLikesSpringDataRepo.delete(userQuestionLikesEntityId);
        userQuestionLikesEntity = userQuestionLikesSpringDataRepo.findOne(userQuestionLikesEntityId);
        assertNull(userQuestionLikesEntity);

        questionSpringDataRepository.delete(questionID);
        questionEntity = questionSpringDataRepository.findOne(questionID);
        assertNull(questionEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }

    @Test
    public void testFindByQuestionId() {

        assertNotNull(userQuestionLikesSpringDataRepo);

        assertNotNull(userSpringDataRepo);

        assertNotNull(questionSpringDataRepository);

        UserEntity userEntity = new UserEntity("UQuestLikesSpringDataRepoTestByQID", "Test", "Test", "UQuestLikesSpringDataRepoTestByQID@test.com", "Test", LocalDateTime.now(), LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        final Long userID = userEntity.getUserId();


        QuestionEntity questionEntity = new QuestionEntity(1L,
                "UQuestLikesSpringDataRepoTestByQID Question",
                "UQuestLikesSpringDataRepoTestByQID Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long questionID = questionEntity.getQuestionId();

        final UserQuestionLikesEntityId userQuestionLikesEntityId = new UserQuestionLikesEntityId();
        userQuestionLikesEntityId.setUserId(userID);
        userQuestionLikesEntityId.setQuestionId(questionID);
        UserQuestionLikesEntity userQuestionLikesEntity = new UserQuestionLikesEntity();
        userQuestionLikesEntity.setId(userQuestionLikesEntityId);
        userQuestionLikesEntity = userQuestionLikesSpringDataRepo.save(userQuestionLikesEntity);
        assertNotNull(userQuestionLikesEntity);
        assertThat(userQuestionLikesEntity.getId().getUserId(), equalTo(userID));
        assertThat(userQuestionLikesEntity.getId().getQuestionId(), equalTo(questionID));

        userQuestionLikesEntity = userQuestionLikesSpringDataRepo.findOne(userQuestionLikesEntityId);
        assertNotNull(userQuestionLikesEntity);
        assertThat(userQuestionLikesEntity.getId().getUserId(), equalTo(userID));
        assertThat(userQuestionLikesEntity.getId().getQuestionId(), equalTo(questionID));

        List<UserQuestionLikesEntity> userQuestionLikesEntityList = userQuestionLikesSpringDataRepo.findByUserId(userID);
        assertNotNull(userQuestionLikesEntityList);
        assertThat(userQuestionLikesEntityList.size(), equalTo(1));

        userQuestionLikesEntityList = userQuestionLikesSpringDataRepo.findByQuestionId(questionID);
        assertNotNull(userQuestionLikesEntityList);
        assertThat(userQuestionLikesEntityList.size(), equalTo(1));

        userQuestionLikesSpringDataRepo.delete(userQuestionLikesEntityId);
        userQuestionLikesEntity = userQuestionLikesSpringDataRepo.findOne(userQuestionLikesEntityId);
        assertNull(userQuestionLikesEntity);

        questionSpringDataRepository.delete(questionID);
        questionEntity = questionSpringDataRepository.findOne(questionID);
        assertNull(questionEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }
}
