/**
 * Name      : com.malcolm.qme.springdata.repository.RoleSpringDataRepositoryTest.java
 * Date      : 5/11/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA RoleEntity Repository
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.RoleEntity;
import com.malcolm.qme.springdata.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @Author: Malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class RoleSpringDataRepositoryTest {

    /**
     * RoleEntity Repository
     */
    @Autowired
    private RoleSpringDataRepository roleSpringDataRepository;

    @Test
    public void testFindAll(){
        assertNotNull(roleSpringDataRepository);
        List<RoleEntity> roleEntities = roleSpringDataRepository.findAll();
        assertNotNull(roleEntities);
        assertThat(roleEntities.size(), greaterThan(0));
    }

    @Test
    public void testFindById(){
        assertNotNull(roleSpringDataRepository);
        RoleEntity roleEntity = roleSpringDataRepository.findOne(1);
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(),equalTo(1));
    }

    @Test
    public void testCRUD(){
        assertNotNull(roleSpringDataRepository);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("RoleSpringDataRepositoryTest");
        roleEntity.setRoleDesc("RoleSpringDataRepositoryTest Desc");
        roleEntity = roleSpringDataRepository.save(roleEntity);
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(), greaterThan(0));

        Integer roleID = roleEntity.getRoleId();

        roleEntity = roleSpringDataRepository.findOne(roleID);
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(), equalTo(roleID));

        roleEntity.setRoleName("RoleSpringDataRepositoryTest Updated");
        roleEntity.setRoleDesc("RoleSpringDataRepositoryTest Desc Updated");
        roleEntity = roleSpringDataRepository.save(roleEntity);
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(), equalTo(roleID));
        assertThat(roleEntity.getRoleName(), equalTo("RoleSpringDataRepositoryTest Updated"));
        assertThat(roleEntity.getRoleDesc(), equalTo("RoleSpringDataRepositoryTest Desc Updated"));

        roleSpringDataRepository.delete(roleID);
        roleEntity = roleSpringDataRepository.findOne(roleID);
        assertNull(roleEntity);
    }


    @Test
    public void testFindByRoleName(){
        assertNotNull(roleSpringDataRepository);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("RoleSpringDataRepositoryTest NAME");
        roleEntity.setRoleDesc("RoleSpringDataRepositoryTest NAME Desc");
        roleEntity = roleSpringDataRepository.save(roleEntity);
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(), greaterThan(0));

        Integer roleID = roleEntity.getRoleId();

        roleEntity = roleSpringDataRepository.findOne(roleID);
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(), equalTo(roleID));

        roleEntity = roleSpringDataRepository.findByRoleNameIgnoreCase("RoleSpringDataRepositoryTest NAME");
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(), equalTo(roleID));

        roleEntity = roleSpringDataRepository.findByRoleNameIgnoreCase("rolespringdatarepositorytest NAME");
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(), equalTo(roleID));

        roleEntity.setRoleName("RoleSpringDataRepositoryTest NAME Updated");
        roleEntity.setRoleDesc("RoleSpringDataRepositoryTest NAME Desc Updated");
        roleEntity = roleSpringDataRepository.save(roleEntity);
        assertNotNull(roleEntity);
        assertThat(roleEntity.getRoleId(), equalTo(roleID));
        assertThat(roleEntity.getRoleName(), equalTo("RoleSpringDataRepositoryTest NAME Updated"));
        assertThat(roleEntity.getRoleDesc(), equalTo("RoleSpringDataRepositoryTest NAME Desc Updated"));

        roleSpringDataRepository.delete(roleID);
        roleEntity = roleSpringDataRepository.findOne(roleID);
        assertNull(roleEntity);
    }
}
