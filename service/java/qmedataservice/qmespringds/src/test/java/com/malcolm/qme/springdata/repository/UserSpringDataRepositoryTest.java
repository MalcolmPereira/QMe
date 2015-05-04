/**
 * Name      : com.malcolm.qme.springdata.repository.UserSpringDataRepositoryTest.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA UserEntity Repository
 */

package com.malcolm.qme.springdata.repository;


import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @Author: malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class UserSpringDataRepositoryTest {
    /**
     * UserEntity Repository
     */
    @Autowired
    private UserSpringDataRepository userSpringDataRepo;

    @Test
    public void testFetchAll(){
        assertNotNull(userSpringDataRepo);
        List<UserEntity> userEntities = userSpringDataRepo.findAll();
        assertNotNull(userEntities);
        assertThat(userEntities.size(), greaterThan(0));
    }

    @Test
    public void testFetchOne(){
        assertNotNull(userSpringDataRepo);
        UserEntity userEntity = userSpringDataRepo.findOne(1);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), equalTo(1));
    }

    @Test
    public void testCRUD(){
        assertNotNull(userSpringDataRepo);

        UserEntity userEntity = new UserEntity("testUser1", "Test", "UserEntity", "testuser@test.com", "testpassword", new Date(), new Date());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0));

        Integer userID = userEntity.getUserId();
        userEntity = userSpringDataRepo.findOne(userID);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), equalTo(userID));
        userEntity.setUserFirstName("First Name Updated");
        userEntity.setUserLastName("Last Name Updated");
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), equalTo(userID));
        assertThat(userEntity.getUserFirstName(), equalTo("First Name Updated"));
        assertThat(userEntity.getUserLastName(), equalTo("Last Name Updated"));

        userSpringDataRepo.delete(userEntity);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }
}
