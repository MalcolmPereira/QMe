/**
 * Name      : com.malcolm.qme.springdata.repository.UserRoleSpringDataRepositoryTest.java
 * Date      : 5/11/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA UserRoleEntity Repository
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.RoleEntity;
import com.malcolm.qme.springdata.entity.UserEntity;
import com.malcolm.qme.springdata.entity.UserRolesEntity;
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
public class UserRoleSpringDataRepositoryTest {

    /**
     * UserRoleEntity Repository
     */
    @Autowired
    private UserRoleSpringDataRepository userRoleSpringDataRepository;

    /**
     * UserEntity Repository
     */
    @Autowired
    private UserSpringDataRepository userSpringDataRepo;

    /**
     * RoleEntity Repository
     */
    @Autowired
    private RoleSpringDataRepository roleSpringDataRepository;


    @Test
    public void testFindAll(){
        assertNotNull(userRoleSpringDataRepository);
        List<UserRolesEntity> userRolesEntities = userRoleSpringDataRepository.findAll();
        assertNotNull(userRolesEntities);
        assertThat(userRolesEntities.size(), greaterThan(0));
    }

    @Test
    public void testFindById(){
        assertNotNull(userRoleSpringDataRepository);
        UserRolesEntity userRolesEntity = userRoleSpringDataRepository.findOne(1L);
        assertNotNull(userRolesEntity);
        assertThat(userRolesEntity.getUserRoleId(), equalTo(1L));
    }

    @Test
    public void testCRUD(){
        assertNotNull(userRoleSpringDataRepository);

        assertNotNull(userSpringDataRepo);

        assertNotNull(roleSpringDataRepository);

        UserEntity userEntity = new UserEntity("UserRoleSpringDataRepositoryTest", "Test", "Test", "UserRoleSpringDataRepositoryTest@test.com", "Test", new Date(), new Date());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));

        Long userID = userEntity.getUserId();
        userEntity = userSpringDataRepo.findOne(userID);
        assertNotNull(userEntity);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("UserRoleSpringDataRepositoryTest");
        roleEntity.setRoleDesc("UserRoleSpringDataRepositoryTest Desc");
        roleEntity = roleSpringDataRepository.save(roleEntity);
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(), greaterThan(0));

        Integer roleID = roleEntity.getRoleId();

        roleEntity = roleSpringDataRepository.findOne(roleID);
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(), equalTo(roleID));

        UserRolesEntity userRolesEntity = new UserRolesEntity();
        userRolesEntity.setUserId(userID);
        userRolesEntity.setRoleId(roleID);
        userRolesEntity = userRoleSpringDataRepository.save(userRolesEntity);
        assertNotNull(userRolesEntity);
        assertThat(userRolesEntity.getUserRoleId(), greaterThan(0L));

        Long userRoleID = userRolesEntity.getUserRoleId();

        userRolesEntity = userRoleSpringDataRepository.findOne(userRoleID);
        assertNotNull(userRolesEntity);
        assertThat(userRolesEntity.getUserRoleId(), equalTo(userRoleID));

        userRoleSpringDataRepository.delete(userRoleID);
        userRolesEntity = userRoleSpringDataRepository.findOne(userRoleID);
        assertNull(userRolesEntity);

        roleSpringDataRepository.delete(roleID);
        roleEntity = roleSpringDataRepository.findOne(roleID);
        assertNull(roleEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }

    @Test
    public void testFindByUserId(){
        assertNotNull(userRoleSpringDataRepository);

        assertNotNull(userSpringDataRepo);

        assertNotNull(roleSpringDataRepository);

        UserEntity userEntity = new UserEntity("UserRoleSpringDataRepositoryTest ByUserID", "Test", "Test", "UserRoleSpringDataRepositoryTestByUserID@test.com", "Test", new Date(), new Date());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));

        Long userID = userEntity.getUserId();
        userEntity = userSpringDataRepo.findOne(userID);
        assertNotNull(userEntity);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("UserRoleSpringDataRepositoryTest ByUserID");
        roleEntity.setRoleDesc("UserRoleSpringDataRepositoryTest ByUserID Desc");
        roleEntity = roleSpringDataRepository.save(roleEntity);
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(), greaterThan(0));

        Integer roleID = roleEntity.getRoleId();

        roleEntity = roleSpringDataRepository.findOne(roleID);
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(), equalTo(roleID));

        UserRolesEntity userRolesEntity = new UserRolesEntity();
        userRolesEntity.setUserId(userID);
        userRolesEntity.setRoleId(roleID);
        userRolesEntity = userRoleSpringDataRepository.save(userRolesEntity);
        assertNotNull(userRolesEntity);
        assertThat(userRolesEntity.getUserRoleId(), greaterThan(0L));

        Long userRoleID = userRolesEntity.getUserRoleId();

        userRolesEntity = userRoleSpringDataRepository.findOne(userRoleID);
        assertNotNull(userRolesEntity);
        assertThat(userRolesEntity.getUserRoleId(), equalTo(userRoleID));

        List<UserRolesEntity>  userRolesEntityList = userRoleSpringDataRepository.findByUserId(userID);
        assertNotNull(userRolesEntityList);
        assertThat(userRolesEntityList.size(), equalTo(1));

        userRoleSpringDataRepository.delete(userRoleID);
        userRolesEntity = userRoleSpringDataRepository.findOne(userRoleID);
        assertNull(userRolesEntity);

        roleSpringDataRepository.delete(roleID);
        roleEntity = roleSpringDataRepository.findOne(roleID);
        assertNull(roleEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }

    @Test
    public void testFindByRoleId(){
        assertNotNull(userRoleSpringDataRepository);

        assertNotNull(userSpringDataRepo);

        assertNotNull(roleSpringDataRepository);

        UserEntity userEntity = new UserEntity("UserRoleSpringDataRepositoryTest ByRoleId", "Test", "Test", "UserRoleSpringDataRepositoryTestByRoleId@test.com", "Test", new Date(), new Date());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));

        Long userID = userEntity.getUserId();
        userEntity = userSpringDataRepo.findOne(userID);
        assertNotNull(userEntity);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("UserRoleSpringDataRepositoryTest ByRoleId");
        roleEntity.setRoleDesc("UserRoleSpringDataRepositoryTest ByRoleId Desc");
        roleEntity = roleSpringDataRepository.save(roleEntity);
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(), greaterThan(0));

        Integer roleID = roleEntity.getRoleId();

        roleEntity = roleSpringDataRepository.findOne(roleID);
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(), equalTo(roleID));

        UserRolesEntity userRolesEntity = new UserRolesEntity();
        userRolesEntity.setUserId(userID);
        userRolesEntity.setRoleId(roleID);
        userRolesEntity = userRoleSpringDataRepository.save(userRolesEntity);
        assertNotNull(userRolesEntity);
        assertThat(userRolesEntity.getUserRoleId(), greaterThan(0L));

        Long userRoleID = userRolesEntity.getUserRoleId();

        userRolesEntity = userRoleSpringDataRepository.findOne(userRoleID);
        assertNotNull(userRolesEntity);
        assertThat(userRolesEntity.getUserRoleId(), equalTo(userRoleID));

        List<UserRolesEntity>  userRolesEntityList = userRoleSpringDataRepository.findByUserId(userID);
        assertNotNull(userRolesEntityList);
        assertThat(userRolesEntityList.size(), equalTo(1));

        userRoleSpringDataRepository.delete(userRoleID);
        userRolesEntity = userRoleSpringDataRepository.findOne(userRoleID);
        assertNull(userRolesEntity);

        roleSpringDataRepository.delete(roleID);
        roleEntity = roleSpringDataRepository.findOne(roleID);
        assertNull(roleEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }
}
