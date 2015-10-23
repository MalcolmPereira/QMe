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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
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
public class UserSpringDataRepositoryTest {
    /**
     * UserEntity Repository
     */
    @Autowired
    private UserSpringDataRepository userSpringDataRepo;

    @Test
    public void testFindAll() {
        assertNotNull(userSpringDataRepo);
        List<UserEntity> userEntities = userSpringDataRepo.findAll();
        assertNotNull(userEntities);
        assertThat(userEntities.size(), greaterThan(0));
    }

    @Test
    public void testFindAllPaged() {
        assertNotNull(userSpringDataRepo);
        PageRequest pageRequest = new PageRequest(0, 10);
        //Sort.Direction.ASC,sortFieldList.toArray(new String[sortFieldList.size()])
        Page<UserEntity> userList = ((PagingAndSortingRepository)userSpringDataRepo).findAll(pageRequest);
        List<UserEntity> userEntities =userList.getContent();
        assertNotNull(userEntities);
        assertThat(userEntities.size(), greaterThan(0));

        pageRequest = new PageRequest(0, 10, Sort.Direction.ASC, "userName");
        userList = ((PagingAndSortingRepository)userSpringDataRepo).findAll(pageRequest);
        userEntities =userList.getContent();
        assertNotNull(userEntities);
        assertThat(userEntities.size(), greaterThan(0));

        pageRequest = new PageRequest(0, 10, Sort.Direction.ASC, "userFirstName");
        userList = ((PagingAndSortingRepository)userSpringDataRepo).findAll(pageRequest);
        userEntities =userList.getContent();
        assertNotNull(userEntities);
        assertThat(userEntities.size(), greaterThan(0));

        pageRequest = new PageRequest(0, 10, Sort.Direction.ASC, "userLastName");
        userList = ((PagingAndSortingRepository)userSpringDataRepo).findAll(pageRequest);
        userEntities =userList.getContent();
        assertNotNull(userEntities);
        assertThat(userEntities.size(), greaterThan(0));

        pageRequest = new PageRequest(0, 10, Sort.Direction.ASC, "userEmail");
        userList = ((PagingAndSortingRepository)userSpringDataRepo).findAll(pageRequest);
        userEntities =userList.getContent();
        assertNotNull(userEntities);
        assertThat(userEntities.size(), greaterThan(0));

        pageRequest = new PageRequest(0, 10, Sort.Direction.DESC, "userName");
        userList = ((PagingAndSortingRepository)userSpringDataRepo).findAll(pageRequest);
        userEntities =userList.getContent();
        assertNotNull(userEntities);
        assertThat(userEntities.size(), greaterThan(0));

        pageRequest = new PageRequest(0, 10, Sort.Direction.DESC, "userFirstName");
        userList = ((PagingAndSortingRepository)userSpringDataRepo).findAll(pageRequest);
        userEntities =userList.getContent();
        assertNotNull(userEntities);
        assertThat(userEntities.size(), greaterThan(0));

        pageRequest = new PageRequest(0, 10, Sort.Direction.DESC, "userLastName");
        userList = ((PagingAndSortingRepository)userSpringDataRepo).findAll(pageRequest);
        userEntities =userList.getContent();
        assertNotNull(userEntities);
        assertThat(userEntities.size(), greaterThan(0));

        pageRequest = new PageRequest(0, 10, Sort.Direction.DESC, "userEmail");
        userList = ((PagingAndSortingRepository)userSpringDataRepo).findAll(pageRequest);
        userEntities =userList.getContent();
        assertNotNull(userEntities);
        assertThat(userEntities.size(), greaterThan(0));

        pageRequest = new PageRequest(0, 10, Sort.Direction.ASC, "userEmail", "userName", "userFirstName", "userLastName");
        userList = ((PagingAndSortingRepository)userSpringDataRepo).findAll(pageRequest);
        userEntities =userList.getContent();
        assertNotNull(userEntities);
        assertThat(userEntities.size(), greaterThan(0));

        pageRequest = new PageRequest(0, 10, Sort.Direction.DESC,  "userEmail", "userName", "userFirstName", "userLastName");
        userList = ((PagingAndSortingRepository)userSpringDataRepo).findAll(pageRequest);
        userEntities =userList.getContent();
        assertNotNull(userEntities);
        assertThat(userEntities.size(), greaterThan(0));
    }

    @Test
    public void testFindById() {
        assertNotNull(userSpringDataRepo);
        UserEntity userEntity = userSpringDataRepo.findOne(1L);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), equalTo(1L));
    }

    @Test
    public void testCRUD() {
        assertNotNull(userSpringDataRepo);

        UserEntity userEntity = new UserEntity("UserSpringDataRepositoryTest", "Test", "Test", "UserSpringDataRepositoryTest@test.com", "Test", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));

        Long userID = userEntity.getUserId();
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

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }

    @Test
    public void testFindByUserNameIgnoreCase() {
        assertNotNull(userSpringDataRepo);

        UserEntity userEntity = new UserEntity("UserSpringDataRepositoryTestUserName", "Test", "Test", "UserSpringDataRepositoryTestUserName@test.com", "Test", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));

        Long userID = userEntity.getUserId();
        userEntity = userSpringDataRepo.findOne(userID);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), equalTo(userID));

        userEntity = userSpringDataRepo.findByUserNameIgnoreCase("UserSpringDataRepositoryTestUserName");
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), equalTo(userID));

        userEntity = userSpringDataRepo.findByUserNameIgnoreCase("userspringdatarepositorytestusername");
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), equalTo(userID));

        userEntity.setUserFirstName("First Name Updated");
        userEntity.setUserLastName("Last Name Updated");
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), equalTo(userID));
        assertThat(userEntity.getUserFirstName(), equalTo("First Name Updated"));
        assertThat(userEntity.getUserLastName(), equalTo("Last Name Updated"));

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);

    }

    @Test
    public void findByUserEmailIgnoreCase() {
        assertNotNull(userSpringDataRepo);

        UserEntity userEntity = new UserEntity("UserSpringDataRepositoryTestUserName1", "Test", "Test", "UserSpringDataRepositoryTestUserName1@test.com", "Test", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));

        Long userID = userEntity.getUserId();
        userEntity = userSpringDataRepo.findOne(userID);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), equalTo(userID));

        userEntity = userSpringDataRepo.findByUserEmailIgnoreCase("UserSpringDataRepositoryTestUserName1@test.com");
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), equalTo(userID));

        userEntity = userSpringDataRepo.findByUserEmailIgnoreCase("userspringdatarepositorytestusername1@TEST.COM");
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), equalTo(userID));

        userEntity.setUserFirstName("First Name Updated");
        userEntity.setUserLastName("Last Name Updated");
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), equalTo(userID));
        assertThat(userEntity.getUserFirstName(), equalTo("First Name Updated"));
        assertThat(userEntity.getUserLastName(), equalTo("Last Name Updated"));

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);

    }
}
