/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizSpringDataRepositoryTest.java
 * Date      : 5/18/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA UserQuizEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.QuizEntity;
import com.malcolm.qme.springdata.entity.UserEntity;
import com.malcolm.qme.springdata.entity.UserQuizEntity;
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
public class UserQuizSpringDataRepositoryTest {
    /**
     * UserQuizEntity Repository
     */
    @Autowired
    private UserQuizSpringDataRepository userQuizSpringDataRepository;

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

    @Test
    public void testFindAll() {
        assertNotNull(userQuizSpringDataRepository);
        List<UserQuizEntity> userQuizEntities = userQuizSpringDataRepository.findAll();
        assertNotNull(userQuizEntities);
        assertThat(userQuizEntities.size(), greaterThan(0));
    }

    @Test
    public void testFindById() {
        assertNotNull(userQuizSpringDataRepository);
        UserQuizEntity userQuizEntity = userQuizSpringDataRepository.findOne(1L);
        assertNotNull(userQuizEntity);
        assertThat(userQuizEntity.getUserQuizId(), equalTo(1L));
    }

    @Test
    public void testCRUD() {

        assertNotNull(userQuizSpringDataRepository);

        assertNotNull(userSpringDataRepo);

        assertNotNull(quizSpringDataRepository);

        UserEntity userEntity = new UserEntity("UserQuizSpringDataRepositoryTest",
                "Test", "Test", "UserQuizSpringDataRepositoryTest@test.com",
                "Test", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        Long userID = userEntity.getUserId();


        QuizEntity quizEntity = new QuizEntity(
                "UserQuizSpringDataRepositoryTest Quiz", "UserQuizSpringDataRepositoryTest Quiz Desc", 1L, 0L, 0L,
                0, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L
        );
        quizEntity = quizSpringDataRepository.save(quizEntity);
        assertNotNull(quizEntity);
        assertThat(quizEntity.getQuizId(), greaterThan(0L));
        final Long quizID = quizEntity.getQuizId();

        UserQuizEntity userQuizEntity = new UserQuizEntity(userID, quizID, 1L, LocalDateTime.now(), 0, 10);
        userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
        assertNotNull(userQuizEntity);
        assertThat(userQuizEntity.getUserQuizId(), greaterThan(0L));
        final Long userQuizID = userQuizEntity.getUserQuizId();

        userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
        assertNotNull(userQuizEntity);
        assertThat(userQuizEntity.getUserQuizId(), equalTo(userQuizID));
        assertThat(userQuizEntity.getUserId(), equalTo(userID));
        assertThat(userQuizEntity.getQuizId(), equalTo(quizID));
        assertThat(userQuizEntity.getQuizMaxScore(), equalTo(10));

        userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
        assertNotNull(userQuizEntity);

        userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
        assertNotNull(userQuizEntity);
        assertThat(userQuizEntity.getUserQuizId(), equalTo(userQuizID));
        assertThat(userQuizEntity.getUserId(), equalTo(userID));
        assertThat(userQuizEntity.getQuizId(), equalTo(quizID));
        assertThat(userQuizEntity.getQuizMaxScore(), equalTo(10));

        userQuizSpringDataRepository.delete(userQuizID);
        userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
        assertNull(userQuizEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);

        quizSpringDataRepository.delete(quizID);
        quizEntity = quizSpringDataRepository.findOne(quizID);
        assertNull(quizEntity);
    }

    @Test
    public void testFindByUserId() {

        assertNotNull(userQuizSpringDataRepository);

        assertNotNull(userSpringDataRepo);

        assertNotNull(quizSpringDataRepository);

        UserEntity userEntity = new UserEntity("UQuizSpringDataRepositoryTestByUserID",
                "Test", "Test", "UQuizSpringDataRepositoryTestByUserID@test.com",
                "Test", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        Long userID = userEntity.getUserId();


        QuizEntity quizEntity = new QuizEntity(
                "UQuizSpringDataRepositoryTestByUserID Quiz", "UQuizSpringDataRepositoryTestByUserID Quiz Desc", 1L, 0L, 0L,
                0, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L
        );
        quizEntity = quizSpringDataRepository.save(quizEntity);
        assertNotNull(quizEntity);
        assertThat(quizEntity.getQuizId(), greaterThan(0L));
        final Long quizID = quizEntity.getQuizId();

        UserQuizEntity userQuizEntity = new UserQuizEntity(userID, quizID, 1L, LocalDateTime.now(), 0, 10);
        userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
        assertNotNull(userQuizEntity);
        assertThat(userQuizEntity.getUserQuizId(), greaterThan(0L));
        final Long userQuizID = userQuizEntity.getUserQuizId();

        userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
        assertNotNull(userQuizEntity);
        assertThat(userQuizEntity.getUserQuizId(), equalTo(userQuizID));
        assertThat(userQuizEntity.getUserId(), equalTo(userID));
        assertThat(userQuizEntity.getQuizId(), equalTo(quizID));
        assertThat(userQuizEntity.getQuizMaxScore(), equalTo(10));


        List<UserQuizEntity> userQuizEntityList = userQuizSpringDataRepository.findByUserId(userID);
        assertNotNull(userQuizEntityList);
        assertThat(userQuizEntityList.size(), equalTo(1));
        assertThat(userQuizEntityList.get(0).getUserQuizId(), equalTo(userQuizID));
        assertThat(userQuizEntityList.get(0).getUserId(), equalTo(userID));
        assertThat(userQuizEntityList.get(0).getQuizId(), equalTo(quizID));
        assertThat(userQuizEntityList.get(0).getQuizMaxScore(), equalTo(10));


        userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
        assertNotNull(userQuizEntity);

        userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
        assertNotNull(userQuizEntity);
        assertThat(userQuizEntity.getUserQuizId(), equalTo(userQuizID));
        assertThat(userQuizEntity.getUserId(), equalTo(userID));
        assertThat(userQuizEntity.getQuizId(), equalTo(quizID));
        assertThat(userQuizEntity.getQuizMaxScore(), equalTo(10));


        userQuizEntityList = userQuizSpringDataRepository.findByUserId(userID);
        assertNotNull(userQuizEntityList);
        assertThat(userQuizEntityList.size(), equalTo(1));
        assertThat(userQuizEntityList.get(0).getUserQuizId(), equalTo(userQuizID));
        assertThat(userQuizEntityList.get(0).getUserId(), equalTo(userID));
        assertThat(userQuizEntityList.get(0).getQuizId(), equalTo(quizID));
        assertThat(userQuizEntityList.get(0).getQuizMaxScore(), equalTo(10));


        userQuizSpringDataRepository.delete(userQuizID);
        userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
        assertNull(userQuizEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);

        quizSpringDataRepository.delete(quizID);
        quizEntity = quizSpringDataRepository.findOne(quizID);
        assertNull(quizEntity);
    }

    @Test
    public void testFindByQuizId() {

        assertNotNull(userQuizSpringDataRepository);

        assertNotNull(userSpringDataRepo);

        assertNotNull(quizSpringDataRepository);

        UserEntity userEntity = new UserEntity("UQuizSpringDataRepositoryTestByQuizID",
                "Test", "Test", "UQuizSpringDataRepositoryTestByQuizID@test.com",
                "Test", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        Long userID = userEntity.getUserId();


        QuizEntity quizEntity = new QuizEntity(
                "UQuizSpringDataRepositoryTestByQuizID Quiz", "UQuizSpringDataRepositoryTestByQuizID Quiz Desc", 1L, 0L, 0L,
                0, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L
        );
        quizEntity = quizSpringDataRepository.save(quizEntity);
        assertNotNull(quizEntity);
        assertThat(quizEntity.getQuizId(), greaterThan(0L));
        final Long quizID = quizEntity.getQuizId();

        UserQuizEntity userQuizEntity = new UserQuizEntity(userID, quizID, 1L, LocalDateTime.now(), 0, 10);
        userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
        assertNotNull(userQuizEntity);
        assertThat(userQuizEntity.getUserQuizId(), greaterThan(0L));
        final Long userQuizID = userQuizEntity.getUserQuizId();

        userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
        assertNotNull(userQuizEntity);
        assertThat(userQuizEntity.getUserQuizId(), equalTo(userQuizID));
        assertThat(userQuizEntity.getUserId(), equalTo(userID));
        assertThat(userQuizEntity.getQuizId(), equalTo(quizID));
        assertThat(userQuizEntity.getQuizMaxScore(), equalTo(10));


        List<UserQuizEntity> userQuizEntityList = userQuizSpringDataRepository.findByQuizId(quizID);
        assertNotNull(userQuizEntityList);
        assertThat(userQuizEntityList.size(), equalTo(1));
        assertThat(userQuizEntityList.get(0).getUserQuizId(), equalTo(userQuizID));
        assertThat(userQuizEntityList.get(0).getUserId(), equalTo(userID));
        assertThat(userQuizEntityList.get(0).getQuizId(), equalTo(quizID));
        assertThat(userQuizEntityList.get(0).getQuizMaxScore(), equalTo(10));

        userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
        assertNotNull(userQuizEntity);

        userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
        assertNotNull(userQuizEntity);
        assertThat(userQuizEntity.getUserQuizId(), equalTo(userQuizID));
        assertThat(userQuizEntity.getUserId(), equalTo(userID));
        assertThat(userQuizEntity.getQuizId(), equalTo(quizID));
        assertThat(userQuizEntity.getQuizMaxScore(), equalTo(10));

        userQuizEntityList = userQuizSpringDataRepository.findByQuizId(quizID);
        assertNotNull(userQuizEntityList);
        assertThat(userQuizEntityList.size(), equalTo(1));
        assertThat(userQuizEntityList.get(0).getUserQuizId(), equalTo(userQuizID));
        assertThat(userQuizEntityList.get(0).getUserId(), equalTo(userID));
        assertThat(userQuizEntityList.get(0).getQuizId(), equalTo(quizID));
        assertThat(userQuizEntityList.get(0).getQuizMaxScore(), equalTo(10));

        userQuizSpringDataRepository.delete(userQuizID);
        userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
        assertNull(userQuizEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);

        quizSpringDataRepository.delete(quizID);
        quizEntity = quizSpringDataRepository.findOne(quizID);
        assertNull(quizEntity);
    }


    @Test
    public void testFindQuizzesForUser() {
        assertNotNull(userQuizSpringDataRepository);

        assertNotNull(userSpringDataRepo);

        assertNotNull(quizSpringDataRepository);

        UserEntity userEntity = new UserEntity("UserQuizSpringDataRepositoryTestFindForUser","Test", "Test", "UserQuizSpringDataRepositoryTestFindForUser@test.com","Test", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        Long userID = userEntity.getUserId();

        QuizEntity quizEntity = new QuizEntity("UserQuizSpringDataRepositoryTestFindForUser Quiz", "UserQuizSpringDataRepositoryTestFindForUser Quiz Desc", 1L, 0L, 0L,0, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        quizEntity = quizSpringDataRepository.save(quizEntity);
        assertNotNull(quizEntity);
        assertThat(quizEntity.getQuizId(), greaterThan(0L));
        final Long quizID = quizEntity.getQuizId();

        UserQuizEntity userQuizEntity = new UserQuizEntity(userID, quizID, 1L, LocalDateTime.now(), 0, 10);
        userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
        assertNotNull(userQuizEntity);
        assertThat(userQuizEntity.getUserQuizId(), greaterThan(0L));
        final Long userQuizID = userQuizEntity.getUserQuizId();

        QuizEntity quizEntityAnother = new QuizEntity("UserQuizSpringDataRepositoryTestFindForUser Quiz 2", "UserQuizSpringDataRepositoryTestFindForUser Quiz 2 Desc", 1L, 0L, 0L,0, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        quizEntityAnother = quizSpringDataRepository.save(quizEntityAnother);
        assertNotNull(quizEntityAnother);
        assertThat(quizEntityAnother.getQuizId(), greaterThan(0L));
        final Long quizIDAnother = quizEntityAnother.getQuizId();

        UserQuizEntity userQuizEntityAnother = new UserQuizEntity(userID, quizIDAnother, 1L, LocalDateTime.now(), 0, 10);
        userQuizEntityAnother = userQuizSpringDataRepository.save(userQuizEntityAnother);
        assertNotNull(userQuizEntityAnother);
        assertThat(userQuizEntityAnother.getUserQuizId(), greaterThan(0L));
        final Long userQuizIDAnother = userQuizEntityAnother.getUserQuizId();

        List<UserQuizEntity> userQuizEntityList = userQuizSpringDataRepository.findQuizzesForUser(userID);
        assertNotNull(userQuizEntityList);
        assertThat(userQuizEntityList.size(), greaterThan(2));

        userQuizSpringDataRepository.delete(userQuizID);
        userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
        assertNull(userQuizEntity);

        userQuizSpringDataRepository.delete(userQuizIDAnother);
        userQuizEntityAnother = userQuizSpringDataRepository.findOne(userQuizIDAnother);
        assertNull(userQuizEntityAnother);

        quizSpringDataRepository.delete(quizID);
        quizEntity = quizSpringDataRepository.findOne(quizID);
        assertNull(quizEntity);

        quizSpringDataRepository.delete(quizIDAnother);
        quizEntity = quizSpringDataRepository.findOne(quizIDAnother);
        assertNull(quizEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }

    @Test
    public void testFindCompletedByUserId() {
        assertNotNull(userQuizSpringDataRepository);

        assertNotNull(userSpringDataRepo);

        assertNotNull(quizSpringDataRepository);

        UserEntity userEntity = new UserEntity("UserQuizSpringDataRepositoryTestFindForUserC","Test", "Test", "UserQuizSpringDataRepositoryTestFindForUserC@test.com","Test", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        Long userID = userEntity.getUserId();

        QuizEntity quizEntity = new QuizEntity("UserQuizSpringDataRepositoryTestFindForUserC Quiz", "UserQuizSpringDataRepositoryTestFindForUserC Quiz Desc", 1L, 0L, 0L,0, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        quizEntity = quizSpringDataRepository.save(quizEntity);
        assertNotNull(quizEntity);
        assertThat(quizEntity.getQuizId(), greaterThan(0L));
        final Long quizID = quizEntity.getQuizId();

        UserQuizEntity userQuizEntity = new UserQuizEntity(userID, quizID, 1L, LocalDateTime.now(), LocalDateTime.now(), 10, 10, "token");
        userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
        assertNotNull(userQuizEntity);
        assertThat(userQuizEntity.getUserQuizId(), greaterThan(0L));
        final Long userQuizID = userQuizEntity.getUserQuizId();

        List<UserQuizEntity> userQuizEntityList = userQuizSpringDataRepository.findCompletedByUserId(userID);
        assertNotNull(userQuizEntityList);
        assertThat(userQuizEntityList.size(), greaterThan(0));

        userQuizSpringDataRepository.delete(userQuizID);
        userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
        assertNull(userQuizEntity);

        quizSpringDataRepository.delete(quizID);
        quizEntity = quizSpringDataRepository.findOne(quizID);
        assertNull(quizEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }

    @Test
    public void testFindPendingByUserId() {
        assertNotNull(userQuizSpringDataRepository);

        assertNotNull(userSpringDataRepo);

        assertNotNull(quizSpringDataRepository);

        UserEntity userEntity = new UserEntity("UserQuizSpringDataRepositoryTestFindForUserP","Test", "Test", "UserQuizSpringDataRepositoryTestFindForUserP@test.com","Test", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        Long userID = userEntity.getUserId();

        QuizEntity quizEntity = new QuizEntity("UserQuizSpringDataRepositoryTestFindForUserP Quiz", "UserQuizSpringDataRepositoryTestFindForUserP Quiz Desc", 1L, 0L, 0L,0, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        quizEntity = quizSpringDataRepository.save(quizEntity);
        assertNotNull(quizEntity);
        assertThat(quizEntity.getQuizId(), greaterThan(0L));
        final Long quizID = quizEntity.getQuizId();

        UserQuizEntity userQuizEntity = new UserQuizEntity(userID, quizID, 1L, LocalDateTime.now(), 0, 10);
        userQuizEntity = userQuizSpringDataRepository.save(userQuizEntity);
        assertNotNull(userQuizEntity);
        assertThat(userQuizEntity.getUserQuizId(), greaterThan(0L));
        final Long userQuizID = userQuizEntity.getUserQuizId();

        List<UserQuizEntity> userQuizEntityList = userQuizSpringDataRepository.findQuizzesForUser(userID);
        assertNotNull(userQuizEntityList);
        assertThat(userQuizEntityList.size(), greaterThan(0));

        userQuizSpringDataRepository.delete(userQuizID);
        userQuizEntity = userQuizSpringDataRepository.findOne(userQuizID);
        assertNull(userQuizEntity);

        quizSpringDataRepository.delete(quizID);
        quizEntity = quizSpringDataRepository.findOne(quizID);
        assertNull(quizEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }
}
