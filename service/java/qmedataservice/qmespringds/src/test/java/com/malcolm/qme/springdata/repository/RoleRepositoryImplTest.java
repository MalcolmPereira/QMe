/**
 * Name      : com.malcolm.qme.springdata.repository.RoleRepositoryImplTest.java
 * Date      : 5/11/15
 * Developer : Malcolm
 * Purpose   : Tests for Role Repository Implementation
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Role;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.RoleRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.RoleEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class RoleRepositoryImplTest {

    /**
     * Spring Data RoleEntity Repository
     */
    @Autowired
    @Qualifier("RoleRepository")
    private RoleRepository roleRepo;

    @Mock
    private RoleSpringDataRepository roleSpringDataRepositoryMOCK;

    @InjectMocks
    private RoleRepository roleRepositoryWithMock;

    @Before
    public void initMocks(){
        roleRepositoryWithMock = new RoleRepositoryImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws QMeException {
        assertNotNull(roleRepo);
        List<Role> roles = roleRepo.findAll();
        assertNotNull(roles);
        assertThat(roles.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(roleRepo);
        Role role = roleRepo.findById(1);
        assertNotNull(role);
        assertThat(role.getRoleID(), equalTo(1));
    }

    @Test
    public void testCRUD() throws QMeException {
        assertNotNull(roleRepo);

        Role role = new Role("RoleRepositoryImplTest", "RoleRepositoryImplTest Desc");
        role = roleRepo.save(role);
        assertNotNull(role);
        assertThat(role.getRoleID(), greaterThan(0));

        Integer roleID = role.getRoleID();

        role = roleRepo.findById(roleID);
        assertNotNull(role);
        assertThat(role.getRoleID(), equalTo(roleID));

        Role roleUpdate = new Role(roleID, "RoleRepositoryImplTest Updated", "RoleRepositoryImplTest Updated Desc");

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
    public void testFindByRoleName() throws QMeException {
        assertNotNull(roleRepo);

        Role role = new Role("RoleSpringDataRepositoryTest NAME", "RoleSpringDataRepositoryTest NAME Desc");
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

        Role roleUpdate = new Role(roleID, "RoleSpringDataRepositoryTest NAME Updated", "RoleSpringDataRepositoryTest NAME Updated Desc");

        roleUpdate = roleRepo.update(roleUpdate, 1L);
        assertNotNull(roleUpdate);
        assertThat(roleUpdate.getRoleID(), equalTo(roleID));
        assertThat(roleUpdate.getRoleName(), equalTo("RoleSpringDataRepositoryTest NAME Updated"));
        assertThat(roleUpdate.getRoleDesc(), equalTo("RoleSpringDataRepositoryTest NAME Updated Desc"));

        roleRepo.delete(roleID);
        role = roleRepo.findById(roleID);
        assertNull(role);
    }

    @Test
    public void testFindAllNullReturn() throws QMeException {
        when(roleSpringDataRepositoryMOCK.findAll()).thenReturn(null);
        List<Role> roleList = roleRepositoryWithMock.findAll();
        verify(roleSpringDataRepositoryMOCK).findAll();
        assertNotNull(roleList);
        assertThat(roleList.size(), equalTo(0));
    }

    @Test(expected = QMeException.class)
    public void testFindAllQMeException() throws QMeException {
        when(roleSpringDataRepositoryMOCK.findAll()).thenThrow(new RuntimeException("some error"));
        roleRepositoryWithMock.findAll();
        verify(roleSpringDataRepositoryMOCK).findAll();
    }

    @Test(expected = QMeException.class)
    public void testFindByRoleNameQMeException() throws QMeException {
        when(roleSpringDataRepositoryMOCK.findByRoleNameIgnoreCase("test")).thenThrow(new RuntimeException("some error"));
        roleRepositoryWithMock.findByRoleName("test");
        verify(roleSpringDataRepositoryMOCK).findByRoleNameIgnoreCase("test");
    }

    @Test(expected = QMeException.class)
    public void testFindByIDQMeException() throws QMeException {
        when(roleSpringDataRepositoryMOCK.findOne(1)).thenThrow(new RuntimeException("some error"));
        roleRepositoryWithMock.findById(1);
        verify(roleSpringDataRepositoryMOCK).findOne(1);
    }

    @Test(expected = QMeException.class)
    public void testSaveQMeException() throws QMeException {
        when(roleSpringDataRepositoryMOCK.save(Matchers.<RoleEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        roleRepositoryWithMock.save(new Role("RoleRepositoryImplTest", "RoleRepositoryImplTest Desc"));
        verify(roleSpringDataRepositoryMOCK).save(Matchers.<RoleEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testUpdateQMeException() throws QMeException {
        when(roleSpringDataRepositoryMOCK.save(Matchers.<RoleEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        roleRepositoryWithMock.update(new Role("RoleRepositoryImplTest", "RoleRepositoryImplTest Desc"), 1L);
        verify(roleSpringDataRepositoryMOCK).save(Matchers.<RoleEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testDeleteQMeException() throws QMeException {
        doThrow(new RuntimeException("some error")).when(roleSpringDataRepositoryMOCK).delete(1);
        roleRepositoryWithMock.delete(1);
        verify(roleSpringDataRepositoryMOCK).delete(1);
    }
}
