/**
 * Name      : com.malcolm.qme.springdata.repository.UserPasswordResetSpringDataRepositoryTest.java
 * Date      : 5/28/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA UserQuizLikesEntity Repository
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.UserEntity;
import com.malcolm.qme.springdata.entity.UserPasswordResetEntity;
import com.malcolm.qme.springdata.entity.UserPasswordResetEntityId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class UserPasswordResetSpringDataRepositoryTest {
    /**
     * UserEntity Repository
     */
    @Autowired
    private UserSpringDataRepository userSpringDataRepo;

    /**
     * UserEntity Repository
     */
    @Autowired
    private UserPasswordResetSpringDataRepository userPasswordResetSpringDataRepo;

    @Test
    public void testCRUD() {
        assertNotNull(userSpringDataRepo);

        assertNotNull(userPasswordResetSpringDataRepo);

        UserEntity userEntity = new UserEntity("UserPasswordResetSpringDataRepositoryTest", "Test", "Test", "UserPasswordResetSpringDataRepositoryTest@test.com", "Test", LocalDateTime.now(), LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        final Long userID = userEntity.getUserId();

        UserPasswordResetEntity userPasswordResetEntity = new UserPasswordResetEntity();
        UserPasswordResetEntityId userPasswordResetEntityId = new UserPasswordResetEntityId();
        userPasswordResetEntityId.setUserId(userID);
        userPasswordResetEntityId.setResetToken(1L);
        userPasswordResetEntity.setId(userPasswordResetEntityId);
        userPasswordResetEntity.setCreatedTimestamp(LocalDateTime.now());

        userPasswordResetEntity = userPasswordResetSpringDataRepo.save(userPasswordResetEntity);
        assertNotNull(userPasswordResetEntity);
        assertThat(userPasswordResetEntity.getId().getUserId(), equalTo(userID));
        assertThat(userPasswordResetEntity.getId().getResetToken(), equalTo(1L));

        userPasswordResetEntity = userPasswordResetSpringDataRepo.findOne(userPasswordResetEntityId);
        assertNotNull(userPasswordResetEntity);
        assertThat(userPasswordResetEntity.getId().getUserId(), equalTo(userID));
        assertThat(userPasswordResetEntity.getId().getResetToken(), equalTo(1L));

        userPasswordResetSpringDataRepo.delete(userPasswordResetEntityId);
        userPasswordResetEntity = userPasswordResetSpringDataRepo.findOne(userPasswordResetEntityId);
        assertNull(userPasswordResetEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }
}
