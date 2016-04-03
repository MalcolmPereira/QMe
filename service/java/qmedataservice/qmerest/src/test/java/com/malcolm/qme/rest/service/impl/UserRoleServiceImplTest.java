/**
 * Name      : com.malcolm.qme.rest.service.impl.UserRoleServiceImplTest.java
 * Date      : 9/24/15
 * Developer : malcolm
 * Purpose   : User Role Service Implementation Test
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.UserRole;
import com.malcolm.qme.core.domain.fixtures.RoleFixtures;
import com.malcolm.qme.core.domain.fixtures.UserFixtures;
import com.malcolm.qme.core.domain.fixtures.UserRoleFixtures;
import com.malcolm.qme.core.repository.*;
import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeUserRole;
import com.malcolm.qme.rest.model.fixtures.QMeUserRoleFixtures;
import com.malcolm.qme.rest.service.UserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRoleServiceImplTest {

    @Mock
    private UserRoleRepository userRoleRepo;

    @Mock
    private RoleRepository roleRepo;

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private final UserRoleService userRoleService = new UserRoleServiceImpl();

    @Test
    public void testCount() throws Exception {
        when(userRoleRepo.count()).thenReturn(10L);
        Long roleCount = userRoleService.count();
        verify(userRoleRepo).count();
        assertThat(roleCount, equalTo(10L));
    }

    @Test(expected = QMeServerException.class)
    public void testCountQMeServerException() throws Exception {
        when(userRoleRepo.count()).thenThrow(QMeException.class);
        userRoleService.count();
        verify(userRoleRepo).count();
    }

    @Test
    public void testList() throws Exception {
        when(userRoleRepo.findAll()).thenReturn(UserRoleFixtures.simpleUserRoleList());

        List<QMeUserRole> userRoleList = userRoleService.list();

        verify(userRoleRepo).findAll();
        assertNotNull(userRoleList);
        assertThat(userRoleList.size(), equalTo(5));

        for (QMeUserRole qMeUserRole : userRoleList) {
            assertThat(qMeUserRole.getRoleID(), anyOf(
                            is(1),
                            is(2),
                            is(3),
                            is(4),
                            is(5)
            ));
            assertThat(qMeUserRole.getRoleName(), anyOf(
                    is("role name 1"),
                    is("role name 2"),
                    is("role name 3"),
                    is("role name 4"),
                    is("role name 5")
            ));
        }
    }

    @Test
    public void testListPaged() throws Exception {
        when(userRoleRepo.findAll()).thenReturn(UserRoleFixtures.simpleUserRoleList());
        List<QMeUserRole> userRoleList = userRoleService.list(0,10,true, "FIRSTNAME");
        verify(userRoleRepo).findAll();
        assertNotNull(userRoleList);
        assertThat(userRoleList.size(), equalTo(5));

        for (QMeUserRole qMeUserRole : userRoleList) {
            assertThat(qMeUserRole.getRoleID(), anyOf(
                    is(1),
                    is(2),
                    is(3),
                    is(4),
                    is(5)
            ));
            assertThat(qMeUserRole.getRoleName(), anyOf(
                    is("role name 1"),
                    is("role name 2"),
                    is("role name 3"),
                    is("role name 4"),
                    is("role name 5")
            ));
        }
    }

    @Test
    public void testListNullRoles() throws Exception {
        when(userRoleRepo.findAll()).thenReturn(null);

        List<QMeUserRole> userRoleList = userRoleService.list();

        verify(userRoleRepo).findAll();
        assertNotNull(userRoleList);
        assertThat(userRoleList.size(), equalTo(0));
    }

    @Test(expected = QMeServerException.class)
    public void testListQMeException() throws Exception {
        when(userRoleRepo.findAll()).thenThrow(QMeException.class);
        userRoleService.list();
    }

    @Test
    public void testFindByUserId() throws Exception {
        when(userRoleRepo.findByUserId(1L)).thenReturn(UserRoleFixtures.simpleUserRoleListForUser());

        List<QMeUserRole> userRoleList = userRoleService.findByUserId(1L);

        verify(userRoleRepo).findByUserId(1L);
        assertNotNull(userRoleList);
        assertThat(userRoleList.size(), equalTo(3));

        for (QMeUserRole qMeUserRole : userRoleList) {
            assertThat(qMeUserRole.getRoleID(), anyOf(
                            is(1),
                            is(2),
                            is(3)
            ));
            assertThat(qMeUserRole.getRoleName(), anyOf(
                    is("role name 1"),
                    is("role name 2"),
                    is("role name 3")
            ));
        }
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testFindByUserIdUserNotFound() throws Exception {
        when(userRoleRepo.findByUserId(1L)).thenReturn(null);
        userRoleService.findByUserId(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testFindByUserIdQMeException() throws Exception {
        when(userRoleRepo.findByUserId(1L)).thenThrow(QMeException.class);
        userRoleService.findByUserId(1L);
    }

    @Test
    public void testFindByRoleId() throws Exception {
        when(userRoleRepo.findByRoleId(1)).thenReturn(UserRoleFixtures.simpleUserRoleListForRole());

        List<QMeUserRole> userRoleList = userRoleService.findByRoleId(1);

        verify(userRoleRepo).findByRoleId(1);
        assertNotNull(userRoleList);
        assertThat(userRoleList.size(), equalTo(3));

        for (QMeUserRole qMeUserRole : userRoleList) {
            assertThat(qMeUserRole.getRoleID(), anyOf(
                    is(1),
                    is(2),
                    is(3)
            ));
            assertThat(qMeUserRole.getRoleName(), anyOf(
                    is("role name 1"),
                    is("role name 2"),
                    is("role name 3")
            ));
        }
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testFindByRoleIdRoleNotFound() throws Exception {
        when(userRoleRepo.findByRoleId(1)).thenReturn(null);
        userRoleService.findByRoleId(1);
        verify(userRoleRepo).findByRoleId(1);
    }

    @Test(expected = QMeServerException.class)
    public void testFindByRoleIdRoleQMeException() throws Exception {
        when(userRoleRepo.findByRoleId(1)).thenThrow(QMeException.class);
        userRoleService.findByRoleId(1);
        verify(userRoleRepo).findByRoleId(1);
    }


    @Test
    public void testSearchById() throws Exception {
        when(userRoleRepo.findById(1L)).thenReturn(UserRoleFixtures.simpleUserRole());

        QMeUserRole userRole = userRoleService.searchById(1L);

        verify(userRoleRepo).findById(1L);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(1L));
        assertThat(userRole.getRoleID(), equalTo(1));
        assertThat(userRole.getRoleName(), equalTo("role name"));
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testSearchByIdNotFound() throws Exception {
        when(userRoleRepo.findById(1L)).thenReturn(null);
        userRoleService.searchById(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testSearchByIdQMeException() throws Exception {
        when(userRoleRepo.findById(1L)).thenThrow(QMeException.class);
        userRoleService.searchById(1L);
    }

    @Test
    public void testSave() throws Exception {
        when(roleRepo.findById(1)).thenReturn(RoleFixtures.simpleRole());
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        when(userRoleRepo.findByUserId(1L)).thenReturn(UserRoleFixtures.simpleUserRoleListForUserDiff());
        when(userRoleRepo.save(Matchers.<UserRole>anyObject())).thenReturn(UserRoleFixtures.simpleUserRole());

        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();
        userRole = userRoleService.save(userRole,1L);

        verify(roleRepo).findById(1);
        verify(userRepo).findById(1L);
        verify(userRoleRepo).findByUserId(1L);
        verify(userRoleRepo).save(Matchers.<UserRole>anyObject());
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(1L));
        assertThat(userRole.getRoleID(), equalTo(1));
        assertThat(userRole.getRoleName(), equalTo("role name"));
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidRoleId() throws Exception {
        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();
        userRole.setRoleID(null);
        userRoleService.save(userRole,1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidUserId() throws Exception {
        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();
        userRole.setUserID(null);
        userRoleService.save(userRole, 1L);
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testSaveNoRoleById() throws Exception {
        when(roleRepo.findById(1)).thenReturn(null);
        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();
        userRoleService.save(userRole, 1L);
        verify(roleRepo).findById(1);
    }

    @Test(expected = QMeServerException.class)
    public void testSaveGetUserQMeException() throws Exception {
        when(roleRepo.findById(1)).thenThrow(QMeException.class);
        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();
        userRoleService.save(userRole, 1L);
        verify(roleRepo).findById(1);
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testSaveNoUserById() throws Exception {
        when(roleRepo.findById(1)).thenReturn(RoleFixtures.simpleRole());
        when(userRepo.findById(1L)).thenReturn(null);
        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();
        userRoleService.save(userRole, 1L);
        verify(roleRepo).findById(1);
        verify(userRepo).findById(1L);
    }

    @Test(expected = QMeResourceConflictException.class)
    public void testSaveSameRolesForUser() throws Exception {
        when(roleRepo.findById(1)).thenReturn(RoleFixtures.simpleRole());
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        when(userRoleRepo.findByUserId(1L)).thenReturn(UserRoleFixtures.simpleUserRoleListForUser());

        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();
        userRoleService.save(userRole,1L);
        verify(roleRepo).findById(1);
        verify(userRepo).findById(1L);
        verify(userRoleRepo).findByUserId(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testSaveQMeException() throws Exception {
        when(roleRepo.findById(1)).thenReturn(RoleFixtures.simpleRole());
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        when(userRoleRepo.findByUserId(1L)).thenReturn(UserRoleFixtures.simpleUserRoleListForUserDiff());
        when(userRoleRepo.save(Matchers.<UserRole>anyObject())).thenThrow(QMeException.class);

        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();
        userRoleService.save(userRole,1L);

        verify(roleRepo).findById(1);
        verify(userRepo).findById(1L);
        verify(userRoleRepo).findByUserId(1L);
        verify(userRoleRepo).save(Matchers.<UserRole>anyObject());
    }

    @Test
    public void testUpdate() throws Exception {
        when(roleRepo.findById(1)).thenReturn(RoleFixtures.simpleRole());
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        when(userRoleRepo.findByUserId(1L)).thenReturn(UserRoleFixtures.simpleUserRoleListForUserDiff());
        when(userRoleRepo.update(Matchers.<UserRole>anyObject(), eq(1L))).thenReturn(UserRoleFixtures.simpleUserRole());
        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();

        userRole = userRoleService.update(userRole,1L,1L);

        verify(roleRepo).findById(1);
        verify(userRepo).findById(1L);
        verify(userRoleRepo).findByUserId(1L);
        verify(userRoleRepo).update(Matchers.<UserRole>anyObject(), eq(1L));
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(1L));
        assertThat(userRole.getRoleID(), equalTo(1));
        assertThat(userRole.getRoleName(), equalTo("role name"));
    }

    @Test(expected = QMeServerException.class)
    public void testUpdateQMeException() throws Exception {
        when(roleRepo.findById(1)).thenReturn(RoleFixtures.simpleRole());
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        when(userRoleRepo.findByUserId(1L)).thenReturn(UserRoleFixtures.simpleUserRoleListForUserDiff());
        when(userRoleRepo.update(Matchers.<UserRole>anyObject(), eq(1L))).thenThrow(QMeException.class);
        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();

        userRole = userRoleService.update(userRole,1L,1L);

        verify(roleRepo).findById(1);
        verify(userRepo).findById(1L);
        verify(userRoleRepo).findByUserId(1L);
        verify(userRoleRepo).update(Matchers.<UserRole>anyObject(), eq(1L));
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(1L));
        assertThat(userRole.getRoleID(), equalTo(1));
        assertThat(userRole.getRoleName(), equalTo("role name"));
    }

    @Test
    public void testDelete() throws Exception {
        when(userRoleRepo.findById(1L)).thenReturn(UserRoleFixtures.simpleUserRole());
        doNothing().when(userRoleRepo).delete(1L);
        userRoleService.delete(1L);
        verify(userRoleRepo).findById(1L);
        verify(userRoleRepo).delete(1L);
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        when(userRoleRepo.findById(1L)).thenReturn(null);
        userRoleService.delete(1L);
        verify(userRoleRepo).findById(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testDeleteQMeException() throws Exception {
        when(userRoleRepo.findById(1L)).thenThrow(QMeException.class);
        userRoleService.delete(1L);
        verify(userRoleRepo).findById(1L);
    }
}