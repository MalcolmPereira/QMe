/**
 * Name      : com.malcolm.qme.springdata.repository.RoleRepositoryImplTest.java
 * Date      : 5/11/15
 * Developer : Malcolm
 * Purpose   : Tests for Role Repository Implementation
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Role;
import com.malcolm.qme.core.repository.RoleRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class RoleRepositoryImplTest {

    /**
     * Spring Data RoleEntity Repository
     */
    @Autowired
    @Qualifier("RoleRepository")
    private RoleRepository roleRepo;

    @Test
    public void testFindAll(){
        assertNotNull(roleRepo);
        List<Role> roles = roleRepo.findAll();
        assertNotNull(roles);
        assertThat(roles.size(), greaterThan(0));
    }

    @Test
    public void testFindById(){
        assertNotNull(roleRepo);
        Role role = roleRepo.findById(1);
        assertNotNull(role);
        assertThat(role.getRoleID(),equalTo(1));
    }

    @Test
    public void testCRUD(){
        assertNotNull(roleRepo);

        Role role = new Role("RoleRepositoryImplTest","RoleRepositoryImplTest Desc");
        role = roleRepo.save(role);
        assertNotNull(role);
        assertThat(role.getRoleID(), greaterThan(0));

        Integer roleID = role.getRoleID();

        role = roleRepo.findById(roleID);
        assertNotNull(role);
        assertThat(role.getRoleID(), equalTo(roleID));

        Role roleUpdate = new Role(roleID, "RoleRepositoryImplTest Updated","RoleRepositoryImplTest Updated Desc");

        roleUpdate = roleRepo.update(roleUpdate, 1L);
        assertNotNull(roleUpdate);
        assertThat(roleUpdate.getRoleID(), equalTo(roleID));
        assertThat(roleUpdate.getRoleName(), equalTo("RoleRepositoryImplTest Updated"));
        assertThat(roleUpdate.getRoleDesc(), equalTo("RoleRepositoryImplTest Updated Desc"));

        roleRepo.delete(roleID);
        role = roleRepo.findById(roleID);
        assertNull(role);
    }

    @Test
    public void testFindByRoleName(){
        assertNotNull(roleRepo);

        Role role = new Role("RoleSpringDataRepositoryTest NAME","RoleSpringDataRepositoryTest NAME Desc");
        role = roleRepo.save(role);
        assertNotNull(role);
        assertThat(role.getRoleID(), greaterThan(0));

        Integer roleID = role.getRoleID();

        role = roleRepo.findById(roleID);
        assertNotNull(role);
        assertThat(role.getRoleID(), equalTo(roleID));

        role = roleRepo.findByRoleName("RoleSpringDataRepositoryTest NAME");
        assertNotNull(role);
        assertThat(role.getRoleID(), equalTo(roleID));

        role = roleRepo.findByRoleName("rolespringdatarepositorytest NAME");
        assertNotNull(role);
        assertThat(role.getRoleID(), equalTo(roleID));

        Role roleUpdate = new Role(roleID, "RoleSpringDataRepositoryTest NAME Updated","RoleSpringDataRepositoryTest NAME Updated Desc");

        roleUpdate = roleRepo.update(roleUpdate, 1L);
        assertNotNull(roleUpdate);
        assertThat(roleUpdate.getRoleID(), equalTo(roleID));
        assertThat(roleUpdate.getRoleName(), equalTo("RoleSpringDataRepositoryTest NAME Updated"));
        assertThat(roleUpdate.getRoleDesc(), equalTo("RoleSpringDataRepositoryTest NAME Updated Desc"));

        roleRepo.delete(roleID);
        role = roleRepo.findById(roleID);
        assertNull(role);

    }
}
