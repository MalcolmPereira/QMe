/**
 * Name      : com.malcolm.qme.springdata.repository.UserRoleRepositoryImplTest.java
 * Date      : 5/11/15
 * Developer : Malcolm
 * Purpose   : QMe User Roles Repository Implementation Test Cases
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Role;
import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserRole;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.RoleRepository;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.core.repository.UserRoleRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.UserRolesEntity;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class UserRoleRepositoryImplTest {

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRoleRepository")
    private UserRoleRepository userRoleRepository;

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepo;

    /**
     * Spring Data RoleEntity Repository
     */
    @Autowired
    @Qualifier("RoleRepository")
    private RoleRepository roleRepo;

    @Mock
    private UserRoleSpringDataRepository userRoleSpringDataRepositoryMOCK;

    @InjectMocks
    private UserRoleRepository userRoleRepositoryWithMock;

    @Before
    public void initMocks(){
        userRoleRepositoryWithMock = new UserRoleRepositoryImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws QMeException {
        assertNotNull(userRoleRepository);
        List<UserRole> userRoles = userRoleRepository.findAll();
        assertNotNull(userRoles);
        assertThat(userRoles.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(userRoleRepository);
        UserRole userRole = userRoleRepository.findById(1L);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(1L));
    }

    @Test
    public void testCRUD() throws QMeException {

        assertNotNull(userRoleRepository);

        assertNotNull(userRepo);

        assertNotNull(roleRepo);

        User user = new User("RoleRepositoryImplTest", "Test", "Test", "Test", "RoleRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));

        Long userID = user.getUserID();
        user = userRepo.findById(userID);
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));

        Role role = new Role("RoleRepositoryImplTest", "RoleRepositoryImplTest Desc");
        role = roleRepo.save(role);
        assertNotNull(role);
        assertThat(role.getRoleID(), greaterThan(0));

        Integer roleID = role.getRoleID();

        role = roleRepo.findById(roleID);
        assertNotNull(role);
        assertThat(role.getRoleID(), equalTo(roleID));

        UserRole userRole = new UserRole(roleID, userID);
        userRole = userRoleRepository.save(userRole);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), greaterThan(0L));

        Long userRoleID = userRole.getUserRoleID();

        userRole = userRoleRepository.findById(userRoleID);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(userRoleID));

        UserRole userRoleUpdate = new UserRole(userRoleID, roleID, userID);
        userRoleUpdate = userRoleRepository.update(userRoleUpdate, userID);
        assertNotNull(userRoleUpdate);
        assertThat(userRoleUpdate.getUserRoleID(), greaterThan(0L));

        userRoleRepository.delete(userRoleID);
        userRole = userRoleRepository.findById(userRoleID);
        assertNull(userRole);

        roleRepo.delete(roleID);
        role = roleRepo.findById(roleID);
        assertNull(role);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }

    @Test
    public void testFindByUserId() throws QMeException {

        assertNotNull(userRoleRepository);

        assertNotNull(userRepo);

        assertNotNull(roleRepo);

        User user = new User("RoleRepositoryImplTest ByUserID ", "Test", "Test", "Test", "RoleRepositoryImplTestByUserID@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));

        Long userID = user.getUserID();
        user = userRepo.findById(userID);
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));

        Role role = new Role("RoleRepositoryImplTest ByUserID", "RoleRepositoryImplTest ByUserID Desc");
        role = roleRepo.save(role);
        assertNotNull(role);
        assertThat(role.getRoleID(), greaterThan(0));

        Integer roleID = role.getRoleID();

        role = roleRepo.findById(roleID);
        assertNotNull(role);
        assertThat(role.getRoleID(), equalTo(roleID));

        UserRole userRole = new UserRole(roleID, userID);
        userRole = userRoleRepository.save(userRole);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), greaterThan(0L));

        Long userRoleID = userRole.getUserRoleID();

        userRole = userRoleRepository.findById(userRoleID);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(userRoleID));

        List<UserRole> userRolesList = userRoleRepository.findByUserId(userID);
        assertNotNull(userRolesList);
        assertThat(userRolesList.size(), equalTo(1));

        userRoleRepository.delete(userRoleID);
        userRole = userRoleRepository.findById(userRoleID);
        assertNull(userRole);

        roleRepo.delete(roleID);
        role = roleRepo.findById(roleID);
        assertNull(role);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }

    @Test
    public void testFindByRoleId() throws QMeException {

        assertNotNull(userRoleRepository);

        assertNotNull(userRepo);

        assertNotNull(roleRepo);

        User user = new User("RoleRepositoryImplTest ByRoleId ", "Test", "Test", "Test", "RoleRepositoryImplTestByRoleId@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));

        Long userID = user.getUserID();
        user = userRepo.findById(userID);
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));

        Role role = new Role("RoleRepositoryImplTest ByRoleId", "RoleRepositoryImplTest ByRoleId Desc");
        role = roleRepo.save(role);
        assertNotNull(role);
        assertThat(role.getRoleID(), greaterThan(0));

        Integer roleID = role.getRoleID();

        role = roleRepo.findById(roleID);
        assertNotNull(role);
        assertThat(role.getRoleID(), equalTo(roleID));

        UserRole userRole = new UserRole(roleID, userID);
        userRole = userRoleRepository.save(userRole);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), greaterThan(0L));

        Long userRoleID = userRole.getUserRoleID();

        userRole = userRoleRepository.findById(userRoleID);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(userRoleID));

        List<UserRole> userRolesList = userRoleRepository.findByRoleId(roleID);
        assertNotNull(userRolesList);
        assertThat(userRolesList.size(), equalTo(1));

        userRoleRepository.delete(userRoleID);
        userRole = userRoleRepository.findById(userRoleID);
        assertNull(userRole);

        roleRepo.delete(roleID);
        role = roleRepo.findById(roleID);
        assertNull(role);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }

    @Test
    public void testFindAllNullReturn() throws QMeException {
        when(userRoleSpringDataRepositoryMOCK.findAll()).thenReturn(null);
        List<UserRole> userRoles = userRoleRepositoryWithMock.findAll();
        verify(userRoleSpringDataRepositoryMOCK).findAll();
        assertNotNull(userRoles);
        assertThat(userRoles.size(), equalTo(0));
    }

    @Test(expected = QMeException.class)
    public void testFindAllQMeException() throws QMeException {
        when(userRoleSpringDataRepositoryMOCK.findAll()).thenThrow(new RuntimeException("some error"));
        userRoleRepositoryWithMock.findAll();
        verify(userRoleSpringDataRepositoryMOCK).findAll();
    }

    @Test(expected = QMeException.class)
    public void testFindByUserIdQMeException() throws QMeException {
        when(userRoleSpringDataRepositoryMOCK.findByUserId(1L)).thenThrow(new RuntimeException("some error"));
        userRoleRepositoryWithMock.findByUserId(1L);
        verify(userRoleSpringDataRepositoryMOCK).findByUserId(1L);
    }

    @Test(expected = QMeException.class)
    public void testFindByRoleIdQMeException() throws QMeException {
        when(userRoleSpringDataRepositoryMOCK.findByRoleId(1)).thenThrow(new RuntimeException("some error"));
        userRoleRepositoryWithMock.findByRoleId(1);
        verify(userRoleSpringDataRepositoryMOCK).findByRoleId(1);
    }

    @Test(expected = QMeException.class)
    public void testFindByIdQMeException() throws QMeException {
        when(userRoleSpringDataRepositoryMOCK.findOne(1L)).thenThrow(new RuntimeException("some error"));
        userRoleRepositoryWithMock.findById(1L);
        verify(userRoleSpringDataRepositoryMOCK).findOne(1L);
    }

    @Test(expected = QMeException.class)
    public void testSaveQMeException() throws QMeException {
        when(userRoleSpringDataRepositoryMOCK.save(Matchers.<UserRolesEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userRoleRepositoryWithMock.save(new UserRole(1, 1L));
        verify(userRoleSpringDataRepositoryMOCK).save(Matchers.<UserRolesEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testUpdateQMeException() throws QMeException {
        when(userRoleSpringDataRepositoryMOCK.save(Matchers.<UserRolesEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userRoleRepositoryWithMock.update(new UserRole(1, 1L), 1L);
        verify(userRoleSpringDataRepositoryMOCK).save(Matchers.<UserRolesEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testDeleteQMeException() throws QMeException {
        doThrow(new RuntimeException("some error")).when(userRoleSpringDataRepositoryMOCK).delete(1L);
        userRoleRepositoryWithMock.delete(1L);
        verify(userRoleSpringDataRepositoryMOCK).delete(1L);
    }

}
